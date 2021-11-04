package com.credits.app.controllers;

import com.credits.app.models.documents.CreditsPersonal;
import com.credits.app.models.dto.Card;
import com.credits.app.services.CreditsPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/creditpersonal")
public class CreditsPersonalController {
    @Autowired
    private CreditsPersonalService service;


    @GetMapping
    public Flux<CreditsPersonal> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> findByIdFixed(@PathVariable String id){
        return service.findByIdCreditPersonal(id);
    }

    @GetMapping("/dni/{dni}")
    public Flux<CreditsPersonal> listDni(@PathVariable String dni){
        return service.findByDni(dni);
    }

    @PostMapping("/card")
    public Flux<CreditsPersonal> listCard(@Valid @RequestBody Card card){
        return service.findByCard(card);
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> saveCreditPersonal(@Valid @RequestBody CreditsPersonal creditsPersonal){
        return service.saveCreditPersonal(creditsPersonal);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateCreditPersonal(@PathVariable String id, @Valid @RequestBody CreditsPersonal creditsPersonal){
        return service.updateCreditPersonal(id, creditsPersonal);
    }

    @DeleteMapping("/{id}")
    private Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.findById(id).flatMap(p ->{
            return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/saldo")
    public Mono<CreditsPersonal> saldoCard(@Valid @RequestBody CreditsPersonal creditsPersonal){
        return service.saldoCard(creditsPersonal);
    }
}
