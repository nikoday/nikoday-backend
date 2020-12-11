package org.frekele.nikoday.api.entities.inners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.BaseEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NikoCalendar extends BaseEntity {

    private static final long serialVersionUID = -1107327093347283253L;

    private List<NikoCalendarColumn> headColumns;

    private List<NikoCalendarLine> lines;

    @Override
    public String toString() {
        return super.toString();
    }

}
