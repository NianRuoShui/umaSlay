package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import umamusume.powers.umaPower;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import umamusume.powers.*;
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

    private static final int COST = 1;


    public Versatile() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new ChooseLeaderPowerCard());
        choices.add(new ChooseFrontRunnerPowerCard());
        choices.add(new ChooseChaserPowerCard());
        choices.add(new ChooseStalkerPowerCard());
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

    private abstract static class AbstractTacticChoiceCard extends CustomCard {
        protected static final List<String> TACTIC_POWER_IDS = Arrays.asList(
                LeaderPower.POWER_ID,
                FrontRunnerPower.POWER_ID,
                ChaserPower.POWER_ID,
                StalkerPower.POWER_ID
        );

        public AbstractTacticChoiceCard(String id, String name, String imgPath, String description) {
            super(id, name, imgPath, -2, description,CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        }

        protected void applyTactic(AbstractPlayer p, AbstractPower powerToApply) {
            // 移除所有其他跑法
            for (String powerId : TACTIC_POWER_IDS) {
                if (p.hasPower(powerId)) {
                    this.addToBot(new RemoveSpecificPowerAction(p, p, powerId));
                }
            }
            this.addToBot(new ApplyPowerAction(p, p, powerToApply));
        }

        @Override
        public void upgrade() {}
    }

    public static class ChooseLeaderPowerCard extends AbstractTacticChoiceCard {
        private static final String CHOICE_ID = "UmaMod:ChooseLeaderPower";
        private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(CHOICE_ID);

        public ChooseLeaderPowerCard() {
            super(CHOICE_ID, strings.NAME, "umaResources/img/cards/strike.png", strings.DESCRIPTION);
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            applyTactic(p, new LeaderPower(p));
        }

        @Override
        public AbstractCard makeCopy() {
            return new ChooseLeaderPowerCard();
        }
    }

    public static class ChooseFrontRunnerPowerCard extends AbstractTacticChoiceCard {
        private static final String CHOICE_ID = "UmaMod:ChooseFrontRunnerPower";
        private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(CHOICE_ID);

        public ChooseFrontRunnerPowerCard() {
            super(CHOICE_ID, strings.NAME, "umaResources/img/cards/strike.png", strings.DESCRIPTION);
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            applyTactic(p, new FrontRunnerPower(p));
        }

        @Override
        public AbstractCard makeCopy() {
            return new ChooseFrontRunnerPowerCard();
        }
    }

    public static class ChooseChaserPowerCard extends AbstractTacticChoiceCard {
        private static final String CHOICE_ID = "UmaMod:ChooseChaserPower";
        private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(CHOICE_ID);

        public ChooseChaserPowerCard() {
            super(CHOICE_ID, strings.NAME, "umaResources/img/cards/strike.png", strings.DESCRIPTION);
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            applyTactic(p, new ChaserPower(p));
        }

        @Override
        public AbstractCard makeCopy() {
            return new ChooseChaserPowerCard();
        }
    }

    public static class ChooseStalkerPowerCard extends AbstractTacticChoiceCard {
        private static final String CHOICE_ID = "UmaMod:ChooseStalkerPower";
        private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(CHOICE_ID);

        public ChooseStalkerPowerCard() {
            super(CHOICE_ID, strings.NAME, "umaResources/img/cards/strike.png", strings.DESCRIPTION);
        }

        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {
            applyTactic(p, new StalkerPower(p));
        }

        @Override
        public AbstractCard makeCopy() {
            return new ChooseStalkerPowerCard();
        }
    }


}