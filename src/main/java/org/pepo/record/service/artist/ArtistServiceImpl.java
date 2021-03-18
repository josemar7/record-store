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
        artistIterable.forEach(artist -> list.add(artistEntityDtoMapper.entityToDto(artist)));
        return list;
    }

    @Override
    public ArtistDto findById(int id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        return artistEntityDtoMapper.entityToDto(artist);
    }

    @Override
    public ArtistDto findByIdSecured(int id) {
        return artistEntityDtoMapper.entityToDto(artistRepository.findByIdSecured(id));
    }

    @Override
    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public void delete(int id) {
        artistRepository.deleteById(id);
    }
}
