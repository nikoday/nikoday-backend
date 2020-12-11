package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.frekele.nikoday.api.entities.Registry;
import org.frekele.nikoday.api.entities.Team;
import org.frekele.nikoday.api.entities.TeamUser;
import org.frekele.nikoday.api.entities.enums.Role;
import org.frekele.nikoday.api.entities.inners.NikoCalendar;
import org.frekele.nikoday.api.entities.inners.NikoCalendarColumn;
import org.frekele.nikoday.api.entities.inners.NikoCalendarLine;
import org.frekele.nikoday.api.repositories.TeamRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
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

    private final RegistryService registryService;

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

    public NikoCalendar loadNikoCalendar(String teamId, String weekOfYear) {
        Team team = this.findById(teamId, true);
        if (team == null) {
            return null;
        }
        NikoCalendar nikoCalendar = new NikoCalendar();

        List<NikoCalendarColumn> columns = this.buildColumns(weekOfYear);
        nikoCalendar.setHeadColumns(columns);

        List<NikoCalendarLine> lines = new ArrayList<>();
        nikoCalendar.setLines(lines);
        for (TeamUser teamUser : team.getTeamUsers()) {
            if (teamUser.getRoles() != null && teamUser.getRoles().contains(Role.OPERATOR)) {
                NikoCalendarLine line = new NikoCalendarLine();
                line.setTeamUser(teamUser);
                lines.add(line);
                String teamUserId = teamUser.getId();

                List<Registry> registries = new ArrayList<>();
                line.setRegistries(registries);
                for (NikoCalendarColumn column : columns) {
                    Integer day = column.getDay();
                    Integer month = column.getMonth();
                    Integer year = column.getYear();
                    Registry registry = this.registryService.findByTeamUserIdAndDayAndMonthAndYear(teamUserId, day, month, year);
                    if (registry == null) {
                        registry = new Registry();
                        registry.setTeamUserId(teamUserId);
                        registry.setDay(day);
                        registry.setMonth(month);
                        registry.setYear(year);
                        registry.setDayOfWeek(column.getDayOfWeek());
                        registry.setCalendarDateTime(column.getDate());
                    }
                    registries.add(registry);
                }
            }


        }


        return nikoCalendar;
    }

    private List<NikoCalendarColumn> buildColumns(String weekOfYear) {
        List<NikoCalendarColumn> columns = new ArrayList<>();
        LocalDate firstDate = this.getFirstDayOfWeek(weekOfYear);
        for (int i = 0; i < 7; i++) {
            NikoCalendarColumn column = new NikoCalendarColumn();
            LocalDate currentDate = firstDate.plusDays(i);
            column.setDay(currentDate.getDayOfMonth());
            column.setMonth(currentDate.getMonthValue());
            column.setYear(currentDate.getYear());
            column.setDayOfWeek(currentDate.getDayOfWeek().name());
            column.setDate(currentDate.atStartOfDay().toInstant(ZoneOffset.UTC));
            columns.add(column);
        }
        return columns;
    }


    private LocalDate getFirstDayOfWeek(String weekOfYear) {
        LocalDate localDate = LocalDate.parse(weekOfYear,
                new DateTimeFormatterBuilder().appendPattern("YYYY'-W'w")
                        .parseDefaulting(WeekFields.ISO.dayOfWeek(), 1)
                        .toFormatter());
        return localDate;
    }

    private LocalDate getLastDayOfWeek(String weekOfYear, int plusDays) {
        LocalDate localDate = this.getFirstDayOfWeek(weekOfYear);
        localDate = localDate.plusDays(plusDays);
        return localDate;
    }


    private LocalDate getLastDayOfWeek(String weekOfYear) {
        return this.getLastDayOfWeek(weekOfYear, 6);
    }


//    public static void main(String[] args) {
//        String weekOfYear = "2020W50";
//        System.out.println(getFirstDayOfWeek(weekOfYear));
//        System.out.println(getLastDayOfWeek(weekOfYear));
//    }


}
