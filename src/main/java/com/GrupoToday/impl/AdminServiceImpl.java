package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.CategoriaDTO;
import com.GrupoToday.impl.mapper.AdminMapper;
import com.GrupoToday.models.Administrador;
import com.GrupoToday.repository.AdminRepository;
import com.GrupoToday.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<CategoriaDTO> listAllAdmin() {
        List<Administrador> admins = adminRepository.findAll();
        return admins
                .stream()
                .map(adminMapper::allAdmin)
                .collect(Collectors.toList());
    }
}
