package tas.dfa.common.potion.base;

import net.minecraft.potion.Potion;
import tas.dfa.common.potion.PotionMood;

/**
 * Created by fancysaurus on 8/7/16.
 */
public class ModPotions
{
    public static Potion mood;

    public static void Init()
    {
        mood = new PotionMood();
    }

}
