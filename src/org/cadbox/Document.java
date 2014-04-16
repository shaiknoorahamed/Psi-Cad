package org.cadbox;

public abstract interface Document
{
  public abstract boolean isModified();
  
  public abstract void setModified(boolean paramBoolean);
  
  public abstract boolean isSaved();
  
  public abstract void setSaved(boolean paramBoolean);
  
  public abstract void open(String paramString);
  
  public abstract void save(String paramString);
}


