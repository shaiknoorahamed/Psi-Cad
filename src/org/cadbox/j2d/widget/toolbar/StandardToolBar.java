/*   1:    */ package org.cadbox.j2d.widget.toolbar;
/*   2:    */ 
/*   3:    */ import java.awt.Dimension;
/*   4:    */ import java.awt.event.ActionEvent;
/*   5:    */ import java.awt.event.ActionListener;
/*   6:    */ import java.util.Collection;
/*   7:    */ import javax.swing.ImageIcon;
/*   8:    */ import javax.swing.JButton;
/*   9:    */ import org.cadbox.Command;
/*  10:    */ import org.cadbox.Document;
/*  11:    */ import org.cadbox.Observer;
/*  12:    */ import org.cadbox.SimpleURManager;
/*  13:    */ import org.cadbox.UREngine;
/*  14:    */ import org.cadbox.URManager;
/*  15:    */ import org.cadbox.j2d.CadApplication;
/*  16:    */ import org.cadbox.j2d.CadDocument;
/*  17:    */ import org.cadbox.j2d.ClipBoard;
/*  18:    */ import org.cadbox.j2d.command.DocumentCommandFactory;
/*  19:    */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  20:    */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/*  21:    */ import org.cadbox.plaf.ToolBar;
/*  22:    */ 
/*  23:    */ public class StandardToolBar
/*  24:    */   extends ToolBar
/*  25:    */   implements Observer
/*  26:    */ {
/*  27:    */   private CadApplication application;
/*  28:    */   private DocumentCommandFactory documentCommandFactory;
/*  29:    */   private JButton newButton;
/*  30:    */   private JButton openButton;
/*  31:    */   private JButton saveButton;
/*  32:    */   private JButton cutButton;
/*  33:    */   private JButton copyButton;
/*  34:    */   private JButton pasteButton;
/*  35:    */   private JButton undoButton;
/*  36:    */   private JButton redoButton;
/*  37:    */   
/*  38:    */   public StandardToolBar(CadApplication application, DocumentCommandFactory documentCommandFactory)
/*  39:    */   {
/*  40: 66 */     this.application = application;
/*  41: 67 */     this.documentCommandFactory = documentCommandFactory;
/*  42: 68 */     init();
/*  43:    */   }
/*  44:    */   
/*  45:    */   private void init()
/*  46:    */   {
/*  47: 73 */     this.newButton = new JButton();
/*  48: 74 */     this.openButton = new JButton();
/*  49: 75 */     this.saveButton = new JButton();
/*  50: 76 */     this.cutButton = new JButton();
/*  51: 77 */     this.copyButton = new JButton();
/*  52: 78 */     this.pasteButton = new JButton();
/*  53: 79 */     this.undoButton = new JButton();
/*  54: 80 */     this.redoButton = new JButton();
/*  55:    */     
/*  56:    */ 
/*  57: 83 */     setRollover(true);
/*  58: 84 */     setFocusable(false);
/*  59: 85 */     setName("Standard");
/*  60: 86 */     setVerifyInputWhenFocusTarget(false);
/*  61:    */     
/*  62: 88 */     this.newButton.setToolTipText("New File...");
/*  63: 89 */     this.newButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/new.png")));
/*  64: 90 */     this.newButton.setFocusable(false);
/*  65: 91 */     this.newButton.setMaximumSize(new Dimension(25, 25));
/*  66: 92 */     this.newButton.setMinimumSize(new Dimension(25, 25));
/*  67: 93 */     this.newButton.setPreferredSize(new Dimension(25, 25));
/*  68: 94 */     this.newButton.addActionListener(new ActionListener()
/*  69:    */     {
/*  70:    */       public void actionPerformed(ActionEvent evt)
/*  71:    */       {
/*  72: 96 */         StandardToolBar.this.documentCommandFactory.createNewDocumentCommand().execute();
/*  73:    */       }
/*  74: 98 */     });
/*  75: 99 */     add(this.newButton);
/*  76:    */     
/*  77:101 */     this.openButton.setToolTipText("Open");
/*  78:102 */     this.openButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/open.png")));
/*  79:103 */     this.openButton.setFocusable(false);
/*  80:104 */     this.openButton.setHorizontalTextPosition(0);
/*  81:105 */     this.openButton.setMaximumSize(new Dimension(25, 25));
/*  82:106 */     this.openButton.setMinimumSize(new Dimension(25, 25));
/*  83:107 */     this.openButton.setPreferredSize(new Dimension(25, 25));
/*  84:108 */     this.openButton.setVerticalTextPosition(3);
/*  85:109 */     this.openButton.addActionListener(new ActionListener()
/*  86:    */     {
/*  87:    */       public void actionPerformed(ActionEvent evt)
/*  88:    */       {
/*  89:111 */         StandardToolBar.this.documentCommandFactory.createOpenDocumentCommand().execute();
/*  90:    */       }
/*  91:113 */     });
/*  92:114 */     add(this.openButton);
/*  93:    */     
/*  94:116 */     this.saveButton.setToolTipText("Save");
/*  95:117 */     this.saveButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/save.png")));
/*  96:118 */     this.saveButton.setFocusable(false);
/*  97:119 */     this.saveButton.setHorizontalTextPosition(0);
/*  98:120 */     this.saveButton.setMaximumSize(new Dimension(25, 25));
/*  99:121 */     this.saveButton.setMinimumSize(new Dimension(25, 25));
/* 100:122 */     this.saveButton.setPreferredSize(new Dimension(25, 25));
/* 101:123 */     this.saveButton.setVerticalTextPosition(3);
/* 102:124 */     this.saveButton.addActionListener(new ActionListener()
/* 103:    */     {
/* 104:    */       public void actionPerformed(ActionEvent evt)
/* 105:    */       {
/* 106:126 */         StandardToolBar.this.documentCommandFactory.createSaveDocumentCommand().execute();
/* 107:    */       }
/* 108:128 */     });
/* 109:129 */     add(this.saveButton);
/* 110:    */     
/* 111:131 */     addSeparator();
/* 112:    */     
/* 113:133 */     this.cutButton.setName("cutButton");
/* 114:134 */     this.cutButton.setToolTipText("Cut");
/* 115:135 */     this.cutButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/cut.png")));
/* 116:136 */     this.cutButton.setFocusable(false);
/* 117:137 */     this.cutButton.setMaximumSize(new Dimension(25, 25));
/* 118:138 */     this.cutButton.setMinimumSize(new Dimension(25, 25));
/* 119:139 */     this.cutButton.setPreferredSize(new Dimension(25, 25));
/* 120:140 */     this.cutButton.addActionListener(new ActionListener()
/* 121:    */     {
/* 122:    */       public void actionPerformed(ActionEvent evt)
/* 123:    */       {
/* 124:142 */         StandardToolBar.this.documentCommandFactory.createCutCommand().execute();
/* 125:    */       }
/* 126:144 */     });
/* 127:145 */     add(this.cutButton);
/* 128:    */     
/* 129:147 */     this.copyButton.setName("copyButton");
/* 130:148 */     this.copyButton.setToolTipText("Copy");
/* 131:149 */     this.copyButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/copy.png")));
/* 132:150 */     this.copyButton.setFocusable(false);
/* 133:151 */     this.copyButton.setMaximumSize(new Dimension(25, 25));
/* 134:152 */     this.copyButton.setMinimumSize(new Dimension(25, 25));
/* 135:153 */     this.copyButton.setPreferredSize(new Dimension(25, 25));
/* 136:154 */     this.copyButton.addActionListener(new ActionListener()
/* 137:    */     {
/* 138:    */       public void actionPerformed(ActionEvent evt)
/* 139:    */       {
/* 140:156 */         StandardToolBar.this.documentCommandFactory.createCopyCommand().execute();
/* 141:    */       }
/* 142:158 */     });
/* 143:159 */     add(this.copyButton);
/* 144:    */     
/* 145:161 */     this.pasteButton.setName("pasteButton");
/* 146:162 */     this.pasteButton.setToolTipText("Paste");
/* 147:163 */     this.pasteButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/paste.png")));
/* 148:164 */     this.pasteButton.setFocusable(false);
/* 149:165 */     this.pasteButton.setMaximumSize(new Dimension(25, 25));
/* 150:166 */     this.pasteButton.setMinimumSize(new Dimension(25, 25));
/* 151:167 */     this.pasteButton.setPreferredSize(new Dimension(25, 25));
/* 152:168 */     this.pasteButton.addActionListener(new ActionListener()
/* 153:    */     {
/* 154:    */       public void actionPerformed(ActionEvent evt)
/* 155:    */       {
/* 156:170 */         StandardToolBar.this.documentCommandFactory.createPasteCommand().execute();
/* 157:    */       }
/* 158:172 */     });
/* 159:173 */     add(this.pasteButton);
/* 160:    */     
/* 161:175 */     addSeparator();
/* 162:    */     
/* 163:177 */     this.undoButton.setName("undoButton");
/* 164:178 */     this.undoButton.setToolTipText("Undo");
/* 165:179 */     this.undoButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/undo.png")));
/* 166:180 */     this.undoButton.setFocusable(false);
/* 167:181 */     this.undoButton.setMaximumSize(new Dimension(25, 25));
/* 168:182 */     this.undoButton.setMinimumSize(new Dimension(25, 25));
/* 169:183 */     this.undoButton.setPreferredSize(new Dimension(25, 25));
/* 170:184 */     this.undoButton.addActionListener(new ActionListener()
/* 171:    */     {
/* 172:    */       public void actionPerformed(ActionEvent evt)
/* 173:    */       {
/* 174:186 */         StandardToolBar.this.documentCommandFactory.createUndoCommand().execute();
/* 175:    */       }
/* 176:188 */     });
/* 177:189 */     add(this.undoButton);
/* 178:    */     
/* 179:191 */     this.redoButton.setName("redoButton");
/* 180:192 */     this.redoButton.setToolTipText("Redo");
/* 181:193 */     this.redoButton.setIcon(new ImageIcon(getClass().getResource("/org/cadbox/widget/resources/redo.png")));
/* 182:194 */     this.redoButton.setFocusable(false);
/* 183:195 */     this.redoButton.setMaximumSize(new Dimension(25, 25));
/* 184:196 */     this.redoButton.setMinimumSize(new Dimension(25, 25));
/* 185:197 */     this.redoButton.setPreferredSize(new Dimension(25, 25));
/* 186:198 */     this.redoButton.addActionListener(new ActionListener()
/* 187:    */     {
/* 188:    */       public void actionPerformed(ActionEvent evt)
/* 189:    */       {
/* 190:200 */         StandardToolBar.this.documentCommandFactory.createRedoCommand().execute();
/* 191:    */       }
/* 192:202 */     });
/* 193:203 */     add(this.redoButton);
/* 194:    */   }
/* 195:    */   
/* 196:    */   public void update()
/* 197:    */   {
/* 198:209 */     if (this.application.getDocumentCount() > 0) {
/* 199:210 */       this.saveButton.setEnabled(true);
/* 200:    */     } else {
/* 201:212 */       this.saveButton.setEnabled(false);
/* 202:    */     }
/* 203:214 */     Document doc = this.application.getCurrentDocument();
/* 204:215 */     if (doc != null)
/* 205:    */     {
/* 206:216 */       UREngine urEngine = SimpleURManager.getInstance().getUREngine(doc);
/* 207:217 */       if (urEngine != null)
/* 208:    */       {
/* 209:219 */         if (urEngine.canUndo()) {
/* 210:220 */           this.undoButton.setEnabled(true);
/* 211:    */         } else {
/* 212:222 */           this.undoButton.setEnabled(false);
/* 213:    */         }
/* 214:224 */         if (urEngine.canRedo()) {
/* 215:225 */           this.redoButton.setEnabled(true);
/* 216:    */         } else {
/* 217:227 */           this.redoButton.setEnabled(false);
/* 218:    */         }
/* 219:    */       }
/* 220:    */     }
/* 221:    */     else
/* 222:    */     {
/* 223:230 */       this.undoButton.setEnabled(false);
/* 224:231 */       this.redoButton.setEnabled(false);
/* 225:    */     }
/* 226:234 */     if (this.application.getDocumentCount() > 0)
/* 227:    */     {
/* 228:236 */       if (ClipBoard.getInstance().isEmpty()) {
/* 229:237 */         this.pasteButton.setEnabled(false);
/* 230:    */       } else {
/* 231:239 */         this.pasteButton.setEnabled(true);
/* 232:    */       }
/* 233:241 */       CadDocument cadDocument = (CadDocument)this.application.getCurrentDocument();
/* 234:242 */       CadDocumentWindow wnd = this.application.getCadDocumentWindow(cadDocument);
/* 235:243 */       if (!wnd.getDrawingEngine().getSelectedShapeList().isEmpty())
/* 236:    */       {
/* 237:244 */         this.copyButton.setEnabled(true);
/* 238:245 */         this.cutButton.setEnabled(true);
/* 239:    */       }
/* 240:    */       else
/* 241:    */       {
/* 242:247 */         this.copyButton.setEnabled(false);
/* 243:248 */         this.cutButton.setEnabled(false);
/* 244:    */       }
/* 245:    */     }
/* 246:    */     else
/* 247:    */     {
/* 248:251 */       this.copyButton.setEnabled(false);
/* 249:252 */       this.cutButton.setEnabled(false);
/* 250:253 */       this.pasteButton.setEnabled(false);
/* 251:    */     }
/* 252:    */   }
/* 253:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.toolbar.StandardToolBar
 * JD-Core Version:    0.7.0.1
 */