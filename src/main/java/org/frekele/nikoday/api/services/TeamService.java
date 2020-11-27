package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Team;
import org.frekele.nikoday.api.repositories.TeamRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TeamService extends BaseService<Team, String> {

    private static final long serialVersionUID = 5570054219244357590L;

    private final TeamRepository teamRepository;

    @Override
    protected TeamRepository getRepository() {
        return this.teamRepository;
    }

}
