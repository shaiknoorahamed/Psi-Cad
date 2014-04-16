/*  1:   */ package org.cadbox;
/*  2:   */ 
/*  3:   */ import java.util.HashMap;
/*  4:   */ import java.util.Iterator;
/*  5:   */ import java.util.Map;
/*  6:   */ import java.util.Map.Entry;
/*  7:   */ import java.util.Set;
/*  8:   */ 
/*  9:   */ public class SimpleURManager
/* 10:   */   implements URManager
/* 11:   */ {
/* 12:40 */   private static SimpleURManager instance = new SimpleURManager();
/* 13:41 */   private Map map = new HashMap();
/* 14:   */   
/* 15:   */   public static URManager getInstance()
/* 16:   */   {
/* 17:47 */     return instance;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void registerUREngine(UREngine urEngine, Document document)
/* 21:   */   {
/* 22:51 */     this.map.put(document, urEngine);
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void unregisterUREngine(Document document)
/* 26:   */   {
/* 27:55 */     this.map.remove(document);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void unregisterUREngine(UREngine urEngine)
/* 31:   */   {
/* 32:59 */     Iterator iter = this.map.entrySet().iterator();
/* 33:60 */     while (iter.hasNext())
/* 34:   */     {
/* 35:61 */       Map.Entry e = (Map.Entry)iter.next();
/* 36:62 */       if (e.getValue() == urEngine)
/* 37:   */       {
/* 38:63 */         this.map.remove(e.getKey());
/* 39:64 */         break;
/* 40:   */       }
/* 41:   */     }
/* 42:   */   }
/* 43:   */   
/* 44:   */   public UREngine getUREngine(Document document)
/* 45:   */   {
/* 46:70 */     return (UREngine)this.map.get(document);
/* 47:   */   }
/* 48:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.SimpleURManager
 * JD-Core Version:    0.7.0.1
 */