/*   1:    */ package org.cadbox.j2d.widget.dialog;
/*   2:    */ 
/*   3:    */ import java.awt.Component;
/*   4:    */ import java.awt.Container;
/*   5:    */ import java.awt.EventQueue;
/*   6:    */ import java.awt.Frame;
/*   7:    */ import java.awt.event.ActionEvent;
/*   8:    */ import java.awt.event.ActionListener;
/*   9:    */ import java.util.Vector;
/*  10:    */ import javax.swing.AbstractListModel;
/*  11:    */ import javax.swing.JButton;
/*  12:    */ import javax.swing.JDialog;
/*  13:    */ import javax.swing.JFrame;
/*  14:    */ import javax.swing.JLabel;
/*  15:    */ import javax.swing.JList;
/*  16:    */ import javax.swing.JScrollPane;
/*  17:    */ import javax.swing.ListCellRenderer;
/*  18:    */ import org.cadbox.j2d.scale.core.LineWeight;
/*  19:    */ import org.cadbox.j2d.scale.core.LineWeightManager;
import org.cadbox.j2d.widget.LineWeightComboBox;
/*  20:    */ import org.cadbox.j2d.widget.LineWeightComboBox.LineWidthPanel;
/*  21:    */ import org.cadbox.widget.WindowUtils;
/*  22:    */ import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/*  24:    */ 
/*  25:    */ public class LineWeightChooseDialog
/*  26:    */   extends JDialog
/*  27:    */ {
/*  28: 44 */   public static int OK_MODAL_RESULT = 0;
/*  29: 45 */   public static int CANCEL_MODAL_RESULT = 1;
/*  30: 47 */   int modalResult = CANCEL_MODAL_RESULT;
/*  31: 49 */   LineWeight currentLineWeight = null;
/*  32:    */   LineWeight[] lineWeight;
/*  33:    */   private JButton cancelButton;
/*  34:    */   private JLabel currentLabel;
/*  35:    */   private JScrollPane jScrollPane1;
/*  36:    */   private JList lineweightList;
/*  37:    */   private JLabel mainLabel;
/*  38:    */   private JButton okButton;
/*  39:    */   private JLabel staticLabel;
/*  40:    */   
/*  41:    */   public LineWeightChooseDialog(Frame parent, boolean modal)
/*  42:    */   {
/*  43: 55 */     super(parent, modal);
/*  44:    */     
/*  45: 57 */     Vector vector = new Vector(LineWeightManager.getInstance().getLineWeightList());
/*  46: 58 */     this.lineWeight = new LineWeight[vector.size()];
/*  47: 59 */     for (int i = 0; i < vector.size(); i++) {
/*  48: 60 */       this.lineWeight[0] = ((LineWeight)vector.get(i));
/*  49:    */     }
/*  50: 63 */     initComponents();
/*  51:    */     
/*  52: 65 */     this.lineweightList.setModel(new AbstractListModel()
/*  53:    */     {
/*  54:    */       public int getSize()
/*  55:    */       {
/*  56: 66 */         return LineWeightChooseDialog.this.lineWeight.length;
/*  57:    */       }
/*  58:    */       
/*  59:    */       public Object getElementAt(int i)
/*  60:    */       {
/*  61: 67 */         return LineWeightChooseDialog.this.lineWeight[i];
/*  62:    */       }
/*  63: 68 */     });
/*  64: 69 */     this.lineweightList.setSelectedIndex(0);
/*  65:    */     
/*  66:    */ 
/*  67: 72 */     this.lineweightList.setCellRenderer(new JLRenderer());
/*  68:    */     
/*  69: 74 */     WindowUtils.moveScreenCenter(this);
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int showModal()
/*  73:    */   {
/*  74: 78 */     init();
/*  75: 79 */     setVisible(true);
/*  76: 80 */     return this.modalResult;
/*  77:    */   }
/*  78:    */   
/*  79:    */   private void init()
/*  80:    */   {
/*  81: 85 */     Vector vector = new Vector(LineWeightManager.getInstance().getLineWeightList());
/*  82: 86 */     for (int i = 0; i < vector.size(); i++) {
/*  83: 87 */       this.lineWeight[i] = ((LineWeight)vector.get(i));
/*  84:    */     }
/*  85: 90 */     if (this.currentLineWeight != null)
/*  86:    */     {
/*  87: 91 */       this.lineweightList.setSelectedValue(this.currentLineWeight, true);
/*  88: 92 */       this.currentLabel.setText(this.currentLineWeight.toString());
/*  89:    */     }
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void setInitLineweight(LineWeight lineWeight)
/*  93:    */   {
/*  94: 97 */     this.currentLineWeight = lineWeight;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public LineWeight getResult()
/*  98:    */   {
/*  99:101 */     return (LineWeight)this.lineweightList.getSelectedValue();
/* 100:    */   }
/* 101:    */   
/* 102:    */   private void initComponents()
/* 103:    */   {
/* 104:110 */     this.staticLabel = new JLabel();
/* 105:111 */     this.currentLabel = new JLabel();
/* 106:112 */     this.jScrollPane1 = new JScrollPane();
/* 107:113 */     this.lineweightList = new JList();
/* 108:114 */     this.okButton = new JButton();
/* 109:115 */     this.cancelButton = new JButton();
/* 110:116 */     this.mainLabel = new JLabel();
/* 111:    */     
/* 112:118 */     getContentPane().setLayout(new AbsoluteLayout());
/* 113:    */     
/* 114:120 */     setDefaultCloseOperation(2);
/* 115:121 */     setTitle("Lineweight");
/* 116:122 */     setName("LineweightChooseDialog");
/* 117:123 */     setResizable(false);
/* 118:124 */     this.staticLabel.setText("Current lineweight:");
/* 119:125 */     getContentPane().add(this.staticLabel, new AbsoluteConstraints(70, 250, -1, -1));
/* 120:    */     
/* 121:127 */     this.currentLabel.setText("jLabel2");
/* 122:128 */     getContentPane().add(this.currentLabel, new AbsoluteConstraints(20, 250, -1, -1));
/* 123:    */     
/* 124:130 */     this.jScrollPane1.setViewportView(this.lineweightList);
/* 125:    */     
/* 126:132 */     getContentPane().add(this.jScrollPane1, new AbsoluteConstraints(10, 30, 250, 210));
/* 127:    */     
/* 128:134 */     this.okButton.setText("OK");
/* 129:135 */     this.okButton.addActionListener(new ActionListener()
/* 130:    */     {
/* 131:    */       public void actionPerformed(ActionEvent evt)
/* 132:    */       {
/* 133:137 */         LineWeightChooseDialog.this.okButtonActionPerformed(evt);
/* 134:    */       }
/* 135:140 */     });
/* 136:141 */     getContentPane().add(this.okButton, new AbsoluteConstraints(50, 280, 80, -1));
/* 137:    */     
/* 138:143 */     this.cancelButton.setText("Cancel");
/* 139:144 */     this.cancelButton.addActionListener(new ActionListener()
/* 140:    */     {
/* 141:    */       public void actionPerformed(ActionEvent evt)
/* 142:    */       {
/* 143:146 */         LineWeightChooseDialog.this.cancelButtonActionPerformed(evt);
/* 144:    */       }
/* 145:149 */     });
/* 146:150 */     getContentPane().add(this.cancelButton, new AbsoluteConstraints(140, 280, 80, -1));
/* 147:    */     
/* 148:152 */     this.mainLabel.setText("Lineweights:");
/* 149:153 */     getContentPane().add(this.mainLabel, new AbsoluteConstraints(10, 10, -1, -1));
/* 150:    */     
/* 151:155 */     pack();
/* 152:    */   }
/* 153:    */   
/* 154:    */   private void cancelButtonActionPerformed(ActionEvent evt)
/* 155:    */   {
/* 156:159 */     this.modalResult = CANCEL_MODAL_RESULT;
/* 157:160 */     setVisible(false);
/* 158:    */   }
/* 159:    */   
/* 160:    */   private void okButtonActionPerformed(ActionEvent evt)
/* 161:    */   {
/* 162:164 */     this.modalResult = OK_MODAL_RESULT;
/* 163:    */     
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:169 */     setVisible(false);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public static void main(String[] args)
/* 171:    */   {
/* 172:176 */     EventQueue.invokeLater(new Runnable()
/* 173:    */     {
/* 174:    */       public void run()
/* 175:    */       {
/* 176:178 */         new LineWeightChooseDialog(new JFrame(), true).setVisible(true);
/* 177:    */       }
/* 178:    */     });
/* 179:    */   }
/* 180:    */   
/* 181:    */   class JLRenderer
/* 182:    */     implements ListCellRenderer
/* 183:    */   {
/* 184:    */     public JLRenderer() {}
/* 185:    */     
/* 186:    */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 187:    */     {
/* 188:202 */       LineWeight lineWeight = (LineWeight)value;
/* 189:204 */       if (lineWeight != null)
/* 190:    */       {
/* 191:206 */         LineWeightComboBox.LineWidthPanel panel = new LineWeightComboBox.LineWidthPanel(lineWeight, isSelected);
/* 192:    */         
/* 193:    */ 
/* 194:209 */         return panel;
/* 195:    */       }
/* 196:211 */       return new JLabel("");
/* 197:    */     }
/* 198:    */   }
/* 199:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.dialog.LineWeightChooseDialog
 * JD-Core Version:    0.7.0.1
 */