package org.frekele.nikoday.api.entities.inners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.BaseEntity;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NikoCalendarColumn extends BaseEntity {

    private static final long serialVersionUID = 204809435684082491L;

    private String name;

    private Integer day;

    private Integer month;

    private Integer year;

    private String dayOfWeek;

    private Instant date;


    @Override
    public String toString() {
        return super.toString();
    }

}
