package com.example.demo.Bootstrap;

import com.example.demo.Domain.Address;
import com.example.demo.Domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserBootstrap {

    public static List<User> createStudents() {
        List<User> students = new ArrayList<>();
        students.add(new User("Jakub", "Kolacz", 278946, "Madafaka1!",
                797793336, "kolacz.jakub2@gmail.com", "Student",
                new Address("Tenczynek", "32-067", "Na Skalki", 7), null));
        students.add(new User("Sylwia", "Gargula", 278909, "Madafaka1!",
                695291061, "sylwia.gargula@wp.pl", "Student",
                new Address("Krakow", "32-067", "Twardowskiego", 55), null));
        students.add(new User("Jan", "Kowalski", 278969, "Madafaka1!",
                123456789, "jankowalski@gmail.com", "Student",
                new Address("Krakow", "32-067", "Al.Jana Pawła", 30), null));
        students.add(new User("Grazyna", "Nowak", 007, "Madafaka1!",
                123456789, "gnowak@gmail.com", "Office",
                new Address("Krakow", "32-067", "Al.Pokoju", 15), null));
        return students;
    }
}
