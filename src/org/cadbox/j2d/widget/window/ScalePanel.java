/*  1:   */ package org.cadbox.j2d.widget.window;
/*  2:   */ 
/*  3:   */ import javax.swing.JScrollPane;
/*  4:   */ import javax.swing.border.EmptyBorder;
/*  5:   */ import org.cadbox.j2d.CadDocument;
/*  6:   */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  7:   */ import org.cadbox.j2d.scale.core.BasicDocument;
/*  8:   */ import org.cadbox.j2d.scale.core.DrawingContext;
/*  9:   */ 
/* 10:   */ public class ScalePanel
/* 11:   */   extends JScrollPane
/* 12:   */ {
/* 13:   */   private CadPanel cadPanel;
/* 14:   */   
/* 15:   */   public ScalePanel(CadDocument document, CadDrawingEngine drawingEngine)
/* 16:   */   {
/* 17:54 */     setHorizontalScrollBarPolicy(31);
/* 18:55 */     setVerticalScrollBarPolicy(21);
/* 19:56 */     setBorder(new EmptyBorder(0, 0, 0, 0));
/* 20:   */     
/* 21:58 */     this.cadPanel = new CadPanel(document, drawingEngine);
/* 22:59 */     setViewportView(this.cadPanel);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public BasicDocument getDocument()
/* 26:   */   {
/* 27:64 */     return this.cadPanel.getDocument();
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setDrawingEngine(CadDrawingEngine drawingEngine)
/* 31:   */   {
/* 32:68 */     this.cadPanel.setDrawingEngine(drawingEngine);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public CadDrawingEngine getDrawingEngine()
/* 36:   */   {
/* 37:72 */     return this.cadPanel.drawingEngine;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public DrawingContext getDrawingContext()
/* 41:   */   {
/* 42:76 */     return this.cadPanel.drawingContext;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public CadPanel getCadPanel()
/* 46:   */   {
/* 47:80 */     return this.cadPanel;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void refresh()
/* 51:   */   {
/* 52:89 */     this.cadPanel.refresh();
/* 53:   */   }
/* 54:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.window.ScalePanel
 * JD-Core Version:    0.7.0.1
 */