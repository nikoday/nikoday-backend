package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.frekele.nikoday.api.entities.User;
import org.frekele.nikoday.api.repositories.UserRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserService extends BaseService<User, String> {

    private static final long serialVersionUID = -930684010163457597L;

    private final UserRepository userRepository;

    @Override
    protected UserRepository getRepository() {
        return this.userRepository;
    }

    public User authenticate(User entity) {
        User entityFinded = this.findByEmailAndPassword(entity.getEmail(), entity.getPassword());
        if (entityFinded != null) {
            String token = "" + entityFinded.getId() + ":" + entityFinded.getEmail() + ":" + entityFinded.getPassword() + ":";
            token = Base64.encodeBase64String(token.getBytes(StandardCharsets.UTF_8));
            entityFinded.setToken(token);
        }
        return entityFinded;
    }

    public User findByEmailAndPassword(String email, String password) {
        User entity = this.getRepository().findByEmailAndPassword(email, password);
        return entity;
    }

    public User tokenValidate(String authorization) {
        if (StringUtils.isNotBlank(authorization)) {
            String decodedBase64 = new String(Base64.decodeBase64(authorization), StandardCharsets.UTF_8);
            if (decodedBase64 != null) {
                String userId = decodedBase64.split(":")[0];
                if (StringUtils.isNotBlank(userId)) {
                    User userFinded = this.findById(userId);
                    if (userFinded != null) {
                        return this.authenticate(userFinded);
                    }
                }
            }
        }
        return null;
    }

}
