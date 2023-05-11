import service.Leaderboard;

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();

        leaderboard.upsertUser("paras", "paras@example.com", "India");
        leaderboard.upsertUser("ourav", "sourav@example.com", "India");
        leaderboard.upsertUser("abhi", "abhi@example.com", "Canada");
        leaderboard.upsertUser("alok", "alok@example.com", "Canadaa");
        leaderboard.upsertUser("warner", "warner@example.com", "USA");

        leaderboard.upsertScore("paras@example.com", 100);
        leaderboard.upsertScore("sourav@example.com", 50);
        leaderboard.upsertScore("abhi@example.com", 75);
        leaderboard.upsertScore("alok@example.com", 75);
        leaderboard.upsertScore("warner@example.com", 25);

        List<String> topUsers = leaderboard.getTop(2, null);
        System.out.println("Top 2 users globally: " + topUsers);

        topUsers = leaderboard.getTop(1, "Canada");
        System.out.println("Top 1 user in Canada: " + topUsers);

        List<String> usersWithScore = leaderboard.getUsersWithScore(50);
        System.out.println("Users with score 50: " + usersWithScore);

        List<String> matchingUsers = leaderboard.search(null, 75, "Canada");
        System.out.println("Matching users: " + matchingUsers);
    }
}

/**
 * how to proceed MCR of Interview
 *
 * Flow :
 * DRIVER ----calls---> SERVICE ----has ENTITY--has in-mem database---- perform task-- return to DRIVER-->
 *
 * Step-1 : Have model/entity package with all models
 *          a. getter-setters
 *          b. constructors
 *          c. hash, equals, toString
 * Step-2 : Have service package with one class of service
 *          a. It has dummy created database
 *          b. It will perform all functions and return data(if any) to driver/mail class
 * Step-3 : have one Driver class(point of interaction of CMD)
 *          a. create dummy data by calling service
 *          b. get command and call service to perform
 * */
