package com.example.recyclerview_ws_volley.Model;

import android.util.Patterns;

public class Evaluadores {
    private String idEvaluador;
    private String name;
    private String area;
    private String urlPhoto;

    public Evaluadores() {
    }

    public Evaluadores(String idEvaluador, String name, String area, String urlPhoto) {
        this.idEvaluador = idEvaluador;
        this.name = name;
        this.area = area;
        this.urlPhoto = urlPhoto;
    }

    public String getIdEvaluador() {
        return idEvaluador;
    }

    public void setIdEvaluador(String idEvaluador) {
        this.idEvaluador = idEvaluador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

}
