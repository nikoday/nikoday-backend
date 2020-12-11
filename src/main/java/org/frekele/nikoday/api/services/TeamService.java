package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.frekele.nikoday.api.entities.Team;
import org.frekele.nikoday.api.repositories.TeamRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TeamService extends BaseService<Team, String> {

    private static final long serialVersionUID = 5570054219244357590L;

    private final TeamRepository teamRepository;

    private final TeamUserService teamUserService;

    @Override
    protected TeamRepository getRepository() {
        return this.teamRepository;
    }


    public List<Team> findAll(boolean eager) {
        List<Team> result = super.findAll();
        if (eager) {
            for (Iterator<Team> i = result.iterator(); i.hasNext(); ) {
                this.loadEager(i.next());
            }
        }
        return result;
    }

    public Team findById(String id, boolean eager) {
        Team team = super.findById(id);
        if (eager) {
            this.loadEager(team);
        }
        return team;
    }

    private void loadEager(Team team) {
        if (team != null) {
            String teamId = team.getId();
            if (StringUtils.isNotBlank(teamId)) {
                team.setTeamUsers(this.teamUserService.findByTeamId(teamId, true));
            }

        }
    }

}
