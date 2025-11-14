package com.manacode.chickenpop.ui.main.gamescreen

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    data class GameUiState(
        val isRunning: Boolean = false,
        val remainingMillis: Long = TOTAL_TIME,
        val score: Int = 0,
        val chickens: List<Chicken> = emptyList(),
        val showPause: Boolean = false,
        val showGameOver: Boolean = false,
        val combo: Int = 0,
        val bestCombo: Int = 0,
        val speedLevel: Int = 0
    ) {
        val remainingSeconds: Int get() = (remainingMillis.coerceAtLeast(0L) / 1000L).toInt()
        val remainingFraction: Float get() = (remainingMillis.coerceAtLeast(0L) / TOTAL_TIME.toFloat()).coerceIn(0f, 1f)
    }

    enum class ChickenPhase { Hidden, Peeking, Outside }

    data class Chicken(
        val id: Int,
        val slotIndex: Int,
        val speedLevel: Int,
        val phase: ChickenPhase
    )

    sealed class TapOutcome {
        data class Hit(val combo: Int, val chickenId: Int) : TapOutcome()
        object Miss : TapOutcome()
    }

    private data class ActiveChicken(
        val id: Int,
        val slotIndex: Int,
        var remaining: Long,
        val lifetime: Long,
        val speedLevel: Int
    ) {
        fun toUi(): Chicken = Chicken(
            id = id,
            slotIndex = slotIndex,
            speedLevel = speedLevel,
            phase = phase()
        )

        fun phase(): ChickenPhase {
            if (lifetime <= 0L) return ChickenPhase.Outside
            val clampedRemaining = remaining.coerceAtLeast(0L)
            val progress = (1f - clampedRemaining / lifetime.toFloat()).coerceIn(0f, 1f)
            return when {
                progress < 1f / 3f -> ChickenPhase.Hidden
                progress < 2f / 3f -> ChickenPhase.Peeking
                else -> ChickenPhase.Outside
            }
        }
    }

    companion object {
        private const val TOTAL_TIME: Long = 60_000L
        private const val GRID_SIZE = 12
        private const val BASE_SPAWN_DELAY = 1_050L
        private const val MIN_SPAWN_DELAY = 320L
        private const val BASE_VISIBLE_TIME = 1_150L
        private const val MIN_VISIBLE_TIME = 420L
    }

    private val _state = MutableStateFlow(GameUiState())
    val state: StateFlow<GameUiState> = _state

    private val activeChickens = mutableListOf<ActiveChicken>()
    private var timerJob: Job? = null
    private var spawnJob: Job? = null

    private var timeLeft = TOTAL_TIME
    private var combo = 0
    private var bestCombo = 0
    private var speedLevel = 0
    private var nextId = 0

    fun startGame() {
        timerJob?.cancel()
        spawnJob?.cancel()
        activeChickens.clear()
        timeLeft = TOTAL_TIME
        combo = 0
        bestCombo = 0
        speedLevel = 0
        nextId = 0
        _state.value = GameUiState(
            isRunning = true,
            remainingMillis = timeLeft,
            score = 0,
            chickens = emptyList(),
            showPause = false,
            showGameOver = false,
            combo = 0,
            bestCombo = 0,
            speedLevel = 0
        )

        timerJob = viewModelScope.launch {
            var previous = SystemClock.elapsedRealtime()
            while (isActive) {
                delay(16L)
                val now = SystemClock.elapsedRealtime()
                val delta = now - previous
                previous = now
                tick(delta)
            }
        }

        spawnJob = viewModelScope.launch {
            while (isActive) {
                val delayMs = currentSpawnDelay()
                delay(delayMs)
                if (!_state.value.isRunning || _state.value.showPause || _state.value.showGameOver) continue
                spawnChicken()
            }
        }
    }

    private fun tick(elapsed: Long) {
        val state = _state.value
        if (!state.isRunning || state.showPause || state.showGameOver) return
        if (timeLeft <= 0L) return

        timeLeft = (timeLeft - elapsed).coerceAtLeast(0L)
        val hadEscape = updateChickens(elapsed)
        if (hadEscape) combo = 0

        if (timeLeft <= 0L) {
            finishGame()
        } else {
            _state.update {
                it.copy(
                    remainingMillis = timeLeft,
                    chickens = activeChickens.map { chicken -> chicken.toUi() },
                    combo = combo,
                    bestCombo = bestCombo,
                    speedLevel = speedLevel
                )
            }
        }
    }

    private fun updateChickens(elapsed: Long): Boolean {
        var hadEscape = false
        val iterator = activeChickens.iterator()
        while (iterator.hasNext()) {
            val chicken = iterator.next()
            chicken.remaining -= elapsed
            if (chicken.remaining <= 0L) {
                iterator.remove()
                hadEscape = true
            }
        }
        if (hadEscape) {
            _state.update {
                it.copy(
                    chickens = activeChickens.map { chicken -> chicken.toUi() },
                    combo = 0
                )
            }
        }
        return hadEscape
    }

    private fun currentSpawnDelay(): Long {
        val reduction = speedLevel * 70L
        return (BASE_SPAWN_DELAY - reduction).coerceAtLeast(MIN_SPAWN_DELAY)
    }

    private fun currentVisibleDuration(): Long {
        val reduction = speedLevel * 55L
        return (BASE_VISIBLE_TIME - reduction).coerceAtLeast(MIN_VISIBLE_TIME)
    }

    private fun spawnChicken() {
        val availableSlots = (0 until GRID_SIZE).filter { slot ->
            activeChickens.none { it.slotIndex == slot }
        }
        if (availableSlots.isEmpty()) return
        val slot = availableSlots.random()
        val duration = currentVisibleDuration()
        activeChickens += ActiveChicken(
            id = nextId++,
            slotIndex = slot,
            remaining = duration,
            lifetime = duration,
            speedLevel = speedLevel
        )

        _state.update {
            it.copy(chickens = activeChickens.map { chicken -> chicken.toUi() })
        }
    }

    fun tap(slotIndex: Int): TapOutcome {
        val current = _state.value
        if (!current.isRunning || current.showPause || current.showGameOver) return TapOutcome.Miss
        val idx = activeChickens.indexOfFirst { it.slotIndex == slotIndex }
        if (idx == -1) {
            combo = 0
            _state.update { it.copy(combo = 0) }
            return TapOutcome.Miss
        }

        val chicken = activeChickens[idx]
        if (chicken.phase() != ChickenPhase.Outside) {
            combo = 0
            _state.update {
                it.copy(
                    combo = 0,
                    chickens = activeChickens.map { c -> c.toUi() }
                )
            }
            return TapOutcome.Miss
        }

        activeChickens.removeAt(idx)
        combo += 1
        if (combo > bestCombo) bestCombo = combo
        if (combo % 5 == 0) speedLevel = (speedLevel + 1).coerceAtMost(10)
        val scoreGain = 100 + chicken.speedLevel * 15

        _state.update {
            it.copy(
                score = it.score + scoreGain,
                chickens = activeChickens.map { c -> c.toUi() },
                combo = combo,
                bestCombo = bestCombo,
                speedLevel = speedLevel
            )
        }
        return TapOutcome.Hit(combo = combo, chickenId = chicken.id)
    }

    fun openPause() {
        if (!_state.value.isRunning || _state.value.showGameOver) return
        _state.update { it.copy(showPause = true) }
    }

    fun resume() {
        if (_state.value.showPause) {
            _state.update { it.copy(showPause = false) }
        }
    }

    fun finishEarly() {
        finishGame()
    }

    fun retry() {
        startGame()
    }

    private fun finishGame() {
        timerJob?.cancel()
        spawnJob?.cancel()
        activeChickens.clear()
        _state.update {
            it.copy(
                isRunning = false,
                showGameOver = true,
                remainingMillis = 0L,
                chickens = emptyList()
            )
        }
    }

    override fun onCleared() {
        timerJob?.cancel()
        spawnJob?.cancel()
        super.onCleared()
    }
}
