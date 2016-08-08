package tas.dfa.common.item.tool;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.lib.LibItems;

import java.util.List;

/**
 * Created by fancysaurus on 8/8/16.
 */
public class ItemAncientHammer extends  BaseItem
{
    public ItemAncientHammer()
    {
        super(LibItems.hammerArtifact);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        attacker.attackEntityFrom(DamageSource.magic,4.0F);

        if(target.isEntityUndead())
        {
            attacker.attackEntityFrom(DamageSource.magic, 2.0F);
            target.setFire(5);
        }
        return super.hitEntity(stack, target, attacker);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        List<EntityLivingBase> entitiesHit = worldIn.getEntitiesWithinAABB(EntityLivingBase.class,new AxisAlignedBB(pos.getX()-2,pos.getY()-2,pos.getZ()-2,pos.getX()+2,pos.getY()+2,pos.getZ()+2));
        for(EntityLivingBase entity : entitiesHit)
        {
            if(entity instanceof EntityPlayer);
            else
            {
                entity.addVelocity(0, +1, 0);
                entity.velocityChanged = true;
            }
        }
        worldIn.playSound(null,playerIn.posX,playerIn.posY,playerIn.posZ, SoundEvents.BLOCK_ANVIL_PLACE,SoundCategory.PLAYERS,1F,1F);

        return EnumActionResult.SUCCESS;
    }
}
