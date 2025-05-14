package umamusume.patch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import umamusume.powers.umaPower;
@SpirePatch(
    clz = AbstractMonster.class,
    method = "die",
    paramtypez = {}
)
public class MonsterDeathPatch {
    @SpirePostfixPatch
    public static void onMonsterDie() {
        // 遍历玩家所有Power，调用onDeath
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof umaPower) {
                ((umaPower)power).onDeath();
            }
        }
    }
}