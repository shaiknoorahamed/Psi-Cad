/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Layer;
/*  5:   */ 
/*  6:   */ public class LayerVisibleChangeCommand
/*  7:   */   implements UndoableCommand
/*  8:   */ {
/*  9:   */   Layer layer;
/* 10:   */   boolean newState;
/* 11:   */   boolean oldState;
/* 12:   */   
/* 13:   */   public LayerVisibleChangeCommand(Layer layer, boolean newState)
/* 14:   */   {
/* 15:48 */     this.layer = layer;
/* 16:49 */     this.newState = newState;
/* 17:   */     
/* 18:51 */     this.oldState = layer.getVisible();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void execute()
/* 22:   */   {
/* 23:55 */     this.layer.setVisible(this.newState);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void unexecute()
/* 27:   */   {
/* 28:59 */     this.layer.setVisible(this.oldState);
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.LayerVisibleChangeCommand
 * JD-Core Version:    0.7.0.1
 */