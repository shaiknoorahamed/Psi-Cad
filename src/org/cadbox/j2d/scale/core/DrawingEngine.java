package org.cadbox.j2d.scale.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class DrawingEngine
{
  public abstract BufferedImage getImage();
  
  public abstract Color getShapeDrawingColor();
  
  public abstract void setShapeDrawingColor(Color paramColor);
  
  public abstract BasicStroke getShapeDrawingStroke();
  
  public abstract void setShapeDrawingStroke(BasicStroke paramBasicStroke);
}


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.DrawingEngine
 * JD-Core Version:    0.7.0.1
 */