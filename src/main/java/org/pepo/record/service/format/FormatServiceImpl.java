package org.pepo.record.service.format;

import lombok.AllArgsConstructor;
import org.pepo.record.SwaggerCodgen.model.FormatResponseOpenApi;
import org.pepo.record.entity.Format;
import org.pepo.record.mapper.FormatEntityOpenApiMapper;
import org.pepo.record.repository.FormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FormatServiceImpl implements FormatService {

    @Autowired
    private final FormatEntityOpenApiMapper formatEntityOpenApiMapper;

    @Autowired
    private final FormatRepository formatRepository;

    @Override
    public Iterable<FormatResponseOpenApi> findAll() {
        List<FormatResponseOpenApi> list = new ArrayList<>();
        Iterable<Format> formatIterable = formatRepository.findAll();
        formatIterable.forEach(format -> list.add(formatEntityOpenApiMapper.formatToFormatResponseOpenApi(format)));
        return list;
    }

    @Override
    public FormatResponseOpenApi save(Format format) {
        return formatEntityOpenApiMapper.formatToFormatResponseOpenApi(formatRepository.save(format));
    }
}
