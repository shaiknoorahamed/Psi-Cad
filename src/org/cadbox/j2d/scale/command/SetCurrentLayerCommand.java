/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Layer;
/*  5:   */ import org.cadbox.j2d.scale.core.LayeredDocument;
/*  6:   */ 
/*  7:   */ public class SetCurrentLayerCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   LayeredDocument document;
/* 11:   */   Layer layer;
/* 12:   */   Layer oldLayer;
/* 13:   */   
/* 14:   */   public SetCurrentLayerCommand(LayeredDocument document, Layer layer)
/* 15:   */   {
/* 16:49 */     this.document = document;
/* 17:50 */     this.layer = layer;
/* 18:   */     
/* 19:52 */     this.oldLayer = document.getCurrentLayer();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void execute()
/* 23:   */   {
/* 24:56 */     this.document.setCurrentLayer(this.layer);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void unexecute()
/* 28:   */   {
/* 29:60 */     this.document.setCurrentLayer(this.oldLayer);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.SetCurrentLayerCommand
 * JD-Core Version:    0.7.0.1
 */