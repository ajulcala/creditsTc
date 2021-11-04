package com.credits.app.models.dao;

import com.credits.app.models.documents.CreditsPersonal;
import com.credits.app.models.dto.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditsPersonalDao extends ReactiveMongoRepository<CreditsPersonal,String> {
    Flux<CreditsPersonal> findByDni(String dni);
    Flux<CreditsPersonal> findByCard(Card card);
}
