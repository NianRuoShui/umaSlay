package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import umamusume.powers.UmaPaceLatePower;


import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

public class ContinuousStrike extends CustomCard {
    public static final String ID = "UmaMod:ContinuousStrike";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK; // 卡牌类型（攻击、技能、能力）
    private static final CardColor COLOR = Uma_Oguri_Orange; // 卡牌颜色（与角色颜色一致）
    private static final CardRarity RARITY = CardRarity.COMMON; // 卡牌稀有度（基础、普通、罕见、稀有、特殊等）
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int BASE_DAMAGE = 4;
    private static final int BASE_MAGIC_NUMBER = 3;


    public ContinuousStrike(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = BASE_DAMAGE;
        this.tags.add(CardTags.STRIKE);
        this.magicNumber = BASE_MAGIC_NUMBER;
    }

    public void upgrade(){
        if(!upgraded){
          this.upgradeName();
          this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ContinuousStrike();
    }

    @Override
    public void applyPowers(){
        //如果升级了
        this.baseMagicNumber = this.upgraded ? (BASE_MAGIC_NUMBER + 1) : BASE_MAGIC_NUMBER;
        super.applyPowers();

        if (AbstractDungeon.player.hasPower(UmaPaceLatePower.POWER_ID)) {
            this.magicNumber -= 1;
            this.isDamageModified = true;
        }
         else{
            this.damage = this.baseDamage;
            this.magicNumber = this.baseMagicNumber;
        }
    }


    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        this.baseDamage = this.upgraded ? (BASE_DAMAGE + 2) : BASE_DAMAGE;
        if (AbstractDungeon.player.hasPower(UmaPaceLatePower.POWER_ID)) {
            this.magicNumber -= 1;
            this.isDamageModified = true;
        }
        else{
            this.damage = this.baseDamage;
            this.magicNumber = baseMagicNumber;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 1; i < this.magicNumber; ++i) {
            this.addToBot(new DamageAllEnemiesAction(p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        if (AbstractDungeon.player.hasPower(UmaPaceLatePower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 4, false), 4));
        } else {
            this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 2, false), 2));
        }
    }
}
