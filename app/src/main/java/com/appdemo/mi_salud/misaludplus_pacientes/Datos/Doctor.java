package com.appdemo.mi_salud.misaludplus_pacientes.Datos;

public class Doctor {

    private String id="";
    private String nombre = "";
    private String slogan = "";
    private int img = 0;
    private String upregrado="";
    private String uposgrado="";
    private int actagrado=0;
    private int diploma=0;
    private String[] servicios={};
    private int visitas=0;
    private int calificacion=0;

    public Doctor(String id, String nombre, String slogan, int img, String upre, String upos, int agr, int dpl, String[] serv, int vis, int calf) {
        this.id = id;
        this.nombre = nombre;
        this.slogan = slogan;
        this.img = img;
        this.upregrado=upre;
        this.uposgrado=upos;
        this.actagrado=agr;
        this.diploma=dpl;
        this.servicios=serv;
        this.visitas=vis;
        this.calificacion=calf;
    }

    public String getUpregrado() {
        return upregrado;
    }

    public void setUpregrado(String upregrado) {
        this.upregrado = upregrado;
    }

    public String getUposgrado() {
        return uposgrado;
    }

    public void setUposgrado(String uposgrado) {
        this.uposgrado = uposgrado;
    }

    public int getActagrado() {
        return actagrado;
    }

    public void setActagrado(int actagrado) {
        this.actagrado = actagrado;
    }

    public int getDiploma() {
        return diploma;
    }

    public void setDiploma(int diploma) {
        this.diploma = diploma;
    }

    public String[] getServicios() {
        return servicios;
    }

    public void setServicios(String[] servicios) {
        this.servicios = servicios;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


}
