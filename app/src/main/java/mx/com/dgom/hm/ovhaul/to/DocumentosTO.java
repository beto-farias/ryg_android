package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class DocumentosTO implements Serializable {
    private int id_documento;
     private String uuid;
     private int id_tarea;
     private int id_usuario;
     private String txt_nombre;
     private String txt_url_archivo;
     private String fch_creacion;
     private String usuarioNombre;


    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getTxt_nombre() {
        return txt_nombre;
    }

    public void setTxt_nombre(String txt_nombre) {
        this.txt_nombre = txt_nombre;
    }

    public String getTxt_url_archivo() {
        return txt_url_archivo;
    }

    public void setTxt_url_archivo(String txt_url_archivo) {
        this.txt_url_archivo = txt_url_archivo;
    }

    public String getFch_creacion() {
        return fch_creacion;
    }

    public void setFch_creacion(String fch_creacion) {
        this.fch_creacion = fch_creacion;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
}
