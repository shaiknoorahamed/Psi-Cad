 package org.cadbox;
 import java.awt.Toolkit;

 public class ScreenManager
 	{
 private static ScreenManager instance = new ScreenManager();
  
   public static ScreenManager getInstance()
   {
     return instance;
/* 12:   */   }
/* 13:   */   
   public int getScreenResolution()
   {
     return Toolkit.getDefaultToolkit().getScreenResolution();
   }
 }


