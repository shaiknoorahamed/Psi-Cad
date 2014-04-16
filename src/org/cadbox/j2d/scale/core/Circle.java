/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.awt.Graphics2D;
/*   4:    */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*   5:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   6:    */ 
/*   7:    */ public class Circle
/*   8:    */   extends Shape
/*   9:    */ {
/*  10:    */   double x;
/*  11:    */   double y;
/*  12:    */   double radius;
/*  13:    */   
/*  14:    */   public Circle() {}
/*  15:    */   
/*  16:    */   public Circle(double x, double y, double radius)
/*  17:    */   {
/*  18: 52 */     this.x = x;
/*  19: 53 */     this.y = y;
/*  20: 54 */     this.radius = radius;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public double getX()
/*  24:    */   {
/*  25: 58 */     return this.x;
/*  26:    */   }
/*  27:    */   
/*  28:    */   public void setX(double x)
/*  29:    */   {
/*  30: 62 */     this.x = x;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public double getY()
/*  34:    */   {
/*  35: 66 */     return this.y;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void setY(double y)
/*  39:    */   {
/*  40: 70 */     this.y = y;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public double getRadius()
/*  44:    */   {
/*  45: 74 */     return this.radius;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void setRadius(double radius)
/*  49:    */   {
/*  50: 78 */     this.radius = radius;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public Rectangle2D_ getBounds()
/*  54:    */   {
/*  55: 82 */     return new Rectangle2D_(this.x - this.radius, this.y + this.radius, 2.0D * this.radius, 2.0D * this.radius);
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void draw(Context context)
/*  59:    */   {
/*  60: 86 */     DrawingContext drawingContext = context.getDrawingContext();
/*  61: 87 */     Graphics2D g = drawingContext.getGraphics();
/*  62: 88 */     Point2I p = drawingContext.translateToScreen(this.x, this.y);
/*  63: 89 */     int r = drawingContext.distanseToScreen(this.radius);
/*  64: 90 */     g.drawOval(p.x - r, p.y - r, r * 2, r * 2);
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void drawOptimized(Context context)
/*  68:    */   {
/*  69: 94 */     draw(context);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean containsPoint(double xp, double yp, double scale)
/*  73:    */   {
/*  74: 98 */     double r = GeometryUtils.length(xp, yp, this.x, this.y);
/*  75: 99 */     if (GeometryUtils.equals(r, this.radius, 2.0D * scale)) {
/*  76:100 */       return true;
/*  77:    */     }
/*  78:102 */     return false;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public void move(double xV, double yV)
/*  82:    */   {
/*  83:106 */     this.x += xV;
/*  84:107 */     this.y += yV;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void rotate(double xP, double yP, double angle)
/*  88:    */   {
/*  89:111 */     double tmpX = xP + (this.x - xP) * Math.cos(angle) - (this.y - yP) * Math.sin(angle);
/*  90:112 */     double tmpY = yP + (this.x - xP) * Math.sin(angle) + (this.y - yP) * Math.cos(angle);
/*  91:113 */     this.x = tmpX;
/*  92:114 */     this.y = tmpY;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void tranclate(double x, double y, double xV, double yV) {}
/*  96:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Circle
 * JD-Core Version:    0.7.0.1
 */