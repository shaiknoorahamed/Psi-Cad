/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import org.cadbox.UndoableCommand;
/*  5:   */ import org.cadbox.j2d.scale.core.Layer;
/*  6:   */ 
/*  7:   */ public class LayerColorChangeCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   Layer layer;
/* 11:   */   Color newColor;
/* 12:   */   Color oldColor;
/* 13:   */   
/* 14:   */   public LayerColorChangeCommand(Layer layer, Color newColor)
/* 15:   */   {
/* 16:51 */     this.layer = layer;
/* 17:52 */     this.newColor = newColor;
/* 18:   */     
/* 19:54 */     this.oldColor = layer.getColor();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void execute()
/* 23:   */   {
/* 24:58 */     this.layer.setColor(this.newColor);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void unexecute()
/* 28:   */   {
/* 29:62 */     this.layer.setColor(this.oldColor);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.LayerColorChangeCommand
 * JD-Core Version:    0.7.0.1
 */