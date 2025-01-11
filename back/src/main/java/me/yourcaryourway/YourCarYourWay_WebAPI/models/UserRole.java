package me.yourcaryourway.YourCarYourWay_WebAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Table(name = "USER_ROLE")
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToStringExclude
    private Long id;

    @NotNull
    private String name;
}