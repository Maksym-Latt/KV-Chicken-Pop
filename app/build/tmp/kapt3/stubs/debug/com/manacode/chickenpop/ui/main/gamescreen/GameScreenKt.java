package com.manacode.chickenpop.ui.main.gamescreen;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u001aN\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0003\u001a\u0018\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0003\u001a$\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a\b\u0010\u0012\u001a\u00020\u0001H\u0003\u001a\b\u0010\u0013\u001a\u00020\u0001H\u0003\u001aT\u0010\u0014\u001a\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u00162\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a&\u0010\u0019\u001a\u00020\u00012\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0007\u001a\u0016\u0010\u001d\u001a\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\"\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rH\u0003\u001a\u0010\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u0005H\u0003\u001a6\u0010$\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\u0006\u0010%\u001a\u00020\u0005H\u0003\u00a8\u0006&"}, d2 = {"ChickenCell", "", "chicken", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel$Chicken;", "combo", "", "effect", "Lcom/manacode/chickenpop/ui/main/gamescreen/CellEffect;", "onTap", "Lkotlin/Function0;", "onEffectConsumed", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "ChickenSprite", "speedLevel", "ComboPlank", "EffectOverlay", "FarmSlotDecor", "GameBackground", "GameBoard", "chickens", "", "effects", "onTapSlot", "GameScreen", "onExitToMenu", "viewModel", "Lcom/manacode/chickenpop/ui/main/gamescreen/GameViewModel;", "MenuButton", "onPause", "ScoreBoard", "score", "timeSeconds", "SpeedBadge", "level", "TopHud", "speed", "app_debug"})
public final class GameScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void GameScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onExitToMenu, @org.jetbrains.annotations.NotNull()
    com.manacode.chickenpop.ui.main.gamescreen.GameViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TopHud(int timeSeconds, int score, int combo, kotlin.jvm.functions.Function0<kotlin.Unit> onPause, int speed) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ScoreBoard(int score, int timeSeconds, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MenuButton(kotlin.jvm.functions.Function0<kotlin.Unit> onPause) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ComboPlank(int combo) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SpeedBadge(int level) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GameBoard(java.util.List<com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken> chickens, int combo, java.util.List<com.manacode.chickenpop.ui.main.gamescreen.CellEffect> effects, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onTapSlot, kotlin.jvm.functions.Function1<? super com.manacode.chickenpop.ui.main.gamescreen.CellEffect, kotlin.Unit> onEffectConsumed) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ChickenCell(com.manacode.chickenpop.ui.main.gamescreen.GameViewModel.Chicken chicken, int combo, com.manacode.chickenpop.ui.main.gamescreen.CellEffect effect, kotlin.jvm.functions.Function0<kotlin.Unit> onTap, kotlin.jvm.functions.Function1<? super com.manacode.chickenpop.ui.main.gamescreen.CellEffect, kotlin.Unit> onEffectConsumed, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EffectOverlay(com.manacode.chickenpop.ui.main.gamescreen.CellEffect effect, kotlin.jvm.functions.Function1<? super com.manacode.chickenpop.ui.main.gamescreen.CellEffect, kotlin.Unit> onEffectConsumed) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FarmSlotDecor() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ChickenSprite(int speedLevel, int combo) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void GameBackground() {
    }
}