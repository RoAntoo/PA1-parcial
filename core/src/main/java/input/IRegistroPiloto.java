package input;

import java.time.LocalDate;
import java.util.UUID;

import exception.ExceptionPiloto;

public interface IRegistroPiloto {
	UUID registrarPiloto(UUID id, String nombre, String documento, LocalDate fecha_Nac) throws ExceptionPiloto;
}



