package com.riot.Examples;

import java.util.*;

public class RiotExamples {

    /**
     * Given a time series [1,4,5,8] when teemo attacks, and a duration of how long his poison lasts
     * how long will Ashe be poisoned?
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        //check for bad inputs
        if (timeSeries.length<1 || duration<1) {
            return 0;
        }
        //define a total length poison count
        Integer timePoisoned =0;

        //for each input in the time series
        //if the next input is two or more away, add two to the poison duration
        //else, add the delta between this time stamp and the next one
        for (int i=0; i<timeSeries.length; i++) {
            if (i==timeSeries.length-1) {
                //last one, just add duration
                timePoisoned+=duration;
            } else {
                Integer delta = timeSeries[i+1]-timeSeries[i];
                timePoisoned+=Math.min(delta, duration);
            }
        }
        return timePoisoned;
    }

    /**
     * Given a stream where each line is in the form $time $kill $team
     * Find the 30 second period with the most deaths
     * Can you count on champ names never having space?
     * Can I assume this log is sorted by time?
     * Hackerrank #2: You get a list of $time $kill $team. Find a 30 seconds periods with biggest number of deaths
     */
    public class KillEvent {
        public Integer time;
        public String playerKilled;
        public String playerWhoKilled;
        public String team;
    }
    public Integer findStartOfMostDeaths (String input) {
        //check edge cases
        if (input== null || input.equals("")) {
            //error
            return null;
        }
        Integer timeWindow = 30;
        Integer currentMaxDeaths =0;
        Integer maxDeathTimeStamp =0;
        Integer currentWindowKillCount =0;
        Scanner scanner = new Scanner(input);
        //setup a queue to hold the current rolling 30 second window
        Queue<KillEvent> killTimes = new LinkedList<>();
        while (scanner.hasNextInt()) {
            //get the next line of the log
            KillEvent event = new KillEvent();
            event.time = scanner.nextInt();
            event.playerKilled = scanner.next();
            event.team = scanner.next();

            System.out.println("KILL at: " + event.time + " Player: " + event.playerKilled
                + " Team: " + event.team);
            //while the queue is not empty, check if the next item is still in the current window
            while (!killTimes.isEmpty() && event.time- killTimes.peek().time > timeWindow) {
                //if the item is in the current window, proceed
                //if it is not, remove the front item from the queue, decrement kill count, and try again
                killTimes.remove();
            }

            //no matter what, add the item and increment the kill count
            killTimes.add(event);

            //check if the new kill count is greater than the current Max
            if (killTimes.size()>currentMaxDeaths) {
                //if it is, set the new max and record the time of the front of the queu
                currentMaxDeaths++;
                maxDeathTimeStamp = killTimes.peek().time;
            }
        }
        return maxDeathTimeStamp;
    }

    /**
     * Hackerrank #3: You get a list of $time $kill $player. Find players and periods, with no longer than 15 seconds holes, with biggest number of deaths.
     */
    //This returns the earliest streak - what about later streaks that are equal size?
    //can you assume the log is sorted by time?
    public Integer findStartOfLongestStreak (String input) {
        //check edge cases
        if (input==null || input.equals("")) {
            //error
            return null;
        }
        Scanner scanner = new Scanner (input);
        //define kill streak window
        Integer KILL_STREAK_WINDOW=15;
        //define the max kill streak
        Stack<KillEvent> maxKillStreak = new Stack<>();
        //Define a map of active streaks (stack of events) for all detected players
        Map<String, Stack<KillEvent>> killStreaks = new HashMap<>();

        //while there are still events
        while (scanner.hasNext()) {
            //extract an event
            KillEvent event = new KillEvent();
            event.time = scanner.nextInt();
            event.playerKilled = scanner.next();
            event.playerWhoKilled = scanner.next();
            System.out.println("Time: " + event.time + " " + event.playerWhoKilled + " killed " +event.playerKilled);
            //is there an active streak for this player?
            if (!killStreaks.containsKey(event.playerWhoKilled)) {
                //if not, create it
                killStreaks.put(event.playerWhoKilled, new Stack<KillEvent>());
            } else {
                //if there is
                //is the most recent event within 15 seconds?
                if (event.time-killStreaks.get(event.playerWhoKilled).peek().time >15) {
                    //if it is not, the old streak is over, replace it with a new stack
                    killStreaks.put(event.playerWhoKilled, new Stack<KillEvent>());
                }
            }
            //push event onto the stack
            killStreaks.get(event.playerWhoKilled).push(event);

            //is the active streak for this player bigger than the current max?  If so, make it the new max streak;
            if (killStreaks.get(event.playerWhoKilled).size() > maxKillStreak.size()) {
                maxKillStreak=killStreaks.get(event.playerWhoKilled);
            }
        }
        //output the streak
        Integer startingTimeStamp =0;
        System.out.println("\nMAX Kill streak stack: ");
        while (!maxKillStreak.isEmpty()) {
            KillEvent event = maxKillStreak.pop();
            System.out.println("Time: " + event.time + " " + event.playerWhoKilled + " killed " +event.playerKilled);
            startingTimeStamp=event.time;
        }
        //return the last time stamp (when the streak began)
        return startingTimeStamp;
    }

    /**
     * Hackerrank #1: You get a list of $time $money $team. Find a longest period, when change of money per time was biggest
     */
    //can you assume log is sorted by time?
    public Integer findLargestMoneyFluctuation (String input) {
        //check for input edge cases
        if (input==null || input.equals("")) {
            return null; //error
        }
        //define detected max fluctuation
        Double maxFluctuation = 0.0;
        //define its time stamp
        Integer maxTimeStamp = 0;
        String maxOwningTeam = "";

        //retrieve the initial event (to set the baseline)
        Scanner scanner = new Scanner(input);
        Integer currentBalanceRed =0;
        Integer currentBalanceBlue=0;
        Integer lastTimeRed =0;
        Integer lastTimeBlue =0;

        //while there are still events
        while (scanner.hasNext()) {
            //find the change in money per unit time since the last event
            Integer currTime = scanner.nextInt();
            Integer currMoney = scanner.nextInt();
            String team = scanner.next(); //team not important
            Double changePerTime;
            System.out.println("Time: " + currTime + " team: " + team + " new balance: " + currMoney);
            if (team.toUpperCase().equals("RED")) {
                changePerTime = Math.abs((double)(currentBalanceRed-currMoney))/(double)(currTime-lastTimeRed);
                currentBalanceRed=currMoney;
                lastTimeRed=currTime;
            } else {
                changePerTime = Math.abs((double)(currentBalanceBlue-currMoney))/(double)(currTime-lastTimeBlue);
                currentBalanceBlue=currMoney;
                lastTimeBlue=currTime;
            }
            System.out.println("Change per second: " + changePerTime);
            if (changePerTime>maxFluctuation) {
                //if it exceeds the current max, set the new max and record the unit time
                maxFluctuation=changePerTime;
                maxOwningTeam = team;
                maxTimeStamp = currTime;
            }

        }


        //output the delta and the time stamp
        System.out.println("At time: " + maxTimeStamp + " Team " + maxOwningTeam + " changed their balance " +
            maxFluctuation + " per second");
        //return the timestamp
        return maxTimeStamp;
    }


}
