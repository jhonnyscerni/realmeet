package br.com.siberius.realmeet.domain.entity.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Employee {

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_email")
    private String email;

}
