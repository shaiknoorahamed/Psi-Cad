/*   1:    */ package org.cadbox.plaf;
/*   2:    */ 
/*   3:    */ import java.awt.BorderLayout;
/*   4:    */ import java.awt.Component;
/*   5:    */ import java.awt.Container;
/*   6:    */ import java.awt.Dimension;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import javax.swing.Icon;
/*   9:    */ import javax.swing.ImageIcon;
/*  10:    */ import javax.swing.JFrame;
/*  11:    */ import javax.swing.JMenuBar;
/*  12:    */ import javax.swing.JPanel;
/*  13:    */ import javax.swing.JToolBar;
/*  14:    */ import javax.swing.border.BevelBorder;
/*  15:    */ import org.cadbox.widget.ClosableTabbedPane;
/*  16:    */ import org.cadbox.widget.WindowUtils;
/*  17:    */ import org.tigris.toolbar.layouts.DockLayout;
/*  18:    */ 
/*  19:    */ public class AppWindow
/*  20:    */   extends JFrame
/*  21:    */   implements Window
/*  22:    */ {
/*  23:    */   private ClosableTabbedPane documentTabbedPane;
/*  24:    */   private JPanel statusBar;
/*  25:    */   private JPanel desktopPane;
/*  26:    */   
/*  27:    */   public AppWindow()
/*  28:    */   {
/*  29: 50 */     initializate();
/*  30:    */   }
/*  31:    */   
/*  32:    */   public void initializate()
/*  33:    */   {
/*  34: 55 */     setDefaultCloseOperation(3);
/*  35:    */     
/*  36: 57 */     setSize(new Dimension(800, 600));
/*  37: 58 */     WindowUtils.moveScreenCenter(this);
/*  38:    */     
/*  39: 60 */     setExtendedState(6);
/*  40:    */     
/*  41:    */ 
/*  42: 63 */     this.statusBar = new JPanel();
/*  43: 64 */     this.desktopPane = new JPanel();
/*  44:    */     
/*  45:    */ 
/*  46: 67 */     getContentPane().setLayout(new BorderLayout());
/*  47:    */     
/*  48:    */ 
/*  49: 70 */     this.desktopPane.setLayout(new DockLayout(this, 1));
/*  50: 71 */     add(this.desktopPane, "Center");
/*  51:    */     
/*  52:    */ 
/*  53:    */ 
/*  54:    */ 
/*  55: 76 */     Icon mIcon = new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/close.png"));
/*  56: 77 */     this.documentTabbedPane = new ClosableTabbedPane(mIcon);
/*  57: 78 */     this.documentTabbedPane.setBorder(new BevelBorder(1));
/*  58:    */     
/*  59: 80 */     this.desktopPane.add(this.documentTabbedPane, "Center");
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void addInternalWindow(InternalWindow frame)
/*  63:    */   {
/*  64: 84 */     if (frame != null)
/*  65:    */     {
/*  66: 85 */       this.documentTabbedPane.addTab(frame.getTitle(), frame);
/*  67: 86 */       this.documentTabbedPane.setSelectedComponent(frame);
/*  68:    */     }
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void removeInternalWindow(InternalWindow frame)
/*  72:    */   {
/*  73: 91 */     this.documentTabbedPane.remove(frame);
/*  74:    */   }
/*  75:    */   
/*  76:    */   public InternalWindow[] getAllInternalWindows()
/*  77:    */   {
/*  78: 95 */     ArrayList list = new ArrayList();
/*  79: 96 */     Component[] c = this.documentTabbedPane.getComponents();
/*  80: 97 */     for (int i = 0; i < c.length; i++) {
/*  81: 98 */       if ((c[i] instanceof InternalWindow)) {
/*  82: 99 */         list.add(c[i]);
/*  83:    */       }
/*  84:    */     }
/*  85:102 */     InternalWindow[] result = new InternalWindow[list.size()];
/*  86:103 */     for (int i = 0; i < list.size(); i++) {
/*  87:104 */       result[i] = ((InternalWindow)list.get(i));
/*  88:    */     }
/*  89:106 */     return result;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void addToolBar(JToolBar toolBar, Object constraints)
/*  93:    */   {
/*  94:110 */     if ((constraints instanceof String))
/*  95:    */     {
/*  96:111 */       String ss = (String)constraints;
/*  97:113 */       if (ss.compareTo("North") == 0) {
/*  98:114 */         this.desktopPane.add("North", toolBar);
/*  99:    */       }
/* 100:116 */       if (ss.compareTo("South") == 0) {
/* 101:117 */         this.desktopPane.add("South", toolBar);
/* 102:    */       }
/* 103:119 */       if (ss.compareTo("West") == 0) {
/* 104:120 */         this.desktopPane.add("West", toolBar);
/* 105:    */       }
/* 106:122 */       if (ss.compareTo("East") == 0) {
/* 107:123 */         this.desktopPane.add("East", toolBar);
/* 108:    */       }
/* 109:    */     }
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void removeToolBar(JToolBar toolBar)
/* 113:    */   {
/* 114:129 */     this.desktopPane.remove(toolBar);
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setMainMenu(JMenuBar menuBar)
/* 118:    */   {
/* 119:133 */     setJMenuBar(menuBar);
/* 120:    */   }
/* 121:    */   
/* 122:    */   public JMenuBar getMainMenu()
/* 123:    */   {
/* 124:137 */     return getJMenuBar();
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setStatusBar(JPanel statusBar)
/* 128:    */   {
/* 129:141 */     remove(this.statusBar);
/* 130:142 */     this.statusBar = statusBar;
/* 131:143 */     add(statusBar, "South");
/* 132:    */   }
/* 133:    */   
/* 134:    */   public JPanel getStatusBar()
/* 135:    */   {
/* 136:147 */     return this.statusBar;
/* 137:    */   }
/* 138:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.plaf.AppWindow
 * JD-Core Version:    0.7.0.1
 */