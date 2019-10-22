package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class DashboardTO implements Serializable {
    private String descripcion;
    private int cantidad;


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
