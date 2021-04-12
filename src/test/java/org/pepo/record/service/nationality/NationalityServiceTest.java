package org.pepo.record.service.nationality;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.NationalityResponseOpenApi;
import org.pepo.record.entity.Nationality;
import org.pepo.record.mapper.NationalityEntityOpenApiMapper;
import org.pepo.record.repository.NationalityRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class NationalityServiceTest {

    @InjectMocks
    NationalityServiceImpl nationalityService;

    @Mock
    NationalityRepository nationalityRepository;

    @Mock
    NationalityEntityOpenApiMapper nationalityEntityOpenApiMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(nationalityRepository.findAll()).thenReturn(new ArrayList<>());
        when(nationalityEntityOpenApiMapper.nationalityToNationalityResponseOpenApi(ArgumentMatchers.any(Nationality.class))).thenReturn(new NationalityResponseOpenApi());
        Iterable<NationalityResponseOpenApi> nationalityDtos = nationalityService.findAll();
        verify(nationalityRepository, times(1)).findAll();
        assertNotNull(nationalityDtos);
    }
}