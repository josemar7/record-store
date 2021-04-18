package org.pepo.record.service.artist;

import lombok.AllArgsConstructor;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;
import org.pepo.record.mapper.ArtistEntityOpenApiMapper;
import org.pepo.record.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<ArtistResponseOpenApi> findByNameLike(final String name) {
        List<ArtistResponseOpenApi> list = new ArrayList<>();
        List<Artist> artistList =  artistRepository.findByNameLikeIgnoreCase("%" + name + "%");
        artistList.forEach(artist -> list.add(artistEntityOpenApiMapper.artistToArtistResponseOpenApi(artist)));
        return list;
    }
}
