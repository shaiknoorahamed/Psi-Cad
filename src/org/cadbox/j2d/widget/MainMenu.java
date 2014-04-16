/*   1:    */ package org.cadbox.j2d.widget;
/*   2:    */ 
/*   3:    */ import java.awt.event.ActionEvent;
/*   4:    */ import java.awt.event.ActionListener;
/*   5:    */ import java.util.Collection;
/*   6:    */ import javax.swing.JMenu;
/*   7:    */ import javax.swing.JMenuBar;
/*   8:    */ import javax.swing.JMenuItem;
/*   9:    */ import javax.swing.JSeparator;
/*  10:    */ import javax.swing.KeyStroke;
/*  11:    */ import org.cadbox.Command;
/*  12:    */ import org.cadbox.Document;
/*  13:    */ import org.cadbox.Observer;
/*  14:    */ import org.cadbox.SimpleURManager;
/*  15:    */ import org.cadbox.UREngine;
/*  16:    */ import org.cadbox.URManager;
/*  17:    */ import org.cadbox.j2d.CadApplication;
/*  18:    */ import org.cadbox.j2d.CadDocument;
/*  19:    */ import org.cadbox.j2d.ClipBoard;
/*  20:    */ import org.cadbox.j2d.command.DocumentCommandFactory;
/*  21:    */ import org.cadbox.j2d.command.ShowLayerDialogCommand;
/*  22:    */ import org.cadbox.j2d.scale.CadDrawingEngine;
/*  23:    */ import org.cadbox.j2d.widget.dialog.AboutDialog;
/*  24:    */ import org.cadbox.j2d.widget.dialog.DialogManager;
/*  25:    */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/*  26:    */ 
/*  27:    */ public class MainMenu
/*  28:    */   extends JMenuBar
/*  29:    */   implements Observer
/*  30:    */ {
/*  31:    */   private CadApplication application;
/*  32:    */   private DocumentCommandFactory documentCommand;
/*  33:    */   private JMenuItem aboutMenu;
/*  34:    */   private JMenuItem closeAllMenu;
/*  35:    */   private JMenuItem closeMenu;
/*  36:    */   private JMenuItem colorMenu;
/*  37:    */   private JMenuItem copyMenu;
/*  38:    */   private JMenuItem cutMenu;
/*  39:    */   private JMenu editMenu;
/*  40:    */   private JMenuItem exitMenu;
/*  41:    */   private JMenu fileMenu;
/*  42:    */   private JMenu formatMenu;
/*  43:    */   private JMenu helpMenu;
/*  44:    */   private JMenuItem jMenuItem1;
/*  45:    */   private JMenuItem jMenuItem2;
/*  46:    */   private JMenuItem jMenuItem3;
/*  47:    */   private JSeparator separator1;
/*  48:    */   private JSeparator separator2;
/*  49:    */   private JSeparator separator3;
/*  50:    */   private JSeparator separator4;
/*  51:    */   private JMenuItem layerMenu;
/*  52:    */   private JMenuItem lineTypeMenu;
/*  53:    */   private JMenuItem lineWeightMenu;
/*  54:    */   private JMenu newMenu;
/*  55:    */   private JMenuItem openMenu;
/*  56:    */   private JMenuItem pasteMenu;
/*  57:    */   private JMenuItem redoMenu;
/*  58:    */   private JMenuItem saveAsMenu;
/*  59:    */   private JMenuItem saveMenu;
/*  60:    */   private JMenuItem printMenu;
/*  61:    */   private JMenuItem undoMenu;
/*  62:    */   
/*  63:    */   public MainMenu(CadApplication application, DocumentCommandFactory documentCommand)
/*  64:    */   {
/*  65: 60 */     this.application = application;
/*  66: 61 */     this.documentCommand = documentCommand;
/*  67: 62 */     initComponents();
/*  68:    */   }
/*  69:    */   
/*  70:    */   private void initComponents()
/*  71:    */   {
/*  72: 67 */     this.fileMenu = new JMenu();
/*  73: 68 */     this.newMenu = new JMenu();
/*  74: 69 */     this.jMenuItem1 = new JMenuItem();
/*  75: 70 */     this.jMenuItem2 = new JMenuItem();
/*  76: 71 */     this.jMenuItem3 = new JMenuItem();
/*  77: 72 */     this.openMenu = new JMenuItem();
/*  78: 73 */     this.saveMenu = new JMenuItem();
/*  79: 74 */     this.saveAsMenu = new JMenuItem();
/*  80: 75 */     this.printMenu = new JMenuItem();
/*  81: 76 */     this.separator2 = new JSeparator();
/*  82: 77 */     this.closeMenu = new JMenuItem();
/*  83: 78 */     this.closeAllMenu = new JMenuItem();
/*  84: 79 */     this.separator1 = new JSeparator();
/*  85: 80 */     this.separator4 = new JSeparator();
/*  86: 81 */     this.exitMenu = new JMenuItem();
/*  87: 82 */     this.editMenu = new JMenu();
/*  88: 83 */     this.undoMenu = new JMenuItem();
/*  89: 84 */     this.redoMenu = new JMenuItem();
/*  90: 85 */     this.separator3 = new JSeparator();
/*  91: 86 */     this.cutMenu = new JMenuItem();
/*  92: 87 */     this.copyMenu = new JMenuItem();
/*  93: 88 */     this.pasteMenu = new JMenuItem();
/*  94: 89 */     this.formatMenu = new JMenu();
/*  95: 90 */     this.layerMenu = new JMenuItem();
/*  96: 91 */     this.colorMenu = new JMenuItem();
/*  97: 92 */     this.lineTypeMenu = new JMenuItem();
/*  98: 93 */     this.lineWeightMenu = new JMenuItem();
/*  99: 94 */     this.helpMenu = new JMenu();
/* 100: 95 */     this.aboutMenu = new JMenuItem();
/* 101:    */     
/* 102:    */ 
/* 103: 98 */     this.fileMenu.setText("File");
/* 104:    */     
/* 105:100 */     this.newMenu.setText("New File...");
/* 106:101 */     this.newMenu.setName("newMenu");
/* 107:    */     
/* 108:103 */     this.jMenuItem1.setText("2Cad File");
/* 109:    */     
/* 110:105 */     this.jMenuItem1.addActionListener(new ActionListener()
/* 111:    */     {
/* 112:    */       public void actionPerformed(ActionEvent e)
/* 113:    */       {
/* 114:107 */         MainMenu.this.documentCommand.createNewDocumentCommand().execute();
/* 115:    */       }
/* 116:109 */     });
/* 117:110 */     this.newMenu.add(this.jMenuItem1);
/* 118:    */     
/* 119:112 */     this.jMenuItem2.setText("Earth File");
/* 120:113 */     this.jMenuItem2.addActionListener(new ActionListener()
/* 121:    */     {
/* 122:    */       public void actionPerformed(ActionEvent e) {}
/* 123:116 */     });
/* 124:117 */     this.newMenu.add(this.jMenuItem2);
/* 125:    */     
/* 126:119 */     this.jMenuItem3.setText("Test File");
/* 127:120 */     this.jMenuItem3.addActionListener(new ActionListener()
/* 128:    */     {
/* 129:    */       public void actionPerformed(ActionEvent e) {}
/* 130:123 */     });
/* 131:124 */     this.newMenu.add(this.jMenuItem3);
/* 132:    */     
/* 133:126 */     this.fileMenu.add(this.newMenu);
/* 134:    */     
/* 135:128 */     this.openMenu.setAccelerator(KeyStroke.getKeyStroke(79, 2));
/* 136:129 */     this.openMenu.setText("Open File...");
/* 137:130 */     this.openMenu.setName("openMenu");
/* 138:131 */     this.openMenu.addActionListener(new ActionListener()
/* 139:    */     {
/* 140:    */       public void actionPerformed(ActionEvent e)
/* 141:    */       {
/* 142:133 */         MainMenu.this.documentCommand.createOpenDocumentCommand().execute();
/* 143:    */       }
/* 144:135 */     });
/* 145:136 */     this.fileMenu.add(this.openMenu);
/* 146:    */     
/* 147:138 */     this.saveMenu.setAccelerator(KeyStroke.getKeyStroke(83, 2));
/* 148:139 */     this.saveMenu.setText("Save");
/* 149:140 */     this.saveMenu.setName("saveMenu");
/* 150:141 */     this.saveMenu.addActionListener(new ActionListener()
/* 151:    */     {
/* 152:    */       public void actionPerformed(ActionEvent e) {}
/* 153:144 */     });
/* 154:145 */     this.fileMenu.add(this.saveMenu);
/* 155:    */     
/* 156:147 */     this.saveAsMenu.setAccelerator(KeyStroke.getKeyStroke(83, 3));
/* 157:148 */     this.saveAsMenu.setText("Save As...");
/* 158:149 */     this.saveAsMenu.setName("saveAsMenu");
/* 159:150 */     this.saveAsMenu.addActionListener(new ActionListener()
/* 160:    */     {
/* 161:    */       public void actionPerformed(ActionEvent e) {}
/* 162:153 */     });
/* 163:154 */     this.fileMenu.add(this.saveAsMenu);
/* 164:    */     
/* 165:156 */     this.fileMenu.add(this.separator2);
/* 166:    */     
/* 167:158 */     this.printMenu.setText("Print...");
/* 168:159 */     this.printMenu.setName("printMenu");
/* 169:160 */     this.printMenu.addActionListener(new ActionListener()
/* 170:    */     {
/* 171:    */       public void actionPerformed(ActionEvent e)
/* 172:    */       {
/* 173:162 */         MainMenu.this.documentCommand.createPrintCommand().execute();
/* 174:    */       }
/* 175:164 */     });
/* 176:165 */     this.fileMenu.add(this.printMenu);
/* 177:    */     
/* 178:167 */     this.fileMenu.add(this.separator4);
/* 179:    */     
/* 180:    */ 
/* 181:170 */     this.closeMenu.setText("Close");
/* 182:171 */     this.closeMenu.setName("closeMenu");
/* 183:172 */     this.closeMenu.addActionListener(new ActionListener()
/* 184:    */     {
/* 185:    */       public void actionPerformed(ActionEvent e)
/* 186:    */       {
/* 187:174 */         MainMenu.this.documentCommand.createCloseDocumentCommand().execute();
/* 188:    */       }
/* 189:176 */     });
/* 190:177 */     this.fileMenu.add(this.closeMenu);
/* 191:    */     
/* 192:179 */     this.closeAllMenu.setAccelerator(KeyStroke.getKeyStroke(87, 3));
/* 193:180 */     this.closeAllMenu.setText("Close All");
/* 194:181 */     this.closeAllMenu.setName("closeAllMenu");
/* 195:182 */     this.closeAllMenu.addActionListener(new ActionListener()
/* 196:    */     {
/* 197:    */       public void actionPerformed(ActionEvent e)
/* 198:    */       {
/* 199:184 */         MainMenu.this.documentCommand.createCloseAllCommand().execute();
/* 200:    */       }
/* 201:186 */     });
/* 202:187 */     this.fileMenu.add(this.closeAllMenu);
/* 203:188 */     this.fileMenu.add(this.separator1);
/* 204:    */     
/* 205:190 */     this.exitMenu.setAccelerator(KeyStroke.getKeyStroke(81, 2));
/* 206:191 */     this.exitMenu.setText("Exit");
/* 207:192 */     this.exitMenu.setName("exitMenu");
/* 208:193 */     this.exitMenu.addActionListener(new ActionListener()
/* 209:    */     {
/* 210:    */       public void actionPerformed(ActionEvent e)
/* 211:    */       {
/* 212:195 */         MainMenu.this.documentCommand.createExitCommand().execute();
/* 213:    */       }
/* 214:197 */     });
/* 215:198 */     this.fileMenu.add(this.exitMenu);
/* 216:    */     
/* 217:200 */     add(this.fileMenu);
/* 218:    */     
/* 219:202 */     this.editMenu.setText("Edit");
/* 220:    */     
/* 221:    */ 
/* 222:205 */     this.undoMenu.setText("Undo");
/* 223:206 */     this.undoMenu.setName("undoMenu");
/* 224:207 */     this.undoMenu.setAccelerator(KeyStroke.getKeyStroke(90, 2));
/* 225:208 */     this.undoMenu.addActionListener(new ActionListener()
/* 226:    */     {
/* 227:    */       public void actionPerformed(ActionEvent e)
/* 228:    */       {
/* 229:210 */         MainMenu.this.documentCommand.createUndoCommand().execute();
/* 230:    */       }
/* 231:212 */     });
/* 232:213 */     this.editMenu.add(this.undoMenu);
/* 233:    */     
/* 234:215 */     this.redoMenu.setAccelerator(KeyStroke.getKeyStroke(89, 2));
/* 235:216 */     this.redoMenu.setText("Redo");
/* 236:217 */     this.redoMenu.setName("redoMenu");
/* 237:218 */     this.redoMenu.addActionListener(new ActionListener()
/* 238:    */     {
/* 239:    */       public void actionPerformed(ActionEvent e)
/* 240:    */       {
/* 241:220 */         MainMenu.this.documentCommand.createRedoCommand().execute();
/* 242:    */       }
/* 243:222 */     });
/* 244:223 */     this.editMenu.add(this.redoMenu);
/* 245:    */     
/* 246:225 */     this.editMenu.add(this.separator2);
/* 247:    */     
/* 248:227 */     this.cutMenu.setAccelerator(KeyStroke.getKeyStroke(88, 2));
/* 249:228 */     this.cutMenu.setText("Cut");
/* 250:229 */     this.cutMenu.setName("cutMenu");
/* 251:230 */     this.cutMenu.addActionListener(new ActionListener()
/* 252:    */     {
/* 253:    */       public void actionPerformed(ActionEvent e)
/* 254:    */       {
/* 255:232 */         MainMenu.this.documentCommand.createCutCommand().execute();
/* 256:    */       }
/* 257:234 */     });
/* 258:235 */     this.editMenu.add(this.cutMenu);
/* 259:    */     
/* 260:237 */     this.copyMenu.setAccelerator(KeyStroke.getKeyStroke(67, 2));
/* 261:238 */     this.copyMenu.setText("Copy");
/* 262:239 */     this.copyMenu.setName("copyMenu");
/* 263:240 */     this.copyMenu.addActionListener(new ActionListener()
/* 264:    */     {
/* 265:    */       public void actionPerformed(ActionEvent e)
/* 266:    */       {
/* 267:242 */         MainMenu.this.documentCommand.createCopyCommand().execute();
/* 268:    */       }
/* 269:244 */     });
/* 270:245 */     this.editMenu.add(this.copyMenu);
/* 271:    */     
/* 272:247 */     this.pasteMenu.setAccelerator(KeyStroke.getKeyStroke(67, 2));
/* 273:248 */     this.pasteMenu.setText("Paste");
/* 274:249 */     this.pasteMenu.setName("pasteMenu");
/* 275:250 */     this.pasteMenu.addActionListener(new ActionListener()
/* 276:    */     {
/* 277:    */       public void actionPerformed(ActionEvent e)
/* 278:    */       {
/* 279:252 */         MainMenu.this.documentCommand.createPasteCommand().execute();
/* 280:    */       }
/* 281:254 */     });
/* 282:255 */     this.editMenu.add(this.pasteMenu);
/* 283:    */     
/* 284:257 */     add(this.editMenu);
/* 285:    */     
/* 286:259 */     this.formatMenu.setText("Format");
/* 287:    */     
/* 288:261 */     this.layerMenu.setText("Layer...");
/* 289:262 */     this.layerMenu.addActionListener(new ActionListener()
/* 290:    */     {
/* 291:    */       public void actionPerformed(ActionEvent e)
/* 292:    */       {
/* 293:264 */         ShowLayerDialogCommand cmd = new ShowLayerDialogCommand(MainMenu.this.application);
/* 294:265 */         cmd.execute();
/* 295:    */       }
/* 296:267 */     });
/* 297:268 */     this.formatMenu.add(this.layerMenu);
/* 298:    */     
/* 299:270 */     this.colorMenu.setText("Color...");
/* 300:271 */     this.colorMenu.addActionListener(new ActionListener()
/* 301:    */     {
/* 302:    */       public void actionPerformed(ActionEvent e)
/* 303:    */       {
/* 304:273 */         Command cmd = MainMenu.this.application.getDocumentCommandFactory().createShowColorDialogCommand();
/* 305:274 */         cmd.execute();
/* 306:    */       }
/* 307:276 */     });
/* 308:277 */     this.formatMenu.add(this.colorMenu);
/* 309:    */     
/* 310:    */ 
/* 311:    */ 
/* 312:    */ 
/* 313:    */ 
/* 314:283 */     this.lineWeightMenu.setText("Lineweight...");
/* 315:284 */     this.lineWeightMenu.addActionListener(new ActionListener()
/* 316:    */     {
/* 317:    */       public void actionPerformed(ActionEvent e)
/* 318:    */       {
/* 319:286 */         Command cmd = MainMenu.this.application.getDocumentCommandFactory().createShowLineWeightDialogCommand();
/* 320:287 */         cmd.execute();
/* 321:    */       }
/* 322:289 */     });
/* 323:290 */     this.formatMenu.add(this.lineWeightMenu);
/* 324:    */     
/* 325:292 */     add(this.formatMenu);
/* 326:    */     
/* 327:294 */     this.aboutMenu.setText("About");
/* 328:295 */     this.aboutMenu.addActionListener(new ActionListener()
/* 329:    */     {
/* 330:    */       public void actionPerformed(ActionEvent e)
/* 331:    */       {
/* 332:297 */         AboutDialog aboutDialog = MainMenu.this.application.getDialogManager().getAboutDialog();
/* 333:298 */         aboutDialog.setVisible(true);
/* 334:    */       }
/* 335:301 */     });
/* 336:302 */     this.helpMenu.setText("Help");
/* 337:303 */     this.helpMenu.add(this.aboutMenu);
/* 338:304 */     add(this.helpMenu);
/* 339:    */   }
/* 340:    */   
/* 341:    */   public void update()
/* 342:    */   {
/* 343:309 */     Document doc = this.application.getCurrentDocument();
/* 344:311 */     if (this.application.getDocumentCount() > 0)
/* 345:    */     {
/* 346:312 */       this.saveMenu.setEnabled(true);
/* 347:313 */       this.saveAsMenu.setEnabled(true);
/* 348:314 */       this.closeMenu.setEnabled(true);
/* 349:315 */       this.closeAllMenu.setEnabled(true);
/* 350:    */     }
/* 351:    */     else
/* 352:    */     {
/* 353:317 */       this.saveMenu.setEnabled(false);
/* 354:318 */       this.saveAsMenu.setEnabled(false);
/* 355:319 */       this.closeMenu.setEnabled(false);
/* 356:320 */       this.closeAllMenu.setEnabled(false);
/* 357:    */     }
/* 358:323 */     if (doc != null)
/* 359:    */     {
/* 360:324 */       UREngine urEngine = SimpleURManager.getInstance().getUREngine(doc);
/* 361:325 */       if (urEngine != null)
/* 362:    */       {
/* 363:326 */         if (urEngine.canUndo()) {
/* 364:327 */           this.undoMenu.setEnabled(true);
/* 365:    */         } else {
/* 366:329 */           this.undoMenu.setEnabled(false);
/* 367:    */         }
/* 368:331 */         if (urEngine.canRedo()) {
/* 369:332 */           this.redoMenu.setEnabled(true);
/* 370:    */         } else {
/* 371:334 */           this.redoMenu.setEnabled(false);
/* 372:    */         }
/* 373:    */       }
/* 374:    */     }
/* 375:    */     else
/* 376:    */     {
/* 377:337 */       this.undoMenu.setEnabled(false);
/* 378:338 */       this.redoMenu.setEnabled(false);
/* 379:    */     }
/* 380:341 */     if (this.application.getDocumentCount() > 0)
/* 381:    */     {
/* 382:343 */       if (ClipBoard.getInstance().isEmpty()) {
/* 383:344 */         this.pasteMenu.setEnabled(false);
/* 384:    */       } else {
/* 385:346 */         this.pasteMenu.setEnabled(true);
/* 386:    */       }
/* 387:348 */       CadDocument cadDocument = (CadDocument)this.application.getCurrentDocument();
/* 388:349 */       CadDocumentWindow wnd = this.application.getCadDocumentWindow(cadDocument);
/* 389:350 */       if (!wnd.getDrawingEngine().getSelectedShapeList().isEmpty())
/* 390:    */       {
/* 391:351 */         this.copyMenu.setEnabled(true);
/* 392:352 */         this.cutMenu.setEnabled(true);
/* 393:    */       }
/* 394:    */       else
/* 395:    */       {
/* 396:354 */         this.copyMenu.setEnabled(false);
/* 397:355 */         this.cutMenu.setEnabled(false);
/* 398:    */       }
/* 399:    */     }
/* 400:    */     else
/* 401:    */     {
/* 402:358 */       this.copyMenu.setEnabled(false);
/* 403:359 */       this.cutMenu.setEnabled(false);
/* 404:360 */       this.pasteMenu.setEnabled(false);
/* 405:    */     }
/* 406:    */   }
/* 407:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.MainMenu
 * JD-Core Version:    0.7.0.1
 */