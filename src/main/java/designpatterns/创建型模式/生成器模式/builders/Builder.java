package designpatterns.创建型模式.生成器模式.builders;

import designpatterns.创建型模式.生成器模式.cars.CarType;
import designpatterns.创建型模式.生成器模式.components.Engine;
import designpatterns.创建型模式.生成器模式.components.GPSNavigator;
import designpatterns.创建型模式.生成器模式.components.Transmission;
import designpatterns.创建型模式.生成器模式.components.TripComputer;

/**
 * 通用生成器接口
 */
public interface Builder {
    void setCarType(CarType type);
    void setSeats(int seats);
    void setEngine(Engine engine);
    void setTransmission(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator gpsNavigator);
}
