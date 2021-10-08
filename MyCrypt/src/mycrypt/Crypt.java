package mycrypt;

/**
 * @author halil        7-9/10/2021
 */
public class Crypt 
{
    public String crypt10(String a)
    {
    String crypt = ""; 
    String[] blocks = block16(a);
    String[][] matrix = new String[4][4];
    String temp = "";
        for (String block : blocks) {
            //sifrele
             //sbox[0] uygula
             temp = sBox(block,"crypt");
            //matrise cevir
            matrix = stringToMatrix(temp);
            //shift
           matrix = shiftLeftMatrix(matrix);
            //90 derece sola döndür
            matrix = matrix90Left(matrix);
            //matrixi stringe cevir sifreye ekle
           crypt += matrixToString(matrix);
         }
               
    return crypt;
}
    
    public String decrypt10(String a){// a şifreli string 
    String decrypt = ""; 
    String[] blocks = block16(a);
    String[][] matrix = new String[4][4];
    String temp = "";
    for (String block : blocks) {
    // matrise çevir
    matrix = stringToMatrix(block);
    // 3 kere sola dödür
    matrix = matrix90Left(matrix);
    matrix = matrix90Left(matrix);
    matrix = matrix90Left(matrix);
    //saga kaydır
    matrix = shiftLeftMatrix(matrix);
    matrix = shiftLeftMatrix(matrix);
    matrix = shiftLeftMatrix(matrix);
    // Stringe çevir
    temp = matrixToString(matrix);
    // Sbox[1] uygula
    decrypt += sBox(temp,"decrypt");
    }
        return decrypt;
    }
    
    private String sBox(String a, String doWhat ){
    String result="",temp;
     int h = 0;
    while(h<a.length()){
        temp = ""+a.charAt(h); 
        if(doWhat.equals("decrypt")){
        for (String[] cryptedLetter : cryptedLetters) {
            if (temp.equals(cryptedLetter[1])) {
                result += cryptedLetter[0];
                break;
            } 
        }
        }
        else{
        for (String[] cryptedLetter : cryptedLetters) {
            if (temp.equals(cryptedLetter[0])) {
                result += cryptedLetter[1];
                break;
            } 
        }
        }
    h++;
    }
     return result;
    }
    
    private String matrixToString(String[][] m){
        String result = "";
    for(int i =0; i<m.length; i++){
        for(int j=0; j<m[0].length; j++){
        result += m[i][j];
        }
    }
    return result;
    }
    
    private String[][] matrix90Left(String[][] matrix){
            String[][] arr = new String[4][4];
            int ii = 0, jj=0;
            
            for(int j = 3; j>=0; j--){
                jj =0;
                for(int i =0; i<4;i++){
                arr[ii][jj] = matrix[i][j];
                jj++;
                }
                ii++;
            }
    return arr;
    }
    
    private String[][] shiftLeftMatrix(String[][] mX)
    {
        String[][] res = new String[4][4];
        int point=0;
       for(int i=0; i<4; i++){
           point = i;
           for(int j=0; j<4; j++){
               if(point == 3 ){
                   res[i][j] = mX[i][point];
                   point = -1;
               } else{
                   res[i][j] = mX[i][point];
               }
               point++;
           }
       }
       return res;
    }

    private String[][] stringToMatrix(String text)
    {
          String[][] result = new String[4][4];
    String temp="";
for(byte i=0;i<text.length();i++)
{
    temp = ""+text.charAt(i);
result[i/4][i%4 ] = temp;
}
  return result;    
}    
               
    private  String[] block16(String text){//gelen stringi 16lık parcalara boler
    String temp="";
 if(!(text.length() %16 ==0))
 {
 int b = text.length() %16;
 for(int i = 0; i<=16-b;i++)
 {
 text += ";";
 }    
 }   
 int j =text.length()/16;
 String[] blockText16Piece = new String[j];
j=0;
 for(int i = 0; i<text.length(); i++){
      temp +=""+text.charAt(i);
 if((i+1)%16 == 0 ){
     blockText16Piece[j] = temp;
     temp="";
     j++;
 }
 }
 return blockText16Piece;
}

    String[][] cryptedLetters ={  //  cryptedLetters[plainLetter][CryptedLetter]
    // karakterlerle harfleri şifrele
    {"a","!"},{"b","-"},{"c","*"},{"d",":"},{"e","_"},{"f","]"},{"g","+"},{"h","x"},
    {"i","6"},{"j","#"},{"k","1"},{"l","4"},{"m","7"},{"n","5"},{"o","="},{"p",","},
    {"r","g"},{"s","a"},{"t","@"},{"u","["},{"v","8"},{"y","."},{"z","3"},{"q","2"},
    {"w","c"},{"1","{"},{"2","v"},{"3","j"},{"4","k"},{"5","t"},{"6","n"},{"7","m"},{"8","s"},
    
    {"\"","^"},{"é","\'"},{"<","e"},{"!","£"},{">","y"},{"\'","("},{"£","&"},{"^","l"},{"#",")"},{"+","%"},
    {"$","r"},{"%","?"},{"&","w"},{"/","i"},{"{","$"},{"(","}"},{")",">"},{"[","/"},{"]","q"},{"=","\\"},
    {"}","é"},{"?","<"},{"*","o"},{"\\","\""},{"-","h"},{"_","l"},{".","|"},{":","u"},{"x","z"},{",","f"},
    {"|","p"},{"@","€"},{"₺","æ"},{"€","ß"},{"æ","d"},{"ß","₺"},{"´","`"},{"`","´"}
         
            //16 block ayar yönetimi
               ,{" "," "} ,{";"," "}
            //16 block ayar yönetimi
            
            //uyumsuz
            ,{"ç","ç"},{"ğ","ğ"},{"ı","ı"},{"ö","ö"},{"ş","ş"},{"ü","ü"}
            //uyumsuz
    };

}
