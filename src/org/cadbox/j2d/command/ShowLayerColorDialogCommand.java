/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import javax.swing.JColorChooser;
/*  5:   */ import org.cadbox.Command;
/*  6:   */ import org.cadbox.j2d.CadApplication;
/*  7:   */ import org.cadbox.j2d.scale.core.Layer;
/*  8:   */ import org.cadbox.j2d.widget.dialog.DialogManager;
/*  9:   */ 
/* 10:   */ public class ShowLayerColorDialogCommand
/* 11:   */   implements Command
/* 12:   */ {
/* 13:   */   private CadApplication application;
/* 14:   */   private Layer layer;
/* 15:   */   private Color result;
/* 16:   */   
/* 17:   */   public ShowLayerColorDialogCommand(CadApplication application, Layer layer)
/* 18:   */   {
/* 19:51 */     this.application = application;
/* 20:52 */     this.layer = layer;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void execute()
/* 24:   */   {
/* 25:56 */     JColorChooser dialog = this.application.getDialogManager().getColorDialog();
/* 26:57 */     if (this.layer != null)
/* 27:   */     {
/* 28:59 */       this.result = JColorChooser.showDialog(null, "Choose color", this.layer.getColor());
/* 29:   */       
/* 30:   */ 
/* 31:   */ 
/* 32:   */ 
/* 33:   */ 
/* 34:65 */       this.application.updateAllObservers();
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public Color getColor()
/* 39:   */   {
/* 40:70 */     return this.result;
/* 41:   */   }
/* 42:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.ShowLayerColorDialogCommand
 * JD-Core Version:    0.7.0.1
 */