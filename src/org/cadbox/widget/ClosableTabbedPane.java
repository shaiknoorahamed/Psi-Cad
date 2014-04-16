/*   1:    */ package org.cadbox.widget;
/*   2:    */ 
/*   3:    */ import java.awt.Component;
/*   4:    */ import java.awt.Graphics;
/*   5:    */ import java.awt.Rectangle;
/*   6:    */ import java.awt.event.MouseAdapter;
/*   7:    */ import java.awt.event.MouseEvent;
/*   8:    */ import javax.swing.Icon;
/*   9:    */ import javax.swing.JTabbedPane;
/*  10:    */ import javax.swing.event.ChangeEvent;
/*  11:    */ import javax.swing.event.ChangeListener;
/*  12:    */ import org.cadbox.plaf.InternalWindow;
/*  13:    */ 
/*  14:    */ public class ClosableTabbedPane
/*  15:    */   extends JTabbedPane
/*  16:    */ {
/*  17:    */   private TabCloseIcon closeIcon;
/*  18:    */   
/*  19:    */   public ClosableTabbedPane(Icon closeIcon)
/*  20:    */   {
/*  21: 51 */     this.closeIcon = new TabCloseIcon(closeIcon);
/*  22:    */     
/*  23:    */ 
/*  24: 54 */     addChangeListener(new ChangeListener()
/*  25:    */     {
/*  26:    */       public void stateChanged(ChangeEvent e)
/*  27:    */       {
/*  28: 56 */         ClosableTabbedPane.this.fireActivatedTabListener();
/*  29:    */       }
/*  30:    */     });
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void insertTab(String title, Icon icon, Component component, String tip, int index)
/*  34:    */   {
/*  35: 62 */     super.insertTab(title, this.closeIcon, component, tip, index);
/*  36:    */   }
/*  37:    */   
/*  38:    */   protected void fireTabCloseListener(int index)
/*  39:    */   {
/*  40: 66 */     Component component = getComponentAt(index);
/*  41: 67 */     if ((component instanceof InternalWindow))
/*  42:    */     {
/*  43: 68 */       InternalWindow frame = (InternalWindow)component;
/*  44: 69 */       frame.fireCloseInternalWindowAction();
/*  45:    */     }
/*  46:    */   }
/*  47:    */   
/*  48:    */   protected void fireActivatedTabListener()
/*  49:    */   {
/*  50: 75 */     Component component = getSelectedComponent();
/*  51: 76 */     InternalWindow frame = (InternalWindow)component;
/*  52: 77 */     if (frame != null) {
/*  53: 78 */       frame.fireActivatedInternalWindowAction();
/*  54:    */     }
/*  55:    */   }
/*  56:    */   
/*  57:    */   class TabCloseIcon
/*  58:    */     implements Icon
/*  59:    */   {
/*  60:    */     private final Icon mIcon;
/*  61: 84 */     private JTabbedPane mTabbedPane = null;
/*  62: 85 */     private transient Rectangle mPosition = null;
/*  63: 86 */     private boolean border = false;
/*  64:    */     
/*  65:    */     public TabCloseIcon(Icon icon)
/*  66:    */     {
/*  67: 89 */       this.mIcon = icon;
/*  68:    */     }
/*  69:    */     
/*  70:    */     public void paintIcon(Component c, Graphics g, int x, int y)
/*  71:    */     {
/*  72: 93 */       if (null == this.mTabbedPane)
/*  73:    */       {
/*  74: 94 */         this.mTabbedPane = ((JTabbedPane)c);
/*  75: 95 */         this.mTabbedPane.addMouseListener(new MouseAdapter()
/*  76:    */         {
/*  77:    */           public void mouseReleased(MouseEvent e)
/*  78:    */           {
/*  79: 98 */             if ((!e.isConsumed()) && (ClosableTabbedPane.TabCloseIcon.this.mPosition.contains(e.getX(), e.getY())))
/*  80:    */             {
/*  81: 99 */               int index = ClosableTabbedPane.TabCloseIcon.this.mTabbedPane.getSelectedIndex();
/*  82:    */               
/*  83:101 */               ClosableTabbedPane.this.fireTabCloseListener(index);
/*  84:102 */               e.consume();
/*  85:    */             }
/*  86:    */           }
/*  87:    */         });
/*  88:    */       }
/*  89:107 */       this.mPosition = new Rectangle(x, y, getIconWidth(), getIconHeight());
/*  90:108 */       this.mIcon.paintIcon(c, g, x, y);
/*  91:    */     }
/*  92:    */     
/*  93:    */     public int getIconWidth()
/*  94:    */     {
/*  95:113 */       return this.mIcon.getIconWidth();
/*  96:    */     }
/*  97:    */     
/*  98:    */     public int getIconHeight()
/*  99:    */     {
/* 100:117 */       return this.mIcon.getIconHeight();
/* 101:    */     }
/* 102:    */   }
/* 103:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.widget.ClosableTabbedPane
 * JD-Core Version:    0.7.0.1
 */