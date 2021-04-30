package org.pepo.record.repository.artist;

import org.pepo.record.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistCustomRepository {

    List<Artist> findAll(int pageNumber, int pageSize);

    Page<Artist> findFilterByName(Pageable pageable, String name);
}
