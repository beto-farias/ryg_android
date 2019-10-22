package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class MonedaTO implements Serializable {
    private int id_moneda;
    private String txt_moneda;
    private String txt_siglas;
    private int b_habilitado;

    public int getId_moneda() {
        return id_moneda;
    }

    public void setId_moneda(int id_moneda) {
        this.id_moneda = id_moneda;
    }

    public String getTxt_moneda() {
        return txt_moneda;
    }

    public void setTxt_moneda(String txt_moneda) {
        this.txt_moneda = txt_moneda;
    }

    public String getTxt_siglas() {
        return txt_siglas;
    }

    public void setTxt_siglas(String txt_siglas) {
        this.txt_siglas = txt_siglas;
    }

    public int getB_habilitado() {
        return b_habilitado;
    }

    public void setB_habilitado(int b_habilitado) {
        this.b_habilitado = b_habilitado;
    }
}
