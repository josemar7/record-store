package org.pepo.record.repository.artist;

import org.pepo.record.entity.Artist;

import java.util.List;

public interface ArtistCustomRepository {

    List<Artist> findAll(int pageNumber, int pageSize);
}
