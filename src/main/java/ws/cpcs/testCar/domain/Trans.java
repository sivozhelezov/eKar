package ws.cpcs.testCar.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Trans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String threadName;

    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    public Trans(String name) {
        this.threadName = name;
        this.createdAt = new Date();
    }
}
