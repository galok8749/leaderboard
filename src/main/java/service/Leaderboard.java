package service;

import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class Leaderboard {
    private final HashMap<String, User> emailUserMap;

    public Leaderboard() {
        emailUserMap = new HashMap<>();
    }

    public void upsertUser(String name, String email, String country) {
        if (emailUserMap.containsKey(email)) {
            emailUserMap.get(email).setName(name);
            emailUserMap.get(email).setCountry(country);
        } else {
            User user = new User(name, email, country);
            emailUserMap.put(email, user);
        }
    }

    public void upsertScore(String email, int score) {
        if (!emailUserMap.containsKey(email)) {
            System.out.println("User does not exit");
            return;
        }
        emailUserMap.get(email).setScore(score);
    }

    public List<String> getTop(int k, String country) {
        List<User> matchingUser = new ArrayList<>();

        for (User user : emailUserMap.values()) {
            if (country == null || user.getCountry().equals(country))
                matchingUser.add(user);
        }

//        Collections.sort(matchingUser, new Comparator<User>() {
//            @Override
//            public int compare(User u1, User u2) {
//                return u2.getScore() - u1.getScore();
//            }
//        });
        Collections.sort(matchingUser, (u1, u2)-> u2.getScore() - u1.getScore());

        return matchingUser.stream().limit(k).map(User::getEmail).collect(Collectors.toList());
    }

    public List<String> getUsersWithScore(int score) {
        List<String> matchingUser = new ArrayList<>();

        for (User user : emailUserMap.values()) {
            if (user.getScore() == score)
                matchingUser.add(user.getEmail());
        }
        return matchingUser;
    }

    public List<String> search(String name, int score, String country) {
        List<User> matchingUser = new ArrayList<>(emailUserMap.values());

        if (name != null) {
            matchingUser = matchingUser.stream()
                    .filter(user -> user.getName() != null && user.getName().equals(name))
                    .collect(Collectors.toList());
        }

        if (score > 0) {
            matchingUser =matchingUser.stream()
                    .filter(user -> user.getScore() == score)
                    .collect(Collectors.toList());
        }

        if (country != null) {
            matchingUser = matchingUser.stream()
                    .filter(user -> user.getCountry().equals(country))
                    .collect(Collectors.toList());
        }

        return matchingUser.stream().map(User::getEmail).collect(Collectors.toList());
    }
}
