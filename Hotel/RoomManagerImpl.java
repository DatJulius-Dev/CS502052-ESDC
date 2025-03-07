import java.util.*;
import java.rmi.*;

public class RoomManagerImpl implements RoomManager{
    private List<Guest> listGuest;
    private List<Room> listRoom;
    private List<User> listUser;
    private User currUser;

    protected RoomManagerImpl() throws RemoteException{
        super();
        listGuest = new ArrayList<>();
        listRoom = new ArrayList<>();
        listUser = new ArrayList<>();
        currUser = null;
        initializeRooms();
        initializeUser();
    }

    private void initializeRooms(){
        listRoom.add(new Room(0, 55, 10));
        listRoom.add(new Room(1, 75, 20));
        listRoom.add(new Room(2, 80, 5));
        listRoom.add(new Room(3, 150, 3));
        listRoom.add(new Room(4, 230, 2));
    }
    private void initializeUser(){
        listUser.add(new User("Manager", "123456", true));
        listUser.add(new User("Receptionist", "123456", true));
    }

    @Override
    public String login(String username, String password) throws RemoteException{
        for (User user : listUser){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                currUser = user;
                System.out.println("Username: " + username + "logged in.");
                return getUserToken();
            }
        }
        return null;
    }

    public boolean isLoggedIn(){
        return currUser != null;
    }

    @Override
    public String listAvailableRoom() throws RemoteException{
        StringBuilder strb = new StringBuilder();
        for (Room room: listRoom){
            strb.append(room.getLeft()).append("room of type").append(room.getType()).append("are available for").append(room.getCost()).append("per the night.");
        }
        return strb.toString();
    }

    @Override
    public boolean bookRoom(int roomType, String guestName, String guestSSN) throws RemoteException{
        for (Room room: listRoom){
            if((room.getType()) == roomType && room.getLeft() > 0){
                room.checkIn();
                if(guestSSN == null){
                    guestSSN = String.valueOf(System.currentTimeMillis());
                }
                Guest temp = new Guest(guestName, guestSSN);
                temp.addRoom("Room type: " + roomType);
                listGuest.add(temp);
                return true;
            }
        }
        return false;
    }

    public List<Guest> listGuest() throws RemoteException{
        if (currUser != null && currUser.isManager()){
            return listGuest;
        }
        throw new RemoteException("Access denied: Only Managers can view guest information.");
    }

    @Override
    public void logOut() throws RemoteException{
        System.out.println("\nUser: " + currUser.getUsername() + " logged out.");
        currUser = null;
    }

    @Override
    public boolean isManager() throws RemoteException {
        return currUser != null && currUser.isManager();
    }

    @Override
    public String getUserToken() throws RemoteException {
        if (currUser == null) {
            return null;
        }
        return currUser.isManager() ? "MA" : "RE";
    }
}
