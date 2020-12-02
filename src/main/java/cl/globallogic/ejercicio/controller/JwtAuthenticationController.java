package cl.globallogic.ejercicio.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.globallogic.ejercicio.model.dto.LoginRequestBody;
import cl.globallogic.ejercicio.service.AuthUserDetailServiceImpl;
import cl.globallogic.ejercicio.service.EjercicioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class JwtAuthenticationController {

    @Autowired
    EjercicioService ejercicioService;

    @Autowired
    AuthUserDetailServiceImpl authUserDetailServiceImpl;

    //@RequestMapping(value = "/authme", method = RequestMethod.POST)
    @PostMapping("/authme")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestBody loginRequestBody) throws Exception {
        UserDetails userDetails = authUserDetailServiceImpl.loadUserByCredentials(loginRequestBody);

        //return ResponseEntity.ok().build(); 
        //UsuarioEntity ue = ejercicioService.getUsuarioByEmail("juan@rodriguez.org");

        final String token = getJWTToken(userDetails);

        return ResponseEntity.ok(token);
    }

    private String getJWTToken(UserDetails userDetails) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(userDetails.getUsername())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
                //.signWith(SignatureAlgorithm.HS512,secretKey.getBytes()).compact();
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();

		return "Bearer " + token;
	}
}