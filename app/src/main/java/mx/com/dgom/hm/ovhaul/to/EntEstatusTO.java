package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class EntEstatusTO implements Serializable {
    private int id_estatus;
    private int id_localidad;
    private String txt_estatus;
    private String fch_creacion;

    public int getId_estatus() {
        return id_estatus;
    }

    public void setId_estatus(int id_estatus) {
        this.id_estatus = id_estatus;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public String getTxt_estatus() {
        return txt_estatus;
    }

    public void setTxt_estatus(String txt_estatus) {
        this.txt_estatus = txt_estatus;
    }

    public String getFch_creacion() {
        return fch_creacion;
    }

    public void setFch_creacion(String fch_creacion) {
        this.fch_creacion = fch_creacion;
    }
}
