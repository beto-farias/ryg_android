package mx.com.dgom.hm.ovhaul.to;

import android.widget.TextView;

import java.io.Serializable;

public class ComentariosLocalidadTO implements Serializable {
    private String txt_comentario;
    private String fch_comentario;
    private UserTO usuario;


    public String getTxt_comentario() {
        return txt_comentario;
    }

    public void setTxt_comentario(String txt_comentario) {
        this.txt_comentario = txt_comentario;
    }

    public String getFch_comentario() {
        return fch_comentario;
    }

    public void setFch_comentario(String fch_comentario) {
        this.fch_comentario = fch_comentario;
    }

    public UserTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserTO usuario) {
        this.usuario = usuario;
    }
}
