package org.pepo.record.dto;

import lombok.*;
import org.pepo.record.commons.BaseVO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NationalityDto extends BaseVO {

    private int id;
    private String name;

}
