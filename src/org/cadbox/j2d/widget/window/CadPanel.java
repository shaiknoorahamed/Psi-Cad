/*   1:    */ package org.cadbox.j2d.widget.window;
/*   2:    */ 
/*   3:    */ import java.awt.BorderLayout;
/*   4:    */ import java.awt.Graphics;
/*   5:    */ import java.awt.Graphics2D;
/*   6:    */ import java.awt.event.ComponentAdapter;
/*   7:    */ import java.awt.event.ComponentEvent;
/*   8:    */ import java.awt.event.KeyAdapter;
/*   9:    */ import java.awt.event.KeyEvent;
/*  10:    */ import java.awt.event.MouseAdapter;
/*  11:    */ import java.awt.event.MouseEvent;
/*  12:    */ import java.awt.event.MouseMotionAdapter;
/*  13:    */ import java.awt.event.MouseWheelEvent;
/*  14:    */ import java.awt.event.MouseWheelListener;
/*  15:    */ import javax.swing.JOptionPane;
/*  16:    */ import javax.swing.JPanel;
/*  17:    */ import org.cadbox.j2d.CadDocument;
/*  18:    */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  19:    */ import org.cadbox.j2d.scale.core.BasicDocument;
/*  20:    */ import org.cadbox.j2d.scale.core.Context;
/*  21:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*  22:    */ import org.cadbox.j2d.scale.core.Layout;
/*  23:    */ 
/*  24:    */ public class CadPanel
/*  25:    */   extends JPanel
/*  26:    */ {
/*  27: 48 */   CadDocument document = null;
/*  28: 52 */   CadDrawingEngine drawingEngine = null;
/*  29: 53 */   DrawingContext drawingContext = null;
/*  30:    */   
/*  31:    */   public CadPanel(CadDocument document, CadDrawingEngine drawingEngine)
/*  32:    */   {
/*  33: 58 */     this.document = document;
/*  34:    */     
/*  35:    */ 
/*  36: 61 */     this.drawingContext = document.getContext().getDrawingContext();
/*  37: 66 */     if (drawingEngine == null) {
/*  38: 67 */       throw new IllegalArgumentException("DrawingEngine could not be null.");
/*  39:    */     }
/*  40: 69 */     this.drawingEngine = drawingEngine;
/*  41:    */     
/*  42:    */ 
/*  43:    */ 
/*  44: 73 */     initComponents();
/*  45: 74 */     setFocusable(true);
/*  46: 75 */     setSize(1, 1);
/*  47:    */     
/*  48:    */ 
/*  49: 78 */     this.drawingContext.setBounds(0, 0, getWidth(), getHeight());
/*  50:    */     
/*  51: 80 */     this.drawingContext.setLogicalBounds(document.getLayout().getBounds());
/*  52:    */   }
/*  53:    */   
/*  54:    */   public BasicDocument getDocument()
/*  55:    */   {
/*  56: 98 */     return this.document;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setDrawingEngine(CadDrawingEngine drawingEngine)
/*  60:    */   {
/*  61:102 */     this.drawingEngine = drawingEngine;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public CadDrawingEngine getDrawingEngine()
/*  65:    */   {
/*  66:106 */     return this.drawingEngine;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public DrawingContext getDrawingContext()
/*  70:    */   {
/*  71:110 */     return this.drawingContext;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void paint(Graphics g)
/*  75:    */   {
/*  76:115 */     Graphics2D g2 = (Graphics2D)g;
/*  77:116 */     g2.drawImage(this.drawingEngine.getImage(), 0, 0, this);
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void refresh()
/*  81:    */   {
/*  82:120 */     this.drawingContext.setChanged(true);
/*  83:121 */     paint();
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void paint()
/*  87:    */   {
/*  88:126 */     paint(getGraphics());
/*  89:    */   }
/*  90:    */   
/*  91:    */   private void initComponents()
/*  92:    */   {
/*  93:139 */     addComponentListener(new ComponentAdapter()
/*  94:    */     {
/*  95:    */       public void componentResized(ComponentEvent evt)
/*  96:    */       {
/*  97:141 */         CadPanel.this.formComponentResized(evt);
/*  98:    */       }
/*  99:143 */     });
/* 100:144 */     addMouseMotionListener(new MouseMotionAdapter()
/* 101:    */     {
/* 102:    */       public void mouseDragged(MouseEvent evt)
/* 103:    */       {
/* 104:146 */         CadPanel.this.formMouseDragged(evt);
/* 105:    */       }
/* 106:    */       
/* 107:    */       public void mouseMoved(MouseEvent evt)
/* 108:    */       {
/* 109:149 */         CadPanel.this.formMouseMoved(evt);
/* 110:    */       }
/* 111:151 */     });
/* 112:152 */     addKeyListener(new KeyAdapter()
/* 113:    */     {
/* 114:    */       public void keyPressed(KeyEvent evt)
/* 115:    */       {
/* 116:154 */         CadPanel.this.formKeyPressed(evt);
/* 117:    */       }
/* 118:156 */     });
/* 119:157 */     addMouseListener(new MouseAdapter()
/* 120:    */     {
/* 121:    */       public void mouseClicked(MouseEvent evt)
/* 122:    */       {
/* 123:159 */         CadPanel.this.formMouseClicked(evt);
/* 124:    */       }
/* 125:    */       
/* 126:    */       public void mousePressed(MouseEvent evt)
/* 127:    */       {
/* 128:162 */         CadPanel.this.formMousePressed(evt);
/* 129:    */       }
/* 130:    */       
/* 131:    */       public void mouseReleased(MouseEvent evt)
/* 132:    */       {
/* 133:165 */         CadPanel.this.formMouseReleased(evt);
/* 134:    */       }
/* 135:167 */     });
/* 136:168 */     addMouseWheelListener(new MouseWheelListener()
/* 137:    */     {
/* 138:    */       public void mouseWheelMoved(MouseWheelEvent evt)
/* 139:    */       {
/* 140:170 */         CadPanel.this.formMouseWheelMoved(evt);
/* 141:    */       }
/* 142:172 */     });
/* 143:173 */     setLayout(new BorderLayout());
/* 144:    */   }
/* 145:    */   
/* 146:    */   private void formMousePressed(MouseEvent evt)
/* 147:    */   {
/* 148:178 */     requestFocusInWindow();
/* 149:179 */     this.drawingEngine.mousePressed(evt);
/* 150:180 */     paint();
/* 151:    */   }
/* 152:    */   
/* 153:    */   private void formMouseReleased(MouseEvent evt) {}
/* 154:    */   
/* 155:    */   private void formMouseDragged(MouseEvent evt)
/* 156:    */   {
/* 157:188 */     this.drawingEngine.mouseDragged(evt);
/* 158:189 */     paint();
/* 159:    */   }
/* 160:    */   
/* 161:    */   private void formMouseMoved(MouseEvent evt)
/* 162:    */   {
/* 163:193 */     this.drawingEngine.mouseMoved(evt);
/* 164:194 */     paint();
/* 165:    */   }
/* 166:    */   
/* 167:    */   private void formMouseWheelMoved(MouseWheelEvent evt)
/* 168:    */   {
/* 169:198 */     this.drawingEngine.mouseWheelMoved(evt);
/* 170:199 */     paint();
/* 171:    */   }
/* 172:    */   
/* 173:    */   private void formComponentResized(ComponentEvent evt)
/* 174:    */   {
/* 175:    */     try
/* 176:    */     {
/* 177:204 */       this.drawingContext.setBounds(0, 0, getWidth(), getHeight());
/* 178:205 */       paint();
/* 179:    */     }
/* 180:    */     catch (VirtualMachineError err)
/* 181:    */     {
/* 182:207 */       JOptionPane.showMessageDialog(null, "Couldn't create document. Out of memory!!!");
/* 183:    */     }
/* 184:    */   }
/* 185:    */   
/* 186:    */   private void formKeyPressed(KeyEvent evt)
/* 187:    */   {
/* 188:213 */     this.drawingEngine.keyPressed(evt);
/* 189:214 */     paint();
/* 190:    */   }
/* 191:    */   
/* 192:    */   private void formMouseClicked(MouseEvent evt) {}
/* 193:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.window.CadPanel
 * JD-Core Version:    0.7.0.1
 */