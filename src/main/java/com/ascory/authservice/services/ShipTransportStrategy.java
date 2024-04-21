package com.ascory.authservice.services;

import com.ascory.authservice.models.*;
import com.ascory.authservice.utils.CalculateDistanceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipTransportStrategy implements TransportStrategy{

    private final TariffCoefficients tariffCoefficients;

    @Override
    public TransportType getTransportType() {
        return TransportType.SHIP;
    }

    @Override
    public Double calculateDistance(DestinationEntity startDestination, DestinationEntity finalDestination) {
        return CalculateDistanceUtils.calculateHaversineDistance(startDestination, finalDestination);
    }

    @Override
    public Double calculateRouteSegmentTariff(RouteSegmentEntity routeSegment, CargoEntity cargo) {
        Double routeSegmentTariff = tariffCoefficients
                .getTransportTypeCoefficient(TransportType.SHIP)
                + cargo.getMass() * tariffCoefficients.getCargoMassCoefficient(TransportType.SHIP)
                + cargo.getVolume() * tariffCoefficients.getCargoVolumeCoefficient(TransportType.SHIP);
        if(cargo.getIsSpecial()){
            routeSegmentTariff += tariffCoefficients.getSpecialCargoTariffCoefficient();
        }
        if(cargo.getIsDangerous()){
            routeSegmentTariff += tariffCoefficients.getDangerousCargoTariffCoefficient();
        }
        return routeSegmentTariff;
    }
}
