package tas.dfa.common.potion;

import net.minecraft.potion.Potion;

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
