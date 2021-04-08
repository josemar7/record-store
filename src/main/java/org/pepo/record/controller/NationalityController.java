package org.pepo.record.controller;

import org.pepo.record.dto.NationalityDto;
import org.pepo.record.entity.Nationality;
import org.pepo.record.service.nationality.NationalityService;
import org.pepo.record.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nationality")
public class NationalityController {

    @Autowired
    private NationalityService nationalityService;

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<NationalityDto>> getAllNationalities() {
        return ResponseUtils.createResponse(nationalityService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public ResponseEntity<NationalityDto> newArtist(@RequestBody Nationality newNationality) {
        return ResponseUtils.createResponse(nationalityService.save(newNationality), HttpStatus.OK);
    }
}
