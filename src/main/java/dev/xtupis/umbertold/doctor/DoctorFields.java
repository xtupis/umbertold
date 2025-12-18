package dev.xtupis.umbertold.doctor;

public class DoctorFields {
    private final String name;
    private final String specialization;
    private final String clinic;
    private final String rating;
    private final String profileUrl;

    public DoctorFields(String name, String specialization, String clinic, String rating, String profileUrl) {
        this.name = name;
        this.specialization = specialization;
        this.clinic = clinic;
        this.rating = rating;
        this.profileUrl = profileUrl;
    }

    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getClinic() { return clinic; }
    public String getRating() { return rating; }
    public String getProfileUrl() { return profileUrl; }
}
