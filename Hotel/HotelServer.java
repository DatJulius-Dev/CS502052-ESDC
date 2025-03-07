import java.rmi.server.*;
import java.rmi.registry.*;

public class HotelServer{
    public HotelServer(){

    }
    public static void main(String[] args){
        try{
            int index = 0;
            int port = Integer.parseInt(args[index++]);

            RoomManagerImpl obj = new RoomManagerImpl();
            RoomManager skeleton = (RoomManager) UnicastRemoteObject.exportObject(obj, port);
            Registry registry = LocateRegistry.getRegistry(port);
            registry.rebind("Hotel", skeleton);
            System.out.println("Server is ready to launch!");
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }
}