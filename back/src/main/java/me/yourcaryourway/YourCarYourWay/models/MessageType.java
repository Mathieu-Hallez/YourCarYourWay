package me.yourcaryourway.YourCarYourWay.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

@Entity
@Table(name = "MESSAGE_TYPE")
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToStringExclude
    private Long id;

    @NotNull
    private String name;
}
