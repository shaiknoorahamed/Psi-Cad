/*  1:   */ package org.cadbox;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.Collection;
/*  5:   */ import java.util.List;
/*  6:   */ 
/*  7:   */ public class MacroCommand
/*  8:   */   implements UndoableCommand
/*  9:   */ {
/* 10:42 */   List list = new ArrayList();
/* 11:   */   
/* 12:   */   public void addCommand(UndoableCommand command)
/* 13:   */   {
/* 14:48 */     this.list.add(command);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void removeCommand(UndoableCommand command)
/* 18:   */   {
/* 19:52 */     this.list.remove(command);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public Collection getCommands()
/* 23:   */   {
/* 24:56 */     return this.list;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int getCommandCount()
/* 28:   */   {
/* 29:60 */     return this.list.size();
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void execute()
/* 33:   */   {
/* 34:64 */     int size = this.list.size();
/* 35:65 */     for (int i = 0; i < size; i++)
/* 36:   */     {
/* 37:66 */       UndoableCommand cmd = (UndoableCommand)this.list.get(i);
/* 38:67 */       cmd.execute();
/* 39:   */     }
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void unexecute()
/* 43:   */   {
/* 44:72 */     int size = this.list.size();
/* 45:73 */     for (int i = size - 1; i >= 0; i--)
/* 46:   */     {
/* 47:74 */       UndoableCommand cmd = (UndoableCommand)this.list.get(i);
/* 48:75 */       cmd.unexecute();
/* 49:   */     }
/* 50:   */   }
/* 51:   */ }


