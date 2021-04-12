package org.pepo.record.controller;

import org.pepo.record.SwaggerCodgen.api.ArtistApi;
import org.pepo.record.SwaggerCodgen.model.ArtistResponseOpenApi;
import org.pepo.record.mapper.ArtistEntityOpenApiMapper;
import org.pepo.record.service.artist.ArtistService;
import org.pepo.record.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ArtistController implements ArtistApi {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistEntityOpenApiMapper artistEntityOpenApiMapper;

    @Override
    public ResponseEntity<Void> deleteArtistById(final Integer artistId) {
        artistService.delete(artistId);
        return ResponseUtils.createResponse(null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<ArtistResponseOpenApi>> getAllArtists() {
        List<ArtistResponseOpenApi> result =
                StreamSupport.stream(artistService.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ArtistResponseOpenApi> getArtistById(final Integer artistId) {
        return ResponseEntity.ok(artistService.findById(artistId));
    }

    @Override
    public ResponseEntity<ArtistResponseOpenApi> newArtist(final ArtistResponseOpenApi artistResponseOpenApi) {
        return ResponseEntity.ok(artistService.save(artistEntityOpenApiMapper.artistResponseOpenApiToArtist(artistResponseOpenApi)));
    }

    @Override
    public ResponseEntity<ArtistResponseOpenApi> updateArtistById(final Integer artistId, final ArtistResponseOpenApi artistResponseOpenApi) {
        return ResponseEntity.ok(artistService.update(artistEntityOpenApiMapper.artistResponseOpenApiToArtist(artistResponseOpenApi), artistId));
    }
}
