package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.frekele.nikoday.api.entities.TeamUser;
import org.frekele.nikoday.api.repositories.TeamUserRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TeamUserService extends BaseService<TeamUser, String> {

    private static final long serialVersionUID = 6180360573717255530L;

    private final TeamUserRepository teamUserRepository;

    private final UserService userService;

    @Override
    protected TeamUserRepository getRepository() {
        return this.teamUserRepository;
    }


    public List<TeamUser> findByTeamId(String teamId, boolean eager) {
        List<TeamUser> result = this.getRepository().findByTeamId(teamId);
        if (eager) {
            for (Iterator<TeamUser> i = result.iterator(); i.hasNext(); ) {
                this.loadEager(i.next());
            }
        }
        return result;
    }

    public List<TeamUser> findByTeamIdAndUserId(String teamId, String userId, boolean eager) {
        List<TeamUser> result = this.getRepository().findByTeamIdAndUserId(teamId, userId);
        if (eager) {
            for (Iterator<TeamUser> i = result.iterator(); i.hasNext(); ) {
                this.loadEager(i.next());
            }
        }
        return result;
    }

    public List<TeamUser> findAll(boolean eager) {
        List<TeamUser> result = super.findAll();
        if (eager) {
            for (Iterator<TeamUser> i = result.iterator(); i.hasNext(); ) {
                this.loadEager(i.next());
            }
        }
        return result;
    }

    public TeamUser findById(String id, boolean eager) {
        TeamUser teamUser = super.findById(id);
        if (eager) {
            this.loadEager(teamUser);
        }
        return teamUser;
    }

    private void loadEager(TeamUser teamUser) {
        if (teamUser != null) {
            String userId = teamUser.getUserId();
            if (StringUtils.isNotBlank(userId)) {
                teamUser.setUser(this.userService.findById(userId));
            }
        }
    }
}
