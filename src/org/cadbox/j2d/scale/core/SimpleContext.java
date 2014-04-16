/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ public class SimpleContext
/*  4:   */   implements Context
/*  5:   */ {
/*  6:   */   DrawingContext drawingContext;
/*  7:   */   PropertyContext propertyContext;
/*  8:   */   
/*  9:   */   public SimpleContext(DrawingContext drawingContext, PropertyContext propertyContext)
/* 10:   */   {
/* 11:42 */     this.propertyContext = propertyContext;
/* 12:43 */     this.drawingContext = drawingContext;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public DrawingContext getDrawingContext()
/* 16:   */   {
/* 17:47 */     return this.drawingContext;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public PropertyContext getPropertyContext()
/* 21:   */   {
/* 22:51 */     return this.propertyContext;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.SimpleContext
 * JD-Core Version:    0.7.0.1
 */