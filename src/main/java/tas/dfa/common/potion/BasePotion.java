package tas.dfa.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tas.dfa.common.lib.LibMisc;

/**
 * Created by fancysaurus on 8/7/16.
 */
public class BasePotion extends Potion {

    public BasePotion(String name,boolean isBadEffectIn, int liquidColorIn)
    {
        super(isBadEffectIn, liquidColorIn);
        GameRegistry.register(this, new ResourceLocation(LibMisc.MOD_ID,name));
        setPotionName("dfa.potion." + name);
    }

    public boolean hasEffect(EntityLivingBase entity)
    {
       return hasEffect(entity,this);
    }

    public boolean hasEffect(EntityLivingBase entity, Potion potion)
    {
        return entity.getActivePotionEffect(potion) != null;
    }

    public PotionEffect apply(EntityLivingBase entity,int duration,int level)
    {
        PotionEffect effect = new PotionEffect(this,duration,level,false,false);
        entity.addPotionEffect(effect);
        return effect;
    }

}
