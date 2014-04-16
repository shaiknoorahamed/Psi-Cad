/*  1:   */ package org.cadbox.j2d.command;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import javax.swing.JFileChooser;
/*  5:   */ import javax.swing.filechooser.FileFilter;
/*  6:   */ import org.cadbox.Command;
/*  7:   */ 
/*  8:   */ public abstract class DocumentCommand
/*  9:   */   implements Command
/* 10:   */ {
/* 11:   */   public JFileChooser createFileChooser(String title, String fileExtension, String fileExtensionDescription)
/* 12:   */   {
/* 13:45 */     JFileChooser fc = new JFileChooser();
/* 14:46 */     fc.setDialogTitle(title);
/* 15:   */     
/* 16:48 */     fc.addChoosableFileFilter(new TextFileFilter(fileExtension, fileExtensionDescription));
/* 17:49 */     return fc;
/* 18:   */   }
/* 19:   */   
/* 20:   */   protected File askUser(String title, String extension, String description)
/* 21:   */   {
/* 22:53 */     File file = null;
/* 23:54 */     JFileChooser fc = createFileChooser(title, extension, description);
/* 24:55 */     int option = fc.showSaveDialog(null);
/* 25:56 */     if (0 == option)
/* 26:   */     {
/* 27:57 */       file = fc.getSelectedFile();
/* 28:58 */       String filename = file.getPath();
/* 29:59 */       if (filename.lastIndexOf(".") == -1)
/* 30:   */       {
/* 31:60 */         filename = filename + "." + extension;
/* 32:61 */         file = new File(filename);
/* 33:   */       }
/* 34:   */     }
/* 35:64 */     return file;
/* 36:   */   }
/* 37:   */   
/* 38:   */   protected static class TextFileFilter
/* 39:   */     extends FileFilter
/* 40:   */   {
/* 41:   */     private final String extension;
/* 42:   */     private final String description;
/* 43:   */     
/* 44:   */     TextFileFilter(String extension, String description)
/* 45:   */     {
/* 46:75 */       this.extension = extension;
/* 47:76 */       this.description = description;
/* 48:   */     }
/* 49:   */     
/* 50:   */     public boolean accept(File f)
/* 51:   */     {
/* 52:80 */       if (f.isDirectory()) {
/* 53:81 */         return true;
/* 54:   */       }
/* 55:83 */       String fileName = f.getName();
/* 56:84 */       int i = fileName.lastIndexOf('.');
/* 57:85 */       if ((i > 0) && (i < fileName.length() - 1))
/* 58:   */       {
/* 59:86 */         String fileExt = fileName.substring(i + 1);
/* 60:87 */         if (this.extension.equalsIgnoreCase(fileExt)) {
/* 61:88 */           return true;
/* 62:   */         }
/* 63:   */       }
/* 64:91 */       return false;
/* 65:   */     }
/* 66:   */     
/* 67:   */     public String getDescription()
/* 68:   */     {
/* 69:95 */       return this.description;
/* 70:   */     }
/* 71:   */   }
/* 72:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.command.DocumentCommand
 * JD-Core Version:    0.7.0.1
 */