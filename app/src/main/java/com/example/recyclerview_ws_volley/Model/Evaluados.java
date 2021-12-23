package com.example.recyclerview_ws_volley.Model;

public class Evaluados {
    private String nombres;
    private String fechaEvaInicio;
    private String cargo;
    private String relacionDep;
    private String url;

    public Evaluados() {
    }

    public Evaluados(String nombres, String fechaEvaInicio, String cargo, String relacionDep,
                     String url) {

        this.nombres = nombres;
        this.fechaEvaInicio = fechaEvaInicio;
        this.cargo = cargo;
        this.relacionDep = relacionDep;
        this.url = url;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getFechaEvaInicio() {
        return fechaEvaInicio;
    }

    public void setFechaEvaInicio(String fechaEvaInicio) {
        this.fechaEvaInicio = fechaEvaInicio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRelacionDep() {
        return relacionDep;
    }

    public void setRelacionDep(String relacionDep) {
        this.relacionDep = relacionDep;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

