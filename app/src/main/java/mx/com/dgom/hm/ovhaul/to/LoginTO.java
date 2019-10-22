package mx.com.dgom.hm.ovhaul.to;

import java.io.Serializable;

public class LoginTO<T> implements Serializable {
    private T usuario;
    private String token_seguridad;

    public T getUsuario() {
        return usuario;
    }

    public void setUsuario(T usuario) {
        this.usuario = usuario;
    }

    public String getToken_seguridad() {
        return token_seguridad;
    }

    public void setToken_seguridad(String token_seguridad) {
        this.token_seguridad = token_seguridad;
    }
}
