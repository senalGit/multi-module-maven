package fr.maetic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "confirmations")
public class Confirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedBy
    private LocalDateTime createData;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Confirmation(User user) {
        this.user = user;
        this.createData = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }

}