package com.manacode.chickenpop.audio;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u0000 )2\u00020\u0001:\u0003)*+B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0016J\b\u0010\u0017\u001a\u00020\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\bH\u0002J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\nH\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0016J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\u001b\u0010#\u001a\u00020\u000e*\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u000eH\u0002\u00a2\u0006\u0002\u0010%J\f\u0010&\u001a\u00020\u000e*\u00020\bH\u0002J\f\u0010\'\u001a\u00020\u000e*\u00020\nH\u0002J\f\u0010(\u001a\u00020\u000e*\u00020 H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController;", "Lcom/manacode/chickenpop/audio/AudioController;", "context", "Landroid/content/Context;", "settingsRepository", "Lcom/manacode/chickenpop/data/settings/SettingsRepository;", "(Landroid/content/Context;Lcom/manacode/chickenpop/data/settings/SettingsRepository;)V", "currentMusic", "Lcom/manacode/chickenpop/audio/DefaultAudioController$MusicTrack;", "currentSound", "Lcom/manacode/chickenpop/audio/DefaultAudioController$SoundCue;", "musicPlayer", "Landroid/media/MediaPlayer;", "musicVolume", "", "sfxPlayer", "soundVolume", "pauseMusic", "", "playChickenEscape", "playChickenHit", "playGameMusic", "playGameWin", "playMenuMusic", "playMusic", "track", "playRareChicken", "playSound", "effect", "resumeMusic", "setMusicVolume", "percent", "", "setSoundVolume", "stopMusic", "adjustWith", "gain", "(FLjava/lang/Float;)F", "normalizedMusicVolume", "normalizedSoundVolume", "toVolume", "Companion", "MusicTrack", "SoundCue", "app_debug"})
public final class DefaultAudioController implements com.manacode.chickenpop.audio.AudioController {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private float musicVolume;
    private float soundVolume;
    @org.jetbrains.annotations.Nullable()
    private com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack currentMusic;
    @org.jetbrains.annotations.Nullable()
    private com.manacode.chickenpop.audio.DefaultAudioController.SoundCue currentSound;
    @org.jetbrains.annotations.Nullable()
    private android.media.MediaPlayer musicPlayer;
    @org.jetbrains.annotations.Nullable()
    private android.media.MediaPlayer sfxPlayer;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack, java.lang.Float> MUSIC_NORMALIZATION = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<com.manacode.chickenpop.audio.DefaultAudioController.SoundCue, java.lang.Float> SOUND_NORMALIZATION = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.manacode.chickenpop.audio.DefaultAudioController.Companion Companion = null;
    
    @javax.inject.Inject()
    public DefaultAudioController(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
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
    
    @java.lang.Override()
    public void playChickenHit() {
    }
    
    @java.lang.Override()
    public void playRareChicken() {
    }
    
    @java.lang.Override()
    public void playChickenEscape() {
    }
    
    private final void playMusic(com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack track) {
    }
    
    private final void playSound(com.manacode.chickenpop.audio.DefaultAudioController.SoundCue effect) {
    }
    
    private final float toVolume(int $this$toVolume) {
        return 0.0F;
    }
    
    private final float normalizedMusicVolume(com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack $this$normalizedMusicVolume) {
        return 0.0F;
    }
    
    private final float normalizedSoundVolume(com.manacode.chickenpop.audio.DefaultAudioController.SoundCue $this$normalizedSoundVolume) {
        return 0.0F;
    }
    
    private final float adjustWith(float $this$adjustWith, java.lang.Float gain) {
        return 0.0F;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController$Companion;", "", "()V", "MUSIC_NORMALIZATION", "", "Lcom/manacode/chickenpop/audio/DefaultAudioController$MusicTrack;", "", "SOUND_NORMALIZATION", "Lcom/manacode/chickenpop/audio/DefaultAudioController$SoundCue;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController$MusicTrack;", "", "resId", "", "(Ljava/lang/String;II)V", "getResId", "()I", "MenuTheme", "GameLoop", "app_debug"})
    static enum MusicTrack {
        /*public static final*/ MenuTheme /* = new MenuTheme(0) */,
        /*public static final*/ GameLoop /* = new GameLoop(0) */;
        private final int resId = 0;
        
        MusicTrack(@androidx.annotation.RawRes()
        int resId) {
        }
        
        public final int getResId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.manacode.chickenpop.audio.DefaultAudioController.MusicTrack> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lcom/manacode/chickenpop/audio/DefaultAudioController$SoundCue;", "", "resId", "", "(Ljava/lang/String;II)V", "getResId", "()I", "VictoryFanfare", "ChickenHit", "RareChicken", "ChickenEscape", "app_debug"})
    static enum SoundCue {
        /*public static final*/ VictoryFanfare /* = new VictoryFanfare(0) */,
        /*public static final*/ ChickenHit /* = new ChickenHit(0) */,
        /*public static final*/ RareChicken /* = new RareChicken(0) */,
        /*public static final*/ ChickenEscape /* = new ChickenEscape(0) */;
        private final int resId = 0;
        
        SoundCue(@androidx.annotation.RawRes()
        int resId) {
        }
        
        public final int getResId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.manacode.chickenpop.audio.DefaultAudioController.SoundCue> getEntries() {
            return null;
        }
    }
}