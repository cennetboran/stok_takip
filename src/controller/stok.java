/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.ana_sayfa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.SpringLayout;

/**
 *
 * @author Ali Sezer
 */
public class stok {
    
    public int stok_id;
    public String stok_ad;
    public int stok_adet;
    public ana_sayfa as;
    
    
    public stok(int id, String ad, int adet){
        this.stok_id = id;
        this.stok_ad = ad;
        this.stok_adet = adet;
    
    }
    
    public ArrayList<stok> s_array;
    
   public static Connection getConnection()throws Exception{
       
       try{
           String driver = "com.mysql.jdbc.Driver";
           String url = "jdbc:mysql://localhost:3306/stok";
           String username = "root";
           String password = "root";
           Class.forName(driver);
           
           Connection conn = DriverManager.getConnection(url, username, password);
           System.out.println("Connected");
           return conn;
       }catch(Exception e){
           System.out.println("ERRRRROOOOOOOORRRRRRRRRRRRRR" + e);
       }
       
       return null;
   }
   
   public static ArrayList<stok> get() throws Exception {
       
       try{
           Connection con = getConnection();
           PreparedStatement statement = con.prepareStatement("SELECT stok_id,stok_ad,stok_adet FROM stok.stok_table");
           ResultSet result = statement.executeQuery();
           System.out.println(result);
           ArrayList<stok> array = new ArrayList<stok>();
           while(result.next()){
               int id = result.getInt("stok_id");
               String ad = result.getString("stok_ad");
               int adet = result.getInt("stok_adet");
               
               stok stok1 = new stok(id,ad,adet);
               
               /*System.out.print(result.getInt("stok_id"));
               System.out.print(" ");
               System.out.print(result.getString("stok_ad"));
               System.out.print(" ");
               System.out.println(result.getInt("stok_adet"));*/
               
               array.add(stok1);
           }
           System.out.println("Liste");
           return array;
           
           
           
       }catch(Exception e){System.out.println(e);}
       return null;
   }
    public static void post(String stok_ad,int stok_adet) throws Exception{
        try{
        Connection con = getConnection();
        PreparedStatement posted = con.prepareStatement("INSERT INTO stok.stok_table (stok_ad, stok_adet) VALUES ('"+stok_ad+"', "+stok_adet+")");
        posted.executeUpdate();
                }catch(Exception e){
                    System.out.println(e);
                }
        finally{
            System.out.println("Insert Completed");
        }
    }
    
    public static ArrayList<stok> query(String stok_ad) throws Exception{
        if(stok_ad.length()== 0){
            System.out.println("Boştur");
        } 
        else {
        try{
            Connection con = getConnection();
           PreparedStatement statement = con.prepareStatement("SELECT * FROM stok.stok_table WHERE stok_ad LIKE '%"+stok_ad+"%'");
           ResultSet result = statement.executeQuery();
           ArrayList<stok> array = new ArrayList<stok>();
           while(result.next()){
               
               int id = result.getInt("stok_id");
               String ad = result.getString("stok_ad");
               int adet = result.getInt("stok_adet");
               
               stok stok1 = new stok(id,ad,adet);
               
               /*System.out.print(result.getString("stok_id"));
               System.out.print(" ");
               System.out.print(result.getString("stok_ad"));
               System.out.print(" ");
               System.out.println(result.getString("stok_adet"));*/
               
               array.add(stok1);
           }
           System.out.println(array);
           return array;
            
            
        }catch(Exception e){System.out.println("Bulunamdı");}
        
        }
        return null;
    }
    
    public static void update(String ad,String adet) throws Exception {
        try{
            
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE stok.stok_table SET stok_adet = ("+adet+") WHERE stok_ad = '"+ad+"'");
            statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        
    }
    
    public static void delete(int id) throws Exception{
        try{
            
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("Delete from stok.stok_table where stok_id = "+id );
            statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    
}
