package umamusume.relics;

import basemod.abstracts.CustomRelic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static umamusume.characters.Oguri.PlauerTagsEnum.Uma_Oguri_food;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class OguriRelicFood extends CustomRelic {
    public static final String ID = "UmaMod:OguriFood";
    private static final String IMG_PATH = "umaResources/img/relics/OguriFood.png";
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public OguriRelicFood(){
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new OguriRelicFood();
    }


    public void atBattleStart() {
        super.atBattleStart();
        ArrayList<AbstractCard> foodCards = new ArrayList<>();

        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card.tags.contains(Uma_Oguri_food)) {
                foodCards.add(card);
            }
        }
        // for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
        //     if (card.tags.contains(Uma_Oguri_food)) {
        //         foodCards.add(card);
        //     }
        // }
        Collections.shuffle(foodCards, AbstractDungeon.cardRandomRng.random);
        int cardsToAdd = Math.min(3, foodCards.size());
        Random t = new Random();
        
        for (int i = 0; i < 3; i++) {
            AbstractCard foodCard = foodCards.get(t.nextInt(cardsToAdd)).makeStatEquivalentCopy();
            this.addToBot(new MakeTempCardInHandAction(foodCard, 1)); // 将卡牌加入手牌
        }
    }
}
