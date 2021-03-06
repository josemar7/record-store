package org.pepo.record.service.artist;

import org.pepo.record.SwaggerCodgen.model.ArtistPagedResponseOpenApi;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;

import java.util.List;

public interface ArtistService {

    Iterable<ArtistResponseOpenApi> findAll();

    List<ArtistResponseOpenApi> findAll(Integer page, Integer size);

    ArtistResponseOpenApi findById(int id);

    ArtistResponseOpenApi findByIdSecured(int id);

    ArtistResponseOpenApi save(Artist artist);

    ArtistResponseOpenApi update(Artist artist, int artistId);

    void delete(int id);

    ArtistPagedResponseOpenApi findByNameLike(String name, Integer page, Integer size);

    ArtistPagedResponseOpenApi findAllPaged(Integer page, Integer size);
}
