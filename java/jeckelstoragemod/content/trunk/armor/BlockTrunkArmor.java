package jeckelstoragemod.content.trunk.armor;

import jeckelstoragemod.content.trunk.ABlockTrunk;
import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTrunkArmor extends ABlockTrunk
{
	public BlockTrunkArmor()
	{
		super(Refs.ModId, "armor", Material.iron, Block.soundTypeMetal);
	}

	@Override public int getTrunkCargoCapacity(int meta) { return 36; }

	@Override public TileEntity getTrunkTile(World world, int meta, ItemStack stack) { return new TileTrunkArmor(stack); }
}
