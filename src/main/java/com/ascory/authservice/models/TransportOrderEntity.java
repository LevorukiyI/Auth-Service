package com.ascory.authservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "transport_orders")
public class TransportOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double insurancePrice;

    @OneToOne()
    private CargoEntity cargo;

    @OneToMany(mappedBy = "transportOrder", cascade = CascadeType.ALL)
    private List<RouteSegmentEntity> routeSegments;
}
