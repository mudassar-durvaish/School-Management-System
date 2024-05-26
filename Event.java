package sms.first;

public class Event {
    private int registrationId;
    private String name;
    private String eventType;
    private String auditoriumName;
    private int numberOfSeats;

    public Event(int registrationId, String name, String eventType, String auditoriumName, int numberOfSeats) {
        this.registrationId = registrationId;
        this.name = name;
        this.eventType = eventType;
        this.auditoriumName = auditoriumName;
        this.numberOfSeats = numberOfSeats;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public void setAuditoriumName(String auditoriumName) {
        this.auditoriumName = auditoriumName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
