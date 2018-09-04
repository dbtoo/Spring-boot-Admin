
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


 public class CcTest {

     public static void main(String[] args) {
         {

             System.out.println(AdPlace.getValue("1"));
             System.out.println("aa");

         }
     }
 }
enum AdPlace {
    PC("1", "PC "),
    APP("2", "APP");
    /**
     * mccCode
     */
    private String placeCode;
    /**
     * 中文值
     */
    private String chValue;
    public static String getValue(String state) {
        for (AdPlace place : AdPlace.values()) {
            if (place.getPlaceCode().equals(state)) {
                return place.getChValue();
            }
        }
        return "";
    }

    public String getPlaceCode() {
        return placeCode;
    }



    public String getChValue() {
        return chValue;
    }


     AdPlace(String placecode, String placeString) {
        this.placeCode = placecode;
        this.chValue = placeString;
    }

}