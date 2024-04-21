package com.ascory.authservice.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transport_coefficints")
public class TransportTariffCoefficients {
    @Id
    @Enumerated(EnumType.STRING)
    @Column
    private TransportType transportType;

    @Column
    @NonNull
    private Double transportTypeCoefficient;

    @Column
    @NonNull
    private Double cargoMassCoefficient;

    @Column
    @NonNull
    private Double cargoVolumeCoefficient;
}
