package com.credits.app.services;

import com.credits.app.models.dao.CreditsPersonalDao;
import com.credits.app.models.documents.CreditsPersonal;
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
public class CreditsPersonalServiceImpl implements CreditsPersonalService{
    @Autowired
    private CreditsPersonalDao dao;

    @Override
    public Flux<CreditsPersonal> findAll() {
        return dao.findAll();
    }

    @Override
    public Mono<CreditsPersonal> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<CreditsPersonal> save(CreditsPersonal creditsPersonal) {
        return dao.save(creditsPersonal);
    }

    @Override
    public Mono<Void> delete(CreditsPersonal creditsPersonal) {
        return dao.delete(creditsPersonal);
    }

    @Override
    public Flux<CreditsPersonal> findByDni(String dni) {
        return dao.findByDni(dni);
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> saveCreditPersonal(CreditsPersonal creditsPersonal) {
        Map<String, Object> response = new HashMap<>();
        return dao.findByDni(creditsPersonal.getDni()).collectList().flatMap( c -> {
            if(c.isEmpty()){
                if(creditsPersonal.getCreateAt()==null){
                    creditsPersonal.setCreateAt(new Date());
                }
                creditsPersonal.setType("CREDITO PERSONAL");
                creditsPersonal.setStatus(1);
                return dao.save(creditsPersonal).flatMap(cx ->{
                    response.put("Usuario", cx);
                    response.put("Message", "Credito Personal registrado con con éxito");
                    return Mono.just(new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK));
                });
            }else{
                response.put("Message", "Existe un credito vigente con el mismo usuario");
                response.put("Note", "Verifique sus Datos");
                return Mono.just(new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST));
            }
        });
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> updateCreditPersonal(String id, CreditsPersonal creditsPersonal) {
        Map<String, Object> response = new HashMap<>();
        return dao.findById(id).flatMap(c->{
            c.setName(creditsPersonal.getName());
            c.setApem(creditsPersonal.getApem());
            c.setApep(creditsPersonal.getApep());
            c.setAddress(creditsPersonal.getAddress());
            c.setBirth_date(creditsPersonal.getBirth_date());
            c.setStatus(creditsPersonal.getStatus());
            c.setType(creditsPersonal.getType());
            c.setCard(creditsPersonal.getCard());
            c.setLimit(creditsPersonal.getLimit());
            return dao.save(c);
        }).map(customerUpdated->{
            response.put("Message:", "Registro actualizado");
            response.put("Credit:", customerUpdated);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }).defaultIfEmpty(new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Map<String, Object>>> findByIdCreditPersonal(String id) {
        Map<String, Object> response = new HashMap<>();
        return dao.findById(id).map(p -> {
            response.put("Mensaje:", "Registro encontrado");
            response.put("Credit", p);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }).defaultIfEmpty(new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Flux<CreditsPersonal> findByCard(Card card) {
        return dao.findByCard(card);
    }

    @Override
    public Mono<CreditsPersonal> saldoCard(CreditsPersonal creditsPersonal) {
        Mono<CreditsPersonal> aux = dao.findByCard(creditsPersonal.getCard()).next();
        return aux.flatMap(sa->{
            return dao.findById(sa.getId()).flatMap(c->{
                if(creditsPersonal.getCondition().equals(1)){
                    if((c.getAmount() + creditsPersonal.getAmount()) < c.getLimit() || (c.getAmount() + creditsPersonal.getAmount()) == c.getLimit()){
                        c.setAmount(c.getAmount() + creditsPersonal.getAmount());
                        return dao.save(c);
                    }else{
                        return Mono.empty();
                    }

                }else{
                    if((c.getAmount() - creditsPersonal.getAmount()) > 0 || (c.getAmount() - creditsPersonal.getAmount()) == 0){
                        c.setAmount(c.getAmount() - creditsPersonal.getAmount());
                        return dao.save(c);
                    }else{
                        return Mono.empty();
                    }
                }

            });
        });
    }
}
