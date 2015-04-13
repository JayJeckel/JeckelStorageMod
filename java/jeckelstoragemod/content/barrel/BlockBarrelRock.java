package jeckelstoragemod.content.barrel;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockBarrelRock extends ABlockBarrel
{
	public static final String[] SUBNAMES =
	{
		"sandstone", "redsandstone",
		"stone", "granite", "diorite", "andesite",
		"prismarine", "darkprismarine"
	};

	public static final int[] SUBTANKCAPACITY =
	{
		16 * 1000, 16 * 1000,
		16 * 1000, 16 * 1000, 16 * 1000, 16 * 1000,
		16 * 1000, 16 * 1000
	};

	public static Object[] SUBSOURCES =
	{
		Blocks.sandstone, null,
		Blocks.stone, null, null, null, null,
		null, null
	};

	public BlockBarrelRock()
	{
		super(Refs.ModId, "rock", Material.rock, Block.soundTypeStone, SUBNAMES, SUBTANKCAPACITY);
		this.setHardness(2.5F);
		this.setResistance(12.0F);
	}
}
