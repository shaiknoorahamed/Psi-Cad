/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.awt.Graphics2D;
/*   4:    */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*   5:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   6:    */ 
/*   7:    */ public class Line
/*   8:    */   extends Shape
/*   9:    */ {
/*  10:    */   public double x1;
/*  11:    */   public double y1;
/*  12:    */   public double x2;
/*  13:    */   public double y2;
/*  14:    */   Context context;
/*  15:    */   
/*  16:    */   public Line() {}
/*  17:    */   
/*  18:    */   public Line(double x1, double y1, double x2, double y2, Context context)
/*  19:    */   {
/*  20: 50 */     this.context = context;
/*  21: 51 */     this.x1 = x1;
/*  22: 52 */     this.y1 = y1;
/*  23: 53 */     this.x2 = x2;
/*  24: 54 */     this.y2 = y2;
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void draw(Context context)
/*  28:    */   {
/*  29: 58 */     drawOptimized(context);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void drawOptimized(Context context)
/*  33:    */   {
/*  34: 62 */     DrawingContext drawingContext = context.getDrawingContext();
/*  35: 63 */     Graphics2D g = drawingContext.getGraphics();
/*  36:    */     
/*  37: 65 */     Point2I[] pA = new Point2I[4];
/*  38: 66 */     int i = 0;
/*  39:    */     
/*  40: 68 */     Rectangle2D_ slrect = drawingContext.rectangleToLogical(drawingContext.getBounds());
/*  41:    */     
/*  42: 70 */     double[] pts = GeometryUtils.findLineAndLineSegmentIntersection(this.x1, this.y1, this.x2, this.y2, slrect.x, slrect.y, slrect.x + slrect.width, slrect.y);
/*  43: 71 */     if (pts != null)
/*  44:    */     {
/*  45: 72 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  46: 73 */       i++;
/*  47:    */     }
/*  48: 76 */     pts = GeometryUtils.findLineAndLineSegmentIntersection(this.x1, this.y1, this.x2, this.y2, slrect.x + slrect.width, slrect.y, slrect.x + slrect.width, slrect.y - slrect.height);
/*  49: 77 */     if (pts != null)
/*  50:    */     {
/*  51: 78 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  52: 79 */       i++;
/*  53:    */     }
/*  54: 82 */     pts = GeometryUtils.findLineAndLineSegmentIntersection(this.x1, this.y1, this.x2, this.y2, slrect.x + slrect.width, slrect.y - slrect.height, slrect.x, slrect.y - slrect.height);
/*  55: 83 */     if (pts != null)
/*  56:    */     {
/*  57: 84 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  58: 85 */       i++;
/*  59:    */     }
/*  60: 88 */     pts = GeometryUtils.findLineAndLineSegmentIntersection(this.x1, this.y1, this.x2, this.y2, slrect.x, slrect.y - slrect.height, slrect.x, slrect.y);
/*  61: 89 */     if (pts != null)
/*  62:    */     {
/*  63: 90 */       pA[i] = drawingContext.translateToScreen(pts[0], pts[1]);
/*  64: 91 */       i++;
/*  65:    */     }
/*  66: 94 */     if (i == 2) {
/*  67: 95 */       drawingContext.getGraphics().drawLine(pA[0].x, pA[0].y, pA[1].x, pA[1].y);
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   public boolean containsPoint(double x, double y, double scale)
/*  72:    */   {
/*  73:100 */     if (GeometryUtils.isPointNearToLine(this.x1, this.y1, this.x2, this.y2, x, y, 5.0D * scale)) {
/*  74:101 */       return true;
/*  75:    */     }
/*  76:103 */     return false;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public Rectangle2D_ getBounds()
/*  80:    */   {
/*  81:107 */     return new Rectangle2D_((-1.0D / 0.0D), (1.0D / 0.0D), (1.0D / 0.0D), (1.0D / 0.0D));
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void move(double xV, double yV)
/*  85:    */   {
/*  86:111 */     this.x1 += xV;
/*  87:112 */     this.x2 += xV;
/*  88:113 */     this.y1 += yV;
/*  89:114 */     this.y2 += yV;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void rotate(double x, double y, double angle)
/*  93:    */   {
/*  94:120 */     double tmpX = x + (this.x1 - x) * Math.cos(angle) - (this.y1 - y) * Math.sin(angle);
/*  95:121 */     double tmpY = y + (this.x1 - x) * Math.sin(angle) + (this.y1 - y) * Math.cos(angle);
/*  96:122 */     this.x1 = tmpX;
/*  97:123 */     this.y1 = tmpY;
/*  98:124 */     tmpX = x + (this.x2 - x) * Math.cos(angle) - (this.y2 - y) * Math.sin(angle);
/*  99:125 */     tmpY = y + (this.x2 - x) * Math.sin(angle) + (this.y2 - y) * Math.cos(angle);
/* 100:126 */     this.x2 = tmpX;
/* 101:127 */     this.y2 = tmpY;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void tranclate(double x, double y, double xV, double yV) {}
/* 105:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Line
 * JD-Core Version:    0.7.0.1
 */