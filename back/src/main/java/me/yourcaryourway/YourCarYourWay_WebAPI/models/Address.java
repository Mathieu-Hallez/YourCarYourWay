package me.yourcaryourway.YourCarYourWay_WebAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Table(name = "ADDRESS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @ToStringExclude
    private Long id;

    @NotNull
    @Column(name = "street_number")
    private Integer streetNumber;

    @NotNull
    @Column(name = "street_name")
    private String streetName;

    @NotNull
    private Integer postcode;

    @NotNull
    @Column(name = "locality_name")
    private String localityName;

    @NotNull
    private String country;
}
