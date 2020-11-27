package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.TeamUser;
import org.frekele.nikoday.api.repositories.TeamUserRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TeamUserService extends BaseService<TeamUser, String> {

    private static final long serialVersionUID = 6180360573717255530L;

    private final TeamUserRepository teamUserRepository;

    @Override
    protected TeamUserRepository getRepository() {
        return this.teamUserRepository;
    }


}
