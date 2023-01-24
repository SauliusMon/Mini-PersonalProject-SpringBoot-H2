package lt.techin.from.scratch.services;

import lt.techin.from.scratch.dao.UserRepository;
import lt.techin.from.scratch.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonService {

    UserRepository userRepository;

    public PersonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Comparator<Person> customComparator = Comparator.comparing(Person::getId);

    public void addPerson(Person person) {
        userRepository.save(person);
    }

    public List<Person> getAllUsers() {
        return userRepository.findAll().stream().sorted(customComparator).collect(Collectors.toList());
    }

    public List<Person> getUsersByName(String personName) {
        return userRepository.findAll().stream().filter(p -> p.getName().equals(personName)).sorted(customComparator).collect(Collectors.toList());
    }

    public List<Person> getDeletedUsersAndDelete(Long personID) {
        List<Person> usersToDelete = userRepository.findAll().stream().filter(p -> Objects.equals(p.getId(), personID)).collect(Collectors.toList());
        userRepository.deleteAllInBatch(usersToDelete);
        return usersToDelete;
    }

    private final Comparator<Person> customComparatorByRole = (o1, o2) -> {
        //Swapped places for teachers to be on top
        return o1.getRole().equals(o2.getRole()) ? 0 : o2.getRole().compareTo(o1.getRole());
    };

    public List<Person> usersByRoomInt(int roomNumber) {
        return userRepository.findAll().stream().filter(p -> p.getRoom() == roomNumber).sorted(customComparatorByRole).collect(Collectors.toList());
    }

    public void removeUserFromRoom(Long personToRemoveID) {
        Optional<Person> personToRemoveRoom = userRepository.findById(personToRemoveID);
        if (personToRemoveRoom.isPresent()) {
            personToRemoveRoom.get().setRoom(0);
            userRepository.save(personToRemoveRoom.get());
        }
    }

    public void addUserToRoom(Long personToAddID, int roomNumber) {
        Optional<Person> personToAddRoomOpt = userRepository.findById(personToAddID);
        if (personToAddRoomOpt.isPresent()) {
            Person personToAddRoom = personToAddRoomOpt.get();
            if (personToAddRoom.getRoom() == 0) {
                personToAddRoom.setRoom(roomNumber);
                userRepository.save(personToAddRoom);
            }
        }
    }
}
