package com.riot.examples;

import com.riot.Examples.RiotExamples;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RiotExamplesTest {
    private Integer MAX_GAME_LEN = 3600;
    private Integer NUMBER_OF_EVENTS = 100;
    private Integer MAX_GOLD =20000;

    @Test
    public void testMostKills() {
        String eventLog = generateKillEventLog();
        RiotExamples test = new RiotExamples();
        System.out.println("Max kill window begins at: " +
                test.findStartOfMostDeaths(eventLog.toString()));
    }

    @Test
    public void testKillStreakDetection() {
        String eventLog = generateKillStreakLog();
        RiotExamples test = new RiotExamples();
        System.out.println("Longest kill streak window begins at: " +
                test.findStartOfLongestStreak(eventLog.toString()));
    }

    @Test
    public void testMoneyChangerPerSecond() {
        String eventLog = generateMoneyEventLog();
        RiotExamples test = new RiotExamples();
        System.out.println("Sharpest change in team money occurs at: " +
                test.findLargestMoneyFluctuation(eventLog.toString()));
    }

    private String generateKillStreakLog() {
        StringBuilder eventLog = new StringBuilder("");
        List<Integer> eventTimes =new ArrayList<>();
        for (int i=0; i<NUMBER_OF_EVENTS; i++) {
            eventTimes.add(getRandomTime());
        }
        Collections.sort(eventTimes);
        for (int i=0; i<eventTimes.size(); i++) {
            Integer eventTime = eventTimes.get(i);
            String champ = getRandomChampion(5, 0);
            eventLog.append(eventTime + " " + getRandomChampion(5, 5) + " " + champ +"\n");
            int currTime = eventTime;
            int newKillOffset =3;
            while(coinFlip() && i+1<eventTimes.size() && eventTime+newKillOffset<eventTimes.get(i+1)) {
                //any time a player gets a kill, they get a 50/50 shot to kill another 3 seconds later
                // (if it won't collide with the next event)
                eventLog.append((eventTime+newKillOffset)
                        + " " + getRandomChampion(5, 5) + " " + champ +"\n");
                newKillOffset+=3;
            }
        }
        return eventLog.toString();
    }

    private String generateKillEventLog() {
        StringBuilder eventLog = new StringBuilder("");
        List<Integer> eventTimes =new ArrayList<>();
        for (int i=0; i<NUMBER_OF_EVENTS; i++) {
            eventTimes.add(getRandomTime());
        }
        Collections.sort(eventTimes);
        for (Integer eventTime : eventTimes) {
            eventLog.append(eventTime + " " + getRandomChampion(10, 0) + " " + getRandomTeam() +"\n");
        }
        return eventLog.toString();
    }

    private String generateMoneyEventLog() {
        StringBuilder eventLog = new StringBuilder("");
        List<Integer> eventTimes =new ArrayList<>();
        for (int i=0; i<NUMBER_OF_EVENTS; i++) {
            eventTimes.add(getRandomTime());
        }
        Collections.sort(eventTimes);
        for (Integer eventTime : eventTimes) {
            eventLog.append(eventTime + " " + getRandomInt(MAX_GOLD, 0) + " " + getRandomTeam() +"\n");
        }
        return eventLog.toString();
    }

    private Integer getRandomTime() {
        Random generator = new Random();
        int time = generator.nextInt(MAX_GAME_LEN);
        return time;
    }

    private String getRandomChampion(Integer max, Integer offset) {
        Random generator = new Random();
        int champion = generator.nextInt(max) + offset;
        switch (champion) {
            case 0:
                return "Ashe";
            case 1:
                return "Garren";
            case 2:
                return "Renekton";
            case 3:
                return "Morgana";
            case 4:
                return "Veigar";
            case 5:
                return "Walrick";
            case 6:
                return "Yi";
            case 7:
                return "Trundle";
            case 8:
                return "Annie";
            case 9:
                return "Xin";
            default:
                return "Exception";

        }
    }

    private String getRandomTeam() {
        Random generator = new Random();
        int team = generator.nextInt(2);
        return team==0 ? "Blue" : "Red";
    }

    private Boolean coinFlip() {
        Random generator = new Random();
        int team = generator.nextInt(2);
        return team==0;
    }

    private Integer getRandomInt(Integer max, Integer offset) {
        Random generator = new Random();
        return generator.nextInt(max)+offset;
    }
}
