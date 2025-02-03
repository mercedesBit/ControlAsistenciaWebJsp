package entidades;

public class Usuario {
    private int usuarioID;
    private String nombreUsuario;
    private String contrasena;
    private int roleID;
    private String estado;

    // Getters
    public int getUsuarioID() {
        return usuarioID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getRoleID() {
        return roleID;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}