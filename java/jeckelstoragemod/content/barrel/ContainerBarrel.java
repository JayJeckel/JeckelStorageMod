package jeckelstoragemod.content.barrel;

import jeckelcorelibrary.base.guis.AContainerTileInventory;
import jeckelcorelibrary.core.slots.SlotLiquidContainer;
import jeckelcorelibrary.core.slots.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ContainerBarrel extends AContainerTileInventory<TileBarrel>
{
	public ContainerBarrel(EntityPlayer player, TileBarrel tile)
	{
		super(player, tile, tile, 176, 166);

		// Liquid Container Input
		this.addSlotToContainer(new SlotLiquidContainer(tile, 0, 90, 20));

		// Liquid Container Output
		this.addSlotToContainer(new SlotOutput(tile, 1, 90, 51));

		// Player Inventory
		this.addPlayerInventorySlots(this._player.inventory, 8, this._height);
		this.addPlayerHotbarSlots(this._player.inventory, 8, this._height);
	}

	@Override protected int getMergeSlotCount(final int slotIndex)
	{
		switch (slotIndex) { case 0: { return 1; } default: { return 0; } }
	}

	@Override protected boolean isValidSlotItem(final EntityPlayer player, final int slotIndex, final ItemStack stack)
	{
		return this.getSlot(slotIndex).isItemValid(stack);
	}
}
