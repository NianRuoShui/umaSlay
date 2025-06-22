package umamusume.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import umamusume.powers.umaPower;

public class TrainerStopwatch extends CustomRelic {

    public static final String ID = "UmaMod:TrainerStopwatch";
    private static final String IMG_PATH = "umaResources/img/relics/OguriFood.png";
    private static final RelicTier RELIC_TIER = RelicTier.COMMON;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public TrainerStopwatch() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        this.addToBot(
                new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new umaPower(AbstractDungeon.player, 2), 2
        ));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}