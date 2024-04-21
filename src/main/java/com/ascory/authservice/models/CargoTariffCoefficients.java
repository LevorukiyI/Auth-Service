package com.ascory.authservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "cargo_tariff_coefficients")
public class CargoTariffCoefficients {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double dangerousCargoCoefficient;

    @Column
    private Double specialCargoCoefficient;
}
