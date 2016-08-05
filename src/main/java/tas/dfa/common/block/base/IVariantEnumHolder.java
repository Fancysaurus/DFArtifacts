package tas.dfa.common.block.base;

import net.minecraft.util.IStringSerializable;

/**
 * Created by fancysaurus on 8/4/16.
 */
public interface IVariantEnumHolder<T extends Enum<T> & IStringSerializable>
{
    public static final String HEADER = "variant";
    Class<T> getVariantEnum();
}
