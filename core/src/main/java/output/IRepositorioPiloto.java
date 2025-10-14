package output;

import model.Piloto;

public interface IRepositorioPiloto {
	boolean validarPiloto(String documento);
	boolean guardarPiloto(Piloto piloto);
}
