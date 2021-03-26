package org.pepo.record.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.pepo.record.commons.BaseVO;

@Getter
@Setter
@Builder
public class ArtistDto extends BaseVO {

    private Integer id;
    private String name;
    private String style;
    private String nationality;

}
