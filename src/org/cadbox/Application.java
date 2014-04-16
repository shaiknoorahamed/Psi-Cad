package org.cadbox;

import java.util.Collection;

public abstract interface Application
  extends Observable
{
  public abstract void addDocument(Document paramDocument);
  
  public abstract void removeDocument(Document paramDocument);
  
  public abstract Collection getDocuments();
  
  public abstract int getDocumentCount();
  
  public abstract void setCurrentDocument(Document paramDocument);
  
  public abstract Document getCurrentDocument();
}


