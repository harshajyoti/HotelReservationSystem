package HotelReservationSystem;

public class Hotel {

    String name;
    // Added Weekday and weekend regular price
    int weekdayRegularRate;
    int WeekendRegularRate;
    int ratings;

    public Hotel(String name, int weekdayRegularRate, int WeekendRegularRate, int ratings) {
        this.name = name;
        this.weekdayRegularRate = weekdayRegularRate;
        this.WeekendRegularRate = WeekendRegularRate;
        this.ratings = ratings;
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

    public int getRatings() {
        return ratings;
    }

    @Override
    public String toString(){
        return "Hotel: " + name + "\n"
                + "regular rate in weekday: " + weekdayRegularRate + "\n"
                + "regular rate in weekday: " + WeekendRegularRate;
    }
}
