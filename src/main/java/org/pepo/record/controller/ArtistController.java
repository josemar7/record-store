package org.pepo.record.controller;

import org.pepo.record.dto.ArtistDto;
import org.pepo.record.entity.Artist;
import org.pepo.record.service.artist.ArtistService;
import org.pepo.record.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<ArtistDto>> getAllArtists() {
        return ResponseUtils.createResponse(artistService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path="/{artistId}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable int artistId) {
        return ResponseUtils.createResponse(artistService.findById(artistId), HttpStatus.OK);
    }

    @GetMapping(path="/secured/{artistId}")
    public ResponseEntity<ArtistDto> getArtistByIdSecured(@PathVariable int artistId) {
        return ResponseUtils.createResponse(artistService.findByIdSecured(artistId), HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public ResponseEntity<ArtistDto> newArtist(@RequestBody Artist newArtist) {
        return ResponseUtils.createResponse(artistService.save(newArtist), HttpStatus.OK);
    }

    @DeleteMapping(path="/{artistId}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable int artistId) {
        artistService.delete(artistId);
        return ResponseUtils.createResponse(null, HttpStatus.OK);
    }

    @PutMapping(path="/{artistId}")
    public ResponseEntity<Void> updateArtistById(@PathVariable int artistId, @RequestBody Artist updatedArtist) {
        artistService.update(updatedArtist, artistId);
        return ResponseUtils.createResponse(null, HttpStatus.OK);
    }
}
