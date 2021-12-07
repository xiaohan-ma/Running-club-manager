package csc8011;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public final class ClubIO {

    //Run
    public void run () throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        int number;

        Club club = new Club("");
        menuOptions();

        //Main menu options, five options in total
        boolean on = true;
        while(on){
            number = s.nextInt();
            switch(number){
                case 1:
                    //Option to enter club name
                    club.setClubName(enterClub());
                    System.out.println("***Club name set as: " + club.getClubName() + "***\n\n" + "You have returned to the main menu, please select another option:");
                    break;
                case 2:
                    //Option to read CSV file
                    parseList(readCSV(), club);
                    System.out.println("***CSV file read***\n" + "You have returned to the main menu, please select another option:");
                    break;
                case 3:
                    //Option to print details of club name, runners and their times
                    if(club.getClubName() == ""){
                        System.out.println("No club name has been entered. Please select option '1' in main menu to do this\n" + "You have returned to the main menu");
                    } else {
                        printAllDetails(club.runnerList, club);
                        System.out.println("***Finished retrieving data***\n" + "You have returned to the main menu, please select another option:");
                    }
                    break;
                case 4:
                    //Print data on average time (in mins and secs) and full details of runner with the fastest time.
                    if (club.runnerList.size() == 0){
                        System.out.println("No retrieved data on runners available. Please select option '2' in main menu to do this\n" + "You have returned to the main menu");
                    } else {
                        filterData(club);
                        System.out.println("***Finished retrieving data***\n" + "You have returned to the main menu, please select another option:");
                    }
                    break;
                case 5:
                    //Option '5' - exit the programme
                    System.out.println("You are exiting the programme");
                    on = false;
                    break;
                default:
                    System.out.println("This is the wrong input");
            }
        }
    }

    //Print/display menu options to user
    public void menuOptions() {
        System.out.println(
                "- Running Performance Tracker -\n" +
                "Input '1' to enter club name\n" +
                "Input '2' to get runners information from the CSV file\n" +
                "Input '3' to get a summary of the club's details, runners and their times\n" +
                "Input '4' to get the current average time and details of the fastest runner\n" +
                "Input '5' to exit the programme\n" +
                "Please enter a number below:"
        );
    }

    //Option '1' - enter club name method
    public String enterClub(){
        Scanner clubSc = new Scanner(System.in);
        String clubName;

        System.out.println("Please enter the club name:");
        clubName = clubSc.nextLine();

        return clubName;
    }

    //Option '2' - read CSV file for runners information methods
    public ArrayList<String> readCSV() throws FileNotFoundException {
        File csvFile = new File(Club.class.getResource("race.csv").getFile());
        Scanner fileScanner = new Scanner(csvFile);

        ArrayList<String> csvList = new ArrayList<>();

        while(fileScanner.hasNextLine()){
            csvList.add(fileScanner.nextLine());
        }
        csvList.remove(0);

        return csvList;
    }

    public void parseList(ArrayList<String> csvList, Club club){
        for (String x : csvList){
            String[] line = x.split(",");
            club.runnerList.add(new Runner(line[0], line[1], Integer.parseInt(line[2])));
        }
    }

    //Option '3' - print details of club, runner and times in specific format
    public void printAllDetails(ArrayList<Runner> runnerList, Club club){
            String clubName = club.getClubName();
            if(runnerList.size() == 0){
                System.out.println(
                        "Club name: " + clubName + "\n" +
                        "[No available data on runners. Select option '2' in main menu to retrieve information from any available CSV file]");
            } else {
                System.out.println(
                        "Club name: " + clubName);
                for (Runner runner : runnerList){
                    String runnerDetail = "Member Id: " + runner.getMemberID() + " Name: " + runner.getName() + " Time: " + club.convertTime(runner.getTime());
                    System.out.println(runnerDetail);
                }
            }
    }

    //Option '4' - filter data to give details of the fastest runner, and the average time
    public void filterData(Club club){
        Runner runner = club.getFastestRunner();
        int averageTime = club.getAverageTime();
        System.out.println("Fastest runner: " + runner.getName()+ " (" + runner.getMemberID() + "), " + club.convertTime(runner.getTime()) + "\n" +
                "Average time: " + club.convertTime(averageTime));
    }



    public static void main(String[] args) throws FileNotFoundException {
        ClubIO club = new ClubIO();
        //To run the programme
        club.run();
    }

}
