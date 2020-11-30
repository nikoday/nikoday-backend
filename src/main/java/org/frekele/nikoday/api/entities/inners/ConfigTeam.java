package org.frekele.nikoday.api.entities.inners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.frekele.nikoday.core.entities.BaseEntity;
import org.frekele.nikoday.core.validations.OnCreate;
import org.frekele.nikoday.core.validations.OnUpdate;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigTeam extends BaseEntity {

    private static final long serialVersionUID = -8693700194331423929L;

    //Se time está ativo.
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Boolean enabled;

    //Se true, então ativa opção para que outros usuários,
    // não possam ver que marcou emoção.
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Boolean privateMode;

    @Override
    public String toString() {
        return super.toString();
    }

}
