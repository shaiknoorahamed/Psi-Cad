/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Shape;
/*  5:   */ 
/*  6:   */ public class MoveShapeCommand
/*  7:   */   implements UndoableCommand
/*  8:   */ {
/*  9:   */   Shape shape;
/* 10:   */   double xV;
/* 11:   */   double yV;
/* 12:   */   
/* 13:   */   public MoveShapeCommand(Shape shape, double xV, double yV)
/* 14:   */   {
/* 15:46 */     this.shape = shape;
/* 16:47 */     this.xV = xV;
/* 17:48 */     this.yV = yV;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void execute()
/* 21:   */   {
/* 22:52 */     this.shape.move(this.xV, this.yV);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void unexecute()
/* 26:   */   {
/* 27:56 */     this.shape.move(-this.xV, -this.yV);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String toString()
/* 31:   */   {
/* 32:60 */     return "";
/* 33:   */   }
/* 34:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.MoveShapeCommand
 * JD-Core Version:    0.7.0.1
 */