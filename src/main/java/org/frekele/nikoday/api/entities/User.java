package org.frekele.nikoday.api.entities;

import lombok.*;
import org.frekele.nikoday.core.entities.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Document(collection = "users")
public class User extends BaseEntity {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    @Override
    public String toString() {
        return super.toString();
    }

}
