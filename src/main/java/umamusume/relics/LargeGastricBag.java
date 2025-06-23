package umamusume.relics;

import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import static umamusume.characters.Oguri.PlayerTagsEnum.Uma_Oguri_food;

public class LargeGastricBag extends CustomRelic {

    public static final String ID = "UmaMod:LargeGastricBag";
    private static final String IMG_PATH = "umaResources/img/relics/OguriFood.png";
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.HEAVY;
    private static final int REQUIRED_FOOD_CARDS = 25;

    public LargeGastricBag() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atTurnStart() {
        if (isConditionMet()) {
            this.flash();
            this.addToBot(new GainEnergyAction(1));
        }
    }

    // 这是一个辅助方法，用于判断条件是否达成，让代码更清晰
    private boolean isConditionMet() {
        int foodCardCount = 0;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.tags.contains(Uma_Oguri_food)) {
                foodCardCount++;
            }
        }
        // 如果食物牌的数量大于或等于要求，返回true
        return foodCardCount >= REQUIRED_FOOD_CARDS;
    }
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}