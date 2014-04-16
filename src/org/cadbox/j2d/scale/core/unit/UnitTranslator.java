/*  1:   */ package org.cadbox.j2d.scale.core.unit;
/*  2:   */ 
/*  3:   */ import java.awt.Toolkit;
/*  4:   */ 
/*  5:   */ public class UnitTranslator
/*  6:   */ {
/*  7:   */   public static final double oneInch = 2.54D;
/*  8:   */   
/*  9:   */   public static MmUnit inchToMilimetor(InchUnit unit)
/* 10:   */   {
/* 11:41 */     return new MmUnit(unit.getValue() * 2.54D * 10.0D);
/* 12:   */   }
/* 13:   */   
/* 14:   */   public static InchUnit milimetorToInch(MmUnit unit)
/* 15:   */   {
/* 16:45 */     double tmp = unit.getValue() / 25.399999999999999D;
/* 17:46 */     tmp = Math.round(tmp * 1000.0D) / 1000.0D;
/* 18:47 */     return new InchUnit(tmp);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static double milimetorToPixel(MmUnit unit)
/* 22:   */   {
/* 23:51 */     int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
/* 24:52 */     InchUnit inch = milimetorToInch(unit);
/* 25:53 */     return inch.getValue() * dpi;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public static InchUnit pixelToInch(double unit)
/* 29:   */   {
/* 30:57 */     int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
/* 31:58 */     return new InchUnit(unit / dpi);
/* 32:   */   }
/* 33:   */   
/* 34:   */   public static MmUnit pixelToMilemetor(double unit)
/* 35:   */   {
/* 36:62 */     int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
/* 37:63 */     return inchToMilimetor(new InchUnit(unit / dpi));
/* 38:   */   }
/* 39:   */   
/* 40:   */   public static double inchToPixel(InchUnit unit)
/* 41:   */   {
/* 42:67 */     int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
/* 43:68 */     return unit.getValue() * dpi;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public static double lengthUnitToPixel(LengthUnit unit)
/* 47:   */   {
/* 48:72 */     if ((unit instanceof MmUnit)) {
/* 49:73 */       return milimetorToPixel((MmUnit)unit);
/* 50:   */     }
/* 51:75 */     if ((unit instanceof InchUnit)) {
/* 52:76 */       return inchToPixel((InchUnit)unit);
/* 53:   */     }
/* 54:78 */     throw new IllegalArgumentException("Only mm or inch available!!!");
/* 55:   */   }
/* 56:   */   
/* 57:   */   public static void main(String[] args) {}
/* 58:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.unit.UnitTranslator
 * JD-Core Version:    0.7.0.1
 */