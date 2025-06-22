package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import umamusume.powers.umaPower;
import java.util.ArrayList;
import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

public class Versatile extends CustomCard {

    // 卡牌ID
    public static final String ID = "UmaMod:Versatile";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // 卡牌图片，你需要准备一张名为 Versatile.png 的图片
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";

    // 卡牌属性
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Uma_Oguri_Orange;

    private static final int COST = 2;

    // 效果数值
    private static final int TRAINING_AMOUNT = 10;

    public Versatile() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = TRAINING_AMOUNT;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new GainTrainingChoiceCard(this.magicNumber));
        choices.add(new LoseTrainingChoiceCard(this.magicNumber));
        this.addToBot(new ChooseOneAction(choices));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Versatile();
    }


    // 费用为-2，表示不能从手牌中打出，仅用于选择界面
    public static class GainTrainingChoiceCard extends CustomCard {
        // 这张临时卡也需要一个ID和对应的JSON条目
        private static final String CHOICE_ID = "UmaMod:GainTrainingChoice";
        private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(CHOICE_ID);

        public GainTrainingChoiceCard(int amount) {
            // 费用-2，颜色为无色，稀有度为特殊
            super(CHOICE_ID, strings.NAME, "umaResources/img/cards/strike.png", -2, strings.DESCRIPTION,
                    CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
            this.baseMagicNumber = this.magicNumber = amount;
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            // 当玩家选择此卡时，执行效果：获得训练值
            this.addToBot(new ApplyPowerAction(p, p, new umaPower(p, this.magicNumber), this.magicNumber));
        }

        @Override
        public void upgrade() {}

        @Override
        public AbstractCard makeCopy() {
            return new GainTrainingChoiceCard(this.magicNumber);
        }
    }

    public static class LoseTrainingChoiceCard extends CustomCard {
        private static final String CHOICE_ID = "UmaMod:LoseTrainingChoice";
        private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(CHOICE_ID);

        public LoseTrainingChoiceCard(int amount) {
            super(CHOICE_ID, strings.NAME, "umaResources/img/cards/strike.png", -2, strings.DESCRIPTION,
                    CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
            this.baseMagicNumber = this.magicNumber = amount;
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            this.addToBot(new ApplyPowerAction(p, p, new umaPower(p, -this.magicNumber), -this.magicNumber));
        }

        @Override
        public void upgrade() {}

        @Override
        public AbstractCard makeCopy() {
            return new LoseTrainingChoiceCard(this.magicNumber);
        }
    }
}