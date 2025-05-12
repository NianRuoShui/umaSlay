package umamusume.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class OguriRelicFood extends CustomRelic {
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
    public void atBattleStart() {
        super.atBattleStart();
        this.addToBot(new DrawCardAction(3));
    }
}
