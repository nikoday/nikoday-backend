package org.frekele.nikoday.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.api.entities.inners.ConfigTeam;
import org.frekele.nikoday.core.entities.PersistentBaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "teams")
public class Team extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = -140634403636899935L;

    private String name;

    private ConfigTeam config;

    @Override
    public String toString() {
        return super.toString();
    }

}
