package tas.dfa.Api;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.datafix.fixes.PotionItems;

/**
 * Created by David on 8/4/2016.
 */
public class Config
{
    // TODO: Post-Jam support - rework this into a config file so end users can modify its contents
    public static Config instance = new Config();

    public boolean isValidDrawItem(ItemStack stack)
    {
        Item item = stack.getItem();

        if(item == Items.GOLD_INGOT) return true;
        if(item == Items.GHAST_TEAR) return true;
        if(item == Items.BLAZE_POWDER) return true;
        if(item == Items.ENDER_PEARL) return true;
        if(item == Items.REDSTONE)
        if(item == Items.DYE)
        {
            // Check that we're talking about a piece of Lapis Lazuli
            if(stack.getItemDamage() == 4) return true;
        }

        // Fall through = no-go
        return false;
    }

    public boolean isValidBaseItem(ItemStack stack)
    {
        Item item = stack.getItem();

        if(item == Items.IRON_INGOT) return true;
        if(item == Items.DIAMOND) return true;

        // Fall through = no-go
        return false;
    }

    // TODO: Move this section to Generation?
    public ItemStack generateArtifactItem(int drawScore) {
        ItemStack stack = new ItemStack(Items.DIAMOND_SWORD);
        stack.addEnchantment(Enchantments.SHARPNESS, 3);
        stack.setStackDisplayName("Testificalibur");

        return stack;
    }

    public PotionEffect generateFailureDebuff() {
        // HACK: Maybe I'm doing this wrong, but this seems to be the oly method for getting an existing PotionEffect
        // for programmed application to a player
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("Id", 27);   // Bad Luck
        tag.setInteger("Amplifier", 0);
        tag.setInteger("Duration", 300);
        tag.setBoolean("ShowParticles", true);

        PotionEffect effect = PotionEffect.readCustomPotionEffectFromNBT(tag);
        return effect;
    }
}
