package jeckelstoragemod.content.crate;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockCrateRock extends ABlockCrate
{
	public static final String[] SUBNAMES =
	{
		"sandstone", "redsandstone", "stone",
		"granite", "diorite", "andesite",
		"prismarine", "darkprismarine"
	};

	public static final int[] SUBINVSIZES =
	{
		3 * 9, 3 * 9, 3 * 9,
		3 * 9, 3 * 9, 3 * 9,
		3 * 9, 3 * 9
	};

	public static Object[] SUBSOURCES =
	{
		Blocks.sandstone, null, Blocks.stone,
		null, null, null,
		null, null
	};

	public BlockCrateRock()
	{
		super(Refs.ModId, "rock", Material.rock, Block.soundTypeStone, SUBNAMES, SUBINVSIZES);
		this.setHardness(2.5F);
		this.setResistance(12.0F);
	}
}
