/*  1:   */ package org.cadbox.j2d.scale.core;
/*  2:   */ 
/*  3:   */ import java.util.Collection;
/*  4:   */ import java.util.Vector;
/*  5:   */ 
/*  6:   */ public class LineTypeManager
/*  7:   */ {
/*  8:41 */   private static LineTypeManager instance = new LineTypeManager();
/*  9:42 */   private Vector vector = new Vector();
/* 10:44 */   private LineType defaulLineType = new LineType(new float[] { 5.0F, 1.0F, 5.0F });
/* 11:   */   
/* 12:   */   private LineTypeManager()
/* 13:   */   {
/* 14:47 */     this.vector.add(new LineType(new float[] { 5.0F }));
/* 15:48 */     this.vector.add(new LineType(new float[] { 100.0F }));
/* 16:   */   }
/* 17:   */   
/* 18:   */   public static LineTypeManager getInstance()
/* 19:   */   {
/* 20:52 */     return instance;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public Collection getLineWeightList()
/* 24:   */   {
/* 25:56 */     return this.vector;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public LineType getDefaulLineType()
/* 29:   */   {
/* 30:60 */     return this.defaulLineType;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setDefaulLineType(LineType defaulLineType)
/* 34:   */   {
/* 35:64 */     this.defaulLineType = defaulLineType;
/* 36:   */   }
/* 37:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.LineTypeManager
 * JD-Core Version:    0.7.0.1
 */