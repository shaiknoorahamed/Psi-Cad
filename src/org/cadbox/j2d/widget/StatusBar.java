/*  1:   */ package org.cadbox.j2d.widget;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import java.awt.Dimension;
/*  5:   */ import java.awt.FlowLayout;
/*  6:   */ import javax.swing.JLabel;
/*  7:   */ import javax.swing.JPanel;
/*  8:   */ import javax.swing.border.LineBorder;
/*  9:   */ import org.cadbox.Observer;
/* 10:   */ 
/* 11:   */ public class StatusBar
/* 12:   */   extends JPanel
/* 13:   */   implements Observer
/* 14:   */ {
/* 15:   */   private JLabel xFieldLabel;
/* 16:   */   private JLabel yFieldLabel;
/* 17:   */   
/* 18:   */   public StatusBar()
/* 19:   */   {
/* 20:47 */     init();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void init()
/* 24:   */   {
/* 25:51 */     this.xFieldLabel = new JLabel();
/* 26:52 */     this.yFieldLabel = new JLabel();
/* 27:   */     
/* 28:54 */     setLayout(new FlowLayout(0, 2, 2));
/* 29:55 */     setPreferredSize(new Dimension(2147483647, 20));
/* 30:   */     
/* 31:   */ 
/* 32:58 */     this.xFieldLabel.setPreferredSize(new Dimension(80, 16));
/* 33:59 */     this.xFieldLabel.setBorder(new LineBorder(Color.gray));
/* 34:   */     
/* 35:61 */     this.yFieldLabel.setPreferredSize(new Dimension(80, 16));
/* 36:62 */     this.yFieldLabel.setBorder(new LineBorder(Color.gray));
/* 37:   */     
/* 38:64 */     add(this.xFieldLabel);
/* 39:65 */     add(this.yFieldLabel);
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setXFieldValue(String value)
/* 43:   */   {
/* 44:69 */     this.xFieldLabel.setText(" " + value);
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setYFieldValue(String value)
/* 48:   */   {
/* 49:73 */     this.yFieldLabel.setText(" " + value);
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void update() {}
/* 53:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.StatusBar
 * JD-Core Version:    0.7.0.1
 */