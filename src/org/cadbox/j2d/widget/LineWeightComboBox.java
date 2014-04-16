/*   1:    */ package org.cadbox.j2d.widget;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Component;
/*   5:    */ import java.awt.Dimension;
/*   6:    */ import java.awt.FlowLayout;
/*   7:    */ import java.awt.Graphics;
/*   8:    */ import java.awt.Graphics2D;
/*   9:    */ import java.awt.SystemColor;
/*  10:    */ import java.awt.event.ItemEvent;
/*  11:    */ import java.awt.event.ItemListener;
/*  12:    */ import java.util.Vector;
/*  13:    */ import javax.swing.DefaultComboBoxModel;
/*  14:    */ import javax.swing.JComboBox;
/*  15:    */ import javax.swing.JLabel;
/*  16:    */ import javax.swing.JList;
/*  17:    */ import javax.swing.JPanel;
/*  18:    */ import javax.swing.ListCellRenderer;
/*  19:    */ import org.cadbox.Application;
/*  20:    */ import org.cadbox.Document;
/*  21:    */ import org.cadbox.SimpleURManager;
/*  22:    */ import org.cadbox.UREngine;
/*  23:    */ import org.cadbox.URManager;
/*  24:    */ import org.cadbox.j2d.CadDocument;
/*  25:    */ import org.cadbox.j2d.scale.command.LineWeightChangeCommand;
/*  26:    */ import org.cadbox.j2d.scale.core.Context;
/*  27:    */ import org.cadbox.j2d.scale.core.LineWeight;
/*  28:    */ import org.cadbox.j2d.scale.core.LineWeightManager;
/*  29:    */ import org.cadbox.j2d.scale.core.PropertyContext;
/*  30:    */ import org.cadbox.j2d.scale.core.unit.UnitTranslator;
/*  31:    */ 
/*  32:    */ public class LineWeightComboBox
/*  33:    */   extends JComboBox
/*  34:    */ {
/*  35:    */   private Object[] lineWeights;
/*  36:    */   Application application;
/*  37:    */   MyItemListener listener;
/*  38: 60 */   static LineWeightManager inctance = LineWeightManager.getInstance()  ;
/*  39:    */   
/*  40:    */   public LineWeightComboBox(Application application)
/*  41:    */   {
/*  42: 65 */     this.application = application;
/*  43:    */     
/*  44: 67 */     init();
/*  45:    */   }
/*  46:    */   
/*  47:    */   private void init()
/*  48:    */   {
/*  49: 72 */     Vector weightList = new Vector(inctance.getLineWeightList());
/*  50:    */     
/*  51: 74 */     int size = weightList.size() + 1;
/*  52: 75 */     this.lineWeights = new Object[size];
/*  53:    */     
/*  54: 77 */     this.lineWeights[0] = new String("By layer");
/*  55: 79 */     for (int i = 1; i < size; i++) {
/*  56: 80 */       this.lineWeights[i] = weightList.get(i - 1);
/*  57:    */     }
/*  58: 83 */     setModel(new DefaultComboBoxModel(this.lineWeights));
/*  59:    */     
/*  60: 85 */     setSelectedIndex(0);
/*  61: 86 */     setPreferredSize(new Dimension(130, 23));
/*  62: 87 */     setFocusable(false);
/*  63:    */     
/*  64: 89 */     setMaximumRowCount(30);
/*  65:    */     
/*  66: 91 */     JLRenderer cellRenderer = new JLRenderer();
/*  67: 92 */     setRenderer(cellRenderer);
/*  68:    */     
/*  69:    */ 
/*  70: 95 */     this.listener = new MyItemListener();
/*  71: 96 */     addItemListener(this.listener);
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setEnabled(boolean state)
/*  75:    */   {
/*  76:100 */     super.setEnabled(state);
/*  77:101 */     if (!state) {
/*  78:102 */       setSelectedIndex(-1);
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   private PropertyContext getPropertyContext()
/*  83:    */   {
/*  84:106 */     Document doc = this.application.getCurrentDocument();
/*  85:107 */     if (doc != null)
/*  86:    */     {
/*  87:108 */       if ((doc instanceof CadDocument)) {
/*  88:109 */         return ((CadDocument)doc).getContext().getPropertyContext();
/*  89:    */       }
/*  90:111 */       throw new IllegalStateException("Needs Cad document");
/*  91:    */     }
/*  92:114 */     return new PropertyContext();
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setCurrentLineWeight(LineWeight lineWeight)
/*  96:    */   {
/*  97:119 */     removeItemListener(this.listener);
/*  98:    */     
/*  99:121 */     selectLineWeight(lineWeight);
/* 100:    */     
/* 101:123 */     addItemListener(this.listener);
/* 102:    */   }
/* 103:    */   
/* 104:    */   protected void selectLineWeight(LineWeight lineWeight)
/* 105:    */   {
/* 106:128 */     if (lineWeight != null)
/* 107:    */     {
/* 108:129 */       int count = getItemCount();
/* 109:130 */       for (int i = 0; i < count; i++) {
/* 110:132 */         if ((getItemAt(i) instanceof LineWeight))
/* 111:    */         {
/* 112:133 */           LineWeight lw = (LineWeight)getItemAt(i);
/* 113:134 */           if ((lw != null) && 
/* 114:135 */             (lw.equals(lineWeight) == true))
/* 115:    */           {
/* 116:136 */             setSelectedIndex(i);
/* 117:137 */             break;
/* 118:    */           }
/* 119:    */         }
/* 120:    */       }
/* 121:    */     }
/* 122:    */     else
/* 123:    */     {
/* 124:143 */       setSelectedIndex(0);
/* 125:    */     }
/* 126:    */   }
/* 127:    */   
/* 128:    */   public LineWeight getCurrentLineWeight()
/* 129:    */   {
/* 130:149 */     if ((getSelectedItem() instanceof LineWeight)) {
/* 131:150 */       return (LineWeight)getSelectedItem();
/* 132:    */     }
/* 133:152 */     return null;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void update()
/* 137:    */   {
/* 138:158 */     Vector weightList = new Vector(inctance.getLineWeightList());
/* 139:159 */     int size = weightList.size() + 1;
/* 140:    */     
/* 141:161 */     this.lineWeights[0] = new String("By layer");
/* 142:163 */     for (int i = 1; i < size; i++) {
/* 143:164 */       this.lineWeights[i] = weightList.get(i - 1);
/* 144:    */     }
/* 145:166 */     setModel(new DefaultComboBoxModel(this.lineWeights));
/* 146:    */     
/* 147:168 */     setCurrentLineWeight(getPropertyContext().getLineWeight());
/* 148:    */   }
/* 149:    */   
/* 150:    */   class MyItemListener
/* 151:    */     implements ItemListener
/* 152:    */   {
/* 153:    */     LineWeight deselected;
/* 154:    */     
/* 155:    */     public MyItemListener() {}
/* 156:    */     
/* 157:    */     public void itemStateChanged(ItemEvent e)
/* 158:    */     {
/* 159:182 */       if (e.getStateChange() == 2) {
/* 160:183 */         this.deselected = LineWeightComboBox.this.getCurrentLineWeight();
/* 161:    */       }
/* 162:186 */       if (e.getStateChange() == 1)
/* 163:    */       {
/* 164:188 */         LineWeightChangeCommand cmd = new LineWeightChangeCommand(LineWeightComboBox.this.getPropertyContext(), LineWeightComboBox.this.getCurrentLineWeight());
/* 165:189 */         SimpleURManager.getInstance().getUREngine(LineWeightComboBox.this.application.getCurrentDocument()).addCommand(cmd);
/* 166:190 */         cmd.execute();
/* 167:    */         
/* 168:192 */         LineWeightComboBox.this.application.updateAllObservers();
/* 169:    */       }
/* 170:    */     }
/* 171:    */   }
/* 172:    */   
/* 173:    */   private static class LineWidthView
/* 174:    */     extends JPanel
/* 175:    */   {
/* 176:    */     double width;
/* 177:    */     
/* 178:    */     public LineWidthView(double width)
/* 179:    */     {
/* 180:205 */       this.width = width;
/* 181:206 */       init();
/* 182:    */     }
/* 183:    */     
/* 184:    */     public void setWidth(double width)
/* 185:    */     {
/* 186:210 */       this.width = width;
/* 187:    */     }
/* 188:    */     
/* 189:    */     private void init()
/* 190:    */     {
/* 191:214 */       setBackground(SystemColor.text);
/* 192:215 */       setPreferredSize(new Dimension(40, 16));
/* 193:    */     }
/* 194:    */     
/* 195:    */     public void paint(Graphics g)
/* 196:    */     {
/* 197:219 */       super.paint(g);
/* 198:    */       
/* 199:221 */       Graphics2D g2 = (Graphics2D)g;
/* 200:222 */       g2.setColor(Color.black);
/* 201:    */       
/* 202:224 */       int tmp = Math.round((float)this.width);
/* 203:225 */       if (tmp <= 0) {
/* 204:226 */         tmp = 1;
/* 205:    */       }
/* 206:228 */       int offset = getHeight() / 2 - tmp / 2;
/* 207:229 */       g2.fillRect(1, offset, getWidth() - 1, tmp);
/* 208:    */     }
/* 209:    */   }
/* 210:    */   
/* 211:    */   public static class LineWidthPanel
/* 212:    */     extends JPanel
/* 213:    */   {
/* 214:    */     boolean isSelected;
/* 215:    */     LineWeight lineWeight;
/* 216:    */     FlowLayout layout;
/* 217:    */     LineWeightComboBox.LineWidthView lwPanel;
/* 218:    */     JPanel box;
/* 219:    */     JLabel label;
/* 220:    */     
/* 221:    */     public LineWidthPanel(LineWeight lineWeight, boolean isSelected)
/* 222:    */     {
/* 223:249 */       this.lineWeight = lineWeight;
/* 224:250 */       this.isSelected = isSelected;
/* 225:    */       
/* 226:252 */       init();
/* 227:    */     }
/* 228:    */     
/* 229:    */     private void init()
/* 230:    */     {
/* 231:256 */       this.layout = new FlowLayout(0);
/* 232:257 */       this.layout.setHgap(1);
/* 233:258 */       this.layout.setVgap(1);
/* 234:259 */       setLayout(this.layout);
/* 235:261 */       if (!this.isSelected) {
/* 236:262 */         setBackground(SystemColor.text);
/* 237:    */       } else {
/* 238:264 */         setBackground(SystemColor.activeCaption);
/* 239:    */       }
/* 240:268 */       this.lwPanel = new LineWeightComboBox.LineWidthView(UnitTranslator.lengthUnitToPixel(this.lineWeight.getLineWeightValue()));
/* 241:    */       
/* 242:    */ 
/* 243:    */ 
/* 244:272 */       this.lwPanel.setBackground(getBackground());
/* 245:273 */       add(this.lwPanel);
/* 246:    */       
/* 247:    */ 
/* 248:276 */       this.box = new JPanel();
/* 249:277 */       this.box.setPreferredSize(new Dimension(4, 0));
/* 250:278 */       add(this.box);
/* 251:    */       
/* 252:    */ 
/* 253:281 */       this.label = new JLabel();
/* 254:282 */       this.label.setText(this.lineWeight.toString());
/* 255:283 */       if (!this.isSelected) {
/* 256:284 */         this.label.setForeground(SystemColor.controlText);
/* 257:    */       } else {
/* 258:286 */         this.label.setForeground(Color.white);
/* 259:    */       }
/* 260:288 */       add(this.label);
/* 261:    */     }
/* 262:    */     
/* 263:    */     public void setViewLineWeight(LineWeight lineWeight)
/* 264:    */     {
/* 265:292 */       this.lineWeight = lineWeight;
/* 266:293 */       update();
/* 267:    */     }
/* 268:    */     
/* 269:    */     private void update()
/* 270:    */     {
/* 271:299 */       this.lwPanel.setWidth(UnitTranslator.lengthUnitToPixel(this.lineWeight.getLineWeightValue()));
/* 272:    */       
/* 273:    */ 
/* 274:    */ 
/* 275:    */ 
/* 276:    */ 
/* 277:305 */       this.label.setText(this.lineWeight.toString());
/* 278:306 */       if (!this.isSelected) {
/* 279:307 */         this.label.setForeground(SystemColor.controlText);
/* 280:    */       } else {
/* 281:309 */         this.label.setForeground(Color.white);
/* 282:    */       }
/* 283:312 */       updateUI();
/* 284:    */     }
/* 285:    */   }
/* 286:    */   
/* 287:    */   class JLRenderer
/* 288:    */     implements ListCellRenderer
/* 289:    */   {
/* 290:    */     public JLRenderer() {}
/* 291:    */     
/* 292:    */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 293:    */     {
/* 294:327 */       if ((value instanceof LineWeight))
/* 295:    */       {
/* 296:328 */         LineWeight lineWeight = (LineWeight)value;
/* 297:330 */         if (lineWeight != null) {
/* 298:331 */           return new LineWeightComboBox.LineWidthPanel(lineWeight, isSelected);
/* 299:    */         }
/* 300:    */       }
/* 301:336 */       if ((value instanceof String))
/* 302:    */       {
/* 303:337 */         JPanel panel = new JPanel();
/* 304:338 */         panel.setPreferredSize(new Dimension(100, 20));
/* 305:339 */         panel.setLayout(new FlowLayout(0, 5, 0));
/* 306:    */         
/* 307:341 */         JLabel label = new JLabel((String)value);
/* 308:342 */         label.setPreferredSize(new Dimension(100, 20));
/* 309:343 */         panel.add(label);
/* 310:345 */         if (isSelected)
/* 311:    */         {
/* 312:346 */           panel.setBackground(SystemColor.activeCaption);
/* 313:347 */           label.setForeground(Color.white);
/* 314:    */         }
/* 315:    */         else
/* 316:    */         {
/* 317:349 */           panel.setBackground(SystemColor.text);
/* 318:350 */           label.setForeground(SystemColor.controlText);
/* 319:    */         }
/* 320:353 */         return panel;
/* 321:    */       }
/* 322:356 */       return new JLabel("");
/* 323:    */     }
/* 324:    */   }
/* 325:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.LineWeightComboBox
 * JD-Core Version:    0.7.0.1
 */