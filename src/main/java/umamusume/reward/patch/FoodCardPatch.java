package umamusume.reward.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.rewards.RewardItem;

public class FoodCardPatch {
    public static class TypePatch{
        @SpireEnum
        public static RewardItem.RewardType UMA_FOOD_CARD;
    }
}
