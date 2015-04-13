package jeckelstoragemod.content.crate;

import jeckelcorelibrary.base.guis.AScreenTileInventory;
import jeckelstoragemod.core.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScreenCrate extends AScreenTileInventory<TileCrate>
{
	public ScreenCrate(EntityPlayer player, TileCrate tile)
	{
		super(player, tile, new ContainerCrate(player, tile), tile, 176, tile.getInventoryRowCount() * 18 + 17 + 97);
		this._resource = new ResourceLocation(Refs.ModId, "textures/guis/crate.png");

		this.topH = this._tile.getInventoryRowCount() * 18 + 17;
		this.bottomH = 97;
	}

	private final int topH;
	private final int bottomH;

	@Override public ResourceLocation getResourceLocation() { return this._resource; }
	private ResourceLocation _resource;

	@Override protected void doDrawBackground()
	{
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.topH);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop + this.topH, 0, 125, this.xSize, this.bottomH);
	}

	@Override protected void onDrawTexts()
	{
		this.drawTextLeft(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2);
	}
}
