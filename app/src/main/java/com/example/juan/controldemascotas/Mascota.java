package com.example.juan.controldemascotas;


public class Mascota {
    private String nombre;
    private String tipo;
    private String dataNac;
    private static int id = 0;
    private int idMascota;
    private int imgId;
    private String dataVacunacion;
    private String citaVet;
    private Boolean especial;

    public Mascota(String nombre, String tipo,String dataNac) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dataNac = dataNac;
        imgId = R.drawable.unknown;
        idMascota = id++;
        dataVacunacion = null;
        citaVet = null;
        especial = false;
    }

    public Mascota(int idMascota, String nombre, String tipo,int imgId,String dataNac, String dataVacunacion, String citaVet, boolean especial) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dataNac = dataNac;
        this.imgId = imgId;
        this.idMascota = idMascota;
        this.dataVacunacion = dataVacunacion;
        this.citaVet = citaVet;
        this.especial = especial;
    }

    public Mascota() {
        this.nombre = null;
        this.tipo = null;
        this.dataNac = null;
        imgId = R.drawable.unknown;
        idMascota = id++;
        this.dataVacunacion = null;
        this.citaVet = null;
        this.especial = false;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setDataNac(String dataNac){
        this.dataNac = dataNac;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDataNac() {
        return dataNac;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getDataVacunacion() {
        return dataVacunacion;
    }

    public void setDataVacunacion(String dataVacunacion) {
        this.dataVacunacion = dataVacunacion;
    }

    public String getCitaVet() {
        return citaVet;
    }

    public void setCitaVet(String citaVet) {
        this.citaVet = citaVet;
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
    }

    public boolean EsValida() {
        return nombre != null && tipo != null && dataNac != null &&
                !nombre.isEmpty() && !tipo.isEmpty() && !dataNac.isEmpty();
    }

}
