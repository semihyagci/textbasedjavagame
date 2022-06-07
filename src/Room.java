import java.util.ArrayList;

public class Room {

    private int roomID;
    private ArrayList<Townspeople> townspeoples;
    private ArrayList<Monster> monsters;
    private Item reward;
    private Stair stair;
    private Door door1;
    private Door door2;
    private boolean monsterCheck;


    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public ArrayList<Townspeople> getTownspeoples() {
        return townspeoples;
    }

    public void setTownspeoples(ArrayList<Townspeople> townspeoples) {
        this.townspeoples = townspeoples;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public Item getReward() {
        return reward;
    }

    public void setReward(Item reward) {
        this.reward = reward;
    }

    public Stair getStair() {
        return stair;
    }

    public void setStair(Stair stair) {
        this.stair = stair;
    }

    public boolean isMonsterCheck() {
        return monsterCheck;
    }

    public void setMonsterCheck(boolean monsterCheck) {
        this.monsterCheck = monsterCheck;
    }

    public Room(int roomID, ArrayList<Townspeople> townspeoples, ArrayList<Monster> monsters, Item reward, Stair stair, Door door1, Door door2) {
        this.roomID = roomID;
        this.townspeoples = townspeoples;
        this.monsters = monsters;
        this.reward = reward;
        this.stair = stair;
        this.door1 = door1;
        this.door2 = door2;
    }

    public Door getDoor1() {
        return door1;
    }

    public void setDoor1(Door door1) {
        this.door1 = door1;
    }

    public Door getDoor2() {
        return door2;
    }

    public void setDoor2(Door door2) {
        this.door2 = door2;
    }


    public void rewardCheck(){
        try{
            System.out.println("Hero looking for around, too see something...");
            System.out.println("Hero found chest in this room : "+getReward().getName());
            System.out.println();
            System.out.println("If you want to collect that item in chest say: (collect) ");

        }
        catch (NullPointerException e){
            System.out.println("There is nothing in room...");
            System.out.println();
        }
    }




}
