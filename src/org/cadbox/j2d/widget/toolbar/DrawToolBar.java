/*   1:    */ package org.cadbox.j2d.widget.toolbar;
/*   2:    */ 
/*   3:    */ import java.awt.Dimension;
/*   4:    */ import java.awt.event.ActionEvent;
/*   5:    */ import java.awt.event.ActionListener;
/*   6:    */ import javax.swing.ImageIcon;
/*   7:    */ import javax.swing.JButton;
/*   8:    */ import org.cadbox.Application;
/*   9:    */ import org.cadbox.Document;
/*  10:    */ import org.cadbox.Observer;
/*  11:    */ import org.cadbox.j2d.CadApplication;
/*  12:    */ import org.cadbox.j2d.CadDocument;
/*  13:    */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  14:    */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/*  15:    */ import org.cadbox.plaf.ToolBar;
/*  16:    */ 
/*  17:    */ public class DrawToolBar
/*  18:    */   extends ToolBar
/*  19:    */   implements Observer
/*  20:    */ {
/*  21: 48 */   private Application application = null;
/*  22: 49 */   private CadApplication form = null;
/*  23:    */   private JButton lineButton;
/*  24:    */   private JButton segmentButton;
/*  25:    */   private JButton polylineButton;
/*  26:    */   private JButton circleButton;
/*  27:    */   
/*  28:    */   public DrawToolBar(Application application, CadApplication form)
/*  29:    */   {
/*  30: 54 */     this.application = application;
/*  31: 55 */     this.form = form;
/*  32:    */     
/*  33: 57 */     initComponents();
/*  34:    */   }
/*  35:    */   
/*  36:    */   private void initComponents()
/*  37:    */   {
/*  38: 62 */     setName("Draw");
/*  39: 63 */     setRollover(true);
/*  40:    */     
/*  41: 65 */     this.lineButton = new JButton();
/*  42: 66 */     this.segmentButton = new JButton();
/*  43: 67 */     this.polylineButton = new JButton();
/*  44: 68 */     this.circleButton = new JButton();
/*  45:    */     
/*  46:    */ 
/*  47: 71 */     this.lineButton.setName("polygoneButton");
/*  48: 72 */     this.lineButton.setFocusPainted(false);
/*  49: 73 */     this.lineButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar1/line.png")));
/*  50: 74 */     this.lineButton.setMaximumSize(new Dimension(25, 25));
/*  51: 75 */     this.lineButton.setMinimumSize(new Dimension(25, 25));
/*  52: 76 */     this.lineButton.setPreferredSize(new Dimension(25, 25));
/*  53: 77 */     this.lineButton.addActionListener(new ActionListener()
/*  54:    */     {
/*  55:    */       public void actionPerformed(ActionEvent evt)
/*  56:    */       {
/*  57: 79 */         DrawToolBar.this.lineButtonActionPerformed(evt);
/*  58:    */       }
/*  59: 81 */     });
/*  60: 82 */     add(this.lineButton);
/*  61:    */     
/*  62:    */ 
/*  63: 85 */     this.segmentButton.setName("segmentButton");
/*  64: 86 */     this.segmentButton.setFocusPainted(false);
/*  65: 87 */     this.segmentButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar1/segment.png")));
/*  66: 88 */     this.segmentButton.setMaximumSize(new Dimension(25, 25));
/*  67: 89 */     this.segmentButton.setMinimumSize(new Dimension(25, 25));
/*  68: 90 */     this.segmentButton.setPreferredSize(new Dimension(25, 25));
/*  69: 91 */     this.segmentButton.addActionListener(new ActionListener()
/*  70:    */     {
/*  71:    */       public void actionPerformed(ActionEvent evt)
/*  72:    */       {
/*  73: 93 */         DrawToolBar.this.segmentButtonActionPerformed(evt);
/*  74:    */       }
/*  75: 95 */     });
/*  76: 96 */     add(this.segmentButton);
/*  77:    */     
/*  78:    */ 
/*  79: 99 */     this.polylineButton.setName("polylineButton");
/*  80:100 */     this.polylineButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar1/polyline.png")));
/*  81:101 */     this.polylineButton.setMaximumSize(new Dimension(25, 25));
/*  82:102 */     this.polylineButton.setMinimumSize(new Dimension(25, 25));
/*  83:103 */     this.polylineButton.setPreferredSize(new Dimension(25, 25));
/*  84:104 */     this.polylineButton.setFocusPainted(false);
/*  85:105 */     this.polylineButton.addActionListener(new ActionListener()
/*  86:    */     {
/*  87:    */       public void actionPerformed(ActionEvent evt)
/*  88:    */       {
/*  89:107 */         DrawToolBar.this.polylineButtonActionPerformed(evt);
/*  90:    */       }
/*  91:109 */     });
/*  92:110 */     add(this.polylineButton);
/*  93:    */     
/*  94:    */ 
/*  95:113 */     this.circleButton.setName("circleButton");
/*  96:114 */     this.circleButton.setToolTipText("Circle");
/*  97:115 */     this.circleButton.setFocusPainted(false);
/*  98:116 */     this.circleButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar1/circle.png")));
/*  99:117 */     this.circleButton.setMaximumSize(new Dimension(25, 25));
/* 100:118 */     this.circleButton.setMinimumSize(new Dimension(25, 25));
/* 101:119 */     this.circleButton.setPreferredSize(new Dimension(25, 25));
/* 102:120 */     this.circleButton.addActionListener(new ActionListener()
/* 103:    */     {
/* 104:    */       public void actionPerformed(ActionEvent evt)
/* 105:    */       {
/* 106:122 */         DrawToolBar.this.circleButtonActionPerformed(evt);
/* 107:    */       }
/* 108:124 */     });
/* 109:125 */     add(this.circleButton);
/* 110:    */   }
/* 111:    */   
/* 112:    */   private void setShapeType(int type)
/* 113:    */   {
/* 114:129 */     Document document = this.application.getCurrentDocument();
/* 115:130 */     if ((document != null) && ((document instanceof CadDocument)))
/* 116:    */     {
/* 117:131 */       CadDocumentWindow panel = this.form.getCadDocumentWindow(document);
/* 118:132 */       if (panel != null)
/* 119:    */       {
/* 120:133 */         CadDrawingEngine de = panel.getDrawingEngine();
/* 121:134 */         de.setMode(3);
/* 122:135 */         de.setShapeType(type);
/* 123:    */       }
/* 124:    */     }
/* 125:    */   }
/* 126:    */   
/* 127:    */   private void lineButtonActionPerformed(ActionEvent evt)
/* 128:    */   {
/* 129:141 */     setShapeType(5);
/* 130:    */   }
/* 131:    */   
/* 132:    */   private void segmentButtonActionPerformed(ActionEvent evt)
/* 133:    */   {
/* 134:145 */     setShapeType(4);
/* 135:    */   }
/* 136:    */   
/* 137:    */   private void polygoneButtonActionPerformed(ActionEvent evt)
/* 138:    */   {
/* 139:149 */     setShapeType(2);
/* 140:    */   }
/* 141:    */   
/* 142:    */   private void polylineButtonActionPerformed(ActionEvent evt)
/* 143:    */   {
/* 144:153 */     setShapeType(1);
/* 145:    */   }
/* 146:    */   
/* 147:    */   private void circleButtonActionPerformed(ActionEvent evt)
/* 148:    */   {
/* 149:157 */     setShapeType(3);
/* 150:    */   }
/* 151:    */   
/* 152:    */   private void setEnableButtons(boolean enabled)
/* 153:    */   {
/* 154:161 */     this.lineButton.setEnabled(enabled);
/* 155:162 */     this.segmentButton.setEnabled(enabled);
/* 156:163 */     this.polylineButton.setEnabled(enabled);
/* 157:164 */     this.circleButton.setEnabled(enabled);
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void update()
/* 161:    */   {
/* 162:168 */     if (this.application.getDocumentCount() > 0) {
/* 163:169 */       setEnableButtons(true);
/* 164:    */     } else {
/* 165:171 */       setEnableButtons(false);
/* 166:    */     }
/* 167:    */   }
/* 168:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.DrawToolBar
 * JD-Core Version:    0.7.0.1
 */