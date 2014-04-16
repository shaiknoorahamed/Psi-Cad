/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.awt.BasicStroke;
/*   4:    */ import java.awt.Color;
/*   5:    */ import java.awt.Graphics2D;
/*   6:    */ import java.awt.Rectangle;
/*   7:    */ import java.awt.Stroke;
/*   8:    */ import java.awt.image.BufferedImage;
/*   9:    */ import java.beans.PropertyChangeSupport;
/*  10:    */ import java.util.Collection;
/*  11:    */ import java.util.Iterator;
/*  12:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  13:    */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*  14:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*  15:    */ import org.cadbox.j2d.scale.core.unit.UnitTranslator;
/*  16:    */ 
/*  17:    */ public class SimpleDrawingEngine
/*  18:    */   extends DrawingEngine
/*  19:    */ {
/*  20: 54 */   BasicDocument document = null;
/*  21:    */   PropertyChangeSupport propertyChangeSupport;
/*  22: 59 */   private Color snapPointColor = Color.blue;
/*  23: 60 */   private BasicStroke snapPointStroke = new BasicStroke(3.0F);
/*  24: 62 */   private Color shapeDrawingColor = Color.black;
/*  25: 63 */   private BasicStroke shapeDrawingStroke = new BasicStroke(1.0F);
/*  26: 66 */   private Stroke selectedShapeStroke = new BasicStroke(1.0F, 0, 0, 1.0F, new float[] { 5.0F }, 0.0F);
/*  27: 69 */   private Color backgroundColor = Color.white;
/*  28: 70 */   private Color foregroundColor = Color.black;
/*  29: 73 */   private double[] xP = new double[2000];
/*  30: 74 */   private double[] yP = new double[2000];
/*  31:    */   Context context;
/*  32:    */   DrawingContext drawingContext;
/*  33:    */   PropertyContext propertyContext;
/*  34:    */   
/*  35:    */   public SimpleDrawingEngine(BasicDocument document, PropertyChangeSupport propertyChangeSupport)
/*  36:    */   {
/*  37: 82 */     this.document = document;
/*  38: 83 */     this.propertyChangeSupport = propertyChangeSupport;
/*  39:    */     
/*  40: 85 */     this.context = document.getContext();
/*  41: 86 */     this.drawingContext = this.context.getDrawingContext();
/*  42: 87 */     this.propertyContext = this.context.getPropertyContext();
/*  43:    */   }
/*  44:    */   
/*  45:    */   public Color getSnapPointColor()
/*  46:    */   {
/*  47: 91 */     return this.snapPointColor;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public void setSnapPointColor(Color snapPointColor)
/*  51:    */   {
/*  52: 95 */     this.snapPointColor = snapPointColor;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public BasicStroke getSnapPointStroke()
/*  56:    */   {
/*  57: 99 */     return this.snapPointStroke;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void setSnapPointStroke(BasicStroke snapPointStroke)
/*  61:    */   {
/*  62:103 */     this.snapPointStroke = snapPointStroke;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public Color getShapeDrawingColor()
/*  66:    */   {
/*  67:107 */     return this.shapeDrawingColor;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void setShapeDrawingColor(Color shapeDrawingColor)
/*  71:    */   {
/*  72:111 */     this.shapeDrawingColor = shapeDrawingColor;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public BasicStroke getShapeDrawingStroke()
/*  76:    */   {
/*  77:115 */     return this.shapeDrawingStroke;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setShapeDrawingStroke(BasicStroke shapeDrawingStroke)
/*  81:    */   {
/*  82:119 */     this.shapeDrawingStroke = shapeDrawingStroke;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public Stroke getSelectedShapeStroke()
/*  86:    */   {
/*  87:131 */     return this.selectedShapeStroke;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setSelectedShapeStroke(Stroke selectedShapeStroke)
/*  91:    */   {
/*  92:135 */     this.selectedShapeStroke = selectedShapeStroke;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public BufferedImage getImage()
/*  96:    */   {
/*  97:139 */     draw();
/*  98:140 */     return this.drawingContext.getImage();
/*  99:    */   }
/* 100:    */   
/* 101:    */   private synchronized void draw()
/* 102:    */   {
/* 103:144 */     if (this.drawingContext.isChanged())
/* 104:    */     {
/* 105:145 */       paint();
/* 106:146 */       this.drawingContext.setChanged(false);
/* 107:    */     }
/* 108:    */   }
/* 109:    */   
/* 110:    */   private void paint()
/* 111:    */   {
/* 112:152 */     Graphics2D big = this.drawingContext.getGraphics();
/* 113:    */     
/* 114:    */ 
/* 115:155 */     Layout layout = this.document.getLayout();
/* 116:156 */     if ((layout != null) && (layout.getLayoutType() == 0)) {
/* 117:157 */       preDraw();
/* 118:    */     } else {
/* 119:159 */       preDraw1();
/* 120:    */     }
/* 121:165 */     Iterator iter1 = this.document.getLayers().iterator();
/* 122:166 */     while (iter1.hasNext())
/* 123:    */     {
/* 124:167 */       Layer layer = (Layer)iter1.next();
/* 125:168 */       if (layer.getVisible() == true)
/* 126:    */       {
/* 127:169 */         Iterator iter = layer.getShapes().iterator();
/* 128:170 */         while (iter.hasNext())
/* 129:    */         {
/* 130:171 */           Shape shape = (Shape)iter.next();
/* 131:172 */           if (isShapeInWindowBounds(shape))
/* 132:    */           {
/* 133:174 */             big.setColor(shape.getColor());
/* 134:    */             
/* 135:176 */             double width = UnitTranslator.lengthUnitToPixel(shape.getLineWeight().getLineWeightValue());
/* 136:177 */             float w = this.drawingContext.distanseToScreen(width);
/* 137:    */             
/* 138:179 */             float[] t = shape.getLineType().getLineTypeValue();
/* 139:180 */             for (int i = 0; i < t.length; i++) {
/* 140:181 */               t[i] = this.drawingContext.distanseToScreen(t[i] * 10.0F);
/* 141:    */             }
/* 142:185 */             BasicStroke bs = new BasicStroke(w, 1, 1);
/* 143:186 */             big.setStroke(bs);
/* 144:    */             
/* 145:188 */             shape.draw(this.context);
/* 146:    */           }
/* 147:    */         }
/* 148:    */       }
/* 149:    */     }
/* 150:    */   }
/* 151:    */   
/* 152:    */   private boolean isShapeInWindowBounds(Shape shape)
/* 153:    */   {
/* 154:198 */     Rectangle2D_ bounds = shape.getBounds();
/* 155:    */     
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:    */ 
/* 162:    */ 
/* 163:    */ 
/* 164:    */ 
/* 165:209 */     Rectangle s_boundes = this.drawingContext.getBounds();
/* 166:211 */     if ((Double.valueOf(bounds.getWidth()).equals(Double.valueOf((1.0D / 0.0D)))) || (Double.valueOf(bounds.getHeight()).equals(Double.valueOf((1.0D / 0.0D))))) {
/* 167:213 */       return true;
/* 168:    */     }
/* 169:215 */     Rectangle b1 = this.drawingContext.rectangleToScreen(bounds);
/* 170:216 */     if (b1.intersects(s_boundes)) {
/* 171:217 */       return true;
/* 172:    */     }
/* 173:219 */     return false;
/* 174:    */   }
/* 175:    */   
/* 176:    */   private void preDraw()
/* 177:    */   {
/* 178:225 */     Graphics2D big = this.drawingContext.getGraphics();
/* 179:    */     
/* 180:227 */     big.setColor(this.backgroundColor);
/* 181:228 */     big.setStroke(new BasicStroke(1.0F));
/* 182:    */     
/* 183:230 */     big.fillRect(0, 0, this.drawingContext.getWidth(), this.drawingContext.getHeight());
/* 184:    */     
/* 185:232 */     Point2I p1 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalLeft(), this.drawingContext.getLogicalTop()));
/* 186:233 */     Point2I p2 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalRight(), this.drawingContext.getLogicalBottom()));
/* 187:    */     
/* 188:    */ 
/* 189:236 */     big.setColor(Color.black);
/* 190:237 */     big.drawRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
/* 191:    */     
/* 192:    */ 
/* 193:    */ 
/* 194:    */ 
/* 195:    */ 
/* 196:    */ 
/* 197:    */ 
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:    */ 
/* 202:    */ 
/* 203:    */ 
/* 204:    */ 
/* 205:    */ 
/* 206:    */ 
/* 207:    */ 
/* 208:    */ 
/* 209:    */ 
/* 210:    */ 
/* 211:    */ 
/* 212:259 */     big.setColor(Color.lightGray);
/* 213:260 */     p1 = this.drawingContext.translateToScreen(new Point2D_(250.0D, 0.0D));
/* 214:261 */     p2 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 0.0D));
/* 215:262 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/* 216:    */     
/* 217:264 */     p1 = this.drawingContext.translateToScreen(new Point2D_(250.0D, 0.0D));
/* 218:265 */     p2 = this.drawingContext.translateToScreen(new Point2D_(240.0D, 5.0D));
/* 219:266 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/* 220:267 */     p1 = this.drawingContext.translateToScreen(new Point2D_(250.0D, 0.0D));
/* 221:268 */     p2 = this.drawingContext.translateToScreen(new Point2D_(240.0D, -5.0D));
/* 222:269 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/* 223:    */     
/* 224:    */ 
/* 225:272 */     p1 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 250.0D));
/* 226:273 */     p2 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 0.0D));
/* 227:274 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/* 228:    */     
/* 229:276 */     p1 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 250.0D));
/* 230:277 */     p2 = this.drawingContext.translateToScreen(new Point2D_(5.0D, 240.0D));
/* 231:278 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/* 232:    */     
/* 233:280 */     p1 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 250.0D));
/* 234:281 */     p2 = this.drawingContext.translateToScreen(new Point2D_(-5.0D, 240.0D));
/* 235:282 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/* 236:    */   }
/* 237:    */   
/* 238:    */   private void preDraw1()
/* 239:    */   {
/* 240:289 */     Graphics2D big = this.drawingContext.getGraphics();
/* 241:    */     
/* 242:    */ 
/* 243:292 */     big.setColor(Color.gray);
/* 244:293 */     big.setStroke(new BasicStroke(1.0F));
/* 245:    */     
/* 246:295 */     big.fillRect(0, 0, this.drawingContext.getWidth(), this.drawingContext.getHeight());
/* 247:    */     
/* 248:297 */     Point2I p1 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalLeft(), this.drawingContext.getLogicalTop()));
/* 249:298 */     Point2I p2 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalRight(), this.drawingContext.getLogicalBottom()));
/* 250:    */     
/* 251:    */ 
/* 252:301 */     big.setColor(Color.black);
/* 253:302 */     big.fillRect(p1.x + 5, p1.y + 5, p2.x - p1.x, p2.y - p1.y);
/* 254:    */     
/* 255:304 */     big.setColor(Color.white);
/* 256:305 */     big.fillRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
/* 257:    */     
/* 258:307 */     big.setColor(Color.black);
/* 259:308 */     big.drawRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
/* 260:    */   }
/* 261:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.SimpleDrawingEngine
 * JD-Core Version:    0.7.0.1
 */