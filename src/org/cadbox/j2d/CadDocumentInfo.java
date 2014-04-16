/*  1:   */ package org.cadbox.j2d;
/*  2:   */ 
/*  3:   */ import org.cadbox.Counter;
/*  4:   */ 
/*  5:   */ public class CadDocumentInfo
/*  6:   */ {
/*  7:41 */   private String fileName = "Document" + Counter.getIndex();
/*  8:42 */   private String path = "";
/*  9:   */   
/* 10:   */   public String getFileName()
/* 11:   */   {
/* 12:45 */     return this.fileName;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setFileName(String fileName)
/* 16:   */   {
/* 17:49 */     this.fileName = fileName;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setPath(String path)
/* 21:   */   {
/* 22:53 */     this.path = path;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public String getPath()
/* 26:   */   {
/* 27:57 */     return this.path;
/* 28:   */   }
/* 29:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.CadDocumentInfo
 * JD-Core Version:    0.7.0.1
 */