package org.cadbox.plaf;

import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public abstract interface Window
{
  public abstract void addInternalWindow(InternalWindow paramInternalWindow);
  
  public abstract void removeInternalWindow(InternalWindow paramInternalWindow);
  
  public abstract InternalWindow[] getAllInternalWindows();
  
  public abstract void addToolBar(JToolBar paramJToolBar, Object paramObject);
  
  public abstract void removeToolBar(JToolBar paramJToolBar);
  
  public abstract void setMainMenu(JMenuBar paramJMenuBar);
  
  public abstract JMenuBar getMainMenu();
  
  public abstract void setStatusBar(JPanel paramJPanel);
  
  public abstract JPanel getStatusBar();
}


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.plaf.Window
 * JD-Core Version:    0.7.0.1
 */