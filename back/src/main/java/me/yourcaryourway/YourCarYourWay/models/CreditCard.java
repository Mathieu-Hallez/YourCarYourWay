package me.yourcaryourway.YourCarYourWay.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.sql.Date;

@Entity
@Table(name = "CreditCard")
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

    @OneToOne(mappedBy = "credit_card_id")
    private User user;
}
