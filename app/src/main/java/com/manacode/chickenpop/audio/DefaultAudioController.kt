package com.manacode.chickenpop.audio

import javax.inject.Inject
import javax.inject.Singleton
import com.manacode.chickenpop.data.settings.SettingsRepository

@Singleton
class DefaultAudioController @Inject constructor(
    settingsRepository: SettingsRepository
) : AudioController {

    private var musicVolume: Float = settingsRepository.getMusicVolume().toVolume()
    private var soundVolume: Float = settingsRepository.getSoundVolume().toVolume()

    private var currentMusic: MusicTrack? = null

    override fun playMenuMusic() {
        playMusic(MusicTrack.MenuTheme)
    }

    override fun playGameMusic() {
        playMusic(MusicTrack.GameLoop)
    }

    override fun stopMusic() {
        currentMusic = null
    }

    override fun pauseMusic() {
        // Stub implementation – actual audio engine not bundled.
    }

    override fun resumeMusic() {
        // Stub implementation – actual audio engine not bundled.
    }

    override fun setMusicVolume(percent: Int) {
        musicVolume = percent.toVolume()
    }

    override fun setSoundVolume(percent: Int) {
        soundVolume = percent.toVolume()
    }

    override fun playGameWin() {
        playSound(SoundCue.VictoryFanfare)
    }

    private fun playMusic(track: MusicTrack) {
        currentMusic = track
        // A real implementation would stream the asset referenced in track.assetPath at musicVolume.
    }

    private fun playSound(effect: SoundCue) {
        // A real implementation would trigger the asset referenced in effect.assetPath at soundVolume.
    }

    private fun Int.toVolume(): Float = (this.coerceIn(0, 100) / 100f).coerceIn(0f, 1f)

    private enum class MusicTrack(val assetPath: String) {
        MenuTheme("audio/music/menu_theme.ogg"),
        GameLoop("audio/music/game_loop.ogg")
    }

    private enum class SoundCue(val assetPath: String) {
        VictoryFanfare("audio/sfx/victory_fanfare.wav"),
        ChickenHit("audio/sfx/chicken_hit.wav"),
        RareChicken("audio/sfx/rare_chicken.wav"),
        ChickenEscape("audio/sfx/chicken_escape.wav")
    }
}
