/*  1:   */ package org.cadbox.j2d.widget.dialog;
/*  2:   */ 
/*  3:   */ import javax.swing.JColorChooser;
/*  4:   */ import javax.swing.JFrame;
/*  5:   */ import org.cadbox.j2d.CadApplication;
/*  6:   */ 
/*  7:   */ public class DialogManager
/*  8:   */ {
/*  9:   */   private CadApplication application;
/* 10:   */   private JFrame appWindow;
/* 11:47 */   private JColorChooser colorDialog1 = null;
/* 12:48 */   private LayerSettingDialog layerDialog = null;
/* 13:49 */   private LineWeightChooseDialog lineWeightChooseDialog = null;
/* 14:50 */   private LineWeightSettingDialog lineWeightSettingDialog = null;
/* 15:51 */   private AboutDialog aboutDialog = null;
/* 16:   */   
/* 17:   */   public DialogManager(CadApplication application)
/* 18:   */   {
/* 19:54 */     this.application = application;
/* 20:55 */     this.appWindow = application.getAppWindow();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public JColorChooser getColorDialog()
/* 24:   */   {
/* 25:59 */     if (this.colorDialog1 == null) {
/* 26:60 */       this.colorDialog1 = new JColorChooser();
/* 27:   */     }
/* 28:61 */     return this.colorDialog1;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public LayerSettingDialog getLayerDialog()
/* 32:   */   {
/* 33:65 */     if (this.layerDialog == null) {
/* 34:66 */       this.layerDialog = new LayerSettingDialog(this.appWindow, true, this.application);
/* 35:   */     }
/* 36:67 */     return this.layerDialog;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public LineWeightChooseDialog getLineWeightChooseDialog()
/* 40:   */   {
/* 41:71 */     if (this.lineWeightChooseDialog == null) {
/* 42:72 */       this.lineWeightChooseDialog = new LineWeightChooseDialog(this.appWindow, true);
/* 43:   */     }
/* 44:73 */     return this.lineWeightChooseDialog;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public LineWeightSettingDialog getLineWeightSettingDialog()
/* 48:   */   {
/* 49:77 */     if (this.lineWeightSettingDialog == null) {
/* 50:78 */       this.lineWeightSettingDialog = new LineWeightSettingDialog(this.appWindow, true, this.application);
/* 51:   */     }
/* 52:79 */     return this.lineWeightSettingDialog;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public AboutDialog getAboutDialog()
/* 56:   */   {
/* 57:83 */     if (this.aboutDialog == null) {
/* 58:84 */       this.aboutDialog = new AboutDialog(this.appWindow, true);
/* 59:   */     }
/* 60:85 */     return this.aboutDialog;
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.dialog.DialogManager
 * JD-Core Version:    0.7.0.1
 */