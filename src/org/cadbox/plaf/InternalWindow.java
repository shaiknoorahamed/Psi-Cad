/*  1:   */ package org.cadbox.plaf;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ import javax.swing.JPanel;
/*  6:   */ 
/*  7:   */ public abstract class InternalWindow
/*  8:   */   extends JPanel
/*  9:   */ {
/* 10:42 */   private List frameListener = new ArrayList();
/* 11:   */   
/* 12:   */   public abstract String getTitle();
/* 13:   */   
/* 14:   */   public abstract void setTitle(String paramString);
/* 15:   */   
/* 16:   */   public void addInternalWindowListener(InternalWindowListener e)
/* 17:   */   {
/* 18:51 */     this.frameListener.add(e);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void removeInternalWindowListener(InternalWindowListener e)
/* 22:   */   {
/* 23:55 */     this.frameListener.remove(e);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void fireCloseInternalWindowAction()
/* 27:   */   {
/* 28:59 */     for (int i = 0; i < this.frameListener.size(); i++)
/* 29:   */     {
/* 30:60 */       InternalWindowListener l = (InternalWindowListener)this.frameListener.get(i);
/* 31:61 */       l.closeInternalWindowAction(this);
/* 32:   */     }
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void fireOpenInternalWindowAction()
/* 36:   */   {
/* 37:66 */     for (int i = 0; i < this.frameListener.size(); i++)
/* 38:   */     {
/* 39:67 */       InternalWindowListener l = (InternalWindowListener)this.frameListener.get(i);
/* 40:68 */       l.openInternalWindowAction(this);
/* 41:   */     }
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void fireActivatedInternalWindowAction()
/* 45:   */   {
/* 46:73 */     for (int i = 0; i < this.frameListener.size(); i++)
/* 47:   */     {
/* 48:74 */       InternalWindowListener l = (InternalWindowListener)this.frameListener.get(i);
/* 49:75 */       l.activatedInternalWindowAction(this);
/* 50:   */     }
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.plaf.InternalWindow
 * JD-Core Version:    0.7.0.1
 */