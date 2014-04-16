/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ public class LineType
/*  4:   */   implements Cloneable
/*  5:   */ {
/*  6:   */   private float[] dots;
/*  7:   */   
/*  8:   */   public LineType(float[] dots)
/*  9:   */   {
/* 10:42 */     this.dots = dots;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public float[] getLineTypeValue()
/* 14:   */   {
/* 15:46 */     int size = this.dots.length;
/* 16:47 */     float[] dots_tmp = new float[size];
/* 17:48 */     System.arraycopy(this.dots, 0, dots_tmp, 0, size);
/* 18:49 */     return dots_tmp;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public boolean equals(Object obj)
/* 22:   */   {
/* 23:53 */     return true;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String toString()
/* 27:   */   {
/* 28:57 */     return "";
/* 29:   */   }
/* 30:   */   
/* 31:   */   public Object clone()
/* 32:   */   {
/* 33:   */     try
/* 34:   */     {
/* 35:62 */       return super.clone();
/* 36:   */     }
/* 37:   */     catch (CloneNotSupportedException e)
/* 38:   */     {
/* 39:65 */       throw new InternalError();
/* 40:   */     }
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.LineType
 * JD-Core Version:    0.7.0.1
 */