/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ 
/*  5:   */ public class ColorManager
/*  6:   */ {
/*  7:41 */   private static ColorManager instance = new ColorManager();
/*  8:43 */   private Color defaultColor = Color.black;
/*  9:   */   
/* 10:   */   public static ColorManager getInstance()
/* 11:   */   {
/* 12:49 */     return instance;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setDefaultColor(Color defaultColor)
/* 16:   */   {
/* 17:53 */     this.defaultColor = defaultColor;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public Color getDefaultColor()
/* 21:   */   {
/* 22:57 */     return this.defaultColor;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.ColorManager
 * JD-Core Version:    0.7.0.1
 */