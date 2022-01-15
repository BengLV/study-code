package designpatterns.创建型模式.生成器模式.builders;

import designpatterns.创建型模式.生成器模式.cars.CarType;
import designpatterns.创建型模式.生成器模式.cars.Manual;
import designpatterns.创建型模式.生成器模式.components.Engine;
import designpatterns.创建型模式.生成器模式.components.GPSNavigator;
import designpatterns.创建型模式.生成器模式.components.Transmission;
import designpatterns.创建型模式.生成器模式.components.TripComputer;

/**
 * 汽车手册生成器
 */
public class CarManualBuilder implements Builder{
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Manual getResult() {
        return new Manual(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}