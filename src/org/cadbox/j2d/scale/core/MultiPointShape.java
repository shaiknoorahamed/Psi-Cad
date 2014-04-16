/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   4:    */ 
/*   5:    */ public abstract class MultiPointShape
/*   6:    */   extends Shape
/*   7:    */ {
/*   8:    */   public int size;
/*   9:    */   public double[] xP;
/*  10:    */   public double[] yP;
/*  11:    */   Rectangle2D_ bounds;
/*  12:    */   
/*  13:    */   public MultiPointShape() {}
/*  14:    */   
/*  15:    */   public MultiPointShape(double[] xP, double[] yP, int size)
/*  16:    */   {
/*  17: 54 */     this.size = size;
/*  18: 55 */     this.xP = new double[size];
/*  19: 56 */     this.yP = new double[size];
/*  20: 57 */     System.arraycopy(xP, 0, this.xP, 0, size);
/*  21: 58 */     System.arraycopy(yP, 0, this.yP, 0, size);
/*  22:    */   }
/*  23:    */   
/*  24:    */   protected Rectangle2D_ findBounds()
/*  25:    */   {
/*  26: 62 */     double x_min = (1.0D / 0.0D);
/*  27: 63 */     double y_min = (1.0D / 0.0D);
/*  28: 64 */     double x_max = (-1.0D / 0.0D);
/*  29: 65 */     double y_max = (-1.0D / 0.0D);
/*  30: 66 */     for (int i = 0; i < this.size; i++)
/*  31:    */     {
/*  32: 67 */       if (this.xP[i] < x_min) {
/*  33: 68 */         x_min = this.xP[i];
/*  34:    */       }
/*  35: 69 */       if (this.xP[i] > x_max) {
/*  36: 70 */         x_max = this.xP[i];
/*  37:    */       }
/*  38: 71 */       if (this.yP[i] < y_min) {
/*  39: 72 */         y_min = this.yP[i];
/*  40:    */       }
/*  41: 73 */       if (this.yP[i] > y_max) {
/*  42: 74 */         y_max = this.yP[i];
/*  43:    */       }
/*  44:    */     }
/*  45: 76 */     return new Rectangle2D_(x_min, y_max, Math.abs(x_max - x_min), Math.abs(y_max - y_min));
/*  46:    */   }
/*  47:    */   
/*  48:    */   public Rectangle2D_ getBounds()
/*  49:    */   {
/*  50: 80 */     if (this.bounds == null) {
/*  51: 81 */       this.bounds = findBounds();
/*  52:    */     }
/*  53: 82 */     return this.bounds;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public double[] getVectorX()
/*  57:    */   {
/*  58: 86 */     return this.xP;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public double[] getVectorY()
/*  62:    */   {
/*  63: 90 */     return this.yP;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public int getSize()
/*  67:    */   {
/*  68: 94 */     return this.size;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void move(double xV, double yV)
/*  72:    */   {
/*  73: 98 */     for (int i = 0; i < this.size; i++)
/*  74:    */     {
/*  75: 99 */       this.xP[i] += xV;
/*  76:100 */       this.yP[i] += yV;
/*  77:    */     }
/*  78:102 */     findBounds();
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void rotate(double x, double y, double angle)
/*  82:    */   {
/*  83:106 */     for (int i = 0; i < this.size; i++)
/*  84:    */     {
/*  85:108 */       double tmpX = x + (this.xP[i] - x) * Math.cos(angle) - (this.yP[i] - y) * Math.sin(angle);
/*  86:109 */       double tmpY = y + (this.xP[i] - x) * Math.sin(angle) + (this.yP[i] - y) * Math.cos(angle);
/*  87:    */       
/*  88:111 */       this.xP[i] = tmpX;
/*  89:112 */       this.yP[i] = tmpY;
/*  90:    */     }
/*  91:114 */     findBounds();
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void tranclate(double x, double y, double xV, double yV)
/*  95:    */   {
/*  96:118 */     findBounds();
/*  97:    */   }
/*  98:    */   
/*  99:    */   public Object clone()
/* 100:    */   {
/* 101:122 */     MultiPointShape mps = (MultiPointShape)super.clone();
/* 102:123 */     if (this.bounds != null) {
/* 103:124 */       mps.bounds = ((Rectangle2D_)this.bounds.clone());
/* 104:    */     }
/* 105:125 */     mps.xP = ((double[])this.xP.clone());
/* 106:126 */     mps.yP = ((double[])this.yP.clone());
/* 107:127 */     return mps;
/* 108:    */   }
/* 109:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.MultiPointShape
 * JD-Core Version:    0.7.0.1
 */