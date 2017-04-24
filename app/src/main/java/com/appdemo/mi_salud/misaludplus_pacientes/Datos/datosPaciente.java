package com.appdemo.mi_salud.misaludplus_pacientes.Datos;

/**
 *  Clase para los datos de los Pacientes
 */

public class datosPaciente {
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private int fnDia;
    private int fnMes;
    private int fnAnio;
    private String tpDoc;
    private String numDoc;
    private String ocupacion;
    private String genero;
    private String stsCivil;
    private String departamento;
    private String municipio;
    private int estrato;
    private String direccion;
    private String celular;
    private String fijo1;
    private String fijo2;
    private String eps;
    private String arl;
    private String regimen;
    private String grupoEt;
    private String discapacidad;
    private String correo1;
    private String correo2;
    private String psswrd;

    public datosPaciente(){
        this.nombre1 = "";
        this.nombre2 = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.fnDia = 0;
        this.fnMes = 0;
        this.fnAnio = 0;
        this.tpDoc = "";
        this.numDoc = "";
        this.ocupacion = "";
        this.genero = "";
        this.stsCivil = "";
        this.departamento = "";
        this.municipio = "";
        this.estrato = 0;
        this.direccion ="";
        this.celular = "";
        this.fijo1 = "";
        this.fijo2 = "";
        this.eps = "";
        this.arl = "";
        this.regimen = "";
        this.grupoEt = "";
        this.discapacidad = "";
        this.correo1 = "";
        this.correo2 = "";
        this.psswrd = "";
    }

/*
    public datosPaciente(String nombre1, String nombre2, String apellido1, String apellido2, int fnDia, int fnMes, int fnAnio, String tpDoc, String numDoc, String ocupacion, String genero, String stsCivil, String departamento, String municipio, int estrato, String direccion, String celular, String fijo1, String fijo2, String eps, String arl, String regimen, String grupoEt, String discapacidad, String correo1, String correo2, String password) {
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fnDia = fnDia;
        this.fnMes = fnMes;
        this.fnAnio = fnAnio;
        this.tpDoc = tpDoc;
        this.numDoc = numDoc;
        this.genero = genero;
        this.stsCivil = stsCivil;
        this.departamento = departamento;
        this.municipio = municipio;
        this.estrato = estrato;
        this.direccion = direccion;
        this.celular = celular;
        this.fijo1 = fijo1;
        this.fijo2 = fijo2;
        this.eps = eps;
        this.arl = arl;
        this.regimen = regimen;
        this.grupoEt = grupoEt;
        this.discapacidad = discapacidad;
        this.correo1 = correo1;
        this.correo2 = correo2;
        this.psswrd = password;
        this.ocupacion=ocupacion;
    }
*/

    //GET
    public String getNombre1() {
        return nombre1;
    }
    public String getNombre2() {
        return nombre2;
    }
    public String getApellido1() {
        return apellido1;
    }
    public String getApellido2() {
        return apellido2;
    }
    public int getFnDia() {
        return fnDia;
    }
    public int getFnMes() {
        return fnMes;
    }
    public int getFnAnio() {
        return fnAnio;
    }
    public String getDireccion() {
        return direccion;
    }
    public String getPsswrd() {
        return psswrd;
    }
    public String getNumDoc() {
        return numDoc;
    }
    public String getTpDoc() {
        return tpDoc;
    }
    public String getGenero() {
        return genero;
    }
    public String getStsCivil() {
        return stsCivil;
    }
    public String getDepartamento() {
        return departamento;
    }
    public String getMunicipio() {
        return municipio;
    }
    public int getEstrato() {
        return estrato;
    }
    public String getCelular() {
        return celular;
    }
    public String getFijo1() {
        return fijo1;
    }
    public String getFijo2() {
        return fijo2;
    }
    public String getEps() {
        return eps;
    }
    public String getArl() {
        return arl;
    }
    public String getRegimen() {
        return regimen;
    }
    public String getGrupoEt() {
        return grupoEt;
    }
    public String getDiscapacidad() {
        return discapacidad;
    }
    public String getCorreo1() {
        return correo1;
    }
    public String getCorreo2() {
        return correo2;
    }
    public String getOcupacion() {
        return ocupacion;
    }


    //SET
    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }
    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }
    public void setFnDia(int fnDia) {
        this.fnDia = fnDia;
    }
    public void setFnMes(int fnMes) {
        this.fnMes = fnMes;
    }
    public void setFnAnio(int fnAnio) {
        this.fnAnio = fnAnio;
    }
    public void setTpDoc(String tpDoc) {
        this.tpDoc = tpDoc;
    }
    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setStsCivil(String stsCivil) {
        this.stsCivil = stsCivil;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public void setFijo1(String fijo1) {
        this.fijo1 = fijo1;
    }
    public void setFijo2(String fijo2) {
        this.fijo2 = fijo2;
    }
    public void setEps(String eps) {
        this.eps = eps;
    }
    public void setArl(String arl) {
        this.arl = arl;
    }
    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }
    public void setGrupoEt(String grupoEt) {
        this.grupoEt = grupoEt;
    }
    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }
    public void setCorreo1(String correo1) {
        this.correo1 = correo1;
    }
    public void setCorreo2(String correo2) {
        this.correo2 = correo2;
    }
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }
    public void setPsswrd(String psswrd) {
        this.psswrd = psswrd;
    }

}
