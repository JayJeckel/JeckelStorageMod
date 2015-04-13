package jeckelstoragemod.content.trunk;

import jeckelcorelibrary.api.tiles.ITileFrontSide;
import jeckelcorelibrary.base.blocks.ABlockTile;
import jeckelcorelibrary.utils.DirUtil;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ABlockTrunk extends ABlockTile
{
	public ABlockTrunk(String modid, String trunkName, Material material, SoundType stepSound)
	{
		super(modid, "trunk" + "." + trunkName, material, stepSound);
        this._trunkName = trunkName;
        this._iconSides = new IIcon[6];
	}
	
	public String getTrunkName() { return this._trunkName; }
	private String _trunkName;
	
	public String getTrunkCargoType(int meta) { return "trunk.cargo.type." + this.getTrunkName() + ".text"; }
	
	public abstract int getTrunkCargoCapacity(int meta);
	
	public abstract TileEntity getTrunkTile(World world, int meta, ItemStack stack);

	@Override public TileEntity createNewTileEntity(World world, int meta)
	{
		return this.getTrunkTile(world, meta, new ItemStack(this, 1, meta));
	}
	
	@Override public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        return true;
    }
    
    
    // ##################################################
    //
    // Icon Methods
    //
    // ##################################################

	protected IIcon _iconFront;
	protected IIcon[] _iconSides;

    @SideOnly(Side.CLIENT)
	@Override public void registerBlockIcons(IIconRegister reg)
	{
        this._iconFront = reg.registerIcon(this.textureName + "." + "front");
        this._iconSides[0] = reg.registerIcon(this.textureName + "." + "down");
        this._iconSides[1] = reg.registerIcon(this.textureName + "." + "up");
	    for (int i = 2; i < 6; i++)
	    {
	        this._iconSides[i] = reg.registerIcon(this.textureName);
	    }
	}

    @SideOnly(Side.CLIENT)
	public IIcon getFrontIcon(int side, int meta)
	{
		return this._iconFront;
	}

    @SideOnly(Side.CLIENT)
	public IIcon getSideIcon(int side, int meta, int front)
	{
		if (side == front) { return this.getFrontIcon(side, meta); }
	    return this._iconSides[side];
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
