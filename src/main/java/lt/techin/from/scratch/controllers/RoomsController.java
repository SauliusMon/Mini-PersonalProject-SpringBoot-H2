package lt.techin.from.scratch.controllers;

import lt.techin.from.scratch.dao.UserRepository;
import lt.techin.from.scratch.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class RoomsController {

    private UserRepository userRepository;

    public RoomsController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Comparator<Person> customComparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            //Swapped places for teachers to be on top
            return o1.getRole().equals(o2.getRole()) ? 0 : o2.getRole().compareTo(o1.getRole());
        }
    };

    @GetMapping("/rooms")
    public String getDefaultRoom(Model model) {
        List<Person> room1 = userRepository.findAll().stream().filter(p -> p.getRoom() == 1).sorted(customComparator).collect(Collectors.toList());
        model.addAttribute("room1",room1);

        List<Person> room2 = userRepository.findAll().stream().filter(p -> p.getRoom() == 2).sorted(customComparator).collect(Collectors.toList());
        model.addAttribute("room2", room2);

        List<Person> room3 = userRepository.findAll().stream().filter(p -> p.getRoom() == 3).sorted(customComparator).collect(Collectors.toList());
        model.addAttribute("room3", room3);

        List<Person> room4 = userRepository.findAll().stream().filter(p -> p.getRoom() == 4).sorted(customComparator).collect(Collectors.toList());
        model.addAttribute("room4", room4);
        return "rooms";
    }

//    @PostMapping("rooms")
//    public String getLogout(@ModelAttribute Person person) {
//        System.out.println("asd" + person.getName() + person.getPassword());
//        userRepository.save(person);
//        return "rooms";
//    }
}
