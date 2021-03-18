package org.pepo.record.service.nationality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepo.record.dto.NationalityDto;
import org.pepo.record.entity.Nationality;
import org.pepo.record.mapper.NationalityEntityDtoMapper;
import org.pepo.record.repository.NationalityRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NationalityServiceTest {

    @InjectMocks
    NationalityServiceImpl nationalityService;

    @Mock
    NationalityRepository nationalityRepository;

    @Mock
    NationalityEntityDtoMapper nationalityEntityDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(nationalityRepository.findAll()).thenReturn(new ArrayList<>());
        when(nationalityEntityDtoMapper.entityToDto(ArgumentMatchers.any(Nationality.class))).thenReturn(NationalityDto.builder().build());
        Iterable<NationalityDto> nationalityDtos = nationalityService.findAll();
        verify(nationalityRepository, times(1)).findAll();
        assertNotNull(nationalityDtos);
    }

}