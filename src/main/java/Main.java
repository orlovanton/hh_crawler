/**
 * Created by antonorlov on 16/06/2017.
 */
public class Main {

    public static void main(String[] args) {
        String str1 = new String("str1");
        String str2 = new String("str1");

        System.out.println(str1 == str2);

        String str3 = "str";
        String str4 = "str";

        System.out.println(str3 == str4);
    }


    public static boolean isValid(String param) {
        if (param.equals("test")) {
            return true;
        } else {
            return false;
        }
    }


    public static String covenrtToString (Object o){
        //todo: impl
        return null;
    }

}


