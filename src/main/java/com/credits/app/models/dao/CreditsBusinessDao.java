package com.credits.app.models.dao;

import com.credits.app.models.documents.CreditsBusiness;
import com.credits.app.models.dto.Card;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditsBusinessDao extends ReactiveMongoRepository<CreditsBusiness,String> {
    Flux<CreditsBusiness> findByRuc(String ruc);
    Flux<CreditsBusiness> findByCard(Card card);
}
