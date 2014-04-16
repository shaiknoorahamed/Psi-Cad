/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   5:    */ 
/*   6:    */ public abstract class Shape
/*   7:    */   implements Cloneable
/*   8:    */ {
/*   9:    */   Color color;
/*  10:    */   LineWeight lineWeight;
/*  11:    */   LineType lineType;
/*  12:    */   Group group;
/*  13:    */   
/*  14:    */   public abstract Rectangle2D_ getBounds();
/*  15:    */   
/*  16:    */   public abstract boolean containsPoint(double paramDouble1, double paramDouble2, double paramDouble3);
/*  17:    */   
/*  18:    */   public abstract void draw(Context paramContext);
/*  19:    */   
/*  20:    */   public abstract void drawOptimized(Context paramContext);
/*  21:    */   
/*  22:    */   public Color getColor()
/*  23:    */   {
/*  24: 62 */     Color color = this.color;
/*  25: 63 */     if (color != null) {
/*  26: 64 */       return color;
/*  27:    */     }
/*  28: 66 */     Group group = this.group;
/*  29: 67 */     return group != null ? group.getColor() : null;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void setColor(Color color)
/*  33:    */   {
/*  34: 71 */     this.color = color;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public LineWeight getLineWeight()
/*  38:    */   {
/*  39: 75 */     LineWeight lineWeight = this.lineWeight;
/*  40: 76 */     if (lineWeight != null) {
/*  41: 77 */       return lineWeight;
/*  42:    */     }
/*  43: 79 */     Group group = this.group;
/*  44: 80 */     return group != null ? group.getLineWeight() : null;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void setLineWeight(LineWeight lineWeight)
/*  48:    */   {
/*  49: 85 */     this.lineWeight = lineWeight;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public LineType getLineType()
/*  53:    */   {
/*  54: 89 */     LineType lineType = this.lineType;
/*  55: 90 */     if (lineType != null) {
/*  56: 91 */       return lineType;
/*  57:    */     }
/*  58: 93 */     Group group = this.group;
/*  59: 94 */     return group != null ? group.getLineType() : null;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void setLineType(LineType lineType)
/*  63:    */   {
/*  64: 98 */     this.lineType = lineType;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public Group getParent()
/*  68:    */   {
/*  69:102 */     return this.group;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public abstract void move(double paramDouble1, double paramDouble2);
/*  73:    */   
/*  74:    */   public abstract void rotate(double paramDouble1, double paramDouble2, double paramDouble3);
/*  75:    */   
/*  76:    */   public abstract void tranclate(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*  77:    */   
/*  78:    */   public Object clone()
/*  79:    */   {
/*  80:    */     try
/*  81:    */     {
/*  82:118 */       return super.clone();
/*  83:    */     }
/*  84:    */     catch (CloneNotSupportedException e)
/*  85:    */     {
/*  86:121 */       throw new InternalError();
/*  87:    */     }
/*  88:    */   }
/*  89:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.Shape
 * JD-Core Version:    0.7.0.1
 */