/*  1:   */ package org.cadbox.j2d.widget.dialog;
/*  2:   */ 
/*  3:   */ import java.awt.Container;
/*  4:   */ import java.awt.Font;
/*  5:   */ import java.awt.Frame;
/*  6:   */ import java.awt.event.ActionEvent;
/*  7:   */ import java.awt.event.ActionListener;
/*  8:   */ import javax.swing.JButton;
/*  9:   */ import javax.swing.JDialog;
/* 10:   */ import javax.swing.JLabel;
/* 11:   */ import org.cadbox.widget.WindowUtils;
/* 12:   */ import org.netbeans.lib.awtextra.AbsoluteConstraints;
/* 13:   */ import org.netbeans.lib.awtextra.AbsoluteLayout;
/* 14:   */ 
/* 15:   */ public class AboutDialog
/* 16:   */   extends JDialog
/* 17:   */ {
/* 18:   */   private JLabel jLabel1;
/* 19:   */   private JButton okButton;
/* 20:   */   
/* 21:   */   public AboutDialog(Frame parent, boolean modal)
/* 22:   */   {
/* 23:42 */     super(parent, modal);
/* 24:   */     
/* 25:44 */     setTitle("About");
/* 26:45 */     initComponents();
/* 27:   */     
/* 28:47 */     WindowUtils.moveScreenCenter(this);
/* 29:   */   }
/* 30:   */   
/* 31:   */   private void initComponents()
/* 32:   */   {
/* 33:57 */     this.okButton = new JButton();
/* 34:58 */     this.jLabel1 = new JLabel();
/* 35:   */     
/* 36:60 */     getContentPane().setLayout(new AbsoluteLayout());
/* 37:   */     
/* 38:62 */     setDefaultCloseOperation(2);
/* 39:63 */     setModal(true);
/* 40:64 */     setResizable(false);
/* 41:65 */     this.okButton.setText("Ok");
/* 42:66 */     this.okButton.addActionListener(new ActionListener()
/* 43:   */     {
/* 44:   */       public void actionPerformed(ActionEvent evt)
/* 45:   */       {
/* 46:68 */         AboutDialog.this.okButtonActionPerformed(evt);
/* 47:   */       }
/* 48:71 */     });
/* 49:72 */     getContentPane().add(this.okButton, new AbsoluteConstraints(150, 70, 110, -1));
/* 50:   */     
/* 51:74 */     this.jLabel1.setFont(new Font("Tahoma", 0, 14));
/* 52:75 */     this.jLabel1.setText("CADBox.2D Test");
/* 53:76 */     getContentPane().add(this.jLabel1, new AbsoluteConstraints(150, 30, -1, -1));
/* 54:   */     
/* 55:78 */     pack();
/* 56:   */   }
/* 57:   */   
/* 58:   */   private void okButtonActionPerformed(ActionEvent evt)
/* 59:   */   {
/* 60:82 */     setVisible(false);
/* 61:   */   }
/* 62:   */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.dialog.AboutDialog
 * JD-Core Version:    0.7.0.1
 */