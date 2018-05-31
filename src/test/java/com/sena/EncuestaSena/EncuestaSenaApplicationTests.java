package com.sena.EncuestaSena;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.sena.modelo.Usuario;
import com.sena.service.IUsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncuestaSenaApplicationTests {
	
	@Autowired
	private IUsuarioService servicioUsuario;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void contextLoads() {
		
		Usuario usuario = new Usuario(); 
		
		usuario.setUsuario("admin");
		usuario.setClave(passwordEncoder.encode("adsi"));
		
		servicioUsuario.insertar(usuario);
		
	}

}
