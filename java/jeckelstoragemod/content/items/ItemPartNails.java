package jeckelstoragemod.content.items;

import java.util.List;

import jeckelcorelibrary.base.items.AItem;
import jeckelstoragemod.core.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPartNails extends AItem
{
	public ItemPartNails()
	{
		super(Refs.ModId, "part.nails");
	}

	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List infoList, boolean par4)
	{
		infoList.add("Component used in crafting other items.");
	}
}
