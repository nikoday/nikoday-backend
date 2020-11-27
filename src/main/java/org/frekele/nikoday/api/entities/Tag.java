package org.frekele.nikoday.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.PersistentBaseEntity;
import org.frekele.nikoday.core.validations.OnCreate;
import org.frekele.nikoday.core.validations.OnUpdate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "tags")
public class Tag extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = -1725418449757183648L;

    @Indexed(unique = true)
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Override
    public String toString() {
        return super.toString();
    }

}
