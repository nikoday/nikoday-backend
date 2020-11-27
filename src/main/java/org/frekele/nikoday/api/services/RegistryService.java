package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Registry;
import org.frekele.nikoday.api.repositories.RegistryRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class RegistryService extends BaseService<Registry, String> {

    private static final long serialVersionUID = 5706344075028104066L;

    private final RegistryRepository registryRepository;

    @Override
    protected RegistryRepository getRepository() {
        return this.registryRepository;
    }


}
