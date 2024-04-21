package com.ascory.authservice.services;

import com.ascory.authservice.models.*;
import com.ascory.authservice.utils.CalculateDistanceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlainTransportStrategy implements TransportStrategy{

    private final TariffCoefficients tariffCoefficients;

    @Override
    public TransportType getTransportType() {
        return TransportType.PLAIN;
    }

    @Override
    public Double calculateDistance(DestinationEntity startDestination, DestinationEntity finalDestination) {
        return CalculateDistanceUtils.calculateHaversineDistance(startDestination, finalDestination);
    }

    @Override
    public Double calculateRouteSegmentTariff(RouteSegmentEntity routeSegment, CargoEntity cargo) {
        Double routeSegmentTariff = tariffCoefficients
                .getTransportTypeCoefficient(TransportType.PLAIN)
                + cargo.getMass() * tariffCoefficients.getCargoMassCoefficient(TransportType.PLAIN)
                + cargo.getVolume() * tariffCoefficients.getCargoVolumeCoefficient(TransportType.PLAIN);
        if(cargo.getIsSpecial()){
            routeSegmentTariff += tariffCoefficients.getSpecialCargoTariffCoefficient();
        }
        if(cargo.getIsDangerous()){
            routeSegmentTariff += tariffCoefficients.getDangerousCargoTariffCoefficient();
        }
        return routeSegmentTariff;
    }
}
