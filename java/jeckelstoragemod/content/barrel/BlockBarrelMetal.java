package jeckelstoragemod.content.barrel;

import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;

public class BlockBarrelMetal extends ABlockBarrel
{
	public static final String[] SUBNAMES =
	{
		"iron", "gold",
		"copper", "tin", "bronze",
		"silver",
	};

	public static final int[] SUBTANKCAPACITY =
	{
		32 * 1000, 32 * 1000,
		32 * 1000, 32 * 1000, 32 * 1000,
		32 * 1000
	};

	public static Object[] SUBSOURCES =
	{
		Items.iron_ingot, Items.gold_ingot,
		"ingotCopper", "ingotTin", "ingotBronze",
		"ingotSilver"
	};

	public BlockBarrelMetal()
	{
		super(Refs.ModId, "metal", Material.iron, Block.soundTypeMetal, SUBNAMES, SUBTANKCAPACITY);
		this.setHardness(2.5F);
		this.setResistance(8.0F);
	}
}
