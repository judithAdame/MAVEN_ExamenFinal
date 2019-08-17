package com.adame.main;

import com.adame.dao.ManagerDAO;

/** 
 * @author 1895648
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = "./json/";
        ManagerDAO.getResultats(path+"Manager.json", path+"ManagerResult.json");
    }
    
}
