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
/*  17:    */ public class ModifyToolBar
/*  18:    */   extends ToolBar
/*  19:    */   implements Observer
/*  20:    */ {
/*  21: 48 */   private Application application = null;
/*  22: 49 */   private CadApplication form = null;
/*  23: 50 */   private CadDocument document = null;
/*  24:    */   private JButton copyButton;
/*  25:    */   private JButton deleteButton;
/*  26:    */   private JButton moveButton;
/*  27:    */   private JButton rotateButton;
/*  28:    */   
/*  29:    */   public ModifyToolBar(Application application, CadApplication form)
/*  30:    */   {
/*  31: 54 */     this.application = application;
/*  32: 55 */     this.form = form;
/*  33: 56 */     initComponents();
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void setDocument(CadDocument document)
/*  37:    */   {
/*  38: 60 */     this.document = document;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public CadDocument getDocument()
/*  42:    */   {
/*  43: 64 */     return this.document;
/*  44:    */   }
/*  45:    */   
/*  46:    */   private void initComponents()
/*  47:    */   {
/*  48: 69 */     setName("Modify");
/*  49: 70 */     setRollover(true);
/*  50:    */     
/*  51: 72 */     this.deleteButton = new JButton();
/*  52: 73 */     this.copyButton = new JButton();
/*  53: 74 */     this.moveButton = new JButton();
/*  54: 75 */     this.rotateButton = new JButton();
/*  55:    */     
/*  56:    */ 
/*  57: 78 */     this.deleteButton.setName("deleteButton");
/*  58: 79 */     this.deleteButton.setToolTipText("Erase object");
/*  59: 80 */     this.deleteButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar2/erase.png")));
/*  60: 81 */     this.deleteButton.setMaximumSize(new Dimension(25, 25));
/*  61: 82 */     this.deleteButton.setMinimumSize(new Dimension(25, 25));
/*  62: 83 */     this.deleteButton.setPreferredSize(new Dimension(25, 25));
/*  63: 84 */     this.deleteButton.setFocusPainted(false);
/*  64: 85 */     this.deleteButton.addActionListener(new ActionListener()
/*  65:    */     {
/*  66:    */       public void actionPerformed(ActionEvent evt)
/*  67:    */       {
/*  68: 87 */         ModifyToolBar.this.deleteButtonActionPerformed(evt);
/*  69:    */       }
/*  70: 89 */     });
/*  71: 90 */     add(this.deleteButton);
/*  72:    */     
/*  73:    */ 
/*  74: 93 */     this.copyButton.setName("copyButton");
/*  75: 94 */     this.copyButton.setToolTipText("Copy object");
/*  76: 95 */     this.copyButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar2/copy.png")));
/*  77: 96 */     this.copyButton.setFocusPainted(false);
/*  78: 97 */     this.copyButton.setMaximumSize(new Dimension(25, 25));
/*  79: 98 */     this.copyButton.setMinimumSize(new Dimension(25, 25));
/*  80: 99 */     this.copyButton.setPreferredSize(new Dimension(25, 25));
/*  81:100 */     this.copyButton.addActionListener(new ActionListener()
/*  82:    */     {
/*  83:    */       public void actionPerformed(ActionEvent evt)
/*  84:    */       {
/*  85:102 */         ModifyToolBar.this.copyButtonActionPerformed(evt);
/*  86:    */       }
/*  87:104 */     });
/*  88:105 */     add(this.copyButton);
/*  89:    */     
/*  90:    */ 
/*  91:108 */     this.moveButton.setName("moveButton");
/*  92:109 */     this.moveButton.setToolTipText("Move object");
/*  93:110 */     this.moveButton.setFocusPainted(false);
/*  94:111 */     this.moveButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar2/move.png")));
/*  95:112 */     this.moveButton.setMaximumSize(new Dimension(25, 25));
/*  96:113 */     this.moveButton.setMinimumSize(new Dimension(25, 25));
/*  97:114 */     this.moveButton.setPreferredSize(new Dimension(25, 25));
/*  98:115 */     this.moveButton.addActionListener(new ActionListener()
/*  99:    */     {
/* 100:    */       public void actionPerformed(ActionEvent evt)
/* 101:    */       {
/* 102:117 */         ModifyToolBar.this.moveButtonActionPerformed(evt);
/* 103:    */       }
/* 104:119 */     });
/* 105:120 */     add(this.moveButton);
/* 106:    */     
/* 107:    */ 
/* 108:123 */     this.rotateButton.setName("rotateButton");
/* 109:124 */     this.rotateButton.setToolTipText("Rotate object");
/* 110:125 */     this.rotateButton.setFocusPainted(false);
/* 111:126 */     this.rotateButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/toolbar2/rotate.png")));
/* 112:127 */     this.rotateButton.setMaximumSize(new Dimension(25, 25));
/* 113:128 */     this.rotateButton.setMinimumSize(new Dimension(25, 25));
/* 114:129 */     this.rotateButton.setPreferredSize(new Dimension(25, 25));
/* 115:130 */     this.rotateButton.addActionListener(new ActionListener()
/* 116:    */     {
/* 117:    */       public void actionPerformed(ActionEvent evt)
/* 118:    */       {
/* 119:132 */         ModifyToolBar.this.rotateButtonActionPerformed(evt);
/* 120:    */       }
/* 121:134 */     });
/* 122:135 */     add(this.rotateButton);
/* 123:    */   }
/* 124:    */   
/* 125:    */   private void deleteButtonActionPerformed(ActionEvent evt)
/* 126:    */   {
/* 127:141 */     Document document = this.application.getCurrentDocument();
/* 128:142 */     if ((document != null) && ((document instanceof CadDocument)))
/* 129:    */     {
/* 130:143 */       CadDocumentWindow panel = this.form.getCadDocumentWindow(document);
/* 131:144 */       if (panel != null)
/* 132:    */       {
/* 133:145 */         CadDrawingEngine de = panel.getDrawingEngine();
/* 134:146 */         de.deleteSelectedShape();
/* 135:147 */         this.application.updateAllObservers();
/* 136:    */       }
/* 137:    */     }
/* 138:    */   }
/* 139:    */   
/* 140:    */   private void copyButtonActionPerformed(ActionEvent evt)
/* 141:    */   {
/* 142:153 */     Document document = this.application.getCurrentDocument();
/* 143:154 */     if ((document != null) && ((document instanceof CadDocument)))
/* 144:    */     {
/* 145:155 */       CadDocumentWindow panel = this.form.getCadDocumentWindow(document);
/* 146:156 */       if (panel != null)
/* 147:    */       {
/* 148:157 */         CadDrawingEngine de = panel.getDrawingEngine();
/* 149:158 */         de.setAction(3);
/* 150:159 */         this.application.updateAllObservers();
/* 151:    */       }
/* 152:    */     }
/* 153:    */   }
/* 154:    */   
/* 155:    */   private void moveButtonActionPerformed(ActionEvent evt)
/* 156:    */   {
/* 157:165 */     Document document = this.application.getCurrentDocument();
/* 158:166 */     if ((document != null) && ((document instanceof CadDocument)))
/* 159:    */     {
/* 160:167 */       CadDocumentWindow panel = this.form.getCadDocumentWindow(document);
/* 161:168 */       if (panel != null)
/* 162:    */       {
/* 163:169 */         CadDrawingEngine de = panel.getDrawingEngine();
/* 164:170 */         de.setAction(2);
/* 165:171 */         this.application.updateAllObservers();
/* 166:    */       }
/* 167:    */     }
/* 168:    */   }
/* 169:    */   
/* 170:    */   private void rotateButtonActionPerformed(ActionEvent evt)
/* 171:    */   {
/* 172:177 */     Document document = this.application.getCurrentDocument();
/* 173:178 */     if ((document != null) && ((document instanceof CadDocument)))
/* 174:    */     {
/* 175:179 */       CadDocumentWindow panel = this.form.getCadDocumentWindow(document);
/* 176:180 */       if (panel != null)
/* 177:    */       {
/* 178:181 */         CadDrawingEngine de = panel.getDrawingEngine();
/* 179:182 */         de.setAction(4);
/* 180:183 */         this.application.updateAllObservers();
/* 181:    */       }
/* 182:    */     }
/* 183:    */   }
/* 184:    */   
/* 185:    */   private void setEnableButtons(boolean enabled)
/* 186:    */   {
/* 187:189 */     this.copyButton.setEnabled(enabled);
/* 188:190 */     this.deleteButton.setEnabled(enabled);
/* 189:191 */     this.moveButton.setEnabled(enabled);
/* 190:192 */     this.rotateButton.setEnabled(enabled);
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void update()
/* 194:    */   {
/* 195:196 */     if (this.application.getDocumentCount() > 0) {
/* 196:197 */       setEnableButtons(true);
/* 197:    */     } else {
/* 198:199 */       setEnableButtons(false);
/* 199:    */     }
/* 200:    */   }
/* 201:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.ModifyToolBar
 * JD-Core Version:    0.7.0.1
 */