/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.awt.BorderLayout;
/*   4:    */ import java.awt.Color;
/*   5:    */ import java.awt.Container;
/*   6:    */ import java.awt.Dimension;
/*   7:    */ import java.awt.EventQueue;
/*   8:    */ import java.awt.Graphics;
/*   9:    */ import java.awt.Graphics2D;
/*  10:    */ import java.awt.Rectangle;
/*  11:    */ import java.awt.event.ActionEvent;
/*  12:    */ import java.awt.event.ActionListener;
/*  13:    */ import java.awt.event.ComponentAdapter;
/*  14:    */ import java.awt.event.ComponentEvent;
/*  15:    */ import java.awt.event.MouseAdapter;
/*  16:    */ import java.awt.event.MouseEvent;
/*  17:    */ import java.awt.event.MouseMotionAdapter;
/*  18:    */ import java.awt.event.MouseWheelEvent;
/*  19:    */ import java.awt.event.MouseWheelListener;
/*  20:    */ import java.beans.PropertyChangeSupport;
/*  21:    */ import javax.swing.JButton;
/*  22:    */ import javax.swing.JFrame;
/*  23:    */ import javax.swing.JOptionPane;
/*  24:    */ import javax.swing.JPanel;
/*  25:    */ import javax.swing.JToolBar;
/*  26:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  27:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*  28:    */ 
/*  29:    */ public class TestFrame
/*  30:    */   extends JFrame
/*  31:    */ {
/*  32:    */   SimpleDocument document;
/*  33:    */   Layout layout;
/*  34:    */   SimpleDrawingEngine drawingEngine;
/*  35:    */   PropertyChangeSupport propertyChangeSupport;
/*  36:    */   ScalePanel scalePanel;
/*  37:    */   private JToolBar jToolBar1;
/*  38:    */   private JButton test1Button;
/*  39:    */   
/*  40:    */   public TestFrame()
/*  41:    */   {
/*  42: 54 */     initComponents();
/*  43:    */     
/*  44: 56 */     setExtendedState(6);
/*  45:    */     
/*  46: 58 */     this.layout = LayoutManager.getInstance().getDefaultLayout();
/*  47:    */     
/*  48: 60 */     this.document = new SimpleDocument(this.layout);
/*  49: 61 */     Layer layer = this.document.getCurrentLayer();
/*  50: 62 */     layer.setColor(Color.black);
/*  51: 64 */     for (int i = 0; i < 100; i++)
/*  52:    */     {
/*  53: 66 */       double x = Math.random() * 10000.0D;
/*  54: 67 */       double y = Math.random() * 10000.0D;
/*  55:    */       
/*  56: 69 */       double x1 = Math.random() * 10000.0D;
/*  57: 70 */       double y1 = Math.random() * 10000.0D;
/*  58:    */       
/*  59: 72 */       double r = Math.random() * 100.0D;
/*  60:    */       
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66: 79 */       Shape shape = new Text("123", x, y, 30.0D);
/*  67:    */       
/*  68: 81 */       layer.addShape(shape);
/*  69:    */     }
/*  70: 87 */     this.propertyChangeSupport = new PropertyChangeSupport(this);
/*  71:    */     
/*  72: 89 */     this.drawingEngine = new SimpleDrawingEngine(this.document, this.propertyChangeSupport);
/*  73:    */     
/*  74: 91 */     this.scalePanel = new ScalePanel(this.document, this.drawingEngine);
/*  75:    */     
/*  76: 93 */     add(this.scalePanel, "Center");
/*  77:    */   }
/*  78:    */   
/*  79:    */   private void initComponents()
/*  80:    */   {
/*  81:103 */     this.jToolBar1 = new JToolBar();
/*  82:104 */     this.test1Button = new JButton();
/*  83:    */     
/*  84:106 */     setDefaultCloseOperation(3);
/*  85:107 */     this.jToolBar1.setRollover(true);
/*  86:108 */     this.test1Button.setText("Test");
/*  87:109 */     this.test1Button.setMaximumSize(new Dimension(41, 25));
/*  88:110 */     this.test1Button.setMinimumSize(new Dimension(41, 25));
/*  89:111 */     this.test1Button.setPreferredSize(new Dimension(41, 25));
/*  90:112 */     this.test1Button.addActionListener(new ActionListener()
/*  91:    */     {
/*  92:    */       public void actionPerformed(ActionEvent evt)
/*  93:    */       {
/*  94:114 */         TestFrame.this.test1ButtonActionPerformed(evt);
/*  95:    */       }
/*  96:117 */     });
/*  97:118 */     this.jToolBar1.add(this.test1Button);
/*  98:    */     
/*  99:120 */     getContentPane().add(this.jToolBar1, "North");
/* 100:    */     
/* 101:122 */     pack();
/* 102:    */   }
/* 103:    */   
/* 104:    */   private void test1ButtonActionPerformed(ActionEvent evt)
/* 105:    */   {
/* 106:126 */     DrawingContext drawingContext = this.document.getContext().getDrawingContext();
/* 107:    */     
/* 108:128 */     Rectangle bounds = drawingContext.getBounds();
/* 109:129 */     Rectangle2D_ logicalBounds = drawingContext.getLogicalBounds();
/* 110:    */     
/* 111:    */ 
/* 112:132 */     Rectangle2D_ tmp = drawingContext.rectangleToLogical(bounds);
/* 113:    */     
/* 114:134 */     int i = 0;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public static void main(String[] args)
/* 118:    */   {
/* 119:141 */     EventQueue.invokeLater(new Runnable()
/* 120:    */     {
/* 121:    */       public void run()
/* 122:    */       {
/* 123:143 */         new TestFrame().setVisible(true);
/* 124:    */       }
/* 125:    */     });
/* 126:    */   }
/* 127:    */   
/* 128:    */   public class ScalePanel
/* 129:    */     extends JPanel
/* 130:    */   {
/* 131:153 */     BasicDocument document = null;
/* 132:155 */     DrawingEngine drawingEngine = null;
/* 133:156 */     DrawingContext drawingContext = null;
/* 134:    */     Point2D_ p_clicked;
/* 135:    */     
/* 136:    */     public ScalePanel(BasicDocument document, DrawingEngine drawingEngine)
/* 137:    */     {
/* 138:159 */       this.document = document;
/* 139:160 */       this.drawingEngine = drawingEngine;
/* 140:    */       
/* 141:162 */       this.drawingContext = document.getContext().getDrawingContext();
/* 142:    */       
/* 143:164 */       initComponents();
/* 144:165 */       setFocusable(true);
/* 145:166 */       setSize(1, 1);
/* 146:    */       
/* 147:168 */       this.drawingContext.setBounds(0, 0, getWidth(), getHeight());
/* 148:169 */       this.drawingContext.setLogicalBounds(document.getLayout().getBounds());
/* 149:    */     }
/* 150:    */     
/* 151:    */     public void paint(Graphics g)
/* 152:    */     {
/* 153:173 */       Graphics2D g2 = (Graphics2D)g;
/* 154:174 */       g2.drawImage(this.drawingEngine.getImage(), 0, 0, this);
/* 155:    */     }
/* 156:    */     
/* 157:    */     public void refresh()
/* 158:    */     {
/* 159:178 */       this.drawingContext.setChanged(true);
/* 160:179 */       paint();
/* 161:    */     }
/* 162:    */     
/* 163:    */     public void paint()
/* 164:    */     {
/* 165:183 */       paint(getGraphics());
/* 166:    */     }
/* 167:    */     
/* 168:    */     private void initComponents()
/* 169:    */     {
/* 170:188 */       addComponentListener(new ComponentAdapter()
/* 171:    */       {
/* 172:    */         public void componentResized(ComponentEvent evt)
/* 173:    */         {
/* 174:190 */           TestFrame.ScalePanel.this.formComponentResized(evt);
/* 175:    */         }
/* 176:192 */       });
/* 177:193 */       addMouseMotionListener(new MouseMotionAdapter()
/* 178:    */       {
/* 179:    */         public void mouseDragged(MouseEvent evt)
/* 180:    */         {
/* 181:195 */           TestFrame.ScalePanel.this.formMouseDragged(evt);
/* 182:    */         }
/* 183:198 */       });
/* 184:199 */       addMouseListener(new MouseAdapter()
/* 185:    */       {
/* 186:    */         public void mousePressed(MouseEvent evt)
/* 187:    */         {
/* 188:201 */           TestFrame.ScalePanel.this.formMousePressed(evt);
/* 189:    */         }
/* 190:204 */       });
/* 191:205 */       addMouseWheelListener(new MouseWheelListener()
/* 192:    */       {
/* 193:    */         public void mouseWheelMoved(MouseWheelEvent evt)
/* 194:    */         {
/* 195:207 */           TestFrame.ScalePanel.this.formMouseWheelMoved(evt);
/* 196:    */         }
/* 197:209 */       });
/* 198:210 */       setLayout(new BorderLayout());
/* 199:    */     }
/* 200:    */     
/* 201:    */     private void formMousePressed(MouseEvent evt)
/* 202:    */     {
/* 203:216 */       requestFocusInWindow();
/* 204:    */       
/* 205:218 */       this.p_clicked = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/* 206:    */       
/* 207:220 */       paint();
/* 208:    */     }
/* 209:    */     
/* 210:    */     private void formMouseDragged(MouseEvent evt)
/* 211:    */     {
/* 212:225 */       Point2D_ p = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/* 213:    */       
/* 214:227 */       double lenX = this.p_clicked.x - p.x;
/* 215:228 */       double lenY = this.p_clicked.y - p.y;
/* 216:    */       
/* 217:230 */       this.drawingContext.moveTo(lenX, lenY);
/* 218:    */       
/* 219:232 */       refresh();
/* 220:    */     }
/* 221:    */     
/* 222:    */     private void formMouseWheelMoved(MouseWheelEvent evt)
/* 223:    */     {
/* 224:236 */       this.drawingContext.changeScaleTo(evt.getWheelRotation());
/* 225:237 */       paint();
/* 226:    */     }
/* 227:    */     
/* 228:    */     private void formComponentResized(ComponentEvent evt)
/* 229:    */     {
/* 230:    */       try
/* 231:    */       {
/* 232:242 */         this.drawingContext.setBounds(0, 0, getWidth(), getHeight());
/* 233:243 */         paint();
/* 234:    */       }
/* 235:    */       catch (VirtualMachineError err)
/* 236:    */       {
/* 237:245 */         JOptionPane.showMessageDialog(null, "Couldn't create document. Out of memory!!!");
/* 238:    */       }
/* 239:    */     }
/* 240:    */   }
/* 241:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.TestFrame
 * JD-Core Version:    0.7.0.1
 */