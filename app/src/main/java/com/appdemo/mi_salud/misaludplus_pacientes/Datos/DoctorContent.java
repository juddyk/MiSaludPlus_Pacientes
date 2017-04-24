package com.appdemo.mi_salud.misaludplus_pacientes.Datos;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorContent {

    private static List<Doctor> DoctorList = new ArrayList<>();

    public static List getDoctorList() {
        return DoctorList;
    }

    public static final Map<String, Doctor> ITEM_MAP = new HashMap<>();

    public static void addDoctor(Doctor item) {
        DoctorList.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
    
}
