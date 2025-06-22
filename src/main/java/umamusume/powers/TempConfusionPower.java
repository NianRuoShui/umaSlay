package umamusume.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//一回合混乱
public class TempConfusionPower extends ConfusionPower {
    public static final String POWER_ID = "UmaMod:TemporaryConfusionPower";

    public TempConfusionPower(AbstractCreature owner) {
        super(owner);
        this.type = PowerType.BUFF;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
}