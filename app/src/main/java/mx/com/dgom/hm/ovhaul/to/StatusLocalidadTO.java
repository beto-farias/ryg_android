package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class StatusLocalidadTO implements Serializable {
    private int id_catalogo;
    private String txt_nombre;

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getTxt_nombre() {
        return txt_nombre;
    }

    public void setTxt_nombre(String txt_nombre) {
        this.txt_nombre = txt_nombre;
    }
}
