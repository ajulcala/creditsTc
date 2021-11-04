package com.credits.app.controllers;

import com.credits.app.models.documents.CreditsBusiness;
import com.credits.app.models.dto.Card;
import com.credits.app.services.CreditsBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/creditbusiness")
public class CreditsBusinessController {
    @Autowired
    private CreditsBusinessService service;


    @GetMapping
    public Flux<CreditsBusiness> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> findByIdFixed(@PathVariable String id){
        return service.findByIdCreditsBusiness(id);
    }

    @GetMapping("/ruc/{ruc}")
    public Flux<CreditsBusiness> listRuc(@PathVariable String ruc){
        return service.findByRuc(ruc);
    }

    @PostMapping("/card")
    public Flux<CreditsBusiness> listCard(@Valid @RequestBody Card card){
        return service.findByCard(card);
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> saveCreditBusiness(@Valid @RequestBody CreditsBusiness creditsBusiness){
        return service.saveCreditsBusiness(creditsBusiness);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Map<String, Object>>> updateCreditBusiness(@PathVariable String id, @Valid @RequestBody CreditsBusiness creditsBusiness){
        return service.updateCreditsBusiness(id, creditsBusiness);
    }

    @DeleteMapping("/{id}")
    private Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.findById(id).flatMap(p ->{
            return service.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/saldo")
    public Mono<CreditsBusiness> saldoCard(@Valid @RequestBody CreditsBusiness creditsBusiness){
        return service.saldoCard(creditsBusiness);
    }
}
