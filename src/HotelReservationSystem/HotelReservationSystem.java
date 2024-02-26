package HotelReservationSystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservationSystem {

    HashMap<String, Hotel> hotels = new HashMap<>();
    String userType;
    ArrayList<String> days = new ArrayList<>();

    public void addHotel(String name, int weekdayRegularRate, int weekendRegularRate, int weekdayRewardRate, int weekendRewardRate, int ratings){
        hotels.put(name, new Hotel(name, weekdayRegularRate, weekendRegularRate, weekdayRewardRate, weekendRewardRate, ratings));
    }

    public void validateInput(String dateRange) throws IllegalArgumentException {
        Arrays.stream(dateRange.split(", "))
                .map(dateString -> {
                    try {
                        return dateString.substring(0, 3).toUpperCase() + dateString.substring(3);
                    } catch (IndexOutOfBoundsException e) {
                        throw new IllegalArgumentException("Invalid Date format: " + dateString);
                    }
                })
                .forEach(dateString -> {
                    try {
                        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("ddMMMuuuu", Locale.ENGLISH));
                        DayOfWeek dayOfWeek = date.getDayOfWeek();
                        days.add(dayOfWeek.toString().substring(0, 3).toLowerCase());
                    } catch (DateTimeParseException | IndexOutOfBoundsException e) {
                        throw new IllegalArgumentException("Invalid Date format: " + dateString);
                    }
                });
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
                || day.equalsIgnoreCase("thu") || day.equalsIgnoreCase("fri");
    }

    // Method to find the cheapest hotel rate
    public void findCheapHotel() {
        Hotel lakewoodHotel = hotels.get("Lakewood");
        Hotel bridgewoodHotel = hotels.get("Bridgewood");
        Hotel ridgewoodHotel = hotels.get("Ridgewood");

        int lakewoodHotelPrice = days.stream()
                .mapToInt(day -> isWeekday(day) ?
                        (userType.equalsIgnoreCase("regular") ? lakewoodHotel.getWeekdayRegularRate() : lakewoodHotel.getWeekdayRewardRate()) :
                        (userType.equalsIgnoreCase("regular") ? lakewoodHotel.getWeekendRegularRate() : lakewoodHotel.getWeekendRewardRate()))
                .sum();

        int bridgewoodHotelPrice = days.stream()
                .mapToInt(day -> isWeekday(day) ?
                        (userType.equalsIgnoreCase("regular") ? bridgewoodHotel.getWeekdayRegularRate() : bridgewoodHotel.getWeekdayRewardRate()) :
                        (userType.equalsIgnoreCase("regular") ? bridgewoodHotel.getWeekendRegularRate() : bridgewoodHotel.getWeekendRewardRate()))
                .sum();

        int ridgewoodHotelPrice = days.stream()
                .mapToInt(day -> isWeekday(day) ?
                        (userType.equalsIgnoreCase("regular") ? ridgewoodHotel.getWeekdayRegularRate() : ridgewoodHotel.getWeekdayRewardRate()) :
                        (userType.equalsIgnoreCase("regular") ? ridgewoodHotel.getWeekendRegularRate() : ridgewoodHotel.getWeekendRewardRate()))
                .sum();

        Map<Integer, String> hotelPrices = new HashMap<>();
        hotelPrices.put(lakewoodHotelPrice, lakewoodHotel.getName());
        hotelPrices.put(bridgewoodHotelPrice, bridgewoodHotel.getName());
        hotelPrices.put(ridgewoodHotelPrice, ridgewoodHotel.getName());

        int cheapestPrice = hotelPrices.keySet().stream().min(Integer::compareTo).orElse(0);
        String cheapestHotelName = hotelPrices.get(cheapestPrice);

        System.out.println(cheapestHotelName + ", Total Rate: $" + cheapestPrice);
    }

    public void bestRatedHotel() {
        Map<String, Hotel> hotelsMap = new HashMap<>();
        hotelsMap.put("Lakewood", hotels.get("Lakewood"));
        hotelsMap.put("Bridgewood", hotels.get("Bridgewood"));
        hotelsMap.put("Ridgewood", hotels.get("Ridgewood"));

        Map<Hotel, Integer> hotelPrices = hotelsMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getValue,
                        entry -> {
                            Hotel hotel = entry.getValue();
                            int totalPrice = 0;
                            for (String day : days) {
                                if (isWeekday(day)) {
                                    totalPrice += userType.equalsIgnoreCase("regular") ? hotel.getWeekdayRegularRate() : hotel.getWeekdayRewardRate();
                                } else {
                                    totalPrice += userType.equalsIgnoreCase("regular") ? hotel.getWeekendRegularRate() : hotel.getWeekendRewardRate();
                                }
                            }
                            return totalPrice;
                        },
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        Hotel bestRatedHotel = hotelPrices.entrySet().stream()
                .max(Map.Entry.comparingByKey(Comparator.comparing(Hotel::getRatings)))
                .map(Map.Entry::getKey)
                .orElse(null);

        if (bestRatedHotel != null) {
            int bestRatedHotelPrice = hotelPrices.get(bestRatedHotel);
            System.out.println(bestRatedHotel.getName() + " & Total Rate $ " + bestRatedHotelPrice);
        }
    }

    public static void main(String[] args) {

        HotelReservationSystem reservationSystem = new HotelReservationSystem();

        reservationSystem.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservationSystem.addHotel("Bridgewood", 160, 60, 110, 50, 4);
        reservationSystem.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        reservationSystem.acceptInput();
        reservationSystem.findCheapHotel();
        reservationSystem.bestRatedHotel();
    }

}
