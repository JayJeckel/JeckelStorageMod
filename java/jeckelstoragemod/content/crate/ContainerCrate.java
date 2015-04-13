package jeckelstoragemod.content.crate;

import invtweaks.api.container.ChestContainer;
import jeckelcorelibrary.base.guis.AContainerTileInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@ChestContainer()
public class ContainerCrate extends AContainerTileInventory<TileCrate>
{
	public ContainerCrate(final EntityPlayer player, final TileCrate tile)
	{
		super(player, tile, tile, 176, tile.getInventoryRowCount() * 18 + 17 + 97);

		// Internal Inventory
		this.addInventorySlots(this._inventory, 8, 18, tile.getInventoryColCount(), tile.getInventoryRowCount());

		// Player Inventory
		this.addPlayerInventorySlots(this._player.inventory, 8, this._height);
		this.addPlayerHotbarSlots(this._player.inventory, 8, this._height);
	}

	@Override protected int getMergeSlotCount(final int slotIndex)
	{
		return this._inventory.getSizeInventory() - slotIndex;
	}

	@Override protected boolean isValidSlotItem(final EntityPlayer player, final int slotIndex, final ItemStack stack)
	{
		return this.getSlot(slotIndex).isItemValid(stack);
	}
}
