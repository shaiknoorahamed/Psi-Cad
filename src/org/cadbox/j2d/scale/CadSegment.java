/*   1:    */ package org.cadbox.j2d.scale;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collection;
/*   5:    */ import java.util.List;
/*   6:    */ import org.cadbox.j2d.scale.core.Context;
/*   7:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*   8:    */ import org.cadbox.j2d.scale.core.Segment;
/*   9:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  10:    */ 
/*  11:    */ public class CadSegment
/*  12:    */   extends Segment
/*  13:    */   implements CadShape
/*  14:    */ {
/*  15: 45 */   private final int TYPE_ACTION_MOVE = 1;
/*  16: 46 */   private final int TYPE_ACTION_TAIL_MOVE = 2;
/*  17:    */   private ActionPoint actionPoint;
/*  18:    */   
/*  19:    */   public CadSegment() {}
/*  20:    */   
/*  21:    */   public CadSegment(double x1, double y1, double x2, double y2)
/*  22:    */   {
/*  23: 55 */     super(x1, y1, x2, y2);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public Collection getActionPoints()
/*  27:    */   {
/*  28: 59 */     List list = new ArrayList();
/*  29: 60 */     list.add(new ActionPoint(new Point2D_(this.x1, this.y1), 2));
/*  30: 61 */     list.add(new ActionPoint(new Point2D_(this.x2, this.y2), 2));
/*  31: 62 */     list.add(new ActionPoint(new Point2D_((this.x1 + this.x2) / 2.0D, (this.y1 + this.y2) / 2.0D), 1));
/*  32: 63 */     return list;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void setActionPoint(ActionPoint actionPoint)
/*  36:    */   {
/*  37: 68 */     this.actionPoint = actionPoint;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void postActionPoint(CadDrawingEngine engine, double x, double y)
/*  41:    */   {
/*  42: 72 */     if (this.actionPoint != null)
/*  43:    */     {
/*  44: 74 */       Point2D_ p = this.actionPoint.getPoint();
/*  45: 76 */       if (this.actionPoint.getActionType() == 2)
/*  46:    */       {
/*  47: 77 */         if ((this.x1 == p.x) && (this.y1 == p.y))
/*  48:    */         {
/*  49: 78 */           CadSegment shape = (CadSegment)clone();
/*  50: 79 */           shape.x1 = x;
/*  51: 80 */           shape.y1 = y;
/*  52: 81 */           engine.modifyShape(this, shape);
/*  53:    */         }
/*  54: 83 */         if ((this.x2 == p.x) && (this.y2 == p.y))
/*  55:    */         {
/*  56: 84 */           CadSegment shape = (CadSegment)clone();
/*  57: 85 */           shape.x2 = x;
/*  58: 86 */           shape.y2 = y;
/*  59: 87 */           engine.modifyShape(this, shape);
/*  60:    */         }
/*  61:    */       }
/*  62: 91 */       if (this.actionPoint.getActionType() == 1)
/*  63:    */       {
/*  64: 92 */         double vX = x - p.x;
/*  65: 93 */         double vY = y - p.y;
/*  66: 94 */         engine.moveShape(this, vX, vY);
/*  67:    */       }
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void moveActionPoint(CadDrawingEngine engine, Context context, double xV, double yV)
/*  72:    */   {
/*  73:101 */     DrawingContext drawingContext = context.getDrawingContext();
/*  74:103 */     if (this.actionPoint != null)
/*  75:    */     {
/*  76:105 */       Point2D_ p = this.actionPoint.getPoint();
/*  77:107 */       if (this.actionPoint.getActionType() == 2)
/*  78:    */       {
/*  79:108 */         if ((this.x1 == p.x) && (this.y1 == p.y)) {
/*  80:109 */           DrawingUtils.drawLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, this.x2, this.y2);
/*  81:    */         }
/*  82:112 */         if ((this.x2 == p.x) && (this.y2 == p.y)) {
/*  83:113 */           DrawingUtils.drawLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, this.x1, this.y1);
/*  84:    */         }
/*  85:    */       }
/*  86:117 */       if (this.actionPoint.getActionType() == 1)
/*  87:    */       {
/*  88:118 */         double a = xV - p.x;
/*  89:119 */         double b = yV - p.y;
/*  90:120 */         Point2D_ p1 = new Point2D_(a + this.x1, b + this.y1);
/*  91:121 */         Point2D_ p2 = new Point2D_(a + this.x2, b + this.y2);
/*  92:122 */         DrawingUtils.drawLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), p1.x, p1.y, p2.x, p2.y);
/*  93:    */       }
/*  94:    */     }
/*  95:    */   }
/*  96:    */   
/*  97:    */   public Collection getSnapPoints()
/*  98:    */   {
/*  99:128 */     List list = new ArrayList();
/* 100:    */     
/* 101:    */ 
/* 102:    */ 
/* 103:132 */     SnapPoint p = new SnapPoint(new Point2D_(this.x1, this.y1), 1);
/* 104:133 */     list.add(p);
/* 105:    */     
/* 106:135 */     p = new SnapPoint(new Point2D_(this.x2, this.y2), 1);
/* 107:136 */     list.add(p);
/* 108:    */     
/* 109:138 */     p = new SnapPoint(new Point2D_((this.x1 + this.x2) / 2.0D, (this.y1 + this.y2) / 2.0D), 2);
/* 110:139 */     list.add(p);
/* 111:    */     
/* 112:141 */     return list;
/* 113:    */   }
/* 114:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.CadSegment
 * JD-Core Version:    0.7.0.1
 */