/*    1:     */ package org.cadbox.j2d.scale.core;
/*    2:     */ 
/*    3:     */ public final class GeometryUtils
/*    4:     */ {
/*    5:     */   private static boolean isBetween(int a, int b, int c)
/*    6:     */   {
/*    7:  13 */     return (c >= a) && (c <= b);
/*    8:     */   }
/*    9:     */   
/*   10:     */   private static boolean isBetween(double a, double b, double c)
/*   11:     */   {
/*   12:  22 */     return (c >= a) && (c <= b);
/*   13:     */   }
/*   14:     */   
/*   15:     */   public static boolean equals(double a, double b, double limit)
/*   16:     */   {
/*   17:  37 */     return Math.abs(a - b) < limit;
/*   18:     */   }
/*   19:     */   
/*   20:     */   public static boolean equals(double a, double b)
/*   21:     */   {
/*   22:  51 */     return equals(a, b, 1.E-005D);
/*   23:     */   }
/*   24:     */   
/*   25:     */   private static double min(double a, double b, double c, double d)
/*   26:     */   {
/*   27:  66 */     return Math.min(Math.min(a, b), Math.min(c, d));
/*   28:     */   }
/*   29:     */   
/*   30:     */   private static double max(double a, double b, double c, double d)
/*   31:     */   {
/*   32:  81 */     return Math.max(Math.max(a, b), Math.max(c, d));
/*   33:     */   }
/*   34:     */   
/*   35:     */   public static boolean isPointNearOfLineSegment(int x0, int y0, int x1, int y1, int x, int y)
/*   36:     */   {
/*   37:  97 */     return (x >= x0) && (x < x1) && (y >= y0) && (y < y1);
/*   38:     */   }
/*   39:     */   
/*   40:     */   public static boolean isPointOnLineSegment(double x0, double y0, double x1, double y1, double x, double y)
/*   41:     */   {
/*   42: 112 */     if (equals(x0, x1)) {
/*   43: 113 */       return (equals(x, x0)) && (isBetween(y0, y1, y));
/*   44:     */     }
/*   45: 115 */     if (equals(y0, y1)) {
/*   46: 116 */       return (equals(y, y0)) && (isBetween(x0, x1, x));
/*   47:     */     }
/*   48: 118 */     double t = (x - x1) / (x0 - x1);
/*   49: 119 */     return (equals(t, (y - y1) / (y0 - y1))) && (isBetween(0.0D, 1.0D, t));
/*   50:     */   }
/*   51:     */   
/*   52:     */   public static boolean isPointInsidePolygon(double[] x, double[] y, double pointX, double pointY)
/*   53:     */   {
/*   54: 132 */     boolean isInside = false;
/*   55: 133 */     int nPoints = x.length;
/*   56:     */     
/*   57: 135 */     int j = 0;
/*   58: 136 */     for (int i = 0; i < nPoints; i++)
/*   59:     */     {
/*   60: 137 */       j++;
/*   61: 138 */       if (j == nPoints) {
/*   62: 138 */         j = 0;
/*   63:     */       }
/*   64: 140 */       if (((y[i] < pointY) && (y[j] >= pointY)) || ((y[j] < pointY) && (y[i] >= pointY) && 
/*   65: 141 */         (x[i] + (pointY - y[i]) / (y[j] - y[i]) * (x[j] - x[i]) < pointX))) {
/*   66: 142 */         isInside = !isInside;
/*   67:     */       }
/*   68:     */     }
/*   69: 147 */     return isInside;
/*   70:     */   }
/*   71:     */   
/*   72:     */   public static boolean isPointInsidePolygon(int[] x, int[] y, int pointX, int pointY)
/*   73:     */   {
/*   74: 161 */     boolean isInside = false;
/*   75: 162 */     int nPoints = x.length;
/*   76:     */     
/*   77: 164 */     int j = 0;
/*   78: 165 */     for (int i = 0; i < nPoints; i++)
/*   79:     */     {
/*   80: 166 */       j++;
/*   81: 167 */       if (j == nPoints) {
/*   82: 167 */         j = 0;
/*   83:     */       }
/*   84: 169 */       if (((y[i] < pointY) && (y[j] >= pointY)) || ((y[j] < pointY) && (y[i] >= pointY) && 
/*   85: 170 */         (x[i] + (pointY - y[i]) / (y[j] - y[i]) * (x[j] - x[i]) < pointX))) {
/*   86: 172 */         isInside = !isInside;
/*   87:     */       }
/*   88:     */     }
/*   89: 177 */     return isInside;
/*   90:     */   }
/*   91:     */   
/*   92:     */   public static double[] computePointOnLine(double[] p0, double[] p1, double fractionFromP0)
/*   93:     */   {
/*   94: 196 */     double[] p = new double[3];
/*   95:     */     
/*   96: 198 */     p0[0] += fractionFromP0 * (p1[0] - p0[0]);
/*   97: 199 */     p0[1] += fractionFromP0 * (p1[1] - p0[1]);
/*   98: 200 */     p0[2] += fractionFromP0 * (p1[2] - p0[2]);
/*   99:     */     
/*  100: 202 */     return p;
/*  101:     */   }
/*  102:     */   
/*  103:     */   public static double[] computePointOnLine(double x0, double y0, double x1, double y1, double fractionFrom0)
/*  104:     */   {
/*  105: 219 */     double[] p0 = { x0, y0, 0.0D };
/*  106: 220 */     double[] p1 = { x1, y1, 0.0D };
/*  107:     */     
/*  108: 222 */     double[] p = computePointOnLine(p0, p1, fractionFrom0);
/*  109:     */     
/*  110: 224 */     double[] r = { p[0], p[1] };
/*  111: 225 */     return r;
/*  112:     */   }
/*  113:     */   
/*  114:     */   public static void extendLine(double[] p0, double[] p1, double toLength, double anchor)
/*  115:     */   {
/*  116: 243 */     double[] p = computePointOnLine(p0, p1, anchor);
/*  117:     */     
/*  118: 245 */     double length0 = toLength * anchor;
/*  119: 246 */     double length1 = toLength * (1.0D - anchor);
/*  120:     */     
/*  121: 248 */     extendLine(p, p0, length0);
/*  122: 249 */     extendLine(p, p1, length1);
/*  123:     */   }
/*  124:     */   
/*  125:     */   public static void extendLine(double[] p0, double[] p1, double toLength)
/*  126:     */   {
/*  127: 262 */     double oldLength = length(p0, p1);
/*  128: 263 */     double lengthFraction = oldLength != 0.0D ? toLength / oldLength : 0.0D;
/*  129:     */     
/*  130: 265 */     p0[0] += (p1[0] - p0[0]) * lengthFraction;
/*  131: 266 */     p0[1] += (p1[1] - p0[1]) * lengthFraction;
/*  132: 267 */     p0[2] += (p1[2] - p0[2]) * lengthFraction;
/*  133:     */   }
/*  134:     */   
/*  135:     */   public static double length(double[] v)
/*  136:     */   {
/*  137: 279 */     return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
/*  138:     */   }
/*  139:     */   
/*  140:     */   public static double length(double[] p0, double[] p1)
/*  141:     */   {
/*  142: 291 */     double[] v = createVector(p0, p1);
/*  143: 292 */     return length(v);
/*  144:     */   }
/*  145:     */   
/*  146:     */   public static double length(int x0, int y0, int x1, int y1)
/*  147:     */   {
/*  148: 305 */     return length(x0, y0, x1, y1);
/*  149:     */   }
/*  150:     */   
/*  151:     */   public static double length(double x0, double y0, double x1, double y1)
/*  152:     */   {
/*  153: 319 */     double dx = x1 - x0;
/*  154: 320 */     double dy = y1 - y0;
/*  155:     */     
/*  156: 322 */     return Math.sqrt(dx * dx + dy * dy);
/*  157:     */   }
/*  158:     */   
/*  159:     */   public static double length(int[] x, int[] y, boolean isClosed)
/*  160:     */   {
/*  161: 336 */     double length = 0.0D;
/*  162:     */     
/*  163: 338 */     int nPoints = x.length;
/*  164: 339 */     for (int i = 0; i < nPoints - 1; i++) {
/*  165: 340 */       length += length(x[i], y[i], x[(i + 1)], y[(i + 1)]);
/*  166:     */     }
/*  167: 343 */     if ((isClosed) && (nPoints > 1)) {
/*  168: 344 */       length += length(x[(nPoints - 1)], y[(nPoints - 1)], x[0], y[0]);
/*  169:     */     }
/*  170: 346 */     return length;
/*  171:     */   }
/*  172:     */   
/*  173:     */   public static double distance(int x0, int y0, int x1, int y1, int x, int y)
/*  174:     */   {
/*  175: 366 */     double length = length(x0, y0, x1, y1);
/*  176: 367 */     if (length == 0.0D) {
/*  177: 368 */       return length(x0, y0, x, y);
/*  178:     */     }
/*  179: 371 */     double u = ((x - x0) * (x1 - x0) + (y - y0) * (y1 - y0)) / (length * length);
/*  180:     */     
/*  181:     */ 
/*  182:     */ 
/*  183:     */ 
/*  184: 376 */     double xp = x0 + u * (x1 - x0);
/*  185: 377 */     double yp = y0 + u * (y1 - y0);
/*  186:     */     
/*  187: 379 */     length = length(xp, yp, x, y);
/*  188: 380 */     return length;
/*  189:     */   }
/*  190:     */   
/*  191:     */   public static double computeAngle(double[] p0, double[] p1, double[] p2)
/*  192:     */   {
/*  193: 392 */     double[] v0 = createVector(p0, p1);
/*  194: 393 */     double[] v1 = createVector(p0, p2);
/*  195:     */     
/*  196: 395 */     double dotProduct = computeDotProduct(v0, v1);
/*  197:     */     
/*  198: 397 */     double length1 = length(v0);
/*  199: 398 */     double length2 = length(v1);
/*  200:     */     
/*  201: 400 */     double denominator = length1 * length2;
/*  202:     */     
/*  203: 402 */     double product = denominator != 0.0D ? dotProduct / denominator : 0.0D;
/*  204:     */     
/*  205: 404 */     double angle = Math.acos(product);
/*  206:     */     
/*  207: 406 */     return angle;
/*  208:     */   }
/*  209:     */   
/*  210:     */   public static double computeDotProduct(double[] v0, double[] v1)
/*  211:     */   {
/*  212: 418 */     return v0[0] * v1[0] + v0[1] * v1[1] + v0[2] * v1[2];
/*  213:     */   }
/*  214:     */   
/*  215:     */   public static double[] computeCrossProduct(double[] v0, double[] v1)
/*  216:     */   {
/*  217: 430 */     double[] crossProduct = new double[3];
/*  218:     */     
/*  219: 432 */     crossProduct[0] = (v0[1] * v1[2] - v0[2] * v1[1]);
/*  220: 433 */     crossProduct[1] = (v0[2] * v1[0] - v0[0] * v1[2]);
/*  221: 434 */     crossProduct[2] = (v0[0] * v1[1] - v0[1] * v1[0]);
/*  222:     */     
/*  223: 436 */     return crossProduct;
/*  224:     */   }
/*  225:     */   
/*  226:     */   public static double[] createVector(double[] p0, double[] p1)
/*  227:     */   {
/*  228: 448 */     double[] v = { p1[0] - p0[0], p1[1] - p0[1], p1[2] - p0[2] };
/*  229: 449 */     return v;
/*  230:     */   }
/*  231:     */   
/*  232:     */   private static int sameSide(double x0, double y0, double x1, double y1, double px0, double py0, double px1, double py1)
/*  233:     */   {
/*  234: 467 */     int sameSide = 0;
/*  235:     */     
/*  236: 469 */     double dx = x1 - x0;
/*  237: 470 */     double dy = y1 - y0;
/*  238: 471 */     double dx1 = px0 - x0;
/*  239: 472 */     double dy1 = py0 - y0;
/*  240: 473 */     double dx2 = px1 - x1;
/*  241: 474 */     double dy2 = py1 - y1;
/*  242:     */     
/*  243:     */ 
/*  244: 477 */     double c1 = dx * dy1 - dy * dx1;
/*  245: 478 */     double c2 = dx * dy2 - dy * dx2;
/*  246: 480 */     if ((c1 != 0.0D) && (c2 != 0.0D)) {
/*  247: 481 */       sameSide = (c1 < 0.0D ? 1 : 0) != (c2 < 0.0D ? 1 : 0) ? -1 : 1;
/*  248: 482 */     } else if ((dx == 0.0D) && (dx1 == 0.0D) && (dx2 == 0.0D)) {
/*  249: 483 */       sameSide = (!isBetween(y0, y1, py0)) && (!isBetween(y0, y1, py1)) ? 1 : 0;
/*  250: 484 */     } else if ((dy == 0.0D) && (dy1 == 0.0D) && (dy2 == 0.0D)) {
/*  251: 485 */       sameSide = (!isBetween(x0, x1, px0)) && (!isBetween(x0, x1, px1)) ? 1 : 0;
/*  252:     */     }
/*  253: 487 */     return sameSide;
/*  254:     */   }
/*  255:     */   
/*  256:     */   private static int sameSide(int x0, int y0, int x1, int y1, int px0, int py0, int px1, int py1)
/*  257:     */   {
/*  258: 504 */     return sameSide(x0, y0, x1, y1, px0, py0, px1, py1);
/*  259:     */   }
/*  260:     */   
/*  261:     */   public static boolean isLineIntersectingLine(int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3)
/*  262:     */   {
/*  263: 519 */     int s1 = sameSide(x0, y0, x1, y1, x2, y2, x3, y3);
/*  264: 520 */     int s2 = sameSide(x2, y2, x3, y3, x0, y0, x1, y1);
/*  265:     */     
/*  266: 522 */     return (s1 <= 0) && (s2 <= 0);
/*  267:     */   }
/*  268:     */   
/*  269:     */   public static boolean isLineIntersectingRectangle(int lx0, int ly0, int lx1, int ly1, int x0, int y0, int x1, int y1)
/*  270:     */   {
/*  271: 543 */     if ((isPointNearOfLineSegment(x0, y0, x1, y1, lx0, ly0)) || (isPointNearOfLineSegment(x0, y0, x1, y1, lx1, ly1))) {
/*  272: 545 */       return true;
/*  273:     */     }
/*  274: 550 */     if (isLineIntersectingLine(lx0, ly0, lx1, ly1, x0, y0, x1, y0)) {
/*  275: 552 */       return true;
/*  276:     */     }
/*  277: 555 */     if (isLineIntersectingLine(lx0, ly0, lx1, ly1, x0, y0, x0, y1)) {
/*  278: 557 */       return true;
/*  279:     */     }
/*  280: 560 */     if (isLineIntersectingLine(lx0, ly0, lx1, ly1, x0, y1, x1, y1)) {
/*  281: 562 */       return true;
/*  282:     */     }
/*  283: 564 */     return false;
/*  284:     */   }
/*  285:     */   
/*  286:     */   public static boolean isPolylineIntersectingRectangle(int[] x, int[] y, int x0, int y0, int x1, int y1)
/*  287:     */   {
/*  288: 582 */     if (x.length == 0) {
/*  289: 582 */       return false;
/*  290:     */     }
/*  291: 584 */     if (isPointNearOfLineSegment(x[0], y[0], x0, y0, x1, y1)) {
/*  292: 585 */       return true;
/*  293:     */     }
/*  294: 587 */     if (x.length == 1) {
/*  295: 588 */       return false;
/*  296:     */     }
/*  297: 590 */     for (int i = 1; i < x.length; i++) {
/*  298: 591 */       if (((x[(i - 1)] != x[i]) || (y[(i - 1)] != y[i])) && 
/*  299: 592 */         (isLineIntersectingRectangle(x[(i - 1)], y[(i - 1)], x[i], y[i], x0, y0, x1, y1))) {
/*  300: 595 */         return true;
/*  301:     */       }
/*  302:     */     }
/*  303: 598 */     return false;
/*  304:     */   }
/*  305:     */   
/*  306:     */   public static boolean isPolygonIntersectingRectangle(int[] x, int[] y, int x0, int y0, int x1, int y1)
/*  307:     */   {
/*  308: 618 */     int n = x.length;
/*  309: 620 */     if (n == 0) {
/*  310: 621 */       return false;
/*  311:     */     }
/*  312: 623 */     if (n == 1) {
/*  313: 624 */       return isPointNearOfLineSegment(x0, y0, x1, y1, x[0], y[0]);
/*  314:     */     }
/*  315: 630 */     if (isPolylineIntersectingRectangle(x, y, x0, y0, x1, y1)) {
/*  316: 631 */       return true;
/*  317:     */     }
/*  318: 634 */     if (isLineIntersectingRectangle(x[(n - 2)], y[(n - 2)], x[(n - 1)], y[(n - 1)], x0, y0, x1, y1)) {
/*  319: 636 */       return true;
/*  320:     */     }
/*  321: 642 */     if ((isPointInsidePolygon(x, y, x0, y0)) || (isPointNearOfLineSegment(x0, y0, x1, y1, x[0], y[0]))) {
/*  322: 644 */       return true;
/*  323:     */     }
/*  324: 647 */     return false;
/*  325:     */   }
/*  326:     */   
/*  327:     */   public static double computePolygonArea(double[] x, double[] y)
/*  328:     */   {
/*  329: 660 */     int n = x.length;
/*  330:     */     
/*  331: 662 */     double area = 0.0D;
/*  332: 663 */     for (int i = 0; i < n - 1; i++) {
/*  333: 664 */       area += x[i] * y[(i + 1)] - x[(i + 1)] * y[i];
/*  334:     */     }
/*  335: 665 */     area += x[(n - 1)] * y[0] - x[0] * y[(n - 1)];
/*  336:     */     
/*  337: 667 */     area *= 0.5D;
/*  338:     */     
/*  339: 669 */     return area;
/*  340:     */   }
/*  341:     */   
/*  342:     */   public static double computePolygonArea(double[] xy)
/*  343:     */   {
/*  344: 681 */     int n = xy.length;
/*  345:     */     
/*  346: 683 */     double area = 0.0D;
/*  347: 684 */     for (int i = 0; i < n - 2; i += 2) {
/*  348: 685 */       area += xy[i] * xy[(i + 3)] - xy[(i + 2)] * xy[(i + 1)];
/*  349:     */     }
/*  350: 686 */     area += xy[(xy.length - 2)] * xy[1] - xy[0] * xy[(xy.length - 1)];
/*  351:     */     
/*  352: 688 */     area *= 0.5D;
/*  353:     */     
/*  354: 690 */     return area;
/*  355:     */   }
/*  356:     */   
/*  357:     */   public static double[] computePolygonCentroid(double[] x, double[] y)
/*  358:     */   {
/*  359: 703 */     double cx = 0.0D;
/*  360: 704 */     double cy = 0.0D;
/*  361:     */     
/*  362: 706 */     int n = x.length;
/*  363: 707 */     for (int i = 0; i < n - 1; i++)
/*  364:     */     {
/*  365: 708 */       double a = x[i] * y[(i + 1)] - x[(i + 1)] * y[i];
/*  366: 709 */       cx += (x[i] + x[(i + 1)]) * a;
/*  367: 710 */       cy += (y[i] + y[(i + 1)]) * a;
/*  368:     */     }
/*  369: 712 */     double a = x[(n - 1)] * y[0] - x[0] * y[(n - 1)];
/*  370: 713 */     cx += (x[(n - 1)] + x[0]) * a;
/*  371: 714 */     cy += (y[(n - 1)] + y[0]) * a;
/*  372:     */     
/*  373: 716 */     double area = computePolygonArea(x, y);
/*  374:     */     
/*  375: 718 */     cx /= 6.0D * area;
/*  376: 719 */     cy /= 6.0D * area;
/*  377:     */     
/*  378: 721 */     return new double[] { cx, cy };
/*  379:     */   }
/*  380:     */   
/*  381:     */   public static void findPolygonExtent(double[] x, double[] y, double[] z, double[] xExtent, double[] yExtent, double[] zExtent)
/*  382:     */   {
/*  383: 741 */     double xMin = 1.7976931348623157E+308D;
/*  384: 742 */     double xMax = -1.7976931348623157E+308D;
/*  385:     */     
/*  386: 744 */     double yMin = 1.7976931348623157E+308D;
/*  387: 745 */     double yMax = -1.7976931348623157E+308D;
/*  388:     */     
/*  389: 747 */     double zMin = 1.7976931348623157E+308D;
/*  390: 748 */     double zMax = -1.7976931348623157E+308D;
/*  391: 750 */     for (int i = 0; i < x.length; i++)
/*  392:     */     {
/*  393: 751 */       if (x[i] < xMin) {
/*  394: 751 */         xMin = x[i];
/*  395:     */       }
/*  396: 752 */       if (x[i] > xMax) {
/*  397: 752 */         xMax = x[i];
/*  398:     */       }
/*  399: 754 */       if (y[i] < yMin) {
/*  400: 754 */         yMin = y[i];
/*  401:     */       }
/*  402: 755 */       if (y[i] > yMax) {
/*  403: 755 */         yMax = y[i];
/*  404:     */       }
/*  405: 757 */       if (z != null)
/*  406:     */       {
/*  407: 758 */         if (z[i] < zMin) {
/*  408: 758 */           zMin = z[i];
/*  409:     */         }
/*  410: 759 */         if (z[i] > zMax) {
/*  411: 759 */           zMax = z[i];
/*  412:     */         }
/*  413:     */       }
/*  414:     */     }
/*  415: 763 */     xExtent[0] = xMin;
/*  416: 764 */     xExtent[1] = xMax;
/*  417:     */     
/*  418: 766 */     yExtent[0] = yMin;
/*  419: 767 */     yExtent[1] = yMax;
/*  420: 769 */     if (z != null)
/*  421:     */     {
/*  422: 770 */       zExtent[0] = zMin;
/*  423: 771 */       zExtent[1] = zMax;
/*  424:     */     }
/*  425:     */   }
/*  426:     */   
/*  427:     */   public static void findPolygonExtent(int[] x, int[] y, int[] xExtent, int[] yExtent)
/*  428:     */   {
/*  429: 789 */     int xMin = 2147483647;
/*  430: 790 */     int xMax = -2147483647;
/*  431:     */     
/*  432: 792 */     int yMin = 2147483647;
/*  433: 793 */     int yMax = -2147483647;
/*  434: 795 */     for (int i = 0; i < x.length; i++)
/*  435:     */     {
/*  436: 796 */       if (x[i] < xMin) {
/*  437: 796 */         xMin = x[i];
/*  438:     */       }
/*  439: 797 */       if (x[i] > xMax) {
/*  440: 797 */         xMax = x[i];
/*  441:     */       }
/*  442: 799 */       if (y[i] < yMin) {
/*  443: 799 */         yMin = y[i];
/*  444:     */       }
/*  445: 800 */       if (y[i] > yMax) {
/*  446: 800 */         yMax = y[i];
/*  447:     */       }
/*  448:     */     }
/*  449: 803 */     xExtent[0] = xMin;
/*  450: 804 */     xExtent[1] = xMax;
/*  451:     */     
/*  452: 806 */     yExtent[0] = yMin;
/*  453: 807 */     yExtent[1] = yMax;
/*  454:     */   }
/*  455:     */   
/*  456:     */   public static int findLineSegmentIntersection(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3, double[] intersection)
/*  457:     */   {
/*  458: 836 */     double LIMIT = 1.E-005D;
/*  459: 837 */     double INFINITY = 10000000000.0D;
/*  460:     */     
/*  461:     */ 
/*  462:     */ 
/*  463:     */ 
/*  464:     */ 
/*  465:     */ 
/*  466:     */ 
/*  467:     */ 
/*  468: 846 */     double a0 = equals(x0, x1, 1.E-005D) ? 10000000000.0D : (y0 - y1) / (x0 - x1);
/*  469:     */     
/*  470: 848 */     double a1 = equals(x2, x3, 1.E-005D) ? 10000000000.0D : (y2 - y3) / (x2 - x3);
/*  471:     */     
/*  472:     */ 
/*  473: 851 */     double b0 = y0 - a0 * x0;
/*  474: 852 */     double b1 = y2 - a1 * x2;
/*  475: 855 */     if (equals(a0, a1))
/*  476:     */     {
/*  477: 856 */       if (!equals(b0, b1)) {
/*  478: 857 */         return -1;
/*  479:     */       }
/*  480: 860 */       if (equals(x0, x1))
/*  481:     */       {
/*  482:     */         double x;
/*  483: 861 */         if ((Math.min(y0, y1) < Math.max(y2, y3)) || (Math.max(y0, y1) > Math.min(y2, y3)))
/*  484:     */         {
/*  485: 863 */           double twoMiddle = y0 + y1 + y2 + y3 - min(y0, y1, y2, y3) - max(y0, y1, y2, y3);
/*  486:     */           
/*  487:     */ 
/*  488: 866 */           double y = twoMiddle / 2.0D;
/*  489: 867 */           x = (y - b0) / a0;
/*  490:     */         }
/*  491:     */         else
/*  492:     */         {
/*  493: 868 */           return -1;
/*  494:     */         }
/*  495:     */       }
/*  496:     */       else
/*  497:     */       {
/*  498:     */         double y;
/*  499: 870 */         if ((Math.min(x0, x1) < Math.max(x2, x3)) || (Math.max(x0, x1) > Math.min(x2, x3)))
/*  500:     */         {
/*  501: 872 */           double twoMiddle = x0 + x1 + x2 + x3 - min(x0, x1, x2, x3) - max(x0, x1, x2, x3);
/*  502:     */           
/*  503:     */ 
/*  504: 875 */           double x = twoMiddle / 2.0D;
/*  505: 876 */           y = a0 * x + b0;
/*  506:     */         }
/*  507:     */         else
/*  508:     */         {
/*  509: 877 */           return -1;
/*  510:     */         }
/*  511:     */       }
/*  512:     */       double y =0;
/*  513:     */       double x = 0;
/*  514: 880 */       intersection[0] = x;
/*  515: 881 */       intersection[1] = y;
/*  516: 882 */       return -2;
/*  517:     */     }
/*  518:     */     double y;
/*  519:     */     double x;
/*  520:     */     
/*  521: 887 */     if (equals(a0, 10000000000.0D))
/*  522:     */     {
/*  523: 888 */        x = x0;
/*  524: 889 */       y = a1 * x + b1;
/*  525:     */     }
/*  526:     */     else
/*  527:     */     {
/*  528:     */      
/*  529: 890 */       if (equals(a1, 10000000000.0D))
/*  530:     */       {
/*  531: 891 */          x = x2;
/*  532: 892 */         y = a0 * x + b0;
/*  533:     */       }
/*  534:     */       else
/*  535:     */       {
/*  536: 894 */         x = -(b0 - b1) / (a0 - a1);
/*  537: 895 */         y = a0 * x + b0;
/*  538:     */       }
/*  539:     */     }
/*  540: 898 */     intersection[0] = x;
/*  541: 899 */     intersection[1] = y;
/*  542:     */     double distanceFrom1;
/*  543:     */  
/*  544: 903 */     if (equals(x0, x1))
/*  545:     */     {
/*  546:     */        
/*  547: 904 */       if (y0 < y1) {
/*  548: 905 */         distanceFrom1 = y > y1 ? length(x, y, x1, y1) : y < y0 ? length(x, y, x0, y0) : 0.0D;
/*  549:     */       } else {
/*  550: 908 */         distanceFrom1 = y > y0 ? length(x, y, x0, y0) : y < y1 ? length(x, y, x1, y1) : 0.0D;
/*  551:     */       }
/*  552:     */     }
/*  553:     */     else
/*  554:     */     {
/*  555:     */     
/*  556: 911 */       if (x0 < x1) {
/*  557: 912 */         distanceFrom1 = x > x1 ? length(x, y, x1, y1) : x < x0 ? length(x, y, x0, y0) : 0.0D;
/*  558:     */       } else {
/*  559: 915 */         distanceFrom1 = x > x0 ? length(x, y, x0, y0) : x < x1 ? length(x, y, x1, y1) : 0.0D;
/*  560:     */       }
/*  561:     */     }
/*  562:     */     double distanceFrom2;
/*  563:     */     
/*  564: 920 */     if (equals(x2, x3))
/*  565:     */     {
/*  566:     */       
/*  567: 921 */       if (y2 < y3) {
/*  568: 922 */         distanceFrom2 = y > y3 ? length(x, y, x3, y3) : y < y2 ? length(x, y, x2, y2) : 0.0D;
/*  569:     */       } else {
/*  570: 925 */         distanceFrom2 = y > y2 ? length(x, y, x2, y2) : y < y3 ? length(x, y, x3, y3) : 0.0D;
/*  571:     */       }
/*  572:     */     }
/*  573:     */     else
/*  574:     */     {
/*  575:     */       
/*  576: 928 */       if (x2 < x3) {
/*  577: 929 */         distanceFrom2 = x > x3 ? length(x, y, x3, y3) : x < x2 ? length(x, y, x2, y2) : 0.0D;
/*  578:     */       } else {
/*  579: 932 */         distanceFrom2 = x > x2 ? length(x, y, x2, y2) : x < x3 ? length(x, y, x3, y3) : 0.0D;
/*  580:     */       }
/*  581:     */     }
/*  582: 936 */     return (equals(distanceFrom1, 0.0D)) && (equals(distanceFrom2, 0.0D)) ? 1 : 0;
/*  583:     */   }
/*  584:     */   
/*  585:     */   public static double[] findLineAndLineSegmentIntersection(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3)
/*  586:     */   {
/*  587: 949 */     double[] rez = new double[2];
/*  588: 950 */     int type = findLineSegmentIntersection(x0, y0, x1, y1, x2, y2, x3, y3, rez);
/*  589: 951 */     if (type == 1) {
/*  590: 952 */       return rez;
/*  591:     */     }
/*  592: 953 */     if ((type == 0) && 
/*  593: 954 */       (isPointOnLineSegment(x2, y2, x3, y3, rez[0], rez[1]))) {
/*  594: 955 */       return rez;
/*  595:     */     }
/*  596: 957 */     return null;
/*  597:     */   }
/*  598:     */   
/*  599:     */   public static double[] findLinePolygonIntersections(double[] x, double[] y, double x0, double y0, double x1, double y1)
/*  600:     */   {
/*  601: 981 */     int nPoints = x.length;
/*  602:     */     
/*  603: 983 */     int nIntersections = 0;
/*  604: 984 */     double[] intersections = new double[24];
/*  605: 985 */     double[] intersection = new double[2];
/*  606: 987 */     for (int i = 0; i < nPoints; i++)
/*  607:     */     {
/*  608: 988 */       int next = i == nPoints - 1 ? 0 : i + 1;
/*  609:     */       
/*  610:     */ 
/*  611: 991 */       double x2 = x[i];
/*  612: 992 */       double y2 = y[i];
/*  613: 993 */       double x3 = x[next];
/*  614: 994 */       double y3 = y[next];
/*  615:     */       
/*  616: 996 */       boolean isIntersecting = false;
/*  617: 999 */       if ((!equals(x2, x3)) || (!equals(y2, y3)))
/*  618:     */       {
/*  619:1002 */         int type = findLineSegmentIntersection(x0, y0, x1, y1, x2, y2, x3, y3, intersection);
/*  620:1006 */         if (type == -2)
/*  621:     */         {
/*  622:1007 */           int p1 = i == 0 ? nPoints - 1 : i - 1;
/*  623:1008 */           int p2 = next == nPoints - 1 ? 0 : next + 1;
/*  624:     */           
/*  625:1010 */           int side = sameSide(x0, y0, x1, y1, x[p1], y[p1], x[p2], y[p2]);
/*  626:1013 */           if (side < 0) {
/*  627:1014 */             isIntersecting = true;
/*  628:     */           }
/*  629:     */         }
/*  630:1015 */         else if (type == 1)
/*  631:     */         {
/*  632:1016 */           isIntersecting = true;
/*  633:     */         }
/*  634:1019 */         if (isIntersecting)
/*  635:     */         {
/*  636:1022 */           if (nIntersections << 1 == intersections.length)
/*  637:     */           {
/*  638:1023 */             double[] newArray = new double[nIntersections << 2];
/*  639:1024 */             System.arraycopy(intersections, 0, newArray, 0, intersections.length);
/*  640:     */             
/*  641:1026 */             intersections = newArray;
/*  642:     */           }
/*  643:1030 */           intersections[(nIntersections << 1)] = intersection[0];
/*  644:1031 */           intersections[(nIntersections << 2)] = intersection[1];
/*  645:     */           
/*  646:1033 */           nIntersections++;
/*  647:     */         }
/*  648:     */       }
/*  649:     */     }
/*  650:1037 */     if (nIntersections == 0) {
/*  651:1037 */       return null;
/*  652:     */     }
/*  653:1040 */     double[] finalArray = new double[nIntersections << 2];
/*  654:1041 */     System.arraycopy(intersections, 0, finalArray, 0, finalArray.length);
/*  655:     */     
/*  656:1043 */     return finalArray;
/*  657:     */   }
/*  658:     */   
/*  659:     */   public static final boolean isPointNearToLine(double x1, double y1, double x2, double y2, double x0, double y0, double length)
/*  660:     */   {
/*  661:1052 */     double a = y2 - y1;
/*  662:1053 */     double b = -(x2 - x1);
/*  663:1054 */     double c = y1 * x2 - x1 * y2;
/*  664:     */     
/*  665:1056 */     double f = Math.abs(a * x0 + b * y0 + c) / Math.sqrt(a * a + b * b);
/*  666:1058 */     if (f <= length) {
/*  667:1059 */       return true;
/*  668:     */     }
/*  669:1061 */     return false;
/*  670:     */   }
/*  671:     */   
/*  672:     */   public static final boolean isPointNearToLineSegment(double x1, double y1, double x2, double y2, double x0, double y0, double length)
/*  673:     */   {
/*  674:1070 */     double a = y2 - y1;
/*  675:1071 */     double b = -(x2 - x1);
/*  676:1072 */     double c = y1 * x2 - x1 * y2;
/*  677:     */     
/*  678:1074 */     double f = Math.abs(a * x0 + b * y0 + c) / Math.sqrt(a * a + b * b);
/*  679:1076 */     if (f <= length)
/*  680:     */     {
/*  681:1077 */       double t = -(c + y0 * b + x0 * a) / (a * a + b * b);
/*  682:1078 */       double xp = x0 + a * t;
/*  683:1079 */       double yp = y0 + b * t;
/*  684:     */       
/*  685:1081 */       double xmin = Math.min(x1, x2);
/*  686:1082 */       double xmax = Math.max(x1, x2);
/*  687:     */       
/*  688:1084 */       double ymin = Math.min(y1, y2);
/*  689:1085 */       double ymax = Math.max(y1, y2);
/*  690:1087 */       if ((xp >= xmin) && (xp <= xmax) && (yp >= ymin) && (yp <= ymax)) {
/*  691:1088 */         return true;
/*  692:     */       }
/*  693:1090 */       return false;
/*  694:     */     }
/*  695:1092 */     return false;
/*  696:     */   }
/*  697:     */   
/*  698:     */   public static final double fingAngele(double x1, double y1, double x2, double y2)
/*  699:     */   {
/*  700:1097 */     double v1x = x1 - x2;
/*  701:1098 */     double v1y = y1 - y2;
/*  702:1099 */     double v1d = Math.sqrt(v1x * v1x + v1y * v1y);
/*  703:     */     
/*  704:1101 */     double v2x = -1.0D;
/*  705:1102 */     double v2y = 0.0D;
/*  706:1103 */     double v2d = Math.sqrt(v2x * v2x + v2y * v2y);
/*  707:     */     
/*  708:1105 */     double scal = v1x * v2x + v1y * v2y;
/*  709:     */     double cosA;
/*  710:     */  
/*  711:1109 */     if (v1d * v2d == 0.0D) {
/*  712:1110 */       cosA = 0.0D;
/*  713:     */     } else {
/*  714:1112 */       cosA = scal / (v1d * v2d);
/*  715:     */     }
/*  716:1114 */     double A = Math.acos(cosA);
/*  717:1116 */     if (y2 < y1) {
/*  718:1117 */       A = -A;
/*  719:     */     }
/*  720:1119 */     return A;
/*  721:     */   }
/*  722:     */   
/*  723:     */   public static boolean findPolygonPosition(int[] x, int[] y, double length, int[] position)
/*  724:     */   {
/*  725:1137 */     if (length < 0.0D) {
/*  726:1137 */       return false;
/*  727:     */     }
/*  728:1139 */     double accumulatedLength = 0.0D;
/*  729:1140 */     for (int i = 1; i < x.length; i++)
/*  730:     */     {
/*  731:1141 */       double legLength = length(x[(i - 1)], y[(i - 1)], x[i], y[i]);
/*  732:1142 */       if (legLength + accumulatedLength >= length)
/*  733:     */       {
/*  734:1143 */         double part = length - accumulatedLength;
/*  735:1144 */         double fraction = part / legLength;
/*  736:1145 */         position[0] = ((int)Math.round(x[(i - 1)] + fraction * (x[i] - x[(i - 1)])));
/*  737:1146 */         position[1] = ((int)Math.round(y[(i - 1)] + fraction * (y[i] - y[(i - 1)])));
/*  738:1147 */         return true;
/*  739:     */       }
/*  740:1150 */       accumulatedLength += legLength;
/*  741:     */     }
/*  742:1154 */     return false;
/*  743:     */   }
/*  744:     */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.GeometryUtils
 * JD-Core Version:    0.7.0.1
 */