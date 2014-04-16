package org.cadbox;

public abstract interface UndoableCommand
  extends Command
{
  public abstract void unexecute();
}


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.UndoableCommand
 * JD-Core Version:    0.7.0.1
 */