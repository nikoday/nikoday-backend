package org.frekele.nikoday.api.entities.inners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigTeam extends BaseEntity {

    private static final long serialVersionUID = -4745121873910744214L;

    private Boolean disabled;

    @Override
    public String toString() {
        return super.toString();
    }

}
