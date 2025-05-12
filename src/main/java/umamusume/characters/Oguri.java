package umamusume.characters;

//import static umamusume.characters.uma.PlayerColorEnum.uma_blue;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import umamusume.cards.Strike;
import umamusume.modcore.MainMod;
import umamusume.modcore.ResourceManager;

public class Oguri extends CustomPlayer {
    // 火堆的人物立绘（行动前）
    private static final String UMA_OGURI_SHOULDER_1 = "umaResources/img/char/shoulder1.png";
    // 火堆的人物立绘（行动）
    private static final String UMA_OGURI_SHOULDER_2 = "umaResources/img/char/shoulder1.png";
    // 人物死亡图像
    private static final String COPRSE_IMAGE = "umaResources/img/char/shoulder1.png";
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",
            "umaResources/img/char/shoulder1.png",

    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};
    // 人物的本地化文本
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("UmaMod:Oguri");

    public Oguri (String name) {
        super(name, PlayerColorEnum.UMA_OGURI, ORB_TEXTURES, "umaResources/img/char/shoulder1.png", LAYER_SPEED, null, null);
        // 人物对话气泡的大小
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);
        // 初始化你的人物
        this.initializeClass(
                "umaResources/img/char/shoulder1.png", // 人物图片
                UMA_OGURI_SHOULDER_2, UMA_OGURI_SHOULDER_1,
                COPRSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );
    }

    // 初始卡组的ID，可直接写或引用变量
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        /*
        for(int x = 0; x < 5 ; x++){
            retVal.add(Strike.ID);
            retVal.add(Defend.ID);
        };
         */
        retVal.add("UmaMod:Strike");
        retVal.add("UmaMod:Defend");
        retVal.add("UmaMod:Strike");
        retVal.add("UmaMod:Defend");
        retVal.add("UmaMod:Strike");
        retVal.add("UmaMod:Defend");
        return retVal;
    }

    //遗物
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(Vajra.ID);//金刚杵
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                characterStrings.NAMES[0], //人物名
                characterStrings.TEXT[0], //人物介绍
                75, // 当前血量
                75, // 最大血量
                0,// 初始充能球栏位
                99,// 初始携带金币
                5,// 每回合抽牌数量
                this, //教程说别动
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(),// 初始卡组
                false //教程说别动
        );
    }

    // 人物名字
    public String getTitle(PlayerClass playerClass) {
        return characterStrings.NAMES[0];
    }

    // 卡牌颜色
    public AbstractCard.CardColor getCardColor() {
        return PlayerColorEnum.Uma_Oguri_Orange;
    }

    // 翻牌事件出现的你的职业牌
    public AbstractCard getStartCardForEvent() {
        return new Strike();
    }

    // 卡牌轨迹颜色
    public Color getCardTrailColor() {
        return ResourceManager.UMA_COLOR;
    }

    // 高进阶带来的生命值损失
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // 卡牌的能量字体
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.screenShake.shake(
                ScreenShake.ShakeIntensity.MED,
                ScreenShake.ShakeDur.SHORT,
                false
        );
    }

    // 碎心图片
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("umaResources/img/char/shoulder1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("umaResources/img/char/shoulder1.png"));
        panels.add(new CutscenePanel("umaResources/img/char/shoulder1.png"));
        return panels;
    }

    // 自定义模式选择你的人物时播放的音效
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_HEAVY";
    }

    // 游戏中左上角显示在你的名字之后的人物名称
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    // 创建人物实例
    public AbstractPlayer newInstance() {
        return new Oguri(this.name);
    }

    // 第三章面对心脏说的话
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    // 打心脏的颜色
    public Color getSlashAttackColor() {
        return ResourceManager.UMA_COLOR;
    }

    // 吸血鬼事件文本
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    // 卡牌选择界面选择该牌的颜色
    public Color getCardRenderColor() {
        return ResourceManager.UMA_COLOR;
    }

    // 第三章面对心脏造成伤害时的特效
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        };
    }

    // 人物枚举、卡牌颜色枚举扩展
    public static class PlayerColorEnum {
        @SpireEnum
        public static PlayerClass UMA_OGURI;

        @SpireEnum
        public static AbstractCard.CardColor Uma_Oguri_Orange;
    }

    public static class PlayerLibraryEnum {
        @SpireEnum
        public static CardLibrary.LibraryType Uma_Oguri_Orange;
    }

}
