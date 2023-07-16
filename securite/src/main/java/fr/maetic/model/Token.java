package fr.maetic.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Table
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String token;
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    private boolean expired;
    private boolean revoked;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
