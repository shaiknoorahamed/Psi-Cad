/*   1:    */ package org.cadbox.j2d.scale;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collection;
/*   5:    */ import java.util.List;
/*   6:    */ import org.cadbox.j2d.scale.core.Circle;
/*   7:    */ import org.cadbox.j2d.scale.core.Context;
/*   8:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*   9:    */ import org.cadbox.j2d.scale.core.GeometryUtils;
/*  10:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  11:    */ 
/*  12:    */ public class CadCircle
/*  13:    */   extends Circle
/*  14:    */   implements CadShape
/*  15:    */ {
/*  16:    */   static final int TYPE_ACTION_MOVE = 1;
/*  17:    */   static final int TYPE_ACTION_RADIUS_CHANGE = 2;
/*  18:    */   ActionPoint actionPoint;
/*  19:    */   
/*  20:    */   public CadCircle() {}
/*  21:    */   
/*  22:    */   public CadCircle(double x, double y, double radius)
/*  23:    */   {
/*  24: 56 */     super(x, y, radius);
/*  25:    */   }
/*  26:    */   
/*  27:    */   public Collection getActionPoints()
/*  28:    */   {
/*  29: 60 */     List list = new ArrayList();
/*  30: 61 */     ActionPoint ap = new ActionPoint(new Point2D_(getX(), getY()), 1);
/*  31: 62 */     list.add(ap);
/*  32: 63 */     ap = new ActionPoint(new Point2D_(getX() + getRadius(), getY()), 2);
/*  33: 64 */     list.add(ap);
/*  34: 65 */     ap = new ActionPoint(new Point2D_(getX() - getRadius(), getY()), 2);
/*  35: 66 */     list.add(ap);
/*  36: 67 */     ap = new ActionPoint(new Point2D_(getX(), getY() + getRadius()), 2);
/*  37: 68 */     list.add(ap);
/*  38: 69 */     ap = new ActionPoint(new Point2D_(getX(), getY() - getRadius()), 2);
/*  39: 70 */     list.add(ap);
/*  40: 71 */     return list;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void postActionPoint(CadDrawingEngine engine, double xP, double yP)
/*  44:    */   {
/*  45: 76 */     if (this.actionPoint != null)
/*  46:    */     {
/*  47: 78 */       if (this.actionPoint.getActionType() == 1) {
/*  48: 79 */         engine.moveShape(this, xP - getX(), yP - getY());
/*  49:    */       }
/*  50: 82 */       if (this.actionPoint.getActionType() == 2)
/*  51:    */       {
/*  52: 83 */         CadCircle shape = (CadCircle)clone();
/*  53: 84 */         shape.setRadius(GeometryUtils.length(getX(), getY(), xP, yP));
/*  54: 85 */         engine.modifyShape(this, shape);
/*  55:    */       }
/*  56:    */     }
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setActionPoint(ActionPoint actionPoint)
/*  60:    */   {
/*  61: 92 */     this.actionPoint = actionPoint;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void moveActionPoint(CadDrawingEngine engine, Context context, double xV, double yV)
/*  65:    */   {
/*  66: 96 */     DrawingContext drawingContext = context.getDrawingContext();
/*  67: 98 */     if (this.actionPoint != null)
/*  68:    */     {
/*  69:100 */       if (this.actionPoint.getActionType() == 1)
/*  70:    */       {
/*  71:101 */         Point2D_ p = this.actionPoint.getPoint();
/*  72:102 */         DrawingUtils.drawOvalXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, getRadius());
/*  73:    */       }
/*  74:105 */       if (this.actionPoint.getActionType() == 2)
/*  75:    */       {
/*  76:106 */         Point2D_ p = this.actionPoint.getPoint();
/*  77:107 */         double new_radius = Math.sqrt((getX() - xV) * (getX() - xV) + (getY() - yV) * (getY() - yV));
/*  78:108 */         DrawingUtils.drawOvalXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), getX(), getY(), new_radius);
/*  79:    */       }
/*  80:    */     }
/*  81:    */   }
/*  82:    */   
/*  83:    */   public Collection getSnapPoints()
/*  84:    */   {
/*  85:114 */     List list = new ArrayList();
/*  86:    */     
/*  87:    */ 
/*  88:    */ 
/*  89:118 */     SnapPoint p = new SnapPoint(new Point2D_(getX(), getY()), 4);
/*  90:119 */     list.add(p);
/*  91:    */     
/*  92:121 */     return list;
/*  93:    */   }
/*  94:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.CadCircle
 * JD-Core Version:    0.7.0.1
 */