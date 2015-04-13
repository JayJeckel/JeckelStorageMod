package jeckelstoragemod.content.trunk;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockTrunk extends ItemBlock
{
	public ItemBlockTrunk(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}
	
	public ABlockTrunk getBlock() { return (ABlockTrunk) this.field_150939_a; }

	@Override public String getUnlocalizedName(ItemStack stack)
	{
		return this.getBlock().getUnlocalizedName();
	}

	@Override public int getMetadata(int meta) { return meta; }

    @SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    @Override public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List infoList, boolean par4)
    {
    	infoList.add("Specialized storage container.");
    	infoList.add("Cargo: " + StatCollector.translateToLocal(this.getBlock().getTrunkCargoType(stack.getItemDamage())));
    	infoList.add("Capacity: " + this.getBlock().getTrunkCargoCapacity(stack.getItemDamage()) + " slots");
    }
}
