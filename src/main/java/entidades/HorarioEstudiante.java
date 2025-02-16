package entidades;

import java.sql.Date;

public class HorarioEstudiante {
	
	    private int estudianteID;

	    private int horarioID;
	    
	    private String nombre;
	    
	    private String apellido;
	    

	    public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public int getHorarioID() {
			return horarioID;
		}

		public void setHorarioID(int horarioID) {
			this.horarioID = horarioID;
		}

		public int getEstudianteID() {
	        return estudianteID;
	    }

	    public void setEstudianteID(int estudianteID) {
	        this.estudianteID = estudianteID;
	    }

}
