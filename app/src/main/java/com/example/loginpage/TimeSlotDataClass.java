package com.example.loginpage;

public class TimeSlotDataClass {

    private String day ;
    private String time_slot;
    private String available_slots;
    private String restaurant_id ;
    private String restaurant_name ;

    public TimeSlotDataClass(String day, String time_slot, String available_slots, String restaurant_id, String restaurant_name) {
        this.day = day;
        this.time_slot = time_slot;
        this.available_slots = available_slots;
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
    }

    public TimeSlotDataClass() {
    }

    public String getDay() {
        return day;
    }

    public String getTime_slot() {
        return time_slot;
    }

    public String getAvailable_slots() {
        return available_slots;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }
}
