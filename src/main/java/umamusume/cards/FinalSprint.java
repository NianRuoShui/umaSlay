package umamusume.cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import umamusume.powers.FinalSprintPower;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

public class FinalSprint extends CustomCard{
    public static final String ID = "UmaMod:FinalSprint";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME; 
    private static final String IMG_PATH = "umaResources/img/cards/strike.png"; 
    private static final int COST = 2; // 卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; 
    private static final CardType TYPE = CardType.SKILL; 
    private static final CardColor COLOR = Uma_Oguri_Orange; 
    private static final CardRarity RARITY = CardRarity.SPECIAL; 
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int WEAK = 3;
    
    public FinalSprint() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = WEAK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 本回合双倍伤害
        this.addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1, false), 1));
        // 下回合虚弱
        this.addToBot(new ApplyPowerAction(p, p, new FinalSprintPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1); // 升级后费用变为1
            this.initializeDescription();
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new FinalSprint();
    }
}
