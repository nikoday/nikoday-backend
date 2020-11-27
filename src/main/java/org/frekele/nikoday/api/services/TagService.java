package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Tag;
import org.frekele.nikoday.api.repositories.TagRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TagService extends BaseService<Tag, String> {

    private static final long serialVersionUID = 4892609352619105937L;

    private final TagRepository tagRepository;

    @Override
    protected TagRepository getRepository() {
        return this.tagRepository;
    }


}
