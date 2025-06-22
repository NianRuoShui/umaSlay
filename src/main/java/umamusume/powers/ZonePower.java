package umamusume.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ZonePower extends AbstractPower {
    public static final String POWER_ID = "UmaMod:ZonePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int DURATION = 3; // 领域持续回合数
    private static final int STRENGTH_GAIN = 5;
    private static final int CARD_DRAW_ON_ATTACK = 1;

    public ZonePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = DURATION;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        String path128 = "umaResources/img/UI/utx_ico_obtain_02.png";
        String path48 = "umaResources/img/UI/utx_ico_obtain_02.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, STRENGTH_GAIN), STRENGTH_GAIN));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        // 在领域内，每次使用攻击牌时抽牌
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            this.addToBot(new DrawCardAction(this.owner, CARD_DRAW_ON_ATTACK));
        }
    }

    @Override
    public void atEndOfRound() {
        // 每回合结束时，减少持续时间
        this.amount--;
        if (this.amount <= 0) {
            // 持续时间结束，移除领域
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
        updateDescription();
    }

    @Override
    public void onRemove() {
        // 领域结束时，移除获得的力量
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -STRENGTH_GAIN), -STRENGTH_GAIN));
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount, STRENGTH_GAIN, CARD_DRAW_ON_ATTACK);
    }
}