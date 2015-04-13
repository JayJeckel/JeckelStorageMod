package jeckelstoragemod.widgets;

import java.awt.Point;
import java.awt.Rectangle;

public class WidgetTrunkArmorButton extends WidgetButton
{
    public WidgetTrunkArmorButton(int id, Rectangle dest, Point source, int action, int colIndex)
    {
    	super(id, dest, source, "");
    	this.action = action;
    	this.colIndex = colIndex;
    }
    
    public WidgetTrunkArmorButton(int id, Rectangle dest, Point source, int action, int colIndex, String text)
    {
    	super(id, dest, source, text);
    	this.action = action;
    	this.colIndex = colIndex;
    }

    public final int action;
    public final int colIndex;
}
