/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collection;
/*   5:    */ import java.util.List;
/*   6:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   7:    */ 
/*   8:    */ public class Group
/*   9:    */   extends Shape
/*  10:    */ {
/*  11: 43 */   String name = "Group";
/*  12: 44 */   List shapeList = new ArrayList();
/*  13:    */   
/*  14:    */   public Group()
/*  15:    */   {
/*  16: 47 */     this("");
/*  17:    */   }
/*  18:    */   
/*  19:    */   public Group(String name)
/*  20:    */   {
/*  21: 51 */     this.name = name;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void draw(Context context)
/*  25:    */   {
/*  26: 55 */     for (int i = 0; i < this.shapeList.size(); i++)
/*  27:    */     {
/*  28: 56 */       Shape shape = (Shape)this.shapeList.get(i);
/*  29: 57 */       shape.draw(context);
/*  30:    */     }
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void drawOptimized(Context context)
/*  34:    */   {
/*  35: 62 */     for (int i = 0; i < this.shapeList.size(); i++)
/*  36:    */     {
/*  37: 63 */       Shape shape = (Shape)this.shapeList.get(i);
/*  38: 64 */       shape.drawOptimized(context);
/*  39:    */     }
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void addShape(Shape shape)
/*  43:    */   {
/*  44: 69 */     if (shape.group != null) {
/*  45: 70 */       shape.group.removeShape(shape);
/*  46:    */     }
/*  47: 72 */     shape.group = this;
/*  48: 73 */     this.shapeList.add(shape);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void removeShape(Shape shape)
/*  52:    */   {
/*  53: 77 */     shape.group = null;
/*  54: 78 */     this.shapeList.remove(shape);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public Collection getShapes()
/*  58:    */   {
/*  59: 82 */     return this.shapeList;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public int getShapeCount()
/*  63:    */   {
/*  64: 86 */     return this.shapeList.size();
/*  65:    */   }
/*  66:    */   
/*  67:    */   public boolean containShape(Shape shape)
/*  68:    */   {
/*  69: 90 */     return this.shapeList.contains(shape);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void removeAllShape()
/*  73:    */   {
/*  74: 94 */     this.shapeList.clear();
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void setName(String name)
/*  78:    */   {
/*  79: 98 */     this.name = name;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public String getName()
/*  83:    */   {
/*  84:102 */     return this.name;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public String toString()
/*  88:    */   {
/*  89:106 */     return this.name;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Rectangle2D_ getBounds()
/*  93:    */   {
/*  94:110 */     Rectangle2D_ res = new Rectangle2D_();
/*  95:111 */     for (int i = 0; i < this.shapeList.size(); i++)
/*  96:    */     {
/*  97:112 */       Shape shape = (Shape)this.shapeList.get(i);
/*  98:113 */       res.add(res);
/*  99:    */     }
/* 100:115 */     return res;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean containsPoint(double x, double y, double scale)
/* 104:    */   {
/* 105:119 */     for (int i = 0; i < this.shapeList.size(); i++)
/* 106:    */     {
/* 107:120 */       Shape shape = (Shape)this.shapeList.get(i);
/* 108:121 */       if (shape.containsPoint(x, y, scale)) {
/* 109:122 */         return true;
/* 110:    */       }
/* 111:    */     }
/* 112:124 */     return false;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void move(double xV, double yV)
/* 116:    */   {
/* 117:128 */     for (int i = 0; i < this.shapeList.size(); i++)
/* 118:    */     {
/* 119:129 */       Shape shape = (Shape)this.shapeList.get(i);
/* 120:130 */       shape.move(xV, yV);
/* 121:    */     }
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void rotate(double x, double y, double radius)
/* 125:    */   {
/* 126:135 */     for (int i = 0; i < this.shapeList.size(); i++)
/* 127:    */     {
/* 128:136 */       Shape shape = (Shape)this.shapeList.get(i);
/* 129:137 */       shape.rotate(x, y, radius);
/* 130:    */     }
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void tranclate(double x, double y, double xV, double yV)
/* 134:    */   {
/* 135:142 */     for (int i = 0; i < this.shapeList.size(); i++)
/* 136:    */     {
/* 137:143 */       Shape shape = (Shape)this.shapeList.get(i);
/* 138:144 */       shape.tranclate(x, y, xV, yV);
/* 139:    */     }
/* 140:    */   }
/* 141:    */   
/* 142:    */   public Object clone()
/* 143:    */   {
/* 144:149 */     Group group = (Group)super.clone();
/* 145:150 */     group.shapeList = new ArrayList();
/* 146:151 */     for (int i = 0; i < this.shapeList.size(); i++) {
/* 147:152 */       group.shapeList.add(((Shape)this.shapeList.get(i)).clone());
/* 148:    */     }
/* 149:153 */     return group;
/* 150:    */   }
/* 151:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Group
 * JD-Core Version:    0.7.0.1
 */