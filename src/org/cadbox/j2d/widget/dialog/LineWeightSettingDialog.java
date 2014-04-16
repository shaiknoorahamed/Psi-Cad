/*   1:    */ package org.cadbox.j2d.widget.dialog;
/*   2:    */ 
/*   3:    */ import java.awt.Component;
/*   4:    */ import java.awt.Container;
/*   5:    */ import java.awt.Frame;
/*   6:    */ import java.awt.event.ActionEvent;
/*   7:    */ import java.awt.event.ActionListener;
/*   8:    */ import java.util.Vector;
/*   9:    */ import javax.swing.AbstractListModel;
/*  10:    */ import javax.swing.BorderFactory;
/*  11:    */ import javax.swing.ButtonGroup;
/*  12:    */ import javax.swing.JButton;
/*  13:    */ import javax.swing.JDialog;
/*  14:    */ import javax.swing.JLabel;
/*  15:    */ import javax.swing.JList;
/*  16:    */ import javax.swing.JPanel;
/*  17:    */ import javax.swing.JRadioButton;
/*  18:    */ import javax.swing.JScrollPane;
/*  19:    */ import javax.swing.ListCellRenderer;
/*  20:    */ import org.cadbox.Document;
/*  21:    */ import org.cadbox.MacroCommand;
/*  22:    */ import org.cadbox.SimpleURManager;
/*  23:    */ import org.cadbox.UREngine;
/*  24:    */ import org.cadbox.URManager;
/*  25:    */ import org.cadbox.j2d.CadApplication;
/*  26:    */ import org.cadbox.j2d.CadDocument;
/*  27:    */ import org.cadbox.j2d.scale.command.LineWeightChangeCommand;
/*  28:    */ import org.cadbox.j2d.scale.command.LineWeightUnitChangeCommand;
/*  29:    */ import org.cadbox.j2d.scale.core.Context;
/*  30:    */ import org.cadbox.j2d.scale.core.LineWeight;
/*  31:    */ import org.cadbox.j2d.scale.core.LineWeightManager;
/*  32:    */ import org.cadbox.j2d.scale.core.PropertyContext;
import org.cadbox.j2d.widget.LineWeightComboBox;
/*  33:    */ import org.cadbox.j2d.widget.LineWeightComboBox.LineWidthPanel;
/*  34:    */ import org.cadbox.widget.WindowUtils;
/*  35:    */ import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/*  37:    */ 
/*  38:    */ public class LineWeightSettingDialog
/*  39:    */   extends JDialog
/*  40:    */ {
/*  41: 52 */   public static int OK_MODAL_RESULT = 0;
/*  42: 53 */   public static int CANCEL_MODAL_RESULT = 1;
/*  43: 55 */   int modalResult = CANCEL_MODAL_RESULT;
/*  44: 57 */   LineWeight currentLineWeight = null;
/*  45:    */   LineWeight[] lineWeight;
/*  46:    */   CadApplication application;
/*  47: 63 */   CadDocument document = null;
/*  48: 65 */   static LineWeightManager lineWeightManager = LineWeightManager.getInstance();
/*  49:    */   int currentType;
/*  50:    */   private JButton cancelButton;
/*  51:    */   private JLabel currentLabel;
/*  52:    */   private JRadioButton inchRadioButton;
/*  53:    */   private JList lineweightList;
/*  54:    */   private JScrollPane listScrollPane;
/*  55:    */   private JLabel mainLabel;
/*  56:    */   private JRadioButton mmRadioButton;
/*  57:    */   private JButton okButton;
/*  58:    */   private JLabel staticLabel;
/*  59:    */   private ButtonGroup unitGroup;
/*  60:    */   private JPanel unitPanel;
/*  61:    */   
/*  62:    */   public LineWeightSettingDialog(Frame parent, boolean modal, CadApplication application)
/*  63:    */   {
/*  64: 71 */     super(parent, modal);
/*  65:    */     
/*  66: 73 */     this.application = application;
/*  67:    */     
/*  68: 75 */     Vector vector = new Vector(lineWeightManager.getLineWeightList());
/*  69: 76 */     this.lineWeight = new LineWeight[vector.size()];
/*  70: 77 */     for (int i = 0; i < vector.size(); i++) {
/*  71: 78 */       this.lineWeight[i] = ((LineWeight)vector.get(i));
/*  72:    */     }
/*  73: 81 */     initComponents();
/*  74:    */     
/*  75: 83 */     this.lineweightList.setModel(new AbstractListModel()
/*  76:    */     {
/*  77:    */       public int getSize()
/*  78:    */       {
/*  79: 84 */         return LineWeightSettingDialog.this.lineWeight.length;
/*  80:    */       }
/*  81:    */       
/*  82:    */       public Object getElementAt(int i)
/*  83:    */       {
/*  84: 85 */         return LineWeightSettingDialog.this.lineWeight[i];
/*  85:    */       }
/*  86: 87 */     });
/*  87: 88 */     this.lineweightList.setSelectedIndex(0);
/*  88: 89 */     this.lineweightList.setCellRenderer(new JLRenderer());
/*  89:    */     
/*  90: 91 */     WindowUtils.moveScreenCenter(this);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public CadDocument getDocument()
/*  94:    */   {
/*  95: 99 */     Document doc = this.application.getCurrentDocument();
/*  96:100 */     if ((doc != null) && ((doc instanceof CadDocument))) {
/*  97:101 */       return (CadDocument)doc;
/*  98:    */     }
/*  99:102 */     return null;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public int showModal()
/* 103:    */   {
/* 104:107 */     this.document = getDocument();
/* 105:108 */     if (this.document == null) {
/* 106:109 */       throw new IllegalArgumentException("Need to set Document!!!");
/* 107:    */     }
/* 108:111 */     init();
/* 109:112 */     setVisible(true);
/* 110:113 */     this.document = null;
/* 111:114 */     return this.modalResult;
/* 112:    */   }
/* 113:    */   
/* 114:    */   private void init()
/* 115:    */   {
/* 116:118 */     this.currentType = lineWeightManager.getUnitType();
/* 117:    */     
/* 118:120 */     Vector vector = new Vector(lineWeightManager.getLineWeightList());
/* 119:121 */     for (int i = 0; i < vector.size(); i++) {
/* 120:122 */       this.lineWeight[i] = ((LineWeight)vector.get(i));
/* 121:    */     }
/* 122:125 */     PropertyContext context = this.document.getContext().getPropertyContext();
/* 123:126 */     LineWeight lw = context.getLineWeight();
/* 124:    */     
/* 125:128 */     int type = lineWeightManager.getUnitType();
/* 126:131 */     if (lw != null)
/* 127:    */     {
/* 128:132 */       this.lineweightList.setSelectedValue(lw, true);
/* 129:133 */       this.currentLabel.setText(lw.toString());
/* 130:    */     }
/* 131:    */     else
/* 132:    */     {
/* 133:135 */       this.lineweightList.setSelectedIndex(0);
/* 134:136 */       this.currentLabel.setText("By layer");
/* 135:    */     }
/* 136:139 */     if (type == LineWeightManager.MILIMETOR_UNITTYPE) {
/* 137:140 */       this.mmRadioButton.setSelected(true);
/* 138:    */     }
/* 139:141 */     if (type == LineWeightManager.INCH_UNITTYPE) {
/* 140:142 */       this.inchRadioButton.setSelected(true);
/* 141:    */     }
/* 142:    */   }
/* 143:    */   
/* 144:    */   private void initComponents()
/* 145:    */   {
/* 146:153 */     this.unitGroup = new ButtonGroup();
/* 147:154 */     this.okButton = new JButton();
/* 148:155 */     this.cancelButton = new JButton();
/* 149:156 */     this.unitPanel = new JPanel();
/* 150:157 */     this.inchRadioButton = new JRadioButton();
/* 151:158 */     this.mmRadioButton = new JRadioButton();
/* 152:159 */     this.mainLabel = new JLabel();
/* 153:160 */     this.listScrollPane = new JScrollPane();
/* 154:161 */     this.lineweightList = new JList();
/* 155:162 */     this.staticLabel = new JLabel();
/* 156:163 */     this.currentLabel = new JLabel();
/* 157:    */     
/* 158:165 */     getContentPane().setLayout(new AbsoluteLayout());
/* 159:    */     
/* 160:167 */     setDefaultCloseOperation(2);
/* 161:168 */     setTitle("Lineweight settings");
/* 162:169 */     setName("lineWeightDialog");
/* 163:170 */     setResizable(false);
/* 164:171 */     this.okButton.setText("OK");
/* 165:172 */     this.okButton.setName("jButton1");
/* 166:173 */     this.okButton.addActionListener(new ActionListener()
/* 167:    */     {
/* 168:    */       public void actionPerformed(ActionEvent evt)
/* 169:    */       {
/* 170:175 */         LineWeightSettingDialog.this.okButtonActionPerformed(evt);
/* 171:    */       }
/* 172:178 */     });
/* 173:179 */     getContentPane().add(this.okButton, new AbsoluteConstraints(50, 310, 80, -1));
/* 174:    */     
/* 175:181 */     this.cancelButton.setText("Cancel");
/* 176:182 */     this.cancelButton.setName("jButton2");
/* 177:183 */     this.cancelButton.addActionListener(new ActionListener()
/* 178:    */     {
/* 179:    */       public void actionPerformed(ActionEvent evt)
/* 180:    */       {
/* 181:185 */         LineWeightSettingDialog.this.cancelButtonActionPerformed(evt);
/* 182:    */       }
/* 183:188 */     });
/* 184:189 */     getContentPane().add(this.cancelButton, new AbsoluteConstraints(140, 310, 80, -1));
/* 185:    */     
/* 186:191 */     this.unitPanel.setLayout(new AbsoluteLayout());
/* 187:    */     
/* 188:193 */     this.unitPanel.setBorder(BorderFactory.createTitledBorder("Units to listing"));
/* 189:194 */     this.unitPanel.setName("jPanel1");
/* 190:195 */     this.unitGroup.add(this.inchRadioButton);
/* 191:196 */     this.inchRadioButton.setText("Inches(in)");
/* 192:197 */     this.inchRadioButton.setName("jRadioButton2");
/* 193:198 */     this.inchRadioButton.addActionListener(new ActionListener()
/* 194:    */     {
/* 195:    */       public void actionPerformed(ActionEvent evt)
/* 196:    */       {
/* 197:200 */         LineWeightSettingDialog.this.inchRadioButtonActionPerformed(evt);
/* 198:    */       }
/* 199:203 */     });
/* 200:204 */     this.unitPanel.add(this.inchRadioButton, new AbsoluteConstraints(130, 20, 80, 20));
/* 201:    */     
/* 202:206 */     this.unitGroup.add(this.mmRadioButton);
/* 203:207 */     this.mmRadioButton.setSelected(true);
/* 204:208 */     this.mmRadioButton.setText("Milimeters(mm)");
/* 205:209 */     this.mmRadioButton.setName("jRadioButton1");
/* 206:210 */     this.mmRadioButton.addActionListener(new ActionListener()
/* 207:    */     {
/* 208:    */       public void actionPerformed(ActionEvent evt)
/* 209:    */       {
/* 210:212 */         LineWeightSettingDialog.this.mmRadioButtonActionPerformed(evt);
/* 211:    */       }
/* 212:215 */     });
/* 213:216 */     this.unitPanel.add(this.mmRadioButton, new AbsoluteConstraints(10, 20, -1, -1));
/* 214:    */     
/* 215:218 */     getContentPane().add(this.unitPanel, new AbsoluteConstraints(10, 210, 220, 60));
/* 216:    */     
/* 217:220 */     this.mainLabel.setText("Lineweights:");
/* 218:221 */     getContentPane().add(this.mainLabel, new AbsoluteConstraints(20, 10, -1, -1));
/* 219:    */     
/* 220:223 */     this.listScrollPane.setViewportView(this.lineweightList);
/* 221:    */     
/* 222:225 */     getContentPane().add(this.listScrollPane, new AbsoluteConstraints(10, 30, 230, 170));
/* 223:    */     
/* 224:227 */     this.staticLabel.setText("Current lineweight:");
/* 225:228 */     getContentPane().add(this.staticLabel, new AbsoluteConstraints(70, 280, -1, -1));
/* 226:    */     
/* 227:230 */     this.currentLabel.setText("jLabel3");
/* 228:231 */     getContentPane().add(this.currentLabel, new AbsoluteConstraints(20, 280, -1, -1));
/* 229:    */     
/* 230:233 */     pack();
/* 231:    */   }
/* 232:    */   
/* 233:    */   private void updateUnit()
/* 234:    */   {
/* 235:236 */     Vector vector = new Vector(lineWeightManager.getLineWeightList());
/* 236:237 */     for (int i = 0; i < vector.size(); i++) {
/* 237:238 */       this.lineWeight[i] = ((LineWeight)vector.get(i));
/* 238:    */     }
/* 239:240 */     this.lineweightList.updateUI();
/* 240:    */   }
/* 241:    */   
/* 242:    */   private void inchRadioButtonActionPerformed(ActionEvent evt)
/* 243:    */   {
/* 244:244 */     lineWeightManager.setUnitType(LineWeightManager.INCH_UNITTYPE);
/* 245:245 */     updateUnit();
/* 246:    */   }
/* 247:    */   
/* 248:    */   private void mmRadioButtonActionPerformed(ActionEvent evt)
/* 249:    */   {
/* 250:249 */     lineWeightManager.setUnitType(LineWeightManager.MILIMETOR_UNITTYPE);
/* 251:250 */     updateUnit();
/* 252:    */   }
/* 253:    */   
/* 254:    */   private void cancelButtonActionPerformed(ActionEvent evt)
/* 255:    */   {
/* 256:254 */     this.modalResult = CANCEL_MODAL_RESULT;
/* 257:255 */     setVisible(false);
/* 258:    */   }
/* 259:    */   
/* 260:    */   private void okButtonActionPerformed(ActionEvent evt)
/* 261:    */   {
/* 262:259 */     this.modalResult = OK_MODAL_RESULT;
/* 263:260 */     MacroCommand cmd = new MacroCommand();
/* 264:    */     
/* 265:262 */     PropertyContext context = this.document.getContext().getPropertyContext();
/* 266:    */     
/* 267:    */ 
/* 268:265 */     LineWeight currentLW = context.getLineWeight();
/* 269:    */     
/* 270:267 */     LineWeight lw = (LineWeight)this.lineweightList.getSelectedValue();
/* 271:268 */     if (!lw.equals(currentLW))
/* 272:    */     {
/* 273:269 */       LineWeightChangeCommand cmdTmp = new LineWeightChangeCommand(context, lw);
/* 274:270 */       cmdTmp.execute();
/* 275:271 */       cmd.addCommand(cmdTmp);
/* 276:    */     }
/* 277:274 */     int type = LineWeightManager.MILIMETOR_UNITTYPE;
/* 278:275 */     if (this.inchRadioButton.isSelected()) {
/* 279:276 */       type = LineWeightManager.INCH_UNITTYPE;
/* 280:    */     }
/* 281:277 */     if (type != this.currentType)
/* 282:    */     {
/* 283:278 */       LineWeightUnitChangeCommand cmdTmp = new LineWeightUnitChangeCommand(type, this.currentType);
/* 284:279 */       cmdTmp.execute();
/* 285:280 */       cmd.addCommand(cmdTmp);
/* 286:    */     }
/* 287:283 */     if (cmd.getCommandCount() > 0) {
/* 288:284 */       SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/* 289:    */     }
/* 290:286 */     setVisible(false);
/* 291:    */   }
/* 292:    */   
/* 293:    */   class JLRenderer
/* 294:    */     implements ListCellRenderer
/* 295:    */   {
/* 296:    */     public JLRenderer() {}
/* 297:    */     
/* 298:    */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 299:    */     {
/* 300:313 */       LineWeight lineWeight = (LineWeight)value;
/* 301:314 */       if (lineWeight != null)
/* 302:    */       {
/* 303:315 */         LineWeightComboBox.LineWidthPanel panel = new LineWeightComboBox.LineWidthPanel(lineWeight, isSelected);
/* 304:316 */         return panel;
/* 305:    */       }
/* 306:318 */       return new JLabel("");
/* 307:    */     }
/* 308:    */   }
/* 309:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.dialog.LineWeightSettingDialog
 * JD-Core Version:    0.7.0.1
 */