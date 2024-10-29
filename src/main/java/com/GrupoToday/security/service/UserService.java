package com.GrupoToday.security.service;

import com.GrupoToday.security.dto.MyReqRes;
import com.GrupoToday.security.entity.Usuario;
import com.GrupoToday.security.repository.IUsuarioRepository;
import com.GrupoToday.security.utils.JwtUtils;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public MyReqRes signup (MyReqRes registrationRequest)throws Exception{
        MyReqRes myDto = new MyReqRes();
        try {
            Usuario user = new Usuario();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());
            Usuario userResult = iUsuarioRepository.save(user);
            if (userResult != null && userResult.getId()>0){
                myDto.setUsuario(userResult);
                myDto.setStatusCode(200);
            }
        }catch (Exception e){
            myDto.setStatusCode(500);
            myDto.setError(e.getMessage());
        }
        return myDto;

    }
    public MyReqRes login(MyReqRes myReqRes){
        MyReqRes response = new MyReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(myReqRes.getEmail(),myReqRes.getPassword()));
            var user = iUsuarioRepository.findByEmail(myReqRes.getEmail()).orElseThrow();
            System.out.println("USER is: " + user);
            var jwt = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setExpirationTime("24 Hrs");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
}
