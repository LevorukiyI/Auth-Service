package com.ascory.authservice.utils;

import com.ascory.authservice.models.DestinationEntity;

public class CalculateDistanceUtils {
    private static final double EARTH_RADIUS = 6371.0; // Радиус Земли в километрах

    public static Double calculateHaversineDistance(DestinationEntity startDestination, DestinationEntity finalDestination){
        double lat1Rad = Math.toRadians(startDestination.getLatitude());
        double lon1Rad = Math.toRadians(startDestination.getLongitude());
        double lat2Rad = Math.toRadians(finalDestination.getLatitude());
        double lon2Rad = Math.toRadians(finalDestination.getLongitude());

        // Разница между долготой и широтой
        double differenceLongitude = lon2Rad - lon1Rad;
        double differenceLatitude = lat2Rad - lat1Rad;

        // Формула гаверсинуса
        double a = Math.pow(Math.sin(differenceLongitude / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(differenceLatitude / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Вычисление расстояния
        double distance = EARTH_RADIUS * c;

        return distance;
    }
}
