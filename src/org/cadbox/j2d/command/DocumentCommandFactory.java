/*   1:    */ package org.cadbox.j2d.command;
/*   2:    */ 
/*   3:    */ import org.cadbox.Command;
/*   4:    */ import org.cadbox.Document;
/*   5:    */ import org.cadbox.j2d.CadApplication;
/*   6:    */ import org.cadbox.j2d.CadDocument;
/*   7:    */ import org.cadbox.j2d.scale.core.Layer;
/*   8:    */ import org.cadbox.j2d.widget.dialog.DialogManager;
/*   9:    */ import org.cadbox.j2d.widget.dialog.LineWeightSettingDialog;
/*  10:    */ 
/*  11:    */ public class DocumentCommandFactory
/*  12:    */ {
/*  13:    */   private CadApplication application;
/*  14: 48 */   String title1 = "Open file";
/*  15: 49 */   String title2 = "Save file";
/*  16: 50 */   String extension = ".cad";
/*  17: 51 */   String discription = "Cad file";
/*  18:    */   
/*  19:    */   public DocumentCommandFactory(CadApplication application)
/*  20:    */   {
/*  21: 54 */     this.application = application;
/*  22: 55 */     init();
/*  23:    */   }
/*  24:    */   
/*  25:    */   private void init() {}
/*  26:    */   
/*  27:    */   public Command createNewDocumentCommand()
/*  28:    */   {
/*  29: 62 */     return new NewDocumentCommand(this.application, 0);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public Command createOpenDocumentCommand()
/*  33:    */   {
/*  34: 66 */     OpenDocumentCommand openDocumentCommand = new OpenDocumentCommand(this.application, this.title1, this.extension, this.discription);
/*  35: 67 */     return openDocumentCommand;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public Command createSaveDocumentCommand()
/*  39:    */   {
/*  40: 71 */     return new SaveDocumentCommand(this.application, this.title2, this.extension, this.discription);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public Command createSaveAsDocumentCommand()
/*  44:    */   {
/*  45: 75 */     return new SaveAsDocumentCommand(this.application, this.title2, this.extension, this.discription);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public Command createCloseDocumentCommand()
/*  49:    */   {
/*  50: 79 */     return new CloseDocumentCommand(this.application, this.title2, this.extension, this.discription);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public Command createCloseAllCommand()
/*  54:    */   {
/*  55: 83 */     return new CloseAllCommand(this.application, this.title2, this.extension, this.discription);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public Command createExitCommand()
/*  59:    */   {
/*  60: 87 */     return new ExitCommand(this.application, this.title2, this.extension, this.discription);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public Command createRedoCommand()
/*  64:    */   {
/*  65: 91 */     return new RedoCommand(this.application);
/*  66:    */   }
/*  67:    */   
/*  68:    */   public Command createUndoCommand()
/*  69:    */   {
/*  70: 95 */     return new UndoCommand(this.application);
/*  71:    */   }
/*  72:    */   
/*  73:    */   public Command createShowColorDialogCommand()
/*  74:    */   {
/*  75: 99 */     return new ShowColoDialogCommand(this.application);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public ShowLayerColorDialogCommand createShowLayerColorDialogCommand(Layer layer)
/*  79:    */   {
/*  80:103 */     return new ShowLayerColorDialogCommand(this.application, layer);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public Command createShowLineWeightDialogCommand()
/*  84:    */   {
/*  85:107 */     return new ShowLineWeightDialogCommand(this.application);
/*  86:    */   }
/*  87:    */   
/*  88:    */   public Command createPrintCommand()
/*  89:    */   {
/*  90:111 */     return new PrintCommand();
/*  91:    */   }
/*  92:    */   
/*  93:    */   public Command createCopyCommand()
/*  94:    */   {
/*  95:115 */     return new CopyCommand(this.application);
/*  96:    */   }
/*  97:    */   
/*  98:    */   public Command createPasteCommand()
/*  99:    */   {
/* 100:119 */     return new PasteCommand(this.application);
/* 101:    */   }
/* 102:    */   
/* 103:    */   public Command createCutCommand()
/* 104:    */   {
/* 105:123 */     return new CutCommand(this.application);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void lineWeightAction()
/* 109:    */   {
/* 110:127 */     Document doc = this.application.getCurrentDocument();
/* 111:128 */     if ((doc != null) && ((doc instanceof CadDocument)))
/* 112:    */     {
/* 113:129 */       LineWeightSettingDialog lineWeightDialog = this.application.getDialogManager().getLineWeightSettingDialog();
/* 114:130 */       if (lineWeightDialog.showModal() == LineWeightSettingDialog.OK_MODAL_RESULT) {
/* 115:131 */         this.application.updateAllObservers();
/* 116:    */       }
/* 117:    */     }
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void chooseColorAction()
/* 121:    */   {
/* 122:137 */     Document doc = this.application.getCurrentDocument();
/* 123:138 */     if ((doc != null) && ((doc instanceof CadDocument))) {}
/* 124:    */   }
/* 125:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.DocumentCommandFactory
 * JD-Core Version:    0.7.0.1
 */