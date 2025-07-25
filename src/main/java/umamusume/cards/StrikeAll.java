package umamusume.cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import com.megacrit.cardcrawl.cards.AbstractCard;

//全体攻击
public class StrikeAll extends CustomCard {
    public static final String ID = "UmaMod:StrikeAll"; //卡牌ID
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID); //本地化描述
    private static final String NAME = CARD_STRINGS.NAME; //卡牌名称，从本地化抽取
    private static final String IMG_PATH = "umaResources/img/cards/strike.png"; //图片
    private static final int COST = 1; //花费
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; //卡牌描述，从本地化中读取
    private static final CardType TYPE = CardType.ATTACK; // 卡牌类型（攻击、技能、能力）
    private static final CardColor COLOR = Uma_Oguri_Orange; // 卡牌颜色（与角色颜色一致）
    private static final CardRarity RARITY = CardRarity.BASIC; // 卡牌稀有度（基础、普通、罕见、稀有、特殊等）
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY; // 卡牌目标（敌人、所有敌人、自身、无目标等）

    public StrikeAll() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 4; // 基础伤害值
        this.tags.add(CardTags.STRIKE); // 标记为打击牌（用于其他卡牌效果联动）
        this.isMultiDamage = true;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName(); //升级名称 加“+”
            this.upgradeDamage(3); //加伤害
            this.initializeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAllEnemiesAction(
                p, this.multiDamage, this.damageTypeForTurn,AbstractGameAction.AttackEffect.BLUNT_LIGHT  )
        );


        // 遍历当前房间中的所有敌人
//        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
//            // 对每个敌人造成伤害
//            this.addToBot(
//                new DamageAction(
//                    monster, //敌人
//                    new DamageInfo(p, this.damage, this.damageTypeForTurn), //伤害信息
//                    AbstractGameAction.AttackEffect.BLUNT_LIGHT)); //效果
//        }
    }
    @Override
    public  AbstractCard makeCopy() {
        return new StrikeAll();
    }
}
