package jeckelstoragemod.content.barrel;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBarrel extends ItemBlock
{
	public ItemBlockBarrel(Block block)
	{
		super(block);
		this.setHasSubtypes(true);
	}
	
	public ABlockBarrel getBlock() { return (ABlockBarrel) this.field_150939_a; }

	@Override public String getUnlocalizedName(ItemStack stack)
	{
		return this.getBlock().getUnlocalizedName() + "." + this.getBlock().getSubNames()[stack.getItemDamage()];
	}

	@Override public int getMetadata(int meta) { return meta; }

    @SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    @Override public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List infoList, boolean par4)
    {
    	infoList.add("General fluid container.");
    	infoList.add("Capacity: " + this.getBlock().getSubTankCapacities()[stack.getItemDamage()] + " mb");
    }
}
