/*   1:    */ package org.cadbox.j2d.scale;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collection;
/*   5:    */ import java.util.List;
/*   6:    */ import org.cadbox.j2d.scale.core.Context;
/*   7:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*   8:    */ import org.cadbox.j2d.scale.core.Polyline;
/*   9:    */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*  10:    */ 
/*  11:    */ public class CadPolyline
/*  12:    */   extends Polyline
/*  13:    */   implements CadShape
/*  14:    */ {
/*  15: 45 */   private final int TYPE_ACTION_MOVE = 1;
/*  16:    */   private ActionPoint actionPoint;
/*  17:    */   
/*  18:    */   public CadPolyline() {}
/*  19:    */   
/*  20:    */   public CadPolyline(double[] xP, double[] yP, int size)
/*  21:    */   {
/*  22: 54 */     super(xP, yP, size);
/*  23:    */   }
/*  24:    */   
/*  25:    */   public Collection getActionPoints()
/*  26:    */   {
/*  27: 58 */     int size = getSize();
/*  28: 59 */     double[] xP = getVectorX();
/*  29: 60 */     double[] yP = getVectorY();
/*  30: 61 */     List list = new ArrayList();
/*  31: 62 */     for (int i = 0; i < size; i++)
/*  32:    */     {
/*  33: 63 */       Point2D_ p = new Point2D_(xP[i], yP[i]);
/*  34: 64 */       ActionPoint ap = new ActionPoint(p, 1);
/*  35: 65 */       list.add(ap);
/*  36:    */     }
/*  37: 67 */     return list;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void setActionPoint(ActionPoint actionPoint)
/*  41:    */   {
/*  42: 72 */     this.actionPoint = actionPoint;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void postActionPoint(CadDrawingEngine engine, double x, double y)
/*  46:    */   {
/*  47: 76 */     double[] xP = getVectorX();
/*  48: 77 */     double[] yP = getVectorY();
/*  49: 79 */     if (this.actionPoint != null)
/*  50:    */     {
/*  51: 80 */       if (this.actionPoint.getActionType() == 1)
/*  52:    */       {
/*  53: 81 */         Point2D_ p = this.actionPoint.getPoint();
/*  54: 82 */         for (int i = 0; i < xP.length; i++) {
/*  55: 83 */           if ((xP[i] == p.x) && (yP[i] == p.y))
/*  56:    */           {
/*  57: 85 */             CadPolyline shape = (CadPolyline)clone();
/*  58: 86 */             shape.xP[i] = x;
/*  59: 87 */             shape.yP[i] = y;
/*  60:    */             
/*  61: 89 */             engine.modifyShape(this, shape);
/*  62:    */             
/*  63: 91 */             break;
/*  64:    */           }
/*  65:    */         }
/*  66:    */       }
/*  67: 95 */       findBounds();
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void moveActionPoint(CadDrawingEngine engine, Context context, double xV, double yV)
/*  72:    */   {
/*  73:101 */     DrawingContext drawingContext = context.getDrawingContext();
/*  74:103 */     if ((this.actionPoint != null) && 
/*  75:104 */       (this.actionPoint.getActionType() == 1))
/*  76:    */     {
/*  77:106 */       Point2D_ pl_tmp = null;
/*  78:107 */       Point2D_ pn_tmp = null;
/*  79:108 */       boolean b = false;
/*  80:    */       
/*  81:110 */       List list = new ArrayList(getActionPoints());
/*  82:111 */       int size = list.size();
/*  83:113 */       for (int i = 0; i < size; i++)
/*  84:    */       {
/*  85:114 */         ActionPoint ap_tmp = (ActionPoint)list.get(i);
/*  86:115 */         if (this.actionPoint.equals(ap_tmp))
/*  87:    */         {
/*  88:116 */           if ((i == 0) && (size > 1))
/*  89:    */           {
/*  90:117 */             pn_tmp = ((ActionPoint)list.get(1)).getPoint();
/*  91:118 */             b = true;
/*  92:119 */             break;
/*  93:    */           }
/*  94:120 */           if ((i == size - 1) && (size > 1))
/*  95:    */           {
/*  96:121 */             pn_tmp = ((ActionPoint)list.get(i - 1)).getPoint();
/*  97:122 */             b = true;
/*  98:123 */             break;
/*  99:    */           }
/* 100:124 */           pl_tmp = ((ActionPoint)list.get(i - 1)).getPoint();
/* 101:125 */           pn_tmp = ((ActionPoint)list.get(i + 1)).getPoint();
/* 102:126 */           b = false;
/* 103:    */           
/* 104:128 */           break;
/* 105:    */         }
/* 106:    */       }
/* 107:131 */       if (b)
/* 108:    */       {
/* 109:132 */         DrawingUtils.drawLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, pn_tmp.x, pn_tmp.y);
/* 110:    */       }
/* 111:    */       else
/* 112:    */       {
/* 113:134 */         DrawingUtils.drawLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, pl_tmp.x, pl_tmp.y);
/* 114:135 */         DrawingUtils.drawLineXor(drawingContext, engine.getShapeDrawingColor(), engine.getShapeDrawingStroke(), xV, yV, pn_tmp.x, pn_tmp.y);
/* 115:    */       }
/* 116:    */     }
/* 117:    */   }
/* 118:    */   
/* 119:    */   public Collection getSnapPoints()
/* 120:    */   {
/* 121:142 */     List list = new ArrayList();
/* 122:    */     
/* 123:    */ 
/* 124:    */ 
/* 125:146 */     int size = getSize();
/* 126:147 */     double[] xP = getVectorX();
/* 127:148 */     double[] yP = getVectorY();
/* 128:150 */     for (int i = 0; i < size; i++)
/* 129:    */     {
/* 130:151 */       Point2D_ p = new Point2D_(xP[i], yP[i]);
/* 131:152 */       SnapPoint sp = new SnapPoint(p, 1);
/* 132:153 */       list.add(sp);
/* 133:    */     }
/* 134:156 */     for (int i = 0; i < size - 1; i++)
/* 135:    */     {
/* 136:157 */       Point2D_ p1 = new Point2D_(xP[i], yP[i]);
/* 137:158 */       Point2D_ p2 = new Point2D_(xP[(i + 1)], yP[(i + 1)]);
/* 138:159 */       Point2D_ p = new Point2D_((p1.x + p2.x) / 2.0D, (p1.y + p2.y) / 2.0D);
/* 139:160 */       SnapPoint sp = new SnapPoint(p, 2);
/* 140:161 */       list.add(sp);
/* 141:    */     }
/* 142:164 */     return list;
/* 143:    */   }
/* 144:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.CadPolyline
 * JD-Core Version:    0.7.0.1
 */