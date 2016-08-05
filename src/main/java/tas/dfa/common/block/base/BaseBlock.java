package tas.dfa.common.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tas.dfa.common.core.DFACreativeTab;
import tas.dfa.common.item.base.BaseBlockItem;
import tas.dfa.common.lib.LibResources;


/**
 * Created by fancysaurus on 8/4/16.
 */
public class BaseBlock extends Block implements IDFABlock
{
    private final String[] variants;
    private final String bareName;

    public BaseBlock(String name, Material materialIn, String... variants) {
        super(materialIn);

        if(variants.length == 0)
            variants = new String[]{name};
        this.variants = variants;
        bareName = name;
        setUnlocalizedName(name);
        setCreativeTab(DFACreativeTab.INSTANCE);
    }

    @Override
    public Block setUnlocalizedName(String name) {
        super.setUnlocalizedName(name);
        setRegistryName(LibResources.PREFIX+name);
        GameRegistry.register(this);
        GameRegistry.register(new BaseBlockItem(this),new ResourceLocation(LibResources.PREFIX+name));
        return this;
    }

    @Override
    public String getBareName() {
        return bareName;
    }

    @Override
    public IProperty[] getIgnoredProperties() {
        return new IProperty[0];
    }

    @Override
    public EnumRarity getBlockRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }
}
