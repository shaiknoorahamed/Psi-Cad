/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import java.awt.Font;
/*  4:   */ import java.awt.Graphics2D;
/*  5:   */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*  6:   */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*  7:   */ 
/*  8:   */ public class Text
/*  9:   */   extends Shape
/* 10:   */ {
/* 11:   */   public double size;
/* 12:   */   public double x;
/* 13:   */   public double y;
/* 14:   */   public String text;
/* 15:   */   
/* 16:   */   public Text() {}
/* 17:   */   
/* 18:   */   public Text(String text, double x, double y, double size)
/* 19:   */   {
/* 20:52 */     this.x = x;
/* 21:53 */     this.y = y;
/* 22:54 */     this.text = text;
/* 23:55 */     this.size = size;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void draw(Context context)
/* 27:   */   {
/* 28:59 */     drawOptimized(context);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void drawOptimized(Context context)
/* 32:   */   {
/* 33:63 */     DrawingContext drawingContext = context.getDrawingContext();
/* 34:64 */     Graphics2D g = drawingContext.getGraphics();
/* 35:   */     
/* 36:66 */     Font fx = new Font("serif", 1, drawingContext.distanseToScreen(this.size));
/* 37:67 */     g.setFont(fx);
/* 38:   */     
/* 39:69 */     Point2I p = drawingContext.translateToScreen(this.x, this.y);
/* 40:   */     
/* 41:71 */     g.drawString(this.text, p.x, p.y);
/* 42:   */   }
/* 43:   */   
/* 44:   */   public boolean containsPoint(double x, double y, double scale)
/* 45:   */   {
/* 46:75 */     return false;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public Rectangle2D_ getBounds()
/* 50:   */   {
/* 51:81 */     return new Rectangle2D_((-1.0D / 0.0D), (1.0D / 0.0D), (1.0D / 0.0D), (1.0D / 0.0D));
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void move(double xV, double yV)
/* 55:   */   {
/* 56:85 */     this.x += xV;
/* 57:86 */     this.y += yV;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void rotate(double x, double y, double angle) {}
/* 61:   */   
/* 62:   */   public void tranclate(double x, double y, double xV, double yV) {}
/* 63:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Text
 * JD-Core Version:    0.7.0.1
 */