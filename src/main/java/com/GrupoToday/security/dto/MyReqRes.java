package com.GrupoToday.security.dto;

import com.GrupoToday.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyReqRes {

    private int statusCode;
    private String error;
    private String token;
    private String expirationTime;
    private String name;
    private String email;
    private String role;
    private String password;
    private Usuario usuario;
}
