/*   1:    */ package org.cadbox.j2d.widget.toolbar;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Component;
/*   5:    */ import java.awt.Dimension;
/*   6:    */ import java.awt.FlowLayout;
/*   7:    */ import java.awt.SystemColor;
/*   8:    */ import java.awt.event.ActionEvent;
/*   9:    */ import java.awt.event.ActionListener;
/*  10:    */ import java.util.Collection;
/*  11:    */ import java.util.Iterator;
/*  12:    */ import javax.swing.ImageIcon;
/*  13:    */ import javax.swing.JButton;
/*  14:    */ import javax.swing.JComboBox;
/*  15:    */ import javax.swing.JLabel;
/*  16:    */ import javax.swing.JList;
/*  17:    */ import javax.swing.JPanel;
/*  18:    */ import javax.swing.ListCellRenderer;
/*  19:    */ import org.cadbox.Document;
/*  20:    */ import org.cadbox.Observer;
/*  21:    */ import org.cadbox.SimpleURManager;
/*  22:    */ import org.cadbox.UREngine;
/*  23:    */ import org.cadbox.URManager;
/*  24:    */ import org.cadbox.j2d.CadApplication;
/*  25:    */ import org.cadbox.j2d.command.ShowLayerDialogCommand;
/*  26:    */ import org.cadbox.j2d.scale.command.SetCurrentLayerCommand;
/*  27:    */ import org.cadbox.j2d.scale.core.Layer;
/*  28:    */ import org.cadbox.j2d.scale.core.LayeredDocument;
/*  29:    */ import org.cadbox.j2d.widget.dialog.LayerSettingDialog;
/*  30:    */ import org.cadbox.plaf.ToolBar;
/*  31:    */ 
/*  32:    */ public class LayerToolBar
/*  33:    */   extends ToolBar
/*  34:    */   implements Observer
/*  35:    */ {
/*  36:    */   private JComboBox layerList;
/*  37:    */   private JButton showButton;
/*  38: 53 */   CadApplication application = null;
/*  39: 54 */   LayerSettingDialog form = null;
/*  40:    */   
/*  41:    */   public LayerToolBar(CadApplication application)
/*  42:    */   {
/*  43: 59 */     this.application = application;
/*  44: 60 */     initComponents();
/*  45:    */     
/*  46: 62 */     load();
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void setEnabled(boolean state)
/*  50:    */   {
/*  51: 68 */     this.layerList.setEnabled(state);
/*  52: 69 */     this.showButton.setEnabled(state);
/*  53: 71 */     if (!state) {
/*  54: 72 */       this.layerList.setSelectedIndex(-1);
/*  55:    */     }
/*  56:    */   }
/*  57:    */   
/*  58:    */   private LayeredDocument getCurrentDocument()
/*  59:    */   {
/*  60: 76 */     Document document = this.application.getCurrentDocument();
/*  61: 77 */     if ((document instanceof LayeredDocument)) {
/*  62: 78 */       return (LayeredDocument)document;
/*  63:    */     }
/*  64: 80 */     return null;
/*  65:    */   }
/*  66:    */   
/*  67:    */   private void load()
/*  68:    */   {
/*  69: 85 */     LayeredDocument document = getCurrentDocument();
/*  70: 87 */     if (document != null)
/*  71:    */     {
/*  72: 89 */       setEnabled(true);
/*  73:    */       
/*  74:    */ 
/*  75: 92 */       this.layerList.removeActionListener(this.layerChangeListener);
/*  76:    */       
/*  77: 94 */       this.layerList.removeAllItems();
/*  78:    */       
/*  79: 96 */       Iterator iter = document.getLayers().iterator();
/*  80: 97 */       while (iter.hasNext())
/*  81:    */       {
/*  82: 98 */         Layer layer = (Layer)iter.next();
/*  83: 99 */         this.layerList.addItem(layer);
/*  84:100 */         if (layer == document.getCurrentLayer()) {
/*  85:101 */           this.layerList.setSelectedItem(layer);
/*  86:    */         }
/*  87:    */       }
/*  88:108 */       this.layerList.addActionListener(this.layerChangeListener);
/*  89:    */     }
/*  90:    */     else
/*  91:    */     {
/*  92:110 */       setEnabled(false);
/*  93:    */     }
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void update()
/*  97:    */   {
/*  98:116 */     load();
/*  99:    */   }
/* 100:    */   
/* 101:    */   private void initComponents()
/* 102:    */   {
/* 103:122 */     setName("Layers");
/* 104:123 */     setRollover(true);
/* 105:    */     
/* 106:125 */     this.showButton = new JButton();
/* 107:126 */     this.layerList = new JComboBox();
/* 108:    */     
/* 109:128 */     this.showButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/layers.png")));
/* 110:129 */     this.showButton.setFocusable(false);
/* 111:130 */     this.showButton.setHorizontalTextPosition(0);
/* 112:131 */     this.showButton.setMaximumSize(new Dimension(25, 25));
/* 113:132 */     this.showButton.setMinimumSize(new Dimension(25, 25));
/* 114:133 */     this.showButton.setName("showButton");
/* 115:134 */     this.showButton.setPreferredSize(new Dimension(25, 25));
/* 116:135 */     this.showButton.setVerticalTextPosition(3);
/* 117:136 */     this.showButton.addActionListener(new ActionListener()
/* 118:    */     {
/* 119:    */       public void actionPerformed(ActionEvent evt)
/* 120:    */       {
/* 121:138 */         LayerToolBar.this.showButtonActionPerformed(evt);
/* 122:    */       }
/* 123:140 */     });
/* 124:141 */     add(this.showButton);
/* 125:    */     
/* 126:    */ 
/* 127:144 */     this.layerList.setFocusable(false);
/* 128:145 */     this.layerList.setRenderer(new JLRenderer());
/* 129:146 */     this.layerList.setMaximumSize(new Dimension(150, 23));
/* 130:147 */     this.layerList.setMinimumSize(new Dimension(150, 23));
/* 131:148 */     this.layerList.setPreferredSize(new Dimension(150, 23));
/* 132:149 */     this.layerList.setName("layerList");
/* 133:    */     
/* 134:151 */     this.layerList.addActionListener(this.layerChangeListener);
/* 135:    */     
/* 136:153 */     add(this.layerList);
/* 137:    */   }
/* 138:    */   
/* 139:156 */   ActionListener layerChangeListener = new ActionListener()
/* 140:    */   {
/* 141:    */     public void actionPerformed(ActionEvent evt)
/* 142:    */     {
/* 143:158 */       LayerToolBar.this.layerListActionPerformed(evt);
/* 144:    */     }
/* 145:    */   };
/* 146:    */   
/* 147:    */   private void layerListActionPerformed(ActionEvent evt)
/* 148:    */   {
/* 149:163 */     Layer layer = (Layer)this.layerList.getSelectedItem();
/* 150:    */     
/* 151:165 */     LayeredDocument document = getCurrentDocument();
/* 152:166 */     if (document != null)
/* 153:    */     {
/* 154:169 */       SetCurrentLayerCommand cmd = new SetCurrentLayerCommand(document, layer);
/* 155:170 */       cmd.execute();
/* 156:171 */       SimpleURManager.getInstance().getUREngine(document).addCommand(cmd);
/* 157:    */     }
/* 158:    */   }
/* 159:    */   
/* 160:    */   private void showButtonActionPerformed(ActionEvent evt)
/* 161:    */   {
/* 162:187 */     ShowLayerDialogCommand cmd = new ShowLayerDialogCommand(this.application);
/* 163:188 */     cmd.execute();
/* 164:    */   }
/* 165:    */   
/* 166:    */   class JLRenderer
/* 167:    */     implements ListCellRenderer
/* 168:    */   {
/* 169:    */     public JLRenderer() {}
/* 170:    */     
/* 171:    */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 172:    */     {
/* 173:200 */       Layer layer = (Layer)value;
/* 174:202 */       if (layer != null)
/* 175:    */       {
/* 176:204 */         JPanel panel = new JPanel();
/* 177:205 */         panel.setLayout(new FlowLayout(0, 0, 0));
/* 178:    */         
/* 179:207 */         JLabel label = new JLabel("  " + layer.getName());
/* 180:    */         
/* 181:209 */         panel.setPreferredSize(new Dimension(150, 18));
/* 182:210 */         panel.add(label);
/* 183:212 */         if (isSelected)
/* 184:    */         {
/* 185:213 */           panel.setBackground(SystemColor.activeCaption);
/* 186:214 */           label.setForeground(Color.white);
/* 187:    */         }
/* 188:    */         else
/* 189:    */         {
/* 190:216 */           panel.setBackground(SystemColor.text);
/* 191:217 */           label.setForeground(SystemColor.controlText);
/* 192:    */         }
/* 193:220 */         return panel;
/* 194:    */       }
/* 195:222 */       return new JLabel("");
/* 196:    */     }
/* 197:    */   }
/* 198:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.LayerToolBar
 * JD-Core Version:    0.7.0.1
 */