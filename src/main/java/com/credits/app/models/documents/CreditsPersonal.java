package com.credits.app.models.documents;

import com.credits.app.models.dto.Card;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Document(collection="credit_personal")
public class CreditsPersonal {
    @Id
    private String id;
    private String dni; //dni o ruc
    private String naccount;//numero de cuneta
    private String currency; // tipo de moneda
    private String name; //nombre
    private String apep;//apellido paterno
    private String apem;// apellido materno
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth_date; //fecha nacimiento
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    private Integer status;
    private Double limit;
    private Double amount;
    private Integer condition;
    private Card card;
}
