package jeckelstoragemod.content.crate;

import java.util.List;

import jeckelcorelibrary.api.tiles.ITileFrontSide;
import jeckelcorelibrary.base.blocks.ABlockTile;
import jeckelcorelibrary.utils.DirUtil;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ABlockCrate extends ABlockTile
{
	public ABlockCrate(String modId, String crateName, Material material, SoundType stepSound, String[] subNames, int[] subInvSizes)
	{
		super(modId, "crate" + "." + crateName, material, stepSound);
        this.isBlockContainer = true;
        
        this._subNames = subNames.clone();
        this._subInvSizes = subInvSizes.clone();
        
        this._iconFront = new IIcon[this._subNames.length];
        this._iconSides = new IIcon[this._subNames.length][6];
	}
	
	public String[] getSubNames() { return this._subNames; }
	private final String[] _subNames;

	public int[] getSubInvSizes() { return this._subInvSizes; }
	private final int[] _subInvSizes;
	
	@Override public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileCrate(new ItemStack(this, 1, meta), this.getSubInvSizes()[meta]);
	}
    
    @Override public int damageDropped(int meta) { return meta; }
	
	@Override public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        return true;
    }
    
    
    // ##################################################
    //
    // SubBlock Methods
    //
    // ##################################################

    @SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override public void getSubBlocks(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list)
	{
		for(int meta = 0; meta < this._subNames.length; ++meta)
		{
			list.add(new ItemStack(this, 1, meta));
		}
	}
    
    
    // ##################################################
    //
    // Icon Methods
    //
    // ##################################################

	protected IIcon[] _iconFront;
	protected IIcon[][] _iconSides;

    @SideOnly(Side.CLIENT)
	@Override public void registerBlockIcons(IIconRegister reg)
	{
		for(int meta = 0; meta < this._subNames.length; ++meta)
		{
	    	String subName = this._subNames[meta];
	        this._iconFront[meta] = reg.registerIcon(this.textureName + "/" + subName + "." + "front");
	        this._iconSides[meta][0] = reg.registerIcon(this.textureName + "/" + subName + "." + "down");
	        this._iconSides[meta][1] = reg.registerIcon(this.textureName + "/" + subName + "." + "up");
		    for (int i = 2; i < 6; i++)
		    {
		        this._iconSides[meta][i] = reg.registerIcon(this.textureName + "/" + subName);
		    }
		}
	}

    @SideOnly(Side.CLIENT)
	public IIcon getFrontIcon(int side, int meta)
	{
		return this._iconFront[meta];
	}

    @SideOnly(Side.CLIENT)
	public IIcon getSideIcon(int side, int meta, int front)
	{
		if (side == front) { return this.getFrontIcon(side, meta); }
	    return this._iconSides[meta][side];
	}

    @SideOnly(Side.CLIENT)
	@Override public IIcon getIcon(int side, int meta)
	{
		return this.getSideIcon(side, meta, DirUtil.DEFAULT_FRONT.ordinal());
	}
	
    @SideOnly(Side.CLIENT)
    @Override public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	int front = DirUtil.DEFAULT_FRONT.ordinal();
    	int meta = blockAccess.getBlockMetadata(x, y, z);
    	TileEntity tile = blockAccess.getTileEntity(x, y, z);
    	if (tile != null && tile instanceof ITileFrontSide) { front = ((ITileFrontSide)tile).getFrontSide(); }
		return this.getSideIcon(side, meta, front);
    }
}
