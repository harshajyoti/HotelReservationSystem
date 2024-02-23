package HotelReservationSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HotelReservationSystem {

    HashMap<String, Hotel> hotels = new HashMap<>();
    String userType;
    ArrayList<String> days = new ArrayList<>();

    public void addHotel(String name, int weekdayRegularRate, int weekendRegularRate, int weekdayRewardRate, int weekendRewardRate, int ratings){
        hotels.put(name, new Hotel(name, weekdayRegularRate, weekendRegularRate, weekdayRewardRate, weekendRewardRate, ratings));
    }

    public void validateInput(String dateRange) throws IllegalArgumentException {
        String[] dateStrings = dateRange.split(", ");
        for (String dateString : dateStrings) {
            if (dateString.matches("\\d{1,2}[A-Za-z]{3}\\d{4}\\(\\w+\\)")) {
                String dayOfWeek = dateString.substring(dateString.indexOf('(') + 1, dateString.indexOf(')'));
                days.add(dayOfWeek);
            } else {
                throw new IllegalArgumentException("Invalid Date format: " + dateString);
            }
        }
    }

    // Method to accept input from the User.
    public void acceptInput(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please Enter Customer type: ");
        userType = scan.nextLine();
        System.out.print("Please Enter Date range: ");
        String userInput = scan.nextLine();
        validateInput(userInput);
    }

    private boolean isWeekday(String day) {
        return day.equalsIgnoreCase("mon") || day.equalsIgnoreCase("tue") || day.equalsIgnoreCase("wed")
                || day.equalsIgnoreCase("thur") || day.equalsIgnoreCase("fri");
    }

    // Method to find the cheapest hotel rate
    public void findCheapHotel(){
        int lakewoodHotelPrice = 0;
        int bridgewoodHotelPrice = 0;

        int ridgewoodHotelPrice = 0;

        Hotel lakewoodHotel = hotels.get("Lakewood");
        Hotel bridgewoodHotel = hotels.get("Bridgewood");
        Hotel ridgewoodHotel = hotels.get("Ridgewood");
        // Able to find cheap hotel for a given date range based on weekday or weekend

        if (userType.equalsIgnoreCase("regular")){
            for (String day : days){
                if (isWeekday(day)){
                    lakewoodHotelPrice += lakewoodHotel.getWeekdayRegularRate();
                    bridgewoodHotelPrice += bridgewoodHotel.getWeekdayRegularRate();
                    ridgewoodHotelPrice += ridgewoodHotel.getWeekdayRegularRate();
                } else {
                    lakewoodHotelPrice += lakewoodHotel.getWeekendRegularRate();
                    bridgewoodHotelPrice += bridgewoodHotel.getWeekendRegularRate();
                    ridgewoodHotelPrice += ridgewoodHotel.getWeekendRegularRate();
                }
            }
        } else {
            for (String day : days){
                if (isWeekday(day)){
                    lakewoodHotelPrice += lakewoodHotel.getWeekdayRewardRate();
                    bridgewoodHotelPrice += bridgewoodHotel.getWeekdayRewardRate();
                    ridgewoodHotelPrice += ridgewoodHotel.getWeekdayRewardRate();
                } else {
                    lakewoodHotelPrice += lakewoodHotel.getWeekendRewardRate();
                    bridgewoodHotelPrice += bridgewoodHotel.getWeekendRewardRate();
                    ridgewoodHotelPrice += ridgewoodHotel.getWeekendRewardRate();
                }
            }
        }

        // Compare the rates and give the cheap rate
        if (lakewoodHotelPrice < bridgewoodHotelPrice && ridgewoodHotelPrice > lakewoodHotelPrice){
            System.out.println(lakewoodHotel.getName() + ", Total Rate: $" + lakewoodHotelPrice);
        } else if (bridgewoodHotelPrice < lakewoodHotelPrice && ridgewoodHotelPrice > bridgewoodHotelPrice){
            System.out.println(bridgewoodHotel.getName() + ", Total Rate: $" + bridgewoodHotelPrice);
        } else {
            System.out.println(ridgewoodHotel.getName() + ", Total Rate: $" + ridgewoodHotelPrice);
        }
    }

    public void bestRatedHotel(){
        int lakewoodHotelPrice = 0;
        int bridgewoodHotelPrice = 0;
        int ridgewoodHotelPrice = 0;

        Hotel lakewoodHotel = hotels.get("Lakewood");
        Hotel bridgewoodHotel = hotels.get("Bridgewood");
        Hotel ridgewoodHotel = hotels.get("Ridgewood");
        if (lakewoodHotel.getRatings() > bridgewoodHotel.getRatings() && ridgewoodHotel.getRatings() < lakewoodHotel.getRatings()){
            for (String day : days){
                if (isWeekday(day)){
                    lakewoodHotelPrice += lakewoodHotel.getWeekdayRegularRate();
                } else {
                    lakewoodHotelPrice += lakewoodHotel.getWeekendRegularRate();
                }
            }
            System.out.println(lakewoodHotel.getName() + " & Total Rate $ " + lakewoodHotelPrice);
        } else if (bridgewoodHotel.getRatings() > lakewoodHotel.getRatings() && ridgewoodHotel.getRatings() < bridgewoodHotel.getRatings()){
            for (String day : days){
                if (isWeekday(day)){
                    bridgewoodHotelPrice += bridgewoodHotel.getWeekdayRegularRate();
                } else {
                    bridgewoodHotelPrice += bridgewoodHotel.getWeekendRegularRate();
                }
            }
            System.out.println(bridgewoodHotel.getName() + " & Total Rate $ " + bridgewoodHotelPrice);
        } else {
            for (String day : days){
                if (isWeekday(day)){
                    ridgewoodHotelPrice += ridgewoodHotel.getWeekdayRegularRate();
                } else {
                    ridgewoodHotelPrice += ridgewoodHotel.getWeekendRegularRate();
                }
            }
            System.out.println(ridgewoodHotel.getName() + " & Total Rate $ " + ridgewoodHotelPrice);
        }
    }

    public static void main(String[] args) {

        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        reservationSystem.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservationSystem.addHotel("Bridgewood", 160, 60, 110, 50, 4);
        reservationSystem.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        Hotel lakewoodHotel = reservationSystem.hotels.get("Lakewood");
        Hotel bridgewoodHotel = reservationSystem.hotels.get("Bridgewood");
        Hotel ridgewoodHotel = reservationSystem.hotels.get("Ridgewood");

        reservationSystem.acceptInput();
        reservationSystem.findCheapHotel();
        reservationSystem.bestRatedHotel();
    }

}
