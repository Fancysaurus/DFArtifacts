package tas.dfa.common.item.base;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tas.dfa.common.block.base.IDFABlock;
import tas.dfa.common.lib.LibResources;

import java.util.List;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class BaseBlockItem extends ItemBlock implements IVariantHolder
{

    private IDFABlock block;

    public BaseBlockItem(Block block) {
        super(block);
        this.block = (IDFABlock) block;
        BaseItem.varaintHolders.add(this);
        if(getVariants().length > 1)
            setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public ItemBlock setUnlocalizedName(String unlocalizedName) {
        GameRegistry.register(this);
        return super.setUnlocalizedName(unlocalizedName);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int dmg = stack.getItemDamage();
        String[] variants = getVariants();
        String name;
        if(dmg >= variants.length)
            name = block.getBareName();
        else
            name = variants[dmg];
        return "tile."+ LibResources.PREFIX + name;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        String[] variants = getVariants();
        for(int i = 0;i < variants.length; i++)
            subItems.add(new ItemStack(itemIn,1,i));
    }

    @Override
    public String[] getVariants() {
        return block.getVariants();
    }


    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return block.getCustomMeshDefinition();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return block.getBlockRarity(stack);
    }
}
