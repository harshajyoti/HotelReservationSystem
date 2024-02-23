package HotelReservationSystem;

public class Hotel {

    String name;
    // Added Weekday and weekend regular price
    int weekdayRegularRate;
    int weekendRegularRate;
    int weekdayRewardRate;
    int weekendRewardRate;
    int ratings;

    public Hotel(String name, int weekdayRegularRate, int weekendRegularRate, int weekdayRewardRate, int weekendRewardRate, int ratings) {
        this.name = name;
        this.weekdayRegularRate = weekdayRegularRate;
        this.weekendRegularRate = weekendRegularRate;
        this.weekdayRewardRate = weekdayRewardRate;
        this.weekendRewardRate = weekendRewardRate;
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public int getWeekdayRegularRate() {
        return weekdayRegularRate;
    }

    public int getWeekendRegularRate() {
        return weekendRegularRate;
    }

    public int getWeekdayRewardRate() {
        return weekdayRewardRate;
    }

    public int getWeekendRewardRate() {
        return weekendRewardRate;
    }

    public int getRatings() {
        return ratings;
    }
}
