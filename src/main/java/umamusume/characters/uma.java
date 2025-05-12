package umamusume.characters;

import static umamusume.characters.uma.PlayerColorEnum.uma_blue;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.brashmonkey.spriter.Player;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.relics.Vajra;
import com.megacrit.cardcrawl.screens.CharSelectInfo;

import basemod.abstracts.CustomPlayer;
import umamusume.cards.Defend;
import umamusume.cards.Strike;
import umamusume.modcore.MainMod;

public class uma extends CustomPlayer{
    private static final String UMA_CHARACTER_SHOULDER_1 = "umaResources/img/char/shoulder1.png";
    private static final String UMA_CHARACTER_SHOULDER_2 = "umaResources/img/char/shoulder1.png";
    private static final String COPRSE_IMAGE = "umaResources/img/char/shoulder1.png";
    private static final String[] ORB_TEXTURES = new String[]{
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",

    };
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("umaMod:MyCharacter");
    public uma(String name){
        super(name, PlayerColorEnum.UMA_CHARACTER, ORB_TEXTURES,"umaResources/img/char/shoulder1.png", LAYER_SPEED, null, null);
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);
        this.initializeClass(
                "umaResources/img/char/shoulder1.png", // 人物图片
                UMA_CHARACTER_SHOULDER_2, UMA_CHARACTER_SHOULDER_1,
                COPRSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );
    }
    // 初始卡组的ID，可直接写或引用变量
    public ArrayList<String> getStartingDeck(){
        ArrayList<String> retVal = new ArrayList<>();
        for(int x = 0; x <5 ; x++){
            retVal.add(Strike.ID);
            retVal.add(Defend.ID);
        };
        retVal.add("umaMod:Strike");
        retVal.add("umaMod:Defend");
        return retVal;
    }
    //遗物
    public ArrayList<String> getStartingRelics(){
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Vajra.ID);
        return retVal;
    }

    public CharSelectInfo getLoadout(){
        return new CharSelectInfo(
            characterStrings.NAMES[0],
            characterStrings.TEXT[0],
            75, 
            75,
            0,
            99,
            5,
            this, //教程说别动
            this.getStartingRelics(), 
            this.getStartingDeck(),
            false //教程说别动
        );
    }

    public String getTitle(PlayerClass playerClass){
        return characterStrings.NAMES[0];
    }

    public AbstractCard.CardColor getCardColor(){
        return uma_blue;
    }
    public AbstractCard getStartCardForEvent(){
        return new Strike();
    }
    public Color getCardTrailColor(){
        return MainMod.MY_COLOR;
    }
    public int getAscensionMaxHPLoss(){
        return 5;
    }
    public BitmapFont getEnergyNumFont(){
        return FontHelper.energyNumFontBlue;
    }
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("umaResources/img/char/shoulder1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("umaResources/img/char/shoulder1.png"));
        panels.add(new CutscenePanel("umaResources/img/char/shoulder1.png"));
        return panels;
    }
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }
    public AbstractPlayer newInstance() {
        return new uma(this.name);
    }
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }
    public Color getSlashAttackColor() {
        return MainMod.MY_COLOR;
    }
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }
    public Color getCardRenderColor() {
        return MainMod.MY_COLOR;
    }
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }
    public static class PlayerColorEnum{
        @SpireEnum
        public static PlayerClass UMA_CHARACTER;

        @SpireEnum
        public static AbstractCard.CardColor uma_blue;
    }

    public static class PlayerLibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType uma_blue;
    }
}