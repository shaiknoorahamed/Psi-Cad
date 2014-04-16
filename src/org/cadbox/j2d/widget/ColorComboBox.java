/*   1:    */ package org.cadbox.j2d.widget;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Component;
/*   5:    */ import java.awt.Dimension;
/*   6:    */ import java.awt.FlowLayout;
/*   7:    */ import java.awt.Graphics;
/*   8:    */ import java.awt.SystemColor;
/*   9:    */ import java.awt.event.ItemEvent;
/*  10:    */ import java.awt.event.ItemListener;
/*  11:    */ import javax.swing.DefaultComboBoxModel;
/*  12:    */ import javax.swing.JComboBox;
/*  13:    */ import javax.swing.JLabel;
/*  14:    */ import javax.swing.JList;
/*  15:    */ import javax.swing.JPanel;
/*  16:    */ import javax.swing.ListCellRenderer;
/*  17:    */ import org.cadbox.Command;
/*  18:    */ import org.cadbox.Document;
/*  19:    */ import org.cadbox.SimpleURManager;
/*  20:    */ import org.cadbox.UREngine;
/*  21:    */ import org.cadbox.URManager;
/*  22:    */ import org.cadbox.j2d.CadApplication;
/*  23:    */ import org.cadbox.j2d.CadDocument;
/*  24:    */ import org.cadbox.j2d.command.DocumentCommandFactory;
/*  25:    */ import org.cadbox.j2d.scale.command.ColorChangeCommand;
/*  26:    */ import org.cadbox.j2d.scale.core.Context;
/*  27:    */ import org.cadbox.j2d.scale.core.PropertyContext;
/*  28:    */ 
/*  29:    */ public class ColorComboBox
/*  30:    */   extends JComboBox
/*  31:    */ {
/*  32:    */   MyItemListener listener;
/*  33:    */   CadApplication application;
/*  34:    */   
/*  35:    */   public ColorComboBox(CadApplication application)
/*  36:    */   {
/*  37: 58 */     this.application = application;
/*  38:    */     
/*  39: 60 */     Object[] items = new Object[9];
/*  40: 61 */     items[0] = new String("By layer");
/*  41: 62 */     items[2] = Color.red;
/*  42: 63 */     items[3] = Color.yellow;
/*  43: 64 */     items[4] = Color.green;
/*  44: 65 */     items[5] = Color.blue;
/*  45: 66 */     items[6] = Color.gray;
/*  46: 67 */     items[7] = Color.magenta;
/*  47: 68 */     items[8] = new String("Select color...");
/*  48:    */     
/*  49: 70 */     init(items);
/*  50:    */   }
/*  51:    */   
/*  52:    */   public ColorComboBox(CadApplication application, Object[] items)
/*  53:    */   {
/*  54: 75 */     super(items);
/*  55: 76 */     this.application = application;
/*  56:    */     
/*  57: 78 */     init(items);
/*  58:    */   }
/*  59:    */   
/*  60:    */   private void init(Object[] items)
/*  61:    */   {
/*  62: 88 */     setModel(new DefaultComboBoxModel(items));
/*  63:    */     
/*  64: 90 */     setSelectedIndex(0);
/*  65: 91 */     setPreferredSize(new Dimension(130, 23));
/*  66: 92 */     setFocusable(false);
/*  67: 93 */     setMaximumRowCount(15);
/*  68:    */     
/*  69:    */ 
/*  70: 96 */     this.listener = new MyItemListener();
/*  71: 97 */     addItemListener(this.listener);
/*  72:    */     
/*  73: 99 */     JLRenderer cellRenderer = new JLRenderer();
/*  74:100 */     setRenderer(cellRenderer);
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void doSelectColor()
/*  78:    */   {
/*  79:105 */     setSelectedIndex(getItemCount() - 1);
/*  80:    */   }
/*  81:    */   
/*  82:    */   private PropertyContext getPropertyContext()
/*  83:    */   {
/*  84:110 */     Document doc = this.application.getCurrentDocument();
/*  85:111 */     if (doc != null)
/*  86:    */     {
/*  87:112 */       if ((doc instanceof CadDocument)) {
/*  88:113 */         return ((CadDocument)doc).getContext().getPropertyContext();
/*  89:    */       }
/*  90:115 */       throw new IllegalStateException("Needs Cad document");
/*  91:    */     }
/*  92:118 */     return new PropertyContext();
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void update()
/*  96:    */   {
/*  97:122 */     setCurrentColor(getPropertyContext().getColor());
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void setEnabled(boolean state)
/* 101:    */   {
/* 102:126 */     super.setEnabled(state);
/* 103:127 */     if (!state) {
/* 104:128 */       setSelectedIndex(-1);
/* 105:    */     }
/* 106:    */   }
/* 107:    */   
/* 108:    */   public Color getCurrentColor()
/* 109:    */   {
/* 110:133 */     if ((getSelectedItem() instanceof Color)) {
/* 111:134 */       return (Color)getSelectedItem();
/* 112:    */     }
/* 113:136 */     return null;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void setCurrentColor(Color color)
/* 117:    */   {
/* 118:141 */     removeItemListener(this.listener);
/* 119:144 */     if (!containColor(color)) {
/* 120:145 */       insertColor(color);
/* 121:    */     }
/* 122:149 */     addItemListener(this.listener);
/* 123:    */   }
/* 124:    */   
/* 125:    */   protected void insertColor(Color color)
/* 126:    */   {
/* 127:153 */     if (color != null)
/* 128:    */     {
/* 129:154 */       insertItemAt(color, 8);
/* 130:155 */       setSelectedIndex(8);
/* 131:    */     }
/* 132:    */   }
/* 133:    */   
/* 134:    */   protected boolean containColor(Color color)
/* 135:    */   {
/* 136:160 */     if (color != null)
/* 137:    */     {
/* 138:161 */       boolean contains = false;
/* 139:162 */       int count = getItemCount();
/* 140:163 */       for (int i = 0; i < count; i++) {
/* 141:164 */         if ((getItemAt(i) instanceof Color))
/* 142:    */         {
/* 143:165 */           Color dis = (Color)getItemAt(i);
/* 144:166 */           if ((dis != null) && 
/* 145:167 */             (dis.equals(color) == true))
/* 146:    */           {
/* 147:168 */             contains = true;
/* 148:169 */             setSelectedIndex(i);
/* 149:    */           }
/* 150:    */         }
/* 151:    */       }
/* 152:174 */       return contains;
/* 153:    */     }
/* 154:176 */     setSelectedIndex(0);
/* 155:    */     
/* 156:178 */     return true;
/* 157:    */   }
/* 158:    */   
/* 159:    */   class MyItemListener
/* 160:    */     implements ItemListener
/* 161:    */   {
/* 162:    */     Color deselected;
/* 163:    */     
/* 164:    */     public MyItemListener() {}
/* 165:    */     
/* 166:    */     public void itemStateChanged(ItemEvent e)
/* 167:    */     {
/* 168:191 */       if (e.getStateChange() == 2) {
/* 169:192 */         this.deselected = ColorComboBox.this.getCurrentColor();
/* 170:    */       }
/* 171:195 */       if (e.getStateChange() == 1)
/* 172:    */       {
/* 173:197 */         int index = ColorComboBox.this.getSelectedIndex();
/* 174:200 */         if (index == ColorComboBox.this.getItemCount() - 1)
/* 175:    */         {
/* 176:203 */           Command scdc = ColorComboBox.this.application.getDocumentCommandFactory().createShowColorDialogCommand();
/* 177:204 */           scdc.execute();
/* 178:    */           
/* 179:206 */           Color color = ColorComboBox.this.getPropertyContext().getColor();
/* 180:208 */           if (color != null) {
/* 181:209 */             ColorComboBox.this.setCurrentColor(color);
/* 182:    */           } else {
/* 183:211 */             ColorComboBox.this.setCurrentColor(this.deselected);
/* 184:    */           }
/* 185:    */         }
/* 186:215 */         ColorChangeCommand cmd = new ColorChangeCommand(ColorComboBox.this.getPropertyContext(), ColorComboBox.this.getCurrentColor());
/* 187:216 */         SimpleURManager.getInstance().getUREngine(ColorComboBox.this.application.getCurrentDocument()).addCommand(cmd);
/* 188:217 */         cmd.execute();
/* 189:    */       }
/* 190:    */     }
/* 191:    */   }
/* 192:    */   
/* 193:    */   public static class ColorPanel
/* 194:    */     extends JPanel
/* 195:    */   {
/* 196:    */     JPanel colorLabel;
/* 197:    */     FlowLayout layout;
/* 198:    */     JPanel box;
/* 199:    */     JLabel label;
/* 200:    */     Color color;
/* 201:    */     boolean isSelected;
/* 202:    */     
/* 203:    */     public ColorPanel(Color color, String name, boolean isSelected)
/* 204:    */     {
/* 205:238 */       this.color = color;
/* 206:239 */       this.isSelected = isSelected;
/* 207:    */       
/* 208:241 */       init();
/* 209:242 */       update();
/* 210:    */     }
/* 211:    */     
/* 212:    */     private void init()
/* 213:    */     {
/* 214:247 */       this.layout = new FlowLayout(0);
/* 215:248 */       this.layout.setHgap(1);
/* 216:249 */       this.layout.setVgap(1);
/* 217:250 */       setLayout(this.layout);
/* 218:252 */       if (!this.isSelected) {
/* 219:253 */         setBackground(SystemColor.text);
/* 220:    */       } else {
/* 221:255 */         setBackground(SystemColor.activeCaption);
/* 222:    */       }
/* 223:259 */       this.colorLabel = new JPanel()
/* 224:    */       {
/* 225:    */         public void paint(Graphics g)
/* 226:    */         {
/* 227:261 */           super.paint(g);
/* 228:262 */           g.setColor(Color.black);
/* 229:263 */           g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
/* 230:    */         }
/* 231:266 */       };
/* 232:267 */       this.colorLabel.setPreferredSize(new Dimension(14, 14));
/* 233:268 */       add(this.colorLabel);
/* 234:    */       
/* 235:    */ 
/* 236:271 */       this.box = new JPanel();
/* 237:272 */       this.box.setPreferredSize(new Dimension(4, 0));
/* 238:273 */       add(this.box);
/* 239:    */       
/* 240:    */ 
/* 241:276 */       this.label = new JLabel();
/* 242:277 */       add(this.label);
/* 243:    */     }
/* 244:    */     
/* 245:    */     public void setViewColor(Color color)
/* 246:    */     {
/* 247:281 */       this.color = color;
/* 248:282 */       update();
/* 249:    */     }
/* 250:    */     
/* 251:    */     private String colorToString(Color color)
/* 252:    */     {
/* 253:286 */       if (color.equals(Color.white)) {
/* 254:287 */         return "White";
/* 255:    */       }
/* 256:289 */       if (color.equals(Color.black)) {
/* 257:290 */         return "Black";
/* 258:    */       }
/* 259:292 */       if (color.equals(Color.red)) {
/* 260:293 */         return "Red";
/* 261:    */       }
/* 262:295 */       if (color.equals(Color.yellow)) {
/* 263:296 */         return "Yellow";
/* 264:    */       }
/* 265:298 */       if (color.equals(Color.blue)) {
/* 266:299 */         return "Blue";
/* 267:    */       }
/* 268:301 */       if (color.equals(Color.green)) {
/* 269:302 */         return "Green";
/* 270:    */       }
/* 271:304 */       if (color.equals(Color.gray)) {
/* 272:305 */         return "Gray";
/* 273:    */       }
/* 274:307 */       if (color.equals(Color.magenta)) {
/* 275:308 */         return "Magenta";
/* 276:    */       }
/* 277:310 */       return color.getRed() + ":" + color.getGreen() + ":" + color.getBlue();
/* 278:    */     }
/* 279:    */     
/* 280:    */     public void update()
/* 281:    */     {
/* 282:317 */       this.label.setText(colorToString(this.color));
/* 283:319 */       if (!this.isSelected)
/* 284:    */       {
/* 285:320 */         setBackground(SystemColor.text);
/* 286:321 */         this.label.setForeground(SystemColor.controlText);
/* 287:    */       }
/* 288:    */       else
/* 289:    */       {
/* 290:324 */         setBackground(SystemColor.activeCaption);
/* 291:325 */         this.label.setForeground(Color.white);
/* 292:    */       }
/* 293:328 */       if (this.color != null) {
/* 294:329 */         this.colorLabel.setBackground(this.color);
/* 295:    */       } else {
/* 296:331 */         this.colorLabel.setBackground(Color.white);
/* 297:    */       }
/* 298:    */     }
/* 299:    */   }
/* 300:    */   
/* 301:    */   class JLRenderer
/* 302:    */     implements ListCellRenderer
/* 303:    */   {
/* 304:    */     public JLRenderer() {}
/* 305:    */     
/* 306:    */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 307:    */     {
/* 308:345 */       if ((value instanceof Color))
/* 309:    */       {
/* 310:346 */         Color color = (Color)value;
/* 311:348 */         if (color != null) {
/* 312:349 */           return new ColorComboBox.ColorPanel(color, "", isSelected);
/* 313:    */         }
/* 314:    */       }
/* 315:353 */       if ((value instanceof String))
/* 316:    */       {
/* 317:354 */         JPanel panel = new JPanel();
/* 318:355 */         panel.setPreferredSize(new Dimension(100, 20));
/* 319:356 */         panel.setLayout(new FlowLayout(0, 5, 0));
/* 320:    */         
/* 321:358 */         JLabel label = new JLabel((String)value);
/* 322:359 */         label.setPreferredSize(new Dimension(100, 20));
/* 323:360 */         panel.add(label);
/* 324:362 */         if (isSelected)
/* 325:    */         {
/* 326:363 */           panel.setBackground(SystemColor.activeCaption);
/* 327:364 */           label.setForeground(Color.white);
/* 328:    */         }
/* 329:    */         else
/* 330:    */         {
/* 331:366 */           panel.setBackground(SystemColor.text);
/* 332:367 */           label.setForeground(SystemColor.controlText);
/* 333:    */         }
/* 334:371 */         return panel;
/* 335:    */       }
/* 336:374 */       return new JLabel("");
/* 337:    */     }
/* 338:    */   }
/* 339:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.ColorComboBox
 * JD-Core Version:    0.7.0.1
 */