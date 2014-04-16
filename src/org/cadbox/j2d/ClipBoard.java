/*  1:   */ package org.cadbox.j2d;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.Collection;
/*  5:   */ import java.util.Iterator;
/*  6:   */ import java.util.List;
/*  7:   */ import org.cadbox.j2d.scale.core.Shape;
/*  8:   */ 
/*  9:   */ public class ClipBoard
/* 10:   */ {
/* 11:43 */   private static ClipBoard instance = new ClipBoard();
/* 12:44 */   private List list = new ArrayList();
/* 13:   */   
/* 14:   */   public static synchronized ClipBoard getInstance()
/* 15:   */   {
/* 16:50 */     return instance;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public synchronized void push(Shape shape)
/* 20:   */   {
/* 21:54 */     this.list.add(shape.clone());
/* 22:   */   }
/* 23:   */   
/* 24:   */   public synchronized void push(Collection shapes)
/* 25:   */   {
/* 26:58 */     Iterator iter = shapes.iterator();
/* 27:59 */     while (iter.hasNext()) {
/* 28:60 */       this.list.add(((Shape)iter.next()).clone());
/* 29:   */     }
/* 30:   */   }
/* 31:   */   
/* 32:   */   public synchronized Collection pop()
/* 33:   */   {
/* 34:65 */     List listTmp = new ArrayList();
/* 35:66 */     for (int i = 0; i < this.list.size(); i++)
/* 36:   */     {
/* 37:67 */       Shape shape = (Shape)this.list.get(i);
/* 38:68 */       listTmp.add(shape.clone());
/* 39:   */     }
/* 40:70 */     return listTmp;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public synchronized void clear()
/* 44:   */   {
/* 45:74 */     this.list.clear();
/* 46:   */   }
/* 47:   */   
/* 48:   */   public synchronized boolean isEmpty()
/* 49:   */   {
/* 50:78 */     return this.list.isEmpty();
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.ClipBoard
 * JD-Core Version:    0.7.0.1
 */