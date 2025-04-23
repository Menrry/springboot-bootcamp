package seguranca.springboot_bootcamp;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorSeguro {

    @GetMapping("/publico")
    public String publico() {
        return "Esta es una página pública.";
    }

    @GetMapping("/usuario")
    @Secured("ROLE_USER")
    public String paginaUsuario() {
        return "Esta es una página solo para usuarios.";
    }

    @GetMapping("/admin/secured")
    @Secured("ROLE_ADMIN")
    public String paginaAdminSecured() {
        return "Esta es una página solo para administradores (usando @Secured).";
    }

    @GetMapping("/admin/preauthorize")
    @PreAuthorize("hasRole('ADMIN')")
    public String paginaAdminPreAuthorize() {
        return "Esta es una página solo para administradores (usando @PreAuthorize).";
    }

    @GetMapping("/admin/rolesallowed")
    @RolesAllowed("ADMIN")
    public String paginaAdminRolesAllowed() {
        return "Esta es una página solo para administradores (usando @RolesAllowed).";
    }

    @GetMapping("/ambos-roles")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public String paginaAmbosRoles() {
        return "Esta página requiere el rol de administrador o usuario.";
    }

    @GetMapping("/usuario-o-admin-spel")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String paginaUsuarioOAdminSpEL() {
        return "Esta página requiere el rol de usuario o administrador (usando SpEL).";
    }
}