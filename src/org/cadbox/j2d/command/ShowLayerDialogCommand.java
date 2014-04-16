/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.Command;
/*  4:   */ import org.cadbox.Document;
/*  5:   */ import org.cadbox.j2d.CadApplication;
/*  6:   */ import org.cadbox.j2d.CadDocument;
/*  7:   */ import org.cadbox.j2d.widget.dialog.DialogManager;
/*  8:   */ import org.cadbox.j2d.widget.dialog.LayerSettingDialog;
/*  9:   */ 
/* 10:   */ public class ShowLayerDialogCommand
/* 11:   */   implements Command
/* 12:   */ {
/* 13:   */   private CadApplication application;
/* 14:   */   
/* 15:   */   public ShowLayerDialogCommand(CadApplication application)
/* 16:   */   {
/* 17:49 */     this.application = application;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void execute()
/* 21:   */   {
/* 22:53 */     LayerSettingDialog dialog = this.application.getDialogManager().getLayerDialog();
/* 23:54 */     Document document = this.application.getCurrentDocument();
/* 24:55 */     if ((document != null) && ((document instanceof CadDocument)))
/* 25:   */     {
/* 26:56 */       dialog.showModal();
/* 27:57 */       this.application.updateAllObservers();
/* 28:   */     }
/* 29:   */   }
/* 30:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.ShowLayerDialogCommand
 * JD-Core Version:    0.7.0.1
 */