package usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import exception.ExceptionPiloto;
import model.Piloto;
import output.IRepositorioPiloto;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)

public class TestRegistroPiloto {
	
	//Mock, interfaz, regPi
    @Mock
    private IRepositorioPiloto repo;
    private CasoUsoRegistrarPiloto registrarPiloto;
    private UUID id = UUID.randomUUID();

    @BeforeEach
    public void setUp(){
        registrarPiloto = new CasoUsoRegistrarPiloto(repo);
    }

    //Test 1: caso normal (se registra correctamente)
    @Test
    public void testRegistrarPiloto() throws ExceptionPiloto {
        Piloto piloto = Piloto.factory(id, "Franco Armani", "12121212", LocalDate.EPOCH);
        when(repo.validarPiloto(piloto.getDocumento())).thenReturn(false);
        when(repo.guardarPiloto(piloto)).thenReturn(true);
        
        Assertions.assertEquals(id, registrarPiloto.registrarPiloto(piloto.getId(), piloto.getNombre(), piloto.getDocumento(), piloto.getFecha_nacimiento()));
    }

    //Test 2: caso alternativo: mismos documentos, diferentes edades
    @Test
    public void testErrorRegistro() throws ExceptionPiloto {
        Piloto piloto = Piloto.factory(id, "Franco Armani", "12121212", LocalDate.EPOCH);
        when(repo.validarPiloto(piloto.getDocumento())).thenReturn(true);
        
        Assertions.assertThrows(ExceptionPiloto.class, () -> registrarPiloto.registrarPiloto(piloto.getId(), piloto.getNombre(), piloto.getDocumento(), piloto.getFecha_nacimiento()));
    }
    
    //Test 3: caso alternativo: mismo documento, diferentes nombres
    @Test
    public void testErrorGuardado() throws ExceptionPiloto {
        Piloto piloto = Piloto.factory(id, "Elvio Lados", "12121212", LocalDate.EPOCH);
        when(repo.validarPiloto(piloto.getDocumento())).thenReturn(false);
        when(repo.guardarPiloto(piloto)).thenReturn(false);
        
        Assertions.assertThrows(ExceptionPiloto.class, () -> registrarPiloto.registrarPiloto(piloto.getId(), piloto.getNombre(), piloto.getDocumento(), piloto.getFecha_nacimiento()));
    }

}

