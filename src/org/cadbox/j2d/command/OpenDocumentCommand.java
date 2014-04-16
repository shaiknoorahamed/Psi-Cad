/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import javax.swing.JComponent;
/*  5:   */ import javax.swing.JFileChooser;
/*  6:   */ import org.cadbox.Application;
/*  7:   */ import org.cadbox.SimpleUREngine;
/*  8:   */ import org.cadbox.SimpleURManager;
/*  9:   */ import org.cadbox.UREngine;
/* 10:   */ import org.cadbox.URManager;
/* 11:   */ import org.cadbox.j2d.CadDocument;
/* 12:   */ import org.cadbox.j2d.CadDocumentInfo;
/* 13:   */ import org.cadbox.j2d.scale.core.Layout;
/* 14:   */ import org.cadbox.j2d.scale.core.LayoutManager;
/* 15:   */ 
/* 16:   */ public class OpenDocumentCommand
/* 17:   */   extends DocumentCommand
/* 18:   */ {
/* 19:   */   private Application application;
/* 20:   */   private String title;
/* 21:   */   private String extension;
/* 22:   */   private String description;
/* 23:   */   private JComponent parent;
/* 24:   */   
/* 25:   */   public OpenDocumentCommand(Application application, String title, String extension, String description)
/* 26:   */   {
/* 27:59 */     this.application = application;
/* 28:60 */     this.title = title;
/* 29:61 */     this.extension = extension;
/* 30:62 */     this.description = description;
/* 31:   */   }
/* 32:   */   
/* 33:   */   protected File askUser()
/* 34:   */   {
/* 35:66 */     File file = null;
/* 36:67 */     JFileChooser fc = createFileChooser(this.title, this.extension, this.description);
/* 37:68 */     int option = fc.showOpenDialog(this.parent);
/* 38:69 */     if (0 == option) {
/* 39:70 */       file = fc.getSelectedFile();
/* 40:   */     }
/* 41:72 */     return file;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void execute()
/* 45:   */   {
/* 46:76 */     File file = askUser();
/* 47:77 */     if (file != null)
/* 48:   */     {
/* 49:78 */       Layout layout = LayoutManager.getInstance().getDefaultLayout();
/* 50:79 */       CadDocument doc = new CadDocument(layout);
/* 51:80 */       doc.open(file.getPath());
/* 52:81 */       doc.getDocumentInfo().setFileName(file.getName());
/* 53:82 */       doc.getDocumentInfo().setPath(file.getPath());
/* 54:83 */       this.application.addDocument(doc);
/* 55:   */       
/* 56:   */ 
/* 57:86 */       UREngine urEngine = new SimpleUREngine();
/* 58:87 */       SimpleURManager.getInstance().registerUREngine(urEngine, doc);
/* 59:   */       
/* 60:89 */       this.application.updateAllObservers();
/* 61:   */     }
/* 62:   */   }
/* 63:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.OpenDocumentCommand
 * JD-Core Version:    0.7.0.1
 */