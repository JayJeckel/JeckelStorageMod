package jeckelstoragemod.content.crate;

import jeckelcorelibrary.api.guis.ITileGuiActivator;
import jeckelcorelibrary.api.tiles.ITileInteractable;
import jeckelcorelibrary.base.tiles.ATileInventory;
import jeckelstoragemod.core.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TileCrate
extends ATileInventory
implements ITileInteractable, ITileGuiActivator
{
	public TileCrate()
	{
		super();
	}

	public TileCrate(final ItemStack stack, final int inventorySize)
	{
		super(inventorySize);
		this.setTileName(stack.getDisplayName());
	}

	public int getInventoryColCount() { return 9; }

	public int getInventoryRowCount()
	{
		int rows = this.getSizeInventory() / this.getInventoryColCount();
		if (this.getSizeInventory() % this.getInventoryColCount() > 0) { rows += 1; }
		return rows;
	}


	// ##################################################
	//
	// Read and Write NBT
	//
	// ##################################################

	/*@Override public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
	}

	@Override public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
	}*/


	// ##################################################
	//
	// ITileInteractable
	//
	// ##################################################

	@Override public void interact(final EntityPlayer player, final World world, final int x, final int y, final int z, final int side)
	{
		if (!player.isSneaking()) {  player.openGui(Refs.getMod(), 0, world, x, y, z); }
	}


	// ##################################################
	//
	// ITileGui
	//
	// ##################################################

	@Override public Object createContainer(final EntityPlayer player) { return new ContainerCrate(player, this); }

	@Override public Object createScreen(final EntityPlayer player) { return new ScreenCrate(player, this); }
}
