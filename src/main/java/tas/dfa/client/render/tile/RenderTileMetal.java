package tas.dfa.client.render.tile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import tas.dfa.common.block.tile.TileMetal;
import tas.dfa.common.item.base.ModItems;

/**
 * Created by fancysaurus on 8/13/16.
 */
public class RenderTileMetal extends TileEntitySpecialRenderer<TileMetal>
{
    float Scale = 0.75F;
    float Offset = 0.5F;


    @Override
    public void renderTileEntityAt(TileMetal te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        RenderItem renderer = Minecraft.getMinecraft().getRenderItem();

        GlStateManager.pushMatrix();
        GlStateManager.translate(x+0.5D,y+0.05D,z+0.5D);
        EntityItem stack = new EntityItem(getWorld(),0,0,0,te.blankStack);
        stack.getEntityItem().stackSize = 1;
        stack.hoverStart = 0.0F;

        GlStateManager.disableLighting();
        GlStateManager.rotate(-90,1,0,0);
        GlStateManager.scale(0.5D,0.5D,0.5D);
        GlStateManager.pushAttrib();
        RenderHelper.disableStandardItemLighting();
        renderer.renderItem(stack.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popAttrib();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
}
