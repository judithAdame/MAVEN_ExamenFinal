package com.adame.utileries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1895648
 */
public class Formatage {
   public static double ParseCurrency(String s){
        String regex = "[^\\d.-]+";
        String newStr = s.replace(",", ".");
        newStr = newStr.replaceAll(regex, "");
        double resultat = Double.parseDouble(newStr);
        return resultat;
    } 
   
    public static Date ParseDate(String s){
        String formatISO8601 = "yyyy-MM-dd";
        Date date = new Date();
            if (isValidFormat(formatISO8601, s, Locale.CANADA_FRENCH)) {
                SimpleDateFormat format = new SimpleDateFormat(formatISO8601);     
                try {
                    date = format.parse(s);
                } catch (ParseException ex) {
                    Logger.getLogger(Formatage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return date;
    }
    
    private static boolean isValidFormat(String format, String value, Locale locale) {
        LocalDateTime ldt = null;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);
        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                }
            }
        }
        return false;
    }
}
