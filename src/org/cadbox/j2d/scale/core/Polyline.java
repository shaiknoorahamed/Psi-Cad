/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import java.awt.Graphics2D;
/*  4:   */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*  5:   */ 
/*  6:   */ public class Polyline
/*  7:   */   extends MultiPointShape
/*  8:   */ {
/*  9:   */   public Polyline() {}
/* 10:   */   
/* 11:   */   public Polyline(double[] xP, double[] yP, int size)
/* 12:   */   {
/* 13:47 */     super(xP, yP, size);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void draw(Context context)
/* 17:   */   {
/* 18:51 */     DrawingContext drawingContext = context.getDrawingContext();
/* 19:   */     
/* 20:53 */     int size = getSize();
/* 21:54 */     double[] xP = getVectorX();
/* 22:55 */     double[] yP = getVectorY();
/* 23:   */     
/* 24:57 */     int[] xPTmp = DrawingContext.getXBuffer();
/* 25:58 */     int[] yPTmp = DrawingContext.getYBuffer();
/* 26:60 */     for (int i = 0; i < size; i++)
/* 27:   */     {
/* 28:61 */       Point2I p_tmp = drawingContext.translateToScreen(xP[i], yP[i]);
/* 29:62 */       xPTmp[i] = p_tmp.x;
/* 30:63 */       yPTmp[i] = p_tmp.y;
/* 31:   */     }
/* 32:65 */     drawingContext.getGraphics().drawPolyline(xPTmp, yPTmp, size);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void drawOptimized(Context context)
/* 36:   */   {
/* 37:69 */     draw(context);
/* 38:   */   }
/* 39:   */   
/* 40:   */   public boolean containsPoint(double x, double y, double scale)
/* 41:   */   {
/* 42:73 */     int size = getSize();
/* 43:74 */     double[] xP = getVectorX();
/* 44:75 */     double[] yP = getVectorY();
/* 45:78 */     for (int i = 0; i < size - 1; i++)
/* 46:   */     {
/* 47:79 */       double x1 = xP[i];
/* 48:80 */       double y1 = yP[i];
/* 49:81 */       double x2 = xP[(i + 1)];
/* 50:82 */       double y2 = yP[(i + 1)];
/* 51:83 */       if (GeometryUtils.isPointNearToLineSegment(x1, y1, x2, y2, x, y, 2.0D * scale)) {
/* 52:84 */         return true;
/* 53:   */       }
/* 54:   */     }
/* 55:86 */     return false;
/* 56:   */   }
/* 57:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Polyline
 * JD-Core Version:    0.7.0.1
 */