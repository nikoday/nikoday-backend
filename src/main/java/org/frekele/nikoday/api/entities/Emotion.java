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
@Document(collection = "emotions")
public class Emotion extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = -1098433216529907924L;

    @Indexed(unique = true)
    private String name;

    private String icon;

    @Override
    public String toString() {
        return super.toString();
    }

}
