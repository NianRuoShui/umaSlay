package umamusume.cards;
import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import static umamusume.characters.Oguri.PlayerTagsEnum.Uma_Oguri_food;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EnergyDrink extends CustomCard{
    public static final String ID = "UmaMod:EnergyDrink";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME; 
    private static final String IMG_PATH = "umaResources/img/cards/strike.png"; 
    private static final int COST = 0; // 卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; 
    private static final CardType TYPE = CardType.SKILL; 
    private static final CardColor COLOR = Uma_Oguri_Orange; 
    private static final CardRarity RARITY = CardRarity.SPECIAL; 
    private static final CardTarget TARGET = CardTarget.SELF; 

    private static final int HEAL = 6;
    private static final int ENERGY = 1;

    public EnergyDrink() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(Uma_Oguri_food);
        this.exhaust = true; //消耗
        this.baseBlock = HEAL;
        this.baseMagicNumber = this.magicNumber = ENERGY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 回复6点生命
        this.addToBot(new HealAction(p, p, this.block));
        // 获得1点能量
        this.addToBot(new GainEnergyAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnergyDrink();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }
}
