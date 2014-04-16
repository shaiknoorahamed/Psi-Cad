/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import org.cadbox.UndoableCommand;
/*  4:   */ import org.cadbox.j2d.scale.core.LineWeight;
/*  5:   */ import org.cadbox.j2d.scale.core.PropertyContext;
/*  6:   */ 
/*  7:   */ public class LineWeightChangeCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   private PropertyContext cadPropertyContext;
/* 11:   */   private LineWeight oldLineWeight;
/* 12:   */   private LineWeight lineWeight;
/* 13:   */   
/* 14:   */   public LineWeightChangeCommand(PropertyContext cadPropertyContext, LineWeight lineWeight)
/* 15:   */   {
/* 16:50 */     this.cadPropertyContext = cadPropertyContext;
/* 17:51 */     this.lineWeight = lineWeight;
/* 18:   */     
/* 19:53 */     this.oldLineWeight = cadPropertyContext.getLineWeight();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void execute()
/* 23:   */   {
/* 24:57 */     this.cadPropertyContext.setLineWeight(this.lineWeight);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void unexecute()
/* 28:   */   {
/* 29:61 */     this.cadPropertyContext.setLineWeight(this.oldLineWeight);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.LineWeightChangeCommand
 * JD-Core Version:    0.7.0.1
 */