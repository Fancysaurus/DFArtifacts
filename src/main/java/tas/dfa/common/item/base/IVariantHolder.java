package tas.dfa.common.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fancysaurus on 8/4/16.
 */
public interface IVariantHolder
{
    String[] getVariants();

    @SideOnly(Side.CLIENT)
    ItemMeshDefinition getCustomMeshDefinition();

}
