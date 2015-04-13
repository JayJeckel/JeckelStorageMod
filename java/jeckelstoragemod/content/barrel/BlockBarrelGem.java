package jeckelstoragemod.content.barrel;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BlockBarrelGem extends ABlockBarrel
{
	public static final String[] SUBNAMES =
	{
		"lapis", "diamond", "emerald", "ruby"
	};

	public static final int[] SUBTANKCAPACITY =
	{
		64 * 1000, 64 * 1000, 64 * 1000, 64 * 1000
	};

	public static Object[] SUBSOURCES =
	{
		new ItemStack(Items.dye, 1, 4), "gemDiamond", "gemEmerald", "gemRuby"
	};

	public BlockBarrelGem()
	{
		super(Refs.ModId, "gem", Material.glass, Block.soundTypeGlass, SUBNAMES, SUBTANKCAPACITY);
		this.setHardness(2.5F);
		this.setResistance(16.0F);
	}
}
