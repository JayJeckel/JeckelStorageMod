package jeckelstoragemod.content.trunk.armor;

import jeckelstoragemod.core.Refs;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageTrunkArmor implements IMessage
{
	public static void register()
	{
		Refs.getModNetwork().registerMessage(MessageTrunkArmor.Handler.class, MessageTrunkArmor.class, Refs.popNextMessageId(), Side.SERVER);
	}

	public MessageTrunkArmor() { }

	public MessageTrunkArmor(TileTrunkArmor tile, int actionId, int colIndex)
	{
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;

		this.actionId = actionId;

		this.colIndex = colIndex;
	}

	public int x;
	public int y;
	public int z;

	public int actionId;
	public int colIndex;

	@Override public void fromBytes(ByteBuf buf)
	{
		int size = 5;

		this.x = ByteBufUtils.readVarInt(buf, size);
		this.y = ByteBufUtils.readVarInt(buf, size);
		this.z = ByteBufUtils.readVarInt(buf, size);

		this.actionId = ByteBufUtils.readVarInt(buf, size);
		this.colIndex = ByteBufUtils.readVarInt(buf, size);
	}

	@Override public void toBytes(ByteBuf buf)
	{
		int size = 5;

		ByteBufUtils.writeVarInt(buf, this.x, size);
		ByteBufUtils.writeVarInt(buf, this.y, size);
		ByteBufUtils.writeVarInt(buf, this.z, size);

		ByteBufUtils.writeVarInt(buf, this.actionId, size);
		ByteBufUtils.writeVarInt(buf, this.colIndex, size);
	}

	public static class Handler implements IMessageHandler<MessageTrunkArmor, IMessage>
	{
		@Override public IMessage onMessage(MessageTrunkArmor message, MessageContext context)
		{
			EntityPlayer player = context.getServerHandler().playerEntity;
			TileTrunkArmor tile = (TileTrunkArmor) player.worldObj.getTileEntity(message.x, message.y, message.z);

			if (message.actionId == TileTrunkArmor.ActionTypes.Swap.id)
			{
				tile.swapArmor(player, message.colIndex);
			}
			else if (message.actionId == TileTrunkArmor.ActionTypes.Equip.id)
			{
				tile.equipArmor(player, message.colIndex);
			}
			else if (message.actionId == TileTrunkArmor.ActionTypes.Unequip.id)
			{
				tile.unequipArmor(player, message.colIndex);
			}
			//System.out.println(String.format("Received %s from %s", message.text, context.getServerHandler().playerEntity.getDisplayName()));
			return null; // no response in this case
		}
	}
}
