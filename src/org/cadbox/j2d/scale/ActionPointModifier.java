package org.cadbox.j2d.scale;

import java.util.Collection;
import org.cadbox.j2d.scale.core.Context;

public abstract interface ActionPointModifier
{
  public abstract Collection getActionPoints();
  
  public abstract void setActionPoint(ActionPoint paramActionPoint);
  
  public abstract void moveActionPoint(CadDrawingEngine paramCadDrawingEngine, Context paramContext, double paramDouble1, double paramDouble2);
  
  public abstract void postActionPoint(CadDrawingEngine paramCadDrawingEngine, double paramDouble1, double paramDouble2);
}


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.ActionPointModifier
 * JD-Core Version:    0.7.0.1
 */