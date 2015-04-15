package jeckelstoragemod.content.barrel;

import java.awt.Point;
import java.awt.Rectangle;

import jeckelcorelibrary.base.guis.AScreenTileInventory;
import jeckelstoragemod.core.Refs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScreenBarrel extends AScreenTileInventory<TileBarrel>
{
	public ScreenBarrel(EntityPlayer player, TileBarrel tile)
	{
		super(player, tile, new ContainerBarrel(player, tile), tile, 176, 180);
		this.setResourceLocation(Refs.ModId, "barrel.png");
	}

	private final Rectangle rectTank = new Rectangle(90, 26, 16, 47);

	private final OverlayInfo infoTankExchanger = new OverlayInfo(new Rectangle(114, 45, 8, 9), new Point(0, 180), new Point(0, 180), false, false, false);

	@Override protected void doDrawTitle() { this.drawTextCenter(this.getTitle(), 5); }

	@Override protected void onDrawTexts()
	{
		final int cap = this._tile.getTank().getCapacity();
		final int amount = this._tile.getTank().getFluidAmount();
		final double percent = ((double)amount / (double)cap) * 100.0D;

		this.drawTextRight("" + cap, 88, 26);
		this.drawTextRight("" + amount, 88, 46);
		this.drawTextRight(String.format("%.1f", percent) + "%", 88, 66);

		this.drawTextLeft(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 93);
	}

	@Override protected void onDrawOverlays()
	{
		if (this._tile.getTank().getFluidAmount() > 0) { this.drawFluidTank(this.rectTank, this._tile.getTank()); }

		if (this._tile.tankExchanger.isProcessing()) { this.drawProcessOverlay(this._tile.tankExchanger, this.infoTankExchanger); }
	}

	@Override protected void onDrawTooltips(int x, int y)
	{
		if (this.rectTank.contains(x, y)) { this.drawTankTooltip(x, y, this._tile.getTank()); }
		else if (this.infoTankExchanger.contains(x, y)) { this.drawProcessTooltip(x, y, "Tank Exchanging", this._tile.tankExchanger); }
	}
}
