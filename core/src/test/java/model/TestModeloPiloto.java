package model;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import exception.ExceptionPiloto;

@ExtendWith(MockitoExtension.class)
public class TestModeloPiloto {
	//Test 1: caso normal
	@Test
    public void testPilotoCompleto()throws Exception{
        Piloto piloto = Piloto.factory(UUID.randomUUID(), "Fanco Colapinto", "123456ABC", LocalDate.MIN);
        Assertions.assertNotNull(piloto);
    }

	//Test 2: caso alternativo
    @Test
    public void testValidarExcepciones(){  	
        Assertions.assertThrows(ExceptionPiloto.class, () -> {Piloto.factory(null, "Fanco Colapinto", "123456ABC", LocalDate.EPOCH);});
        Assertions.assertThrows(ExceptionPiloto.class, () -> {Piloto.factory(UUID.randomUUID(), null, "43545324", LocalDate.MIN);});
        Assertions.assertThrows(ExceptionPiloto.class, () -> {Piloto.factory(UUID.randomUUID(), "", "6578786657", LocalDate.MIN);});
        Assertions.assertThrows(ExceptionPiloto.class, () -> {Piloto.factory(null, "DDDDDD", "", LocalDate.MIN);});
        Assertions.assertThrows(ExceptionPiloto.class, () -> {Piloto.factory(UUID.randomUUID(), "MMMMMMMM", null, LocalDate.EPOCH);});
        Assertions.assertThrows(ExceptionPiloto.class, () -> {Piloto.factory(UUID.randomUUID(), null, "123456ABC", null);});
        Assertions.assertThrows(ExceptionPiloto.class, () -> {Piloto.factory(null, "Fanco Colapinto", "123456ABC", null);});
    }

}
