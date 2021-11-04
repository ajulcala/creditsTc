package com.credits.app.services;

import com.credits.app.models.dao.CreditsBusinessDao;
import com.credits.app.models.documents.CreditsBusiness;
import com.credits.app.models.dto.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class CreditsBusinessServiceImpl implements CreditsBusinessService{
    @Autowired
    private CreditsBusinessDao dao;

    @Override
    public Flux<CreditsBusiness> findAll() {
        return dao.findAll();
    }

    @Override
    public Mono<CreditsBusiness> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<CreditsBusiness> save(CreditsBusiness creditsBusiness) {
        return dao.save(creditsBusiness);
    }

    @Override
    public Mono<Void> delete(CreditsBusiness creditsBusiness) {
        return dao.delete(creditsBusiness);
    }

    @Override
    public Flux<CreditsBusiness> findByRuc(String ruc) {
        return dao.findByRuc(ruc);
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> saveCreditsBusiness(CreditsBusiness creditsBusiness) {
        Map<String, Object> response = new HashMap<>();
        if(creditsBusiness.getCreateAt()==null){
            creditsBusiness.setCreateAt(new Date());
        }
        creditsBusiness.setType("CREDITO EMPRESARIAL");
        creditsBusiness.setStatus(1);
        return dao.save(creditsBusiness).flatMap(cx ->{
            response.put("Usuario", cx);
            response.put("Message", "Credito Empresarial registrado con con éxito");
            return Mono.just(new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK));
        });
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> updateCreditsBusiness(String id, CreditsBusiness creditsBusiness) {
        Map<String, Object> response = new HashMap<>();
        return dao.findById(id).flatMap(c->{
            c.setRuc(creditsBusiness.getRuc());
            c.setBusiness_name(creditsBusiness.getBusiness_name());
            c.setAddress(creditsBusiness.getAddress());
            c.setStatus(creditsBusiness.getStatus());
            c.setAmount(creditsBusiness.getAmount());
            c.setNaccount(creditsBusiness.getNaccount());
            c.setCurrency(creditsBusiness.getCurrency());
            c.setCard(creditsBusiness.getCard());
            c.setLimit(creditsBusiness.getLimit());
            c.setOwners(creditsBusiness.getOwners());
            c.setSignatories(creditsBusiness.getSignatories());
            return dao.save(c);
        }).map(customerUpdated->{
            response.put("Message:", "Registro actualizado");
            response.put("Credit:", customerUpdated);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }).defaultIfEmpty(new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> findByIdCreditsBusiness(String id) {
        Map<String, Object> response = new HashMap<>();
        return dao.findById(id).map(p -> {
            response.put("Mensaje:", "Registro encontrado");
            response.put("Credit", p);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }).defaultIfEmpty(new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Flux<CreditsBusiness> findByCard(Card card) {
        return dao.findByCard(card);
    }

    @Override
    public Mono<CreditsBusiness> saldoCard(CreditsBusiness creditsBusiness) {
        Mono<CreditsBusiness> aux = dao.findByCard(creditsBusiness.getCard()).next();
        return aux.flatMap(sa->{
            return dao.findById(sa.getId()).flatMap(c->{
                if(creditsBusiness.getCondition().equals(1)){
                    if((c.getAmount() + creditsBusiness.getAmount()) < c.getLimit() || (c.getAmount() + creditsBusiness.getAmount()) == c.getLimit()){
                        c.setAmount(c.getAmount() + creditsBusiness.getAmount());
                        return dao.save(c);
                    }else{
                        return Mono.empty();
                    }

                }else{
                    if((c.getAmount() - creditsBusiness.getAmount()) > 0 || (c.getAmount() - creditsBusiness.getAmount()) == 0){
                        c.setAmount(c.getAmount() - creditsBusiness.getAmount());
                        return dao.save(c);
                    }else{
                        return Mono.empty();
                    }
                }

            });
        });
    }
}
