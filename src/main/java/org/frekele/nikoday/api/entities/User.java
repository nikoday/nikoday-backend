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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class User extends PersistentBaseEntity<String> {

    private static final long serialVersionUID = 6360917497233931033L;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Indexed(unique = true)
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    @Email(groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Override
    public String toString() {
        return super.toString();
    }

}
