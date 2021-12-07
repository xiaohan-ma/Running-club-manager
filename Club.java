package csc8011;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Club {
    private String clubName;
    public ArrayList<Runner> runnerList;

    Club(String clubName){
        this.clubName= clubName;
        this.runnerList = new ArrayList<>();
    }

    //Setters and getters
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    public ArrayList<Runner> getRunnerList() {
        return runnerList;
    }

    //Convert runner time into minute/s and second/s format
    public String convertTime(int time){
        int secs = time % 60;
        int minutes = time / 60;
        return minutes + "m " + secs + "s";
    }

    //Method for finding runner with the fastest time
    public Runner getFastestRunner(){
      List<Integer> timeList = new ArrayList<>();
      for (Runner runner : runnerList){
          timeList.add(runner.getTime());
      }

      int runnerIndex = timeList.indexOf(Collections.max(timeList));
      return runnerList.get(runnerIndex);
    }

    //Method for the average time recorded, in seconds(to the nearest second)
    public int getAverageTime(){
        int sum = 0;
            for (Runner runner : runnerList) {
                sum += runner.getTime();
            }
        return Math.round(sum/runnerList.size());
    }

    @Override
    public String toString() {
        return "Club{" +
                "clubName='" + clubName + '\'' +
                ", runnerList=" + runnerList +
                '}';
    }
}
