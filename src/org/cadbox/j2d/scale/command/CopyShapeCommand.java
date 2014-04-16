/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Layer;
/*  5:   */ import org.cadbox.j2d.scale.core.Shape;
/*  6:   */ 
/*  7:   */ public class CopyShapeCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   Shape shape;
/* 11:   */   Layer layer;
/* 12:   */   
/* 13:   */   public CopyShapeCommand(Layer layer, Shape shape)
/* 14:   */   {
/* 15:47 */     this.shape = shape;
/* 16:48 */     this.layer = layer;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void execute()
/* 20:   */   {
/* 21:52 */     this.layer.addShape(this.shape);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void unexecute()
/* 25:   */   {
/* 26:56 */     this.layer.removeShape(this.shape);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String toString()
/* 30:   */   {
/* 31:60 */     return "";
/* 32:   */   }
/* 33:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.CopyShapeCommand
 * JD-Core Version:    0.7.0.1
 */