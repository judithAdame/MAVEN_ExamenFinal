package com.adame.dao;

import com.adame.modele.Departement;
import com.adame.modele.Manager;
import com.adame.utileries.Formatage;
import static com.adame.utileries.Formatage.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * @author 1895648
 */
public class ManagerDAO {
    
    public static boolean getResultats(String input, String output) {
            JSONObject j = new JSONObject();
            Manager m = ManagerDAO.readJSONToFile(input);
            j.accumulate("ID", ManagerDAO.getId(m));
            j.accumulate("year_hire", ManagerDAO.getYearHire(m));
            j.accumulate("totalEmployee", ManagerDAO.getTotalEmployee(m));
            j.accumulate("averageMonths", ManagerDAO.getAverageMonths(m));
            
            Departement d;
            JSONObject departementJSON;
            Iterator<Departement> it = m.getDepartements().iterator();
            JSONArray listeDepartementJSON = new JSONArray();
            while(it.hasNext()){
                d=it.next();   
                departementJSON = new JSONObject();
                departementJSON.accumulate("department", DepartementDAO.getDepartment(d));
                departementJSON.accumulate("situation", DepartementDAO.getSituation(d));
                listeDepartementJSON.add(departementJSON);
            }
            j.accumulate("departments", listeDepartementJSON);
            File f = new File (output);
            try {            
                FileUtils.writeStringToFile (f, j.toString(), "UTF-8");
            } catch (IOException ex) {
                System.err.println("Impossible de créer le fichier "+output);
                return false;
            }
            System.err.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"+
                               "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            System.err.println("Le resultat se trouve dans le fichier :"+ f.getAbsolutePath());
            System.err.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*"+
                               "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
            return true;
    }
    
    //********************************lECTURE SU FICHIER JSON***************************************
    private static Manager readJSONToFile (String input){
       Manager manager = new Manager();
        try {
            String mainJSON = IOUtils.toString(new FileInputStream(input), "UTF-8");
            JSONObject m = JSONObject.fromObject(mainJSON);

            manager.setManagerNumber(m.getString("manager_number"));
            manager.setFirstName(m.getString("first_name"));
            manager.setLastName(m.getString("last_name"));
            manager.setDateHire (m.getString("date_hire"));
            manager.setRetired(m.getBoolean("retired"));
            manager.setSalary(ParseCurrency(m.getString("Salary")));
            
            JSONObject departementJson;
            Departement departement;
            JSONArray listeDepartementsJSON = m.getJSONArray("departments");
            
            for (int j = 0; j < listeDepartementsJSON.size(); j++) {
                departementJson = listeDepartementsJSON.getJSONObject(j);
                departement = new Departement();
                departement.setDepartmentId(departementJson.getString("department_id"));
                departement.setDepartmentName(departementJson.getString("department_name"));
                departement.setCurrent(departementJson.getBoolean("current"));
                departement.setNumberEmployees(departementJson.getInt("numberEmployees"));
                departement.setMonths(departementJson.getInt("months"));
                manager.addDepartement(departement);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Le fichier "+input + " est introuvable");
        } catch (IOException ex) {
            System.err.println("Impossible de lire le fichier "+input);
        }
        return manager;
    }

    private static String getId(Manager m) {
        return m.getManagerNumber()+"-"+m.getLastName();
    }

    private static int getYearHire(Manager m){
        String strDate = m.getDateHire();
        String[] splitDate = strDate.split(" ");
        String annee = splitDate[2].trim();
        return Integer.parseInt(annee);
    }

    private static int getTotalEmployee(Manager m) {
        int total=0;
        LinkedHashSet<Departement> departements = m.getDepartements();
        for (Departement d : departements) {
            total += d.getNumberEmployees();
        }        
        return total;
    }

    private static double getAverageMonths(Manager m) {
        double somme=0.0;
        int i=0;
        LinkedHashSet<Departement> departements = m.getDepartements();
        if (departements.isEmpty())
            return -1;
        for (Departement d : departements) {
            somme += d.getMonths();
            i++;
        }        
        return somme/i;
    }

}
