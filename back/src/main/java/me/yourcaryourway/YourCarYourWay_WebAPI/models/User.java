package me.yourcaryourway.YourCarYourWay_WebAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.sql.Date;

@Entity
@Table(name = "USER")
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToStringExclude
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String lastname;

    @NotNull
    private String firstname;

    @NotNull
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private UserRole userRole;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private CreditCard creditCard;

//    Not used in the PoC
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "licence_id", referencedColumnName = "id")
//    private Licence licence;
}
