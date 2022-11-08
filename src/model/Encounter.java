package model;

public class Encounter {
    private String diagnose;
    private VitalSigns vitalSigns;

    public Encounter(){}

    public Encounter(VitalSigns vitalSigns,String diagnose){
        this.diagnose = diagnose;
        this.vitalSigns = vitalSigns;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
    }
}
