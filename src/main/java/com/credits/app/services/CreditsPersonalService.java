package com.credits.app.services;

import com.credits.app.models.documents.CreditsPersonal;
import com.credits.app.models.dto.Card;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CreditsPersonalService {
    public Flux<CreditsPersonal> findAll();
    public Mono<CreditsPersonal> findById(String id);
    public Mono<CreditsPersonal> save(CreditsPersonal creditsPersonal);
    public Mono<Void> delete(CreditsPersonal creditsPersonal);

    public Flux<CreditsPersonal> findByDni(String dni);
    Mono<ResponseEntity<Map<String, Object>>> saveCreditPersonal(CreditsPersonal creditsPersonal);
    Mono<ResponseEntity<Map<String, Object>>> updateCreditPersonal(String id, CreditsPersonal creditsPersonal);
    public Mono<ResponseEntity<Map<String, Object>>> findByIdCreditPersonal(String id);
    public Flux<CreditsPersonal> findByCard(Card card);
    public Mono<CreditsPersonal> saldoCard(CreditsPersonal creditsPersonal);
}
