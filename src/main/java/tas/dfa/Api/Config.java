package tas.dfa.Api;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
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

import java.util.Random;

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
        Random rng = new Random();

        ItemStack stack = null;

        // TODO: The following section should absolutely be based on config files... after the modjam
        if(drawScore == 0) {
            // A lump of coal for naughty children who contribute nothing to their artifacts.
            stack = generateLumpOfCoal();
        }
        else if(drawScore <= 5) {
            // Tier 1 - Iron gear with only improved durability
            switch(rng.nextInt(4)) {
                case 0: stack = generateWaffleChest(); break;
                case 1: stack = generateWaffleLeggings(); break;
                case 2: stack = generateWaffleBoots(); break;
                case 3: stack = generateWaffleHelmet(); break;
                default: break;
            }
        }
        else if(drawScore <= 10) {
            // Tier 2 - Iron gear with some minor enchantments
            switch(rng.nextInt(3)) {
                case 0: stack = generateLightSaber(); break;
                case 1: stack = generateRustyDuckling(); break;
                case 2: stack = generateColdFeet(); break;
                default: break;
            }
        }
        else if(drawScore <= 15) {
            // Tier 3 - Diamond gear with some decent enchantments
            switch(rng.nextInt()) {
                default: break;
            }
        }
        else if(drawScore <= 20) {
            // Tier 4 - Diamond gear with some sweet enchantments
            switch(rng.nextInt()) {
                default: break;
            }
        }
        else {
            // Tier 5 - Diamond gear with impossible enchantments
            switch(rng.nextInt()) {
                default: break;
            }
        }

        // All artifacts are Unbreaking 5
        if(stack != null)
            stack.addEnchantment(Enchantments.UNBREAKING, 5);

        return stack;
    }

    public PotionEffect generateFailureDebuff()
    {
        return new PotionEffect(Potion.getPotionById(27),600,1,false,false);
    }

    // TODO: These definitely need to be in their own place eventually
    private ItemStack generateLumpOfCoal() {
        ItemStack stack = new ItemStack(Items.COAL);
        stack.setStackDisplayName("Lump of coal");

        return stack;
    }

    private ItemStack generateWaffleChest() {
        ItemStack stack = new ItemStack(Items.CHAINMAIL_CHESTPLATE);
        stack.setStackDisplayName("Waffle Iron Chest");

        return stack;
    }

    private ItemStack generateWaffleLeggings() {
        ItemStack stack = new ItemStack(Items.CHAINMAIL_LEGGINGS);
        stack.setStackDisplayName("Waffle Iron Leggings");

        return stack;
    }

    private ItemStack generateWaffleHelmet() {
        ItemStack stack = new ItemStack(Items.CHAINMAIL_HELMET);
        stack.setStackDisplayName("Waffle Iron Helmet");

        return stack;
    }

    private ItemStack generateWaffleBoots() {
        ItemStack stack = new ItemStack(Items.CHAINMAIL_BOOTS);
        stack.setStackDisplayName("Waffle Iron Boots");

        return stack;
    }

    private ItemStack generateLightSaber() {
        ItemStack stack = new ItemStack(Items.IRON_SWORD);
        stack.setStackDisplayName("Light Saber");
        stack.addEnchantment(Enchantments.SHARPNESS, 3);

        return stack;
    }

    private ItemStack generateRustyDuckling() {
        ItemStack stack = new ItemStack(Items.IRON_HELMET);
        stack.setStackDisplayName("Rusty Duckling");
        stack.addEnchantment(Enchantments.AQUA_AFFINITY, 1);
        stack.addEnchantment(Enchantments.DEPTH_STRIDER, 2);

        return stack;
    }

    private ItemStack generateColdFeet() {
        ItemStack stack = new ItemStack(Items.IRON_BOOTS);
        stack.setStackDisplayName("Cold Feet");
        stack.addEnchantment(Enchantments.FROST_WALKER, 2);
        stack.addEnchantment(Enchantments.MENDING, 1);

        return stack;
    }
}
