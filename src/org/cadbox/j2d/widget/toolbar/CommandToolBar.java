/*  1:   */ package org.cadbox.j2d.widget.toolbar;
/*  2:   */ 
/*  3:   */ import java.awt.Dimension;
/*  4:   */ import javax.swing.JTextField;
/*  5:   */ import org.cadbox.Observer;
/*  6:   */ import org.cadbox.plaf.ToolBar;
/*  7:   */ 
/*  8:   */ public class CommandToolBar
/*  9:   */   extends ToolBar
/* 10:   */   implements Observer
/* 11:   */ {
/* 12:   */   private JTextField test;
/* 13:   */   
/* 14:   */   public CommandToolBar()
/* 15:   */   {
/* 16:46 */     init();
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void init()
/* 20:   */   {
/* 21:50 */     this.test = new JTextField();
/* 22:   */     
/* 23:52 */     setName("Command");
/* 24:53 */     setRollover(true);
/* 25:   */     
/* 26:55 */     this.test.setPreferredSize(new Dimension(500, 25));
/* 27:   */     
/* 28:57 */     add(this.test);
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void update() {}
/* 32:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.CommandToolBar
 * JD-Core Version:    0.7.0.1
 */