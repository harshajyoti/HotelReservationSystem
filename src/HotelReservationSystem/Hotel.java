package HotelReservationSystem;

public class Hotel {

    String name;
    int weekdayRegularRate;
    int WeekendRegularRate;

    public Hotel(String name, int weekdayRegularRate, int WeekendRegularRate) {
        this.name = name;
        this.weekdayRegularRate = weekdayRegularRate;
        this.WeekendRegularRate = WeekendRegularRate;
    }

    public String getName() {
        return name;
    }

    public int getWeekdayRegularRate() {
        return weekdayRegularRate;
    }

    public int getWeekendRegularRate() {
        return WeekendRegularRate;
    }

    @Override
    public String toString(){
        return "Hotel: " + name + "\n"
                + "regular rate in weekday: " + weekdayRegularRate + "\n"
                + "regular rate in weekday: " + WeekendRegularRate;
    }
}
