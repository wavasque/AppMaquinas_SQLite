package com.example.appmaquinas_sqlite.entidades;

public class EMovims {
    public EMovims(){

    }

    private int id;
    private int maquina_id;
    private int cliente_id;
    private double porcentaje;
    private String fecha;
    private double valor_corte;
    private double valor_cliente;
    private double valor_ganancia;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaquina_id() {
        return maquina_id;
    }

    public void setMaquina_id(int maquina_id) {
        this.maquina_id = maquina_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getValor_corte() {
        return valor_corte;
    }

    public void setValor_corte(double valor_corte) {
        this.valor_corte = valor_corte;
    }

    public double getValor_cliente() {
        return valor_cliente;
    }

    public void setValor_cliente(double valor_cliente) {
        this.valor_cliente = valor_cliente;
    }

    public double getValor_ganancia() {
        return valor_ganancia;
    }

    public void setValor_ganancia(double valor_ganancia) {
        this.valor_ganancia = valor_ganancia;
    }
}
