/*   1:    */ package org.cadbox.j2d.widget.window;
/*   2:    */ 
/*   3:    */ import java.awt.BorderLayout;
/*   4:    */ import java.awt.event.MouseEvent;
/*   5:    */ import java.awt.event.MouseMotionListener;
/*   6:    */ import java.beans.PropertyChangeEvent;
/*   7:    */ import java.beans.PropertyChangeListener;
/*   8:    */ import java.beans.PropertyChangeSupport;
/*   9:    */ import org.cadbox.j2d.CadDocument;
/*  10:    */ import org.cadbox.j2d.CadDocumentInfo;
/*  11:    */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  12:    */ import org.cadbox.j2d.scale.core.BasicDocument;
/*  13:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*  14:    */ 
/*  15:    */ public class CadDocumentWindow
/*  16:    */   extends DocumentWindow
/*  17:    */   implements MouseMotionListener
/*  18:    */ {
/*  19: 51 */   private ScalePanel scalePanel = null;
/*  20:    */   private CadDocument document;
/*  21:    */   private CadDrawingEngine drawingEngine;
/*  22:    */   PropertyChangeSupport propertyChangeSupport;
/*  23:    */   
/*  24:    */   public CadDocumentWindow(CadDocument document)
/*  25:    */     throws Exception
/*  26:    */   {
/*  27: 61 */     this.document = document;
/*  28:    */     
/*  29: 63 */     this.propertyChangeSupport = new PropertyChangeSupport(this);
/*  30:    */     
/*  31: 65 */     setLayout(new BorderLayout());
/*  32:    */     
/*  33: 67 */     this.drawingEngine = new CadDrawingEngine(document, this.propertyChangeSupport);
/*  34:    */     
/*  35: 69 */     this.scalePanel = new ScalePanel(document, this.drawingEngine);
/*  36: 70 */     this.scalePanel.addMouseMotionListener(this);
/*  37: 71 */     add(this.scalePanel, "Center");
/*  38:    */     
/*  39:    */ 
/*  40: 74 */     this.propertyChangeSupport.addPropertyChangeListener(new PropertyChangeListener()
/*  41:    */     {
/*  42:    */       public void propertyChange(PropertyChangeEvent evt)
/*  43:    */       {
/*  44: 76 */         CadDocumentWindow.this.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
/*  45:    */       }
/*  46:    */     });
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void setTitle(String title)
/*  50:    */   {
/*  51: 84 */     this.document.getDocumentInfo().setFileName(title);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public String getTitle()
/*  55:    */   {
/*  56: 88 */     return this.document.getDocumentInfo().getFileName();
/*  57:    */   }
/*  58:    */   
/*  59:    */   public BasicDocument getDocument()
/*  60:    */   {
/*  61: 92 */     return this.scalePanel.getDocument();
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setDrawingEngine(CadDrawingEngine drawingEngine)
/*  65:    */   {
/*  66: 96 */     this.scalePanel.setDrawingEngine(drawingEngine);
/*  67:    */   }
/*  68:    */   
/*  69:    */   public CadDrawingEngine getDrawingEngine()
/*  70:    */   {
/*  71:100 */     return this.scalePanel.getDrawingEngine();
/*  72:    */   }
/*  73:    */   
/*  74:    */   public DrawingContext getDrawingContext()
/*  75:    */   {
/*  76:104 */     return this.scalePanel.getDrawingContext();
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void mouseMoved(MouseEvent evt) {}
/*  80:    */   
/*  81:    */   public void mouseDragged(MouseEvent evt) {}
/*  82:    */   
/*  83:    */   public void update()
/*  84:    */   {
/*  85:116 */     this.scalePanel.refresh();
/*  86:    */   }
/*  87:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.window.CadDocumentWindow
 * JD-Core Version:    0.7.0.1
 */