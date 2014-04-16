/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import org.cadbox.j2d.scale.core.unit.MmUnit;
/*  4:   */ 
/*  5:   */ public class LayoutManager
/*  6:   */ {
/*  7:41 */   private static LayoutManager instance = new LayoutManager();
/*  8:43 */   private Layout defaultLayout = new Layout(new MmUnit(-1500000.0D), new MmUnit(1000000.0D), new MmUnit(3000000.0D), new MmUnit(2000000.0D), 0);
/*  9:   */   
/* 10:   */   public static LayoutManager getInstance()
/* 11:   */   {
/* 12:49 */     return instance;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public Layout getDefaultLayout()
/* 16:   */   {
/* 17:53 */     return this.defaultLayout;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setDefaultPageLayout(Layout defaultLayout)
/* 21:   */   {
/* 22:57 */     this.defaultLayout = defaultLayout;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.LayoutManager
 * JD-Core Version:    0.7.0.1
 */