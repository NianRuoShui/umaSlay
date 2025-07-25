package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import umamusume.powers.UmaPaceEndPower;
import umamusume.powers.UmaPaceFrontPower;
import umamusume.powers.UmaPaceLatePower;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

public class PowerfulStrike extends CustomCard {
    public static final String ID = "UmaMod:PowerfulStrike";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK; // 卡牌类型（攻击、技能、能力）
    private static final CardColor COLOR = Uma_Oguri_Orange; // 卡牌颜色（与角色颜色一致）
    private static final CardRarity RARITY = CardRarity.COMMON; // 卡牌稀有度（基础、普通、罕见、稀有、特殊等）
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int CONDITIONAL_COST_F = 1;
    private static final int BASE_DAMAGE = 8;
    private static final int BASE_MAGIC_NUMBER = 2;


    public PowerfulStrike() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = BASE_DAMAGE; //在跑法为先行时生效
        this.baseMagicNumber = BASE_MAGIC_NUMBER;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(-1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PowerfulStrike();
    }


    @Override
    public void applyPowers(){
        //如果升级了
        this.baseDamage = this.upgraded ? (BASE_DAMAGE + 2) : BASE_DAMAGE;
        this.baseMagicNumber = this.upgraded ? (BASE_MAGIC_NUMBER - 1) : BASE_MAGIC_NUMBER;
        super.applyPowers();

        if (AbstractDungeon.player.hasPower(UmaPaceFrontPower.POWER_ID)){
            this.damage += 2;
            this.isDamageModified = false;
            this.magicNumber += 2;
            this.setCostForTurn(CONDITIONAL_COST_F);
        } else if (AbstractDungeon.player.hasPower(UmaPaceLatePower. POWER_ID)) {
            this.damage -= 2;
            this.magicNumber -= 1;
            this.isDamageModified = false;
        } else if (AbstractDungeon.player.hasPower(UmaPaceEndPower.POWER_ID)) {
            this.damage -= 4;
            this.magicNumber -= 2;
            this.isDamageModified = false;
        }
        else{
        }
    }


    @Override
    public void calculateCardDamage(AbstractMonster m){
        super.calculateCardDamage(m);
        this.baseDamage = this.upgraded ? (BASE_DAMAGE + 2) : BASE_DAMAGE;
        if (AbstractDungeon.player.hasPower(UmaPaceFrontPower.POWER_ID)){
            this.damage += 2;
            this.isDamageModified = false;
            this.magicNumber += 2;
            this.setCostForTurn(CONDITIONAL_COST_F);
        } else if (AbstractDungeon.player.hasPower(UmaPaceEndPower.POWER_ID)) {
            this.damage -= 4;
            this.magicNumber -= 2;
            this.isDamageModified = false;
        }else {
            this.damage = this.baseDamage;
            this.magicNumber = this.baseMagicNumber;
            this.isDamageModified = false;
        }
    }

    /*
    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(UmaPacePacePower.POWER_ID)) {
            this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

     */

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new ApplyPowerAction(p, p, new FrailPower(p,this.magicNumber,false), this.magicNumber));
    }
}
