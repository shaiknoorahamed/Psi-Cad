/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Shape;
/*  5:   */ 
/*  6:   */ public class RotateShapeCommand
/*  7:   */   implements UndoableCommand
/*  8:   */ {
/*  9:   */   Shape shape;
/* 10:   */   double x;
/* 11:   */   double y;
/* 12:   */   double angle;
/* 13:   */   
/* 14:   */   public RotateShapeCommand(Shape shape, double x, double y, double angle)
/* 15:   */   {
/* 16:47 */     this.shape = shape;
/* 17:48 */     this.x = x;
/* 18:49 */     this.y = y;
/* 19:50 */     this.angle = angle;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void execute()
/* 23:   */   {
/* 24:54 */     this.shape.rotate(this.x, this.y, this.angle);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void unexecute()
/* 28:   */   {
/* 29:58 */     this.shape.rotate(this.x, this.y, -this.angle);
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String toString()
/* 33:   */   {
/* 34:62 */     return "";
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.RotateShapeCommand
 * JD-Core Version:    0.7.0.1
 */