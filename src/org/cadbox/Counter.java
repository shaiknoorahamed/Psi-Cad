 package org.cadbox;
 
 public class Counter
 
 {
 
	 private static int index = 1;
  
 
	 public static synchronized int getIndex()
   
	 {
    
		 return index++;
   
	 }

 }

