
package mycrypt;

import java.util.Scanner;

/**
 * @author halil        7-9/10/2021
 */
public class MyCrypt {

    public static void main(String[] args) { 
      Scanner sc = new Scanner(System.in);
         Crypt c= new Crypt();
        boolean kontrolDongu = true;
         
         while(kontrolDongu){
             System.out.print("####SEÇİNİZ####\nMetin Şifreleme:1\nMetin Şifresi Çözme:2\nÇıkış:3\n###############\n");
             String option = sc.nextLine();
             
             switch(option){
                 case "1":
                          System.out.print("#Türkçe karakter kullanmayınız#\nŞifrelenecek mesajı giriniz: "); 
                          String textinput = sc.nextLine().toLowerCase();
                          String a = c.crypt10(textinput);
                          System.out.println("\n"+a+"\n"); 
                break;
                
                 case "2":
                          System.out.print("Şifresi çözülecek mesajı giriniz: "); 
                          String cryptedtextinput = sc.nextLine().toLowerCase();
                          String de = c.decrypt10(cryptedtextinput);
                          System.out.println("\nŞifrelenmiş Mesaj:"+de.trim()+"\n"); 
                break;
                 
                 case "3": kontrolDongu = false; break;
                 
                 default:System.out.println(" Hatalı Seçim: "+option+"\n"); break;
             }
         }
    }
}
