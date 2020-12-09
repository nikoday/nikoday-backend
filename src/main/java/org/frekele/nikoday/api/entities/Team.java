package org.frekele.nikoday.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.api.entities.inners.ConfigTeam;
import org.frekele.nikoday.core.entities.PersistentBaseEntity;
import org.frekele.nikoday.core.validations.OnCreate;
import org.frekele.nikoday.core.validations.OnUpdate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "teams")
public class Team extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = -7002166674834330033L;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String name;

    private String description;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private ConfigTeam config;

    @Override
    public String toString() {
        return super.toString();
    }

}
