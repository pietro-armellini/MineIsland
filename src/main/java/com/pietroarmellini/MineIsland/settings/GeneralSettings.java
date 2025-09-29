package com.pietroarmellini.MineIsland.settings;  
  
import org.mineacademy.fo.settings.SimpleSettings;  
  
public class GeneralSettings extends SimpleSettings {    
        
    // Basic price setting    
    public static Double BASIC_PRICE = 10.0;    
        
    // Increasing percentage setting      
    public static Double INCREASING_PERCENTAGE = 15.0;    

        
    private static void init() {    
        // Load basic price    
        if (isSetDefault("Basic_Price"))    
            BASIC_PRICE = getDouble("Basic_Price");    
                
        // Load increasing percentage using the built-in percentage handler    
        if (isSetDefault("Increasing_Percentage"))    
            INCREASING_PERCENTAGE = getPercentage("Increasing_Percentage");    
    }  
  
    public static double getPriceForNextSubRegion(int ownedSubRegions) {  
        return BASIC_PRICE * Math.pow(1 + INCREASING_PERCENTAGE, ownedSubRegions-1);  
    }  
}