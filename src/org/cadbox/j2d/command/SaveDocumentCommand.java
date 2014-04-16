/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import org.cadbox.Application;
/*  5:   */ import org.cadbox.j2d.CadDocument;
/*  6:   */ import org.cadbox.j2d.CadDocumentInfo;
/*  7:   */ 
/*  8:   */ public class SaveDocumentCommand
/*  9:   */   extends DocumentCommand
/* 10:   */ {
/* 11:   */   private Application application;
/* 12:   */   private String title;
/* 13:   */   private String extension;
/* 14:   */   private String description;
/* 15:   */   
/* 16:   */   public SaveDocumentCommand(Application application, String title, String extension, String description)
/* 17:   */   {
/* 18:48 */     this.application = application;
/* 19:49 */     this.title = title;
/* 20:50 */     this.extension = extension;
/* 21:51 */     this.description = description;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void execute()
/* 25:   */   {
/* 26:55 */     CadDocument doc = (CadDocument)this.application.getCurrentDocument();
/* 27:56 */     if (doc != null)
/* 28:   */     {
/* 29:57 */       File file = null;
/* 30:59 */       if (doc.isSaved()) {
/* 31:60 */         file = new File(doc.getDocumentInfo().getPath());
/* 32:   */       } else {
/* 33:62 */         file = askUser(this.title, this.extension, this.description);
/* 34:   */       }
/* 35:64 */       if (file != null)
/* 36:   */       {
/* 37:65 */         doc.save(file.getPath());
/* 38:66 */         doc.getDocumentInfo().setFileName(file.getName());
/* 39:67 */         doc.getDocumentInfo().setPath(file.getPath());
/* 40:68 */         this.application.updateAllObservers();
/* 41:   */       }
/* 42:   */     }
/* 43:   */   }
/* 44:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.SaveDocumentCommand
 * JD-Core Version:    0.7.0.1
 */