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

    override fun playMenuMusic() {
        // Placeholder implementation - no bundled music per requirements.
    }

    override fun playGameMusic() {
        // Placeholder implementation - no bundled music per requirements.
    }

    override fun stopMusic() {
        // No-op placeholder.
    }

    override fun pauseMusic() {
        // No-op placeholder.
    }

    override fun resumeMusic() {
        // No-op placeholder.
    }

    override fun setMusicVolume(percent: Int) {
        musicVolume = percent.toVolume()
    }

    override fun setSoundVolume(percent: Int) {
        soundVolume = percent.toVolume()
    }

    override fun playGameWin() {
        // Placeholder implementation - sound effects disabled.
    }

    private fun Int.toVolume(): Float = (this.coerceIn(0, 100) / 100f).coerceIn(0f, 1f)
}
