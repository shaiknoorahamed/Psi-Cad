/*  1:   */ package org.cadbox.j2d.scale.command;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import org.cadbox.UndoableCommand;
/*  5:   */ import org.cadbox.j2d.scale.core.PropertyContext;
/*  6:   */ 
/*  7:   */ public class ColorChangeCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:   */   private PropertyContext cadPropertyContext;
/* 11:   */   private Color oldCadColor;
/* 12:   */   private Color color;
/* 13:   */   
/* 14:   */   public ColorChangeCommand(PropertyContext cadPropertyContext, Color color)
/* 15:   */   {
/* 16:50 */     this.cadPropertyContext = cadPropertyContext;
/* 17:51 */     this.color = color;
/* 18:   */     
/* 19:53 */     this.oldCadColor = cadPropertyContext.getColor();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void execute()
/* 23:   */   {
/* 24:57 */     this.cadPropertyContext.setColor(this.color);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void unexecute()
/* 28:   */   {
/* 29:61 */     this.cadPropertyContext.setColor(this.oldCadColor);
/* 30:   */   }
/* 31:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.command.ColorChangeCommand
 * JD-Core Version:    0.7.0.1
 */