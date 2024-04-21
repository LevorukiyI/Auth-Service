package com.ascory.authservice.services;

import com.ascory.authservice.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportContext{

    private TransportStrategy transportStrategy;
    private final ShipTransportStrategy shipTransportStrategy;
    private final TruckTransportStrategy truckTransportStrategy;
    private final PlainTransportStrategy plainTransportStrategy;

    public Double calculateDistance(DestinationEntity startDestination, DestinationEntity finalDestination) {
        return transportStrategy.calculateDistance(startDestination, finalDestination);
    }

    public Double calculateTransportTariff(RouteSegmentEntity routeSegment, CargoEntity cargo) {
        return transportStrategy.calculateRouteSegmentTariff(routeSegment, cargo);
    }

    public void setTransportStrategy(TransportStrategy transportStrategy){
        this.transportStrategy = transportStrategy;
    }

    public void setTransportStrategy(TransportType transportType){
        switch (transportType) {
            case SHIP -> this.transportStrategy = shipTransportStrategy;
            case TRUCK -> this.transportStrategy = truckTransportStrategy;
            case PLAIN -> this.transportStrategy = plainTransportStrategy;
        }
    }
}
