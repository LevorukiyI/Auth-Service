package com.ascory.authservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "route_segments")
public class RouteSegmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type")
    private TransportType transportType;

    @Column
    private Double distance;

    @ManyToOne
    @JoinColumn(name = "start_destination_id")
    private DestinationEntity startDestination;

    @ManyToOne
    @JoinColumn(name = "final_destination_id")
    private DestinationEntity finalDestination;

    @ManyToOne
    @JoinColumn(name = "transport_order_id")
    private TransportOrderEntity transportOrder;
}
