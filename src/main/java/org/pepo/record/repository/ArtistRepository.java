package org.pepo.record.repository;

import org.pepo.record.entity.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    @Query("select a from Artist a where a.id = ?1 and true = :#{hasAuthority('artist.read')}")
    Artist findByIdSecured(int artistId);

    List<Artist> findByNameLikeIgnoreCase(String name);
}
