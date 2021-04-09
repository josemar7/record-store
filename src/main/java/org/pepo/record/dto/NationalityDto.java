package org.pepo.record.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
