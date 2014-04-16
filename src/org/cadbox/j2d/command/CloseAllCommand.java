/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.Collection;
/*  6:   */ import java.util.Iterator;
/*  7:   */ import java.util.List;
/*  8:   */ import javax.swing.JOptionPane;
/*  9:   */ import org.cadbox.Application;
/* 10:   */ import org.cadbox.SimpleURManager;
/* 11:   */ import org.cadbox.URManager;
/* 12:   */ import org.cadbox.j2d.CadDocument;
/* 13:   */ import org.cadbox.j2d.CadDocumentInfo;
/* 14:   */ 
/* 15:   */ public class CloseAllCommand
/* 16:   */   extends DocumentCommand
/* 17:   */ {
/* 18:   */   Application application;
/* 19:   */   private String title;
/* 20:   */   private String extension;
/* 21:   */   private String description;
/* 22:   */   
/* 23:   */   public CloseAllCommand(Application application, String title, String extension, String description)
/* 24:   */   {
/* 25:51 */     this.application = application;
/* 26:52 */     this.title = title;
/* 27:53 */     this.extension = extension;
/* 28:54 */     this.description = description;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void execute()
/* 32:   */   {
/* 33:58 */     Iterator iter = this.application.getDocuments().iterator();
/* 34:59 */     List doclist = new ArrayList();
/* 35:61 */     while (iter.hasNext()) {
/* 36:62 */       doclist.add(iter.next());
/* 37:   */     }
/* 38:65 */     iter = doclist.iterator();
/* 39:67 */     while (iter.hasNext())
/* 40:   */     {
/* 41:68 */       CadDocument doc = (CadDocument)iter.next();
/* 42:69 */       if (doc != null) {
/* 43:70 */         if (doc.isModified())
/* 44:   */         {
/* 45:71 */           String title = doc.getDocumentInfo().getFileName();
/* 46:72 */           int option = JOptionPane.showConfirmDialog(null, "Save " + title + "?");
/* 47:73 */           if (option == 0)
/* 48:   */           {
/* 49:74 */             File file = askUser(title, this.extension, this.description);
/* 50:75 */             if (file != null) {
/* 51:76 */               doc.save(file.getPath());
/* 52:   */             } else {
/* 53:78 */               option = 2;
/* 54:   */             }
/* 55:   */           }
/* 56:81 */           if ((option == 0) || (option == 1))
/* 57:   */           {
/* 58:82 */             this.application.removeDocument(doc);
/* 59:   */             
/* 60:   */ 
/* 61:85 */             SimpleURManager.getInstance().unregisterUREngine(doc);
/* 62:   */           }
/* 63:   */         }
/* 64:   */         else
/* 65:   */         {
/* 66:88 */           this.application.removeDocument(doc);
/* 67:   */           
/* 68:   */ 
/* 69:91 */           SimpleURManager.getInstance().unregisterUREngine(doc);
/* 70:   */         }
/* 71:   */       }
/* 72:   */     }
/* 73:95 */     this.application.updateAllObservers();
/* 74:   */   }
/* 75:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.CloseAllCommand
 * JD-Core Version:    0.7.0.1
 */