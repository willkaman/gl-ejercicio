package cl.globallogic.ejercicio;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.globallogic.ejercicio.controller.EjercicioRestController;
import cl.globallogic.ejercicio.service.EjercicioService;

@SpringBootTest
class EjercicioApplicationTests {

	@Autowired
	private EjercicioRestController ejercicioRestController;

	@Autowired
	EjercicioService ejercicioService;

	@Test
	void contextLoads() {
		//assertThat(ejercicioRestController).
		assertNotNull(ejercicioRestController);
		assertNotNull(ejercicioService);
		
	}

}
