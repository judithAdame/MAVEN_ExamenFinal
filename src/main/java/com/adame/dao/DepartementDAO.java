package com.adame.dao;

import com.adame.modele.Departement;

/**
 *
 * @author 1895648
 */
public class DepartementDAO {

    static String getDepartment(Departement d) {
        return d.getDepartmentId()+"-"+d.getDepartmentName();
    }
    static String getSituation(Departement d) {
        return (d.isCurrent()?"Is current":"current");
    }    
}
