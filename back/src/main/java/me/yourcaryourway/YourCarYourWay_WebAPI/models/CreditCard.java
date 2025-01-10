package me.yourcaryourway.YourCarYourWay_WebAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.sql.Date;

@Entity
@Table(name = "CREDIT_CARD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @ToStringExclude
    private Long id;

    @NotNull
    @Column(name = "cb_number")
    private String cbNumber;

    @NotNull
    @Column(name = "expiration_date")
    private Date expirationDate;

    @NotNull
    @Column(name = "crypto_code")
    private String cryptoCode;

    @OneToOne(mappedBy = "creditCard")
    private User user;
}
