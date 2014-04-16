/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.awt.Graphics2D;
/*   4:    */ import java.awt.Rectangle;
/*   5:    */ import java.awt.image.BufferedImage;
/*   6:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*   7:    */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*   8:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   9:    */ 
/*  10:    */ public class DrawingContext
/*  11:    */ {
/*  12: 47 */   private static int bufferSize = 2000;
/*  13:    */   private double scale;
/*  14:    */   private double persent;
/*  15:    */   private double x_center;
/*  16:    */   private double y_center;
/*  17:    */   private Rectangle2D_ logicalBounds;
/*  18:    */   private Rectangle bounds;
/*  19:    */   private double vectorX;
/*  20:    */   private double vectorY;
/*  21:    */   private BufferedImage bi;
/*  22:    */   private Graphics2D big;
/*  23:    */   private boolean changed;
/*  24: 66 */   private static ThreadLocal xBuffer = new ThreadLocal();
/*  25: 67 */   private static ThreadLocal yBuffer = new ThreadLocal();
/*  26:    */   
/*  27:    */   public static void setBufferSize(int size)
/*  28:    */   {
/*  29: 70 */     if (size <= 0) {
/*  30: 71 */       throw new IllegalArgumentException();
/*  31:    */     }
/*  32: 73 */     xBuffer.set(null);
/*  33: 74 */     yBuffer.set(null);
/*  34: 75 */     bufferSize = size;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public static int[] getXBuffer()
/*  38:    */   {
/*  39: 79 */     int[] tmp = (int[])xBuffer.get();
/*  40: 80 */     if (tmp == null)
/*  41:    */     {
/*  42: 81 */       tmp = new int[bufferSize];
/*  43: 82 */       xBuffer.set(tmp);
/*  44:    */     }
/*  45: 84 */     return tmp;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public static int[] getYBuffer()
/*  49:    */   {
/*  50: 88 */     int[] tmp = (int[])yBuffer.get();
/*  51: 89 */     if (tmp == null)
/*  52:    */     {
/*  53: 90 */       tmp = new int[bufferSize];
/*  54: 91 */       yBuffer.set(tmp);
/*  55:    */     }
/*  56: 93 */     return tmp;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public DrawingContext()
/*  60:    */   {
/*  61: 98 */     this.scale = 1.0D;
/*  62: 99 */     this.persent = 1.0D;
/*  63:100 */     this.logicalBounds = new Rectangle2D_();
/*  64:101 */     this.bounds = new Rectangle();
/*  65:102 */     this.changed = true;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public boolean isChanged()
/*  69:    */   {
/*  70:106 */     return this.changed;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void setChanged(boolean changed)
/*  74:    */   {
/*  75:110 */     this.changed = changed;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void setBounds(Rectangle rect)
/*  79:    */   {
/*  80:114 */     setBounds(rect.x, rect.y, rect.width, rect.height);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void setBounds(int x, int y, int width, int height)
/*  84:    */   {
/*  85:118 */     if ((width > 0) && (height > 0))
/*  86:    */     {
/*  87:119 */       this.bounds.x = x;
/*  88:120 */       this.bounds.y = y;
/*  89:121 */       this.bounds.width = width;
/*  90:122 */       this.bounds.height = height;
/*  91:123 */       resize();
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   protected void resize()
/*  96:    */   {
/*  97:128 */     this.bi = new BufferedImage(this.bounds.width, this.bounds.height, 5);
/*  98:129 */     this.big = this.bi.createGraphics();
/*  99:130 */     this.changed = true;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public Rectangle getBounds()
/* 103:    */   {
/* 104:134 */     return new Rectangle(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public int getWidth()
/* 108:    */   {
/* 109:138 */     return (int)this.bounds.getWidth();
/* 110:    */   }
/* 111:    */   
/* 112:    */   public int getHeight()
/* 113:    */   {
/* 114:142 */     return (int)this.bounds.getHeight();
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setLogicalBounds(Rectangle2D_ rect)
/* 118:    */   {
/* 119:146 */     setLogicalBounds(rect.x, rect.y, rect.width, rect.height);
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void setLogicalBounds(double x, double y, double width, double height)
/* 123:    */   {
/* 124:150 */     this.logicalBounds.x = x;
/* 125:151 */     this.logicalBounds.y = y;
/* 126:152 */     this.logicalBounds.width = width;
/* 127:153 */     this.logicalBounds.height = height;
/* 128:154 */     this.x_center = (x + width / 2.0D);
/* 129:155 */     this.y_center = (y - height / 2.0D);
/* 130:    */   }
/* 131:    */   
/* 132:    */   public Rectangle2D_ getLogicalBounds()
/* 133:    */   {
/* 134:159 */     return new Rectangle2D_(this.logicalBounds.getX(), this.logicalBounds.getY(), this.logicalBounds.getWidth(), this.logicalBounds.getHeight());
/* 135:    */   }
/* 136:    */   
/* 137:    */   public double getLogicalLeft()
/* 138:    */   {
/* 139:163 */     return this.logicalBounds.getX();
/* 140:    */   }
/* 141:    */   
/* 142:    */   public double getLogicalTop()
/* 143:    */   {
/* 144:167 */     return this.logicalBounds.getY();
/* 145:    */   }
/* 146:    */   
/* 147:    */   public double getLogicalRight()
/* 148:    */   {
/* 149:171 */     return getLogicalLeft() + getLogicalWidth();
/* 150:    */   }
/* 151:    */   
/* 152:    */   public double getLogicalBottom()
/* 153:    */   {
/* 154:175 */     return getLogicalTop() - getLogicalHeight();
/* 155:    */   }
/* 156:    */   
/* 157:    */   public double getLogicalWidth()
/* 158:    */   {
/* 159:179 */     return this.logicalBounds.getWidth();
/* 160:    */   }
/* 161:    */   
/* 162:    */   public double getLogicalHeight()
/* 163:    */   {
/* 164:183 */     return this.logicalBounds.getHeight();
/* 165:    */   }
/* 166:    */   
/* 167:    */   protected void calculateVector()
/* 168:    */   {
/* 169:187 */     this.vectorX = (this.x_center / this.scale - this.bounds.width / 2.0D);
/* 170:188 */     this.vectorY = (this.y_center / this.scale - this.bounds.height / 2.0D);
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void setScale(double persent)
/* 174:    */   {
/* 175:192 */     if ((persent > 0.0001D) && (persent < 1000000.0D))
/* 176:    */     {
/* 177:193 */       this.persent = persent;
/* 178:194 */       this.scale = (1.0D / persent);
/* 179:195 */       this.changed = true;
/* 180:    */     }
/* 181:    */   }
/* 182:    */   
/* 183:    */   public double getScale()
/* 184:    */   {
/* 185:200 */     return this.persent;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public Point2I translateToScreen(Point2D_ p)
/* 189:    */   {
/* 190:204 */     return translateToScreen(p.x, p.y);
/* 191:    */   }
/* 192:    */   
/* 193:    */   public Point2I translateToScreen(double x, double y)
/* 194:    */   {
/* 195:208 */     Point2I tmp = new Point2I();
/* 196:209 */     tmp.x = ((int)Math.round(x / this.scale - this.vectorX));
/* 197:210 */     tmp.y = (this.bounds.height - (int)Math.round(y / this.scale - this.vectorY));
/* 198:211 */     return tmp;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public Point2D_ translateToLogical(Point2I p)
/* 202:    */   {
/* 203:215 */     return translateToLogical(p.x, p.y);
/* 204:    */   }
/* 205:    */   
/* 206:    */   public Point2D_ translateToLogical(int x, int y)
/* 207:    */   {
/* 208:219 */     Point2D_ tmp = new Point2D_();
/* 209:220 */     tmp.x = ((x + this.vectorX) * this.scale);
/* 210:221 */     tmp.y = ((this.bounds.height - y + this.vectorY) * this.scale);
/* 211:222 */     return tmp;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public int distanseToScreen(double distance)
/* 215:    */   {
/* 216:226 */     return (int)Math.round(distance / this.scale);
/* 217:    */   }
/* 218:    */   
/* 219:    */   public double distanseToLogical(double distance)
/* 220:    */   {
/* 221:230 */     return distance * this.scale;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public Rectangle2D_ rectangleToLogical(Rectangle rect)
/* 225:    */   {
/* 226:234 */     Point2D_ p = translateToLogical(rect.x, rect.y);
/* 227:235 */     return new Rectangle2D_(p.x, p.y, distanseToLogical(rect.width), distanseToLogical(rect.height));
/* 228:    */   }
/* 229:    */   
/* 230:    */   public Rectangle rectangleToScreen(Rectangle2D_ rect)
/* 231:    */   {
/* 232:243 */     Point2I p = translateToScreen(rect.x, rect.y);
/* 233:244 */     return new Rectangle(p.x, p.y, distanseToScreen(rect.width), distanseToScreen(rect.height));
/* 234:    */   }
/* 235:    */   
/* 236:    */   public void moveTo(double x, double y)
/* 237:    */   {
/* 238:252 */     this.x_center += x;
/* 239:253 */     this.y_center += y;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public void changeScaleTo(int direction)
/* 243:    */   {
/* 244:257 */     if (direction < 0) {
/* 245:258 */       setScale(this.persent + this.persent / 3.0D);
/* 246:    */     }
/* 247:259 */     if (direction > 0) {
/* 248:260 */       setScale(this.persent - this.persent / 3.0D);
/* 249:    */     }
/* 250:    */   }
/* 251:    */   
/* 252:    */   public Graphics2D getGraphics()
/* 253:    */   {
/* 254:264 */     calculateVector();
/* 255:265 */     return this.big;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public BufferedImage getImage()
/* 259:    */   {
/* 260:269 */     return this.bi;
/* 261:    */   }
/* 262:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.DrawingContext
 * JD-Core Version:    0.7.0.1
 */