package org.pepo.record.service.artist;

import org.pepo.record.dto.ArtistDto;
import org.pepo.record.entity.Artist;

public interface ArtistService {

    Iterable<ArtistDto> findAll();

    ArtistDto findById(int id);

    ArtistDto findByIdSecured(int id);

    ArtistDto save(Artist artist);

    void delete(int id);

}
