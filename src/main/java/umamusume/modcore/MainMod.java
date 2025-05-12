package umamusume.modcore;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;

import umamusume.cards.Defend;
import umamusume.cards.Strike;
import umamusume.characters.uma;
import com.badlogic.gdx.graphics.Color;
import static umamusume.characters.uma.PlayerColorEnum.uma_blue;
import static umamusume.characters.uma.PlayerColorEnum.UMA_CHARACTER;

@SpireInitializer //语法糖 总之就是加载mod
public class MainMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber {
    //建立一个公共public类class，叫MainMod，要求实现EditCardsSubscriber所定义的方法
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "umaResources/img/cards/strike.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "umaResources/img/cards/strike.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "umaResources/img/cards/strike.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "umaResources/img/cards/strike.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "umaResources/img/cards/strike.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "umaResources/img/cards/strike.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "umaResources/img/cards/strike.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "umaResources/img/cards/strike.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "umaResources/img/cards/strike.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "umaResources/img/cards/strike.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "umaResources/img/cards/strike.png";
    // 颜色
    public static final Color MY_COLOR = new Color(129.0F / 255.0F, 232.0F / 255.0F, 9.0F / 225.0F, 1.0F);

    //类MainMod的构造方法，也就是这个类被创建时会被自动调用
    public MainMod() {
        BaseMod.subscribe(this);//通知BaseMod，要求把我们自己(this)进行注册
        BaseMod.addColor(uma_blue, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);

        //addColor(AbstractCard.CardColor color, Color bgColor, Color backColor, Color frameColor, Color frameOutlineColor, Color descBoxColor, Color trailVfxColor, Color glowColor, String attackBg, String skillBg, String powerBg, String energyOrb, String attackBgPortrait, String skillBgPortrait, String powerBgPortrait, String energyOrbPortrait, String cardEnergyOrb)
    }

    //初始化
    public static void initialize() {
        new MainMod();
    }

    //编辑卡牌的代码
    public void receiveEditCards() {
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
    }
    //本地化
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ZHS";
            // lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "umaResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "umaResources/localization/" + lang + "/characters.json");
    }

    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new uma(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, UMA_CHARACTER);
    }

}

