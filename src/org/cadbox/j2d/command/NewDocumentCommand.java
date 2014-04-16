/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import javax.swing.JOptionPane;
/*  4:   */ import org.cadbox.Application;
/*  5:   */ import org.cadbox.Command;
/*  6:   */ import org.cadbox.Document;
/*  7:   */ import org.cadbox.SimpleUREngine;
/*  8:   */ import org.cadbox.SimpleURManager;
/*  9:   */ import org.cadbox.UREngine;
/* 10:   */ import org.cadbox.URManager;
/* 11:   */ import org.cadbox.j2d.CadDocument;
/* 12:   */ import org.cadbox.j2d.scale.core.Layout;
/* 13:   */ import org.cadbox.j2d.scale.core.LayoutManager;
/* 14:   */ 
/* 15:   */ public class NewDocumentCommand
/* 16:   */   implements Command
/* 17:   */ {
/* 18:   */   private int type;
/* 19:   */   private Application application;
/* 20:   */   
/* 21:   */   public NewDocumentCommand(Application application, int type)
/* 22:   */   {
/* 23:54 */     this.type = type;
/* 24:55 */     this.application = application;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void execute()
/* 28:   */   {
/* 29:   */     try
/* 30:   */     {
/* 31:60 */       Document doc = null;
/* 32:62 */       if (this.type == 0)
/* 33:   */       {
/* 34:63 */         Layout layout = LayoutManager.getInstance().getDefaultLayout();
/* 35:64 */         doc = new CadDocument(layout);
/* 36:   */       }
/* 37:67 */       if ((this.type != 1) || (
/* 38:   */       
/* 39:   */ 
/* 40:   */ 
/* 41:71 */         (this.type != 2) || 
/* 42:   */         
/* 43:   */ 
/* 44:   */ 
/* 45:75 */         (doc != null)))
/* 46:   */       {
/* 47:76 */         this.application.addDocument(doc);
/* 48:   */         
/* 49:   */ 
/* 50:79 */         UREngine urEngine = new SimpleUREngine();
/* 51:80 */         SimpleURManager.getInstance().registerUREngine(urEngine, doc);
/* 52:   */         
/* 53:82 */         this.application.updateAllObservers();
/* 54:   */       }
/* 55:   */     }
/* 56:   */     catch (VirtualMachineError err)
/* 57:   */     {
/* 58:85 */       JOptionPane.showMessageDialog(null, "Couldn't create document. Out of memory!!!");
/* 59:   */     }
/* 60:   */     catch (Exception ex)
/* 61:   */     {
/* 62:87 */       JOptionPane.showMessageDialog(null, ex.getMessage());
/* 63:   */     }
/* 64:   */   }
/* 65:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.NewDocumentCommand
 * JD-Core Version:    0.7.0.1
 */