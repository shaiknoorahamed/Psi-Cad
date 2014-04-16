/*   1:    */ package org.cadbox.j2d.scale.core;
/*   2:    */ 
/*   3:    */ import java.util.Collection;
/*   4:    */ import java.util.Vector;
/*   5:    */ import org.cadbox.ScreenManager;
/*   6:    */ import org.cadbox.j2d.scale.core.unit.MmUnit;
/*   7:    */ import org.cadbox.j2d.scale.core.unit.UnitTranslator;
/*   8:    */ 
/*   9:    */ public class LineWeightManager
/*  10:    */ {
/*  11: 45 */   public static int MILIMETOR_UNITTYPE = 0;
/*  12: 46 */   public static int INCH_UNITTYPE = 1;
/*  13: 47 */   public static int PIXEL_UNITTYPE = 2;
/*  14: 49 */   private static LineWeightManager instance = new LineWeightManager();
/*  15:    */   private Vector etalon;
/*  16:    */   private Vector vector;
/*  17: 54 */   private int unitType = MILIMETOR_UNITTYPE;
/*  18: 56 */   private LineWeight defaulLineWeight = new LineWeight(new MmUnit(0.0D));
/*  19:    */   
/*  20:    */   private LineWeightManager()
/*  21:    */   {
/*  22: 60 */     this.etalon = new Vector();
/*  23: 61 */     this.vector = new Vector();
/*  24:    */     
/*  25: 63 */     this.etalon.add(new LineWeight(new MmUnit(0.0D)));
/*  26: 64 */     this.etalon.add(new LineWeight(new MmUnit(0.05D)));
/*  27: 65 */     this.etalon.add(new LineWeight(new MmUnit(0.09D)));
/*  28: 66 */     this.etalon.add(new LineWeight(new MmUnit(0.13D)));
/*  29: 67 */     this.etalon.add(new LineWeight(new MmUnit(0.15D)));
/*  30: 68 */     this.etalon.add(new LineWeight(new MmUnit(0.18D)));
/*  31: 69 */     this.etalon.add(new LineWeight(new MmUnit(0.2D)));
/*  32: 70 */     this.etalon.add(new LineWeight(new MmUnit(0.25D)));
/*  33: 71 */     this.etalon.add(new LineWeight(new MmUnit(0.3D)));
/*  34: 72 */     this.etalon.add(new LineWeight(new MmUnit(0.35D)));
/*  35: 73 */     this.etalon.add(new LineWeight(new MmUnit(0.4D)));
/*  36: 74 */     this.etalon.add(new LineWeight(new MmUnit(0.5D)));
/*  37: 75 */     this.etalon.add(new LineWeight(new MmUnit(0.53D)));
/*  38: 76 */     this.etalon.add(new LineWeight(new MmUnit(0.6D)));
/*  39: 77 */     this.etalon.add(new LineWeight(new MmUnit(0.7D)));
/*  40: 78 */     this.etalon.add(new LineWeight(new MmUnit(0.8D)));
/*  41: 79 */     this.etalon.add(new LineWeight(new MmUnit(0.9D)));
/*  42: 80 */     this.etalon.add(new LineWeight(new MmUnit(1.0D)));
/*  43: 81 */     this.etalon.add(new LineWeight(new MmUnit(1.06D)));
/*  44: 82 */     this.etalon.add(new LineWeight(new MmUnit(1.2D)));
/*  45: 83 */     this.etalon.add(new LineWeight(new MmUnit(1.4D)));
/*  46: 84 */     this.etalon.add(new LineWeight(new MmUnit(1.58D)));
/*  47: 85 */     this.etalon.add(new LineWeight(new MmUnit(2.0D)));
/*  48: 86 */     this.etalon.add(new LineWeight(new MmUnit(2.11D)));
/*  49:    */     
/*  50: 88 */     this.vector.addAll(this.etalon);
/*  51:    */   }
/*  52:    */   
/*  53:    */   public static LineWeightManager getInstance()
/*  54:    */   {
/*  55: 92 */     return instance;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public Collection getLineWeightList()
/*  59:    */   {
/*  60: 96 */     return this.vector;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void setUnitType(int type)
/*  64:    */   {
/*  65:100 */     if (this.unitType != type)
/*  66:    */     {
/*  67:101 */       this.unitType = type;
/*  68:102 */       reload();
/*  69:    */     }
/*  70:    */   }
/*  71:    */   
/*  72:    */   public int getUnitType()
/*  73:    */   {
/*  74:107 */     return this.unitType;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public LineWeight getDefaulLineWeight()
/*  78:    */   {
/*  79:111 */     return this.defaulLineWeight;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void setDefaulLineWeight(LineWeight defaulLineWeight)
/*  83:    */   {
/*  84:115 */     this.defaulLineWeight = defaulLineWeight;
/*  85:    */   }
/*  86:    */   
/*  87:    */   private void reload()
/*  88:    */   {
/*  89:120 */     if (this.unitType == MILIMETOR_UNITTYPE)
/*  90:    */     {
/*  91:121 */       this.vector.clear();
/*  92:122 */       this.vector.addAll(this.etalon);
/*  93:    */     }
/*  94:125 */     if (this.unitType == INCH_UNITTYPE)
/*  95:    */     {
/*  96:126 */       this.vector.clear();
/*  97:127 */       for (int i = 0; i < this.etalon.size(); i++)
/*  98:    */       {
/*  99:128 */         LineWeight lw = (LineWeight)this.etalon.get(i);
/* 100:129 */         MmUnit unit = (MmUnit)lw.getLineWeightValue();
/* 101:130 */         this.vector.add(new LineWeight(UnitTranslator.milimetorToInch(unit)));
/* 102:    */       }
/* 103:    */     }
/* 104:134 */     if (this.unitType == PIXEL_UNITTYPE)
/* 105:    */     {
/* 106:135 */       this.vector.clear();
/* 107:136 */       for (int i = 0; i < this.etalon.size(); i++)
/* 108:    */       {
/* 109:137 */         LineWeight lw = (LineWeight)this.etalon.get(i);
/* 110:138 */         MmUnit unit = (MmUnit)lw.getLineWeightValue();
/* 111:139 */         int res = ScreenManager.getInstance().getScreenResolution();
/* 112:140 */         this.vector.add(new LineWeight(unit));
/* 113:    */       }
/* 114:    */     }
/* 115:    */   }
/* 116:    */ }


/* Location:           C:\Users\kul_mut\Downloads\CADBox.2D-1.0.0\CADBox.2D\CADBox.2D.jar
 * Qualified Name:     org.cadbox.j2d.scale.core.LineWeightManager
 * JD-Core Version:    0.7.0.1
 */