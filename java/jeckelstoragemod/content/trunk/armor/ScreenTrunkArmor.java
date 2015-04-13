package jeckelstoragemod.content.trunk.armor;

import java.awt.Point;
import java.awt.Rectangle;

import jeckelcorelibrary.base.guis.AScreenTileInventory;
import jeckelstoragemod.core.Refs;
import jeckelstoragemod.widgets.WidgetToggle;
import jeckelstoragemod.widgets.WidgetTrunkArmorButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class ScreenTrunkArmor extends AScreenTileInventory<TileTrunkArmor>
{
	public ScreenTrunkArmor(final EntityPlayer player, final TileTrunkArmor tile)
	{
		super(player, tile, new ContainerTrunkArmor(player, tile), tile, 251, 212);
		this._resource = new ResourceLocation(Refs.ModId, "textures/guis/trunk.armor.png");

		this.buttonSwapArray = new WidgetTrunkArmorButton[this._tile.getColumnCount()];
		this.buttonEquipArray = new WidgetTrunkArmorButton[this._tile.getColumnCount()];
		this.buttonUnequipArray = new WidgetTrunkArmorButton[this._tile.getColumnCount()];
	}

	private final  Rectangle rectPlayer = new Rectangle(8, 18, 52, 70);

	private float xSize_lo;
	private float ySize_lo;

	private WidgetToggle toggleInvertView;

	private final WidgetTrunkArmorButton[] buttonSwapArray;
	private final WidgetTrunkArmorButton[] buttonEquipArray;
	private final WidgetTrunkArmorButton[] buttonUnequipArray;

	@Override public ResourceLocation getResourceLocation() { return this._resource; }
	private ResourceLocation _resource;

	@Override protected void onDrawOverlays()
	{
		this.drawPlayer(this.rectPlayer, this.xSize_lo, this.ySize_lo, this.toggleInvertView.isActive());
	}

	@Override protected void onDrawTexts()
	{
		this.drawTextLeft(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2);
	}

	@Override protected void onDrawTooltips(int x, int y)
	{
		//this.drawTooltip(x, y, "Test");
	}

	@SuppressWarnings("unchecked")
	@Override public void initGui()
	{
		super.initGui();
		this.buttonList.clear();

		final int startX = this.guiLeft + 83;
		int startY;
		Point source;

		int id = -1;

		startY = this.guiTop + 90;
		source = new Point(0, 234);
		for (int colIndex = 0; colIndex < this._tile.getColumnCount(); ++colIndex)
		{
			id += 1;
			int x = startX + (colIndex * 18);
			WidgetTrunkArmorButton button = new WidgetTrunkArmorButton(id, new Rectangle(x, startY, 16, 11), source, TileTrunkArmor.ActionTypes.Swap.id, colIndex);
			button.setResource(this._resource);
			this.buttonList.add(button);
			this.buttonSwapArray[colIndex] = button;
		}

		startY = this.guiTop + 90 + 11;
		source = new Point(0, 245);
		for (int colIndex = 0; colIndex < this._tile.getColumnCount(); ++colIndex)
		{
			int x;
			WidgetTrunkArmorButton button;

			id += 1;
			x = startX + (colIndex * 18);
			button = new WidgetTrunkArmorButton(id, new Rectangle(x, startY, 8, 11), source, TileTrunkArmor.ActionTypes.Equip.id, colIndex);
			button.setResource(this._resource);
			this.buttonList.add(button);
			this.buttonEquipArray[colIndex] = button;
		}

		startY = this.guiTop + 90 + 11;
		source = new Point(24, 245);
		for (int colIndex = 0; colIndex < this._tile.getColumnCount(); ++colIndex)
		{
			id += 1;
			int x = startX + (colIndex * 18) + 8;
			WidgetTrunkArmorButton button = new WidgetTrunkArmorButton(id, new Rectangle(x, startY, 8, 11), source, TileTrunkArmor.ActionTypes.Unequip.id, colIndex);
			button.setResource(this._resource);
			this.buttonList.add(button);
			this.buttonUnequipArray[colIndex] = button;
		}

		startY = this.guiTop + 90;
		source = new Point(48, 245);
		{
			id += 1;
			int x = this.guiLeft + this.rectPlayer.x;
			this.toggleInvertView = new WidgetToggle(id, new Rectangle(x, startY, 11, 11), source, "Invert View");
			this.toggleInvertView.setResource(this._resource);
			this.buttonList.add(this.toggleInvertView);
		}
	}

	@Override protected void actionPerformed(GuiButton button)
	{
		if (!button.enabled) { return; }

		if (button instanceof WidgetTrunkArmorButton)
		{
			WidgetTrunkArmorButton actionButton = (WidgetTrunkArmorButton) button;
			Refs.getModNetwork().sendToServer(new MessageTrunkArmor(this._tile, actionButton.action, actionButton.colIndex));
		}
		else if (button instanceof WidgetToggle)
		{
			WidgetToggle toggle = (WidgetToggle) button;
			toggle.toggle();
		}
	}

	@Override public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = par1;
		this.ySize_lo = par2;
	}

	@Override public void updateScreen()
	{
		super.updateScreen();
		//boolean hasPlayerArmor = this.tile.hasPlayerArmor(this.mc.thePlayer);
		for (int colIndex = 0; colIndex < this._tile.getColumnCount(); ++colIndex)
		{
			//boolean hasColumnArmor = this.tile.hasColumnArmor(colIndex);
			WidgetTrunkArmorButton button;
			boolean enabled;

			button = this.buttonSwapArray[colIndex];
			enabled = this._tile.canSwapArmor(this.mc.thePlayer, colIndex);
			if (button.enabled != enabled) { button.enabled = enabled; }

			button = this.buttonEquipArray[colIndex];
			enabled = this._tile.canEquipArmor(this.mc.thePlayer, colIndex);
			if (button.enabled != enabled) { button.enabled = enabled; }

			button = this.buttonUnequipArray[colIndex];
			enabled = this._tile.canUnequipArmor(this.mc.thePlayer, colIndex);
			if (button.enabled != enabled) { button.enabled = enabled; }
		}
	}
}
