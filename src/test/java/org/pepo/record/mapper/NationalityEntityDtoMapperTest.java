package org.pepo.record.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.pepo.record.dto.NationalityDto;
import org.pepo.record.entity.Nationality;

import static org.junit.jupiter.api.Assertions.*;

class NationalityEntityDtoMapperTest {

    private NationalityEntityDtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(NationalityEntityDtoMapper.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void entityToDto() {
        Nationality nationality = new Nationality();
        nationality.setName("name");
        nationality.setId(1);
        NationalityDto nationalityDto = mapper.nationalityToNationalityDto(nationality);
        assertEquals(nationality.getName(), nationalityDto.getName());
    }
}