package com.manacode.chickenpop.audio;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016J\f\u0010\u0013\u001a\u00020\u0006*\u00020\u0010H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController;", "Lcom/manacode/chickenpop/audio/AudioController;", "settingsRepository", "Lcom/manacode/chickenpop/data/settings/SettingsRepository;", "(Lcom/manacode/chickenpop/data/settings/SettingsRepository;)V", "musicVolume", "", "soundVolume", "pauseMusic", "", "playGameMusic", "playGameWin", "playMenuMusic", "resumeMusic", "setMusicVolume", "percent", "", "setSoundVolume", "stopMusic", "toVolume", "app_debug"})
public final class DefaultAudioController implements com.manacode.chickenpop.audio.AudioController {
    private float musicVolume;
    private float soundVolume;
    
    @javax.inject.Inject()
    public DefaultAudioController(@org.jetbrains.annotations.NotNull()
    com.manacode.chickenpop.data.settings.SettingsRepository settingsRepository) {
        super();
    }
    
    @java.lang.Override()
    public void playMenuMusic() {
    }
    
    @java.lang.Override()
    public void playGameMusic() {
    }
    
    @java.lang.Override()
    public void stopMusic() {
    }
    
    @java.lang.Override()
    public void pauseMusic() {
    }
    
    @java.lang.Override()
    public void resumeMusic() {
    }
    
    @java.lang.Override()
    public void setMusicVolume(int percent) {
    }
    
    @java.lang.Override()
    public void setSoundVolume(int percent) {
    }
    
    @java.lang.Override()
    public void playGameWin() {
    }
    
    private final float toVolume(int $this$toVolume) {
        return 0.0F;
    }
}