package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;
import java.util.ArrayList;

public class TareasTO implements Serializable {
    private int id_tarea;
    private String txt_token;
    private int id_usuario;
    private Integer id_tarea_padre;
    private int id_localidad;
    private Integer id_tipo;
    private String txt_nombre;
    private String txt_descripcion;
    private String txt_tarea;
    private String fch_creacion;
    private String fch_actualizacion;
    private String fch_asignacion;
    private String fch_due_date;
    private int b_completa;
    private String txt_path;
    private String fch_completada;
    private String txt_user_avatar;
    private UserTO usuarioResponsableTarea;
    private ArrayList<ComentariosTareasTO> wrkComentariosTareas;
    private ArrayList<DocumentosTO> wrkDocumentos;
    private String txt_localidad;

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getTxt_token() {
        return txt_token;
    }

    public void setTxt_token(String txt_token) {
        this.txt_token = txt_token;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_tarea_padre() {
        return id_tarea_padre;
    }

    public void setId_tarea_padre(Integer id_tarea_padre) {
        this.id_tarea_padre = id_tarea_padre;
    }

    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
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

    public String getTxt_tarea() {
        return txt_tarea;
    }

    public void setTxt_tarea(String txt_tarea) {
        this.txt_tarea = txt_tarea;
    }

    public String getFch_creacion() {
        return fch_creacion;
    }

    public void setFch_creacion(String fch_creacion) {
        this.fch_creacion = fch_creacion;
    }

    public String getFch_actualizacion() {
        return fch_actualizacion;
    }

    public void setFch_actualizacion(String fch_actualizacion) {
        this.fch_actualizacion = fch_actualizacion;
    }

    public String getFch_asignacion() {
        return fch_asignacion;
    }

    public void setFch_asignacion(String fch_asignacion) {
        this.fch_asignacion = fch_asignacion;
    }

    public String getFch_due_date() {
        return fch_due_date;
    }

    public void setFch_due_date(String fch_due_date) {
        this.fch_due_date = fch_due_date;
    }

    public int getB_completa() {
        return b_completa;
    }

    public void setB_completa(int b_completa) {
        this.b_completa = b_completa;
    }

    public String getTxt_path() {
        return txt_path;
    }

    public void setTxt_path(String txt_path) {
        this.txt_path = txt_path;
    }

    public String getFch_completada() {
        return fch_completada;
    }

    public void setFch_completada(String fch_completada) {
        this.fch_completada = fch_completada;
    }

    public String getTxt_user_avatar() {
        return txt_user_avatar;
    }

    public void setTxt_user_avatar(String txt_user_avatar) {
        this.txt_user_avatar = txt_user_avatar;
    }

    public UserTO getUsuarioResponsableTarea() {
        return usuarioResponsableTarea;
    }

    public void setUsuarioResponsableTarea(UserTO usuarioResponsableTarea) {
        this.usuarioResponsableTarea = usuarioResponsableTarea;
    }

    public ArrayList<ComentariosTareasTO> getWrkComentariosTareas() {
        return wrkComentariosTareas;
    }

    public void setWrkComentariosTareas(ArrayList<ComentariosTareasTO> wrkComentariosTareas) {
        this.wrkComentariosTareas = wrkComentariosTareas;
    }

    public ArrayList<DocumentosTO> getWrkDocumentos() {
        return wrkDocumentos;
    }

    public void setWrkDocumentos(ArrayList<DocumentosTO> wrkDocumentos) {
        this.wrkDocumentos = wrkDocumentos;
    }

    public String getTxt_localidad() {
        return txt_localidad;
    }

    public void setTxt_localidad(String txt_localidad) {
        this.txt_localidad = txt_localidad;
    }
}
