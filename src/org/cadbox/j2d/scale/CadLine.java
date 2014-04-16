/*   1:    */ package org.cadbox.j2d.scale;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collection;
/*   5:    */ import java.util.List;
/*   6:    */ import org.cadbox.j2d.scale.core.Context;
/*   7:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*   8:    */ import org.cadbox.j2d.scale.core.Line;
/*   9:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  10:    */ 
/*  11:    */ public class CadLine
/*  12:    */   extends Line
/*  13:    */   implements CadShape
/*  14:    */ {
/*  15: 46 */   private final int TYPE_ACTION_MOVE = 1;
/*  16: 47 */   private final int TYPE_ACTION_TAIL_MOVE = 2;
/*  17:    */   private ActionPoint actionPoint;
/*  18:    */   private Context context;
/*  19:    */   
/*  20:    */   public CadLine() {}
/*  21:    */   
/*  22:    */   public CadLine(double x1, double y1, double x2, double y2, Context context)
/*  23:    */   {
/*  24: 58 */     super(x1, y1, x2, y2, context);
/*  25: 59 */     this.context = context;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public Collection getActionPoints()
/*  29:    */   {
/*  30: 63 */     List list = new ArrayList();
/*  31: 64 */     list.add(new ActionPoint(new Point2D_(this.x1, this.y1), 2));
/*  32: 65 */     list.add(new ActionPoint(new Point2D_(this.x2, this.y2), 2));
/*  33: 66 */     list.add(new ActionPoint(new Point2D_((this.x1 + this.x2) / 2.0D, (this.y1 + this.y2) / 2.0D), 1));
/*  34: 67 */     return list;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void setActionPoint(ActionPoint actionPoint)
/*  38:    */   {
/*  39: 71 */     this.actionPoint = actionPoint;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void postActionPoint(CadDrawingEngine engine, double x, double y)
/*  43:    */   {
/*  44: 75 */     if (this.actionPoint != null)
/*  45:    */     {
/*  46: 77 */       Point2D_ p = this.actionPoint.getPoint();
/*  47: 79 */       if (this.actionPoint.getActionType() == 2)
/*  48:    */       {
/*  49: 81 */         if ((this.x1 == p.x) && (this.y1 == p.y))
/*  50:    */         {
/*  51: 82 */           CadLine shape = (CadLine)clone();
/*  52: 83 */           shape.x1 = x;
/*  53: 84 */           shape.y1 = y;
/*  54: 85 */           shape.x2 = (this.x1 + this.x2 - x);
/*  55: 86 */           shape.y2 = (this.y1 + this.y2 - y);
/*  56: 87 */           engine.modifyShape(this, shape);
/*  57:    */         }
/*  58: 90 */         if ((this.x2 == p.x) && (this.y2 == p.y))
/*  59:    */         {
/*  60: 91 */           CadLine shape = (CadLine)clone();
/*  61: 92 */           shape.x2 = x;
/*  62: 93 */           shape.y2 = y;
/*  63: 94 */           shape.x1 = (this.x1 + this.x2 - x);
/*  64: 95 */           shape.y1 = (this.y1 + this.y2 - y);
/*  65: 96 */           engine.modifyShape(this, shape);
/*  66:    */         }
/*  67:    */       }
/*  68:100 */       if (this.actionPoint.getActionType() == 1)
/*  69:    */       {
/*  70:101 */         double vX = x - p.x;
/*  71:102 */         double vY = y - p.y;
/*  72:103 */         engine.moveShape(this, vX, vY);
/*  73:    */       }
/*  74:    */     }
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void moveActionPoint(CadDrawingEngine engine, Context context, double xV, double yV)
/*  78:    */   {
/*  79:111 */     DrawingContext drawingContext = context.getDrawingContext();
/*  80:113 */     if (this.actionPoint != null)
/*  81:    */     {
/*  82:115 */       Point2D_ p = this.actionPoint.getPoint();
/*  83:117 */       if (this.actionPoint.getActionType() == 2)
/*  84:    */       {
/*  85:118 */         if ((this.x1 == p.x) && (this.y1 == p.y)) {
/*  86:119 */           DrawingUtils.drawStarngeLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, (this.x1 + this.x2) / 2.0D, (this.y1 + this.y2) / 2.0D);
/*  87:    */         }
/*  88:122 */         if ((this.x2 == p.x) && (this.y2 == p.y)) {
/*  89:123 */           DrawingUtils.drawStarngeLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, (this.x1 + this.x2) / 2.0D, (this.y1 + this.y2) / 2.0D);
/*  90:    */         }
/*  91:    */       }
/*  92:127 */       if (this.actionPoint.getActionType() == 1)
/*  93:    */       {
/*  94:128 */         double a = xV - p.x;
/*  95:129 */         double b = yV - p.y;
/*  96:130 */         Point2D_ p1 = new Point2D_(a + this.x1, b + this.y1);
/*  97:131 */         Point2D_ p2 = new Point2D_(a + this.x2, b + this.y2);
/*  98:132 */         DrawingUtils.drawStarngeLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), p1.x, p1.y, p2.x, p2.y);
/*  99:    */       }
/* 100:    */     }
/* 101:    */   }
/* 102:    */   
/* 103:    */   public Collection getSnapPoints()
/* 104:    */   {
/* 105:138 */     return new ArrayList();
/* 106:    */   }
/* 107:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.CadLine
 * JD-Core Version:    0.7.0.1
 */