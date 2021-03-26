package org.pepo.record.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.pepo.record.commons.BaseVO;

@Getter
@Setter
@Builder
public class StyleDto extends BaseVO {

    private int id;
    private String name;

}
