package tas.dfa.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import org.lwjgl.opengl.GL11;
import tas.dfa.common.block.base.ModBlocks;
import tas.dfa.common.block.tile.TilePool;

/**
 * Created by fancysaurus on 8/10/16.
 */
public class RenderTilePool extends TileEntitySpecialRenderer<TilePool>
{
    @Override
    public void renderTileEntityAt(TilePool te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        if(!te.getWorld().isBlockLoaded(te.getPos(),false) || te.getWorld().getBlockState(te.getPos()).getBlock() != ModBlocks.pool)
            return;

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1F,1F,1F,1F);

        GlStateManager.translate(x+0.5D,y+1.05D,z+0.5D);
        GlStateManager.enableRescaleNormal();

        GlStateManager.pushMatrix();
        float s = 1F / 256F * 12;
        float v = 1F / 8F;
        float w = -v * 3F;
        float alpha = 0.9F;
        int brightness = -1;

        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableAlpha();
        GlStateManager.color(1F,1F,1F,alpha);
        GlStateManager.translate(w,-0.3F,w);
        GlStateManager.rotate(90F,1F,0F,0F);
        GlStateManager.scale(s,s,s);

        renderIcon(0,0, FluidRegistry.WATER.getStill(),16,16,brightness);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();

        //super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }

    public void renderIcon(int x1, int z1, ResourceLocation loc, int x2, int z2, int Brightness)
    {
        TextureAtlasSprite icon = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(loc.toString());
        Tessellator tess = Tessellator.getInstance();

        tess.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

        tess.getBuffer().pos(z1, z1 + x2, 0).tex(icon.getMinU(),icon.getMaxV()).endVertex();
        tess.getBuffer().pos(x1 + x2, z1 + z2, 0).tex(icon.getMaxU(),icon.getMaxV()).endVertex();
        tess.getBuffer().pos(x1 + x2, z1, 0).tex(icon.getMaxU(),icon.getMinV()).endVertex();
        tess.getBuffer().pos(x1, z1, 0).tex(icon.getMinU(),icon.getMinV()).endVertex();
        tess.draw();
    }
}
