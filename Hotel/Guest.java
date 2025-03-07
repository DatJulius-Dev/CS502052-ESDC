import java.util.*;
import java.io.*;

public class Guest implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String ssn;
    List<String> bookedRoom = new ArrayList<>();

    public Guest(String name){
        this.name = name;
        this.ssn = null;
    }

    public Guest(String name, String ssn){
        this.name = name;
        this.ssn = ssn;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public List<String> getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(List<String> bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public void addRoom(String r){
        bookedRoom.add(r);
    }

    @Override
    public String toString(){
        return "Username: " + name + " , ssn: " + ssn + listBookedRoom();
    }

    public String listBookedRoom(){
        StringBuilder strb = new StringBuilder();
        for(String str: bookedRoom){
            strb.append("\t" + str + "\n");
        }
        return strb.toString();
    }
}