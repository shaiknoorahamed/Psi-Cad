/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*  4:   */ import org.cadbox.j2d.scale.core.unit.LengthUnit;
/*  5:   */ import org.cadbox.j2d.scale.core.unit.UnitTranslator;
/*  6:   */ 
/*  7:   */ public class Layout
/*  8:   */ {
/*  9:   */   public static final int MODEL_LAYOUT_TYPE = 0;
/* 10:   */   public static final int CUSTOM_LAYOUT_TYPE = 1;
/* 11:47 */   private int layoutType = 0;
/* 12:   */   private LengthUnit width;
/* 13:   */   private LengthUnit height;
/* 14:   */   private LengthUnit x;
/* 15:   */   private LengthUnit y;
/* 16:   */   
/* 17:   */   public Layout(LengthUnit x, LengthUnit y, LengthUnit width, LengthUnit height, int layoutType)
/* 18:   */   {
/* 19:55 */     this.x = x;
/* 20:56 */     this.y = y;
/* 21:57 */     this.width = width;
/* 22:58 */     this.height = height;
/* 23:59 */     this.layoutType = layoutType;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public Rectangle2D_ getBounds()
/* 27:   */   {
/* 28:63 */     return new Rectangle2D_(UnitTranslator.lengthUnitToPixel(this.x), UnitTranslator.lengthUnitToPixel(this.y), UnitTranslator.lengthUnitToPixel(this.width), UnitTranslator.lengthUnitToPixel(this.height));
/* 29:   */   }
/* 30:   */   
/* 31:   */   public int getLayoutType()
/* 32:   */   {
/* 33:70 */     return this.layoutType;
/* 34:   */   }
/* 35:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Layout
 * JD-Core Version:    0.7.0.1
 */