/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.Layer;
/*  5:   */ import org.cadbox.j2d.scale.core.LayeredDocument;
/*  6:   */ 
/*  7:   */ public class AddLayerCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   LayeredDocument document;
/* 11:   */   Layer layer;
/* 12:   */   
/* 13:   */   public AddLayerCommand(LayeredDocument document, Layer layer)
/* 14:   */   {
/* 15:48 */     this.document = document;
/* 16:49 */     this.layer = layer;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void execute()
/* 20:   */   {
/* 21:53 */     this.document.addLayer(this.layer);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void unexecute()
/* 25:   */   {
/* 26:57 */     this.document.removeLayer(this.layer);
/* 27:   */   }
/* 28:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.AddLayerCommand
 * JD-Core Version:    0.7.0.1
 */