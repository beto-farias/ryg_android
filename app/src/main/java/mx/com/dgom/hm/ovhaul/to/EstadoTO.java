package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class EstadoTO implements Serializable {
    private int id_estado;
    private int id_pais;
    private int num_estado;
    private String txt_nombre;
    private String txt_descripcion;
    private double num_latitud;
    private double num_longitud;
    private int b_habilitado;

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getNum_estado() {
        return num_estado;
    }

    public void setNum_estado(int num_estado) {
        this.num_estado = num_estado;
    }

    public String getTxt_nombre() {
        return txt_nombre;
    }

    public void setTxt_nombre(String txt_nombre) {
        this.txt_nombre = txt_nombre;
    }

    public String getTxt_descripcion() {
        return txt_descripcion;
    }

    public void setTxt_descripcion(String txt_descripcion) {
        this.txt_descripcion = txt_descripcion;
    }

    public double getNum_latitud() {
        return num_latitud;
    }

    public void setNum_latitud(double num_latitud) {
        this.num_latitud = num_latitud;
    }

    public double getNum_longitud() {
        return num_longitud;
    }

    public void setNum_longitud(double num_longitud) {
        this.num_longitud = num_longitud;
    }

    public int getB_habilitado() {
        return b_habilitado;
    }

    public void setB_habilitado(int b_habilitado) {
        this.b_habilitado = b_habilitado;
    }
}
