package umamusume.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class FrontRunnerPower extends AbstractPower{
    public static final String POWER_ID = "UmaMod:FrontRunnerPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

//先
    public FrontRunnerPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false; // 这是一个状态，不是基于回合的

        String path128 = "umaResources/img/UI/utx_ico_obtain_02.png";
        String path48 = "umaResources/img/UI/utx_ico_obtain_02.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.description = DESCRIPTIONS[0];
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        // 每回合敏捷
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 1), 1));
    }

//     @Override
//     public float atDamageGive(float damage, DamageInfo.DamageType type) {
// //      jia shang
//         return damage + (float)this.amount;
//     }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
