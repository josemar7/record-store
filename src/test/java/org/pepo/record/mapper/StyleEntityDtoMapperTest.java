package org.pepo.record.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.StyleResponseOpenApi;
import org.pepo.record.entity.Style;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StyleEntityDtoMapperTest {

    private StyleEntityOpenApiMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(StyleEntityOpenApiMapper.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void styleToStyleResponseOpenApi() {
        Style style = new Style();
        style.setName("name");
        style.setId(1);
        StyleResponseOpenApi styleResponseOpenApi = mapper.styleToStyleResponseOpenApi(style);
        assertEquals(style.getName(), styleResponseOpenApi.getName());
    }
}