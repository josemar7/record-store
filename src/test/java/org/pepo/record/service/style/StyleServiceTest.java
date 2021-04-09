package org.pepo.record.service.style;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepo.record.dto.StyleDto;
import org.pepo.record.entity.Style;
import org.pepo.record.mapper.StyleEntityDtoMapper;
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
    StyleEntityDtoMapper styleEntityDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(styleRepository.findAll()).thenReturn(new ArrayList<>());
        when(styleEntityDtoMapper.styleToStyleDto(ArgumentMatchers.any(Style.class))).thenReturn(StyleDto.builder().build());
        Iterable<StyleDto> styleDtos = styleService.findAll();
        verify(styleRepository, times(1)).findAll();
        assertNotNull(styleDtos);
    }
}