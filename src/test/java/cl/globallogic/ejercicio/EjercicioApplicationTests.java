package cl.globallogic.ejercicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.globallogic.ejercicio.dao.EjercicioDAO;
import cl.globallogic.ejercicio.model.entity.UsuarioEntity;
import cl.globallogic.ejercicio.repository.UsuarioRepository;
import cl.globallogic.ejercicio.service.EjercicioService;
import cl.globallogic.ejercicio.service.EjercicioServiceImpl;

@ExtendWith(MockitoExtension.class)
class EjercicioApplicationTests {

    @Mock(name = "passwordEncoder")
	PasswordEncoder passwordEncoder;
	
	@Mock(name = "usuarioRepository")
	UsuarioRepository usuarioRepository;

	EjercicioDAO ejercicioDAO;

	EjercicioService ejercicioService;


	private UsuarioEntity usuarioEntityBase;

	@BeforeEach
	void mockInit(){
		ejercicioDAO = new EjercicioDAO(usuarioRepository);
		ejercicioService = new EjercicioServiceImpl(ejercicioDAO, passwordEncoder);

		usuarioEntityBase = UsuarioEntity.builder()
		.id(1L)
		.email("mock@mock.cl")
		.build();

		Mockito.lenient().when(usuarioRepository.findById(1L)).thenReturn(
			Optional.ofNullable(usuarioEntityBase)
		);

		// Mockito.lenient().when(ejercicioService.getUsuario(1L)).thenReturn(
		// 	usuarioEntityBase
		// );
	}

	@Test
	@DisplayName("test servicio EjercicioEntity")
	void contextLoads() {
		UsuarioEntity usuarioEntity = ejercicioService.getUsuario(1L);
		assertNotNull(usuarioEntity);
		assertEquals(usuarioEntity, usuarioEntityBase);
	}

}
