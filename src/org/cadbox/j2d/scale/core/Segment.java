/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.awt.Graphics2D;
/*   4:    */ import java.awt.Rectangle;
/*   5:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*   6:    */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*   7:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   8:    */ 
/*   9:    */ public class Segment
/*  10:    */   extends Shape
/*  11:    */ {
/*  12:    */   public double x1;
/*  13:    */   public double y1;
/*  14:    */   public double x2;
/*  15:    */   public double y2;
/*  16:    */   
/*  17:    */   public Segment() {}
/*  18:    */   
/*  19:    */   public Segment(double x1, double y1, double x2, double y2)
/*  20:    */   {
/*  21: 52 */     this.x1 = x1;
/*  22: 53 */     this.y1 = y1;
/*  23: 54 */     this.x2 = x2;
/*  24: 55 */     this.y2 = y2;
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void draw(Context context)
/*  28:    */   {
/*  29: 59 */     DrawingContext drawingContext = context.getDrawingContext();
/*  30: 60 */     Point2I p1 = drawingContext.translateToScreen(this.x1, this.y1);
/*  31: 61 */     Point2I p2 = drawingContext.translateToScreen(this.x2, this.y2);
/*  32: 62 */     drawingContext.getGraphics().drawLine(p1.x, p1.y, p2.x, p2.y);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void drawOptimized(Context context)
/*  36:    */   {
/*  37: 67 */     DrawingContext drawingContext = context.getDrawingContext();
/*  38: 68 */     Graphics2D g = drawingContext.getGraphics();
/*  39:    */     
/*  40: 70 */     Point2I[] pA = new Point2I[4];
/*  41: 71 */     int i = 0;
/*  42:    */     
/*  43: 73 */     Point2I p1 = drawingContext.translateToScreen(this.x1, this.y1);
/*  44: 74 */     Point2I p2 = drawingContext.translateToScreen(this.x2, this.y2);
/*  45:    */     
/*  46: 76 */     Point2D_ pp1 = drawingContext.translateToLogical(0, 0);
/*  47: 77 */     Point2D_ pp2 = drawingContext.translateToLogical(drawingContext.getWidth(), 0);
/*  48: 78 */     Point2D_ ptmp = getIntersaction(pp1, pp2, new Point2D_(this.x1, this.y1), new Point2D_(this.x2, this.y2));
/*  49: 79 */     if (ptmp != null)
/*  50:    */     {
/*  51: 80 */       pA[i] = drawingContext.translateToScreen(ptmp);
/*  52: 81 */       i++;
/*  53:    */     }
/*  54: 84 */     pp1 = drawingContext.translateToLogical(drawingContext.getWidth(), 0);
/*  55: 85 */     pp2 = drawingContext.translateToLogical(drawingContext.getWidth(), drawingContext.getHeight());
/*  56: 86 */     ptmp = getIntersaction(pp1, pp2, new Point2D_(this.x1, this.y1), new Point2D_(this.x2, this.y2));
/*  57: 87 */     if (ptmp != null)
/*  58:    */     {
/*  59: 88 */       pA[i] = drawingContext.translateToScreen(ptmp);
/*  60: 89 */       i++;
/*  61:    */     }
/*  62: 92 */     pp1 = drawingContext.translateToLogical(drawingContext.getWidth(), drawingContext.getHeight());
/*  63: 93 */     pp2 = drawingContext.translateToLogical(0, drawingContext.getHeight());
/*  64: 94 */     ptmp = getIntersaction(pp1, pp2, new Point2D_(this.x1, this.y1), new Point2D_(this.x2, this.y2));
/*  65: 95 */     if (ptmp != null)
/*  66:    */     {
/*  67: 96 */       pA[i] = drawingContext.translateToScreen(ptmp);
/*  68: 97 */       i++;
/*  69:    */     }
/*  70:100 */     pp1 = drawingContext.translateToLogical(0, drawingContext.getHeight());
/*  71:101 */     pp2 = drawingContext.translateToLogical(0, 0);
/*  72:102 */     ptmp = getIntersaction(pp1, pp2, new Point2D_(this.x1, this.y1), new Point2D_(this.x2, this.y2));
/*  73:103 */     if (ptmp != null)
/*  74:    */     {
/*  75:104 */       pA[i] = drawingContext.translateToScreen(ptmp);
/*  76:105 */       i++;
/*  77:    */     }
/*  78:108 */     if (i == 0)
/*  79:    */     {
/*  80:109 */       if ((drawingContext.getBounds().contains(p1)) && (drawingContext.getBounds().contains(p2))) {
/*  81:110 */         drawingContext.getGraphics().drawLine(p1.x, p1.y, p2.x, p2.y);
/*  82:    */       }
/*  83:    */     }
/*  84:112 */     else if (i == 1)
/*  85:    */     {
/*  86:113 */       if (drawingContext.getBounds().contains(p1)) {
/*  87:114 */         drawingContext.getGraphics().drawLine(p1.x, p1.y, pA[0].x, pA[0].y);
/*  88:    */       } else {
/*  89:116 */         drawingContext.getGraphics().drawLine(p2.x, p2.y, pA[0].x, pA[0].y);
/*  90:    */       }
/*  91:    */     }
/*  92:118 */     else if (i == 2) {
/*  93:119 */       drawingContext.getGraphics().drawLine(pA[0].x, pA[0].y, pA[1].x, pA[1].y);
/*  94:    */     }
/*  95:    */   }
/*  96:    */   
/*  97:    */   private Point2D_ getIntersaction(Point2D_ p1_1, Point2D_ p1_2, Point2D_ p2_1, Point2D_ p2_2)
/*  98:    */   {
/*  99:126 */     double[] rez = new double[2];
/* 100:127 */     int i = GeometryUtils.findLineSegmentIntersection(p1_1.x, p1_1.y, p1_2.x, p1_2.y, p2_1.x, p2_1.y, p2_2.x, p2_2.y, rez);
/* 101:128 */     if (i == 1) {
/* 102:129 */       return new Point2D_(rez[0], rez[1]);
/* 103:    */     }
/* 104:131 */     return null;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public boolean containsPoint(double x, double y, double scale)
/* 108:    */   {
/* 109:135 */     if (GeometryUtils.isPointNearToLineSegment(this.x1, this.y1, this.x2, this.y2, x, y, 5.0D * scale)) {
/* 110:136 */       return true;
/* 111:    */     }
/* 112:138 */     return false;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public Rectangle2D_ getBounds()
/* 116:    */   {
/* 117:142 */     return new Rectangle2D_(Math.min(this.x1, this.x2), Math.max(this.y1, this.y2), Math.abs(this.x1 - this.x2), Math.abs(this.y1 - this.y2));
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void move(double xV, double yV)
/* 121:    */   {
/* 122:146 */     this.x1 += xV;
/* 123:147 */     this.x2 += xV;
/* 124:148 */     this.y1 += yV;
/* 125:149 */     this.y2 += yV;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void rotate(double x, double y, double angle)
/* 129:    */   {
/* 130:155 */     double tmpX = x + (this.x1 - x) * Math.cos(angle) - (this.y1 - y) * Math.sin(angle);
/* 131:156 */     double tmpY = y + (this.x1 - x) * Math.sin(angle) + (this.y1 - y) * Math.cos(angle);
/* 132:157 */     this.x1 = tmpX;
/* 133:158 */     this.y1 = tmpY;
/* 134:159 */     tmpX = x + (this.x2 - x) * Math.cos(angle) - (this.y2 - y) * Math.sin(angle);
/* 135:160 */     tmpY = y + (this.x2 - x) * Math.sin(angle) + (this.y2 - y) * Math.cos(angle);
/* 136:161 */     this.x2 = tmpX;
/* 137:162 */     this.y2 = tmpY;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void tranclate(double x, double y, double xV, double yV) {}
/* 141:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Segment
 * JD-Core Version:    0.7.0.1
 */