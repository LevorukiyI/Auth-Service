package com.ascory.authservice.services;

import com.ascory.authservice.models.CargoEntity;
import com.ascory.authservice.models.DestinationEntity;
import com.ascory.authservice.models.RouteSegmentEntity;
import com.ascory.authservice.models.TransportType;

public interface TransportStrategy {
    TransportType getTransportType();
    Double calculateDistance(DestinationEntity startDestination, DestinationEntity finalDestination);
    Double calculateRouteSegmentTariff(RouteSegmentEntity routeSegment, CargoEntity cargo);
}
