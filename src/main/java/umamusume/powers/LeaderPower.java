package umamusume.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LeaderPower extends AbstractPower {
    public static final String POWER_ID = "UmaMod:LeaderPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//逃
    public LeaderPower(AbstractCreature owner) {
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
        this.flash();
        // 易伤
//        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VulnerablePower(this.owner, 1, false), 1));
        // 所有敌人造成5点伤害
        // THORNS表纯效果伤害 不受力量加持
        this.addToBot(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(5, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

//    @Override
//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        return damage + (float)this.amount;
//    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
