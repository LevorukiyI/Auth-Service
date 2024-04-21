package com.ascory.authservice.services;

import com.ascory.authservice.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TruckTransportStrategy implements TransportStrategy{

    private final TariffCoefficients tariffCoefficients;

    @Override
    public TransportType getTransportType() {
        return TransportType.TRUCK;
    }

    @Override
    public Double calculateDistance(DestinationEntity startDestination, DestinationEntity finalDestination) {
        return null;
    }

    @Override
    public Double calculateRouteSegmentTariff(RouteSegmentEntity routeSegment, CargoEntity cargo) {
        Double routeSegmentTariff = tariffCoefficients
                .getTransportTypeCoefficient(TransportType.TRUCK)
                + cargo.getMass() * tariffCoefficients.getCargoMassCoefficient(TransportType.TRUCK)
                + cargo.getVolume() * tariffCoefficients.getCargoVolumeCoefficient(TransportType.TRUCK);
        if(cargo.getIsSpecial()){
            routeSegmentTariff += tariffCoefficients.getSpecialCargoTariffCoefficient();
        }
        if(cargo.getIsDangerous()){
            routeSegmentTariff += tariffCoefficients.getDangerousCargoTariffCoefficient();
        }
        return routeSegmentTariff;
    }
}
