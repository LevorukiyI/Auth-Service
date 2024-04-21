package com.ascory.authservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@RequiredArgsConstructor
@Component
@Entity(name = "tariff_coefficients")
public class TariffCoefficients {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "cargo_tariff_coefficients_id")
    private CargoTariffCoefficients cargoTariffCoefficients;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_tariff_coefficient_id")
    private HashMap<TransportType, TransportTariffCoefficients> transportTariffCoefficients;

    public Double getDangerousCargoTariffCoefficient(){
        return this.cargoTariffCoefficients.getDangerousCargoCoefficient();
    }

    public Double getSpecialCargoTariffCoefficient(){
        return this.cargoTariffCoefficients.getSpecialCargoCoefficient();
    }

    public void putTransportTariffCoefficient(
            TransportTariffCoefficients transportTariffCoefficients){
        this.transportTariffCoefficients.put(transportTariffCoefficients.getTransportType(), transportTariffCoefficients);
    }

    public Double getTransportTypeCoefficient(TransportType transportType){
        return this.getTransportTariffCoefficients(transportType).getTransportTypeCoefficient();
    }

    public Double getCargoMassCoefficient(TransportType transportType){
        return this.getTransportTariffCoefficients(transportType).getCargoMassCoefficient();
    }

    public Double getCargoVolumeCoefficient(TransportType transportType){
        return this.getTransportTariffCoefficients(transportType).getCargoMassCoefficient();
    }

    public TransportTariffCoefficients getTransportTariffCoefficients(TransportType transportType){
        return transportTariffCoefficients.get(transportType);
    }
}
