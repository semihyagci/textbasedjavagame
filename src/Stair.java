public class Stair {

    private int stairID;
    private Level interiorLevel;
    private Level exteriorLevel;
    private Room interiorRoom;
    private Room exteriorRoom;

    public int getStairID() {
        return stairID;
    }

    public void setStairID(int stairID) {
        this.stairID = stairID;
    }

    public Level getInteriorLevel() {
        return interiorLevel;
    }

    public void setInteriorLevel(Level interiorLevel) {
        this.interiorLevel = interiorLevel;
    }

    public Level getExteriorLevel() {
        return exteriorLevel;
    }

    public void setExteriorLevel(Level exteriorLevel) {
        this.exteriorLevel = exteriorLevel;
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

    public Stair(int stairID, Level interiorLevel, Level exteriorLevel, Room interiorRoom, Room exteriorRoom) {
        this.stairID = stairID;
        this.interiorLevel = interiorLevel;
        this.exteriorLevel = exteriorLevel;
        this.interiorRoom = interiorRoom;
        this.exteriorRoom = exteriorRoom;
    }
}
