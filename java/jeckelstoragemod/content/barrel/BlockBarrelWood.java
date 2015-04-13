package jeckelstoragemod.content.barrel;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBarrelWood extends ABlockBarrel
{
	public static final String[] SUBNAMES =
		{
			"oak", "spruce", "birch", "jungle", "acacia", "darkoak"
		};

	public static final int[] SUBTANKCAPACITY =
		{
			8 * 1000, 8 * 1000, 8 * 1000, 8 * 1000, 8 * 1000, 8 * 1000
		};

	public BlockBarrelWood()
	{
		super(Refs.ModId, "wood", Material.wood, Block.soundTypeWood, SUBNAMES, SUBTANKCAPACITY);
		this.setHardness(2.5F);
		this.setResistance(6.0F);
	}
}
