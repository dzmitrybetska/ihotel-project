package by.academy.project.hotel.entities.user;

import lombok.Data;

@Data
public class Passport {
    private String passportSeries;
    private String passportID;
    private Country country;

    public Passport (String passportSeries, String passportID, Country country){
        this.passportSeries = passportSeries;
        this.passportID = passportID;
        this.country = country;
    }

    public Passport setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
        return this;
    }

    public Passport setPassportID(String passportID) {
        this.passportID = passportID;
        return this;
    }

    public Passport setCountry(Country country) {
        this.country = country;
        return this;
    }

}
