/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.util.Collection;
/*  4:   */ import java.util.Iterator;
/*  5:   */ import org.cadbox.Command;
/*  6:   */ import org.cadbox.Document;
/*  7:   */ import org.cadbox.MacroCommand;
/*  8:   */ import org.cadbox.SimpleURManager;
/*  9:   */ import org.cadbox.UREngine;
/* 10:   */ import org.cadbox.URManager;
/* 11:   */ import org.cadbox.j2d.CadApplication;
/* 12:   */ import org.cadbox.j2d.ClipBoard;
/* 13:   */ import org.cadbox.j2d.scale.CadDrawingEngine;
/* 14:   */ import org.cadbox.j2d.scale.core.Shape;
/* 15:   */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/* 16:   */ 
/* 17:   */ public class PasteCommand
/* 18:   */   implements Command
/* 19:   */ {
/* 20:   */   private CadApplication application;
/* 21:   */   
/* 22:   */   public PasteCommand(CadApplication application)
/* 23:   */   {
/* 24:53 */     this.application = application;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void execute()
/* 28:   */   {
/* 29:57 */     Collection col = ClipBoard.getInstance().pop();
/* 30:   */     
/* 31:59 */     Document document = this.application.getCurrentDocument();
/* 32:60 */     CadDocumentWindow wnd = this.application.getCadDocumentWindow(document);
/* 33:   */     
/* 34:62 */     MacroCommand cmd = new MacroCommand();
/* 35:   */     
/* 36:64 */     Iterator iter = col.iterator();
/* 37:65 */     while (iter.hasNext()) {
/* 38:66 */       cmd.addCommand(wnd.getDrawingEngine().addShapeAll((Shape)iter.next()));
/* 39:   */     }
/* 40:69 */     if (cmd.getCommandCount() > 0)
/* 41:   */     {
/* 42:70 */       SimpleURManager.getInstance().getUREngine(document).addCommand(cmd);
/* 43:71 */       cmd.execute();
/* 44:   */     }
/* 45:74 */     this.application.updateAllObservers();
/* 46:   */   }
/* 47:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.PasteCommand
 * JD-Core Version:    0.7.0.1
 */