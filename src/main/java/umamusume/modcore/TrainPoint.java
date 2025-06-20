package umamusume.modcore;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class TrainPoint {
    public static final String POWER_ID = "UmaMod:umaPower";
    public static int modifyByTrainPoint(AbstractCard card, int baseValue) {
        int training = 0;
        if (AbstractDungeon.player.hasPower(POWER_ID)) {
            training = AbstractDungeon.player.getPower(POWER_ID).amount;
        }
        int result = baseValue + training;
        return Math.max(result, 0);
    }
}