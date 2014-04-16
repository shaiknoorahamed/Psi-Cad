/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Group;
/*  5:   */ import org.cadbox.j2d.scale.core.Shape;
/*  6:   */ 
/*  7:   */ public class DeleteShapeCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:42 */   Shape shape = null;
/* 11:43 */   Group layer = null;
/* 12:   */   
/* 13:   */   public DeleteShapeCommand(Group layer, Shape shape)
/* 14:   */   {
/* 15:46 */     this.shape = shape;
/* 16:47 */     this.layer = layer;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void execute()
/* 20:   */   {
/* 21:51 */     this.layer.removeShape(this.shape);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void unexecute()
/* 25:   */   {
/* 26:55 */     this.layer.addShape(this.shape);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String toString()
/* 30:   */   {
/* 31:59 */     return "";
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.DeleteShapeCommand
 * JD-Core Version:    0.7.0.1
 */