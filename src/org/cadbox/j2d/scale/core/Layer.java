/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import org.cadbox.Document;
/*  4:   */ 
/*  5:   */ public class Layer
/*  6:   */   extends Group
/*  7:   */ {
/*  8:   */   private Document document;
/*  9:44 */   private boolean enable = true;
/* 10:45 */   private boolean visible = true;
/* 11:46 */   private boolean printable = true;
/* 12:   */   
/* 13:   */   public Layer(Document document)
/* 14:   */   {
/* 15:49 */     this(document, "Layer");
/* 16:   */   }
/* 17:   */   
/* 18:   */   public Layer(Document document, String name)
/* 19:   */   {
/* 20:53 */     this.name = name;
/* 21:54 */     this.document = document;
/* 22:   */     
/* 23:56 */     setColor(ColorManager.getInstance().getDefaultColor());
/* 24:57 */     setLineWeight(LineWeightManager.getInstance().getDefaulLineWeight());
/* 25:58 */     setLineType(LineTypeManager.getInstance().getDefaulLineType());
/* 26:   */   }
/* 27:   */   
/* 28:   */   public Document getDocument()
/* 29:   */   {
/* 30:62 */     return this.document;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setEnable(boolean enable)
/* 34:   */   {
/* 35:66 */     this.enable = enable;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean getEnable()
/* 39:   */   {
/* 40:70 */     return this.enable;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void setVisible(boolean visible)
/* 44:   */   {
/* 45:74 */     this.visible = visible;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public boolean getVisible()
/* 49:   */   {
/* 50:78 */     return this.visible;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void setPrintable(boolean printable)
/* 54:   */   {
/* 55:82 */     this.printable = printable;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public boolean getPrintable()
/* 59:   */   {
/* 60:86 */     return this.printable;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public String toString()
/* 64:   */   {
/* 65:90 */     return this.name;
/* 66:   */   }
/* 67:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Layer
 * JD-Core Version:    0.7.0.1
 */