package org.pepo.record.service.artist;

import lombok.AllArgsConstructor;
import org.pepo.record.SwaggerCodgen.model.ArtistPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;
import org.pepo.record.mapper.ArtistEntityOpenApiMapper;
import org.pepo.record.repository.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private final ArtistEntityOpenApiMapper artistEntityOpenApiMapper;

    @Autowired
    private final ArtistRepository artistRepository;

    @Override
    public Iterable<ArtistResponseOpenApi> findAll() {
        List<ArtistResponseOpenApi> list = new ArrayList<>();
        Iterable<Artist> artistIterable = artistRepository.findAll();
        artistIterable.forEach(artist -> list.add(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artist)));
        return list;
    }

    @Override
    public ArtistResponseOpenApi findById(final int id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        return artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artist);
    }

    @Override
    public ArtistResponseOpenApi findByIdSecured(final int id) {
        return artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artistRepository.findByIdSecured(id));
    }

    @Override
    public ArtistResponseOpenApi save(final Artist artist) {
        return artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artistRepository.save(artist));
    }

    @Override
    public void delete(final int id) {
        artistRepository.deleteById(id);
    }

    @Override
    public ArtistResponseOpenApi update(final Artist artist, final int artistId) {
        artist.setId(artistId);
        return artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artistRepository.save(artist));
    }

    @Override
    public ArtistPagedResponseOpenApi findByNameLike(final String name, final Integer page, final Integer size) {
        List<ArtistResponseOpenApi> list = new ArrayList<>();
        ArtistPagedResponseOpenApi responseOpenApi = new ArtistPagedResponseOpenApi();
        Pageable paging = PageRequest.of(page, size);
        Page<Artist> artistPage = artistRepository.findFilterByName(paging, name);
        artistPage.getContent().forEach(artist -> list.add(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artist)));
        responseOpenApi.setTotalElements(artistPage.getTotalElements());
        responseOpenApi.setTotalPages(artistPage.getTotalPages());
        responseOpenApi.setResult(list);
        return responseOpenApi;
    }

    @Override
    public List<ArtistResponseOpenApi> findAll(final Integer page, final Integer size) {
        List<ArtistResponseOpenApi> list = new ArrayList<>();
        List<Artist> artistList;
        if (page == null && size == null) {
            artistList = StreamSupport.stream(artistRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());
        }
        else {
            Pageable paging = PageRequest.of(page, size);
            artistList = artistRepository.findAll(paging).getContent();
        }
        artistList.forEach(artist -> list.add(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artist)));
        return list;
    }

    @Override
    public ArtistPagedResponseOpenApi findAllPaged(final Integer page, final Integer size) {
        List<ArtistResponseOpenApi> list = new ArrayList<>();
        ArtistPagedResponseOpenApi responseOpenApi = new ArtistPagedResponseOpenApi();
        Pageable paging = PageRequest.of(page, size);
        Page<Artist> artistPage = artistRepository.findAll(paging);
        artistPage.getContent().forEach(artist -> list.add(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artist)));
        responseOpenApi.setTotalElements(artistPage.getTotalElements());
        responseOpenApi.setTotalPages(artistPage.getTotalPages());
        responseOpenApi.setResult(list);
        return responseOpenApi;
    }
}