public class ParticipantManagement {
    String name;
    int hour;
    int minutes;
    public ParticipantManagement(String name, int h, int m) {
        this.name = name;
        this.hour = h;
        this.minutes = m;
    }

    @Override
    public String toString() {
        return this.name + " at " + this.hour + ":" + this.minutes;
    }
}
