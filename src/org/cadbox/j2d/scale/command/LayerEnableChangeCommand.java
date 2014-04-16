/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Layer;
/*  5:   */ 
/*  6:   */ public class LayerEnableChangeCommand
/*  7:   */   implements UndoableCommand
/*  8:   */ {
/*  9:   */   Layer layer;
/* 10:   */   boolean newState;
/* 11:   */   boolean oldState;
/* 12:   */   
/* 13:   */   public LayerEnableChangeCommand(Layer layer, boolean newState)
/* 14:   */   {
/* 15:49 */     this.layer = layer;
/* 16:50 */     this.newState = newState;
/* 17:   */     
/* 18:52 */     this.oldState = layer.getEnable();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void execute()
/* 22:   */   {
/* 23:56 */     this.layer.setEnable(this.newState);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void unexecute()
/* 27:   */   {
/* 28:60 */     this.layer.setEnable(this.oldState);
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.LayerEnableChangeCommand
 * JD-Core Version:    0.7.0.1
 */