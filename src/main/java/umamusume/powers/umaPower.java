package umamusume.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class umaPower extends AbstractPower {
    public static final String POWER_ID = "UmaMod:umaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public umaPower(AbstractCreature owner, int Amount){
        System.out.println("Amount:"+Amount);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type =PowerType.BUFF;
        this.amount = Amount;
        String path128 = "umaResources/img/UI/utx_ico_obtain_02.png";
        String path48 = "umaResources/img/UI/utx_ico_obtain_02.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.description = DESCRIPTIONS[0];
        this.updateDescription();
    }
    @Override
    public void stackPower(int stackAmount) {
        System.out.println("stackPower called, stackAmount=" + stackAmount);
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        
        if (this.amount >= 999) {
           this.amount = 999;
        }
    
        if (this.amount <= -999) {
           this.amount = -999;
        }
        // this.updateDescription();
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
      return damage + (float)this.amount;
   }
    // 死亡时触发
    // public void onDeath() {
    //     ArrayList<AbstractCard> foodCards = new ArrayList<>();
    //     Random t = new Random();
    //     // 生成食物牌
    //     for (AbstractCard card : CardLibrary.getAllCards()) {
    //         if (card.tags.contains(Uma_Oguri_food)) {
    //             foodCards.add(card);
    //         }
    //     }
    //     AbstractCard foodCard = foodCards.get(t.nextInt(foodCards.size())).makeStatEquivalentCopy();
    //     this.addToBot(new MakeTempCardInHandAction(foodCard, 1));
    // }

        // public void atEndOfTurn(boolean isPlayer) {
        // if (this.amount <= 0) {
        //     // 如果层数为 0，移除该能力
        //     this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        // }
    // }
}
