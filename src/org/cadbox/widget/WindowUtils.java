/*  1:   */ package org.cadbox.widget;
/*  2:   */ 
/*  3:   */ import java.awt.Dialog;
/*  4:   */ import java.awt.Dimension;
/*  5:   */ import java.awt.Toolkit;
/*  6:   */ import javax.swing.JFrame;
/*  7:   */ 
/*  8:   */ public class WindowUtils
/*  9:   */ {
/* 10:   */   public static void moveScreenCenter(JFrame wnd)
/* 11:   */   {
/* 12:44 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 13:45 */     Dimension frameSize = wnd.getSize();
/* 14:47 */     if (frameSize.height > screenSize.height) {
/* 15:48 */       frameSize.height = screenSize.height;
/* 16:   */     }
/* 17:49 */     if (frameSize.width > screenSize.width) {
/* 18:50 */       frameSize.width = screenSize.width;
/* 19:   */     }
/* 20:52 */     wnd.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public static void moveScreenCenter(Dialog wnd)
/* 24:   */   {
/* 25:57 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 26:58 */     Dimension frameSize = wnd.getSize();
/* 27:60 */     if (frameSize.height > screenSize.height) {
/* 28:61 */       frameSize.height = screenSize.height;
/* 29:   */     }
/* 30:62 */     if (frameSize.width > screenSize.width) {
/* 31:63 */       frameSize.width = screenSize.width;
/* 32:   */     }
/* 33:65 */     wnd.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
/* 34:   */   }
/* 35:   */   
/* 36:   */   public static void maximize(JFrame wnd)
/* 37:   */   {
/* 38:70 */     wnd.setExtendedState(6);
/* 39:   */   }
/* 40:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.widget.WindowUtils
 * JD-Core Version:    0.7.0.1
 */