
public class Door {
    private int doorID;
    private Room interiorRoom;
    private Room exteriorRoom;

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public Room getInteriorRoom() {
        return interiorRoom;
    }

    public void setInteriorRoom(Room interiorRoom) {
        this.interiorRoom = interiorRoom;
    }

    public Room getExteriorRoom() {
        return exteriorRoom;
    }

    public void setExteriorRoom(Room exteriorRoom) {
        this.exteriorRoom = exteriorRoom;
    }


    public Door(int doorID, Room interiorRoom, Room exteriorRoom) {
        this.doorID = doorID;
        this.interiorRoom = interiorRoom;
        this.exteriorRoom = exteriorRoom;
    }
}
