package umamusume.modcore;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;

import com.megacrit.cardcrawl.localization.RelicStrings;
import umamusume.cards.Defend;
import umamusume.cards.FoodBurgerMeat;
import umamusume.cards.Strike;
import umamusume.cards.StrikeAll;
import umamusume.characters.Oguri;
import umamusume.relics.OguriRelicFood;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import static umamusume.characters.Oguri.PlayerColorEnum.UMA_OGURI;

@SpireInitializer //语法糖 总之就是加载mod
public class MainMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {//建立一个公共public类class，叫MainMod，要求实现EditCardsSubscriber所定义的方法

    //类MainMod的构造方法，也就是这个类被创建时会被自动调用
    public MainMod() {
        BaseMod.subscribe(this);//通知BaseMod，要求把我们自己(this)进行注册
        BaseMod.addColor(Uma_Oguri_Orange,
                ResourceManager.UMA_COLOR.cpy(),
                ResourceManager.UMA_COLOR.cpy(),
                ResourceManager.UMA_COLOR.cpy(),
                ResourceManager.UMA_COLOR.cpy(),
                ResourceManager.UMA_COLOR.cpy(),
                ResourceManager.UMA_COLOR.cpy(),
                ResourceManager.UMA_COLOR.cpy(),
                ResourceManager.BG_ATTACK_512,
                ResourceManager.BG_SKILL_512,
                ResourceManager.BG_POWER_512,
                ResourceManager.ENEYGY_ORB,
                ResourceManager.BG_ATTACK_1024,
                ResourceManager.BG_SKILL_1024,
                ResourceManager.BG_POWER_1024,
                ResourceManager.BIG_ORB,
                ResourceManager.SMALL_ORB
        );

    }

    //初始化
    public static void initialize() {
        new MainMod();
    }

    //编辑卡牌的代码
    public void receiveEditCards() {
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new StrikeAll());
        BaseMod.addCard(new FoodBurgerMeat());
    }
    //本地化
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "umaResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "umaResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "umaResources/localization/" + lang + "/relics.json");
    }

    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new Oguri(CardCrawlGame.playerName),
                ResourceManager.UMA_OGURI_BUTTON,
                ResourceManager.UMA_OGURI_PORTRAIT,
                UMA_OGURI
        );

    }
    public void receiveEditRelics() {
        BaseMod.addRelic(new OguriRelicFood(), RelicType.SHARED); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
    }

}

