package csc8011;

public class Runner {
    private final String memberID;
    private final String name;
    private final int time;

    Runner(String memberID, String name, int time){
        this.memberID = memberID;
        this.name = name;
        this.time = time;
    }

    //Getters
    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "memberID='" + memberID + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}

