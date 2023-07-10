package fr.maetic.model.securite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.TemporalType.TIMESTAMP;
import static java.time.LocalDateTime.now;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Confirmation {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String token;
    @Temporal(TIMESTAMP)
    @CreatedBy
    private LocalDateTime createData;

    @OneToOne(targetEntity = User.class, fetch = EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Confirmation(User user) {
        this.user = user;
        this.createData = now();
        this.token = UUID.randomUUID().toString();
    }

}