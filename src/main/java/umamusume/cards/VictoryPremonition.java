package umamusume.cards;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;

import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import static umamusume.characters.Oguri.PlayerTagsEnum.Uma_Oguri_zone;

public class VictoryPremonition extends CustomCard{
    public static final String ID = "UmaMod:VictoryPremonition";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME; 
    private static final String IMG_PATH = "umaResources/img/cards/strike.png"; 
    private static final int COST = 1; // 卡牌费用
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION; 
    private static final CardType TYPE = CardType.SKILL; 
    private static final CardColor COLOR = Uma_Oguri_Orange; 
    private static final CardRarity RARITY = CardRarity.RARE; 
    private static final CardTarget TARGET = CardTarget.SELF; 

    public VictoryPremonition() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(Uma_Oguri_zone);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(2));
        this.addToBot(new DrawCardAction(p, 2));
        this.addToBot(new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1, false), 1));
    }
        

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m)) {
            return false;
        }
        if (!p.hasPower("UmaMod:ZonePower")) {
            this.cantUseMessage = "未进入领域"; // 无法使用时显示的提示
            return false;
        }

        return true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new VictoryPremonition();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.initializeDescription();
        }
    }

}
