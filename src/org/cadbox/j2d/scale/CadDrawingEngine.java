/*    1:     */ package org.cadbox.j2d.scale;
/*    2:     */ 
/*    3:     */ import java.awt.BasicStroke;
/*    4:     */ import java.awt.Color;
/*    5:     */ import java.awt.Graphics2D;
/*    6:     */ import java.awt.Stroke;
/*    7:     */ import java.awt.event.KeyEvent;
/*    8:     */ import java.awt.event.MouseEvent;
/*    9:     */ import java.awt.event.MouseWheelEvent;
/*   10:     */ import java.awt.image.BufferedImage;
/*   11:     */ import java.beans.PropertyChangeSupport;
/*   12:     */ import java.util.ArrayList;
/*   13:     */ import java.util.Collection;
/*   14:     */ import java.util.HashSet;
/*   15:     */ import java.util.Iterator;
/*   16:     */ import java.util.List;
/*   17:     */ import java.util.Set;
/*   18:     */ import org.cadbox.MacroCommand;
/*   19:     */ import org.cadbox.SimpleURManager;
/*   20:     */ import org.cadbox.UREngine;
/*   21:     */ import org.cadbox.URManager;
/*   22:     */ import org.cadbox.UndoableCommand;
/*   23:     */ import org.cadbox.j2d.CadDocument;
/*   24:     */ import org.cadbox.j2d.scale.command.AddShapeCommand;
/*   25:     */ import org.cadbox.j2d.scale.command.CopyShapeCommand;
/*   26:     */ import org.cadbox.j2d.scale.command.DeleteShapeCommand;
/*   27:     */ import org.cadbox.j2d.scale.command.ModifyShapeCommand;
/*   28:     */ import org.cadbox.j2d.scale.command.MoveShapeCommand;
/*   29:     */ import org.cadbox.j2d.scale.command.RotateShapeCommand;
/*   30:     */ import org.cadbox.j2d.scale.core.Circle;
/*   31:     */ import org.cadbox.j2d.scale.core.Context;
/*   32:     */ import org.cadbox.j2d.scale.core.DrawingContext;
/*   33:     */ import org.cadbox.j2d.scale.core.DrawingEngine;
/*   34:     */ import org.cadbox.j2d.scale.core.GeometryUtils;
/*   35:     */ import org.cadbox.j2d.scale.core.Group;
/*   36:     */ import org.cadbox.j2d.scale.core.Layer;
/*   37:     */ import org.cadbox.j2d.scale.core.Layout;
/*   38:     */ import org.cadbox.j2d.scale.core.LineType;
/*   39:     */ import org.cadbox.j2d.scale.core.LineWeight;
/*   40:     */ import org.cadbox.j2d.scale.core.PropertyContext;
/*   41:     */ import org.cadbox.j2d.scale.core.Shape;
/*   42:     */ import org.cadbox.j2d.scale.core.geom.Point2D_;
/*   43:     */ import org.cadbox.j2d.scale.core.geom.Point2I;
/*   44:     */ import org.cadbox.j2d.scale.core.geom.Rectangle2D_;
/*   45:     */ import org.cadbox.j2d.scale.core.unit.MmUnit;
/*   46:     */ import org.cadbox.j2d.scale.core.unit.UnitTranslator;
/*   47:     */ 
/*   48:     */ public class CadDrawingEngine
/*   49:     */   extends DrawingEngine
/*   50:     */ {
/*   51:  72 */   CadDocument document = null;
/*   52:     */   PropertyChangeSupport propertyChangeSupport;
/*   53:     */   private double x_clicked;
/*   54:     */   private double y_clicked;
/*   55:     */   private int mouseButton;
/*   56:     */   private double x_clicked_old;
/*   57:     */   private double y_clicked_old;
/*   58:  82 */   private Set selectedShapeList = new HashSet();
/*   59:  85 */   private Color snapPointColor = Color.red;
/*   60:  86 */   private BasicStroke snapPointStroke = new BasicStroke(3.0F);
/*   61:  88 */   private Color shapeDrawingColor = Color.black;
/*   62:  89 */   private BasicStroke shapeDrawingStroke = new BasicStroke(1.0F);
/*   63:  92 */   private Stroke selectedShapeStroke = new BasicStroke(1.0F, 0, 0, 1.0F, new float[] { 5.0F }, 0.0F);
/*   64:  95 */   private Color backgroundColor = Color.white;
/*   65:  96 */   private Color foregroundColor = Color.black;
/*   66:     */   public static final int SELECTION_MODE = 1;
/*   67:     */   public static final int MOTION_MODE = 2;
/*   68:     */   public static final int CREATION_MODE = 3;
/*   69:     */   public static final int ZOOM_IN_MODE = 4;
/*   70:     */   public static final int ZOOM_OUT_MODE = 5;
/*   71: 104 */   private int mode = 1;
/*   72:     */   public static final int SHAPE_NONE = 0;
/*   73:     */   public static final int SHAPE_POLYLINE = 1;
/*   74:     */   public static final int SHAPE_POLYGONE = 2;
/*   75:     */   public static final int SHAPE_CIRCLE = 3;
/*   76:     */   public static final int SHAPE_SEGMENT = 4;
/*   77:     */   public static final int SHAPE_LINE = 5;
/*   78: 113 */   public int shapeType = 0;
/*   79: 115 */   private int pCount = 0;
/*   80: 117 */   private int snapSensitiveLength = 10;
/*   81: 119 */   private double[] xP = new double[2000];
/*   82: 120 */   private double[] yP = new double[2000];
/*   83:     */   public static final int NONE_ACTION = 1;
/*   84:     */   public static final int MOVE_ACTION = 2;
/*   85:     */   public static final int COPY_ACTION = 3;
/*   86:     */   public static final int ROTATE_ACTION = 4;
/*   87:     */   public static final int EDIT_ACTION = 5;
/*   88: 128 */   private int action = 1;
/*   89: 130 */   private List actionShapeList = new ArrayList();
/*   90:     */   private CadShape actionShape;
/*   91:     */   Context context;
/*   92:     */   DrawingContext drawingContext;
/*   93:     */   PropertyContext propertyContext;
/*   94: 138 */   private List snapList = new ArrayList();
/*   95:     */   private SnapPoint currentSnapPoint;
/*   96:     */   
/*   97:     */   public CadDrawingEngine(CadDocument document, PropertyChangeSupport propertyChangeSupport)
/*   98:     */   {
/*   99: 143 */     this.document = document;
/*  100: 144 */     this.propertyChangeSupport = propertyChangeSupport;
/*  101:     */     
/*  102: 146 */     this.context = document.getContext();
/*  103: 147 */     this.drawingContext = this.context.getDrawingContext();
/*  104: 148 */     this.propertyContext = this.context.getPropertyContext();
/*  105:     */   }
/*  106:     */   
/*  107:     */   public void reset()
/*  108:     */   {
/*  109: 152 */     this.selectedShapeList.clear();
/*  110: 153 */     this.actionShape = null;
/*  111: 154 */     this.actionShapeList.clear();
/*  112: 155 */     this.pCount = 0;
/*  113:     */   }
/*  114:     */   
/*  115:     */   public void setMode(int mode)
/*  116:     */   {
/*  117: 159 */     this.mode = mode;
/*  118: 160 */     this.selectedShapeList.clear();
/*  119:     */   }
/*  120:     */   
/*  121:     */   public int getMode()
/*  122:     */   {
/*  123: 164 */     return this.mode;
/*  124:     */   }
/*  125:     */   
/*  126:     */   public void setShapeType(int shapeType)
/*  127:     */   {
/*  128: 168 */     this.shapeType = shapeType;
/*  129: 169 */     this.selectedShapeList.clear();
/*  130:     */   }
/*  131:     */   
/*  132:     */   public int getShapeType()
/*  133:     */   {
/*  134: 173 */     return this.shapeType;
/*  135:     */   }
/*  136:     */   
/*  137:     */   public void setAction(int action)
/*  138:     */   {
/*  139: 178 */     this.action = action;
/*  140:     */   }
/*  141:     */   
/*  142:     */   public int getAction()
/*  143:     */   {
/*  144: 182 */     return this.action;
/*  145:     */   }
/*  146:     */   
/*  147:     */   public void clearSelectedShapes()
/*  148:     */   {
/*  149: 186 */     this.selectedShapeList.clear();
/*  150:     */   }
/*  151:     */   
/*  152:     */   public Color getSnapPointColor()
/*  153:     */   {
/*  154: 190 */     return this.snapPointColor;
/*  155:     */   }
/*  156:     */   
/*  157:     */   public void setSnapPointColor(Color snapPointColor)
/*  158:     */   {
/*  159: 194 */     this.snapPointColor = snapPointColor;
/*  160:     */   }
/*  161:     */   
/*  162:     */   public BasicStroke getSnapPointStroke()
/*  163:     */   {
/*  164: 198 */     return this.snapPointStroke;
/*  165:     */   }
/*  166:     */   
/*  167:     */   public void setSnapPointStroke(BasicStroke snapPointStroke)
/*  168:     */   {
/*  169: 202 */     this.snapPointStroke = snapPointStroke;
/*  170:     */   }
/*  171:     */   
/*  172:     */   public Color getShapeDrawingColor()
/*  173:     */   {
/*  174: 206 */     return this.shapeDrawingColor;
/*  175:     */   }
/*  176:     */   
/*  177:     */   public void setShapeDrawingColor(Color shapeDrawingColor)
/*  178:     */   {
/*  179: 210 */     this.shapeDrawingColor = shapeDrawingColor;
/*  180:     */   }
/*  181:     */   
/*  182:     */   public BasicStroke getShapeDrawingStroke()
/*  183:     */   {
/*  184: 214 */     return this.shapeDrawingStroke;
/*  185:     */   }
/*  186:     */   
/*  187:     */   public void setShapeDrawingStroke(BasicStroke shapeDrawingStroke)
/*  188:     */   {
/*  189: 218 */     this.shapeDrawingStroke = shapeDrawingStroke;
/*  190:     */   }
/*  191:     */   
/*  192:     */   public Collection getSelectedShapeList()
/*  193:     */   {
/*  194: 222 */     return this.selectedShapeList;
/*  195:     */   }
/*  196:     */   
/*  197:     */   public Stroke getSelectedShapeStroke()
/*  198:     */   {
/*  199: 233 */     return this.selectedShapeStroke;
/*  200:     */   }
/*  201:     */   
/*  202:     */   public void setSelectedShapeStroke(Stroke selectedShapeStroke)
/*  203:     */   {
/*  204: 237 */     this.selectedShapeStroke = selectedShapeStroke;
/*  205:     */   }
/*  206:     */   
/*  207:     */   public BufferedImage getImage()
/*  208:     */   {
/*  209: 241 */     draw();
/*  210: 242 */     return this.drawingContext.getImage();
/*  211:     */   }
/*  212:     */   
/*  213:     */   private synchronized void draw()
/*  214:     */   {
/*  215: 246 */     if (this.drawingContext.isChanged())
/*  216:     */     {
/*  217: 247 */       paint();
/*  218: 248 */       this.drawingContext.setChanged(false);
/*  219:     */     }
/*  220:     */   }
/*  221:     */   
/*  222:     */   private void paint()
/*  223:     */   {
/*  224: 254 */     Graphics2D big = this.drawingContext.getGraphics();
/*  225:     */     
/*  226: 256 */     Layout layout = this.document.getLayout();
/*  227: 257 */     if ((layout != null) && (layout.getLayoutType() == 0)) {
/*  228: 258 */       preDraw();
/*  229:     */     } else {
/*  230: 260 */       preDraw1();
/*  231:     */     }
/*  232: 264 */     Iterator iter1 = this.document.getLayers().iterator();
/*  233: 265 */     while (iter1.hasNext())
/*  234:     */     {
/*  235: 266 */       Layer layer = (Layer)iter1.next();
/*  236: 267 */       if (layer.getVisible() == true)
/*  237:     */       {
/*  238: 268 */         Iterator iter = layer.getShapes().iterator();
/*  239: 269 */         while (iter.hasNext())
/*  240:     */         {
/*  241: 270 */           Shape shape = (Shape)iter.next();
/*  242:     */           
/*  243: 272 */           big.setColor(shape.getColor());
/*  244:     */           
/*  245: 274 */           double width = UnitTranslator.lengthUnitToPixel(shape.getLineWeight().getLineWeightValue());
/*  246: 275 */           float w = this.drawingContext.distanseToScreen(width);
/*  247:     */           
/*  248: 277 */           float[] t = shape.getLineType().getLineTypeValue();
/*  249: 278 */           for (int i = 0; i < t.length; i++) {
/*  250: 279 */             t[i] = this.drawingContext.distanseToScreen(t[i] * 10.0F);
/*  251:     */           }
/*  252: 283 */           BasicStroke bs = new BasicStroke(w, 1, 1);
/*  253: 284 */           big.setStroke(bs);
/*  254: 286 */           if ((!this.selectedShapeList.contains(shape)) && 
/*  255: 287 */             (inWindowBounds(shape))) {
/*  256: 288 */             shape.draw(this.context);
/*  257:     */           }
/*  258:     */         }
/*  259:     */       }
/*  260:     */     }
/*  261: 297 */     if (this.selectedShapeList.size() > 0)
/*  262:     */     {
/*  263: 298 */       big.setStroke(this.selectedShapeStroke);
/*  264: 299 */       Iterator iter = this.selectedShapeList.iterator();
/*  265: 300 */       while (iter.hasNext())
/*  266:     */       {
/*  267: 301 */         Shape shape_tmp = (Shape)iter.next();
/*  268: 304 */         if (inWindowBounds(shape_tmp))
/*  269:     */         {
/*  270: 310 */           big.setColor(shape_tmp.getColor());
/*  271: 311 */           shape_tmp.drawOptimized(this.context);
/*  272:     */           
/*  273: 313 */           big.setColor(Color.blue);
/*  274: 314 */           Iterator iter2 = ((CadShape)shape_tmp).getActionPoints().iterator();
/*  275: 315 */           while (iter2.hasNext())
/*  276:     */           {
/*  277: 316 */             Point2D_ p = ((ActionPoint)iter2.next()).getPoint();
/*  278: 317 */             Point2I p1 = this.drawingContext.translateToScreen(p.x, p.y);
/*  279: 318 */             big.fillRect(p1.x - 5, p1.y - 5, 10, 10);
/*  280:     */           }
/*  281:     */         }
/*  282:     */       }
/*  283:     */     }
/*  284:     */   }
/*  285:     */   
/*  286:     */   private boolean inWindowBounds(Shape shape)
/*  287:     */   {
/*  288: 327 */     return true;
/*  289:     */   }
/*  290:     */   
/*  291:     */   private void preDraw()
/*  292:     */   {
/*  293: 332 */     Graphics2D big = this.drawingContext.getGraphics();
/*  294:     */     
/*  295: 334 */     big.setColor(this.backgroundColor);
/*  296: 335 */     big.setStroke(new BasicStroke(1.0F));
/*  297:     */     
/*  298: 337 */     big.fillRect(0, 0, this.drawingContext.getWidth(), this.drawingContext.getHeight());
/*  299:     */     
/*  300: 339 */     Point2I p1 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalLeft(), this.drawingContext.getLogicalTop()));
/*  301: 340 */     Point2I p2 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalRight(), this.drawingContext.getLogicalBottom()));
/*  302:     */     
/*  303:     */ 
/*  304: 343 */     big.setColor(Color.black);
/*  305: 344 */     big.drawRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
/*  306:     */     
/*  307:     */ 
/*  308:     */ 
/*  309:     */ 
/*  310:     */ 
/*  311:     */ 
/*  312:     */ 
/*  313:     */ 
/*  314:     */ 
/*  315:     */ 
/*  316:     */ 
/*  317:     */ 
/*  318:     */ 
/*  319:     */ 
/*  320:     */ 
/*  321:     */ 
/*  322:     */ 
/*  323:     */ 
/*  324:     */ 
/*  325:     */ 
/*  326:     */ 
/*  327: 366 */     big.setColor(Color.lightGray);
/*  328: 367 */     p1 = this.drawingContext.translateToScreen(new Point2D_(250.0D, 0.0D));
/*  329: 368 */     p2 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 0.0D));
/*  330: 369 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  331:     */     
/*  332: 371 */     p1 = this.drawingContext.translateToScreen(new Point2D_(250.0D, 0.0D));
/*  333: 372 */     p2 = this.drawingContext.translateToScreen(new Point2D_(240.0D, 5.0D));
/*  334: 373 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  335: 374 */     p1 = this.drawingContext.translateToScreen(new Point2D_(250.0D, 0.0D));
/*  336: 375 */     p2 = this.drawingContext.translateToScreen(new Point2D_(240.0D, -5.0D));
/*  337: 376 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  338:     */     
/*  339:     */ 
/*  340: 379 */     p1 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 250.0D));
/*  341: 380 */     p2 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 0.0D));
/*  342: 381 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  343:     */     
/*  344: 383 */     p1 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 250.0D));
/*  345: 384 */     p2 = this.drawingContext.translateToScreen(new Point2D_(5.0D, 240.0D));
/*  346: 385 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  347:     */     
/*  348: 387 */     p1 = this.drawingContext.translateToScreen(new Point2D_(0.0D, 250.0D));
/*  349: 388 */     p2 = this.drawingContext.translateToScreen(new Point2D_(-5.0D, 240.0D));
/*  350: 389 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  351:     */   }
/*  352:     */   
/*  353:     */   private void preDraw1()
/*  354:     */   {
/*  355: 397 */     Graphics2D big = this.drawingContext.getGraphics();
/*  356:     */     
/*  357:     */ 
/*  358: 400 */     big.setColor(Color.gray);
/*  359: 401 */     big.setStroke(new BasicStroke(1.0F));
/*  360:     */     
/*  361: 403 */     big.fillRect(0, 0, this.drawingContext.getWidth(), this.drawingContext.getHeight());
/*  362:     */     
/*  363: 405 */     Point2I p1 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalLeft(), this.drawingContext.getLogicalTop()));
/*  364: 406 */     Point2I p2 = this.drawingContext.translateToScreen(new Point2D_(this.drawingContext.getLogicalRight(), this.drawingContext.getLogicalBottom()));
/*  365:     */     
/*  366:     */ 
/*  367: 409 */     big.setColor(Color.black);
/*  368: 410 */     big.fillRect(p1.x + 5, p1.y + 5, p2.x - p1.x, p2.y - p1.y);
/*  369:     */     
/*  370: 412 */     big.setColor(Color.white);
/*  371: 413 */     big.fillRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
/*  372:     */     
/*  373: 415 */     big.setColor(Color.black);
/*  374: 416 */     big.drawRect(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
/*  375:     */   }
/*  376:     */   
/*  377:     */   protected void actionMoveCopy(Shape shape, double x1, double y1, double x2, double y2)
/*  378:     */   {
/*  379: 421 */     Graphics2D big = this.drawingContext.getGraphics();
/*  380:     */     
/*  381: 423 */     big.setXORMode(Color.white);
/*  382:     */     
/*  383: 425 */     Point2I p1 = this.drawingContext.translateToScreen(new Point2D_(x1, y1));
/*  384: 426 */     Point2I p2 = this.drawingContext.translateToScreen(new Point2D_(x2, y2));
/*  385: 427 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  386:     */     
/*  387: 429 */     shape.move(x1 - x2, y1 - y2);
/*  388: 430 */     shape.draw(this.context);
/*  389: 431 */     shape.move(-(x1 - x2), -(y1 - y2));
/*  390: 432 */     big.setPaintMode();
/*  391:     */   }
/*  392:     */   
/*  393:     */   protected void actionRotate(Shape shape, double x1, double y1, double x2, double y2)
/*  394:     */   {
/*  395: 439 */     Graphics2D big = this.drawingContext.getGraphics();
/*  396:     */     
/*  397: 441 */     big.setXORMode(Color.white);
/*  398:     */     
/*  399: 443 */     Point2I p1 = this.drawingContext.translateToScreen(new Point2D_(x1, y1));
/*  400: 444 */     Point2I p2 = this.drawingContext.translateToScreen(new Point2D_(x2, y2));
/*  401: 445 */     big.drawLine(p1.x, p1.y, p2.x, p2.y);
/*  402:     */     
/*  403: 447 */     double A = GeometryUtils.fingAngele(x1, y1, x2, y2);
/*  404:     */     
/*  405: 449 */     shape.rotate(x1, y1, A);
/*  406: 450 */     shape.draw(this.context);
/*  407: 451 */     shape.rotate(x1, y1, -A);
/*  408: 452 */     big.setPaintMode();
/*  409:     */   }
/*  410:     */   
/*  411:     */   public void addShape(Shape shape)
/*  412:     */   {
/*  413: 458 */     PropertyContext pContext = this.context.getPropertyContext();
/*  414:     */     
/*  415: 460 */     UndoableCommand cmd = addShapeAll(shape);
/*  416: 461 */     SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/*  417: 462 */     cmd.execute();
/*  418:     */     
/*  419: 464 */     this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  420:     */   }
/*  421:     */   
/*  422:     */   public void deleteSelectedShape()
/*  423:     */   {
/*  424: 468 */     if (this.selectedShapeList.size() > 0)
/*  425:     */     {
/*  426: 469 */       MacroCommand cmd = new MacroCommand();
/*  427: 470 */       Iterator iter = this.selectedShapeList.iterator();
/*  428: 471 */       while (iter.hasNext())
/*  429:     */       {
/*  430: 472 */         Shape shape_tmp = (Shape)iter.next();
/*  431: 473 */         Group g = shape_tmp.getParent();
/*  432: 474 */         if (g != null)
/*  433:     */         {
/*  434: 476 */           removeStaticSnapPoint((CadShape)shape_tmp);
/*  435:     */           
/*  436:     */ 
/*  437:     */ 
/*  438: 480 */           DeleteShapeCommand dc = new DeleteShapeCommand(g, shape_tmp);
/*  439: 481 */           cmd.addCommand(dc);
/*  440:     */         }
/*  441:     */       }
/*  442: 484 */       if (cmd.getCommandCount() > 0)
/*  443:     */       {
/*  444: 485 */         SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/*  445: 486 */         cmd.execute();
/*  446:     */       }
/*  447: 488 */       this.selectedShapeList.clear();
/*  448: 489 */       this.document.setModified(true);
/*  449: 490 */       this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  450:     */     }
/*  451:     */   }
/*  452:     */   
/*  453:     */   public void modifyShape(Shape oldShape, Shape newShape)
/*  454:     */   {
/*  455: 496 */     ModifyShapeCommand cmd = new ModifyShapeCommand(oldShape, newShape);
/*  456: 497 */     SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/*  457:     */     
/*  458: 499 */     removeStaticSnapPoint((CadShape)oldShape);
/*  459: 500 */     cmd.execute();
/*  460: 501 */     addStaticSnapPoint((CadShape)newShape);
/*  461:     */     
/*  462: 503 */     this.selectedShapeList.remove(oldShape);
/*  463: 504 */     this.selectedShapeList.add(newShape);
/*  464: 505 */     this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  465:     */   }
/*  466:     */   
/*  467:     */   public void moveShape(Shape shape, double vX, double vY)
/*  468:     */   {
/*  469: 509 */     UndoableCommand cmd = moveShapeAll(shape, vX, vY);
/*  470: 510 */     SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/*  471:     */     
/*  472: 512 */     removeStaticSnapPoint((CadShape)shape);
/*  473: 513 */     cmd.execute();
/*  474: 514 */     addStaticSnapPoint((CadShape)shape);
/*  475:     */     
/*  476: 516 */     this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  477:     */   }
/*  478:     */   
/*  479:     */   public void copyShape(Layer layer, Shape shape)
/*  480:     */   {
/*  481: 520 */     UndoableCommand cmd = copyShapeAll(layer, shape);
/*  482: 521 */     SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/*  483:     */     
/*  484:     */ 
/*  485: 524 */     cmd.execute();
/*  486: 525 */     addStaticSnapPoint((CadShape)shape);
/*  487:     */     
/*  488: 527 */     this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  489:     */   }
/*  490:     */   
/*  491:     */   public void rotateShape(Shape shape, double x, double y, double angle)
/*  492:     */   {
/*  493: 531 */     UndoableCommand cmd = rotateShapeAll(shape, x, y, angle);
/*  494: 532 */     SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/*  495:     */     
/*  496: 534 */     removeStaticSnapPoint((CadShape)shape);
/*  497: 535 */     cmd.execute();
/*  498: 536 */     addStaticSnapPoint((CadShape)shape);
/*  499:     */     
/*  500: 538 */     this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  501:     */   }
/*  502:     */   
/*  503:     */   public UndoableCommand addShapeAll(Shape shape)
/*  504:     */   {
/*  505: 542 */     PropertyContext pContext = this.context.getPropertyContext();
/*  506:     */     
/*  507: 544 */     shape.setColor(pContext.getColor());
/*  508: 545 */     shape.setLineWeight(pContext.getLineWeight());
/*  509: 546 */     shape.setLineType(pContext.getLineType());
/*  510:     */     
/*  511: 548 */     addStaticSnapPoint((CadShape)shape);
/*  512:     */     
/*  513: 550 */     AddShapeCommand cmd = new AddShapeCommand(shape, this.document.getCurrentLayer());
/*  514: 551 */     return cmd;
/*  515:     */   }
/*  516:     */   
/*  517:     */   public UndoableCommand moveShapeAll(Shape shape, double vX, double vY)
/*  518:     */   {
/*  519: 555 */     MoveShapeCommand cmd = new MoveShapeCommand(shape, vX, vY);
/*  520: 556 */     return cmd;
/*  521:     */   }
/*  522:     */   
/*  523:     */   public UndoableCommand copyShapeAll(Layer layer, Shape shape)
/*  524:     */   {
/*  525: 560 */     CopyShapeCommand cmd = new CopyShapeCommand(layer, shape);
/*  526: 561 */     return cmd;
/*  527:     */   }
/*  528:     */   
/*  529:     */   public UndoableCommand rotateShapeAll(Shape shape, double x, double y, double angle)
/*  530:     */   {
/*  531: 565 */     RotateShapeCommand cmd = new RotateShapeCommand(shape, x, y, angle);
/*  532: 566 */     return cmd;
/*  533:     */   }
/*  534:     */   
/*  535:     */   private void addStaticSnapPoint(CadShape shape)
/*  536:     */   {
/*  537: 570 */     Collection c = shape.getSnapPoints();
/*  538: 571 */     Iterator iter = c.iterator();
/*  539: 572 */     while (iter.hasNext())
/*  540:     */     {
/*  541: 573 */       SnapPoint sp = (SnapPoint)iter.next();
/*  542: 574 */       if (!this.snapList.contains(sp)) {
/*  543: 575 */         this.snapList.add(sp);
/*  544:     */       }
/*  545:     */     }
/*  546:     */   }
/*  547:     */   
/*  548:     */   private void removeStaticSnapPoint(CadShape shape)
/*  549:     */   {
/*  550: 580 */     Collection c = shape.getSnapPoints();
/*  551: 581 */     this.snapList.removeAll(c);
/*  552:     */   }
/*  553:     */   
/*  554:     */   public void mousePressed(MouseEvent evt)
/*  555:     */   {
/*  556: 587 */     Point2D_ p2d = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/*  557:     */     
/*  558: 589 */     double x = p2d.x;
/*  559: 590 */     double y = p2d.y;
/*  560:     */     
/*  561: 592 */     this.x_clicked = (this.x_clicked_old = x);
/*  562: 593 */     this.y_clicked = (this.y_clicked_old = y);
/*  563:     */     
/*  564: 595 */     this.mouseButton = evt.getButton();
/*  565: 597 */     if ((this.mode == 4) && (this.mouseButton == 1))
/*  566:     */     {
/*  567: 599 */       this.drawingContext.changeScaleTo(-1);
/*  568: 600 */       this.document.setModified(true);
/*  569:     */     }
/*  570: 603 */     if ((this.mode == 5) && (this.mouseButton == 1))
/*  571:     */     {
/*  572: 605 */       this.drawingContext.changeScaleTo(1);
/*  573: 606 */       this.document.setModified(true);
/*  574:     */     }
/*  575: 609 */     if (this.pCount == 0)
/*  576:     */     {
/*  577: 610 */       startAction(evt);
/*  578: 611 */       return;
/*  579:     */     }
/*  580: 614 */     if ((this.pCount > 0) && 
/*  581: 615 */       (this.xP[(this.pCount - 1)] != x) && (this.yP[(this.pCount - 1)] != y)) {
/*  582: 616 */       endAction(evt);
/*  583:     */     }
/*  584: 620 */     this.document.setModified(true);
/*  585:     */   }
/*  586:     */   
/*  587:     */   public void mouseDragged(MouseEvent evt)
/*  588:     */   {
/*  589: 627 */     Point2D_ p2d = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/*  590: 628 */     double x = p2d.x;
/*  591: 629 */     double y = p2d.y;
/*  592: 631 */     if ((this.mode == 2) || (this.mouseButton == 3))
/*  593:     */     {
/*  594: 634 */       boolean redraw = true;
/*  595:     */       
/*  596: 636 */       double lenX = this.x_clicked - x;
/*  597: 637 */       double lenY = this.y_clicked - y;
/*  598:     */       
/*  599:     */ 
/*  600: 640 */       this.drawingContext.moveTo(lenX, lenY);
/*  601:     */       
/*  602: 642 */       this.pCount = 0;
/*  603: 644 */       if (redraw) {
/*  604: 645 */         this.drawingContext.setChanged(true);
/*  605:     */       }
/*  606: 646 */       return;
/*  607:     */     }
/*  608: 649 */     if (this.pCount == 1) {
/*  609: 650 */       continousAction(evt);
/*  610:     */     }
/*  611:     */   }
/*  612:     */   
/*  613:     */   public void mouseMoved(MouseEvent evt)
/*  614:     */   {
/*  615: 656 */     Graphics2D big = this.drawingContext.getGraphics();
/*  616:     */     
/*  617: 658 */     Point2D_ p2d = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/*  618: 659 */     double x = p2d.x;
/*  619: 660 */     double y = p2d.y;
/*  620:     */     
/*  621: 662 */     this.propertyChangeSupport.firePropertyChange("mouseCoordinateX", Double.valueOf(0.0D), Double.valueOf(UnitTranslator.pixelToMilemetor(p2d.x).getValue()));
/*  622: 663 */     this.propertyChangeSupport.firePropertyChange("mouseCoordinateY", Double.valueOf(0.0D), Double.valueOf(UnitTranslator.pixelToMilemetor(p2d.y).getValue()));
/*  623:     */     
/*  624: 665 */     this.propertyChangeSupport.firePropertyChange("scale", Double.valueOf(0.0D), Double.valueOf(this.drawingContext.getScale()));
/*  625: 667 */     if (this.pCount > 0) {
/*  626: 668 */       continousAction(evt);
/*  627:     */     }
/*  628: 671 */     if (this.mode == 3)
/*  629:     */     {
/*  630: 673 */       if (this.currentSnapPoint != null) {
/*  631: 674 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/*  632:     */       }
/*  633: 676 */       this.currentSnapPoint = null;
/*  634:     */       
/*  635:     */ 
/*  636: 679 */       int minlen = 2147483647;
/*  637: 680 */       for (int i = 0; i < this.snapList.size(); i++)
/*  638:     */       {
/*  639: 681 */         SnapPoint sp = (SnapPoint)this.snapList.get(i);
/*  640: 682 */         Point2D_ p = sp.getPoint();
/*  641:     */         
/*  642: 684 */         double len = GeometryUtils.length(p.x, p.y, x, y);
/*  643: 685 */         int ilen = this.drawingContext.distanseToScreen(len);
/*  644: 687 */         if ((ilen < this.snapSensitiveLength) && (minlen > ilen))
/*  645:     */         {
/*  646: 688 */           minlen = ilen;
/*  647: 689 */           this.currentSnapPoint = sp;
/*  648:     */         }
/*  649:     */       }
/*  650: 692 */       if (this.currentSnapPoint != null) {
/*  651: 693 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/*  652:     */       }
/*  653:     */     }
/*  654:     */   }
/*  655:     */   
/*  656:     */   protected void startAction(MouseEvent evt)
/*  657:     */   {
/*  658: 701 */     Graphics2D big = this.drawingContext.getGraphics();
/*  659:     */     
/*  660: 703 */     double x = this.x_clicked;
/*  661: 704 */     double y = this.y_clicked;
/*  662: 706 */     if ((this.selectedShapeList.size() > 0) && (this.action == 1))
/*  663:     */     {
/*  664: 707 */       Iterator iter = this.selectedShapeList.iterator();
/*  665: 708 */       while (iter.hasNext())
/*  666:     */       {
/*  667: 709 */         CadShape shape_tmp = (CadShape)iter.next();
/*  668:     */         
/*  669: 711 */         Iterator iter1 = shape_tmp.getActionPoints().iterator();
/*  670: 712 */         while (iter1.hasNext())
/*  671:     */         {
/*  672: 713 */           ActionPoint ap = (ActionPoint)iter1.next();
/*  673: 714 */           Point2D_ p = ap.getPoint();
/*  674:     */           
/*  675: 716 */           double dis = this.drawingContext.distanseToLogical(5.0D);
/*  676: 718 */           if ((x >= p.x - dis) && (x <= p.x + dis) && (y >= p.y - dis) && (y <= p.y + dis))
/*  677:     */           {
/*  678: 719 */             this.action = 5;
/*  679: 720 */             shape_tmp.setActionPoint(ap);
/*  680: 721 */             this.actionShape = shape_tmp;
/*  681: 722 */             break;
/*  682:     */           }
/*  683:     */         }
/*  684:     */       }
/*  685:     */     }
/*  686: 728 */     if ((this.action != 1) && (this.selectedShapeList.size() > 0) && (this.mouseButton == 1))
/*  687:     */     {
/*  688: 731 */       this.xP[this.pCount] = this.x_clicked;
/*  689: 732 */       this.yP[this.pCount] = this.y_clicked;
/*  690: 734 */       if (this.action != 5)
/*  691:     */       {
/*  692: 735 */         Iterator iter = this.selectedShapeList.iterator();
/*  693: 736 */         while (iter.hasNext())
/*  694:     */         {
/*  695: 737 */           Shape shape_tmp = (Shape)iter.next();
/*  696:     */           
/*  697: 739 */           this.actionShapeList.add(shape_tmp);
/*  698: 742 */           if (this.action == 2) {
/*  699: 743 */             actionMoveCopy(shape_tmp, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  700:     */           }
/*  701: 746 */           if (this.action == 3) {
/*  702: 747 */             actionMoveCopy(shape_tmp, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  703:     */           }
/*  704: 750 */           if (this.action == 4) {
/*  705: 751 */             actionRotate(shape_tmp, this.x_clicked, this.y_clicked, this.x_clicked_old, this.y_clicked_old);
/*  706:     */           }
/*  707:     */         }
/*  708:     */       }
/*  709: 756 */       if ((this.action == 5) && (this.actionShape != null)) {
/*  710: 758 */         this.actionShape.moveActionPoint(this, this.context, x, y);
/*  711:     */       }
/*  712: 761 */       this.pCount += 1;
/*  713: 762 */       return;
/*  714:     */     }
/*  715: 764 */     this.action = 1;
/*  716: 767 */     if ((this.mode == 3) && (this.mouseButton == 1))
/*  717:     */     {
/*  718: 769 */       if (this.currentSnapPoint != null)
/*  719:     */       {
/*  720: 770 */         Point2D_ p = this.currentSnapPoint.getPoint();
/*  721: 771 */         x = p.x;
/*  722: 772 */         y = p.y;
/*  723:     */       }
/*  724: 775 */       this.xP[this.pCount] = x;
/*  725: 776 */       this.yP[this.pCount] = y;
/*  726: 779 */       if ((this.shapeType == 3) && 
/*  727: 780 */         (this.pCount == 1))
/*  728:     */       {
/*  729: 781 */         double radius = Math.sqrt((this.xP[0] - this.xP[1]) * (this.xP[0] - this.xP[1]) + (this.yP[0] - this.yP[1]) * (this.yP[0] - this.yP[1]));
/*  730: 782 */         Shape shape = new Circle(this.xP[0], this.yP[0], radius);
/*  731: 783 */         addShape(shape);
/*  732:     */         
/*  733: 785 */         this.pCount = 0;
/*  734: 786 */         this.mode = 1;
/*  735: 787 */         this.drawingContext.setChanged(true);
/*  736: 788 */         return;
/*  737:     */       }
/*  738: 793 */       if ((this.shapeType == 5) && 
/*  739: 794 */         (this.pCount > 0)) {
/*  740: 795 */         for (int i = 0; i < this.pCount; i++) {
/*  741: 796 */           DrawingUtils.drawStarngeLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.xP[i], this.yP[i], this.xP[(i + 1)], this.yP[(i + 1)]);
/*  742:     */         }
/*  743:     */       }
/*  744: 801 */       if ((this.shapeType == 2) && 
/*  745: 802 */         (this.pCount > 0)) {
/*  746: 803 */         for (int i = 0; i < this.pCount; i++) {
/*  747: 804 */           DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.xP[i], this.yP[i], this.xP[(i + 1)], this.yP[(i + 1)]);
/*  748:     */         }
/*  749:     */       }
/*  750: 809 */       if ((this.shapeType == 1) && 
/*  751: 810 */         (this.pCount > 0)) {
/*  752: 811 */         for (int i = 0; i < this.pCount; i++) {
/*  753: 812 */           DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.xP[i], this.yP[i], this.xP[(i + 1)], this.yP[(i + 1)]);
/*  754:     */         }
/*  755:     */       }
/*  756: 817 */       if ((this.shapeType == 4) && 
/*  757: 818 */         (this.pCount > 0)) {
/*  758: 819 */         for (int i = 0; i < this.pCount; i++) {
/*  759: 820 */           DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.xP[i], this.yP[i], this.xP[(i + 1)], this.yP[(i + 1)]);
/*  760:     */         }
/*  761:     */       }
/*  762: 825 */       this.x_clicked_old = x;
/*  763: 826 */       this.y_clicked_old = y;
/*  764:     */       
/*  765: 828 */       this.pCount += 1;
/*  766:     */     }
/*  767: 831 */     if ((this.mode == 1) && (this.pCount == 0) && (this.mouseButton == 1))
/*  768:     */     {
/*  769: 836 */       Iterator iter1 = this.document.getLayers().iterator();
/*  770: 837 */       while (iter1.hasNext())
/*  771:     */       {
/*  772: 838 */         Layer layer = (Layer)iter1.next();
/*  773: 839 */         if ((layer.getVisible()) && (layer.getEnable()))
/*  774:     */         {
/*  775: 841 */           Iterator iter = layer.getShapes().iterator();
/*  776: 842 */           while (iter.hasNext())
/*  777:     */           {
/*  778: 843 */             Shape shape = (Shape)iter.next();
/*  779:     */             
/*  780: 845 */             double dis = this.drawingContext.distanseToLogical(2.0D);
/*  781: 847 */             if (shape.containsPoint(x, y, dis))
/*  782:     */             {
/*  783: 849 */               this.selectedShapeList.add(shape);
/*  784:     */               
/*  785: 851 */               this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  786: 852 */               this.drawingContext.setChanged(true);
/*  787: 853 */               return;
/*  788:     */             }
/*  789:     */           }
/*  790:     */         }
/*  791:     */       }
/*  792: 859 */       DrawingUtils.drawRectangleXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  793: 860 */       this.xP[this.pCount] = x;
/*  794: 861 */       this.yP[this.pCount] = y;
/*  795: 862 */       this.pCount += 1;
/*  796:     */     }
/*  797:     */   }
/*  798:     */   
/*  799:     */   protected void continousAction(MouseEvent evt)
/*  800:     */   {
/*  801: 870 */     Point2D_ p2d = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/*  802: 871 */     double x = p2d.x;
/*  803: 872 */     double y = p2d.y;
/*  804:     */     
/*  805:     */ 
/*  806:     */ 
/*  807:     */ 
/*  808:     */ 
/*  809: 878 */     Graphics2D big = this.drawingContext.getGraphics();
/*  810: 880 */     if ((this.action != 1) && (this.mouseButton == 1))
/*  811:     */     {
/*  812: 883 */       if (this.currentSnapPoint != null)
/*  813:     */       {
/*  814: 884 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/*  815:     */         
/*  816: 886 */         this.currentSnapPoint = null;
/*  817:     */       }
/*  818: 888 */       int minlen = 2147483647;
/*  819: 889 */       for (int i = 0; i < this.snapList.size(); i++)
/*  820:     */       {
/*  821: 890 */         SnapPoint sp = (SnapPoint)this.snapList.get(i);
/*  822: 891 */         Point2D_ p = sp.getPoint();
/*  823: 892 */         double len = GeometryUtils.length(p.x, p.y, x, y);
/*  824: 893 */         int ilen = this.drawingContext.distanseToScreen(len);
/*  825: 894 */         if ((ilen < this.snapSensitiveLength) && (minlen > ilen))
/*  826:     */         {
/*  827: 895 */           minlen = ilen;
/*  828: 896 */           this.currentSnapPoint = sp;
/*  829:     */         }
/*  830:     */       }
/*  831: 899 */       if (this.currentSnapPoint != null) {
/*  832: 900 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/*  833:     */       }
/*  834: 905 */       if (this.action != 5) {
/*  835: 906 */         for (int i = 0; i < this.actionShapeList.size(); i++)
/*  836:     */         {
/*  837: 908 */           Shape shape_tmp = (Shape)this.actionShapeList.get(i);
/*  838: 910 */           if (this.action == 2)
/*  839:     */           {
/*  840: 911 */             actionMoveCopy(shape_tmp, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  841: 912 */             actionMoveCopy(shape_tmp, x, y, this.x_clicked, this.y_clicked);
/*  842:     */           }
/*  843: 915 */           if (this.action == 3)
/*  844:     */           {
/*  845: 916 */             actionMoveCopy(shape_tmp, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  846: 917 */             actionMoveCopy(shape_tmp, x, y, this.x_clicked, this.y_clicked);
/*  847:     */           }
/*  848: 920 */           if (this.action == 4)
/*  849:     */           {
/*  850: 921 */             actionRotate(shape_tmp, this.x_clicked, this.y_clicked, this.x_clicked_old, this.y_clicked_old);
/*  851: 922 */             actionRotate(shape_tmp, this.x_clicked, this.y_clicked, x, y);
/*  852:     */           }
/*  853:     */         }
/*  854:     */       }
/*  855: 927 */       if ((this.action == 5) && (this.actionShape != null))
/*  856:     */       {
/*  857: 929 */         this.actionShape.moveActionPoint(this, this.context, this.x_clicked_old, this.y_clicked_old);
/*  858: 930 */         this.actionShape.moveActionPoint(this, this.context, x, y);
/*  859:     */       }
/*  860: 934 */       this.x_clicked_old = x;
/*  861: 935 */       this.y_clicked_old = y;
/*  862: 936 */       return;
/*  863:     */     }
/*  864: 939 */     if (this.mode == 3)
/*  865:     */     {
/*  866: 942 */       if (this.currentSnapPoint != null) {
/*  867: 943 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/*  868:     */       }
/*  869: 945 */       this.currentSnapPoint = null;
/*  870:     */       
/*  871:     */ 
/*  872: 948 */       int minlen = 2147483647;
/*  873: 949 */       for (int i = 0; i < this.snapList.size(); i++)
/*  874:     */       {
/*  875: 950 */         SnapPoint sp = (SnapPoint)this.snapList.get(i);
/*  876: 951 */         Point2D_ p = sp.getPoint();
/*  877:     */         
/*  878: 953 */         double len = GeometryUtils.length(p.x, p.y, x, y);
/*  879: 954 */         int ilen = this.drawingContext.distanseToScreen(len);
/*  880: 956 */         if ((ilen < this.snapSensitiveLength) && (minlen > ilen))
/*  881:     */         {
/*  882: 957 */           minlen = ilen;
/*  883: 958 */           this.currentSnapPoint = sp;
/*  884:     */         }
/*  885:     */       }
/*  886: 961 */       if (this.currentSnapPoint != null) {
/*  887: 962 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/*  888:     */       }
/*  889: 966 */       int tmp = this.pCount - 1;
/*  890: 968 */       if (this.pCount > 0)
/*  891:     */       {
/*  892: 970 */         if ((this.shapeType == 3) && 
/*  893: 971 */           (this.pCount == 1))
/*  894:     */         {
/*  895: 972 */           double r = GeometryUtils.length(this.xP[0], this.yP[0], this.x_clicked_old, this.y_clicked_old);
/*  896: 973 */           DrawingUtils.drawOvalXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.xP[0], this.yP[0], r);
/*  897: 974 */           r = GeometryUtils.length(this.xP[0], this.yP[0], x, y);
/*  898: 975 */           DrawingUtils.drawOvalXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.xP[0], this.yP[0], r);
/*  899:     */         }
/*  900: 979 */         if (this.shapeType == 5)
/*  901:     */         {
/*  902: 980 */           DrawingUtils.drawStarngeLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.xP[tmp], this.yP[tmp]);
/*  903: 981 */           DrawingUtils.drawStarngeLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, x, y, this.xP[tmp], this.yP[tmp]);
/*  904:     */         }
/*  905: 984 */         if (this.shapeType == 4)
/*  906:     */         {
/*  907: 985 */           DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.xP[tmp], this.yP[tmp]);
/*  908: 986 */           DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, x, y, this.xP[tmp], this.yP[tmp]);
/*  909:     */         }
/*  910: 989 */         if (this.shapeType == 1)
/*  911:     */         {
/*  912: 990 */           DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.xP[tmp], this.yP[tmp]);
/*  913: 991 */           DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, x, y, this.xP[tmp], this.yP[tmp]);
/*  914:     */         }
/*  915:     */       }
/*  916: 995 */       this.x_clicked_old = x;
/*  917: 996 */       this.y_clicked_old = y;
/*  918:     */       
/*  919: 998 */       return;
/*  920:     */     }
/*  921:1002 */     if ((this.mode == 1) && (this.pCount == 1) && (this.mouseButton == 1))
/*  922:     */     {
/*  923:1006 */       DrawingUtils.drawRectangleXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  924:1007 */       DrawingUtils.drawRectangleXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, x, y, this.x_clicked, this.y_clicked);
/*  925:     */       
/*  926:1009 */       this.x_clicked_old = x;
/*  927:1010 */       this.y_clicked_old = y;
/*  928:1011 */       return;
/*  929:     */     }
/*  930:     */   }
/*  931:     */   
/*  932:     */   protected void endAction(MouseEvent evt)
/*  933:     */   {
/*  934:1017 */     Point2D_ p2d = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/*  935:1018 */     double x = p2d.x;
/*  936:1019 */     double y = p2d.y;
/*  937:     */     
/*  938:1021 */     Graphics2D big = this.drawingContext.getGraphics();
/*  939:1023 */     if ((this.action != 1) && (this.mouseButton == 1))
/*  940:     */     {
/*  941:1026 */       if (this.currentSnapPoint != null)
/*  942:     */       {
/*  943:1027 */         Point2D_ p = this.currentSnapPoint.getPoint();
/*  944:1028 */         x = p.x;
/*  945:1029 */         y = p.y;
/*  946:1030 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/*  947:     */         
/*  948:1032 */         this.currentSnapPoint = null;
/*  949:     */       }
/*  950:1035 */       if (this.action != 5) {
/*  951:1037 */         if (this.actionShapeList.size() > 0)
/*  952:     */         {
/*  953:1040 */           MacroCommand cmd = new MacroCommand();
/*  954:1042 */           for (int i = 0; i < this.actionShapeList.size(); i++)
/*  955:     */           {
/*  956:1044 */             Shape shape_tmp = (Shape)this.actionShapeList.get(i);
/*  957:1046 */             if (this.action == 2)
/*  958:     */             {
/*  959:1047 */               actionMoveCopy(shape_tmp, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  960:1048 */               cmd.addCommand(moveShapeAll(shape_tmp, x - this.xP[0], y - this.yP[0]));
/*  961:1049 */               this.selectedShapeList.clear();
/*  962:     */             }
/*  963:1052 */             if (this.action == 3)
/*  964:     */             {
/*  965:1053 */               actionMoveCopy(shape_tmp, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/*  966:     */               
/*  967:1055 */               Shape sh = (Shape)shape_tmp.clone();
/*  968:1056 */               sh.move(x - this.xP[0], y - this.yP[0]);
/*  969:     */               
/*  970:1058 */               cmd.addCommand(copyShapeAll(this.document.getCurrentLayer(), sh));
/*  971:     */               
/*  972:1060 */               addStaticSnapPoint((CadShape)sh);
/*  973:1061 */               this.selectedShapeList.clear();
/*  974:     */             }
/*  975:1064 */             if (this.action == 4)
/*  976:     */             {
/*  977:1065 */               actionRotate(shape_tmp, this.x_clicked, this.y_clicked, this.x_clicked_old, this.y_clicked_old);
/*  978:1066 */               double A = GeometryUtils.fingAngele(this.xP[0], this.yP[0], x, y);
/*  979:     */               
/*  980:1068 */               cmd.addCommand(rotateShapeAll(shape_tmp, this.xP[0], this.yP[0], A));
/*  981:     */               
/*  982:1070 */               this.selectedShapeList.clear();
/*  983:     */             }
/*  984:     */           }
/*  985:1074 */           if (cmd.getCommandCount() > 0)
/*  986:     */           {
/*  987:1075 */             SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/*  988:1076 */             cmd.execute();
/*  989:     */             
/*  990:1078 */             this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/*  991:     */           }
/*  992:     */         }
/*  993:     */       }
/*  994:1083 */       if ((this.action == 5) && (this.actionShape != null)) {
/*  995:1084 */         this.actionShape.postActionPoint(this, x, y);
/*  996:     */       }
/*  997:1087 */       this.action = 1;
/*  998:1088 */       this.actionShapeList.clear();
/*  999:1089 */       this.actionShape = null;
/* 1000:     */       
/* 1001:1091 */       this.drawingContext.setChanged(true);
/* 1002:     */       
/* 1003:1093 */       this.pCount = 0;
/* 1004:1094 */       return;
/* 1005:     */     }
/* 1006:1098 */     if ((this.mode == 3) && (this.mouseButton == 1))
/* 1007:     */     {
/* 1008:1100 */       if (this.currentSnapPoint != null)
/* 1009:     */       {
/* 1010:1101 */         Point2D_ p = this.currentSnapPoint.getPoint();
/* 1011:1102 */         x = p.x;
/* 1012:1103 */         y = p.y;
/* 1013:1104 */         DrawingUtils.drawSnapPointXor(this.drawingContext, this.snapPointColor, this.snapPointStroke, this.currentSnapPoint);
/* 1014:     */         
/* 1015:1106 */         this.currentSnapPoint = null;
/* 1016:     */       }
/* 1017:1110 */       this.xP[this.pCount] = x;
/* 1018:1111 */       this.yP[this.pCount] = y;
/* 1019:1114 */       if ((this.shapeType == 3) && 
/* 1020:1115 */         (this.pCount == 1))
/* 1021:     */       {
/* 1022:1116 */         double radius = Math.sqrt((this.xP[0] - this.xP[1]) * (this.xP[0] - this.xP[1]) + (this.yP[0] - this.yP[1]) * (this.yP[0] - this.yP[1]));
/* 1023:1117 */         Shape shape = new CadCircle(this.xP[0], this.yP[0], radius);
/* 1024:1118 */         addShape(shape);
/* 1025:     */         
/* 1026:1120 */         this.selectedShapeList.clear();
/* 1027:1121 */         this.pCount = 0;
/* 1028:1122 */         this.mode = 1;
/* 1029:1123 */         this.drawingContext.setChanged(true);
/* 1030:1124 */         return;
/* 1031:     */       }
/* 1032:1129 */       if ((this.shapeType == 5) && 
/* 1033:1130 */         (this.pCount > 0))
/* 1034:     */       {
/* 1035:1132 */         Shape shape = new CadLine(this.xP[0], this.yP[0], this.xP[1], this.yP[1], this.context);
/* 1036:1133 */         addShape(shape);
/* 1037:     */         
/* 1038:1135 */         this.selectedShapeList.clear();
/* 1039:     */         
/* 1040:1137 */         this.pCount = 0;
/* 1041:1138 */         this.mode = 1;
/* 1042:1139 */         this.drawingContext.setChanged(true);
/* 1043:1140 */         return;
/* 1044:     */       }
/* 1045:1144 */       if ((this.shapeType == 4) && 
/* 1046:1145 */         (this.pCount > 0))
/* 1047:     */       {
/* 1048:1146 */         DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.xP[(this.pCount - 1)], this.yP[(this.pCount - 1)]);
/* 1049:1147 */         for (int i = 0; i < this.pCount; i++) {
/* 1050:1148 */           DrawingUtils.drawLine(this.drawingContext, this.foregroundColor, this.shapeDrawingStroke, this.xP[i], this.yP[i], this.xP[(i + 1)], this.yP[(i + 1)]);
/* 1051:     */         }
/* 1052:     */       }
/* 1053:1153 */       if ((this.shapeType == 1) && 
/* 1054:1154 */         (this.pCount > 0))
/* 1055:     */       {
/* 1056:1155 */         DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.xP[(this.pCount - 1)], this.yP[(this.pCount - 1)]);
/* 1057:1156 */         for (int i = 0; i < this.pCount; i++) {
/* 1058:1157 */           DrawingUtils.drawLine(this.drawingContext, this.foregroundColor, this.shapeDrawingStroke, this.xP[i], this.yP[i], this.xP[(i + 1)], this.yP[(i + 1)]);
/* 1059:     */         }
/* 1060:     */       }
/* 1061:1162 */       this.x_clicked_old = x;
/* 1062:1163 */       this.y_clicked_old = y;
/* 1063:     */       
/* 1064:1165 */       this.pCount += 1;
/* 1065:1166 */       return;
/* 1066:     */     }
/* 1067:1170 */     if ((this.mode == 1) && (this.mouseButton == 1))
/* 1068:     */     {
/* 1069:1174 */       double x_tmp = Math.min(this.xP[0], x);
/* 1070:1175 */       double y_tmp = Math.max(this.yP[0], y);
/* 1071:1176 */       double width_tmp = Math.abs(this.xP[0] - x);
/* 1072:1177 */       double height_tmp = Math.abs(this.yP[0] - y);
/* 1073:1178 */       Rectangle2D_ rect_tmp = new Rectangle2D_(x_tmp, y_tmp, width_tmp, height_tmp);
/* 1074:     */       
/* 1075:1180 */       Iterator iter1 = this.document.getLayers().iterator();
/* 1076:1181 */       while (iter1.hasNext())
/* 1077:     */       {
/* 1078:1182 */         Layer layer = (Layer)iter1.next();
/* 1079:1183 */         if ((layer.getVisible()) && (layer.getEnable()))
/* 1080:     */         {
/* 1081:1184 */           Iterator iter = layer.getShapes().iterator();
/* 1082:1185 */           while (iter.hasNext())
/* 1083:     */           {
/* 1084:1186 */             Shape shape = (Shape)iter.next();
/* 1085:1187 */             Rectangle2D_ rect = shape.getBounds();
/* 1086:1189 */             if (containsBounds(rect_tmp, rect)) {
/* 1087:1190 */               this.selectedShapeList.add(shape);
/* 1088:     */             }
/* 1089:     */           }
/* 1090:     */         }
/* 1091:     */       }
/* 1092:1196 */       DrawingUtils.drawRectangleXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.x_clicked, this.y_clicked);
/* 1093:     */       
/* 1094:1198 */       this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/* 1095:     */       
/* 1096:1200 */       this.drawingContext.setChanged(true);
/* 1097:1201 */       this.pCount = 0;
/* 1098:1202 */       return;
/* 1099:     */     }
/* 1100:     */   }
/* 1101:     */   
/* 1102:     */   protected boolean containsBounds(Rectangle2D_ r1, Rectangle2D_ r2)
/* 1103:     */   {
/* 1104:1207 */     if ((r1.x <= r2.x) && (r1.y >= r2.y) && (r1.x + r1.width >= r2.x + r2.width) && (r1.y - r1.height <= r2.y - r2.height)) {
/* 1105:1211 */       return true;
/* 1106:     */     }
/* 1107:1212 */     return false;
/* 1108:     */   }
/* 1109:     */   
/* 1110:     */   public void mouseWheelMoved(MouseWheelEvent evt)
/* 1111:     */   {
/* 1112:1216 */     Graphics2D big = this.drawingContext.getGraphics();
/* 1113:     */     
/* 1114:1218 */     Point2D_ p2d = this.drawingContext.translateToLogical(evt.getX(), evt.getY());
/* 1115:1219 */     double x = p2d.x;
/* 1116:1220 */     double y = p2d.y;
/* 1117:     */     
/* 1118:1222 */     this.x_clicked_old = this.x_clicked;
/* 1119:1223 */     this.y_clicked_old = this.y_clicked;
/* 1120:1225 */     if ((this.mode == 1) && (this.pCount == 1) && (this.mouseButton == 1)) {
/* 1121:1229 */       DrawingUtils.drawRectangleXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, x, y, this.x_clicked, this.y_clicked);
/* 1122:     */     }
/* 1123:1232 */     this.drawingContext.changeScaleTo(evt.getWheelRotation());
/* 1124:     */     
/* 1125:1234 */     this.propertyChangeSupport.firePropertyChange("scale", Double.valueOf(0.0D), Double.valueOf(this.drawingContext.getScale()));
/* 1126:     */   }
/* 1127:     */   
/* 1128:     */   public void keyPressed(KeyEvent evt)
/* 1129:     */   {
/* 1130:1239 */     Graphics2D big = this.drawingContext.getGraphics();
/* 1131:1241 */     if (this.mode == 1)
/* 1132:     */     {
/* 1133:1242 */       if ((evt.getKeyCode() == 127) && 
/* 1134:1243 */         (this.selectedShapeList.size() > 0))
/* 1135:     */       {
/* 1136:1244 */         deleteSelectedShape();
/* 1137:1245 */         this.drawingContext.setChanged(true);
/* 1138:1246 */         this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/* 1139:1247 */         return;
/* 1140:     */       }
/* 1141:1251 */       if (evt.getKeyCode() == 27)
/* 1142:     */       {
/* 1143:1252 */         if (this.selectedShapeList.size() > 0)
/* 1144:     */         {
/* 1145:1253 */           this.selectedShapeList.clear();
/* 1146:1254 */           this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/* 1147:     */         }
/* 1148:1257 */         this.drawingContext.setChanged(true);
/* 1149:1258 */         this.pCount = 0;
/* 1150:     */       }
/* 1151:     */     }
/* 1152:1263 */     if ((this.mode == 3) && 
/* 1153:1264 */       (evt.getKeyCode() == 27))
/* 1154:     */     {
/* 1155:1265 */       int count = this.pCount - 1;
/* 1156:     */       
/* 1157:     */ 
/* 1158:1268 */       DrawingUtils.drawLineXor(this.drawingContext, this.shapeDrawingColor, this.shapeDrawingStroke, this.x_clicked_old, this.y_clicked_old, this.xP[count], this.yP[count]);
/* 1159:     */       
/* 1160:     */ 
/* 1161:     */ 
/* 1162:1272 */       Shape shape = null;
/* 1163:1274 */       if (this.shapeType == 4)
/* 1164:     */       {
/* 1165:1275 */         MacroCommand cmd = new MacroCommand();
/* 1166:1276 */         for (int i = 0; i < this.pCount - 1; i++)
/* 1167:     */         {
/* 1168:1277 */           shape = new CadSegment(this.xP[i], this.yP[i], this.xP[(i + 1)], this.yP[(i + 1)]);
/* 1169:1278 */           cmd.addCommand(addShapeAll(shape));
/* 1170:     */         }
/* 1171:1280 */         if (cmd.getCommandCount() > 0)
/* 1172:     */         {
/* 1173:1281 */           SimpleURManager.getInstance().getUREngine(this.document).addCommand(cmd);
/* 1174:1282 */           cmd.execute();
/* 1175:     */           
/* 1176:1284 */           this.propertyChangeSupport.firePropertyChange("redraw", Double.valueOf(1.0D), Double.valueOf(2.0D));
/* 1177:     */         }
/* 1178:     */       }
/* 1179:1288 */       if (this.shapeType == 1)
/* 1180:     */       {
/* 1181:1289 */         shape = new CadPolyline(this.xP, this.yP, this.pCount);
/* 1182:1290 */         addShape(shape);
/* 1183:     */       }
/* 1184:1293 */       this.selectedShapeList.clear();
/* 1185:     */       
/* 1186:     */ 
/* 1187:1296 */       this.pCount = 0;
/* 1188:1297 */       this.mode = 1;
/* 1189:1298 */       this.drawingContext.setChanged(true);
/* 1190:     */     }
/* 1191:     */   }
/* 1192:     */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.CadDrawingEngine
 * JD-Core Version:    0.7.0.1
 */