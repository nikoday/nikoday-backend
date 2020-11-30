package org.frekele.nikoday.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.PersistentBaseEntity;
import org.frekele.nikoday.core.validations.OnCreate;
import org.frekele.nikoday.core.validations.OnUpdate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "registries")
public class Registry extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = -2763365876281145719L;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String teamUserId;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Emotion emotion;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Instant calendarDateTime;

    private List<Tag> tags;

    private String description;

    @Override
    public String toString() {
        return super.toString();
    }

}
