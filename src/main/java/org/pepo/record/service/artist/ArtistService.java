package org.pepo.record.service.artist;

import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.entity.Artist;

import java.util.List;

public interface ArtistService {

    Iterable<ArtistResponseOpenApi> findAll();

    ArtistResponseOpenApi findById(int id);

    ArtistResponseOpenApi findByIdSecured(int id);

    ArtistResponseOpenApi save(Artist artist);

    ArtistResponseOpenApi update(Artist artist, int artistId);

    void delete(int id);

    List<ArtistResponseOpenApi> findByNameLike(String name);
}
