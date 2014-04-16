/*   1:    */ package org.cadbox.j2d;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collection;
/*   5:    */ import java.util.List;
/*   6:    */ import org.cadbox.j2d.scale.core.BasicDocument;
/*   7:    */ import org.cadbox.j2d.scale.core.Context;
/*   8:    */ import org.cadbox.j2d.scale.core.DrawingContext;
/*   9:    */ import org.cadbox.j2d.scale.core.Layer;
/*  10:    */ import org.cadbox.j2d.scale.core.Layout;
/*  11:    */ import org.cadbox.j2d.scale.core.PropertyContext;
/*  12:    */ import org.cadbox.j2d.scale.core.SimpleContext;
/*  13:    */ 
/*  14:    */ public class CadDocument
/*  15:    */   implements BasicDocument
/*  16:    */ {
/*  17:    */   public static final int DOCUMENT_TYPE_2CADD = 0;
/*  18:    */   public static final int DOCUMENT_TYPE_EARTH = 1;
/*  19:    */   public static final int DOCUMENT_TYPE_TEST = 2;
/*  20: 52 */   CadDocumentInfo docInfo = new CadDocumentInfo();
/*  21: 54 */   boolean modified = false;
/*  22: 55 */   boolean saved = false;
/*  23: 57 */   Layer currentLayer = null;
/*  24: 59 */   Layout currentLayout = null;
/*  25: 60 */   List layerList = new ArrayList();
/*  26:    */   Context context;
/*  27:    */   DrawingContext drawingContext;
/*  28:    */   PropertyContext cadPropertyContext;
/*  29:    */   
/*  30:    */   public CadDocument(Layout layout)
/*  31:    */   {
/*  32: 69 */     if (layout == null) {
/*  33: 70 */       throw new IllegalArgumentException("Layout could not be null.");
/*  34:    */     }
/*  35: 72 */     setLayout(layout);
/*  36:    */     
/*  37: 74 */     this.cadPropertyContext = new PropertyContext();
/*  38:    */     
/*  39:    */ 
/*  40: 77 */     this.drawingContext = new DrawingContext();
/*  41:    */     
/*  42: 79 */     this.drawingContext.setBounds(0, 0, 10, 10);
/*  43: 80 */     this.drawingContext.setLogicalBounds(layout.getBounds());
/*  44:    */     
/*  45: 82 */     this.context = new SimpleContext(this.drawingContext, this.cadPropertyContext);
/*  46:    */     
/*  47:    */ 
/*  48:    */ 
/*  49:    */ 
/*  50:    */ 
/*  51:    */ 
/*  52:    */ 
/*  53:    */ 
/*  54: 91 */     Layer layer = new Layer(this);
/*  55: 92 */     layer.setName("Layer");
/*  56: 93 */     addLayer(layer);
/*  57:    */     
/*  58: 95 */     setCurrentLayer(layer);
/*  59:    */     
/*  60:    */ 
/*  61:    */ 
/*  62:    */ 
/*  63:    */ 
/*  64:    */ 
/*  65:    */ 
/*  66:    */ 
/*  67:    */ 
/*  68:    */ 
/*  69:    */ 
/*  70:    */ 
/*  71:    */ 
/*  72:    */ 
/*  73:    */ 
/*  74:    */ 
/*  75:    */ 
/*  76:    */ 
/*  77:    */ 
/*  78:    */ 
/*  79:    */ 
/*  80:    */ 
/*  81:    */ 
/*  82:    */ 
/*  83:    */ 
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87:124 */     setModified(false);
/*  88:    */   }
/*  89:    */   
/*  90:    */   public Context getContext()
/*  91:    */   {
/*  92:128 */     return this.context;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setLayout(Layout layout)
/*  96:    */   {
/*  97:132 */     this.currentLayout = layout;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public Layout getLayout()
/* 101:    */   {
/* 102:136 */     return this.currentLayout;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void addLayer(Layer layer)
/* 106:    */   {
/* 107:140 */     if (layer != null)
/* 108:    */     {
/* 109:141 */       this.layerList.add(layer);
/* 110:    */       
/* 111:    */ 
/* 112:144 */       setModified(true);
/* 113:    */     }
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void removeLayer(Layer layer)
/* 117:    */   {
/* 118:149 */     if (this.layerList.size() > 1)
/* 119:    */     {
/* 120:151 */       int index = this.layerList.indexOf(layer);
/* 121:    */       
/* 122:153 */       this.layerList.remove(layer);
/* 123:154 */       if (this.currentLayer == layer) {
/* 124:156 */         if (index >= this.layerList.size()) {
/* 125:157 */           this.currentLayer = ((Layer)this.layerList.get(index - 1));
/* 126:    */         } else {
/* 127:159 */           this.currentLayer = ((Layer)this.layerList.get(index));
/* 128:    */         }
/* 129:    */       }
/* 130:163 */       setModified(true);
/* 131:    */     }
/* 132:    */   }
/* 133:    */   
/* 134:    */   public Collection getLayers()
/* 135:    */   {
/* 136:168 */     return this.layerList;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public int getLayerCount()
/* 140:    */   {
/* 141:172 */     return this.layerList.size();
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setCurrentLayer(Layer currentLayer)
/* 145:    */   {
/* 146:176 */     this.currentLayer = currentLayer;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public Layer getCurrentLayer()
/* 150:    */   {
/* 151:180 */     return this.currentLayer;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public CadDocumentInfo getDocumentInfo()
/* 155:    */   {
/* 156:184 */     return this.docInfo;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public boolean isModified()
/* 160:    */   {
/* 161:188 */     return this.modified;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setModified(boolean modified)
/* 165:    */   {
/* 166:192 */     this.modified = modified;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public boolean isSaved()
/* 170:    */   {
/* 171:196 */     return this.saved;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void setSaved(boolean saved)
/* 175:    */   {
/* 176:200 */     this.saved = saved;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void open(String file) {}
/* 180:    */   
/* 181:    */   public void save(String file) {}
/* 182:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.CadDocument
 * JD-Core Version:    0.7.0.1
 */