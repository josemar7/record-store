package org.pepo.record.repository.artist;

import org.pepo.record.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer>, ArtistCustomRepository {

    @Query("select a from Artist a where a.id = ?1 and true = :#{hasAuthority('artist.read')}")
    Artist findByIdSecured(int artistId);

    List<Artist> findByNameLikeIgnoreCase(String name);

    Page<Artist> findAll(Pageable pageable);
}
