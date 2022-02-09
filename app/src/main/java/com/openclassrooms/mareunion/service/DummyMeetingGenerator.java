package com.openclassrooms.mareunion.service;


import com.openclassrooms.mareunion.R;
import com.openclassrooms.mareunion.model.Meeting;

import com.openclassrooms.mareunion.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room("Salle A", R.color.bright_red),
            new Room("Salle B", R.color.teal_700),
            new Room("Salle C", R.color.bright_purple),
            new Room("Salle D", R.color.teal_200),
            new Room("Salle E", R.color.red),
            new Room("Salle F", R.color.hot_pink),
            new Room("Salle G", R.color.bright_orange),
            new Room("Salle H", R.color.bright_green),
            new Room("Salle I", R.color.teal_200),
            new Room("Salle J", R.color.red)
    );

    public static List<String> PARTICIPANTS_EMAIL = Arrays.asList(
            "alex@lamzone.com",
            "ager@lamzone.com",
            "skretel@lamzone.com",
            "hypia@lamzone.com",
            "gerrard@lamzone.com"
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(DUMMY_ROOMS.get(0),"Subject C", "05/01/2022", "14:00", "15:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(6),"Subject A", "07/01/2022", "13:00", "14:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(7),"Subject D", "23/02/2022", "11:00", "13:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(8),"Subject B", "18/05/2022", "12:00", "14:00", PARTICIPANTS_EMAIL),
            new Meeting(DUMMY_ROOMS.get(2),"Subject E", "07/07/2022", "18:00", "18:30", PARTICIPANTS_EMAIL)
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }

    public static List<Room> generateRooms() {
        return new ArrayList<>(DUMMY_ROOMS);
    }

    public static List<String> generateParticipantsEmail() {
        return new ArrayList<>(PARTICIPANTS_EMAIL);
    }

}
