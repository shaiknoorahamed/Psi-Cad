package org.cadbox;

public abstract interface UREngine
{
  public abstract void doUndo();
  
  public abstract void doRedo();
  
  public abstract boolean canUndo();
  
  public abstract boolean canRedo();
  
  public abstract void addCommand(UndoableCommand paramUndoableCommand);
}


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.UREngine
 * JD-Core Version:    0.7.0.1
 */