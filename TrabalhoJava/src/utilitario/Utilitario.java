package utilitario;

import java.text.*;
import java.util.*;
import javax.swing.text.MaskFormatter;
//import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Elida
 */
public class Utilitario {


     public static String getDateTimeToStringBD(Date data) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(data);
    }

    public static String getDateToString(String formatDate, Date data) {
        DateFormat df = new SimpleDateFormat(formatDate);
        return df.format(data);
    } 
     
    public static String formatarString(String texto, String mascara) throws ParseException {
        MaskFormatter mf = new MaskFormatter(mascara);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(texto);
    }
    
    public static Date getStringToDate(String formatDate, String data) {
        DateFormat df = new SimpleDateFormat(formatDate);
        try {
            return df.parse(data);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public static String getSomenteNumeros(String texto) {
        String number = "0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < texto.length(); i++) {
            if (number.indexOf(texto.charAt(i)) != -1) {
                sb.append(texto.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String getCPFFormatado(String cpf) {
        try {
            return formatarString(cpf.replaceAll("[^0-9]", ""), "###.###.###-##");
        } catch (ParseException ex) {
            return "";
        } catch (NullPointerException ex) {
            return "";
        }
    }

  

}