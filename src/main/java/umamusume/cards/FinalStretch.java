package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import umamusume.powers.UmaPaceFrontPower;
import umamusume.powers.UmaPaceLatePower;
import umamusume.powers.UmaPacePacePower;
import umamusume.powers.UmaPaceEndPower;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;


public class FinalStretch extends CustomCard {


    public static final String ID = "UmaMod:FinalStretch";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final String IMG_PATH = "umaResources/img/cards/strike.png";

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Uma_Oguri_Orange;

    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 3; // 5 -> 8
    private static final int CARD_DRAW = 1;
    private static final int ENERGY_GAIN = 1;

    public FinalStretch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = CARD_DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));

        if (p.hasPower(UmaPaceFrontPower.POWER_ID) || p.hasPower(UmaPaceLatePower.POWER_ID)) {
            this.addToBot(new DrawCardAction(p, this.magicNumber));
        }

        if (p.hasPower(UmaPacePacePower.POWER_ID) || p.hasPower(UmaPaceEndPower.POWER_ID)) {
            this.addToBot(new GainEnergyAction(ENERGY_GAIN));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FinalStretch();
    }
}