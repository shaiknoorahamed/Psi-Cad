package org.cadbox.j2d.scale.core;

import java.util.Collection;
import org.cadbox.Document;

public abstract interface LayeredDocument
  extends Document
{
  public abstract void addLayer(Layer paramLayer);
  
  public abstract void removeLayer(Layer paramLayer);
  
  public abstract Collection getLayers();
  
  public abstract int getLayerCount();
  
  public abstract void setCurrentLayer(Layer paramLayer);
  
  public abstract Layer getCurrentLayer();
}


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.LayeredDocument
 * JD-Core Version:    0.7.0.1
 */