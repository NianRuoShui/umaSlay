package umamusume.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Arrays;
import java.util.List;

public class umaPower extends AbstractPower {
    public static final String POWER_ID = "UmaMod:umaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final List<String> TACTIC_ID = Arrays.asList(
            "UmaMod:LeaderPower",
            "UmaMod:FrontRunnerPower", // 先
            "UmaMod:StalkerPower",     // 差
            "UmaMod:ChaserPower"       // 追
    );


    public umaPower(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type =PowerType.BUFF;
        this.amount = amount;
        String path128 = "umaResources/img/UI/utx_ico_obtain_02.png";
        String path48 = "umaResources/img/UI/utx_ico_obtain_02.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.description = DESCRIPTIONS[0];
        this.updateDescription();
        this.updateTactic();
    }
    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        
        if (this.amount >= 999) {
           this.amount = 999;
        }
    
        if (this.amount <= -999) {
           this.amount = -999;
        }
        this.updateTactic();
        this.updateDescription();
    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
//    @Override
//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        return damage + (float)this.amount;
//    }

    public void updateTactic(){
        for (String tacticId:TACTIC_ID){
            if(this.owner.hasPower(tacticId)){
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, tacticId));
            }
        }
        if (this.amount >= 10){
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new LeaderPower(this.owner)));
        }else if (this.amount >= 0){
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new FrontRunnerPower(this.owner)));
        }else  if (this.amount >= -10){
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new ChaserPower(this.owner)));
        }else {
            this.addToTop(new ApplyPowerAction(this.owner, this.owner, new StalkerPower(this.owner)));
        }
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
