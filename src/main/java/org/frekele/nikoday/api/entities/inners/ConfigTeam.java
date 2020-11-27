package org.frekele.nikoday.api.entities.inners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.BaseEntity;
import org.frekele.nikoday.core.validations.OnCreate;
import org.frekele.nikoday.core.validations.OnUpdate;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigTeam extends BaseEntity {

    private static final long serialVersionUID = 2804131198761832701L;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Boolean disabled;

    @Override
    public String toString() {
        return super.toString();
    }

}
