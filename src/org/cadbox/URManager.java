package org.cadbox;

public abstract interface URManager
{
  public abstract void registerUREngine(UREngine paramUREngine, Document paramDocument);
  
  public abstract void unregisterUREngine(UREngine paramUREngine);
  
  public abstract void unregisterUREngine(Document paramDocument);
  
  public abstract UREngine getUREngine(Document paramDocument);
}


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.URManager
 * JD-Core Version:    0.7.0.1
 */