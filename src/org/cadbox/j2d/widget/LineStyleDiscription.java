/*  1:   */ package org.cadbox.j2d.widget;
/*  2:   */ 
/*  3:   */ import java.awt.BasicStroke;
/*  4:   */ 
/*  5:   */ public class LineStyleDiscription
/*  6:   */ {
/*  7:   */   public String name;
/*  8:   */   public BasicStroke stroke;
/*  9:   */   
/* 10:   */   public LineStyleDiscription()
/* 11:   */   {
/* 12:42 */     this("Continuous", new BasicStroke(1.0F));
/* 13:   */   }
/* 14:   */   
/* 15:   */   public LineStyleDiscription(String name, BasicStroke stroke)
/* 16:   */   {
/* 17:46 */     this.name = name;
/* 18:47 */     this.stroke = stroke;
/* 19:   */   }
/* 20:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.LineStyleDiscription
 * JD-Core Version:    0.7.0.1
 */