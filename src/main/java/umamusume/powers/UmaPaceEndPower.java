package umamusume.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class UmaPaceEndPower extends AbstractPower {
    public static final String POWER_ID = "UmaMod:UmaPaceEndPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    //追
    public UmaPaceEndPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false; // 这是一个状态，不是基于回合的

        String path128 = "umaResources/img/UI/utx_ico_obtain_02.png";
        String path48 = "umaResources/img/UI/utx_ico_obtain_02.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.description = DESCRIPTIONS[0];
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
    }
    //未打出攻击牌则抽1牌，+1能量
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            // 检查本回合打出的牌中，是否没有任何一张是攻击牌
            if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream()
                    .noneMatch(card -> card.type == AbstractCard.CardType.ATTACK)) {
                this.flash();
                this.addToBot(new ApplyPowerAction(owner, owner, new DrawCardNextTurnPower(owner, 1), 1));
                this.addToBot(new ApplyPowerAction(owner, owner, new EnergizedPower(owner, 1), 1));
            }
        }
    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
