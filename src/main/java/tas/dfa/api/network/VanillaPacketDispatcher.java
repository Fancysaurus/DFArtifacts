package tas.dfa.api.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * Created by fancysaurus on 8/11/16.
 */
public class VanillaPacketDispatcher
{
    public static void dispatchTEToNearbyPlayers(TileEntity tile)
    {
        if(tile.getWorld() instanceof WorldServer)
        {
            WorldServer server = (WorldServer)tile.getWorld();
            SPacketUpdateTileEntity packet = tile.getUpdatePacket();

            if(packet == null)
                return;

            for(EntityPlayer player : server.playerEntities)
            {
                EntityPlayerMP MPPlayer = (EntityPlayerMP) player;
                if(player.getDistanceSq(tile.getPos()) < 64 * 64 && server.getPlayerChunkMap().isPlayerWatchingChunk(MPPlayer,tile.getPos().getX() >> 4,tile.getPos().getZ() >> 4))
                {
                    MPPlayer.connection.sendPacket(packet);
                }
            }

        }
    }

    public static void dispatchTEToNearbyPlayer(World world, BlockPos pos)
    {
        TileEntity tile = world.getTileEntity(pos);
        if(tile != null)
            dispatchTEToNearbyPlayers(tile);
    }


}
