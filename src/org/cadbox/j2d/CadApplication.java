/*   1:    */ package org.cadbox.j2d;
/*   2:    */ 
/*   3:    */ import java.awt.event.WindowEvent;
/*   4:    */ import java.awt.event.WindowListener;
/*   5:    */ import java.beans.PropertyChangeEvent;
/*   6:    */ import java.beans.PropertyChangeListener;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.text.DecimalFormat;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.Collection;
/*  11:    */ import java.util.List;
/*  12:    */ import org.cadbox.Application;
/*  13:    */ import org.cadbox.Command;
/*  14:    */ import org.cadbox.Document;
/*  15:    */ import org.cadbox.Observer;
/*  16:    */ import org.cadbox.SimpleURManager;
/*  17:    */ import org.cadbox.URManager;
/*  18:    */ import org.cadbox.j2d.command.DocumentCommandFactory;
/*  19:    */ import org.cadbox.j2d.widget.MainMenu;
/*  20:    */ import org.cadbox.j2d.widget.StatusBar;
/*  21:    */ import org.cadbox.j2d.widget.dialog.DialogManager;
/*  22:    */ import org.cadbox.j2d.widget.toolbar.CommandToolBar;
/*  23:    */ import org.cadbox.j2d.widget.toolbar.DrawToolBar;
/*  24:    */ import org.cadbox.j2d.widget.toolbar.LayerToolBar;
/*  25:    */ import org.cadbox.j2d.widget.toolbar.ModeToolBar;
/*  26:    */ import org.cadbox.j2d.widget.toolbar.ModifyToolBar;
/*  27:    */ import org.cadbox.j2d.widget.toolbar.PropertiesToolBar;
/*  28:    */ import org.cadbox.j2d.widget.toolbar.SnapToolBar;
/*  29:    */ import org.cadbox.j2d.widget.toolbar.StandardToolBar;
/*  30:    */ import org.cadbox.j2d.widget.window.CadDocumentWindow;
/*  31:    */ import org.cadbox.plaf.AppWindow;
/*  32:    */ import org.cadbox.plaf.InternalWindow;
/*  33:    */ import org.cadbox.plaf.InternalWindowListener;
/*  34:    */ 
/*  35:    */ public class CadApplication
/*  36:    */   implements Application
/*  37:    */ {
/*  38: 66 */   private List observers = new ArrayList();
/*  39: 67 */   private List documents = new ArrayList();
/*  40: 69 */   private Document currentDocument = null;
/*  41:    */   private LayerToolBar layerToolBar;
/*  42:    */   private ModeToolBar modeToolBar;
/*  43:    */   private PropertiesToolBar propertiesToolBar;
/*  44:    */   private StandardToolBar standardToolBar;
/*  45:    */   private ModifyToolBar modifyToolBar;
/*  46:    */   private DrawToolBar drawToolBar;
/*  47:    */   private CommandToolBar commandToolBar;
/*  48:    */   private SnapToolBar snapToolBar;
/*  49:    */   private MainMenu mainMenu;
/*  50:    */   private StatusBar statusBar;
/*  51:    */   private DocumentCommandFactory documentCommandFactory;
/*  52:    */   private DialogManager dialogManager;
/*  53: 93 */   private URManager urManager = null;
/*  54:    */   private AppWindow appWindow;
/*  55:    */   
/*  56:    */   public CadApplication()
/*  57:    */   {
/*  58:100 */     this.urManager = SimpleURManager.getInstance();
/*  59:    */     
/*  60:102 */     this.documentCommandFactory = new DocumentCommandFactory(this);
/*  61:    */     
/*  62:104 */     this.dialogManager = new DialogManager(this);
/*  63:    */     
/*  64:106 */     createToolBars();
/*  65:    */     
/*  66:108 */     this.mainMenu = createMainMenu();
/*  67:109 */     addObserver(this.mainMenu);
/*  68:    */     
/*  69:111 */     this.statusBar = new StatusBar();
/*  70:112 */     addObserver(this.statusBar);
/*  71:    */     
/*  72:114 */     this.appWindow = createMainWindow();
/*  73:115 */     this.appWindow.setVisible(true);
/*  74:    */     
/*  75:    */ 
/*  76:    */ 
/*  77:119 */     this.documentCommandFactory.createNewDocumentCommand().execute();
/*  78:    */   }
/*  79:    */   
/*  80:    */   private AppWindow createMainWindow()
/*  81:    */   {
/*  82:124 */     AppWindow mainForm = new AppWindow();
/*  83:    */     
/*  84:126 */     mainForm.setTitle("CADBox.2D");
/*  85:    */     
/*  86:128 */     mainForm.setMainMenu(this.mainMenu);
/*  87:    */     
/*  88:130 */     mainForm.setStatusBar(this.statusBar);
/*  89:    */     
/*  90:132 */     mainForm.addToolBar(this.standardToolBar, "North");
/*  91:133 */     mainForm.addToolBar(this.layerToolBar, "North");
/*  92:134 */     mainForm.addToolBar(this.modeToolBar, "North");
/*  93:135 */     mainForm.addToolBar(this.propertiesToolBar, "North");
/*  94:136 */     mainForm.addToolBar(this.snapToolBar, "North");
/*  95:    */     
/*  96:138 */     mainForm.addToolBar(this.modifyToolBar, "West");
/*  97:139 */     mainForm.addToolBar(this.drawToolBar, "West");
/*  98:    */     
/*  99:    */ 
/* 100:142 */     mainForm.addWindowListener(new WindowListener()
/* 101:    */     {
/* 102:    */       public void windowActivated(WindowEvent e) {}
/* 103:    */       
/* 104:    */       public void windowClosed(WindowEvent e) {}
/* 105:    */       
/* 106:    */       public void windowClosing(WindowEvent e)
/* 107:    */       {
/* 108:148 */         CadApplication.this.documentCommandFactory.createCloseAllCommand().execute();
/* 109:    */       }
/* 110:    */       
/* 111:    */       public void windowDeactivated(WindowEvent e) {}
/* 112:    */       
/* 113:    */       public void windowDeiconified(WindowEvent e) {}
/* 114:    */       
/* 115:    */       public void windowIconified(WindowEvent e) {}
/* 116:    */       
/* 117:    */       public void windowOpened(WindowEvent e) {}
/* 118:161 */     });
/* 119:162 */     return mainForm;
/* 120:    */   }
/* 121:    */   
/* 122:    */   private MainMenu createMainMenu()
/* 123:    */   {
/* 124:166 */     MainMenu mm = new MainMenu(this, this.documentCommandFactory);
/* 125:167 */     return mm;
/* 126:    */   }
/* 127:    */   
/* 128:    */   private void createToolBars()
/* 129:    */   {
/* 130:172 */     this.standardToolBar = new StandardToolBar(this, this.documentCommandFactory);
/* 131:173 */     addObserver(this.standardToolBar);
/* 132:    */     
/* 133:    */ 
/* 134:176 */     this.layerToolBar = new LayerToolBar(this);
/* 135:177 */     addObserver(this.layerToolBar);
/* 136:    */     
/* 137:    */ 
/* 138:180 */     this.modeToolBar = new ModeToolBar(this, this);
/* 139:181 */     addObserver(this.modeToolBar);
/* 140:    */     
/* 141:    */ 
/* 142:184 */     this.propertiesToolBar = new PropertiesToolBar(this);
/* 143:185 */     addObserver(this.propertiesToolBar);
/* 144:    */     
/* 145:    */ 
/* 146:188 */     this.modifyToolBar = new ModifyToolBar(this, this);
/* 147:189 */     addObserver(this.modifyToolBar);
/* 148:    */     
/* 149:    */ 
/* 150:192 */     this.drawToolBar = new DrawToolBar(this, this);
/* 151:193 */     addObserver(this.drawToolBar);
/* 152:    */     
/* 153:195 */     this.commandToolBar = new CommandToolBar();
/* 154:196 */     addObserver(this.commandToolBar);
/* 155:    */     
/* 156:198 */     this.snapToolBar = new SnapToolBar();
/* 157:199 */     addObserver(this.snapToolBar);
/* 158:    */   }
/* 159:    */   
/* 160:    */   public AppWindow getAppWindow()
/* 161:    */   {
/* 162:203 */     return this.appWindow;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public DocumentCommandFactory getDocumentCommandFactory()
/* 166:    */   {
/* 167:207 */     return this.documentCommandFactory;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public DialogManager getDialogManager()
/* 171:    */   {
/* 172:211 */     return this.dialogManager;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void addDocument(Document document)
/* 176:    */   {
/* 177:223 */     if (document != null)
/* 178:    */     {
/* 179:224 */       this.documents.add(document);
/* 180:225 */       addCadDocumentFrame(document);
/* 181:    */     }
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void removeDocument(Document document)
/* 185:    */   {
/* 186:230 */     if (document != null)
/* 187:    */     {
/* 188:231 */       this.documents.remove(document);
/* 189:232 */       removeCadDocumentFrame(document);
/* 190:    */     }
/* 191:    */   }
/* 192:    */   
/* 193:    */   public Collection getDocuments()
/* 194:    */   {
/* 195:237 */     return this.documents;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public int getDocumentCount()
/* 199:    */   {
/* 200:241 */     return this.documents.size();
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void setCurrentDocument(Document document)
/* 204:    */   {
/* 205:245 */     if (this.currentDocument != document) {
/* 206:246 */       this.currentDocument = document;
/* 207:    */     }
/* 208:    */   }
/* 209:    */   
/* 210:    */   public Document getCurrentDocument()
/* 211:    */   {
/* 212:252 */     return this.currentDocument;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public void addObserver(Observer observer)
/* 216:    */   {
/* 217:262 */     this.observers.add(observer);
/* 218:    */   }
/* 219:    */   
/* 220:    */   public void removeObserver(Observer observer)
/* 221:    */   {
/* 222:266 */     this.observers.remove(observer);
/* 223:    */   }
/* 224:    */   
/* 225:    */   public void updateAllObservers()
/* 226:    */   {
/* 227:270 */     for (int i = 0; i < this.observers.size(); i++)
/* 228:    */     {
/* 229:271 */       Observer observer = (Observer)this.observers.get(i);
/* 230:272 */       observer.update();
/* 231:    */     }
/* 232:    */   }
/* 233:    */   
/* 234:    */   public URManager getURManager()
/* 235:    */   {
/* 236:277 */     return this.urManager;
/* 237:    */   }
/* 238:    */   
/* 239:    */   class CadDocumentFrameListener
/* 240:    */     implements InternalWindowListener
/* 241:    */   {
/* 242:    */     CadDocumentFrameListener() {}
/* 243:    */     
/* 244:    */     public void openInternalWindowAction(InternalWindow frame) {}
/* 245:    */     
/* 246:    */     public void closeInternalWindowAction(InternalWindow frame)
/* 247:    */     {
/* 248:294 */       CadApplication.this.documentCommandFactory.createCloseDocumentCommand().execute();
/* 249:    */     }
/* 250:    */     
/* 251:    */     public void activatedInternalWindowAction(InternalWindow frame)
/* 252:    */     {
/* 253:300 */       CadDocumentWindow oldFrame = CadApplication.this.getCadDocumentWindow(CadApplication.this.getCurrentDocument());
/* 254:301 */       if (oldFrame != null) {
/* 255:302 */         CadApplication.this.removeObserver(oldFrame);
/* 256:    */       }
/* 257:304 */       CadApplication.this.setCurrentDocument(((CadDocumentWindow)frame).getDocument());
/* 258:305 */       CadApplication.this.addObserver((CadDocumentWindow)frame);
/* 259:306 */       CadApplication.this.updateAllObservers();
/* 260:    */     }
/* 261:    */   }
/* 262:    */   
/* 263:310 */   CadDocumentFrameListener frameListener = new CadDocumentFrameListener();
/* 264:312 */   PropertyChangeListener propertyChangeListener = new PropertyChangeListener()
/* 265:    */   {
/* 266:    */     public void propertyChange(PropertyChangeEvent evt)
/* 267:    */     {
/* 268:314 */       String name = evt.getPropertyName();
/* 269:315 */       DecimalFormat df = new DecimalFormat("#########0.0000");
/* 270:317 */       if (name.compareTo("redraw") == 0) {
/* 271:318 */         CadApplication.this.updateAllObservers();
/* 272:    */       }
/* 273:320 */       if (name.compareTo("mouseCoordinateX") == 0)
/* 274:    */       {
/* 275:321 */         Double d = (Double)evt.getNewValue();
/* 276:322 */         String ff = df.format(d);
/* 277:323 */         CadApplication.this.statusBar.setXFieldValue(ff);
/* 278:    */       }
/* 279:326 */       if (name.compareTo("mouseCoordinateY") == 0)
/* 280:    */       {
/* 281:327 */         Double d = (Double)evt.getNewValue();
/* 282:328 */         String ff = df.format(d);
/* 283:329 */         CadApplication.this.statusBar.setYFieldValue(ff);
/* 284:    */       }
/* 285:332 */       if (name.compareTo("scale") == 0)
/* 286:    */       {
/* 287:333 */         Double d = (Double)evt.getNewValue();
/* 288:334 */         String ff = df.format(d);
/* 289:335 */         CadApplication.this.modeToolBar.showScaleValue(d.doubleValue());
/* 290:    */       }
/* 291:    */     }
/* 292:    */   };
/* 293:    */   
/* 294:    */   private void addCadDocumentFrame(Document document)
/* 295:    */   {
/* 296:343 */     if (document != null) {
/* 297:    */       try
/* 298:    */       {
/* 299:345 */         if ((document instanceof CadDocument))
/* 300:    */         {
/* 301:346 */           CadDocumentWindow frame = new CadDocumentWindow((CadDocument)document);
/* 302:    */           
/* 303:348 */           frame.addInternalWindowListener(this.frameListener);
/* 304:349 */           frame.addPropertyChangeListener(this.propertyChangeListener);
/* 305:    */           
/* 306:351 */           this.appWindow.addInternalWindow(frame);
/* 307:    */         }
/* 308:    */       }
/* 309:    */       catch (Exception ex)
/* 310:    */       {
/* 311:354 */         System.out.println("Open document error");
/* 312:355 */         throw new IllegalArgumentException();
/* 313:    */       }
/* 314:    */     }
/* 315:    */   }
/* 316:    */   
/* 317:    */   private void removeCadDocumentFrame(Document document)
/* 318:    */   {
/* 319:361 */     if (document != null)
/* 320:    */     {
/* 321:362 */       CadDocumentWindow frame = getCadDocumentWindow(document);
/* 322:363 */       if (frame != null)
/* 323:    */       {
/* 324:365 */         frame.removeInternalWindowListener(this.frameListener);
/* 325:366 */         frame.removePropertyChangeListener(this.propertyChangeListener);
/* 326:    */         
/* 327:368 */         this.appWindow.removeInternalWindow(frame);
/* 328:370 */         if (getDocumentCount() == 0)
/* 329:    */         {
/* 330:371 */           setCurrentDocument(null);
/* 331:372 */           removeObserver(frame);
/* 332:    */         }
/* 333:    */       }
/* 334:    */     }
/* 335:    */   }
/* 336:    */   
/* 337:    */   public CadDocumentWindow getCadDocumentWindow(Document document)
/* 338:    */   {
/* 339:379 */     InternalWindow[] c = this.appWindow.getAllInternalWindows();
/* 340:380 */     for (int i = 0; i < c.length; i++) {
/* 341:381 */       if ((c[i] instanceof CadDocumentWindow))
/* 342:    */       {
/* 343:382 */         CadDocumentWindow frame = (CadDocumentWindow)c[i];
/* 344:383 */         if ((frame != null) && 
/* 345:384 */           (frame.getDocument() == document)) {
/* 346:385 */           return frame;
/* 347:    */         }
/* 348:    */       }
/* 349:    */     }
/* 350:390 */     return null;
/* 351:    */   }
/* 352:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.CadApplication
 * JD-Core Version:    0.7.0.1
 */