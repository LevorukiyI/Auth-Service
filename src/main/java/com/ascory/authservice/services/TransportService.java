package com.ascory.authservice.services;

import com.ascory.authservice.models.RouteSegmentEntity;
import com.ascory.authservice.models.TransportOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportService {

    private final TransportContext transportContext;

    public Double calculatePrice(TransportOrderEntity transportOrder){
        Double price = 0.0;
        for(RouteSegmentEntity routeSegment: transportOrder.getRouteSegments()){
            transportContext.setTransportStrategy(routeSegment.getTransportType());
            price += transportContext.calculateTransportTariff(routeSegment, transportOrder.getCargo())
                    * transportContext.calculateDistance(routeSegment.getStartDestination(), routeSegment.getFinalDestination());
        }
        price += transportOrder.getInsurancePrice();
        return price;
    }

}
