
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


 public class CcTest {

     public static void main(String[] args) {
         {

              List<Users> l =new ArrayList<Users>();
              Users u= new Users();
              u.id=1;
              u.name="ccc";
              l.add(u);
             Users u2= new Users();
              u2.id=2;
              u2.name="ddd";
              l.add(u2);

              for (Users ui:l){


              }

             System.out.println(JSONObject.toJSON(l));

         }
     }
 }

 class Users{
     public int id;
     public String name;
 }