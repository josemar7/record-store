package org.pepo.record.service.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.StyleResponseOpenApi;
import org.pepo.record.entity.Style;
import org.pepo.record.mapper.StyleEntityOpenApiMapper;
import org.pepo.record.repository.StyleRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StyleServiceTest {

    @InjectMocks
    StyleServiceImpl styleService;

    @Mock
    StyleRepository styleRepository;

    @Mock
    StyleEntityOpenApiMapper styleEntityOpenApiMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(styleRepository.findAll()).thenReturn(new ArrayList<>());
        when(styleEntityOpenApiMapper.styleToStyleResponseOpenApi(ArgumentMatchers.any(Style.class))).thenReturn(new StyleResponseOpenApi());
        Iterable<StyleResponseOpenApi> styleResponseOpenApiIterable = styleService.findAll();
        verify(styleRepository, times(1)).findAll();
        assertNotNull(styleResponseOpenApiIterable);
    }
}