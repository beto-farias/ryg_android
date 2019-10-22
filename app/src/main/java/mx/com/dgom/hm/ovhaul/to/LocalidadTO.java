package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;
import java.util.ArrayList;

public class LocalidadTO implements Serializable {
    private int id_localidad;
    private int id_usuario;
    private String txt_tag_estatus_tracker;
    private String cms;
    private String txt_token;
    private String txt_nombre;
    private String txt_arrendador;
    private String txt_beneficiario;
    private String txt_calle;
    private String txt_colonia;
    private String texto_colonia;
    private String txt_municipio;
    private String txt_cp;
    private String txt_antecedentes;
    private double num_renta_actual;
    private double num_incremento_autorizado;
    private double num_pretencion_renta;
    private double num_incremento_cliente;
    private double num_pretencion_renta_cliente;
    private String fch_vencimiento_contratro;
    private String fch_creacion;
    private String fch_asignacion;
    private int b_problemas_acceso;
    private int b_archivada;
    private String txt_contacto;
    private String texto_estado;
    private String txt_frecuencia;
    private StatusLocalidadTO bStatusLocalidad;
    private MonedaTO moneda;
    private EstadoTO estado;
    private ArrayList<TareasTO> wrkTareas;
    private ArrayList<EntEstatusTO> entEstatuses;
    private ArrayList<UserTO> usuariosDirectores;
    private UserTO usuarioResponsable;


    public int getId_localidad() {
        return id_localidad;
    }

    public void setId_localidad(int id_localidad) {
        this.id_localidad = id_localidad;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTxt_tag_estatus_tracker() {
        return txt_tag_estatus_tracker;
    }

    public void setTxt_tag_estatus_tracker(String txt_tag_estatus_tracker) {
        this.txt_tag_estatus_tracker = txt_tag_estatus_tracker;
    }

    public String getCms() {
        return cms;
    }

    public void setCms(String cms) {
        this.cms = cms;
    }

    public String getTxt_token() {
        return txt_token;
    }

    public void setTxt_token(String txt_token) {
        this.txt_token = txt_token;
    }

    public String getTxt_nombre() {
        return txt_nombre;
    }

    public void setTxt_nombre(String txt_nombre) {
        this.txt_nombre = txt_nombre;
    }

    public String getTxt_arrendador() {
        return txt_arrendador;
    }

    public void setTxt_arrendador(String txt_arrendador) {
        this.txt_arrendador = txt_arrendador;
    }

    public String getTxt_beneficiario() {
        return txt_beneficiario;
    }

    public void setTxt_beneficiario(String txt_beneficiario) {
        this.txt_beneficiario = txt_beneficiario;
    }

    public String getTxt_calle() {
        return txt_calle;
    }

    public void setTxt_calle(String txt_calle) {
        this.txt_calle = txt_calle;
    }

    public String getTxt_colonia() {
        return txt_colonia;
    }

    public void setTxt_colonia(String txt_colonia) {
        this.txt_colonia = txt_colonia;
    }

    public String getTexto_colonia() {
        return texto_colonia;
    }

    public void setTexto_colonia(String texto_colonia) {
        this.texto_colonia = texto_colonia;
    }

    public String getTxt_municipio() {
        return txt_municipio;
    }

    public void setTxt_municipio(String txt_municipio) {
        this.txt_municipio = txt_municipio;
    }

    public String getTxt_cp() {
        return txt_cp;
    }

    public void setTxt_cp(String txt_cp) {
        this.txt_cp = txt_cp;
    }

    public String getTxt_antecedentes() {
        return txt_antecedentes;
    }

    public void setTxt_antecedentes(String txt_antecedentes) {
        this.txt_antecedentes = txt_antecedentes;
    }

    public double getNum_renta_actual() {
        return num_renta_actual;
    }

    public void setNum_renta_actual(double num_renta_actual) {
        this.num_renta_actual = num_renta_actual;
    }

    public double getNum_incremento_autorizado() {
        return num_incremento_autorizado;
    }

    public void setNum_incremento_autorizado(double num_incremento_autorizado) {
        this.num_incremento_autorizado = num_incremento_autorizado;
    }

    public double getNum_pretencion_renta() {
        return num_pretencion_renta;
    }

    public void setNum_pretencion_renta(double num_pretencion_renta) {
        this.num_pretencion_renta = num_pretencion_renta;
    }

    public double getNum_incremento_cliente() {
        return num_incremento_cliente;
    }

    public void setNum_incremento_cliente(double num_incremento_cliente) {
        this.num_incremento_cliente = num_incremento_cliente;
    }

    public double getNum_pretencion_renta_cliente() {
        return num_pretencion_renta_cliente;
    }

    public void setNum_pretencion_renta_cliente(double num_pretencion_renta_cliente) {
        this.num_pretencion_renta_cliente = num_pretencion_renta_cliente;
    }

    public String getFch_vencimiento_contratro() {
        return fch_vencimiento_contratro;
    }

    public void setFch_vencimiento_contratro(String fch_vencimiento_contratro) {
        this.fch_vencimiento_contratro = fch_vencimiento_contratro;
    }

    public String getFch_creacion() {
        return fch_creacion;
    }

    public void setFch_creacion(String fch_creacion) {
        this.fch_creacion = fch_creacion;
    }

    public String getFch_asignacion() {
        return fch_asignacion;
    }

    public void setFch_asignacion(String fch_asignacion) {
        this.fch_asignacion = fch_asignacion;
    }

    public int getB_problemas_acceso() {
        return b_problemas_acceso;
    }

    public void setB_problemas_acceso(int b_problemas_acceso) {
        this.b_problemas_acceso = b_problemas_acceso;
    }

    public int getB_archivada() {
        return b_archivada;
    }

    public void setB_archivada(int b_archivada) {
        this.b_archivada = b_archivada;
    }

    public String getTxt_contacto() {
        return txt_contacto;
    }

    public void setTxt_contacto(String txt_contacto) {
        this.txt_contacto = txt_contacto;
    }

    public String getTexto_estado() {
        return texto_estado;
    }

    public void setTexto_estado(String texto_estado) {
        this.texto_estado = texto_estado;
    }

    public String getTxt_frecuencia() {
        return txt_frecuencia;
    }

    public void setTxt_frecuencia(String txt_frecuencia) {
        this.txt_frecuencia = txt_frecuencia;
    }

    public StatusLocalidadTO getbStatusLocalidad() {
        return bStatusLocalidad;
    }

    public void setbStatusLocalidad(StatusLocalidadTO bStatusLocalidad) {
        this.bStatusLocalidad = bStatusLocalidad;
    }

    public MonedaTO getMoneda() {
        return moneda;
    }

    public void setMoneda(MonedaTO moneda) {
        this.moneda = moneda;
    }

    public EstadoTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoTO estado) {
        this.estado = estado;
    }

    public ArrayList<TareasTO> getWrkTareas() {
        return wrkTareas;
    }

    public void setWrkTareas(ArrayList<TareasTO> wrkTareas) {
        this.wrkTareas = wrkTareas;
    }

    public ArrayList<EntEstatusTO> getEntEstatuses() {
        return entEstatuses;
    }

    public void setEntEstatuses(ArrayList<EntEstatusTO> entEstatuses) {
        this.entEstatuses = entEstatuses;
    }

    public ArrayList<UserTO> getUsuariosDirectores() {
        return usuariosDirectores;
    }

    public void setUsuariosDirectores(ArrayList<UserTO> usuariosDirectores) {
        this.usuariosDirectores = usuariosDirectores;
    }

    public UserTO getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(UserTO usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }
}
