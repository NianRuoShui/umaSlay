package umamusume.cards;
import static umamusume.characters.Oguri.PlauerTagsEnum.Uma_Oguri_food;
import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomCard;
import umamusume.powers.umaPower;
public class PrepareTraining extends CustomCard{
    public static final String ID = "UmaMod:PrepareTraining";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = Uma_Oguri_Orange;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;

    public PrepareTraining() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void upgrade() { //When card upgrade
        if (!this.upgraded) {
            this.upgradeName();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 赋予“预备训练”能力
        this.addToBot(new ApplyPowerAction(p, p, new umaPower(p, this.magicNumber), this.magicNumber));
    }

}
