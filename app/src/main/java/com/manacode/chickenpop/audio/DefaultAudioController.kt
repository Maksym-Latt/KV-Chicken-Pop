package com.manacode.chickenpop.audio

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import com.manacode.chickenpop.R
import javax.inject.Inject
import javax.inject.Singleton
import com.manacode.chickenpop.data.settings.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext

@Singleton
class DefaultAudioController @Inject constructor(
    @ApplicationContext private val context: Context,
    settingsRepository: SettingsRepository
) : AudioController {

    private var musicVolume: Float = settingsRepository.getMusicVolume().toVolume()
    private var soundVolume: Float = settingsRepository.getSoundVolume().toVolume()

    private var currentMusic: MusicTrack? = null
    private var musicPlayer: MediaPlayer? = null
    private var sfxPlayer: MediaPlayer? = null

    // ---------------------- PUBLIC API ----------------------

    override fun playMenuMusic() {
        playMusic(MusicTrack.MenuTheme)
    }

    override fun playGameMusic() {
        playMusic(MusicTrack.GameLoop)
    }

    override fun stopMusic() {
        musicPlayer?.run {
            stop()
            release()
        }
        musicPlayer = null
        currentMusic = null
    }

    override fun pauseMusic() {
        musicPlayer?.takeIf { it.isPlaying }?.pause()
    }

    override fun resumeMusic() {
        musicPlayer?.start()
    }

    override fun setMusicVolume(percent: Int) {
        musicVolume = percent.toVolume()
        musicPlayer?.setVolume(musicVolume, musicVolume)
    }

    override fun setSoundVolume(percent: Int) {
        soundVolume = percent.toVolume()
        // новый звук возьмёт уже обновлённое значение
    }

    override fun playGameWin() {
        playSound(SoundCue.VictoryFanfare)
    }

    override fun playChickenHit() {
        playSound(SoundCue.ChickenHit)
    }

    override fun playRareChicken() {
        playSound(SoundCue.RareChicken)
    }

    override fun playChickenEscape() {
        playSound(SoundCue.ChickenEscape)
    }

    // ---------------------- INTERNAL IMPLEMENTATION ----------------------

    private fun playMusic(track: MusicTrack) {
        if (currentMusic == track && musicPlayer != null) {
            musicPlayer?.setVolume(musicVolume, musicVolume)
            if (musicPlayer?.isPlaying != true) {
                musicPlayer?.start()
            }
            return
        }

        stopMusic()

        musicPlayer = MediaPlayer.create(context, track.resId).apply {
            isLooping = true
            setVolume(musicVolume, musicVolume)
            setOnCompletionListener(null)
            start()
        }
        currentMusic = track
    }

    private fun playSound(effect: SoundCue) {
        sfxPlayer?.run {
            stop()
            release()
        }
        sfxPlayer = MediaPlayer.create(context, effect.resId).apply {
            isLooping = false
            setVolume(soundVolume, soundVolume)
            setOnCompletionListener {
                it.release()
                if (sfxPlayer === it) {
                    sfxPlayer = null
                }
            }
            start()
        }
    }

    private fun Int.toVolume(): Float = (this.coerceIn(0, 100) / 100f).coerceIn(0f, 1f)

    // ---------------------- ENUMS ПОД res/raw ----------------------

    private enum class MusicTrack(@RawRes val resId: Int) {
        // res/raw/menu_theme.mp3
        MenuTheme(R.raw.menu_theme),

        // res/raw/game_loop.mp3
        GameLoop(R.raw.game_loop)
    }

    private enum class SoundCue(@RawRes val resId: Int) {
        VictoryFanfare(R.raw.sfx_victory_fanfare),

        ChickenHit(R.raw.sfx_chicken_hit),

        RareChicken(R.raw.sfx_rare_chicken),

        ChickenEscape(R.raw.sfx_chicken_escape)
    }
}
