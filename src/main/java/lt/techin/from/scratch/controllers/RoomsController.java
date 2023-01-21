package lt.techin.from.scratch.controllers;

import lt.techin.from.scratch.dao.UserRepository;
import lt.techin.from.scratch.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class RoomsController {

    private final UserRepository userRepository;

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

    @GetMapping("rooms")
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

    @PostMapping("/remove-user-from-room")
    public String removePersonFromRoom(@RequestParam("personId") Long personToRemoveID, RedirectAttributes redirectAttributes) {
        Optional<Person> personToRemoveRoom = userRepository.findById(personToRemoveID);
        if (personToRemoveRoom.isPresent()) {
            personToRemoveRoom.get().setRoom(0);
            userRepository.save(personToRemoveRoom.get());
        }
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room1")
    public String addPersonToRoom1(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            Optional<Person> personToAddRoomOpt = userRepository.findById(personID);
            if (personToAddRoomOpt.isPresent()) {
                Person personToAddRoom = personToAddRoomOpt.get();
                if (personToAddRoom.getRoom() == 0) {
                    personToAddRoom.setRoom(1);
                    userRepository.save(personToAddRoom);
                }
            }
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room2")
    public String addPersonToRoom2(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            Optional<Person> personToAddRoomOpt = userRepository.findById(personID);
            if (personToAddRoomOpt.isPresent()) {
                Person personToAddRoom = personToAddRoomOpt.get();
                if (personToAddRoom.getRoom() == 0) {
                    personToAddRoom.setRoom(2);
                    userRepository.save(personToAddRoom);
                }
            }
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room3")
    public String addPersonToRoom3(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            Optional<Person> personToAddRoomOpt = userRepository.findById(personID);
            if (personToAddRoomOpt.isPresent()) {
                Person personToAddRoom = personToAddRoomOpt.get();
                if (personToAddRoom.getRoom() == 0) {
                    personToAddRoom.setRoom(3);
                    userRepository.save(personToAddRoom);
                }
            }
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room4")
    public String addPersonToRoom4(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            Optional<Person> personToAddRoomOpt = userRepository.findById(personID);
            if (personToAddRoomOpt.isPresent()) {
                Person personToAddRoom = personToAddRoomOpt.get();
                if (personToAddRoom.getRoom() == 0) {
                    personToAddRoom.setRoom(4);
                    userRepository.save(personToAddRoom);
                }
            }
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("display-mainhub")
    public String displayRooms(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("attribute name", "Successful switch!");
        return "redirect:mainHub";
    }
}
