package org.pepo.record.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArtistDto {

    private Integer id;
    private String name;
    private String style;
    private String nationality;

}
