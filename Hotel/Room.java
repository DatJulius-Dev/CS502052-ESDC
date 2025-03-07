public class Room{
    private int type;
    private int cost;
    private int numOfRoom;
    private int left;

    public Room(int type, int cost, int numOfRoom){
        this.type = type;
        this.cost = cost;
        this.numOfRoom = numOfRoom;
        this.left = numOfRoom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNumOfRoom() {
        return numOfRoom;
    }

    public void setNumOfRoom(int numOfRoom) {
        this.numOfRoom = numOfRoom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void checkIn(){
        this.left--;
    }

    public void checkOut(){
        this.left++;
    }

    @Override
    public String toString(){
        return "Room[ type= " + type + " , cost = " + cost + " , num of rooms = " + numOfRoom + "left = " + left + "]";
    }
}
