/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.Command;
/*  4:   */ import org.cadbox.j2d.CadApplication;
/*  5:   */ import org.cadbox.j2d.widget.dialog.DialogManager;
/*  6:   */ import org.cadbox.j2d.widget.dialog.LineWeightSettingDialog;
/*  7:   */ 
/*  8:   */ public class ShowLineWeightDialogCommand
/*  9:   */   implements Command
/* 10:   */ {
/* 11:   */   private CadApplication application;
/* 12:   */   
/* 13:   */   public ShowLineWeightDialogCommand(CadApplication application)
/* 14:   */   {
/* 15:47 */     this.application = application;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void execute()
/* 19:   */   {
/* 20:51 */     LineWeightSettingDialog dialog = this.application.getDialogManager().getLineWeightSettingDialog();
/* 21:52 */     dialog.showModal();
/* 22:53 */     this.application.updateAllObservers();
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.ShowLineWeightDialogCommand
 * JD-Core Version:    0.7.0.1
 */