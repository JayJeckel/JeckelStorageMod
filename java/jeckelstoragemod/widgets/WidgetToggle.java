package jeckelstoragemod.widgets;

import java.awt.Point;
import java.awt.Rectangle;

import net.minecraft.client.Minecraft;

public class WidgetToggle extends WidgetButton
{
    public WidgetToggle(int id, Rectangle dest, Point source)
    {
        super(id, dest, source, "");
    }

    public WidgetToggle(int id, Rectangle dest, Point source, String text)
    {
        super(id, dest, source, text);
    }
    
    public boolean isActive() { return this._active; }
    protected void setActive(boolean value) { this._active = value; }
    protected boolean _active = false;
    
    public boolean toggle()
    {
    	this._active = !this._active;
    	return this._active;
    }

    /**
     * Returns the state of the button.
     * this button.
     * Disable = 0
     * Inactive = 1
     * Active = 2
     * Inactive Hover = 3
     * Active Hover = 4
     */
    @Override protected int getButtonState(Minecraft mc, int mouseX, int mouseY)
    {
        if (!this.enabled) { return 0; }
        
        int state = (this._active ? 2 : 1);
        if (this.containsMouse(mouseX, mouseY)) { state += 2; }
        return state;
    }
    
    @Override public void drawButtonText(Minecraft mc, int mouseX, int mouseY, int color)
    {
    	this.drawString(mc.fontRenderer, this.displayString,
    			this.xPosition + this.width + 1,// + (this.width / 2),
    			this.yPosition + ((this.height - mc.fontRenderer.FONT_HEIGHT) / 2),
    			color);
    }
}
