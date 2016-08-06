package tas.dfa.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import tas.dfa.common.block.base.BaseBlock;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class testBlock extends BaseBlock {
    public testBlock()
    {
        super("testBlock",Material.ANVIL);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }


}
