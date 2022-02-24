package com.example.appmaquinas_sqlite.entidades;

public class EMaquinas {

    public EMaquinas(){
        
    }

    private int id;
    private String maquina;
    private String ubicacion;
    private String departamento;
    private String utl_fecha_revisada;
    private String pro_fecha_revisar;
    private String status;
    private String cliente;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUtl_fecha_revisada() {
        return utl_fecha_revisada;
    }

    public void setUtl_fecha_revisada(String utl_fecha_revisada) {
        this.utl_fecha_revisada = utl_fecha_revisada;
    }

    public String getPro_fecha_revisar() {
        return pro_fecha_revisar;
    }

    public void setPro_fecha_revisar(String pro_fecha_revisar) {
        this.pro_fecha_revisar = pro_fecha_revisar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
