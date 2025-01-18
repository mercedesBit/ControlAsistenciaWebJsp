package entidades;

public class Rol {
    private int roleID;
    private String nombreRole;

    // Getters
    public int getRoleID() {
        return roleID;
    }

    public String getNombreRole() {
        return nombreRole;
    }

    // Setters
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setNombreRole(String nombreRole) {
        this.nombreRole = nombreRole;
    }
}

