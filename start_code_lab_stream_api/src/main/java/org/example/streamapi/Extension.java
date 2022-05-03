package org.example.streamapi;

import org.example.streamapi.model.Bodybuilder;
import org.example.streamapi.model.Friend;
import org.example.streamapi.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Extension {
    /*
        Given two int numbers a and b, return int [] with values that are in the range between smaller and bigger arg:
        - use IntStream.range
        - swap the argument's values without introducing a new, local variable.
    */
    public int [] streamNumbers(int a, int b) {
        // Implement me :)
        return IntStream.range(min(a,b), max(a,b)).toArray();
    }

    /*
        Given a list of users, return a User with the given user id. If there is no user with this id,
        return new user with the name "New user", given id, gender "unknown".

        (use Optional API)
    */
    public User getUserByIdOrCreateNew(List<User> users, long userId) {
        // Implement me :)
        List<User> filteredList = users.stream().filter(user -> user.getId() == userId).toList();

        if(filteredList.isEmpty()){
            return new User(userId, "New user", User.GENDER.UNKNOWN);
        }
        return filteredList.get(0);
    }

    /*
        Given List<Friend>, filter the ones who are available on Saturday and want to party:
        - getAvailableDay returns "Saturday" and
        - getActivity returns "Party"
        - define predicates and use '.and' method.
    */

    public List<String> partyWithFriends(List<Friend> friends) {
        Predicate<Friend> availableOnSaturday = friend -> friend.getAvailableDay().equals("Saturday");
        Predicate<Friend> wantsToParty = friend -> friend.getActivity().equals("Party");

        // Implement me :)
        return friends.stream()
                .filter(availableOnSaturday.and(wantsToParty))
                .map(friend -> friend.getName())
                .toList();

    }

    /*
        Return names of sorted bodybuilders.

        Sort List<Bodybuilder> using your custom comparator.

        Write a comparator for type BodyBuilder that will sort bodybuilders by:
        - who can lift more,
        - then who is younger,
        - then name alphabetically.
     */
    public List<String> sortBodybuilders(List<Bodybuilder> bodybuilders) {
        // Implement me :)
        Comparator<Bodybuilder> byLift = Comparator
                .comparing(Bodybuilder::getLift, (s1, s2) -> s2.compareTo(s1));
        Comparator<Bodybuilder> byAge = Comparator
                .comparing(Bodybuilder::getAge);
        Comparator<Bodybuilder> byName = Comparator
                .comparing(Bodybuilder::getName);


        return bodybuilders.stream()
                .sorted(byName)
                .sorted(byAge)
                .sorted(byLift)
                .map(Bodybuilder::getName)
                .toList();
    }

}
