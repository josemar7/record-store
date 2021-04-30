package org.pepo.record.service.record;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.pepo.record.SwaggerCodgen.model.RecordPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.RecordResponseOpenApi;
import org.pepo.record.entity.Record;
import org.pepo.record.mapper.RecordEntityOpenApiMapper;
import org.pepo.record.repository.record.RecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecordServiceImplTest {

    @InjectMocks
    RecordServiceImpl recordService;

    @Mock
    RecordRepository recordRepository;

    @Mock
    RecordEntityOpenApiMapper recordEntityOpenApiMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(recordRepository.findAll()).thenReturn(new ArrayList<>());
        when(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        Iterable<RecordResponseOpenApi> recordResponseOpenApiIterable = recordService.findAll();
        verify(recordRepository, times(1)).findAll();
        assertNotNull(recordResponseOpenApiIterable);
    }

    @Test
    void findAllPaginated() {
        when(recordRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(Page.empty());
        when(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        Iterable<RecordResponseOpenApi> recordResponseOpenApiIterable = recordService.findAll(0, 3);
        verify(recordRepository, times(1)).findAll(Mockito.any(PageRequest.class));
        assertNotNull(recordResponseOpenApiIterable);
    }

    @Test
    void findById() {
        when(recordRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Record()));
        when(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        RecordResponseOpenApi recordResponseOpenApi =  recordService.findById(1);
        verify(recordRepository, times(1)).findById(Mockito.anyInt());
        assertNotNull(recordResponseOpenApi);
    }

    @Test
    void save() {
        when(recordRepository.save(ArgumentMatchers.any(Record.class))).thenReturn(new Record());
        when(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        RecordResponseOpenApi recordResponseOpenApi = recordService.save(new Record());
        verify(recordEntityOpenApiMapper, times(1)).recordToRecordResponseOpenApi(Mockito.any(Record.class));
        verify(recordRepository, times(1)).save(ArgumentMatchers.any(Record.class));
        assertNotNull(recordResponseOpenApi);
    }

    @Test
    void update() {
        when(recordRepository.save(ArgumentMatchers.any(Record.class))).thenReturn(new Record());
        when(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        RecordResponseOpenApi recordResponseOpenApi = recordService.update(new Record(), 1);
        verify(recordEntityOpenApiMapper, times(1)).recordToRecordResponseOpenApi(Mockito.any(Record.class));
        verify(recordRepository, times(1)).save(ArgumentMatchers.any(Record.class));
        assertNotNull(recordResponseOpenApi);
    }

    @Test
    void delete() {
        doNothing().when(recordRepository).delete(ArgumentMatchers.any(Record.class));
        recordService.delete(1);
    }

    @Test
    void filteredRecords() {
        when(recordRepository.findFilteredRecords(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(Pageable.class)))
                .thenReturn(Page.empty());
        when(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        RecordPagedResponseOpenApi recordPagedResponseOpenApi = recordService.filteredRecords("name", "artist", "format", "style", 0, 5);
        verify(recordRepository, times(1))
                .findFilteredRecords(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(Pageable.class));
        assertNotNull(recordPagedResponseOpenApi);
    }

    @Test
    void findAllPaged() {
        when(recordRepository.findAll(Mockito.any(Pageable.class))).thenReturn(Page.empty());
        when(recordEntityOpenApiMapper.recordToRecordResponseOpenApi(Mockito.any(Record.class))).thenReturn(new RecordResponseOpenApi());
        RecordPagedResponseOpenApi recordPagedResponseOpenApi = recordService.findAllPaged(0, 5);
        verify(recordRepository, times(1)).findAll(Mockito.any(Pageable.class));
        assertNotNull(recordPagedResponseOpenApi);
    }
}