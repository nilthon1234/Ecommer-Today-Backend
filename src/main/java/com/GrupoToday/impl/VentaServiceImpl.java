package com.GrupoToday.impl;

import com.GrupoToday.DTO.modelsDto.DetalleDto;
import com.GrupoToday.DTO.response.VentaDto;
import com.GrupoToday.models.*;
import com.GrupoToday.repository.BoletaRepository;
import com.GrupoToday.repository.ClienteRepository;
import com.GrupoToday.repository.DetalleBoletaRepository;
import com.GrupoToday.repository.ZapatillaRepository;
import com.GrupoToday.service.VentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    private final DetalleBoletaRepository detalleBoletaRepository;
    private final BoletaRepository boletaRepository;
    private final ClienteRepository clienteRepository;
    private final ZapatillaRepository zapatillaRepository;

    public VentaServiceImpl(DetalleBoletaRepository detalleBoletaRepository,
                            BoletaRepository boletaRepository,
                            ClienteRepository clienteRepository,
                            ZapatillaRepository zapatillaRepository) {
        this.detalleBoletaRepository = detalleBoletaRepository;
        this.boletaRepository = boletaRepository;
        this.clienteRepository = clienteRepository;
        this.zapatillaRepository = zapatillaRepository;
    }

    @Override
    @Transactional
    public void registrarVenta(VentaDto ventaDto, List<DetalleDto> detalles) {
        // Save Cliente
        Cliente cliente = new Cliente();
        cliente.setNombre(ventaDto.getNombreCliente());
        cliente.setApellido(ventaDto.getApellidoCliente());
        cliente.setGmail(ventaDto.getGmailCliente());
        cliente.setTelefono(ventaDto.getTelefonoCliente());
        cliente.setDireccion(ventaDto.getDireccionCliente());

        Departamento departamento = new Departamento();
        departamento.setId(ventaDto.getIdDepartamento());
        cliente.setDepartamento(departamento);

        Cliente saveCliente = clienteRepository.save(cliente);

        // Save Boleta
        Boleta boleta = new Boleta();
        boleta.setCliente(saveCliente);
        Boleta saveBoleta = boletaRepository.save(boleta);

        // Save DetalleBoleta entries
        for (DetalleDto detalle : detalles) {
            DetalleBoleta detalleBoleta = new DetalleBoleta();

            Zapatilla zapatilla = zapatillaRepository.findById(detalle.getIdZapatilla())
                    .orElseThrow(() -> new RuntimeException("Zapatilla Id NO Encontrada"));

            // Set up composite key
            DetalleBoletaId detalleBoletaId = new DetalleBoletaId();
            detalleBoletaId.setBoletaId(saveBoleta.getId());
            detalleBoletaId.setZapatillaId(zapatilla.getId());

            detalleBoleta.setId(detalleBoletaId);  // Set composite key
            detalleBoleta.setZapatilla(zapatilla);
            detalleBoleta.setBoleta(saveBoleta);
            detalleBoleta.setPrecio(detalle.getPrecioZapatilla());
            detalleBoleta.setCantidad(detalle.getCantidad());

            double subTotal = detalle.getPrecioZapatilla() * detalle.getCantidad();
            detalleBoleta.setSubTotal(subTotal);

            detalleBoletaRepository.save(detalleBoleta);
        }
    }
}
