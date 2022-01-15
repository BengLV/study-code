package designpatterns.创建型模式.生成器模式.director;

import designpatterns.创建型模式.生成器模式.builders.Builder;
import designpatterns.创建型模式.生成器模式.cars.CarType;
import designpatterns.创建型模式.生成器模式.components.Engine;
import designpatterns.创建型模式.生成器模式.components.GPSNavigator;
import designpatterns.创建型模式.生成器模式.components.Transmission;
import designpatterns.创建型模式.生成器模式.components.TripComputer;

/**
 * 主管类,非必须
 */
public class Director {

    public void constructSportsCar(Builder builder) {
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructCityCar(Builder builder) {
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    }

    public void constructSUV(Builder builder) {
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }
}