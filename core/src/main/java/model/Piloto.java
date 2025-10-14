package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.UUID;

import exception.ExceptionPiloto;

public class Piloto {

    private UUID id;
    private String nombre;
    private String documento;
    private LocalDate fecha_nacimiento;

    private Piloto(UUID id, String nombre, String documento, LocalDate fecha_nacimiento){
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public static Piloto factory(UUID id, String nombre, String documento, LocalDate fecha_nacimiento) 
    		throws ExceptionPiloto {
    	//Lanzar Excepciones
        if (id == null ) throw new ExceptionPiloto("ERROR: El campo del ID es obligatorio");
        
        if (nombre == null || nombre.isBlank()) throw new ExceptionPiloto("ERROR: el campo del nombre es obligatorio");
        
        if (documento == null || documento.isBlank()) throw new ExceptionPiloto("ERROR: el campo del documento es obligatorio");
        
        if (fecha_nacimiento == null ) throw new ExceptionPiloto("ERROR: el campo de la fecha de nacimiento es obligatoria");

        //Calcular edad 
        int edad = Period.between(fecha_nacimiento, LocalDate.now()).getYears();
        
        if (edad < 18) throw new ExceptionPiloto("ERROR: El piloto debe ser mayor de edad");
        
        return new Piloto(id, nombre, documento, fecha_nacimiento);
    }

    public UUID getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDocumento() {
        return documento;
    }
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

	@Override
	public int hashCode() {
		return Objects.hash(documento, fecha_nacimiento, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piloto other = (Piloto) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(fecha_nacimiento, other.fecha_nacimiento)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	
    
}