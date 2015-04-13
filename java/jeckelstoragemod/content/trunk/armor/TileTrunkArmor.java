package jeckelstoragemod.content.trunk.armor;

import jeckelcorelibrary.api.guis.ITileGuiActivator;
import jeckelcorelibrary.api.tiles.ITileInteractable;
import jeckelcorelibrary.base.tiles.ATileInventory;
import jeckelstoragemod.core.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TileTrunkArmor
extends ATileInventory
implements ITileInteractable, ITileGuiActivator
{
	public enum ActionTypes
	{
		Swap(0),
		Equip(1),
		Unequip(2);

		private ActionTypes(final int id) { this.id = id; }
		public final int id;
	};

	public TileTrunkArmor()
	{
		super(36);
	}

	public TileTrunkArmor(final ItemStack stack)
	{
		super(36);
		this.setTileName(stack.getDisplayName());
	}


	// ##################################################
	//
	// Armor Methods
	//
	// ##################################################

	public int getColumnCount() { return 9; }

	public int getRowCount() { return 4; }

	public boolean hasColumnArmor(final int colIndex)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			if (this.getArmor(colIndex, rowIndex) != null) { return true; }
		}
		return false;
	}

	public ItemStack getArmor(final int colIndex, final int rowIndex)
	{
		int index = colIndex + (rowIndex * this.getColumnCount());
		return this.getStackInSlot(index);
	}

	public void setArmor(final int colIndex, final int rowIndex, final ItemStack stack)
	{
		int index = colIndex + (rowIndex * this.getColumnCount());
		this.setInventorySlotContents(index, stack);
	}

	public boolean hasPlayerArmor(final EntityPlayer player)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			if (this.getArmor(player, rowIndex) != null) { return true; }
		}
		return false;
	}

	public ItemStack getArmor(final EntityPlayer player, final int rowIndex)
	{
		return player.inventory.getStackInSlot(36 + 3 - rowIndex);
	}

	public void setArmor(final EntityPlayer player, final int rowIndex, final ItemStack stack)
	{
		player.inventory.setInventorySlotContents(36 + 3 - rowIndex, stack);
	}

	public boolean canSwapArmor(final EntityPlayer player, final int colIndex)
	{
		return this.hasPlayerArmor(player) || this.hasColumnArmor(colIndex);
	}

	public void swapArmor(final EntityPlayer player, final int colIndex)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			ItemStack newPlayerArmor = this.getArmor(colIndex, rowIndex);
			ItemStack newColumnArmor = this.getArmor(player, rowIndex);

			if (newPlayerArmor != null || newColumnArmor != null)
			{
				this.setArmor(player, rowIndex, newPlayerArmor);
				this.setArmor(colIndex, rowIndex, newColumnArmor);
			}
		}
	}

	public boolean canEquipArmor(final EntityPlayer player, final int colIndex)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			ItemStack playerArmor = this.getArmor(player, rowIndex);
			ItemStack columnArmor = this.getArmor(colIndex, rowIndex);

			if (playerArmor == null && columnArmor != null)
			{ return true; }
		}
		return false;
	}

	public void equipArmor(final EntityPlayer player, final int colIndex)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			ItemStack playerArmor = this.getArmor(player, rowIndex);
			ItemStack columnArmor = this.getArmor(colIndex, rowIndex);

			if (playerArmor == null && columnArmor != null)
			{
				this.setArmor(player, rowIndex, columnArmor);
				this.setArmor(colIndex, rowIndex, null);
			}
		}
	}

	public boolean canUnequipArmor(final EntityPlayer player, final int colIndex)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			ItemStack playerArmor = this.getArmor(player, rowIndex);
			ItemStack columnArmor = this.getArmor(colIndex, rowIndex);

			if (playerArmor != null && columnArmor == null) { return true; }
		}
		return false;
	}

	public void unequipArmor(final EntityPlayer player, final int colIndex)
	{
		for (int rowIndex = 0; rowIndex < 4; rowIndex++)
		{
			ItemStack playerArmor = this.getArmor(player, rowIndex);
			ItemStack columnArmor = this.getArmor(colIndex, rowIndex);

			if (playerArmor != null && columnArmor == null)
			{
				this.setArmor(player, rowIndex, null);
				this.setArmor(colIndex, rowIndex, playerArmor);
			}
		}
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
		if (!player.isSneaking()) { player.openGui(Refs.getMod(), 0, world, x, y, z); }
	}


	// ##################################################
	//
	// ITileGui
	//
	// ##################################################

	@Override public Object createContainer(final EntityPlayer player) { return new ContainerTrunkArmor(player, this); }

	@Override public Object createScreen(final EntityPlayer player) { return new ScreenTrunkArmor(player, this); }
}
