/*   1:    */ package org.cadbox.j2d.widget.toolbar;
/*   2:    */ 
/*   3:    */ import java.awt.Dimension;
/*   4:    */ import javax.swing.JLabel;
/*   5:    */ import org.cadbox.Document;
/*   6:    */ import org.cadbox.Observer;
/*   7:    */ import org.cadbox.j2d.CadApplication;
/*   8:    */ import org.cadbox.j2d.CadDocument;
/*   9:    */ import org.cadbox.j2d.widget.ColorComboBox;
/*  10:    */ import org.cadbox.j2d.widget.LineStyleComboBox;
/*  11:    */ import org.cadbox.j2d.widget.LineWeightComboBox;
/*  12:    */ import org.cadbox.plaf.ToolBar;
/*  13:    */ 
/*  14:    */ public class PropertiesToolBar
/*  15:    */   extends ToolBar
/*  16:    */   implements Observer
/*  17:    */ {
/*  18:    */   ColorComboBox colorComboBox;
/*  19:    */   LineWeightComboBox lineWeightComboBox;
/*  20:    */   LineStyleComboBox lineStyleComboBox;
/*  21:    */   CadApplication application;
/*  22:    */   
/*  23:    */   public PropertiesToolBar(CadApplication application)
/*  24:    */   {
/*  25: 60 */     this.application = application;
/*  26:    */     
/*  27: 62 */     setName("Properties");
/*  28: 63 */     setRollover(true);
/*  29:    */     
/*  30:    */ 
/*  31: 66 */     this.colorComboBox = new ColorComboBox(application);
/*  32:    */     
/*  33:    */ 
/*  34: 69 */     this.lineStyleComboBox = new LineStyleComboBox();
/*  35:    */     
/*  36:    */ 
/*  37: 72 */     this.lineWeightComboBox = new LineWeightComboBox(application);
/*  38:    */     
/*  39: 74 */     add(this.colorComboBox);
/*  40:    */     
/*  41:    */ 
/*  42: 77 */     add(this.lineStyleComboBox);
/*  43:    */     
/*  44: 79 */     add(this.lineWeightComboBox);
/*  45:    */     
/*  46: 81 */     JLabel l = new JLabel();
/*  47: 82 */     l.setPreferredSize(new Dimension(0, 25));
/*  48: 83 */     add(l);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void setEnabled(boolean state)
/*  52:    */   {
/*  53: 87 */     this.colorComboBox.setEnabled(state);
/*  54: 88 */     this.lineWeightComboBox.setEnabled(state);
/*  55: 89 */     this.lineStyleComboBox.setEnabled(state);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void update()
/*  59:    */   {
/*  60: 93 */     Document doc = this.application.getCurrentDocument();
/*  61: 94 */     if ((doc instanceof CadDocument))
/*  62:    */     {
/*  63: 95 */       setEnabled(true);
/*  64:    */       
/*  65: 97 */       this.colorComboBox.update();
/*  66: 98 */       this.lineWeightComboBox.update();
/*  67:    */     }
/*  68:    */     else
/*  69:    */     {
/*  70:100 */       setEnabled(false);
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void doSelectColor()
/*  75:    */   {
/*  76:105 */     this.colorComboBox.doSelectColor();
/*  77:    */   }
/*  78:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.PropertiesToolBar
 * JD-Core Version:    0.7.0.1
 */