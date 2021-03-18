package org.pepo.record.service.style;

import lombok.AllArgsConstructor;
import org.pepo.record.dto.StyleDto;
import org.pepo.record.entity.Style;
import org.pepo.record.mapper.StyleEntityDtoMapper;
import org.pepo.record.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StyleServiceImpl implements StyleService {

    @Autowired
    private final StyleEntityDtoMapper styleEntityDtoMapper;

    @Autowired
    private final StyleRepository styleRepository;

    @Override
    public Iterable<StyleDto> findAll() {
        List<StyleDto> list = new ArrayList<>();
        Iterable<Style> styleIterable = styleRepository.findAll();
        styleIterable.forEach(style -> list.add(styleEntityDtoMapper.entityToDto(style)));
        return list;
    }

}
