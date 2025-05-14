package umamusume.reward;

import basemod.BaseMod;
import basemod.abstracts.CustomReward;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rewards.RewardSave;
import umamusume.reward.patch.FoodCardPatch;
import java.util.List;
import java.util.stream.Collectors;



public class UmaFoodReward extends CustomReward {
    private static final Texture ICON = new Texture(Gdx.files.internal("umaResources/img/relics/OguriFood.png"));
    private static final String ID = "UmaMod:RewardUIFoodCard";
    private static final UIStrings UiString = CardCrawlGame.languagePack.getUIString(ID);


    public UmaFoodReward(String CardString) {
        super(ICON, UiString.TEXT[0], FoodCardPatch.TypePatch.UMA_FOOD_CARD);

        AbstractCard card = CardLibrary.getCard(CardString);
        if (card != null) {
            init(card.makeCopy());
        } //局内奖励封装（不确定可行与否）
    }


    public UmaFoodReward(){
        super(ICON , UiString.TEXT[0] , FoodCardPatch.TypePatch.UMA_FOOD_CARD);
        init(UmaFoodCardRolling());
    }

    public static void register() {
        BaseMod.registerCustomReward(FoodCardPatch.TypePatch.UMA_FOOD_CARD , rewardSave -> new UmaFoodReward(rewardSave.id), customReward -> customReward.cards.isEmpty() ? new RewardSave(customReward.type.toString(), "") : new RewardSave(customReward.type.toString() , ((AbstractCard)customReward.cards.get(0)).cardID));
    }

     public static void addUmaFoodReward() {
         UmaFoodReward umaFoodReward = new UmaFoodReward();
         if (((RewardItem) umaFoodReward).cards.isEmpty())
             return;
         AbstractDungeon.getCurrRoom().addCardReward((RewardItem) umaFoodReward);
     }


    private void init(AbstractCard card){
        this.cards.clear(); //清除card list？
        if (card == null) {
            return; //woid
        }
        for (AbstractRelic relic : AbstractDungeon.player.relics){ //遍历player's relic
            relic.onPreviewObtainCard(card);
        }
        this.cards.add(card);

    }

    public boolean claimReward(){
        if (this.cards.isEmpty()){
            return true;
        }
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD){
            AbstractDungeon.cardRewardScreen.open(this.cards , (RewardItem)this, UiString.TEXT[1]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD; //定义Reward界面
        }
        return false;
    }


    public static AbstractCard UmaFoodCardRolling(){
       List<AbstractCard> card_list = AbstractDungeon.srcCommonCardPool.group;
       int b = AbstractDungeon.cardRandomRng.random(card_list.size() - 1);
       return ((AbstractCard)card_list.get(b).makeCopy());
    }



}
