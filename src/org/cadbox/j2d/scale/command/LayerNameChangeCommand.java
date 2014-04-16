/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Layer;
/*  5:   */ 
/*  6:   */ public class LayerNameChangeCommand
/*  7:   */   implements UndoableCommand
/*  8:   */ {
/*  9:   */   Layer layer;
/* 10:   */   String newName;
/* 11:   */   String oldName;
/* 12:   */   
/* 13:   */   public LayerNameChangeCommand(Layer layer, String newName)
/* 14:   */   {
/* 15:48 */     this.layer = layer;
/* 16:49 */     this.newName = newName;
/* 17:   */     
/* 18:51 */     this.oldName = layer.getName();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void execute()
/* 22:   */   {
/* 23:55 */     this.layer.setName(this.newName);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void unexecute()
/* 27:   */   {
/* 28:59 */     this.layer.setName(this.oldName);
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.LayerNameChangeCommand
 * JD-Core Version:    0.7.0.1
 */