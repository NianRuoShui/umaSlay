package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import umamusume.powers.UmaPacePacePower;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

public class VictoryLeap extends CustomCard {

    public static final String ID = "UmaMod:VictoryLeap";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "umaResources/img/cards/strike.png";

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Uma_Oguri_Orange;

    private static final int COST = 3;
    private static final int CONDITIONAL_COST = 1;
    private static final int BASE_DAMAGE = 20;
    private static final int UPGRADE_PLUS_DMG = 5;

    public VictoryLeap() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = BASE_DAMAGE;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new VictoryLeap();
    }


    @Override
    public void applyPowers() {
        // 重置基础伤害，然后调用父类方法应用力量等效果
        this.baseDamage = this.upgraded ? (BASE_DAMAGE + UPGRADE_PLUS_DMG) : BASE_DAMAGE;
        super.applyPowers();

        // 使用辅助方法检查条件
        if (AbstractDungeon.player.hasPower(UmaPacePacePower.POWER_ID)) {
            this.damage *= 2;
            this.isDamageModified = true;
            this.setCostForTurn(CONDITIONAL_COST);
        } else {
            // 确保在条件不满足时，费用恢复正常
            this.setCostForTurn(COST);
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        // 重置基础伤害，然后调用父类方法应用易伤等效果
        this.baseDamage = this.upgraded ? (BASE_DAMAGE + UPGRADE_PLUS_DMG) : BASE_DAMAGE;
        super.calculateCardDamage(mo);
        if (AbstractDungeon.player.hasPower(UmaPacePacePower.POWER_ID)) {
            this.damage *= 2;
            this.isDamageModified = true;
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        if (AbstractDungeon.player.hasPower(UmaPacePacePower.POWER_ID)) {
            this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}