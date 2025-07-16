package umamusume.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;


import static umamusume.characters.Oguri.PlayerColorEnum.Uma_Oguri_Orange;

public class ContinuousStrike extends CustomCard {
    public static final String ID = "UmaMod:ContinuousStrike";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "umaResources/img/cards/strike.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK; // 卡牌类型（攻击、技能、能力）
    private static final CardColor COLOR = Uma_Oguri_Orange; // 卡牌颜色（与角色颜色一致）
    private static final CardRarity RARITY = CardRarity.COMMON; // 卡牌稀有度（基础、普通、罕见、稀有、特殊等）
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public ContinuousStrike(){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 4;
        this.tags.add(CardTags.STRIKE);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void upgrade(){
        if(!upgraded){
          this.upgradeName();
          this.upgradeDamage(1);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        for(int i = 1; i < this.magicNumber; ++i) {
            this.addToBot(new DamageAllEnemiesAction(p, this.damage,this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        this.addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,2,false),2));
    }
}
