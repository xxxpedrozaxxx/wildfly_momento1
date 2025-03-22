package beans;

import model.usuario;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UsuarioBean {
    private List<usuario> usuarios;
    private usuario usuarioSeleccionado;

    @PostConstruct
    public void init() {
        // Datos quemados
        usuarios = new ArrayList<>();
        usuarios.add(new usuario(1, "Ana López", "ana@example.com", 28));
        usuarios.add(new usuario(2, "Carlos Ruiz", "carlos@example.com", 35));
        usuarios.add(new usuario(3, "María García", "maria@example.com", 42));
    }

    public List<usuario> getUsuarios() {
        return usuarios;
    }

    public usuario getUsuarioSeleccionado() {
        String idParam = FacesContext.getCurrentInstance()
                                    .getExternalContext()
                                    .getRequestParameterMap()
                                    .get("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            usuarioSeleccionado = usuarios.stream()
                                          .filter(u -> u.getId() == id)
                                          .findFirst()
                                          .orElse(null);
        }
        return usuarioSeleccionado;
    }
}