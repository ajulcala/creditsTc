package com.credits.app.services;

import com.credits.app.models.documents.CreditsBusiness;
import com.credits.app.models.dto.Card;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CreditsBusinessService {
    public Flux<CreditsBusiness> findAll();
    public Mono<CreditsBusiness> findById(String id);
    public Mono<CreditsBusiness> save(CreditsBusiness creditsBusiness);
    public Mono<Void> delete(CreditsBusiness creditsBusiness);

    public Flux<CreditsBusiness> findByRuc(String ruc);
    Mono<ResponseEntity<Map<String, Object>>> saveCreditsBusiness(CreditsBusiness creditsBusiness);
    Mono<ResponseEntity<Map<String, Object>>> updateCreditsBusiness(String id, CreditsBusiness creditsBusiness);
    public Mono<ResponseEntity<Map<String, Object>>> findByIdCreditsBusiness(String id);
    public Flux<CreditsBusiness> findByCard(Card card);
    public Mono<CreditsBusiness> saldoCard(CreditsBusiness creditsBusiness);
}
