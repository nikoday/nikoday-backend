package org.frekele.nikoday.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.PersistentBaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "tags")
public class Tag extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = -706294954861215452L;

    @Indexed(unique = true)
    private String name;

    @Override
    public String toString() {
        return super.toString();
    }

}
