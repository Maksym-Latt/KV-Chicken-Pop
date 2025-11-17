package com.manacode.chickenpop.audio;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016J\b\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u000bH\u0016J\f\u0010\u001a\u001a\u00020\b*\u00020\u0017H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController;", "Lcom/manacode/chickenpop/audio/AudioController;", "settingsRepository", "Lcom/manacode/chickenpop/data/settings/SettingsRepository;", "(Lcom/manacode/chickenpop/data/settings/SettingsRepository;)V", "currentMusic", "Lcom/manacode/chickenpop/audio/DefaultAudioController$MusicTrack;", "musicVolume", "", "soundVolume", "pauseMusic", "", "playGameMusic", "playGameWin", "playMenuMusic", "playMusic", "track", "playSound", "effect", "Lcom/manacode/chickenpop/audio/DefaultAudioController$SoundCue;", "resumeMusic", "setMusicVolume", "percent", "", "setSoundVolume", "stopMusic", "toVolume", "MusicTrack", "SoundCue", "app_debug"})
public final class DefaultAudioController implements com.manacode.chickenpop.audio.AudioController {
    private float musicVolume;
    private float soundVolume;
    @org.jetbrains.annotations.Nullable()
    private com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack currentMusic;
    
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
    
    private final void playMusic(com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack track) {
    }
    
    private final void playSound(com.manacode.chickenpop.audio.DefaultAudioController.SoundCue effect) {
    }
    
    private final float toVolume(int $this$toVolume) {
        return 0.0F;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController$MusicTrack;", "", "assetPath", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAssetPath", "()Ljava/lang/String;", "MenuTheme", "GameLoop", "app_debug"})
    static enum MusicTrack {
        /*public static final*/ MenuTheme /* = new MenuTheme(null) */,
        /*public static final*/ GameLoop /* = new GameLoop(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String assetPath = null;
        
        MusicTrack(java.lang.String assetPath) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAssetPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController$SoundCue;", "", "assetPath", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getAssetPath", "()Ljava/lang/String;", "VictoryFanfare", "ChickenHit", "RareChicken", "ChickenEscape", "app_debug"})
    static enum SoundCue {
        /*public static final*/ VictoryFanfare /* = new VictoryFanfare(null) */,
        /*public static final*/ ChickenHit /* = new ChickenHit(null) */,
        /*public static final*/ RareChicken /* = new RareChicken(null) */,
        /*public static final*/ ChickenEscape /* = new ChickenEscape(null) */;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String assetPath = null;
        
        SoundCue(java.lang.String assetPath) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getAssetPath() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.manacode.chickenpop.audio.DefaultAudioController.SoundCue> getEntries() {
            return null;
        }
    }
}