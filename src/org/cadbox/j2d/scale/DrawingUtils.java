/*   1:    */ package org.cadbox.j2d.scale;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Graphics2D;
/*   5:    */ import java.awt.Stroke;
/*   6:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*   7:    */ import org.cadbox.j2d.scale.core.GeometryUtils;
/*   8:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*   9:    */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*  10:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*  11:    */ 
/*  12:    */ public class DrawingUtils
/*  13:    */ {
/*  14:    */   public static final void drawLineXor(DrawingContext drawingContext, Color color, Stroke stroke, double x1, double y1, double x2, double y2)
/*  15:    */   {
/*  16: 51 */     Graphics2D big = drawingContext.getGraphics();
/*  17:    */     
/*  18: 53 */     Stroke stroke_old = big.getStroke();
/*  19: 54 */     Color color_old = big.getColor();
/*  20:    */     
/*  21: 56 */     big.setColor(color);
/*  22: 57 */     big.setStroke(stroke);
/*  23:    */     
/*  24: 59 */     Point2I p1 = drawingContext.translateToScreen(x1, y1);
/*  25: 60 */     Point2I p2 = drawingContext.translateToScreen(x2, y2);
/*  26:    */     
/*  27: 62 */     big.setXORMode(Color.white);
/*  28: 63 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  29: 64 */     big.setPaintMode();
/*  30:    */     
/*  31: 66 */     big.setColor(color_old);
/*  32: 67 */     big.setStroke(stroke_old);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static final void drawLine(DrawingContext drawingContext, Color color, Stroke stroke, double x1, double y1, double x2, double y2)
/*  36:    */   {
/*  37: 72 */     Graphics2D big = drawingContext.getGraphics();
/*  38:    */     
/*  39: 74 */     Stroke stroke_old = big.getStroke();
/*  40: 75 */     Color color_old = big.getColor();
/*  41:    */     
/*  42: 77 */     big.setColor(color);
/*  43: 78 */     big.setStroke(stroke);
/*  44:    */     
/*  45: 80 */     Point2I p1 = drawingContext.translateToScreen(x1, y1);
/*  46: 81 */     Point2I p2 = drawingContext.translateToScreen(x2, y2);
/*  47:    */     
/*  48: 83 */     big.setPaintMode();
/*  49: 84 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  50:    */     
/*  51: 86 */     big.setColor(color_old);
/*  52: 87 */     big.setStroke(stroke_old);
/*  53:    */   }
/*  54:    */   
/*  55:    */   public static final void drawStarngeLineXor(DrawingContext drawingContext, Color color, Stroke stroke, double x1, double y1, double x2, double y2)
/*  56:    */   {
/*  57: 92 */     Graphics2D g = drawingContext.getGraphics();
/*  58: 94 */     if ((GeometryUtils.equals(x1, x2)) || (GeometryUtils.equals(y1, y2))) {
/*  59: 96 */       return;
/*  60:    */     }
/*  61: 99 */     Stroke stroke_old = g.getStroke();
/*  62:100 */     Color color_old = g.getColor();
/*  63:    */     
/*  64:102 */     g.setColor(color);
/*  65:103 */     g.setStroke(stroke);
/*  66:    */     
/*  67:105 */     g.setXORMode(Color.white);
/*  68:    */     
/*  69:    */ 
/*  70:    */ 
/*  71:109 */     Point2I[] pA = new Point2I[4];
/*  72:110 */     int i = 0;
/*  73:    */     
/*  74:112 */     Rectangle2D_ slrect = drawingContext.rectangleToLogical(drawingContext.getBounds());
/*  75:    */     
/*  76:114 */     double[] pts = GeometryUtils.findLineAndLineSegmentIntersection(x1, y1, x2, y2, slrect.x, slrect.y, slrect.x + slrect.width, slrect.y);
/*  77:115 */     if (pts != null)
/*  78:    */     {
/*  79:116 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  80:117 */       i++;
/*  81:    */     }
/*  82:120 */     pts = GeometryUtils.findLineAndLineSegmentIntersection(x1, y1, x2, y2, slrect.x + slrect.width, slrect.y, slrect.x + slrect.width, slrect.y - slrect.height);
/*  83:121 */     if (pts != null)
/*  84:    */     {
/*  85:122 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  86:123 */       i++;
/*  87:    */     }
/*  88:126 */     pts = GeometryUtils.findLineAndLineSegmentIntersection(x1, y1, x2, y2, slrect.x + slrect.width, slrect.y - slrect.height, slrect.x, slrect.y - slrect.height);
/*  89:127 */     if (pts != null)
/*  90:    */     {
/*  91:128 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  92:129 */       i++;
/*  93:    */     }
/*  94:132 */     pts = GeometryUtils.findLineAndLineSegmentIntersection(x1, y1, x2, y2, slrect.x, slrect.y - slrect.height, slrect.x, slrect.y);
/*  95:133 */     if (pts != null)
/*  96:    */     {
/*  97:134 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  98:135 */       i++;
/*  99:    */     }
/* 100:138 */     if (i == 2) {
/* 101:139 */       drawingContext.getGraphics().drawLine(pA[0].x, pA[0].y, pA[1].x, pA[1].y);
/* 102:    */     }
/* 103:142 */     g.setPaintMode();
/* 104:143 */     g.setColor(color_old);
/* 105:144 */     g.setStroke(stroke_old);
/* 106:    */   }
/* 107:    */   
/* 108:    */   public static final void drawRectangleXor(DrawingContext drawingContext, Color color, Stroke stroke, double x1, double y1, double x2, double y2)
/* 109:    */   {
/* 110:149 */     Graphics2D big = drawingContext.getGraphics();
/* 111:    */     
/* 112:151 */     Stroke stroke_old = big.getStroke();
/* 113:152 */     Color color_old = big.getColor();
/* 114:    */     
/* 115:154 */     big.setColor(color);
/* 116:155 */     big.setStroke(stroke);
/* 117:    */     
/* 118:157 */     double x_min = Math.min(x1, x2);
/* 119:158 */     double y_min = Math.max(y1, y2);
/* 120:    */     
/* 121:160 */     big.setXORMode(Color.white);
/* 122:    */     
/* 123:162 */     Point2I p1 = drawingContext.translateToScreen(x_min, y_min);
/* 124:    */     
/* 125:164 */     int width = drawingContext.distanseToScreen(Math.abs(x2 - x1));
/* 126:165 */     int height = drawingContext.distanseToScreen(Math.abs(y2 - y1));
/* 127:    */     
/* 128:167 */     big.drawRect(p1.x, p1.y, width, height);
/* 129:    */     
/* 130:169 */     big.setPaintMode();
/* 131:    */     
/* 132:171 */     big.setColor(color_old);
/* 133:172 */     big.setStroke(stroke_old);
/* 134:    */   }
/* 135:    */   
/* 136:    */   public static final void drawOvalXor(DrawingContext drawingContext, Color color, Stroke stroke, double x1, double y1, double r)
/* 137:    */   {
/* 138:177 */     Graphics2D big = drawingContext.getGraphics();
/* 139:    */     
/* 140:179 */     Stroke stroke_old = big.getStroke();
/* 141:180 */     Color color_old = big.getColor();
/* 142:    */     
/* 143:182 */     big.setColor(color);
/* 144:183 */     big.setStroke(stroke);
/* 145:    */     
/* 146:185 */     big.setXORMode(Color.white);
/* 147:    */     
/* 148:187 */     int radius = drawingContext.distanseToScreen(r);
/* 149:188 */     Point2I p1 = drawingContext.translateToScreen(x1, y1);
/* 150:    */     
/* 151:190 */     big.drawOval(p1.x - radius, p1.y - radius, radius * 2, radius * 2);
/* 152:    */     
/* 153:192 */     big.setPaintMode();
/* 154:    */     
/* 155:194 */     big.setColor(color_old);
/* 156:195 */     big.setStroke(stroke_old);
/* 157:    */   }
/* 158:    */   
/* 159:    */   public static final void drawSnapPointXor(DrawingContext drawingContext, Color color, Stroke stroke, SnapPoint sp)
/* 160:    */   {
/* 161:199 */     Graphics2D big = drawingContext.getGraphics();
/* 162:    */     
/* 163:201 */     Point2D_ p = sp.getPoint();
/* 164:202 */     Point2I p1 = drawingContext.translateToScreen(p);
/* 165:    */     
/* 166:204 */     Stroke stroke_old = big.getStroke();
/* 167:205 */     Color color_old = big.getColor();
/* 168:    */     
/* 169:207 */     big.setXORMode(Color.white);
/* 170:208 */     big.setStroke(stroke);
/* 171:209 */     big.setColor(color);
/* 172:211 */     switch (sp.getPointType())
/* 173:    */     {
/* 174:    */     case 1: 
/* 175:213 */       big.drawRect(p1.x - 5, p1.y - 5, 10, 10);
/* 176:214 */       break;
/* 177:    */     case 2: 
/* 178:217 */       int[] px = new int[3];
/* 179:218 */       int[] py = new int[3];
/* 180:219 */       px[0] = p1.x;
/* 181:220 */       px[1] = (p1.x - 5);
/* 182:221 */       px[2] = (p1.x + 5);
/* 183:222 */       py[0] = (p1.y - 5);
/* 184:223 */       py[1] = (p1.y + 5);
/* 185:224 */       py[2] = (p1.y + 5);
/* 186:225 */       big.drawPolygon(px, py, 3);
/* 187:226 */       break;
/* 188:    */     case 4: 
/* 189:229 */       big.drawOval(p1.x - 6, p1.y - 6, 12, 12);
/* 190:    */     }
/* 191:233 */     big.setColor(color_old);
/* 192:234 */     big.setStroke(stroke_old);
/* 193:235 */     big.setPaintMode();
/* 194:    */   }
/* 195:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.DrawingUtils
 * JD-Core Version:    0.7.0.1
 */