package com.credits.app.models.documents;

import com.credits.app.models.dto.Card;
import com.credits.app.models.dto.Owners;
import com.credits.app.models.dto.Signatories;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Document(collection="credit_business")
public class CreditsBusiness {
    @Id
    private String id;
    private String naccount;//numero de cuneta
    private String currency; // tipo de moneda
    private String ruc; //dni o ruc
    private String business_name; //nombre
    private String address;//direccion
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    private Integer status; // estado
    private String type; // tipo de cuenta
    private Double limit;
    private Double amount;
    private Integer condition;
    private Card card;
    private List<Owners> owners; //titulares
    private List<Signatories> signatories; //firmantes
}
