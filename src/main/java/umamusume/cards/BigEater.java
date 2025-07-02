package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import umamusume.powers.BigEaterPower;


public class BigEater extends CustomCard {
    public static final String ID = "UmaMod:BigEater";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME; 
    private static final String IMG_PATH = "umaResources/img/cards/strike.png"; 
    private static final int COST = 1; // 卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; 
    private static final CardType TYPE = CardType.SKILL; 
    private static final CardColor COLOR = Uma_Oguri_Orange; 
    private static final CardRarity RARITY = CardRarity.SPECIAL; 
    private static final CardTarget TARGET = CardTarget.SELF; 

    public BigEater() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new BigEaterPower(p), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BigEater();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0); // 升级后费用变为0
            this.initializeDescription();
        }
    }
}
