package org.dongyf.listview01.entity;

/**
 * Created by dongyf on 2014/10/24.
 */
public class BusStatus
{

    private String stationName;
    private String stationCode;
    private String carCode;
    private String arrivalTime;

    public BusStatus()
    {
    }

    public BusStatus(String stationName, String stationCode, String carCode, String arrivalTime)
    {
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.carCode = carCode;
        this.arrivalTime = arrivalTime;
    }

    public String getStationName()
    {
        return stationName;
    }

    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public String getStationCode()
    {
        return stationCode;
    }

    public void setStationCode(String stationCode)
    {
        this.stationCode = stationCode;
    }

    public String getCarCode()
    {
        return carCode;
    }

    public void setCarCode(String carCode)
    {
        this.carCode = carCode;
    }

    public String getArrivalTime()
    {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString()
    {
        return "BusStatus{" +
                "stationName='" + stationName + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", carCode='" + carCode + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                '}';
    }
}
