/*   1:    */ package org.cadbox.j2d.widget;
/*   2:    */ 
/*   3:    */ import java.awt.BasicStroke;
/*   4:    */ import java.awt.Color;
/*   5:    */ import java.awt.Component;
/*   6:    */ import java.awt.Dimension;
/*   7:    */ import java.awt.FlowLayout;
/*   8:    */ import java.awt.Graphics;
/*   9:    */ import java.awt.Graphics2D;
/*  10:    */ import java.awt.Stroke;
/*  11:    */ import java.awt.SystemColor;
/*  12:    */ import javax.swing.DefaultComboBoxModel;
/*  13:    */ import javax.swing.JComboBox;
/*  14:    */ import javax.swing.JLabel;
/*  15:    */ import javax.swing.JList;
/*  16:    */ import javax.swing.JPanel;
/*  17:    */ import javax.swing.ListCellRenderer;
/*  18:    */ 
/*  19:    */ public class LineStyleComboBox
/*  20:    */   extends JComboBox
/*  21:    */ {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/*  22:    */   private LineStyleDiscription[] styles;
/*  23:    */   private static final int DefaultComboBox_Width = 180;
/*  24:    */   private static final int DefaultComboBox_Height = 23;
/*  25:    */   private static final int DefaultLineWidthPanel_Width = 80;
/*  26:    */   private static final int DefaultLineWidthPanel_Height = 16;
/*  27:    */   
/*  28:    */   public LineStyleComboBox()
/*  29:    */   {
/*  30: 53 */     this.styles = new LineStyleDiscription[3];
/*  31: 54 */     this.styles[0] = new LineStyleDiscription("By layer", null);
/*  32: 55 */     this.styles[1] = new LineStyleDiscription("Continuous", new BasicStroke(1.0F));
/*  33: 56 */     this.styles[2] = new LineStyleDiscription("Dash", new BasicStroke(1.0F, 0, 0, 10.0F, new float[] { 5.0F }, 0.0F));
/*  34:    */     
/*  35:    */ 
/*  36: 59 */     init(this.styles);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public LineStyleComboBox(LineStyleDiscription[] styles)
/*  40:    */   {
/*  41: 64 */     this.styles = styles;
/*  42:    */     
/*  43: 66 */     init(styles);
/*  44:    */   }
/*  45:    */   
/*  46:    */   private void init(LineStyleDiscription[] styles)
/*  47:    */   {
/*  48: 70 */     setModel(new DefaultComboBoxModel(styles));
/*  49:    */     
/*  50:    */ 
/*  51: 73 */     setSelectedIndex(0);
/*  52: 74 */     setPreferredSize(new Dimension(180, 23));
/*  53: 75 */     setFocusable(false);
/*  54:    */     
/*  55: 77 */     JLRenderer cellRenderer = new JLRenderer();
/*  56: 78 */     setRenderer(cellRenderer);
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setEnabled(boolean state)
/*  60:    */   {
/*  61: 82 */     super.setEnabled(state);
/*  62: 83 */     if (!state) {
/*  63: 84 */       setSelectedIndex(-1);
/*  64:    */     }
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setCurrentStroke(Stroke stroke)
/*  68:    */   {
/*  69: 88 */     if (this.styles != null)
/*  70:    */     {
/*  71: 89 */       int size = this.styles.length;
/*  72: 90 */       for (int i = 0; i < size; i++) {
/*  73: 91 */         if (this.styles[i].stroke.equals(stroke) == true)
/*  74:    */         {
/*  75: 92 */           setSelectedIndex(i);
/*  76: 93 */           break;
/*  77:    */         }
/*  78:    */       }
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public BasicStroke getCurrentStroke()
/*  83:    */   {
/*  84:100 */     LineStyleDiscription disc = (LineStyleDiscription)getSelectedItem();
/*  85:101 */     if (disc != null) {
/*  86:102 */       return disc.stroke;
/*  87:    */     }
/*  88:104 */     return null;
/*  89:    */   }
/*  90:    */   
/*  91:    */   class LineWidthPanel
/*  92:    */     extends JPanel
/*  93:    */   {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/*  94:    */     Stroke stroke;
/*  95:    */     
/*  96:    */     public LineWidthPanel(BasicStroke stroke)
/*  97:    */     {
/*  98:115 */       this.stroke = stroke;
/*  99:116 */       setPreferredSize(new Dimension(80, 16));
/* 100:    */     }
/* 101:    */     
/* 102:    */     public void paint(Graphics g)
/* 103:    */     {
/* 104:120 */       super.paint(g);
/* 105:    */       
/* 106:122 */       Graphics2D g2 = (Graphics2D)g;
/* 107:123 */       g2.setColor(Color.black);
/* 108:124 */       g2.setStroke(this.stroke);
/* 109:    */       
/* 110:126 */       g2.drawLine(1, getHeight() / 2, getWidth() - 1, getHeight() / 2);
/* 111:    */     }
/* 112:    */   }
/* 113:    */   
/* 114:    */   class JLRenderer
/* 115:    */     implements ListCellRenderer
/* 116:    */   {
/* 117:    */     public JLRenderer() {}
/* 118:    */     
/* 119:    */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 120:    */     {
/* 121:141 */       LineStyleDiscription lineStyleDiscription = (LineStyleDiscription)value;
/* 122:143 */       if (lineStyleDiscription != null)
/* 123:    */       {
/* 124:144 */         JPanel p = new JPanel();
/* 125:145 */         FlowLayout layout = new FlowLayout(0);
/* 126:146 */         layout.setHgap(1);
/* 127:147 */         layout.setVgap(1);
/* 128:148 */         p.setLayout(layout);
/* 129:150 */         if (!isSelected) {
/* 130:151 */           p.setBackground(LineStyleComboBox.this.getBackground());
/* 131:    */         } else {
/* 132:153 */           p.setBackground(SystemColor.activeCaption);
/* 133:    */         }
/* 134:    */         LineStyleComboBox.LineWidthPanel lwPanel;
/* 135:    */        // LineStyleComboBox.LineWidthPanel lwPanel;
/* 136:158 */         if (lineStyleDiscription.stroke != null) {
/* 137:159 */         //  lwPanel = new LineStyleComboBox.LineWidthPanel(LineStyleComboBox.this, lineStyleDiscription.stroke);
						lwPanel = new LineStyleComboBox.LineWidthPanel(lineStyleDiscription.stroke);
/* 138:    */         } else {
/* 139:161 */          // lwPanel = new LineStyleComboBox.LineWidthPanel(LineStyleComboBox.this, new BasicStroke(1.0F));
						lwPanel = new LineStyleComboBox.LineWidthPanel(new BasicStroke(1.0F));
/* 140:    */         }
/* 141:163 */         lwPanel.setBackground(LineStyleComboBox.this.getBackground());
/* 142:164 */         p.add(lwPanel);
/* 143:    */         
/* 144:    */ 
/* 145:167 */         JPanel box = new JPanel();
/* 146:168 */         box.setPreferredSize(new Dimension(4, 0));
/* 147:169 */         p.add(box);
/* 148:    */         
/* 149:    */ 
/* 150:172 */         JLabel l = new JLabel();
/* 151:173 */         l.setText(lineStyleDiscription.name);
/* 152:174 */         if (!isSelected) {
/* 153:175 */           l.setForeground(SystemColor.controlText);
/* 154:    */         } else {
/* 155:177 */           l.setForeground(Color.white);
/* 156:    */         }
/* 157:179 */         p.add(l);
/* 158:    */         
/* 159:181 */         return p;
/* 160:    */       }
/* 161:183 */       return new JLabel("");
/* 162:    */     }
/* 163:    */   }
/* 164:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.LineStyleComboBox
 * JD-Core Version:    0.7.0.1
 */