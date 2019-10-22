package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class ComentariosTareasTO implements Serializable {
    private int id_comentario;
    private int id_usuario;
    private int id_tarea;
    private String txt_comentario;
    private String fch_comentario;
     private int b_habilitado;
     private UserTO usuario;

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

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

    public int getB_habilitado() {
        return b_habilitado;
    }

    public void setB_habilitado(int b_habilitado) {
        this.b_habilitado = b_habilitado;
    }

    public UserTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserTO usuario) {
        this.usuario = usuario;
    }
}
