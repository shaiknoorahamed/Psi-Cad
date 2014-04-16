/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.Application;
/*  4:   */ import org.cadbox.Command;
/*  5:   */ 
/*  6:   */ public class ExitCommand
/*  7:   */   implements Command
/*  8:   */ {
/*  9:42 */   CloseAllCommand cmd = null;
/* 10:   */   
/* 11:   */   public ExitCommand(Application application, String title, String extension, String description)
/* 12:   */   {
/* 13:45 */     this.cmd = new CloseAllCommand(application, title, extension, description);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public void execute()
/* 17:   */   {
/* 18:49 */     this.cmd.execute();
/* 19:50 */     System.exit(0);
/* 20:   */   }
/* 21:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.ExitCommand
 * JD-Core Version:    0.7.0.1
 */