/*  1:   */ package org.cadbox;
/*  2:   */ 
/*  3:   */ import java.util.Vector;
/*  4:   */ 
/*  5:   */ public class SimpleUREngine
/*  6:   */   implements UREngine
/*  7:   */ {
/*  8:39 */   Vector vector = null;
/*  9:   */   int index;
/* 10:   */   
/* 11:   */   public SimpleUREngine()
/* 12:   */   {
/* 13:43 */     this.index = -1;
/* 14:44 */     this.vector = new Vector();
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void doUndo()
/* 18:   */   {
/* 19:48 */     if (canUndo())
/* 20:   */     {
/* 21:49 */       UndoableCommand cmd = (UndoableCommand)this.vector.get(this.index);
/* 22:50 */       cmd.unexecute();
/* 23:51 */       this.index -= 1;
/* 24:   */     }
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void doRedo()
/* 28:   */   {
/* 29:56 */     if (canRedo())
/* 30:   */     {
/* 31:57 */       this.index += 1;
/* 32:58 */       UndoableCommand cmd = (UndoableCommand)this.vector.get(this.index);
/* 33:59 */       cmd.execute();
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public boolean canUndo()
/* 38:   */   {
/* 39:64 */     return this.index != -1;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean canRedo()
/* 43:   */   {
/* 44:68 */     return this.vector.size() - 1 != this.index;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void addCommand(UndoableCommand command)
/* 48:   */   {
/* 49:72 */     if (this.vector.size() - 1 == this.index)
/* 50:   */     {
/* 51:73 */       this.vector.add(command);
/* 52:74 */       this.index = (this.vector.size() - 1);
/* 53:   */     }
/* 54:76 */     if (this.vector.size() - 1 > this.index)
/* 55:   */     {
/* 56:77 */       this.vector.setSize(this.index + 1);
/* 57:78 */       this.vector.add(command);
/* 58:79 */       this.index = (this.vector.size() - 1);
/* 59:   */     }
/* 60:   */   }
/* 61:   */   
/* 62:   */   public String toString()
/* 63:   */   {
/* 64:84 */     if ((this.index >= 0) && (this.index < this.vector.size()))
/* 65:   */     {
/* 66:85 */       UndoableCommand cmd = (UndoableCommand)this.vector.get(this.index);
/* 67:86 */       return cmd.toString();
/* 68:   */     }
/* 69:88 */     return "No element";
/* 70:   */   }
/* 71:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.SimpleUREngine
 * JD-Core Version:    0.7.0.1
 */