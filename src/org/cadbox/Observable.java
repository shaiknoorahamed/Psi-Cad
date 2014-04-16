package org.cadbox;

public abstract interface Observable
{
  public abstract void addObserver(Observer paramObserver);
  
  public abstract void removeObserver(Observer paramObserver);
  
  public abstract void updateAllObservers();
}


