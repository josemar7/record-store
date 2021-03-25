package org.pepo.record.controller;

import org.pepo.record.dto.StyleDto;
import org.pepo.record.service.style.StyleService;
import org.pepo.record.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/style")
public class StyleController {

    @Autowired
    private StyleService styleService;

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<StyleDto>> getAllArtists() {
        return ResponseUtils.createResponse(styleService.findAll(), HttpStatus.OK);
    }

}
