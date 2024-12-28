package me.yourcaryourway.YourCarYourWay.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.sql.Timestamp;

@Entity
@Table(name = "MESSAGE")
@Data
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToStringExclude
    private Long id;

    @Nullable
    private String subject;

    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    @NotNull
    private MessageType type;

    @Column(name = "is_read")
    @NotNull
    private Boolean isRead;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    @NotNull
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    @NotNull
    private User receiver;

    @OneToOne
    @JoinColumn(name = "message_parent_id")
    @Nullable
    private Message parent;

    @OneToOne(mappedBy = "parent")
    private Message child;

    @Column(name = "created_at")
    @NotNull
    private Timestamp createdAt;
}
