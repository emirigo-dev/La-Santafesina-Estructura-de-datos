package tpp2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestRecu {

	Empresa e;
	@Before
	public void setUp() throws Exception {
		e= new Empresa("30112223334","La Santafesina");
		e.agregarDestino("Cordoba", 350);
		e.agregarDestino("Corrientes", 900);
		e.agregarDestino("Parana", 30);
	}
	

	@Test
	public void transporteReemplazo() throws Exception {
		e.agregarDeposito(40000, false, false);
		e.agregarFlete("HOLA33", 4000, 350, 10, 1, 800);
		e.agregarFlete("CHAU44", 4000, 350, 10, 1, 800);
		e.asignarDestino("HOLA33", "Parana");
		e.incorporarPaquete("Parana", 100, 5, false);
		e.incorporarPaquete("Parana", 400, 12, false);
		e.incorporarPaquete("Parana", 50, 8, false);
		e.cargarTransporte("HOLA33");
		e.iniciarViaje("HOLA33");
		e.reemplazoTransporte("HOLA33");

		assertEquals(false,e.dameTransporte("HOLA33").getViajando());
		assertEquals(true,e.dameTransporte("CHAU44").getViajando());
		assertEquals(0,e.dameTransporte("HOLA33").getCargaActual(),0);
		assertEquals(550,e.dameTransporte("CHAU44").getCargaActual(),0);
//		assertEquals(paquetesTransporteNuevo, 3, 0);
		System.out.println(e.toString());
	}


}
