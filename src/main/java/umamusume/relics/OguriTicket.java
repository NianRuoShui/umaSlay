package umamusume.relics;

import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import umamusume.cards.EnergyDrink;

public class OguriTicket extends CustomRelic {

    public static final String ID = "UmaMod:Ticket";
    private static final String IMG_PATH = "umaResources/img/relics/OguriFood.png";
    private static final RelicTier RELIC_TIER = RelicTier.SHOP;  //出现在商店中
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public OguriTicket() {

        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }


    // 只在获得触发
    @Override
    public void onEquip() {
        // 回复所有生命值
        AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth, true);

        // 能量饮料加入
        AbstractCard cardToAdd = new EnergyDrink();

        float cardX = (float)Settings.WIDTH / 2.0F;
        float cardY = (float)Settings.HEIGHT / 2.0F;

        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(cardToAdd.makeCopy(), cardX - 50.0f * Settings.scale, cardY));
        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(cardToAdd.makeCopy(), cardX + 50.0f * Settings.scale, cardY));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}