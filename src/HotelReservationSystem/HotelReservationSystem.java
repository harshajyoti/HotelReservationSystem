package HotelReservationSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HotelReservationSystem {

    HashMap<String, Hotel> hotels = new HashMap<>();
    ArrayList<String> days = new ArrayList<>();

    public void addHotel(String name, int weekdayRegularRate, int weekendRegularRate){
        hotels.put(name, new Hotel(name, weekdayRegularRate, weekendRegularRate));
    }

    // Method to accept input from the User.
    public void acceptInput(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please Enter Date range: ");
        String userInput = scan.nextLine();
        // example - 10Sep2020(wed), 11Sep2020(thur)
        String[] dateStrings = userInput.split(", ");
        for (String dateString : dateStrings) {
            String dayOfWeek = dateString.substring(dateString.indexOf('(') + 1, dateString.indexOf(')'));
            days.add(dayOfWeek);
        }
        for (String day : days){
            System.out.println(day);
        }
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
        for (String day : days){
            if (day.equals("mon")  || day.equals("tue") || day.equals("wed") || day.equals("thur") || day.equals("fri")){
                lakewoodHotelPrice += lakewoodHotel.getWeekdayRegularRate();
                bridgewoodHotelPrice += bridgewoodHotel.getWeekdayRegularRate();
                ridgewoodHotelPrice += ridgewoodHotel.getWeekdayRegularRate();
            } else {
                lakewoodHotelPrice += lakewoodHotel.getWeekendRegularRate();
                bridgewoodHotelPrice += bridgewoodHotel.getWeekendRegularRate();
                ridgewoodHotelPrice += ridgewoodHotel.getWeekendRegularRate();
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

    public static void main(String[] args) {

        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        reservationSystem.addHotel("Lakewood", 110, 90);
        reservationSystem.addHotel("Bridgewood", 160, 60);
        reservationSystem.addHotel("Ridgewood", 220, 150);

        Hotel lakewoodHotel = reservationSystem.hotels.get("Lakewood");
        Hotel bridgewoodHotel = reservationSystem.hotels.get("Bridgewood");
        Hotel ridgewoodHotel = reservationSystem.hotels.get("Ridgewood");

        reservationSystem.acceptInput();
        reservationSystem.findCheapHotel();

    }

}
