package umamusume.powers;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static umamusume.characters.Oguri.PlauerTagsEnum.Uma_Oguri_food;

public class umaPower extends AbstractPower {
    public static final String POWER_ID = "UmaMod:PowerPrepareTraining";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public umaPower(AbstractCreature owner, int Amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type =PowerType.BUFF;
        this.amount = Amount;
        String path128 = "umaResources/img/cards/strike.png";
        String path48 = "umaResources/img/cards/strike.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.description = DESCRIPTIONS[0];
    }


    public void onMonsterDeath(AbstractMonster monster) {
        ArrayList<AbstractCard> foodCards = new ArrayList<>();
        Random t = new Random();
        // 检查敌人是否被斩杀
        if (monster.currentHealth <= 0 || monster.isDying ){
        // if (monster.currentHealth <= 0 && !monster.halfDead && !monster.isDying) {
            System.out.println("敌人被斩杀了！");
            // 生成食物牌
            for (AbstractCard card : CardLibrary.getAllCards()) {
                if (card.tags.contains(Uma_Oguri_food)) {
                    foodCards.add(card);
            }
            AbstractCard foodCard = foodCards.get(t.nextInt(foodCards.size())).makeStatEquivalentCopy();
            this.addToBot(new MakeTempCardInHandAction(foodCard, 1));
            


        }
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.amount--;
        if (this.amount <= 0) {
            // 如果层数为 0，移除该能力
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }

    }
}
