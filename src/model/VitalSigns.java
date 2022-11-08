package model;

public class VitalSigns {
    private double bodyTemperature;

    private double pulseRate;

    private double diastolicPressure;

    private double systolicPressure;

    public VitalSigns() {}

    public VitalSigns(double bodyTemperature, double pulseRate, double diastolicPressure, double systolicPressure) {
        this.bodyTemperature = bodyTemperature;
        this.pulseRate = pulseRate;
        this.diastolicPressure = diastolicPressure;
        this.systolicPressure = systolicPressure;
    }

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public double getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(double pulseRate) {
        this.pulseRate = pulseRate;
    }

    public double getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(double diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public double getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(double systolicPressure) {
        this.systolicPressure = systolicPressure;
    }
}
