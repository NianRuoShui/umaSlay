package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import static umamusume.characters.Oguri.PlayerTagsEnum.Uma_Oguri_food;

public class SnackTime extends CustomCard {

    public static final String ID = "UmaMod:SnackTime";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Uma_Oguri_Orange;

    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    public SnackTime() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        AbstractCard randomFoodCard = getRandomFoodCard();
        if (randomFoodCard != null) {
            this.addToBot(new MakeTempCardInDrawPileAction(randomFoodCard, 1, true, true));
        }
    }

    private AbstractCard getRandomFoodCard() {
        ArrayList<AbstractCard> foodCards = new ArrayList<>();
        for (AbstractCard c : CardLibrary.getAllCards()) {
            if (c.hasTag(Uma_Oguri_food)) {
                foodCards.add(c);
            }
        }

        // 如果找到了至少一张食物牌
        if (!foodCards.isEmpty()) {
            return foodCards.get(AbstractDungeon.cardRandomRng.random(foodCards.size() - 1)).makeCopy();
        }
        return null;
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
        return new SnackTime();
    }
}