package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserTO implements Serializable {
    private int id_usuario;
    private String txt_auth_item;
    private String txt_token;
    private String txt_imagen;
    private String txt_username;
    private String txt_apellido_paterno;
    private String txt_email;
    private String fch_creacion;
    private String fch_actualizacion;
    private int id_status;
    private String txt_push_token;
    private ArrayList<UserTO> usuariosHijos;
    private EstatusTO status;
    private int id_master_cliente;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTxt_auth_item() {
        return txt_auth_item;
    }

    public void setTxt_auth_item(String txt_auth_item) {
        this.txt_auth_item = txt_auth_item;
    }

    public String getTxt_token() {
        return txt_token;
    }

    public void setTxt_token(String txt_token) {
        this.txt_token = txt_token;
    }

    public String getTxt_imagen() {
        return txt_imagen;
    }

    public void setTxt_imagen(String txt_imagen) {
        this.txt_imagen = txt_imagen;
    }

    public String getTxt_username() {
        return txt_username;
    }

    public void setTxt_username(String txt_username) {
        this.txt_username = txt_username;
    }

    public String getTxt_apellido_paterno() {
        return txt_apellido_paterno;
    }

    public void setTxt_apellido_paterno(String txt_apellido_paterno) {
        this.txt_apellido_paterno = txt_apellido_paterno;
    }

    public String getTxt_email() {
        return txt_email;
    }

    public void setTxt_email(String txt_email) {
        this.txt_email = txt_email;
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

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getTxt_push_token() {
        return txt_push_token;
    }

    public void setTxt_push_token(String txt_push_token) {
        this.txt_push_token = txt_push_token;
    }

    public ArrayList<UserTO> getUsuariosHijos() {
        return usuariosHijos;
    }

    public void setUsuariosHijos(ArrayList<UserTO> usuariosHijos) {
        this.usuariosHijos = usuariosHijos;
    }

    public EstatusTO getStatus() {
        return status;
    }

    public void setStatus(EstatusTO status) {
        this.status = status;
    }

    public int getId_master_cliente() {
        return id_master_cliente;
    }

    public void setId_master_cliente(int id_master_cliente) {
        this.id_master_cliente = id_master_cliente;
    }
}
