package umamusume.cards;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import umamusume.powers.TempConfusionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;
import static umamusume.characters.Oguri.PlayerTagsEnum.Uma_Oguri_food;

public class GreenJuice extends CustomCard{
    public static final String ID = "UmaMod:GreenJuice"; //卡牌ID
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = Uma_Oguri_Orange;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public GreenJuice() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(Uma_Oguri_food); // 添加“食物”标签
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 移除所有负面效果
        this.addToBot(new RemoveDebuffsAction(p));
        // 获得1层混乱
        this.addToBot(new ApplyPowerAction(p, p, new TempConfusionPower(p), 1));
    }

    public void upgrade(){
        if(!this.upgraded){
            this.upgradeName();
            this.magicNumber = this.baseMagicNumber = 0;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    @Override
    public AbstractCard makeCopy() {
        return new GreenJuice();
    }
}
