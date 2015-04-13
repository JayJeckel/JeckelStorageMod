package jeckelstoragemod.content.crate;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCrateWood extends ABlockCrate
{
	public static final String[] SUBNAMES =
		{
			"oak", "spruce", "birch", "jungle", "acacia", "darkoak"
		};

	public static final int[] SUBINVSIZES =
		{
			2 * 9, 2 * 9, 2 * 9, 2 * 9, 2 * 9, 2 * 9
		};

	public BlockCrateWood()
	{
		super(Refs.ModId, "wood", Material.wood, Block.soundTypeWood, SUBNAMES, SUBINVSIZES);
		this.setHardness(2.5F);
		this.setResistance(6.0F);
	}
}
