/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Layer;
/*  5:   */ import org.cadbox.j2d.scale.core.LineWeight;
/*  6:   */ 
/*  7:   */ public class LayerLineweightChangeCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   Layer layer;
/* 11:   */   LineWeight newLineWeight;
/* 12:   */   LineWeight oldLineWeight;
/* 13:   */   
/* 14:   */   public LayerLineweightChangeCommand(Layer layer, LineWeight newLineWeight)
/* 15:   */   {
/* 16:51 */     this.layer = layer;
/* 17:52 */     this.newLineWeight = newLineWeight;
/* 18:   */     
/* 19:54 */     this.oldLineWeight = layer.getLineWeight();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void execute()
/* 23:   */   {
/* 24:58 */     this.layer.setLineWeight(this.newLineWeight);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void unexecute()
/* 28:   */   {
/* 29:62 */     this.layer.setLineWeight(this.oldLineWeight);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.LayerLineweightChangeCommand
 * JD-Core Version:    0.7.0.1
 */