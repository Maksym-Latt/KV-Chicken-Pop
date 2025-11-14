package com.manacode.chickenpop.ui.main.gamescreen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u0000 +2\u00020\u0001:\u0005)*+,-B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0014J\u0006\u0010\u001d\u001a\u00020\u001aJ\u0006\u0010\u001e\u001a\u00020\u001aJ\u0006\u0010\u001f\u001a\u00020\u001aJ\b\u0010 \u001a\u00020\u001aH\u0002J\u0006\u0010!\u001a\u00020\u001aJ\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\nJ\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u0015H\u0002J\u0010\u0010\'\u001a\u00020(2\u0006\u0010&\u001a\u00020\u0015H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GameUiState;", "activeChickens", "", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$ActiveChicken;", "bestCombo", "", "combo", "nextId", "spawnJob", "Lkotlinx/coroutines/Job;", "speedLevel", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "timeLeft", "", "timerJob", "currentSpawnDelay", "currentVisibleDuration", "finishEarly", "", "finishGame", "onCleared", "openPause", "resume", "retry", "spawnChicken", "startGame", "tap", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome;", "slotIndex", "tick", "elapsed", "updateChickens", "", "ActiveChicken", "Chicken", "Companion", "GameUiState", "TapOutcome", "app_debug"})
public final class GameViewModel extends androidx.lifecycle.ViewModel {
    private static final long TOTAL_TIME = 60000L;
    private static final int GRID_SIZE = 12;
    private static final long BASE_SPAWN_DELAY = 1050L;
    private static final long MIN_SPAWN_DELAY = 320L;
    private static final long BASE_VISIBLE_TIME = 1150L;
    private static final long MIN_VISIBLE_TIME = 420L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.ActiveChicken> activeChickens = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job spawnJob;
    private long timeLeft = 60000L;
    private int combo = 0;
    private int bestCombo = 0;
    private int speedLevel = 0;
    private int nextId = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Companion Companion = null;
    
    public GameViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState> getState() {
        return null;
    }
    
    public final void startGame() {
    }
    
    private final void tick(long elapsed) {
    }
    
    private final boolean updateChickens(long elapsed) {
        return false;
    }
    
    private final long currentSpawnDelay() {
        return 0L;
    }
    
    private final long currentVisibleDuration() {
        return 0L;
    }
    
    private final void spawnChicken() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome tap(int slotIndex) {
        return null;
    }
    
    public final void openPause() {
    }
    
    public final void resume() {
    }
    
    public final void finishEarly() {
    }
    
    public final void retry() {
    }
    
    private final void finishGame() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\u0006\u0010\u001f\u001a\u00020 R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\r\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000b\u00a8\u0006!"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$ActiveChicken;", "", "id", "", "slotIndex", "remaining", "", "lifetime", "speedLevel", "(IIJJI)V", "getId", "()I", "getLifetime", "()J", "getRemaining", "setRemaining", "(J)V", "getSlotIndex", "getSpeedLevel", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "toUi", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Chicken;", "app_debug"})
    static final class ActiveChicken {
        private final int id = 0;
        private final int slotIndex = 0;
        private long remaining;
        private final long lifetime = 0L;
        private final int speedLevel = 0;
        
        public ActiveChicken(int id, int slotIndex, long remaining, long lifetime, int speedLevel) {
            super();
        }
        
        public final int getId() {
            return 0;
        }
        
        public final int getSlotIndex() {
            return 0;
        }
        
        public final long getRemaining() {
            return 0L;
        }
        
        public final void setRemaining(long p0) {
        }
        
        public final long getLifetime() {
            return 0L;
        }
        
