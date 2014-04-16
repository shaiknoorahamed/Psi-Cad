/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Group;
/*  5:   */ import org.cadbox.j2d.scale.core.Shape;
/*  6:   */ 
/*  7:   */ public class ModifyShapeCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   Shape oldShape;
/* 11:   */   Shape newShape;
/* 12:   */   Group layer;
/* 13:   */   
/* 14:   */   public ModifyShapeCommand(Shape oldShape, Shape newShape)
/* 15:   */   {
/* 16:47 */     this.oldShape = oldShape;
/* 17:48 */     this.newShape = newShape;
/* 18:49 */     this.layer = oldShape.getParent();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void execute()
/* 22:   */   {
/* 23:53 */     this.layer.removeShape(this.oldShape);
/* 24:54 */     this.layer.addShape(this.newShape);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void unexecute()
/* 28:   */   {
/* 29:58 */     this.layer.removeShape(this.newShape);
/* 30:59 */     this.layer.addShape(this.oldShape);
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String toString()
/* 34:   */   {
/* 35:63 */     return "";
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.ModifyShapeCommand
 * JD-Core Version:    0.7.0.1
 */