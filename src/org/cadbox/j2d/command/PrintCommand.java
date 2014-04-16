/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.awt.print.PageFormat;
/*  4:   */ import java.awt.print.PrinterJob;
/*  5:   */ import org.cadbox.UndoableCommand;
/*  6:   */ import org.cadbox.j2d.scale.core.unit.InchUnit;
/*  7:   */ import org.cadbox.j2d.scale.core.unit.MmUnit;
/*  8:   */ import org.cadbox.j2d.scale.core.unit.UnitTranslator;
/*  9:   */ 
/* 10:   */ public class PrintCommand
/* 11:   */   implements UndoableCommand
/* 12:   */ {
/* 13:   */   public void execute()
/* 14:   */   {
/* 15:49 */     PrinterJob printerJob = PrinterJob.getPrinterJob();
/* 16:   */     double w;
/* 17:50 */     if (printerJob.printDialog())
/* 18:   */     {
/* 19:51 */       PageFormat pageFormat = printerJob.defaultPage();
/* 20:52 */       pageFormat = printerJob.pageDialog(pageFormat);
/* 21:   */       
/* 22:54 */       double h = pageFormat.getHeight();
/* 23:   */       
/* 24:   */ 
/* 25:57 */       InchUnit ich = new InchUnit(h);
/* 26:   */       
/* 27:59 */       MmUnit m = UnitTranslator.inchToMilimetor(ich);
/* 28:   */       
/* 29:61 */       double v = m.getValue();
/* 30:   */       
/* 31:63 */       w = 0.0D;
/* 32:   */     }
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void unexecute() {}
/* 36:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.PrintCommand
 * JD-Core Version:    0.7.0.1
 */