package com.manacode.chickenpop.ui.main.gamescreen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0004\u001b\u001c\u001d\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0014J\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\rJ\u0006\u0010\u0011\u001a\u00020\rJ\u0006\u0010\u0012\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\rJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\f\u0010\u0018\u001a\u00020\u0019*\u00020\u001aH\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001f"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GameUiState;", "engine", "Lcom/manacode/chickenpop/game/GameEngine;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "exitToMenu", "", "onCleared", "pause", "pauseAndOpenSettings", "resume", "retry", "startGame", "tap", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome;", "slotIndex", "", "toUiChicken", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Chicken;", "Lcom/manacode/chickenpop/game/GameEngine$Chicken;", "Chicken", "GamePhase", "GameUiState", "TapOutcome", "app_debug"})
public final class GameViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.manacode.chickenpop.game.GameEngine engine = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState> state = null;
    
    public GameViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState> getState() {
        return null;
    }
    
    public final void startGame() {
    }
    
    public final void pause() {
    }
    
    public final void pauseAndOpenSettings() {
    }
    
    public final void resume() {
    }
    
    public final void retry() {
    }
    
    public final void exitToMenu() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome tap(int slotIndex) {
        return null;
    }
    
    private final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken toUiChicken(com.manacode.chickenpop.game.GameEngine.Chicken $this$toUiChicken) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0006H\u00c6\u0003J\'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u0014\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0019"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Chicken;", "", "id", "", "slotIndex", "type", "Lcom/manacode/chickenpop/game/GameEngine$ChickenType;", "(IILcom/manacode/chickenpop/game/GameEngine$ChickenType;)V", "getId", "()I", "isRare", "", "()Z", "getSlotIndex", "getType", "()Lcom/manacode/chickenpop/game/GameEngine$ChickenType;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "app_debug"})
    public static final class Chicken {
        private final int id = 0;
        private final int slotIndex = 0;
        @org.jetbrains.annotations.NotNull()
        private final com.manacode.chickenpop.game.GameEngine.ChickenType type = null;
        
        public Chicken(int id, int slotIndex, @org.jetbrains.annotations.NotNull()
        com.manacode.chickenpop.game.GameEngine.ChickenType type) {
            super();
        }
        
        public final int getId() {
            return 0;
        }
        
        public final int getSlotIndex() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.game.GameEngine.ChickenType getType() {
            return null;
        }
        
        public final boolean isRare() {
            return false;
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int component2() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.game.GameEngine.ChickenType component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken copy(int id, int slotIndex, @org.jetbrains.annotations.NotNull()
        com.manacode.chickenpop.game.GameEngine.ChickenType type) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GamePhase;", "", "(Ljava/lang/String;I)V", "Intro", "Running", "Paused", "Result", "app_debug"})
    public static enum GamePhase {
        /*public static final*/ Intro /* = new Intro() */,
        /*public static final*/ Running /* = new Running() */,
        /*public static final*/ Paused /* = new Paused() */,
        /*public static final*/ Result /* = new Result() */;
        
        GamePhase() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GamePhase> getEntries() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B[\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0007H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\u0007H\u00c6\u0003J_\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\u0007H\u00d6\u0001J\t\u0010+\u001a\u00020,H\u00d6\u0001R\u0011\u0010\f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011R\u0011\u0010\r\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GameUiState;", "", "phase", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GamePhase;", "remainingMillis", "", "score", "", "chickens", "", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Chicken;", "combo", "bestCombo", "speedLevel", "rareHits", "(Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GamePhase;JILjava/util/List;IIII)V", "getBestCombo", "()I", "getChickens", "()Ljava/util/List;", "getCombo", "getPhase", "()Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$GamePhase;", "getRareHits", "getRemainingMillis", "()J", "remainingSeconds", "getRemainingSeconds", "getScore", "getSpeedLevel", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
    public static final class GameUiState {
        @org.jetbrains.annotations.NotNull()
        private final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GamePhase phase = null;
        private final long remainingMillis = 0L;
        private final int score = 0;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> chickens = null;
        private final int combo = 0;
        private final int bestCombo = 0;
        private final int speedLevel = 0;
        private final int rareHits = 0;
        
        public GameUiState(@org.jetbrains.annotations.NotNull()
        com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GamePhase phase, long remainingMillis, int score, @org.jetbrains.annotations.NotNull()
        java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> chickens, int combo, int bestCombo, int speedLevel, int rareHits) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GamePhase getPhase() {
            return null;
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
        
        public final int getCombo() {
            return 0;
        }
        
        public final int getBestCombo() {
            return 0;
        }
        
        public final int getSpeedLevel() {
            return 0;
        }
        
        public final int getRareHits() {
            return 0;
        }
        
        public final int getRemainingSeconds() {
            return 0;
        }
        
        public GameUiState() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GamePhase component1() {
            return null;
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
        
        public final int component5() {
            return 0;
        }
        
        public final int component6() {
            return 0;
        }
        
        public final int component7() {
            return 0;
        }
        
        public final int component8() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GameUiState copy(@org.jetbrains.annotations.NotNull()
        com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.GamePhase phase, long remainingMillis, int score, @org.jetbrains.annotations.NotNull()
        java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> chickens, int combo, int bestCombo, int speedLevel, int rareHits) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome$Hit;", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$TapOutcome;", "chickenId", "", "type", "Lcom/manacode/chickenpop/game/GameEngine$ChickenType;", "combo", "(ILcom/manacode/chickenpop/game/GameEngine$ChickenType;I)V", "getChickenId", "()I", "getCombo", "getType", "()Lcom/manacode/chickenpop/game/GameEngine$ChickenType;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
        public static final class Hit extends com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome {
            private final int chickenId = 0;
            @org.jetbrains.annotations.NotNull()
            private final com.manacode.chickenpop.game.GameEngine.ChickenType type = null;
            private final int combo = 0;
            
            public Hit(int chickenId, @org.jetbrains.annotations.NotNull()
            com.manacode.chickenpop.game.GameEngine.ChickenType type, int combo) {
            }
            
            public final int getChickenId() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.manacode.chickenpop.game.GameEngine.ChickenType getType() {
                return null;
            }
            
            public final int getCombo() {
                return 0;
            }
            
            public final int component1() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.manacode.chickenpop.game.GameEngine.ChickenType component2() {
                return null;
            }
            
            public final int component3() {
                return 0;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.TapOutcome.Hit copy(int chickenId, @org.jetbrains.annotations.NotNull()
            com.manacode.chickenpop.game.GameEngine.ChickenType type, int combo) {
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