/*   1:    */ package org.cadbox.j2d.widget.toolbar;
/*   2:    */ 
/*   3:    */ import java.awt.Dimension;
/*   4:    */ import java.awt.event.ActionEvent;
/*   5:    */ import java.awt.event.ActionListener;
/*   6:    */ import java.awt.event.ItemEvent;
/*   7:    */ import java.awt.event.ItemListener;
/*   8:    */ import javax.swing.ImageIcon;
/*   9:    */ import javax.swing.JButton;
/*  10:    */ import javax.swing.JComboBox;
/*  11:    */ import org.cadbox.Application;
/*  12:    */ import org.cadbox.Document;
/*  13:    */ import org.cadbox.Observer;
/*  14:    */ import org.cadbox.j2d.CadApplication;
/*  15:    */ import org.cadbox.j2d.CadDocument;
/*  16:    */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  17:    */ import org.cadbox.j2d.scale.core.Context;
/*  18:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*  19:    */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/*  20:    */ import org.cadbox.plaf.ToolBar;
/*  21:    */ 
/*  22:    */ public class ModeToolBar
/*  23:    */   extends ToolBar
/*  24:    */   implements Observer
/*  25:    */ {
/*  26:    */   JButton moveButton;
/*  27:    */   JButton selectButton;
/*  28:    */   JButton zoominButton;
/*  29:    */   JButton zoomoutButton;
/*  30:    */   JComboBox scaleComboBox;
/*  31: 56 */   CadApplication form = null;
/*  32: 57 */   Application application = null;
/*  33:    */   
/*  34:    */   public ModeToolBar(CadApplication mainFrame, Application application)
/*  35:    */   {
/*  36: 61 */     this.form = mainFrame;
/*  37: 62 */     this.application = application;
/*  38:    */     
/*  39: 64 */     initComponents();
/*  40:    */   }
/*  41:    */   
/*  42:    */   private void initComponents()
/*  43:    */   {
/*  44: 68 */     setName("Modes");
/*  45: 69 */     setRollover(true);
/*  46:    */     
/*  47: 71 */     this.moveButton = new JButton();
/*  48: 72 */     this.selectButton = new JButton();
/*  49: 73 */     this.zoominButton = new JButton();
/*  50: 74 */     this.zoomoutButton = new JButton();
/*  51: 75 */     this.scaleComboBox = new JComboBox();
/*  52:    */     
/*  53: 77 */     this.moveButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/move.png")));
/*  54: 78 */     this.moveButton.setFocusable(false);
/*  55: 79 */     this.moveButton.setHorizontalTextPosition(0);
/*  56: 80 */     this.moveButton.setMaximumSize(new Dimension(25, 25));
/*  57: 81 */     this.moveButton.setMinimumSize(new Dimension(25, 25));
/*  58: 82 */     this.moveButton.setPreferredSize(new Dimension(25, 25));
/*  59: 83 */     this.moveButton.setVerticalTextPosition(3);
/*  60: 84 */     this.moveButton.addActionListener(new ActionListener()
/*  61:    */     {
/*  62:    */       public void actionPerformed(ActionEvent evt)
/*  63:    */       {
/*  64: 86 */         ModeToolBar.this.setMode(2);
/*  65:    */       }
/*  66: 88 */     });
/*  67: 89 */     add(this.moveButton);
/*  68:    */     
/*  69: 91 */     this.selectButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/select.png")));
/*  70: 92 */     this.selectButton.setFocusable(false);
/*  71: 93 */     this.selectButton.setHorizontalTextPosition(0);
/*  72: 94 */     this.selectButton.setMaximumSize(new Dimension(25, 25));
/*  73: 95 */     this.selectButton.setMinimumSize(new Dimension(25, 25));
/*  74: 96 */     this.selectButton.setPreferredSize(new Dimension(25, 25));
/*  75: 97 */     this.selectButton.setVerticalTextPosition(3);
/*  76: 98 */     this.selectButton.addActionListener(new ActionListener()
/*  77:    */     {
/*  78:    */       public void actionPerformed(ActionEvent evt)
/*  79:    */       {
/*  80:100 */         ModeToolBar.this.setMode(1);
/*  81:    */       }
/*  82:102 */     });
/*  83:103 */     add(this.selectButton);
/*  84:    */     
/*  85:105 */     addSeparator();
/*  86:    */     
/*  87:107 */     this.zoominButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/zoomin.png")));
/*  88:108 */     this.zoominButton.setFocusable(false);
/*  89:109 */     this.zoominButton.setHorizontalTextPosition(0);
/*  90:110 */     this.zoominButton.setMaximumSize(new Dimension(25, 25));
/*  91:111 */     this.zoominButton.setMinimumSize(new Dimension(25, 25));
/*  92:112 */     this.zoominButton.setPreferredSize(new Dimension(25, 25));
/*  93:113 */     this.zoominButton.setVerticalTextPosition(3);
/*  94:114 */     this.zoominButton.addActionListener(new ActionListener()
/*  95:    */     {
/*  96:    */       public void actionPerformed(ActionEvent evt)
/*  97:    */       {
/*  98:116 */         ModeToolBar.this.setMode(4);
/*  99:    */       }
/* 100:118 */     });
/* 101:119 */     add(this.zoominButton);
/* 102:    */     
/* 103:121 */     this.zoomoutButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/zoomout.png")));
/* 104:122 */     this.zoomoutButton.setFocusable(false);
/* 105:123 */     this.zoomoutButton.setHorizontalTextPosition(0);
/* 106:124 */     this.zoomoutButton.setMaximumSize(new Dimension(25, 25));
/* 107:125 */     this.zoomoutButton.setMinimumSize(new Dimension(25, 25));
/* 108:126 */     this.zoomoutButton.setPreferredSize(new Dimension(25, 25));
/* 109:127 */     this.zoomoutButton.setVerticalTextPosition(3);
/* 110:128 */     this.zoomoutButton.addActionListener(new ActionListener()
/* 111:    */     {
/* 112:    */       public void actionPerformed(ActionEvent evt)
/* 113:    */       {
/* 114:130 */         ModeToolBar.this.setMode(5);
/* 115:    */       }
/* 116:132 */     });
/* 117:133 */     add(this.zoomoutButton);
/* 118:    */     
/* 119:    */ 
/* 120:136 */     this.scaleComboBox.setEditable(true);
/* 121:137 */     this.scaleComboBox.setMaximumSize(new Dimension(100, 25));
/* 122:138 */     this.scaleComboBox.setPreferredSize(new Dimension(100, 25));
/* 123:139 */     this.scaleComboBox.setMinimumSize(new Dimension(100, 25));
/* 124:140 */     this.scaleComboBox.addItemListener(this.scaleComboBoxItemListener);
/* 125:141 */     this.scaleComboBox.addItem(Double.valueOf(0.5D));
/* 126:142 */     this.scaleComboBox.addItem(Double.valueOf(1.0D));
/* 127:143 */     this.scaleComboBox.addItem(Double.valueOf(2.0D));
/* 128:144 */     this.scaleComboBox.addItem(Double.valueOf(3.0D));
/* 129:145 */     this.scaleComboBox.addItem(Double.valueOf(4.0D));
/* 130:146 */     this.scaleComboBox.setSelectedIndex(1);
/* 131:147 */     add(this.scaleComboBox);
/* 132:    */   }
/* 133:    */   
/* 134:    */   private void setScale(double value)
/* 135:    */   {
/* 136:151 */     Document doc = this.application.getCurrentDocument();
/* 137:152 */     if ((doc != null) && 
/* 138:153 */       ((doc instanceof CadDocument)))
/* 139:    */     {
/* 140:154 */       CadDocument cadDoc = (CadDocument)doc;
/* 141:155 */       cadDoc.getContext().getDrawingContext().setScale(value);
/* 142:    */     }
/* 143:    */   }
/* 144:    */   
/* 145:    */   private void setMode(int mode)
/* 146:    */   {
/* 147:161 */     Document doc = this.application.getCurrentDocument();
/* 148:162 */     if ((doc != null) && 
/* 149:163 */       ((doc instanceof CadDocument)))
/* 150:    */     {
/* 151:164 */       CadDocumentWindow panel = this.form.getCadDocumentWindow(doc);
/* 152:165 */       CadDrawingEngine engine = panel.getDrawingEngine();
/* 153:166 */       engine.setMode(mode);
/* 154:    */     }
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void showScaleValue(double value)
/* 158:    */   {
/* 159:173 */     this.scaleComboBox.removeItemListener(this.scaleComboBoxItemListener);
/* 160:    */     
/* 161:175 */     this.scaleComboBox.removeItemAt(0);
/* 162:    */     
/* 163:177 */     value = (int)(value * 1000.0D) / 1000.0D;
/* 164:    */     
/* 165:179 */     this.scaleComboBox.insertItemAt(Double.valueOf(value), 0);
/* 166:180 */     this.scaleComboBox.setSelectedIndex(0);
/* 167:    */     
/* 168:182 */     this.scaleComboBox.addItemListener(this.scaleComboBoxItemListener);
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void setEnabled(boolean state)
/* 172:    */   {
/* 173:186 */     this.moveButton.setEnabled(state);
/* 174:187 */     this.selectButton.setEnabled(state);
/* 175:188 */     this.zoominButton.setEnabled(state);
/* 176:189 */     this.zoomoutButton.setEnabled(state);
/* 177:190 */     this.scaleComboBox.setEnabled(state);
/* 178:    */   }
/* 179:    */   
/* 180:    */   public void update()
/* 181:    */   {
/* 182:194 */     Document doc = this.application.getCurrentDocument();
/* 183:195 */     if ((doc instanceof CadDocument)) {
/* 184:196 */       setEnabled(true);
/* 185:    */     } else {
/* 186:198 */       setEnabled(false);
/* 187:    */     }
/* 188:    */   }
/* 189:    */   
/* 190:202 */   ItemListener scaleComboBoxItemListener = new ItemListener()
/* 191:    */   {
/* 192:    */     public void itemStateChanged(ItemEvent e)
/* 193:    */     {
/* 194:204 */       if (e.getStateChange() == 1)
/* 195:    */       {
/* 196:205 */         Double value = (Double)ModeToolBar.this.scaleComboBox.getSelectedItem();
/* 197:206 */         ModeToolBar.this.setScale(value.doubleValue());
/* 198:207 */         ModeToolBar.this.application.updateAllObservers();
/* 199:    */       }
/* 200:    */     }
/* 201:    */   };
/* 202:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.ModeToolBar
 * JD-Core Version:    0.7.0.1
 */