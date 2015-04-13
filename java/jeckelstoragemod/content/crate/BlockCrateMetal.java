package jeckelstoragemod.content.crate;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;

public class BlockCrateMetal extends ABlockCrate
{
	public static final String[] SUBNAMES =
	{
		"iron", "gold",
		"copper", "tin", "bronze",
		"silver",
	};

	public static final int[] SUBINVSIZES =
	{
		4 * 9, 4 * 9,
		4 * 9, 4 * 9, 4 * 9,
		4 * 9
	};

	public static Object[] SUBSOURCES =
	{
		Items.iron_ingot, Items.gold_ingot,
		"ingotCopper", "ingotTin", "ingotBronze",
		"ingotSilver"
	};

	public BlockCrateMetal()
	{
		super(Refs.ModId, "metal", Material.iron, Block.soundTypeMetal, SUBNAMES, SUBINVSIZES);
		this.setHardness(2.5F);
		this.setResistance(8.0F);
	}
}
