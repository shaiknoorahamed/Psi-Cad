/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import javax.swing.JColorChooser;
/*  5:   */ import org.cadbox.Command;
/*  6:   */ import org.cadbox.Document;
/*  7:   */ import org.cadbox.SimpleURManager;
/*  8:   */ import org.cadbox.UREngine;
/*  9:   */ import org.cadbox.URManager;
/* 10:   */ import org.cadbox.j2d.CadApplication;
/* 11:   */ import org.cadbox.j2d.CadDocument;
/* 12:   */ import org.cadbox.j2d.scale.command.ColorChangeCommand;
/* 13:   */ import org.cadbox.j2d.scale.core.Context;
/* 14:   */ import org.cadbox.j2d.scale.core.PropertyContext;
/* 15:   */ import org.cadbox.j2d.widget.dialog.DialogManager;
/* 16:   */ 
/* 17:   */ public class ShowColoDialogCommand
/* 18:   */   implements Command
/* 19:   */ {
/* 20:   */   private CadApplication application;
/* 21:   */   
/* 22:   */   public ShowColoDialogCommand(CadApplication application)
/* 23:   */   {
/* 24:52 */     this.application = application;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void execute()
/* 28:   */   {
/* 29:56 */     JColorChooser dialog = this.application.getDialogManager().getColorDialog();
/* 30:57 */     Document document = this.application.getCurrentDocument();
/* 31:58 */     if ((document != null) && ((document instanceof CadDocument)))
/* 32:   */     {
/* 33:60 */       PropertyContext context = ((CadDocument)document).getContext().getPropertyContext();
/* 34:   */       
/* 35:62 */       Color result = JColorChooser.showDialog(null, "Choose color", context.getColor());
/* 36:   */       
/* 37:64 */       ColorChangeCommand cmd = new ColorChangeCommand(context, result);
/* 38:65 */       SimpleURManager.getInstance().getUREngine(this.application.getCurrentDocument()).addCommand(cmd);
/* 39:66 */       cmd.execute();
/* 40:   */       
/* 41:68 */       this.application.updateAllObservers();
/* 42:   */     }
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.ShowColoDialogCommand
 * JD-Core Version:    0.7.0.1
 */