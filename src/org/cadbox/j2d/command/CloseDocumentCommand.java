 package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import javax.swing.JOptionPane;
/*  5:   */ import org.cadbox.Application;
/*  6:   */ import org.cadbox.SimpleURManager;
/*  7:   */ import org.cadbox.URManager;
/*  8:   */ import org.cadbox.j2d.CadDocument;
/*  9:   */ import org.cadbox.j2d.CadDocumentInfo;
/* 10:   */ 
/* 11:   */ public class CloseDocumentCommand
/* 12:   */   extends DocumentCommand
/* 13:   */ {
/* 14:   */   Application application;
/* 15:   */   private String title;
/* 16:   */   private String extension;
/* 17:   */   private String description;
/* 18:   */   
/* 19:   */   public CloseDocumentCommand(Application application, String title, String extension, String description)
/* 20:   */   {
/* 21:50 */     this.application = application;
/* 22:51 */     this.title = title;
/* 23:52 */     this.extension = extension;
/* 24:53 */     this.description = description;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void execute()
/* 28:   */   {
/* 29:57 */     CadDocument doc = (CadDocument)this.application.getCurrentDocument();
/* 30:58 */     if (doc != null)
/* 31:   */     {
/* 32:59 */       if (doc.isModified())
/* 33:   */       {
/* 34:60 */         String title = doc.getDocumentInfo().getFileName();
/* 35:61 */         int option = JOptionPane.showConfirmDialog(null, "Save " + title + "?");
/* 36:62 */         if (option == 0)
/* 37:   */         {
/* 38:63 */           File file = askUser(title, this.extension, this.description);
/* 39:64 */           if (file != null) {
/* 40:65 */             doc.save(file.getPath());
/* 41:   */           } else {
/* 42:67 */             option = 2;
/* 43:   */           }
/* 44:   */         }
/* 45:70 */         if ((option == 0) || (option == 1))
/* 46:   */         {
/* 47:71 */           this.application.removeDocument(doc);
/* 48:   */           
/* 49:   */ 
/* 50:74 */           SimpleURManager.getInstance().unregisterUREngine(doc);
/* 51:   */         }
/* 52:   */       }
/* 53:   */       else
/* 54:   */       {
/* 55:77 */         this.application.removeDocument(doc);
/* 56:   */         
/* 57:   */ 
/* 58:80 */         SimpleURManager.getInstance().unregisterUREngine(doc);
/* 59:   */       }
/* 60:82 */       this.application.updateAllObservers();
/* 61:   */     }
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.CloseDocumentCommand
 * JD-Core Version:    0.7.0.1
 */