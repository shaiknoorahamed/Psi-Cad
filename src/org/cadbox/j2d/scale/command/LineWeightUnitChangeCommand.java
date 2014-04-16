/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.LineWeightManager;
/*  5:   */ 
/*  6:   */ public class LineWeightUnitChangeCommand
/*  7:   */   implements UndoableCommand
/*  8:   */ {
/*  9:   */   private int oldType;
/* 10:   */   private int newType;
/* 11:   */   
/* 12:   */   public LineWeightUnitChangeCommand(int newType, int oldType)
/* 13:   */   {
/* 14:45 */     this.newType = newType;
/* 15:46 */     this.oldType = oldType;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void execute()
/* 19:   */   {
/* 20:50 */     LineWeightManager.getInstance().setUnitType(this.newType);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void unexecute()
/* 24:   */   {
/* 25:54 */     LineWeightManager.getInstance().setUnitType(this.oldType);
/* 26:   */   }
/* 27:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.LineWeightUnitChangeCommand
 * JD-Core Version:    0.7.0.1
 */