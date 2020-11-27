package org.frekele.nikoday.core.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class PersistentBaseEntity<ID> extends BaseEntity {

    private static final long serialVersionUID = 3083466711317102354L;

    @Id
    private ID id;

    @CreatedDate
    private Instant createdDateTime;

    @LastModifiedDate
    private Instant lastModifiedDateTime;

    @Override
    public String toString() {
        return super.toString();
    }

}
