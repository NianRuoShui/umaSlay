package umamusume.modcore; //当前java文件被引用时的名字
import basemod.BaseMod;    //导入
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import umamusume.cards.Strike;

@SpireInitializer //语法糖 总之就是加载mod
public class MainMod implements EditCardsSubscriber, EditStringsSubscriber {
    //建立一个公共public类class，叫MainMod，要求实现EditCardsSubscriber所定义的方法

    //类MainMod的构造方法，也就是这个类被创建时会被自动调用
    public MainMod(){
        BaseMod.subscribe(this);//通知BaseMod，要求把我们自己(this)进行注册
    }
    //初始化
    public static void initialize(){

        new MainMod();
    }
    //编辑卡牌的代码
    public void receiveEditCards(){
        BaseMod.addCard(new Strike());
    }

    public void receiveEditStrings(){
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS){
            lang = "ZHS";
        }else{
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "resources/localization/" + lang + "/cards.json");
    }
}

