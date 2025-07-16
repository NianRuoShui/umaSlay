package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import umamusume.powers.*; // 导入所有跑法能力

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

public class InitialGuidance extends CustomCard {

    public static final String ID = "UmaMod:InitialGuidance";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";
    private static final List<String> TACTIC_POWER_IDS = Arrays.asList(
            UmaPaceFrontPower.POWER_ID,
            UmaPacePacePower.POWER_ID,
            UmaPaceEndPower.POWER_ID,
            UmaPaceLatePower.POWER_ID
    );

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Uma_Oguri_Orange;

    private static final int COST = 0;

    public InitialGuidance() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.isEthereal = true;
        this.isInnate = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        }

        for (String powerId : TACTIC_POWER_IDS) {
            if (p.hasPower(powerId)) {
                this.cantUseMessage = "已确定跑法。"; // 无法使用时显示的提示
                return false;
            }
        }

        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 变幻自在
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new Versatile.ChooseLeaderPowerCard());
        choices.add(new Versatile.ChooseFrontRunnerPowerCard());
        choices.add(new Versatile.ChooseChaserPowerCard());
        choices.add(new Versatile.ChooseStalkerPowerCard());
        this.addToBot(new ChooseOneAction(choices));

    }

    @Override
    public void upgrade() {

    }

    @Override
    public AbstractCard makeCopy() {
        return new InitialGuidance();
    }
}