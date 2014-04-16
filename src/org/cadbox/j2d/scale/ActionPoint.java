/*  1:   */ package org.cadbox.j2d.scale;
/*  2:   */ 
/*  3:   */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  4:   */ 
/*  5:   */ public class ActionPoint
/*  6:   */   implements Cloneable
/*  7:   */ {
/*  8:   */   private Point2D_ point;
/*  9:   */   private int actionType;
/* 10:   */   
/* 11:   */   private ActionPoint() {}
/* 12:   */   
/* 13:   */   public ActionPoint(Point2D_ point, int actionType)
/* 14:   */   {
/* 15:49 */     this.point = point;
/* 16:50 */     this.actionType = actionType;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setPoint(Point2D_ point)
/* 20:   */   {
/* 21:54 */     this.point = point;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public Point2D_ getPoint()
/* 25:   */   {
/* 26:58 */     return this.point;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setActionType(int actionType)
/* 30:   */   {
/* 31:62 */     this.actionType = actionType;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int getActionType()
/* 35:   */   {
/* 36:66 */     return this.actionType;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public boolean equals(Object obj)
/* 40:   */   {
/* 41:70 */     if ((obj instanceof ActionPoint))
/* 42:   */     {
/* 43:71 */       ActionPoint ap = (ActionPoint)obj;
/* 44:72 */       return (this.point.equals(ap.point)) && (this.actionType == ap.actionType);
/* 45:   */     }
/* 46:74 */     return false;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public Object clone()
/* 50:   */   {
/* 51:   */     try
/* 52:   */     {
/* 53:79 */       return super.clone();
/* 54:   */     }
/* 55:   */     catch (CloneNotSupportedException e)
/* 56:   */     {
/* 57:82 */       throw new InternalError();
/* 58:   */     }
/* 59:   */   }
/* 60:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.ActionPoint
 * JD-Core Version:    0.7.0.1
 */