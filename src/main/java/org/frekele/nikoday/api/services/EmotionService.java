package org.frekele.nikoday.api.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.frekele.nikoday.api.entities.Emotion;
import org.frekele.nikoday.api.repositories.EmotionRepository;
import org.frekele.nikoday.core.services.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class EmotionService extends BaseService<Emotion, String> {

    private static final long serialVersionUID = 8556122041479490257L;

    private final EmotionRepository emotionRepository;

    @Override
    protected EmotionRepository getRepository() {
        return this.emotionRepository;
    }


}
