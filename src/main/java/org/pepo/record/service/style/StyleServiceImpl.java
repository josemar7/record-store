package org.pepo.record.service.style;

import lombok.AllArgsConstructor;
import org.pepo.record.SwaggerCodgen.model.StyleResponseOpenApi;
import org.pepo.record.entity.Style;
import org.pepo.record.mapper.StyleEntityOpenApiMapper;
import org.pepo.record.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StyleServiceImpl implements StyleService {

    @Autowired
    private final StyleEntityOpenApiMapper styleEntityOpenApiMapper;

    @Autowired
    private final StyleRepository styleRepository;

    @Override
    public Iterable<StyleResponseOpenApi> findAll() {
        List<StyleResponseOpenApi> list = new ArrayList<>();
        Iterable<Style> styleIterable = styleRepository.findAll();
        styleIterable.forEach(style -> list.add(styleEntityOpenApiMapper.styleToStyleResponseOpenApi(style)));
        return list;
    }

    @Override
    public StyleResponseOpenApi save(Style style) {
        return styleEntityOpenApiMapper.styleToStyleResponseOpenApi(styleRepository.save(style));
    }
}
