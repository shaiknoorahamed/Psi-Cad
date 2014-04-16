/*  1:   */ package org.cadbox.j2d.scale.core.unit;
/*  2:   */ 
/*  3:   */ import java.text.DecimalFormat;
/*  4:   */ 
/*  5:   */ public class MmUnit
/*  6:   */   implements LengthUnit
/*  7:   */ {
/*  8:39 */   private static DecimalFormat df = new DecimalFormat("#########0.00");
/*  9:   */   double value;
/* 10:42 */   String unit = "mm";
/* 11:   */   
/* 12:   */   public MmUnit(double value)
/* 13:   */   {
/* 14:45 */     this.value = value;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public String getUnits()
/* 18:   */   {
/* 19:49 */     return this.unit;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public double getValue()
/* 23:   */   {
/* 24:53 */     return this.value;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public String toString()
/* 28:   */   {
/* 29:57 */     return df.format(this.value);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public boolean equals(Object obj)
/* 33:   */   {
/* 34:61 */     if ((obj instanceof MmUnit))
/* 35:   */     {
/* 36:62 */       MmUnit tmp = (MmUnit)obj;
/* 37:63 */       if (tmp.getValue() == this.value) {
/* 38:64 */         return true;
/* 39:   */       }
/* 40:   */     }
/* 41:66 */     return false;
/* 42:   */   }
/* 43:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.unit.MmUnit
 * JD-Core Version:    0.7.0.1
 */