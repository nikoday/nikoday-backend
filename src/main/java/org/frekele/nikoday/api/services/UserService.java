package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.User;
import org.frekele.nikoday.api.repositories.UserRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
