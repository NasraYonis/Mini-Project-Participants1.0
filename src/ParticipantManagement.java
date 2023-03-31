public class ParticipantManagement {
    private String name;
    private int hour;
    private int minutes;

    public ParticipantManagement(String name, int hour, int minutes) {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new IllegalArgumentException("Name participant not valid!");
        }

        this.name = name;
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getAppointmentTime() {
        return this.hour * 60 + this.minutes;
    }

    @Override
    public String toString() {
        return this.name + " at " + this.hour + ":" + this.minutes;
    }
}
