package br.com.siberius.realmeet.domain.entity;

import static java.util.Objects.isNull;

import br.com.siberius.realmeet.domain.entity.embeddable.Employee;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Embedded
    private Employee employee;

    @Column(name = "subject")
    private String subject;

    @Column(name = "start_at")
    private OffsetDateTime startAt;

    @Column(name = "end_at")
    private OffsetDateTime endAt;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updateAt;

    @PrePersist
    public void prePersist() {
        if (isNull(createdAt)) {
            setCreatedAt(OffsetDateTime.now());
        }
        setUpdateAt(createdAt);
    }

    @PreUpdate
    public void preUpdate() {
        setUpdateAt(OffsetDateTime.now());
    }
}
