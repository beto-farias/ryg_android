package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class EstatusTO implements Serializable {
    private int id_status;
    private String txt_nombre;
    private String txt_descripcion;
    private int b_habilitado;

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
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

    public int getB_habilitado() {
        return b_habilitado;
    }

    public void setB_habilitado(int b_habilitado) {
        this.b_habilitado = b_habilitado;
    }
}
