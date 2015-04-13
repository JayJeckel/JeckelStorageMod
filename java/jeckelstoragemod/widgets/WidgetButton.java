package jeckelstoragemod.widgets;

import java.awt.Point;
import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class WidgetButton extends GuiButton
{
    public WidgetButton(int id, Rectangle dest, Point source)
    {
        super(id, dest.x, dest.y, dest.width, dest.height, "");
        this.source = source;
    }
    
    public WidgetButton(int id, Rectangle dest, Point source, String text)
    {
        super(id, dest.x, dest.y, dest.width, dest.height, text);
        this.source = source;
    }
    
    protected Point source;
    protected ResourceLocation _resource;
    
    public ResourceLocation getResource() { return this._resource; }
    public void setResource(ResourceLocation resource) { this._resource = resource; }
    
    public void bindTexture(Minecraft mc)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	mc.renderEngine.bindTexture(this._resource);
    }
    

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     * Disable = 0
     * Normal = 1
     * Hover = 2
     * Pressed = 3
     */
    /*@Override protected int getHoverState(boolean containsMouse)
    {
        if (!this.enabled) { return 0; }
        else if (!containsMouse) { return 1; }
        else { return 2; }
    }*/
    
    protected boolean containsMouse(int mouseX, int mouseY)
    {
    	return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    /**
     * Returns the state of the button.
     * this button.
     * Disable = 0
     * Normal = 1
     * Hover = 2
     */
    protected int getButtonState(Minecraft mc, int mouseX, int mouseY)
    {
        if (!this.enabled) { return 0; }
        else if (!this.containsMouse(mouseX, mouseY)) { return 1; }
        else { return 2; }
    }
    
    public void drawButtonText(Minecraft mc, int mouseX, int mouseY, int color)
    {
    	this.drawString(mc.fontRenderer, this.displayString,
    			this.xPosition,// + (this.width / 2),
    			this.yPosition,// + (this.height / 2),
    			color);
    }
    
    @Override public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
        	this.bindTexture(mc);
            //this.field_82253_i = this.containsMouse(mouseX, mouseY);//mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
        	boolean hover = this.containsMouse(mouseX, mouseY);
            int buttonState = this.getButtonState(mc, mouseX, mouseY);
            
            int textureX = this.source.x + (buttonState * this.width);
            int textureY = this.source.y;
            this.drawTexturedModalRect(this.xPosition, this.yPosition, textureX, textureY, this.width, this.height);
            
            this.mouseDragged(mc, mouseX, mouseY);
            
            if (this.displayString != null && this.displayString.length() > 0)
            {
                int color;
                if (!this.enabled) { color = -6250336; }
                //else if (this.field_82253_i) { color = 16777120; }
                else if (hover) { color = 16777120; }
                else { color = 14737632; }
                
            	this.drawButtonText(mc, mouseX, mouseY, color);
            }
        }
    }
}
