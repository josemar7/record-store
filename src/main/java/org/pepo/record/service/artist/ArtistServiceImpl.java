package org.pepo.record.service.artist;

import lombok.AllArgsConstructor;
import org.pepo.record.dto.ArtistDto;
import org.pepo.record.entity.Artist;
import org.pepo.record.mapper.ArtistEntityDtoMapper;
import org.pepo.record.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private final ArtistEntityDtoMapper artistEntityDtoMapper;

    @Autowired
    private final ArtistRepository artistRepository;

    @Override
    public Iterable<ArtistDto> findAll() {
        List<ArtistDto> list = new ArrayList<>();
        Iterable<Artist> artistIterable = artistRepository.findAll();
        artistIterable.forEach(artist -> list.add(artistEntityDtoMapper.artistToArtistDto(artist)));
        return list;
    }

    @Override
    public ArtistDto findById(final int id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        return artistEntityDtoMapper.artistToArtistDto(artist);
    }

    @Override
    public ArtistDto findByIdSecured(final int id) {
        return artistEntityDtoMapper.artistToArtistDto(artistRepository.findByIdSecured(id));
    }

    @Override
    public ArtistDto save(final Artist artist) {
        return artistEntityDtoMapper.artistToArtistDto(artistRepository.save(artist));
    }

    @Override
    public void delete(final int id) {
        artistRepository.deleteById(id);
    }

    @Override
    public ArtistDto update(final Artist artist, final int artistId) {
        artist.setId(artistId);
        return artistEntityDtoMapper.artistToArtistDto(artistRepository.save(artist));
    }
}
