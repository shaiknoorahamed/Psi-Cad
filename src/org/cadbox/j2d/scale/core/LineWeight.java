/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import org.cadbox.j2d.scale.core.unit.LengthUnit;
/*  4:   */ 
/*  5:   */ public class LineWeight
/*  6:   */   implements Cloneable
/*  7:   */ {
/*  8:   */   private LengthUnit weight;
/*  9:   */   
/* 10:   */   public LineWeight(LengthUnit weight)
/* 11:   */   {
/* 12:45 */     this.weight = weight;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public LengthUnit getLineWeightValue()
/* 16:   */   {
/* 17:49 */     return this.weight;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public boolean equals(Object obj)
/* 21:   */   {
/* 22:53 */     if ((obj instanceof LineWeight))
/* 23:   */     {
/* 24:54 */       LineWeight tmp = (LineWeight)obj;
/* 25:55 */       if (tmp.getLineWeightValue().equals(this.weight) == true) {
/* 26:56 */         return true;
/* 27:   */       }
/* 28:   */     }
/* 29:58 */     return false;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String toString()
/* 33:   */   {
/* 34:62 */     return this.weight.toString() + " " + this.weight.getUnits();
/* 35:   */   }
/* 36:   */   
/* 37:   */   public Object clone()
/* 38:   */   {
/* 39:   */     try
/* 40:   */     {
/* 41:67 */       return super.clone();
/* 42:   */     }
/* 43:   */     catch (CloneNotSupportedException e)
/* 44:   */     {
/* 45:70 */       throw new InternalError();
/* 46:   */     }
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.LineWeight
 * JD-Core Version:    0.7.0.1
 */