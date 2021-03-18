package org.pepo.record.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.pepo.record.dto.StyleDto;
import org.pepo.record.entity.Style;

import static org.junit.jupiter.api.Assertions.*;

class StyleEntityDtoMapperTest {

    private StyleEntityDtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(StyleEntityDtoMapper.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void entityToDto() {
        Style style = new Style();
        style.setName("name");
        style.setId(1);
        StyleDto styleDto = mapper.entityToDto(style);
        assertEquals(style.getName(), styleDto.getName());
    }
}