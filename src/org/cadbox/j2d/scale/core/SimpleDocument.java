/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.Collection;
/*   5:    */ import java.util.List;
/*   6:    */ 
/*   7:    */ public class SimpleDocument
/*   8:    */   implements BasicDocument
/*   9:    */ {
/*  10: 41 */   private boolean modified = false;
/*  11: 42 */   private boolean saved = false;
/*  12: 44 */   private Layer currentLayer = null;
/*  13: 45 */   private List layerList = new ArrayList();
/*  14: 47 */   private Layout currentLayout = null;
/*  15:    */   private Context context;
/*  16:    */   private DrawingContext drawingContext;
/*  17:    */   private PropertyContext cadPropertyContext;
/*  18:    */   
/*  19:    */   public SimpleDocument(Layout layout)
/*  20:    */   {
/*  21: 57 */     if (layout == null) {
/*  22: 58 */       throw new IllegalArgumentException("Layout could not be null.");
/*  23:    */     }
/*  24: 60 */     setLayout(layout);
/*  25:    */     
/*  26:    */ 
/*  27:    */ 
/*  28: 64 */     this.cadPropertyContext = new PropertyContext();
/*  29:    */     
/*  30: 66 */     this.drawingContext = new DrawingContext();
/*  31: 67 */     this.drawingContext.setBounds(0, 0, 10, 10);
/*  32: 68 */     this.drawingContext.setLogicalBounds(layout.getBounds());
/*  33:    */     
/*  34: 70 */     this.context = new SimpleContext(this.drawingContext, this.cadPropertyContext);
/*  35:    */     
/*  36:    */ 
/*  37:    */ 
/*  38: 74 */     Layer layer = new Layer(this);
/*  39: 75 */     layer.setName("Layer");
/*  40: 76 */     addLayer(layer);
/*  41: 77 */     setCurrentLayer(layer);
/*  42:    */     
/*  43:    */ 
/*  44:    */ 
/*  45: 81 */     setModified(false);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public Context getContext()
/*  49:    */   {
/*  50: 85 */     return this.context;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void setLayout(Layout layout)
/*  54:    */   {
/*  55: 89 */     this.currentLayout = layout;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public Layout getLayout()
/*  59:    */   {
/*  60: 93 */     return this.currentLayout;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void addLayer(Layer layer)
/*  64:    */   {
/*  65: 98 */     if (layer != null)
/*  66:    */     {
/*  67: 99 */       this.layerList.add(layer);
/*  68:    */       
/*  69:    */ 
/*  70:102 */       setModified(true);
/*  71:    */     }
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void removeLayer(Layer layer)
/*  75:    */   {
/*  76:107 */     if (this.layerList.size() > 1)
/*  77:    */     {
/*  78:109 */       int index = this.layerList.indexOf(layer);
/*  79:    */       
/*  80:111 */       this.layerList.remove(layer);
/*  81:112 */       if (this.currentLayer == layer) {
/*  82:114 */         if (index >= this.layerList.size()) {
/*  83:115 */           this.currentLayer = ((Layer)this.layerList.get(index - 1));
/*  84:    */         } else {
/*  85:117 */           this.currentLayer = ((Layer)this.layerList.get(index));
/*  86:    */         }
/*  87:    */       }
/*  88:121 */       setModified(true);
/*  89:    */     }
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Collection getLayers()
/*  93:    */   {
/*  94:126 */     return this.layerList;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public int getLayerCount()
/*  98:    */   {
/*  99:130 */     return this.layerList.size();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void setCurrentLayer(Layer currentLayer)
/* 103:    */   {
/* 104:134 */     this.currentLayer = currentLayer;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public Layer getCurrentLayer()
/* 108:    */   {
/* 109:138 */     return this.currentLayer;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public boolean isModified()
/* 113:    */   {
/* 114:142 */     return this.modified;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setModified(boolean modified)
/* 118:    */   {
/* 119:146 */     this.modified = modified;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public boolean isSaved()
/* 123:    */   {
/* 124:150 */     return this.saved;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setSaved(boolean saved)
/* 128:    */   {
/* 129:154 */     this.saved = saved;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void open(String file) {}
/* 133:    */   
/* 134:    */   public void save(String file) {}
/* 135:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.SimpleDocument
 * JD-Core Version:    0.7.0.1
 */