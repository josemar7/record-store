package org.pepo.record.service.format;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.FormatResponseOpenApi;
import org.pepo.record.entity.Format;
import org.pepo.record.mapper.FormatEntityOpenApiMapper;
import org.pepo.record.repository.FormatRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FormatServiceImplTest {

    @InjectMocks
    FormatServiceImpl formatService;

    @Mock
    FormatRepository formatRepository;

    @Mock
    FormatEntityOpenApiMapper formatEntityOpenApiMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(formatRepository.findAll()).thenReturn(new ArrayList<>());
        when(formatEntityOpenApiMapper.entityToOpenApi(ArgumentMatchers.any(Format.class))).thenReturn(new FormatResponseOpenApi());
        Iterable<FormatResponseOpenApi> formatResponseOpenApiIterable = formatService.findAll();
        verify(formatRepository, times(1)).findAll();
        assertNotNull(formatResponseOpenApiIterable);
    }

    @Test
    void save() {
        when(formatRepository.save(ArgumentMatchers.any(Format.class))).thenReturn(new Format());
        when(formatEntityOpenApiMapper.entityToOpenApi(Mockito.any(Format.class))).thenReturn(new FormatResponseOpenApi());
        FormatResponseOpenApi formatResponseOpenApi = formatService.save(new Format());
        verify(formatEntityOpenApiMapper, times(1)).entityToOpenApi(Mockito.any(Format.class));
        verify(formatRepository, times(1)).save(ArgumentMatchers.any(Format.class));
        assertNotNull(formatResponseOpenApi);
    }
}