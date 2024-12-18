package com.GrupoToday.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.GrupoToday.DTO.modelsDto.ZapatillasDto;
import com.GrupoToday.impl.mapper.ZapatillaMapper;
import com.GrupoToday.models.Zapatilla;
import com.GrupoToday.repository.ZapatillaRepository;
import com.GrupoToday.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ZapatillaServiceImplTest {

    @Mock
    private ZapatillaRepository zapatillaRepository;

    @Mock
    private FileService fileService;

    @Mock
    private ZapatillaMapper zapatillaMapper;

    @InjectMocks
    private ZapatillaServiceImpl zapatillaService;

    private ZapatillasDto sampleZapatillaDto;
    private Zapatilla sampleZapatilla;

    @BeforeEach
    void setUp() {
        sampleZapatillaDto = new ZapatillasDto();
        sampleZapatillaDto.setIdZapatilla(1);
        sampleZapatillaDto.setNombreZapatilla("Zapatilla de prueba");
        sampleZapatillaDto.setDescripcionZapatilla("Descripción de prueba");
        sampleZapatillaDto.setPrecioZapatilla("120.0");
        sampleZapatillaDto.setStockZapatilla("10");

        sampleZapatilla = new Zapatilla();
        sampleZapatilla.setId(1);
        sampleZapatilla.setNombre("Zapatilla de prueba");
        sampleZapatilla.setDescripcion("Descripción de prueba");
        sampleZapatilla.setPrecio("120.0");
        sampleZapatilla.setStock("10");
    }

    @Test
    void agregarZapatilla_shouldReturnZapatillaDto() throws IOException {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getOriginalFilename()).thenReturn("imagen.jpg");
        when(fileService.subirArchivo(anyString(), eq(mockFile))).thenReturn("imagen.jpg");
        when(zapatillaMapper.zapatillaMapper(eq(sampleZapatillaDto), any(Zapatilla.class))).thenReturn(sampleZapatilla);
        when(zapatillaRepository.save(sampleZapatilla)).thenReturn(sampleZapatilla);
        when(zapatillaMapper.retornoZapatillaDto(sampleZapatilla)).thenReturn(sampleZapatillaDto);

        ZapatillasDto result = zapatillaService.agregarZapatilla(sampleZapatillaDto, mockFile);

        assertNotNull(result);
        assertEquals("Zapatilla de prueba", result.getNombreZapatilla());
        verify(fileService).subirArchivo(anyString(), eq(mockFile));
        verify(zapatillaRepository).save(any(Zapatilla.class));
    }

    @Test
    void buscarIdZapatilla_shouldReturnZapatillaDtoWhenFound() {
        when(zapatillaRepository.findById(1)).thenReturn(Optional.of(sampleZapatilla));
        when(zapatillaMapper.listAllZapatillas(sampleZapatilla)).thenReturn(sampleZapatillaDto);

        ZapatillasDto result = zapatillaService.buscarIdZapatilla(1);

        assertNotNull(result);
        assertEquals("Zapatilla de prueba", result.getNombreZapatilla());
        verify(zapatillaRepository).findById(1);
    }

    @Test
    void deleteZapatilla_shouldCallDeleteRepositoryWhenFound() {
        when(zapatillaRepository.findById(1)).thenReturn(Optional.of(sampleZapatilla));

        zapatillaService.deleteZapatilla(1);

        verify(zapatillaRepository).deleteById(1);
    }

    @Test
    void deleteZapatilla_shouldThrowExceptionWhenNotFound() {
        when(zapatillaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> zapatillaService.deleteZapatilla(1));
    }
}