/*  1:   */ package org.cadbox.j2d.scale;
/*  2:   */ 
/*  3:   */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  4:   */ 
/*  5:   */ public class SnapPoint
/*  6:   */ {
/*  7:   */   public static final int END_SNAP_POINT = 1;
/*  8:   */   public static final int MIDDLE_SNAP_POINT = 2;
/*  9:   */   public static final int PERPENDICULAR_SNAP_POINT = 3;
/* 10:   */   public static final int CENTER_SNAP_POINT = 4;
/* 11:   */   private Point2D_ point;
/* 12:   */   private int pointType;
/* 13:   */   
/* 14:   */   public SnapPoint(Point2D_ point, int pointType)
/* 15:   */   {
/* 16:52 */     validatePointType(pointType);
/* 17:   */     
/* 18:54 */     this.point = point;
/* 19:55 */     this.pointType = pointType;
/* 20:   */   }
/* 21:   */   
/* 22:   */   private void validatePointType(int pointType)
/* 23:   */   {
/* 24:59 */     switch (pointType)
/* 25:   */     {
/* 26:   */     case 1: 
/* 27:   */     case 2: 
/* 28:   */     case 3: 
/* 29:   */     case 4: 
/* 30:   */       break;
/* 31:   */     default: 
/* 32:66 */       throw new IllegalArgumentException("SnapPoint: use kwnown point type!!!");
/* 33:   */     }
/* 34:   */   }
/* 35:   */   
/* 36:   */   public Point2D_ getPoint()
/* 37:   */   {
/* 38:71 */     return this.point;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public int getPointType()
/* 42:   */   {
/* 43:75 */     return this.pointType;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public boolean equals(Object obj)
/* 47:   */   {
/* 48:79 */     if ((obj instanceof SnapPoint))
/* 49:   */     {
/* 50:80 */       SnapPoint sp = (SnapPoint)obj;
/* 51:81 */       return (this.pointType == sp.getPointType()) && (this.point.equals(sp.getPoint()));
/* 52:   */     }
/* 53:83 */     return false;
/* 54:   */   }
/* 55:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.SnapPoint
 * JD-Core Version:    0.7.0.1
 */