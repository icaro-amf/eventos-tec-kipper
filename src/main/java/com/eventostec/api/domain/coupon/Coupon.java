package com.eventostec.api.domain.coupon;

import com.eventostec.api.domain.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity                    //define a classe como uma entidade do banco de dados
@Table(name = "coupon")    //vincula a tabela coupon
@Getter                    //Gera os getters automaticamente
@Setter                    //gera os setters automaticamente
@NoArgsConstructor         //inicializa um construtor vazio
@AllArgsConstructor        //inicializa um contrutor com todos os argumentos da classe
public class Coupon {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 24)
    private String code;

    @Column(columnDefinition = "timestamp(8)")
    private LocalDateTime valid;

    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "event_id")  //vincula a chave estrangeira
    private Event event;            //FK
}
