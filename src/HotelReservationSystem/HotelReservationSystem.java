package HotelReservationSystem;

import java.util.HashMap;

public class HotelReservationSystem {

    HashMap<String, Hotel> hotels = new HashMap<>();

    public void addHotel(String name, int weekdayRegularRate, int weekendRegularRate){
        hotels.put(name, new Hotel(name, weekdayRegularRate, weekendRegularRate));
    }

    public static void main(String[] args) {

        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        reservationSystem.addHotel("Lakewood", 110, 90);
        reservationSystem.addHotel("Bridgewood", 160, 60);
        reservationSystem.addHotel("Ridgewood", 220, 150);

        Hotel lakewoodHotel = reservationSystem.hotels.get("Lakewood");
        System.out.println(lakewoodHotel.toString());
        Hotel bridgewoodHotel = reservationSystem.hotels.get("Bridgewood");
        System.out.println(bridgewoodHotel.toString());
        Hotel ridgewoodHotel = reservationSystem.hotels.get("Ridgewood");
        System.out.println(ridgewoodHotel.toString());
    }

}
