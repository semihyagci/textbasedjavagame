import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hero extends Character implements HeroBattle {
     static Scanner input = new Scanner(System.in);

    private Weapon weapon;
    private Armor armor;
    private int movableWeight;
    private ArrayList<Item> Inventory;
    private Room currentRoom;
    private Level currentLevel;


    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getMovableWeight() {
        return movableWeight;
    }

    public void setMovableWeight(int movableWeight) {
        this.movableWeight = movableWeight;
    }

    public ArrayList<Item> getInventory() {
        return Inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        Inventory = inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }


    public Hero(String name, int HP, int hitDamage, Weapon weapon, Armor armor, int movableWeight, ArrayList<Item> inventory, Room currentRoom, Level currentLevel) {
        super(name, HP, hitDamage);
        this.weapon = weapon;
        this.armor = armor;
        this.movableWeight = movableWeight;
        Inventory = inventory;
        this.currentRoom = currentRoom;
        this.currentLevel = currentLevel;
    }

    public Hero(Weapon weapon, Armor armor, int movableWeight, ArrayList<Item> inventory, Room currentRoom, Level currentLevel) {
        super();
        this.weapon = weapon;
        this.armor = armor;
        this.movableWeight = movableWeight;
        Inventory = inventory;
       this.currentRoom = currentRoom;
        this.currentLevel = currentLevel;
    }

    @Override
    public void attack(Monster monster, Hero hero) {
        Random random = new Random();
        while (monster.getHP() > 0) {
            int randomRange = random.nextInt(4);
            if (randomRange <= hero.getWeapon().getRange()) {
                    System.out.println("Hero is attacking to Monster...");
                    System.out.println("The Damage Given by Hero to Monster: " + hero.getHitDamage() + hero.getWeapon().getDamage() * randomRange);
                    monster.setHP((monster.getHP()) - (hero.getHitDamage() + hero.getWeapon().getDamage() * randomRange));
                    System.out.println();
                    System.out.println("The remaining HP of monster is " + monster.getHP());
                    System.out.println();
            } else if (randomRange > getWeapon().getRange()) {
                System.out.println("The hero missed the attack...");
            }
            monster.attack(hero,monster);
        }
    }

    public void displayInfo() {
        System.out.println("Hero " + super.getName());
        System.out.println("Hero is in room: " + getCurrentRoom().getRoomID() + " with level of: " + getCurrentLevel().getLevelID());
        System.out.println("Hero's HP :" + super.getHP());
        System.out.println("Hero's weapon is :" + getWeapon().getName());
        System.out.println("Hero's armor is :" + getArmor().getName());
        System.out.println("Hero carry : " + getMovableWeight()+ " kg weight now.");
        System.out.println("The hero sees the following.");
        System.out.println();
        try {
            for (int i=0;i<currentRoom.getMonsters().size();i++) {
                System.out.println(currentRoom.getMonsters().get(i).getName()+""+"(m"+(i+1)+")");
                System.out.println();
            }
            for (int i=0;i<currentRoom.getTownspeoples().size();i++) {
                System.out.println(currentRoom.getTownspeoples().get(i).getName()+""+"(t"+(i+1)+")");
                System.out.println();
            }
            if (currentRoom.getStair()!=null){
                System.out.println("Stair");
                System.out.println();
            }

            if (currentRoom.getTownspeoples().size()==0)
                System.out.println("There is no townspeople in this room...");
            System.out.println();
            if (currentRoom.getMonsters().size()==0) {
                System.out.println("There is no monster in this room...");
                System.out.println();
                currentRoom.setMonsterCheck(true);
            }

            currentRoom.rewardCheck();
        } catch (NullPointerException e) {
            throw new NullPointerException("There is nothing in this room.");
        }
    }

}
