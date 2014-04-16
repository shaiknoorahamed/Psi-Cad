/*   1:    */ package org.cadbox.j2d.widget.dialog;
/*   2:    */ 
/*   3:    */ import java.awt.BorderLayout;
/*   4:    */ import java.awt.Color;
/*   5:    */ import java.awt.Container;
/*   6:    */ import java.awt.Dimension;
/*   7:    */ import java.awt.FlowLayout;
/*   8:    */ import java.awt.Frame;
/*   9:    */ import java.awt.SystemColor;
/*  10:    */ import java.awt.event.ActionEvent;
/*  11:    */ import java.awt.event.ActionListener;
/*  12:    */ import java.awt.event.ComponentAdapter;
/*  13:    */ import java.awt.event.ComponentEvent;
/*  14:    */ import java.awt.event.FocusAdapter;
/*  15:    */ import java.awt.event.FocusEvent;
/*  16:    */ import java.awt.event.MouseAdapter;
/*  17:    */ import java.awt.event.MouseEvent;
/*  18:    */ import java.awt.event.MouseListener;
/*  19:    */ import java.util.ArrayList;
/*  20:    */ import java.util.Collection;
/*  21:    */ import java.util.Iterator;
/*  22:    */ import java.util.List;
/*  23:    */ import javax.swing.BorderFactory;
/*  24:    */ import javax.swing.BoxLayout;
/*  25:    */ import javax.swing.JButton;
/*  26:    */ import javax.swing.JCheckBox;
/*  27:    */ import javax.swing.JDialog;
/*  28:    */ import javax.swing.JLabel;
/*  29:    */ import javax.swing.JOptionPane;
/*  30:    */ import javax.swing.JPanel;
/*  31:    */ import javax.swing.JRootPane;
/*  32:    */ import javax.swing.JScrollPane;
/*  33:    */ import javax.swing.JTextField;
/*  34:    */ import org.cadbox.MacroCommand;
/*  35:    */ import org.cadbox.SimpleURManager;
/*  36:    */ import org.cadbox.UREngine;
/*  37:    */ import org.cadbox.URManager;
/*  38:    */ import org.cadbox.j2d.CadApplication;
/*  39:    */ import org.cadbox.j2d.CadDocument;
/*  40:    */ import org.cadbox.j2d.command.DocumentCommandFactory;
/*  41:    */ import org.cadbox.j2d.command.ShowLayerColorDialogCommand;
/*  42:    */ import org.cadbox.j2d.scale.command.AddLayerCommand;
/*  43:    */ import org.cadbox.j2d.scale.command.LayerColorChangeCommand;
/*  44:    */ import org.cadbox.j2d.scale.command.LayerEnableChangeCommand;
/*  45:    */ import org.cadbox.j2d.scale.command.LayerLineweightChangeCommand;
/*  46:    */ import org.cadbox.j2d.scale.command.LayerNameChangeCommand;
/*  47:    */ import org.cadbox.j2d.scale.command.LayerPrintChangeCommand;
/*  48:    */ import org.cadbox.j2d.scale.command.LayerVisibleChangeCommand;
/*  49:    */ import org.cadbox.j2d.scale.command.RemoveLayerCommand;
/*  50:    */ import org.cadbox.j2d.scale.command.SetCurrentLayerCommand;
/*  51:    */ import org.cadbox.j2d.scale.core.Layer;
/*  52:    */ import org.cadbox.j2d.scale.core.LineWeight;
import org.cadbox.j2d.widget.ColorComboBox;
/*  53:    */ import org.cadbox.j2d.widget.ColorComboBox.ColorPanel;
import org.cadbox.j2d.widget.LineWeightComboBox;
/*  54:    */ import org.cadbox.j2d.widget.LineWeightComboBox.LineWidthPanel;
/*  55:    */ import org.cadbox.widget.WindowUtils;
/*  56:    */ import org.jdesktop.layout.GroupLayout;
/*  57:    */ import org.jdesktop.layout.GroupLayout.ParallelGroup;
/*  58:    */ import org.jdesktop.layout.GroupLayout.SequentialGroup;
/*  59:    */ import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
/*  61:    */ 
/*  62:    */ public class LayerSettingDialog
/*  63:    */   extends JDialog
/*  64:    */ {
/*  65:    */   public static final int OK_MODAL_RESULT = 0;
/*  66:    */   public static final int CANCEL_MODAL_RESULT = 1;
/*  67: 68 */   private int modalResult = 1;
/*  68:    */   CadApplication application;
/*  69: 72 */   LayerOptionPanel currentLayerOptionPanel = null;
/*  70: 73 */   LayerOptionPanel oldLayerOptionPanel = null;
/*  71: 76 */   CadDocument document = null;
/*  72:    */   MacroCommand macroCommand;
/*  73:    */   List list;
/*  74:    */   private JButton addLayerButton;
/*  75:    */   private JButton cancelButton;
/*  76:    */   private JPanel centerPanel;
/*  77:    */   private JLabel currentLayerLabel;
/*  78:    */   private JButton deleteButton;
/*  79:    */   private JButton downButton;
/*  80:    */   private JPanel downPanel;
/*  81:    */   private JPanel headerPanel;
/*  82:    */   private JLabel jLabel1;
/*  83:    */   private JLabel jLabel2;
/*  84:    */   private JLabel jLabel3;
/*  85:    */   private JLabel jLabel4;
/*  86:    */   private JLabel jLabel5;
/*  87:    */   private JLabel jLabel6;
/*  88:    */   private JLabel jLabel7;
/*  89:    */   private JScrollPane jScrollPane1;
/*  90:    */   private JPanel layerPanel;
/*  91:    */   private JButton okButton;
/*  92:    */   private JButton setCurrentLayerButton;
/*  93:    */   private JButton upButton;
/*  94:    */   private JPanel upPanel;
/*  95:    */   
/*  96:    */   public LayerSettingDialog(Frame parent, boolean modal, CadApplication application)
/*  97:    */   {
/*  98: 84 */     super(parent, modal);
/*  99:    */     
/* 100: 86 */     this.application = application;
/* 101:    */     
/* 102: 88 */     initComponents();
/* 103:    */     
/* 104:    */ 
/* 105: 91 */     this.list = new ArrayList();
/* 106:    */     
/* 107: 93 */     getRootPane().setDefaultButton(this.okButton);
/* 108:    */     
/* 109: 95 */     setSize(640, 480);
/* 110: 96 */     WindowUtils.moveScreenCenter(this);
/* 111:    */   }
/* 112:    */   
/* 113:    */   public int showModal()
/* 114:    */   {
/* 115:101 */     init();
/* 116:102 */     setVisible(true);
/* 117:103 */     return this.modalResult;
/* 118:    */   }
/* 119:    */   
/* 120:    */   private List getLayers()
/* 121:    */   {
/* 122:108 */     List list = new ArrayList();
/* 123:109 */     Iterator iter = this.document.getLayers().iterator();
/* 124:110 */     while (iter.hasNext()) {
/* 125:111 */       list.add(iter.next());
/* 126:    */     }
/* 127:112 */     return list;
/* 128:    */   }
/* 129:    */   
/* 130:    */   private void init()
/* 131:    */   {
/* 132:117 */     if ((this.application.getCurrentDocument() instanceof CadDocument)) {
/* 133:118 */       this.document = ((CadDocument)this.application.getCurrentDocument());
/* 134:    */     }
/* 135:120 */     this.macroCommand = new MacroCommand();
/* 136:    */     
/* 137:122 */     clearAllLayerPanel();
/* 138:    */     
/* 139:124 */     List list = getLayers();
/* 140:125 */     for (int i = 0; i < list.size(); i++)
/* 141:    */     {
/* 142:127 */       Layer layer = (Layer)list.get(i);
/* 143:128 */       addLayerPanel(layer);
/* 144:    */     }
/* 145:131 */     this.currentLayerLabel.setText(this.document.getCurrentLayer().getName());
/* 146:    */     
/* 147:133 */     refresh();
/* 148:    */   }
/* 149:    */   
/* 150:    */   private void clearAllLayerPanel()
/* 151:    */   {
/* 152:137 */     this.list.clear();
/* 153:138 */     this.layerPanel.removeAll();
/* 154:    */   }
/* 155:    */   
/* 156:    */   private void refresh()
/* 157:    */   {
/* 158:150 */     this.layerPanel.updateUI();
/* 159:    */   }
/* 160:    */   
/* 161:    */   private void setActivePanel(LayerOptionPanel newPanel)
/* 162:    */   {
/* 163:154 */     this.currentLayerOptionPanel = newPanel;
/* 164:155 */     if (this.oldLayerOptionPanel != null) {
/* 165:156 */       this.oldLayerOptionPanel.setActive(false);
/* 166:    */     }
/* 167:157 */     newPanel.setActive(true);
/* 168:158 */     this.oldLayerOptionPanel = newPanel;
/* 169:    */   }
/* 170:    */   
/* 171:    */   private class LayerListner
/* 172:    */     implements MouseListener
/* 173:    */   {
/* 174:    */     private LayerListner() {}
/* 175:    */     
/* 176:    */     public void mouseClicked(MouseEvent e)
/* 177:    */     {
/* 178:164 */       LayerSettingDialog.LayerOptionPanel panel = (LayerSettingDialog.LayerOptionPanel)e.getSource();
/* 179:165 */       LayerSettingDialog.this.setActivePanel(panel);
/* 180:    */     }
/* 181:    */     
/* 182:    */     public void mouseEntered(MouseEvent e) {}
/* 183:    */     
/* 184:    */     public void mouseExited(MouseEvent e) {}
/* 185:    */     
/* 186:    */     public void mousePressed(MouseEvent e) {}
/* 187:    */     
/* 188:    */     public void mouseReleased(MouseEvent e) {}
/* 189:    */   }
/* 190:    */   
/* 191:    */   private void addLayerPanel(Layer layer)
/* 192:    */   {
/* 193:179 */     LayerOptionPanel panel = new LayerOptionPanel(layer);
/* 194:180 */     panel.setActive(true);
/* 195:181 */     panel.addMouseListener(new LayerListner());
/* 196:    */     
/* 197:183 */     setActivePanel(panel);
/* 198:    */     
/* 199:185 */     this.list.add(panel);
/* 200:186 */     this.layerPanel.add(panel);
/* 201:    */   }
/* 202:    */   
/* 203:    */   private void removeLayerPanel(LayerOptionPanel panel)
/* 204:    */   {
/* 205:191 */     if (this.list.size() > 1)
/* 206:    */     {
/* 207:193 */       int index = this.list.indexOf(panel);
/* 208:    */       
/* 209:195 */       this.layerPanel.remove(panel);
/* 210:196 */       this.list.remove(panel);
/* 211:    */       
/* 212:    */ 
/* 213:199 */       Layer clayer = this.document.getCurrentLayer();
/* 214:200 */       if (clayer != null) {
/* 215:201 */         for (int i = 0; i < this.layerPanel.getComponentCount(); i++)
/* 216:    */         {
/* 217:202 */           LayerOptionPanel curr = (LayerOptionPanel)this.layerPanel.getComponent(i);
/* 218:203 */           if (curr.getLayer() == clayer)
/* 219:    */           {
/* 220:204 */             setActivePanel(curr);
/* 221:205 */             this.currentLayerLabel.setText(curr.getLayerName());
/* 222:    */           }
/* 223:    */         }
/* 224:    */       }
/* 225:    */     }
/* 226:    */   }
/* 227:    */   
/* 228:    */   private void addLayer()
/* 229:    */   {
/* 230:215 */     Layer layer = new Layer(this.document);
/* 231:216 */     layer.setName("Layer");
/* 232:    */     
/* 233:    */ 
/* 234:219 */     AddLayerCommand cmd = new AddLayerCommand(this.document, layer);
/* 235:220 */     cmd.execute();
/* 236:221 */     this.macroCommand.addCommand(cmd);
/* 237:    */     
/* 238:223 */     addLayerPanel(layer);
/* 239:    */     
/* 240:225 */     refresh();
/* 241:    */   }
/* 242:    */   
/* 243:    */   private void deleteLayer()
/* 244:    */   {
/* 245:230 */     int option = JOptionPane.showConfirmDialog(this, "Delete layer?");
/* 246:231 */     if ((option == 0) && 
/* 247:232 */       (this.currentLayerOptionPanel != null))
/* 248:    */     {
/* 249:234 */       Layer layer = this.currentLayerOptionPanel.getLayer();
/* 250:    */       
/* 251:    */ 
/* 252:237 */       RemoveLayerCommand cmd = new RemoveLayerCommand(this.document, layer);
/* 253:238 */       cmd.execute();
/* 254:239 */       this.macroCommand.addCommand(cmd);
/* 255:    */       
/* 256:241 */       removeLayerPanel(this.currentLayerOptionPanel);
/* 257:    */     }
/* 258:244 */     refresh();
/* 259:    */   }
/* 260:    */   
/* 261:    */   private void setCurrentLayerCommand()
/* 262:    */   {
/* 263:249 */     if (this.currentLayerOptionPanel != null)
/* 264:    */     {
/* 265:252 */       SetCurrentLayerCommand cmd = new SetCurrentLayerCommand(this.document, this.currentLayerOptionPanel.getLayer());
/* 266:253 */       cmd.execute();
/* 267:254 */       this.macroCommand.addCommand(cmd);
/* 268:    */       
/* 269:256 */       this.currentLayerLabel.setText(this.currentLayerOptionPanel.getLayerName());
/* 270:    */     }
/* 271:    */   }
/* 272:    */   
/* 273:    */   private void moveUp() {}
/* 274:    */   
/* 275:    */   private void moveDown() {}
/* 276:    */   
/* 277:    */   private void okButtonClose()
/* 278:    */   {
/* 279:304 */     this.modalResult = 0;
/* 280:306 */     if (this.macroCommand.getCommandCount() > 0) {
/* 281:307 */       SimpleURManager.getInstance().getUREngine(this.document).addCommand(this.macroCommand);
/* 282:    */     }
/* 283:310 */     setVisible(false);
/* 284:    */   }
/* 285:    */   
/* 286:    */   private void cancelButtonClose()
/* 287:    */   {
/* 288:314 */     this.modalResult = 1;
/* 289:316 */     if (this.macroCommand.getCommandCount() > 0) {
/* 290:318 */       this.macroCommand.unexecute();
/* 291:    */     }
/* 292:321 */     setVisible(false);
/* 293:    */   }
/* 294:    */   
/* 295:    */   private void initComponents()
/* 296:    */   {
/* 297:331 */     this.upPanel = new JPanel();
/* 298:332 */     this.addLayerButton = new JButton();
/* 299:333 */     this.deleteButton = new JButton();
/* 300:334 */     this.upButton = new JButton();
/* 301:335 */     this.downButton = new JButton();
/* 302:336 */     this.setCurrentLayerButton = new JButton();
/* 303:337 */     this.jLabel6 = new JLabel();
/* 304:338 */     this.currentLayerLabel = new JLabel();
/* 305:339 */     this.centerPanel = new JPanel();
/* 306:340 */     this.headerPanel = new JPanel();
/* 307:341 */     this.jLabel1 = new JLabel();
/* 308:342 */     this.jLabel2 = new JLabel();
/* 309:343 */     this.jLabel3 = new JLabel();
/* 310:344 */     this.jLabel4 = new JLabel();
/* 311:345 */     this.jLabel5 = new JLabel();
/* 312:346 */     this.jLabel7 = new JLabel();
/* 313:347 */     this.jScrollPane1 = new JScrollPane();
/* 314:348 */     this.layerPanel = new JPanel();
/* 315:349 */     this.downPanel = new JPanel();
/* 316:350 */     this.okButton = new JButton();
/* 317:351 */     this.cancelButton = new JButton();
/* 318:    */     
/* 319:353 */     setTitle("Layers properties");
/* 320:354 */     addComponentListener(new ComponentAdapter()
/* 321:    */     {
/* 322:    */       public void componentHidden(ComponentEvent evt)
/* 323:    */       {
/* 324:356 */         LayerSettingDialog.this.formComponentHidden(evt);
/* 325:    */       }
/* 326:359 */     });
/* 327:360 */     this.upPanel.setLayout(new AbsoluteLayout());
/* 328:    */     
/* 329:362 */     this.upPanel.setMinimumSize(new Dimension(0, 50));
/* 330:363 */     this.upPanel.setName("upPanel");
/* 331:364 */     this.upPanel.setOpaque(false);
/* 332:365 */     this.upPanel.setPreferredSize(new Dimension(100, 80));
/* 333:366 */     this.addLayerButton.setText("Add layer");
/* 334:367 */     this.addLayerButton.setName("addLayerButton");
/* 335:368 */     this.addLayerButton.addActionListener(new ActionListener()
/* 336:    */     {
/* 337:    */       public void actionPerformed(ActionEvent evt)
/* 338:    */       {
/* 339:370 */         LayerSettingDialog.this.addLayerButtonActionPerformed(evt);
/* 340:    */       }
/* 341:373 */     });
/* 342:374 */     this.upPanel.add(this.addLayerButton, new AbsoluteConstraints(20, 10, -1, -1));
/* 343:    */     
/* 344:376 */     this.deleteButton.setText("Delete layer");
/* 345:377 */     this.deleteButton.setName("deleteButton");
/* 346:378 */     this.deleteButton.addActionListener(new ActionListener()
/* 347:    */     {
/* 348:    */       public void actionPerformed(ActionEvent evt)
/* 349:    */       {
/* 350:380 */         LayerSettingDialog.this.deleteButtonActionPerformed(evt);
/* 351:    */       }
/* 352:383 */     });
/* 353:384 */     this.upPanel.add(this.deleteButton, new AbsoluteConstraints(110, 10, -1, -1));
/* 354:    */     
/* 355:386 */     this.upButton.setText("Up");
/* 356:387 */     this.upButton.setName("upButton");
/* 357:388 */     this.upButton.addActionListener(new ActionListener()
/* 358:    */     {
/* 359:    */       public void actionPerformed(ActionEvent evt)
/* 360:    */       {
/* 361:390 */         LayerSettingDialog.this.upButtonActionPerformed(evt);
/* 362:    */       }
/* 363:393 */     });
/* 364:394 */     this.upPanel.add(this.upButton, new AbsoluteConstraints(450, 10, -1, -1));
/* 365:    */     
/* 366:396 */     this.downButton.setText("Down");
/* 367:397 */     this.downButton.setName("downButton");
/* 368:398 */     this.downButton.addActionListener(new ActionListener()
/* 369:    */     {
/* 370:    */       public void actionPerformed(ActionEvent evt)
/* 371:    */       {
/* 372:400 */         LayerSettingDialog.this.downButtonActionPerformed(evt);
/* 373:    */       }
/* 374:403 */     });
/* 375:404 */     this.upPanel.add(this.downButton, new AbsoluteConstraints(380, 10, -1, -1));
/* 376:    */     
/* 377:406 */     this.setCurrentLayerButton.setText("Set current");
/* 378:407 */     this.setCurrentLayerButton.addActionListener(new ActionListener()
/* 379:    */     {
/* 380:    */       public void actionPerformed(ActionEvent evt)
/* 381:    */       {
/* 382:409 */         LayerSettingDialog.this.setCurrentLayerButtonActionPerformed(evt);
/* 383:    */       }
/* 384:412 */     });
/* 385:413 */     this.upPanel.add(this.setCurrentLayerButton, new AbsoluteConstraints(220, 10, -1, -1));
/* 386:    */     
/* 387:415 */     this.jLabel6.setText("Current layer:");
/* 388:416 */     this.upPanel.add(this.jLabel6, new AbsoluteConstraints(10, 50, -1, -1));
/* 389:    */     
/* 390:418 */     this.upPanel.add(this.currentLayerLabel, new AbsoluteConstraints(80, 50, 60, 20));
/* 391:    */     
/* 392:420 */     getContentPane().add(this.upPanel, "North");
/* 393:    */     
/* 394:422 */     this.centerPanel.setLayout(new BorderLayout());
/* 395:    */     
/* 396:424 */     this.headerPanel.setBorder(BorderFactory.createLineBorder(SystemColor.controlDkShadow));
/* 397:425 */     this.headerPanel.setMaximumSize(new Dimension(32767, 20));
/* 398:426 */     this.headerPanel.setMinimumSize(new Dimension(0, 20));
/* 399:427 */     this.headerPanel.setPreferredSize(new Dimension(100, 20));
/* 400:428 */     this.jLabel1.setText("Name");
/* 401:    */     
/* 402:430 */     this.jLabel2.setText("Visible");
/* 403:    */     
/* 404:432 */     this.jLabel3.setText("Enable");
/* 405:    */     
/* 406:434 */     this.jLabel4.setText("Color");
/* 407:    */     
/* 408:436 */     this.jLabel5.setText("Lineweight");
/* 409:    */     
/* 410:438 */     this.jLabel7.setText("Print");
/* 411:    */     
/* 412:440 */     GroupLayout headerPanelLayout = new GroupLayout(this.headerPanel);
/* 413:441 */     this.headerPanel.setLayout(headerPanelLayout);
/* 414:442 */     headerPanelLayout.setHorizontalGroup(headerPanelLayout.createParallelGroup(1).add(headerPanelLayout.createSequentialGroup().add(60, 60, 60).add(this.jLabel1).add(88, 88, 88).add(this.jLabel2).add(17, 17, 17).add(this.jLabel3).add(43, 43, 43).add(this.jLabel4).add(51, 51, 51).add(this.jLabel5).add(57, 57, 57).add(this.jLabel7).addContainerGap(159, 32767)));
/* 415:    */     
/* 416:    */ 
/* 417:    */ 
/* 418:    */ 
/* 419:    */ 
/* 420:    */ 
/* 421:    */ 
/* 422:    */ 
/* 423:    */ 
/* 424:    */ 
/* 425:    */ 
/* 426:    */ 
/* 427:    */ 
/* 428:    */ 
/* 429:    */ 
/* 430:    */ 
/* 431:459 */     headerPanelLayout.setVerticalGroup(headerPanelLayout.createParallelGroup(1).add(headerPanelLayout.createSequentialGroup().add(headerPanelLayout.createParallelGroup(3).add(this.jLabel1).add(this.jLabel2).add(this.jLabel3).add(this.jLabel4).add(this.jLabel5).add(this.jLabel7)).addContainerGap(-1, 32767)));
/* 432:    */     
/* 433:    */ 
/* 434:    */ 
/* 435:    */ 
/* 436:    */ 
/* 437:    */ 
/* 438:    */ 
/* 439:    */ 
/* 440:    */ 
/* 441:    */ 
/* 442:    */ 
/* 443:471 */     this.centerPanel.add(this.headerPanel, "North");
/* 444:    */     
/* 445:473 */     this.layerPanel.setLayout(new BoxLayout(this.layerPanel, 1));
/* 446:    */     
/* 447:475 */     this.layerPanel.setBackground(new Color(255, 255, 255));
/* 448:476 */     this.jScrollPane1.setViewportView(this.layerPanel);
/* 449:    */     
/* 450:478 */     this.centerPanel.add(this.jScrollPane1, "Center");
/* 451:    */     
/* 452:480 */     getContentPane().add(this.centerPanel, "Center");
/* 453:    */     
/* 454:482 */     this.downPanel.setMinimumSize(new Dimension(0, 50));
/* 455:483 */     this.downPanel.setName("downPanel");
/* 456:484 */     this.downPanel.setPreferredSize(new Dimension(100, 50));
/* 457:485 */     this.okButton.setText("OK");
/* 458:486 */     this.okButton.setMaximumSize(new Dimension(80, 23));
/* 459:487 */     this.okButton.setName("closeButton");
/* 460:488 */     this.okButton.setPreferredSize(new Dimension(80, 23));
/* 461:489 */     this.okButton.addActionListener(new ActionListener()
/* 462:    */     {
/* 463:    */       public void actionPerformed(ActionEvent evt)
/* 464:    */       {
/* 465:491 */         LayerSettingDialog.this.okButtonActionPerformed(evt);
/* 466:    */       }
/* 467:494 */     });
/* 468:495 */     this.cancelButton.setText("Cancel");
/* 469:496 */     this.cancelButton.setPreferredSize(new Dimension(80, 23));
/* 470:497 */     this.cancelButton.addActionListener(new ActionListener()
/* 471:    */     {
/* 472:    */       public void actionPerformed(ActionEvent evt)
/* 473:    */       {
/* 474:499 */         LayerSettingDialog.this.cancelButtonActionPerformed(evt);
/* 475:    */       }
/* 476:502 */     });
/* 477:503 */     GroupLayout downPanelLayout = new GroupLayout(this.downPanel);
/* 478:504 */     this.downPanel.setLayout(downPanelLayout);
/* 479:505 */     downPanelLayout.setHorizontalGroup(downPanelLayout.createParallelGroup(1).add(2, downPanelLayout.createSequentialGroup().addContainerGap(483, 32767).add(this.okButton, -2, 76, -2).addPreferredGap(0).add(this.cancelButton, -2, -1, -2).add(18, 18, 18)));
/* 480:    */     
/* 481:    */ 
/* 482:    */ 
/* 483:    */ 
/* 484:    */ 
/* 485:    */ 
/* 486:    */ 
/* 487:    */ 
/* 488:514 */     downPanelLayout.setVerticalGroup(downPanelLayout.createParallelGroup(1).add(2, downPanelLayout.createSequentialGroup().addContainerGap(16, 32767).add(downPanelLayout.createParallelGroup(3).add(this.okButton, -2, -1, -2).add(this.cancelButton, -2, -1, -2)).addContainerGap()));
/* 489:    */     
/* 490:    */ 
/* 491:    */ 
/* 492:    */ 
/* 493:    */ 
/* 494:    */ 
/* 495:    */ 
/* 496:    */ 
/* 497:523 */     getContentPane().add(this.downPanel, "South");
/* 498:    */     
/* 499:525 */     pack();
/* 500:    */   }
/* 501:    */   
/* 502:    */   private void setCurrentLayerButtonActionPerformed(ActionEvent evt)
/* 503:    */   {
/* 504:529 */     setCurrentLayerCommand();
/* 505:    */   }
/* 506:    */   
/* 507:    */   private void cancelButtonActionPerformed(ActionEvent evt)
/* 508:    */   {
/* 509:533 */     cancelButtonClose();
/* 510:    */   }
/* 511:    */   
/* 512:    */   private void okButtonActionPerformed(ActionEvent evt)
/* 513:    */   {
/* 514:537 */     okButtonClose();
/* 515:    */   }
/* 516:    */   
/* 517:    */   private void addLayerButtonActionPerformed(ActionEvent evt)
/* 518:    */   {
/* 519:541 */     addLayer();
/* 520:    */   }
/* 521:    */   
/* 522:    */   private void deleteButtonActionPerformed(ActionEvent evt)
/* 523:    */   {
/* 524:545 */     deleteLayer();
/* 525:    */   }
/* 526:    */   
/* 527:    */   private void formComponentHidden(ComponentEvent evt) {}
/* 528:    */   
/* 529:    */   private void upButtonActionPerformed(ActionEvent evt)
/* 530:    */   {
/* 531:554 */     moveUp();
/* 532:    */   }
/* 533:    */   
/* 534:    */   private void downButtonActionPerformed(ActionEvent evt)
/* 535:    */   {
/* 536:559 */     moveDown();
/* 537:    */   }
/* 538:    */   
/* 539:    */   public class LayerOptionPanel
/* 540:    */     extends JPanel
/* 541:    */   {
/* 542:    */     Layer layer;
/* 543:    */     Color color;
/* 544:    */     ColorComboBox.ColorPanel colorPanel;
/* 545:    */     LineWeightComboBox.LineWidthPanel lineWeightPanel;
/* 546:    */     JTextField name;
/* 547:    */     JCheckBox visibleCB;
/* 548:    */     JCheckBox enableCB;
/* 549:    */     JCheckBox printCB;
/* 550:    */     JPanel p1;
/* 551:    */     JPanel p2;
/* 552:    */     JPanel p3;
/* 553:    */     JPanel p4;
/* 554:    */     JPanel p5;
/* 555:    */     LayerOptionPanel instance;
/* 556:    */     
/* 557:    */     public LayerOptionPanel(Layer layer)
/* 558:    */     {
/* 559:617 */       this.instance = this;
/* 560:    */       
/* 561:619 */       this.layer = layer;
/* 562:620 */       init();
/* 563:    */     }
/* 564:    */     
/* 565:    */     private void init()
/* 566:    */     {
/* 567:625 */       setLayout(new FlowLayout(0, 2, 2));
/* 568:    */       
/* 569:627 */       setBackground(new Color(255, 255, 255));
/* 570:    */       
/* 571:629 */       setMaximumSize(new Dimension(32767, 24));
/* 572:630 */       setMinimumSize(new Dimension(234, 24));
/* 573:631 */       setPreferredSize(new Dimension(100, 24));
/* 574:    */       
/* 575:633 */       setRequestFocusEnabled(false);
/* 576:    */       
/* 577:635 */       this.color = getBackground();
/* 578:    */       
/* 579:    */ 
/* 580:638 */       this.p1 = new JPanel();
/* 581:639 */       this.p1.setPreferredSize(new Dimension(10, 0));
/* 582:640 */       add(this.p1);
/* 583:    */       
/* 584:642 */       this.name = new JTextField();
/* 585:643 */       this.name.setText(this.layer.getName());
/* 586:644 */       this.name.setPreferredSize(new Dimension(150, 20));
/* 587:    */       
/* 588:646 */       this.name.addFocusListener(new FocusAdapter()
/* 589:    */       {
/* 590:    */         public void focusLost(FocusEvent e)
/* 591:    */         {
/* 592:650 */           LayerNameChangeCommand cmd = new LayerNameChangeCommand(LayerSettingDialog.LayerOptionPanel.this.layer, LayerSettingDialog.LayerOptionPanel.this.name.getText());
/* 593:651 */           cmd.execute();
/* 594:652 */           LayerSettingDialog.this.macroCommand.addCommand(cmd);
/* 595:    */         }
/* 596:    */         
/* 597:    */         public void focusGained(FocusEvent e)
/* 598:    */         {
/* 599:655 */           LayerSettingDialog.this.setActivePanel(LayerSettingDialog.LayerOptionPanel.this.instance);
/* 600:    */         }
/* 601:658 */       });
/* 602:659 */       add(this.name);
/* 603:    */       
/* 604:    */ 
/* 605:662 */       this.p2 = new JPanel();
/* 606:663 */       this.p2.setPreferredSize(new Dimension(25, 0));
/* 607:664 */       add(this.p2);
/* 608:    */       
/* 609:666 */       this.visibleCB = new JCheckBox();
/* 610:667 */       this.visibleCB.setBackground(SystemColor.text);
/* 611:668 */       this.visibleCB.setSelected(this.layer.getVisible());
/* 612:669 */       this.visibleCB.addActionListener(new ActionListener()
/* 613:    */       {
/* 614:    */         public void actionPerformed(ActionEvent e)
/* 615:    */         {
/* 616:672 */           LayerSettingDialog.this.setActivePanel(LayerSettingDialog.LayerOptionPanel.this.instance);
/* 617:    */           
/* 618:    */ 
/* 619:675 */           LayerVisibleChangeCommand cmd = new LayerVisibleChangeCommand(LayerSettingDialog.LayerOptionPanel.this.layer, LayerSettingDialog.LayerOptionPanel.this.visibleCB.isSelected());
/* 620:676 */           cmd.execute();
/* 621:677 */           LayerSettingDialog.this.macroCommand.addCommand(cmd);
/* 622:    */         }
/* 623:679 */       });
/* 624:680 */       add(this.visibleCB);
/* 625:    */       
/* 626:    */ 
/* 627:683 */       this.p3 = new JPanel();
/* 628:684 */       this.p3.setPreferredSize(new Dimension(25, 0));
/* 629:685 */       add(this.p3);
/* 630:    */       
/* 631:687 */       this.enableCB = new JCheckBox();
/* 632:688 */       this.enableCB.setBackground(SystemColor.text);
/* 633:689 */       this.enableCB.setSelected(this.layer.getEnable());
/* 634:690 */       this.enableCB.addActionListener(new ActionListener()
/* 635:    */       {
/* 636:    */         public void actionPerformed(ActionEvent e)
/* 637:    */         {
/* 638:693 */           LayerSettingDialog.this.setActivePanel(LayerSettingDialog.LayerOptionPanel.this.instance);
/* 639:    */           
/* 640:    */ 
/* 641:696 */           LayerEnableChangeCommand cmd = new LayerEnableChangeCommand(LayerSettingDialog.LayerOptionPanel.this.layer, LayerSettingDialog.LayerOptionPanel.this.enableCB.isSelected());
/* 642:697 */           cmd.execute();
/* 643:698 */           LayerSettingDialog.this.macroCommand.addCommand(cmd);
/* 644:    */         }
/* 645:700 */       });
/* 646:701 */       add(this.enableCB);
/* 647:    */       
/* 648:    */ 
/* 649:704 */       this.p4 = new JPanel();
/* 650:705 */       this.p4.setPreferredSize(new Dimension(25, 0));
/* 651:706 */       add(this.p4);
/* 652:    */       
/* 653:708 */       this.colorPanel = new ColorComboBox.ColorPanel(this.layer.getColor(), "", false);
/* 654:709 */       this.colorPanel.setPreferredSize(new Dimension(90, 20));
/* 655:710 */       this.colorPanel.addMouseListener(new MouseAdapter()
/* 656:    */       {
/* 657:    */         public void mouseClicked(MouseEvent evt)
/* 658:    */         {
/* 659:713 */           LayerSettingDialog.this.setActivePanel(LayerSettingDialog.LayerOptionPanel.this.instance);
/* 660:    */           
/* 661:715 */           ShowLayerColorDialogCommand scdc = LayerSettingDialog.this.application.getDocumentCommandFactory().createShowLayerColorDialogCommand(LayerSettingDialog.LayerOptionPanel.this.layer);
/* 662:716 */           scdc.execute();
/* 663:    */           
/* 664:    */ 
/* 665:    */ 
/* 666:    */ 
/* 667:    */ 
/* 668:    */ 
/* 669:    */ 
/* 670:724 */           Color color = scdc.getColor();
/* 671:726 */           if (color != null)
/* 672:    */           {
/* 673:728 */             LayerColorChangeCommand cmd = new LayerColorChangeCommand(LayerSettingDialog.LayerOptionPanel.this.layer, color);
/* 674:729 */             cmd.execute();
/* 675:730 */             LayerSettingDialog.this.macroCommand.addCommand(cmd);
/* 676:    */             
/* 677:732 */             LayerSettingDialog.LayerOptionPanel.this.colorPanel.setViewColor(color);
/* 678:    */           }
/* 679:    */         }
/* 680:735 */       });
/* 681:736 */       add(this.colorPanel);
/* 682:    */       
/* 683:    */ 
/* 684:739 */       this.lineWeightPanel = new LineWeightComboBox.LineWidthPanel(this.layer.getLineWeight(), false);
/* 685:740 */       this.lineWeightPanel.setPreferredSize(new Dimension(120, 20));
/* 686:741 */       this.lineWeightPanel.addMouseListener(new MouseAdapter()
/* 687:    */       {
/* 688:    */         public void mouseClicked(MouseEvent evt)
/* 689:    */         {
/* 690:744 */           LayerSettingDialog.this.setActivePanel(LayerSettingDialog.LayerOptionPanel.this.instance);
/* 691:    */           
/* 692:746 */           LineWeightChooseDialog dialog = LayerSettingDialog.this.application.getDialogManager().getLineWeightChooseDialog();
/* 693:    */           
/* 694:748 */           dialog.setInitLineweight(LayerSettingDialog.LayerOptionPanel.this.layer.getLineWeight());
/* 695:749 */           if (dialog.showModal() == LineWeightChooseDialog.OK_MODAL_RESULT)
/* 696:    */           {
/* 697:751 */             LineWeight lineWeight = dialog.getResult();
/* 698:753 */             if (lineWeight != null)
/* 699:    */             {
/* 700:756 */               LayerLineweightChangeCommand cmd = new LayerLineweightChangeCommand(LayerSettingDialog.LayerOptionPanel.this.layer, lineWeight);
/* 701:757 */               cmd.execute();
/* 702:758 */               LayerSettingDialog.this.macroCommand.addCommand(cmd);
/* 703:    */               
/* 704:760 */               LayerSettingDialog.LayerOptionPanel.this.lineWeightPanel.setViewLineWeight(lineWeight);
/* 705:    */             }
/* 706:    */           }
/* 707:    */         }
/* 708:764 */       });
/* 709:765 */       add(this.lineWeightPanel);
/* 710:    */       
/* 711:    */ 
/* 712:768 */       this.p5 = new JPanel();
/* 713:769 */       this.p5.setPreferredSize(new Dimension(10, 0));
/* 714:770 */       add(this.p5);
/* 715:    */       
/* 716:    */ 
/* 717:773 */       this.printCB = new JCheckBox();
/* 718:774 */       this.printCB.setBackground(SystemColor.text);
/* 719:775 */       this.printCB.setSelected(this.layer.getPrintable());
/* 720:776 */       this.printCB.addActionListener(new ActionListener()
/* 721:    */       {
/* 722:    */         public void actionPerformed(ActionEvent e)
/* 723:    */         {
/* 724:779 */           LayerSettingDialog.this.setActivePanel(LayerSettingDialog.LayerOptionPanel.this.instance);
/* 725:    */           
/* 726:    */ 
/* 727:782 */           LayerPrintChangeCommand cmd = new LayerPrintChangeCommand(LayerSettingDialog.LayerOptionPanel.this.layer, LayerSettingDialog.LayerOptionPanel.this.printCB.isSelected());
/* 728:783 */           cmd.execute();
/* 729:784 */           LayerSettingDialog.this.macroCommand.addCommand(cmd);
/* 730:    */         }
/* 731:786 */       });
/* 732:787 */       add(this.printCB);
/* 733:    */     }
/* 734:    */     
/* 735:    */     public Layer getLayer()
/* 736:    */     {
/* 737:792 */       return this.layer;
/* 738:    */     }
/* 739:    */     
/* 740:    */     public String getLayerName()
/* 741:    */     {
/* 742:796 */       return this.name.getText();
/* 743:    */     }
/* 744:    */     
/* 745:    */     public void setActive(boolean active)
/* 746:    */     {
/* 747:800 */       if (active)
/* 748:    */       {
/* 749:801 */         setBackground(SystemColor.activeCaption);
/* 750:802 */         this.p1.setBackground(SystemColor.activeCaption);
/* 751:803 */         this.p2.setBackground(SystemColor.activeCaption);
/* 752:804 */         this.p3.setBackground(SystemColor.activeCaption);
/* 753:805 */         this.p4.setBackground(SystemColor.activeCaption);
/* 754:    */       }
/* 755:    */       else
/* 756:    */       {
/* 757:807 */         setBackground(SystemColor.text);
/* 758:808 */         this.p1.setBackground(SystemColor.text);
/* 759:809 */         this.p2.setBackground(SystemColor.text);
/* 760:810 */         this.p3.setBackground(SystemColor.text);
/* 761:811 */         this.p4.setBackground(SystemColor.text);
/* 762:    */       }
/* 763:    */     }
/* 764:    */   }
/* 765:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.widget.dialog.LayerSettingDialog
 * JD-Core Version:    0.7.0.1
 */