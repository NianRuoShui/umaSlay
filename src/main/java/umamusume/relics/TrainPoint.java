package umamusume.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import umamusume.cards.Versatile;

import static umamusume.characters.Oguri.PlayerTagsEnum.*;

public class TrainPoint extends CustomRelic {
    public static final String ID = "UmaMod:TrainPoint";
    private static final String IMG_PATH = "umaResources/img/relics/OguriFood.png";
    private static final RelicTier RELIC_TIER = RelicTier.SPECIAL;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    private static final int UMA_REALM_ON_NUM = 20;
//    private static int UMA_REALM_CURRENT;

    public TrainPoint() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    public String getUpdatedDescription(){
        return this.DESCRIPTIONS[0] + counter;
    }

    public void atTurnStart(){
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card , UseCardAction action){
        if(card.hasTag(Uma_TP_plus_six)){
            this.counter = this.counter + 6;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }
        if(card.hasTag(Uma_TP_plus_one)){
            ++this.counter;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }
        if(card.hasTag(Uma_TP_plus_two)){
            this.counter = this.counter + 2;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }
        if(card.hasTag(Uma_TP_plus_eight)){
            this.counter = this.counter + 8;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }
        if(card.hasTag(Uma_TP_plus_four)){
            this.counter = this.counter + 4;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }

        if(card.hasTag(Uma_TP_minus_fifteen)){
            this.counter = this.counter - 15;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }

        if(card.hasTag(Uma_TP_minus_five)){
            this.counter = this.counter - 5;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }

        if(card.hasTag(Uma_TP_minus_six)){
            this.counter = this.counter - 6;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }

        if(card.hasTag(Uma_TP_minus_ten)){
            this.counter = this.counter - 10;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }
        if(card.hasTag(Uma_TP_minus_two)){
            this.counter = this.counter - 2;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }
        if(card.hasTag(Uma_TP_minus_twenty)){
            this.counter = this.counter - 20;
            if(this.counter %UMA_REALM_ON_NUM >= 0){
                this.flash();
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Versatile()));
            }
        }else{
            --this.counter;
        }
    }

    public void onVictory() {
        this.counter = -1;
    }

    public AbstractRelic makeCopy() {
        return new TrainPoint();
    }
}
