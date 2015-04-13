package jeckelstoragemod.content.crate;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BlockCrateGem extends ABlockCrate
{
	public static final String[] SUBNAMES =
	{
		"lapis", "diamond", "emerald", "ruby"
	};

	public static final int[] SUBINVSIZES =
	{
		5 * 9, 5 * 9, 5 * 9, 5 * 9
	};

	public static Object[] SUBSOURCES =
	{
		new ItemStack(Items.dye, 1, 4), "gemDiamond", "gemEmerald", "gemRuby"
	};

	public BlockCrateGem()
	{
		super(Refs.ModId, "gem", Material.glass, Block.soundTypeGlass, SUBNAMES, SUBINVSIZES);
		this.setHardness(2.5F);
		this.setResistance(16.0F);
	}
}
