package tas.dfa.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tas.dfa.common.block.BlockAncientAnvil;
import tas.dfa.common.block.base.ModBlocks;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.lib.LibItems;

/**
 * Created by fancysaurus on 8/6/16.
 */
public class ItemSoulWater extends BaseItem
{
    public ItemSoulWater()
    {
        super(LibItems.soulWater);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.getBlockState(pos).getBlock() != Blocks.ANVIL) return EnumActionResult.FAIL;

        worldIn.setBlockState(pos, ModBlocks.ancientAnvil.getDefaultState());
        worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pos.getX(), pos.getY(), pos.getZ(), 0, 5, 0);

        stack.stackSize--;
        if(stack.stackSize == 0)
            playerIn.setHeldItem(hand, null);

        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.UNCOMMON;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
