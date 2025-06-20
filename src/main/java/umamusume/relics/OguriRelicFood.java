package umamusume.relics;

import basemod.abstracts.CustomRelic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static umamusume.characters.Oguri.PlauerTagsEnum.Uma_Oguri_food;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class OguriRelicFood extends CustomRelic {
    // private boolean waitingForCardReward = false;
    public static final String ID = "UmaMod:OguriFood";
    private static final String IMG_PATH = "umaResources/img/relics/OguriFood.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public OguriRelicFood(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new OguriRelicFood();
    }

    // 战斗开始时触发
    public void atBattleStart() {
        System.out.println("遗物触发了！");
        int count = 1;
        super.atBattleStart();
        ArrayList<AbstractCard> foodCards = new ArrayList<>();
        // 遍历所有卡牌，筛选出带有 Uma_Oguri_food 标签的卡
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card.tags.contains(Uma_Oguri_food)) {
                foodCards.add(card);
            }
        }
        System.out.println("食物卡数量: " + foodCards.size());
        // for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
        //     if (card.tags.contains(Uma_Oguri_food)) {
        //         foodCards.add(card);
        //     }
        // }
        // 随机打乱食物卡顺序
        Collections.shuffle(foodCards, AbstractDungeon.cardRandomRng.random);
        int cardsToAdd = Math.min(3, foodCards.size());
        Random t = new Random();
        // 随机选取一张食物卡，加入手牌 for循环避免想加多个卡牌重写
        for (int i = 0; i < count; i++) {
            AbstractCard foodCard = foodCards.get(t.nextInt(cardsToAdd)).makeStatEquivalentCopy();
            this.addToBot(new MakeTempCardInHandAction(foodCard, 1)); // 将卡牌加入手牌
        }
    }
    // public void onVictory() {
    //     super.onVictory();
    //     // 获取所有food卡牌
    //     ArrayList<AbstractCard> foodCards = new ArrayList<>();
    //     for (AbstractCard card : CardLibrary.getAllCards()) {
    //         if (card.tags.contains(Uma_Oguri_food)) {
    //             foodCards.add(card);
    //         }
    //     }
    //     // 随机打乱卡牌顺序
    //     Collections.shuffle(foodCards, AbstractDungeon.cardRandomRng.random);
    //     // 取前三张卡牌作为奖励选项
    //     int cardsToShow = Math.min(3, foodCards.size());
    //     ArrayList<AbstractCard> rewardCards = new ArrayList<>(foodCards.subList(0, cardsToShow));
    //     // 显示奖励选择界面
    //     // AbstractDungeon.cardRewardScreen.chooseOneOpen(rewardCards);
    //     AbstractDungeon.cardRewardScreen.customCombatOpen(rewardCards, "选择一张食物卡加入卡组", true);
    //     waitingForCardReward = true;
    // }
    // @Override
    // public void update() {
    //     super.update();
    //     if (waitingForCardReward && AbstractDungeon.cardRewardScreen.discoveryCard != null) {
    //         AbstractCard chosen = AbstractDungeon.cardRewardScreen.discoveryCard;
    //         if (chosen != null) {
    //             AbstractDungeon.player.masterDeck.addToTop(chosen.makeStatEquivalentCopy());
    //             AbstractDungeon.cardRewardScreen.discoveryCard = null;
    //             waitingForCardReward = false;
    //         }
    //     }
    // }
}
