package umamusume.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;


import static umamusume.characters.Oguri.PlayerTagsEnum.Uma_Oguri_food;


public class DiamondHeadwear extends CustomRelic {

    public static final String ID = "UmaMod:DiamondHeadwear";
    private static final int CARDS_TO_DRAW = 1;
    private static final String IMG_PATH = "umaResources/img/relics/OguriFood.png";
    private static final RelicTier RELIC_TIER = RelicTier.RARE;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;
    public DiamondHeadwear() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.tags.contains(Uma_Oguri_food)) {
            this.flash();
            this.addToBot(new DrawCardAction(CARDS_TO_DRAW));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}