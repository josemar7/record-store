package org.pepo.record.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.NationalityResponseOpenApi;
import org.pepo.record.entity.Nationality;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NationalityEntityOpenApiMapperTest {

    private NationalityEntityOpenApiMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(NationalityEntityOpenApiMapper.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void entityToDto() {
        Nationality nationality = new Nationality();
        nationality.setName("name");
        nationality.setId(1);
        NationalityResponseOpenApi nationalityResponseOpenApi = mapper.nationalityToNationalityResponseOpenApi(nationality);
        assertEquals(nationality.getName(), nationalityResponseOpenApi.getName());
    }
}