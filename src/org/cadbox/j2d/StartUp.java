/*  1:   */ package org.cadbox.j2d;
/*  2:   */ 
/*  3:   */ import java.awt.EventQueue;
/*  4:   */ import javax.swing.UIManager;
/*  5:   */ 
/*  6:   */ public class StartUp
/*  7:   */ {
/*  8:   */   private static CadApplication form;
/*  9:   */   
/* 10:   */   public static void main(String[] args)
/* 11:   */   {
/* 12:44 */     EventQueue.invokeLater(new Runnable()
/* 13:   */     {
/* 14:   */       public void run()
/* 15:   */       {
/* 16:   */         try
/* 17:   */         {
/* 18:49 */           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/* 19:   */         }
/* 20:   */         catch (Exception ex) {}
/* 21:55 */         form = new CadApplication();
/* 22:   */       }
/* 23:   */     });
/* 24:   */   }
/* 25:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.StartUp
 * JD-Core Version:    0.7.0.1
 */