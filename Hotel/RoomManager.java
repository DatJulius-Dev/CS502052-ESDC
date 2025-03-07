import java.util.*;
import java.rmi.*;

public interface RoomManager extends Remote{
    String login(String username, String password) throws RemoteException;
    boolean isLoggedIn() throws RemoteException;
    List<Guest> listGuest() throws RemoteException;
    String listAvailableRoom() throws RemoteException;
    void logOut() throws RemoteException;
    boolean isManager() throws RemoteException;
    boolean bookRoom(int roomType, String guestName, String guestSNN) throws RemoteException;
    String getUserToken() throws RemoteException;
}
