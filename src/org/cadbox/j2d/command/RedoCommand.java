/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.Command;
/*  4:   */ import org.cadbox.Document;
/*  5:   */ import org.cadbox.SimpleURManager;
/*  6:   */ import org.cadbox.UREngine;
/*  7:   */ import org.cadbox.URManager;
/*  8:   */ import org.cadbox.j2d.CadApplication;
/*  9:   */ import org.cadbox.j2d.scale.CadDrawingEngine;
/* 10:   */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/* 11:   */ 
/* 12:   */ public class RedoCommand
/* 13:   */   implements Command
/* 14:   */ {
/* 15:45 */   CadApplication application = null;
/* 16:   */   
/* 17:   */   public RedoCommand(CadApplication application)
/* 18:   */   {
/* 19:48 */     this.application = application;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void execute()
/* 23:   */   {
/* 24:52 */     Document doc = this.application.getCurrentDocument();
/* 25:53 */     if (doc != null)
/* 26:   */     {
/* 27:54 */       UREngine urEngine = SimpleURManager.getInstance().getUREngine(doc);
/* 28:55 */       if ((urEngine != null) && 
/* 29:56 */         (urEngine.canRedo()))
/* 30:   */       {
/* 31:58 */         CadDocumentWindow frame = this.application.getCadDocumentWindow(doc);
/* 32:59 */         frame.getDrawingEngine().clearSelectedShapes();
/* 33:   */         
/* 34:61 */         urEngine.doRedo();
/* 35:62 */         this.application.updateAllObservers();
/* 36:   */       }
/* 37:   */     }
/* 38:   */   }
/* 39:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.RedoCommand
 * JD-Core Version:    0.7.0.1
 */