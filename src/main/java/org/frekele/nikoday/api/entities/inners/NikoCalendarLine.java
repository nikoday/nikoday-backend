package org.frekele.nikoday.api.entities.inners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.api.entities.Registry;
import org.frekele.nikoday.api.entities.TeamUser;
import org.frekele.nikoday.core.entities.BaseEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NikoCalendarLine extends BaseEntity {

    private static final long serialVersionUID = -6515851067342878357L;

    private TeamUser teamUser;

    private List<Registry> registries;

    @Override
    public String toString() {
        return super.toString();
    }

}
