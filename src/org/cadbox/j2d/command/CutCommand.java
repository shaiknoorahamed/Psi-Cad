/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.util.Collection;
/*  4:   */ import org.cadbox.Command;
/*  5:   */ import org.cadbox.j2d.CadApplication;
/*  6:   */ import org.cadbox.j2d.CadDocument;
/*  7:   */ import org.cadbox.j2d.ClipBoard;
/*  8:   */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  9:   */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/* 10:   */ 
/* 11:   */ public class CutCommand
/* 12:   */   implements Command
/* 13:   */ {
/* 14:   */   private CadApplication application;
/* 15:   */   
/* 16:   */   public CutCommand(CadApplication application)
/* 17:   */   {
/* 18:50 */     this.application = application;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void execute()
/* 22:   */   {
/* 23:54 */     CadDocument cadDocument = (CadDocument)this.application.getCurrentDocument();
/* 24:55 */     CadDocumentWindow wnd = this.application.getCadDocumentWindow(cadDocument);
/* 25:56 */     Collection col = wnd.getDrawingEngine().getSelectedShapeList();
/* 26:   */     
/* 27:58 */     ClipBoard cb = ClipBoard.getInstance();
/* 28:   */     
/* 29:60 */     cb.clear();
/* 30:61 */     cb.push(col);
/* 31:   */     
/* 32:63 */     wnd.getDrawingEngine().deleteSelectedShape();
/* 33:   */     
/* 34:65 */     this.application.updateAllObservers();
/* 35:   */   }
/* 36:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.CutCommand
 * JD-Core Version:    0.7.0.1
 */