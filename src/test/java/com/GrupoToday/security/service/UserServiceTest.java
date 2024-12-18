package com.GrupoToday.security.service;


import com.GrupoToday.security.dto.MyReqRes;
import com.GrupoToday.security.entity.Usuario;
import com.GrupoToday.security.repository.IUsuarioRepository;
import com.GrupoToday.security.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUsuarioRepository iUsuarioRepository;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testLoginSuccess() {
        // Datos de prueba
        String email = "test@example.com";
        String password = "password123";
        MyReqRes request = new MyReqRes();
        request.setEmail(email);
        request.setPassword(password);

        // Usuario simulado
        Usuario user = new Usuario();
        user.setId(1);
        user.setEmail(email);
        user.setPassword(password);

        // Comportamiento simulado de los mocks
        when(iUsuarioRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(jwtUtils.generateToken(user)).thenReturn("mock-jwt-token");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);  // Simula autenticación exitosa sin error

        // resultado
        MyReqRes response = userService.login(request);

        // Verificación de resultados
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getToken());
        assertEquals("mock-jwt-token", response.getToken());
        assertEquals("24 Hrs", response.getExpirationTime());

        // Verifica que las interacciones con los mocks hayan sucedido
        verify(iUsuarioRepository).findByEmail(email);
        verify(jwtUtils).generateToken(user);
    }

    @Test
    void testLoginFailure_UserNotFound() {
        // Datos de prueba
        String email = "test@example.com";
        String password = "password123";
        MyReqRes request = new MyReqRes();
        request.setEmail(email);
        request.setPassword(password);

        // Comportamiento simulado de los mocks
        when(iUsuarioRepository.findByEmail(email)).thenReturn(Optional.empty()); // Usuario no encontrado

        // Llamada al método a probar
        MyReqRes response = userService.login(request);

        // Verificación de resultados
        assertEquals(500, response.getStatusCode());
        assertNotNull(response.getError());
        assertTrue(response.getError().contains("No value present"));

        // Verifica que la interacción con el repositorio haya sucedido
        verify(iUsuarioRepository).findByEmail(email);
    }

    @Test
    void testLoginFailure_AuthenticationError() {
        // Datos de prueba
        String email = "test@example.com";
        String password = "wrongpassword";
        MyReqRes request = new MyReqRes();
        request.setEmail(email);
        request.setPassword(password);

        // Comportamiento simulado de los mocks
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

        // Llamada al método a probar
        MyReqRes response = userService.login(request);

        // Verificación de resultados
        assertEquals(500, response.getStatusCode());
        assertNotNull(response.getError());
        assertTrue(response.getError().contains("Authentication failed"));

        // Verifica que la interacción con los mocks haya sucedido
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

}