/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ 
/*  5:   */ public class PropertyContext
/*  6:   */ {
/*  7:   */   Color color;
/*  8:   */   LineWeight lineWeight;
/*  9:   */   LineType lineType;
/* 10:   */   
/* 11:   */   public void setColor(Color color)
/* 12:   */   {
/* 13:46 */     this.color = color;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public Color getColor()
/* 17:   */   {
/* 18:50 */     return this.color;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setLineWeight(LineWeight lineWeight)
/* 22:   */   {
/* 23:54 */     this.lineWeight = lineWeight;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public LineWeight getLineWeight()
/* 27:   */   {
/* 28:58 */     return this.lineWeight;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public LineType getLineType()
/* 32:   */   {
/* 33:62 */     return this.lineType;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void setLineType(LineType lineType)
/* 37:   */   {
/* 38:66 */     this.lineType = lineType;
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.PropertyContext
 * JD-Core Version:    0.7.0.1
 */