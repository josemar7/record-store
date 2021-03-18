package org.pepo.record.controller;

import org.pepo.record.dto.ArtistDto;
import org.pepo.record.entity.Artist;
import org.pepo.record.security.perms.ArtistCreatePermission;
import org.pepo.record.security.perms.ArtistDeletePermission;
import org.pepo.record.security.perms.ArtistReadPermission;
import org.pepo.record.service.artist.ArtistService;
import org.pepo.record.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

//    @ArtistReadPermission
    @GetMapping(path="/all")
    public ResponseEntity<Iterable<ArtistDto>> getAllArtists() {
        return ResponseUtils.createResponse(artistService.findAll(), HttpStatus.OK);
    }

    @ArtistReadPermission
    @GetMapping(path="/{artistId}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable int artistId) {
        return ResponseUtils.createResponse(artistService.findById(artistId), HttpStatus.OK);
    }

    @GetMapping(path="/secured/{artistId}")
    public ResponseEntity<ArtistDto> getArtistByIdSecured(@PathVariable int artistId) {
        return ResponseUtils.createResponse(artistService.findByIdSecured(artistId), HttpStatus.OK);
    }

    @ArtistCreatePermission
    @PostMapping(path = "/new")
    public ResponseEntity<Artist> newArtist(@RequestBody Artist newArtist) {
        return ResponseUtils.createResponse(artistService.save(newArtist), HttpStatus.OK);
    }

    @ArtistDeletePermission
    @DeleteMapping(path="/{artistId}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable int artistId) {
        artistService.delete(artistId);
        return ResponseUtils.createResponse(null, HttpStatus.OK);
    }


}
