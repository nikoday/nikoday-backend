package org.frekele.nikoday.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.PersistentBaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "registries")
public class Registry extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = 1565613706639712946L;

    private String name;

    @Override
    public String toString() {
        return super.toString();
    }

}
