/*  1:   */ package org.cadbox.j2d.widget.toolbar;
/*  2:   */ 
/*  3:   */ import javax.swing.JButton;
/*  4:   */ import org.cadbox.Observer;
/*  5:   */ import org.cadbox.plaf.ToolBar;
/*  6:   */ 
/*  7:   */ public class SnapToolBar
/*  8:   */   extends ToolBar
/*  9:   */   implements Observer
/* 10:   */ {
/* 11:   */   private JButton buttonSnapSettings;
/* 12:   */   
/* 13:   */   public SnapToolBar()
/* 14:   */   {
/* 15:46 */     init();
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void init()
/* 19:   */   {
/* 20:50 */     this.buttonSnapSettings = new JButton();
/* 21:   */     
/* 22:52 */     setName("Snaps");
/* 23:53 */     setRollover(true);
/* 24:   */     
/* 25:   */ 
/* 26:   */ 
/* 27:57 */     add(this.buttonSnapSettings);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void update() {}
/* 31:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.SnapToolBar
 * JD-Core Version:    0.7.0.1
 */