        public final int getSpeedLevel() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken toUi() {
            return null;
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final long component3() {
            return 0L;
        }
        
        public final long component4() {
            return 0L;
        }
        
        public final int component5() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.ActiveChicken copy(int id, int slotIndex, long remaining, long lifetime, int speedLevel) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Chicken;", "", "id", "", "slotIndex", "speedLevel", "(III)V", "getId", "()I", "getSlotIndex", "getSpeedLevel", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class Chicken {
        private final int id = 0;
        private final int slotIndex = 0;
        private final int speedLevel = 0;
        
        public Chicken(int id, int slotIndex, int speedLevel) {
            super();
        }
        
        public final int getId() {
            return 0;
        }
        
        public final int getSlotIndex() {
            return 0;
        }
        
        public final int getSpeedLevel() {
            return 0;
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken copy(int id, int slotIndex, int speedLevel) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Companion;", "", "()V", "BASE_SPAWN_DELAY", "", "BASE_VISIBLE_TIME", "GRID_SIZE", "", "MIN_SPAWN_DELAY", "MIN_VISIBLE_TIME", "TOTAL_TIME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u0010J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0007H\u00c6\u0003J\t\u0010*\u001a\u00020\u0007H\u00c6\u0003J\t\u0010+\u001a\u00020\u0007H\u00c6\u0003Ji\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010-\u001a\u00020\u00032\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\u0007H\u00d6\u0001J\t\u00100\u001a\u000201H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012\u00a8\u00062"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GameUiState;", "", "isRunning", "", "remainingMillis", "", "score", "", "chickens", "", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Chicken;", "showPause", "showGameOver", "combo", "bestCombo", "speedLevel", "(ZJILjava/util/List;ZZIII)V", "getBestCombo", "()I", "getChickens", "()Ljava/util/List;", "getCombo", "()Z", "remainingFraction", "", "getRemainingFraction", "()F", "getRemainingMillis", "()J", "remainingSeconds", "getRemainingSeconds", "getScore", "getShowGameOver", "getShowPause", "getSpeedLevel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
    public static final class GameUiState {
        private final boolean isRunning = false;
        private final long remainingMillis = 0L;
        private final int score = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> chickens = null;
        private final boolean showPause = false;
        private final boolean showGameOver = false;
        private final int combo = 0;
        private final int bestCombo = 0;
        private final int speedLevel = 0;
        
        public GameUiState(boolean isRunning, long remainingMillis, int score, @org.jetbrains.annotations.NotNull()
        java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> chickens, boolean showPause, boolean showGameOver, int combo, int bestCombo, int speedLevel) {
            super();
        }
        
        public final boolean isRunning() {
            return false;
        }
        
        public final long getRemainingMillis() {
            return 0L;
        }
        
        public final int getScore() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> getChickens() {
            return null;
        }
        
        public final boolean getShowPause() {
            return false;
        }
        
        public final boolean getShowGameOver() {
            return false;
        }
        
        public final int getCombo() {
            return 0;
        }
        
        public final int getBestCombo() {
            return 0;
        }
        
        public final int getSpeedLevel() {
            return 0;
        }
        
        public final int getRemainingSeconds() {
            return 0;
        }
        
        public final float getRemainingFraction() {
            return 0.0F;
        }
        
        public GameUiState() {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final long component2() {
            return 0L;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> component4() {
            return null;
        }
        
        public final boolean component5() {
            return false;
        }
        
        public final boolean component6() {
            return false;
        }
        
        public final int component7() {
            return 0;
        }
        
        public final int component8() {
            return 0;
        }
        
        public final int component9() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState copy(boolean isRunning, long remainingMillis, int score, @org.jetbrains.annotations.NotNull()
        java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> chickens, boolean showPause, boolean showGameOver, int combo, int bestCombo, int speedLevel) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome;", "", "()V", "Hit", "Miss", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome$Hit;", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome$Miss;", "app_debug"})
    public static abstract class TapOutcome {
        
        private TapOutcome() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome$Hit;", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome;", "combo", "", "chickenId", "(II)V", "getChickenId", "()I", "getCombo", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
        public static final class Hit extends com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome {
            private final int combo = 0;
            private final int chickenId = 0;
            
            public Hit(int combo, int chickenId) {
            }
            
            public final int getCombo() {
                return 0;
            }
            
            public final int getChickenId() {
                return 0;
            }
            
            public final int component1() {
                return 0;
            }
            
            public final int component2() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome.Hit copy(int combo, int chickenId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome$Miss;", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome;", "()V", "app_debug"})
        public static final class Miss extends com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome {
            @org.jetbrains.annotations.NotNull()
            public static final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome.Miss INSTANCE = null;
            
            private Miss() {
            }
        }
    }
}