package usecase;

import java.time.LocalDate;
import java.util.UUID;

import exception.ExceptionPiloto;
import input.IRegistroPiloto;
import model.Piloto;
import output.IRepositorioPiloto;

public class CasoUsoRegistrarPiloto implements IRegistroPiloto{
	private final IRepositorioPiloto rep;

	public CasoUsoRegistrarPiloto(IRepositorioPiloto rep) {
		this.rep = rep;
	}
	
	@Override
	public UUID registrarPiloto(UUID id, String nombre, String documento, LocalDate fecha_Nac) throws ExceptionPiloto {
		Piloto piloto = Piloto.factory(id, nombre, documento, fecha_Nac);
		
		if (rep.validarPiloto(piloto.getDocumento())) {
			throw new ExceptionPiloto("ERROR: El piloto que intenta registrar ya existe cargado");
		}
		
		if (!rep.guardarPiloto(piloto)) {
			throw new ExceptionPiloto("ERROR: No se pudo guardar al piloto");
		}
		
		return piloto.getId();
	}
	
	
	
	
}
