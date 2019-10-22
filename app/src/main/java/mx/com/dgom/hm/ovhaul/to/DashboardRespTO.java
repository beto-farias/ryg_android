package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class DashboardRespTO implements Serializable {
    private int localidades_registradas;
    private int consignaciones_proximas;
    private int casos_audiencia;
    private int tareas_pendientes;
    private int mis_casos_audiencia;
    private int mis_tareas_pendientes;


    public int getLocalidades_registradas() {
        return localidades_registradas;
    }

    public void setLocalidades_registradas(int localidades_registradas) {
        this.localidades_registradas = localidades_registradas;
    }

    public int getConsignaciones_proximas() {
        return consignaciones_proximas;
    }

    public void setConsignaciones_proximas(int consignaciones_proximas) {
        this.consignaciones_proximas = consignaciones_proximas;
    }

    public int getCasos_audiencia() {
        return casos_audiencia;
    }

    public void setCasos_audiencia(int casos_audiencia) {
        this.casos_audiencia = casos_audiencia;
    }

    public int getTareas_pendientes() {
        return tareas_pendientes;
    }

    public void setTareas_pendientes(int tareas_pendientes) {
        this.tareas_pendientes = tareas_pendientes;
    }

    public int getMis_casos_audiencia() {
        return mis_casos_audiencia;
    }

    public void setMis_casos_audiencia(int mis_casos_audiencia) {
        this.mis_casos_audiencia = mis_casos_audiencia;
    }

    public int getMis_tareas_pendientes() {
        return mis_tareas_pendientes;
    }

    public void setMis_tareas_pendientes(int mis_tareas_pendientes) {
        this.mis_tareas_pendientes = mis_tareas_pendientes;
    }
}
