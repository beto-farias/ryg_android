package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class LogTO implements Serializable {
    private int id_evento;
    private String token;
    private int id_tipo_evento;
    private int id_localidad;
    private int id_tarea;
    private int id_usuario;
    private String txt_area;
    private String txt_accion;
    private String fch_evento;
    private String txt_nombre_usuario;
    private String txt_localidad;
    private String txt_tarea;
    private String txt_user_avatar;


    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId_tipo_evento() {
        return id_tipo_evento;
    }

    public void setId_tipo_evento(int id_tipo_evento) {
        this.id_tipo_evento = id_tipo_evento;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTxt_area() {
        return txt_area;
    }

    public void setTxt_area(String txt_area) {
        this.txt_area = txt_area;
    }

    public String getTxt_accion() {
        return txt_accion;
    }

    public void setTxt_accion(String txt_accion) {
        this.txt_accion = txt_accion;
    }

    public String getFch_evento() {
        return fch_evento;
    }

    public void setFch_evento(String fch_evento) {
        this.fch_evento = fch_evento;
    }

    public String getTxt_nombre_usuario() {
        return txt_nombre_usuario;
    }

    public void setTxt_nombre_usuario(String txt_nombre_usuario) {
        this.txt_nombre_usuario = txt_nombre_usuario;
    }

    public String getTxt_localidad() {
        return txt_localidad;
    }

    public void setTxt_localidad(String txt_localidad) {
        this.txt_localidad = txt_localidad;
    }

    public String getTxt_tarea() {
        return txt_tarea;
    }

    public void setTxt_tarea(String txt_tarea) {
        this.txt_tarea = txt_tarea;
    }

    public String getTxt_user_avatar() {
        return txt_user_avatar;
    }

    public void setTxt_user_avatar(String txt_user_avatar) {
        this.txt_user_avatar = txt_user_avatar;
    }
}
