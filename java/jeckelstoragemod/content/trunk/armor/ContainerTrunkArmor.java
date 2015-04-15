package jeckelstoragemod.content.trunk.armor;

import jeckelcorelibrary.base.guis.AContainerTileInventory;
import jeckelcorelibrary.core.slots.SlotArmor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrunkArmor extends AContainerTileInventory<TileTrunkArmor>
{
	public ContainerTrunkArmor(final EntityPlayer player, final TileTrunkArmor tile)
	{
		super(player, tile, tile, 251, 226);

		// Internal Inventory
		final int startX = 83;
		final int startY = 26;
		for (int rowIndex = 0; rowIndex < this._tile.getRowCount(); ++rowIndex)
		{
			for (int colIndex = 0; colIndex < this._tile.getColumnCount(); ++colIndex)
			{
				int id = colIndex + (rowIndex * this._tile.getColumnCount());
				int x = startX + (colIndex * 18);
				int y = startY + (rowIndex * 18);
				this.addSlotToContainer(new SlotArmor(tile, id, x, y, rowIndex, false));
			}
		}

		// Player Inventory
		this.addPlayerInventorySlots(player.inventory, 46, this._height);
		this.addPlayerHotbarSlots(player.inventory, 46, this._height);

		// Player Armor
		this.addPlayerArmorSlots(player.inventory, 63, 26);
	}

	@Override public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
	{
		final int colCount = this._tile.getColumnCount();
		ItemStack outStack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack())
		{
			final int inventoryCount = this._inventory.getSizeInventory();
			final int playerCount = inventoryCount + 27;
			final int hotbarCount = playerCount + 9;
			//final int totalCount = this.inventorySlots.size();
			ItemStack slotStack = slot.getStack();
			outStack = slotStack.copy();

			if (slotIndex < inventoryCount)
			{
				int rowIndex = slotIndex / colCount;
				ItemStack playerArmor = this._tile.getArmor(player, rowIndex);
				//player.addChatMessage(String.format("%d, %d, %b", slotIndex, rowIndex, playerArmor == null));
				if (playerArmor == null)
				{
					int indexStart = hotbarCount + rowIndex;
					int indexStop = indexStart + 1;
					if (!this.mergeItemStack(slotStack, indexStart, indexStop, false)) { return null; }
				}
				else if (!this.mergeItemStack(slotStack, inventoryCount, hotbarCount, true)) { return null; }
			}
			else
			{
				if (this.getSlot(colCount * 0).isItemValid(slotStack))
				{
					int indexStart = colCount * 0;
					if (!this.mergeItemStack(slotStack, indexStart, indexStart + colCount, false)) { return null; }
				}
				else if (this.getSlot(colCount * 1).isItemValid(slotStack))
				{
					int indexStart = colCount * 1;
					if (!this.mergeItemStack(slotStack, indexStart, indexStart + colCount, false)) { return null; }
				}
				else if (this.getSlot(colCount * 2).isItemValid(slotStack))
				{
					int indexStart = colCount * 2;
					if (!this.mergeItemStack(slotStack, indexStart, indexStart + colCount, false)) { return null; }
				}
				else if (this.getSlot(colCount * 3).isItemValid(slotStack))
				{
					int indexStart = colCount * 3;
					if (!this.mergeItemStack(slotStack, indexStart, indexStart + colCount, false)) { return null; }
				}
				else if (slotIndex >= inventoryCount && slotIndex < playerCount)
				{
					if (!this.mergeItemStack(slotStack, playerCount, hotbarCount, false))
					{
						return null;
					}
				}
				else if (slotIndex >= playerCount && slotIndex < hotbarCount)
				{
					if (!this.mergeItemStack(slotStack, inventoryCount, playerCount, false))
					{
						return null;
					}
				}
				//else if (!this.mergeItemStack(slotStack, 0, inventorySize, false)) { return null; }
			}

			if (slotStack.stackSize == 0) { slot.putStack((ItemStack)null); }
			else { slot.onSlotChanged(); }

			if (slotStack.stackSize == outStack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, slotStack);
		}

		return outStack;
	}
}
