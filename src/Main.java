import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class Main implements ItemStats {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[]args) throws InterruptedException, FileNotFoundException {

        // Outputes the highest 5 score when game start.
        try {
            File myObj = new File("scores.txt");
            if (myObj.createNewFile()) {
                System.out.println("Highscores File created: " + myObj.getName());
                System.out.println("Game loading...");
                System.out.println(" ");
            } else {
                System.out.println("Highscores File already exists.");

            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Top 5 HighScores: ");
        Scanner scanner = new Scanner(new File("scores.txt"));
        ArrayList<Integer> scores = new ArrayList<>();
        while (scanner.hasNextInt()) {
            scores.add(scanner.nextInt());
        }
        Collections.sort(scores, Collections.reverseOrder());
        if (scores.size()>5){
            for (int i = 0; i < 5;i++)
            {
                System.out.println((i+1)+": "+scores.get(i));
            }
        }
        else {
            for (int i = 0; i < scores.size();i++)
            {
                System.out.println((i+1)+": "+scores.get(i));
            }
        }

        int scoreCounter=0;

        // Creating and Initializing the items.
        Sword[] swords = new Sword[3];
        Axe[] axes = new Axe[3];
        Bow[] bows = new Bow[3];
        Armor[] armors = new Armor[3];

        for (int i=0;i<3;i++){
            swords[i]= new Sword(swordsNames[i],swordsWeights[i],swordsWorths[i],swordsDamages[i],swordsRanges[i],swordsBlockPercentages[i]);
            axes[i]= new Axe(axesNames[i],axesWeights[i],axesWorths[i],axesDamages[i],axesRanges[i],axesBlockPercentages[i]);
            bows[i]= new Bow(bowsNames[i],bowsWeights[i],bowsWorths[i],bowsDamages[i],bowsRanges[i],bowsBlockPercentages[i]);
            armors[i]= new Armor(armorsNames[i],armorsWeights[i],armorsWorths[i],armorsProtectionValues[i]);
        }

        ArrayList<Weapon> weapons = new ArrayList<>(9);

        for (int i=0;i<3;i++){
            weapons.add(swords[i]);
            weapons.add(axes[i]);
            weapons.add(bows[i]);
        }

        ArrayList<Item> items = new ArrayList<>(12);

        for (int i=0;i<3;i++){
            items.add(swords[i]);
            items.add(axes[i]);
            items.add(bows[i]);
            items.add(armors[i]);
        }

        // Creating places of the map.
        Random random = new Random();
        Room[][] rooms = new Room[16][4];
        Door[][] doors = new Door[16][3];
        Stair[][] stairs = new Stair[16][1];
        Level[] levels = new Level[16];
        int roomcounter=1;
        int doorcounter=1;



        for (int j=0;j<16;j++){
            levels[j]= new Level(j+1);
        }

        for (int i=0;i<16;i++){
            for (int j=0;j<4;j++){
                rooms[i][j]=new Room(roomcounter,new ArrayList<Townspeople>(),new ArrayList<Monster>(),null,null,null,null);
                roomcounter++;
            }
            for (int j=0;j<3;j++){
                doors[i][j]=new Door(doorcounter,rooms[i][j],rooms[i][j+1]);
                doorcounter++;
            }
        }

        for (int i=0;i<15;i++){
            stairs[i][0]= new Stair(i+1,levels[i],levels[i+1],rooms[i][3],rooms[i+1][0]);
            rooms[i][3].setStair(stairs[i][0]);
        }

        //Creating and Initializing Monsters
        ArrayList<ArrayList<Monster>> monsters= new ArrayList<>();
        int monsterNumber = random.nextInt(4);
        int monsterWeaponPointer=random.nextInt(3);
        int monsterWeaponPointer2=random.nextInt(9);
        int monsterArmorPointer=random.nextInt(3);

        //Creating and Initializing Monsters
        ArrayList<ArrayList<Townspeople>> townspeoples = new ArrayList<>();
        int TownspeopleNumber=0;
        if (monsterNumber%3==0)
            TownspeopleNumber=monsterNumber/3;
        else
            TownspeopleNumber=1;


        for (int i=0;i<64;i++){
            ArrayList<Monster> roomMonsters = new ArrayList<>(monsterNumber);

            if (monsterNumber%3==0)
                TownspeopleNumber=monsterNumber/3;
            else
                TownspeopleNumber=1;
            ArrayList<Townspeople> roomTownspeople = new ArrayList<>(TownspeopleNumber);

            Weapon weapon=null;
            Armor armor=null;

            // Setting Difficulties of High tier and Low Tier Monsters.
            for (int j=0;j<monsterNumber;j++) {
                if (i < 40) {
                    weapon = weapons.get(monsterWeaponPointer);
                    armor = armors[monsterArmorPointer];
                }
                else if(i>40){
                    weapon = weapons.get(monsterWeaponPointer2);
                    armor = armors[monsterArmorPointer];
                }
                Monster monster = new Monster("Monster",20,6,weapon,armor);
                roomMonsters.add(monster);
                monsterWeaponPointer=random.nextInt(3);
                monsterWeaponPointer2=random.nextInt(9);
                monsterArmorPointer=random.nextInt(3);
            }

            for (int z=0; z<TownspeopleNumber;z++){
                Townspeople townspeople = new Townspeople("Townspeople",1,1);
                roomTownspeople.add(townspeople);
            }
            monsters.add(roomMonsters);
            townspeoples.add(roomTownspeople);
            monsterNumber=random.nextInt(4);
            if (monsterNumber%3==0)
                TownspeopleNumber=monsterNumber/3;
            else
                TownspeopleNumber=1;
            }

        int monsterArrayListPointer=0;


        for (int i=0;i<16;i++){
            for (int j=0;j<4;j++){
                rooms[i][j].setMonsters(monsters.get(monsterArrayListPointer));
                rooms[i][j].setTownspeoples(townspeoples.get(monsterArrayListPointer));
                monsterArrayListPointer++;
            }
        }

        // Adding Chest in Random Rooms (%25)
        int rewardPointer= random.nextInt(4);
        int rewardChooser= random.nextInt(12);

        for (int i=0;i<16;i++){
            for (int j=0;j<4;j++){
                Item reward = new Item();
                if (j==rewardPointer) {
                    reward=items.get(rewardChooser);
                    rooms[i][j].setReward(reward);
                }
                else
                    rooms[i][j].setReward(null);

            }
            rewardPointer= random.nextInt(4);
            rewardChooser= random.nextInt(12);
        }

        String tempName="";
        ArrayList<Item> heroInventory = new ArrayList<>();
        displayMenu();
        tempName=input.nextLine();
        Hero hero = new Hero(tempName,100,5,swords[0],armors[0],0,heroInventory,rooms[0][0],levels[0]);

        while (hero.getHP()>0){
            hero.displayInfo();
            displayActions();

            if (hero.getCurrentRoom().isMonsterCheck()==true){
                if (hero.getCurrentRoom().getRoomID()%4==1)
                    hero.setCurrentRoom(rooms[hero.getCurrentLevel().getLevelID()-1][1]);
                else if(hero.getCurrentRoom().getRoomID()%4==2)
                    hero.setCurrentRoom(rooms[hero.getCurrentLevel().getLevelID()-1][2]);
                else if(hero.getCurrentRoom().getRoomID()%4==3)
                    hero.setCurrentRoom(rooms[hero.getCurrentLevel().getLevelID()-1][3]);
                System.out.println("Hero see the door at the corner...");
                System.out.println("Write something in action line to enter the door...");
                System.out.println();
            }

            String choice="";
            System.out.println("What do you want to do?");
            System.out.println("Action: ");
            choice=input.nextLine();

            switch (choice){

                case "attack monster":
                    for (int i=0;i<hero.getCurrentRoom().getMonsters().size();i++){

                            hero.attack(hero.getCurrentRoom().getMonsters().get(i),hero);

                        if (hero.getCurrentRoom().getMonsters().get(i).getHP()<=0) {

                            if (hero.getHP() <= 0){
                                endGame();
                                break;
                            }
                            else {
                                hero.getCurrentRoom().getMonsters().get(i).monsterDropItem(hero.getCurrentRoom().getMonsters().get(i), hero);
                            }
                        }


                    }
                        hero.getCurrentRoom().getMonsters().clear();


                if (hero.getHP() >= 0) {
                    if (hero.getCurrentRoom().getMonsters().size() == 0) {
                        System.out.println("Townspeople is so gratefull for saving them...");
                        System.out.println("They give you a potion as a gift, you drink it and your HP is increased...");
                        try {
                            hero.getCurrentRoom().getTownspeoples().get(0).greetings(hero);
                            hero.getCurrentRoom().getTownspeoples().remove(0);
                        } catch (IndexOutOfBoundsException e) {
                            throw new IndexOutOfBoundsException("There is no townspeople in room...");
                        }
                        System.out.println("New HP is: " + hero.getHP());
                        scoreCounter++;
                        System.out.println("Hero can see the door and moving to it.");
                        if (hero.getCurrentRoom().getRoomID() % 4 == 1)
                            hero.setCurrentRoom(rooms[hero.getCurrentLevel().getLevelID() - 1][1]);
                        else if (hero.getCurrentRoom().getRoomID() % 4 == 2)
                            hero.setCurrentRoom(rooms[hero.getCurrentLevel().getLevelID() - 1][2]);
                        else if (hero.getCurrentRoom().getRoomID() % 4 == 3)
                            hero.setCurrentRoom(rooms[hero.getCurrentLevel().getLevelID() - 1][3]);

                    }
                }

                    break;

                case "move stair":
                    if (hero.getCurrentLevel().getLevelID()==1) {
                        hero.setCurrentLevel(levels[hero.getCurrentLevel().getLevelID()]);
                        hero.setCurrentRoom(stairs[hero.getCurrentLevel().getLevelID() - 1][0].getExteriorRoom());
                    }
                    else {
                        hero.setCurrentLevel(levels[hero.getCurrentLevel().getLevelID() + 1]);
                        hero.setCurrentRoom(stairs[hero.getCurrentLevel().getLevelID()][0].getExteriorRoom());
                    }
                    break;

                case "weapon stats":
                    hero.getWeapon().displayWeapon(hero.getWeapon());
                        break;

                case "armor stats":
                    hero.getArmor().displayArmor();
                    break;

                case "inventory":
                        for (int i=0;i<hero.getInventory().size();i++){
                            try{
                                System.out.println(hero.getInventory().get(i).getName());
                            }
                          catch (NullPointerException e){
                                throw new NullPointerException("Your inventory is empty.");

                          }
                            System.out.println();
                        }
                        break;


                case "collect":
                    try {
                        hero.getInventory().add(hero.getCurrentRoom().getReward());
                        hero.setMovableWeight(hero.getMovableWeight()+hero.getCurrentRoom().getReward().getWeight());
                    }catch (NullPointerException e) {
                        System.out.println("The room is empty, you can't collect anything.");
                    }
                    break;


                case "change weapon":
                    ArrayList<Weapon> weapons2 = new ArrayList<>();
                    for (int i=0;i<hero.getInventory().size();i++){
                        if (hero.getInventory().get(i) instanceof Weapon){
                            weapons2.add((Weapon)hero.getInventory().get(i));
                        }
                    }

                    for (int i=0;i<weapons2.size();i++){
                        System.out.println((i+1)+". weapon is "+weapons2.get(i).getName()+" with the damage value: "+weapons2.get(i).getDamage());
                    }
                    int weaponChoice=0;
                    System.out.println("Which one do you want to wear?");
                    weaponChoice=input.nextInt();
                    switch (weaponChoice){
                        case 1:
                            hero.setWeapon(weapons2.get(0));
                            break;

                        case 2:
                            hero.setWeapon(weapons2.get(1));
                            break;

                        case 3:
                            hero.setWeapon(weapons2.get(2));
                            break;

                        case 4:
                            hero.setWeapon(weapons2.get(3));
                            break;

                        case 5:
                            hero.setWeapon(weapons2.get(4));
                            break;

                        default:
                            System.out.println("Invalid value.");
                    }
                    break;

                case "change armor":
                    ArrayList<Armor> armors2 = new ArrayList<>();
                    for (int i=0;i<hero.getInventory().size();i++){
                        if (hero.getInventory().get(i) instanceof Armor){
                            armors2.add((Armor)hero.getInventory().get(i));
                        }
                    }

                    for (int i=0;i<armors2.size();i++){
                        System.out.println((i+1)+". armor is "+armors2.get(i).getName()+" with the protection value: "+armors2.get(i).getProtectionValue());
                    }
                    int armorChoice=0;
                    System.out.println("Which one do you want to wear?");
                    armorChoice=input.nextInt();
                    switch (armorChoice){
                        case 1:
                            hero.setArmor(armors2.get(0));
                            break;

                        case 2:
                            hero.setArmor(armors2.get(1));
                            break;

                        case 3:
                            hero.setArmor(armors2.get(2));
                            break;

                        case 4:
                            hero.setArmor(armors2.get(3));
                            break;

                        case 5:
                            hero.setArmor(armors2.get(4));
                            break;

                        default:
                            System.out.println("Invalid value.");
                    }
                    break;
            }



            // Limitting Hero to Carrying Less Items
            while (hero.getMovableWeight()>20){
                String choice2="";
                int dropChoice=0;
                System.out.println("Your movable weight is so high, drop some item...");
                System.out.println("Your total weight is "+hero.getMovableWeight());
                System.out.println("Drop most worthless item? Y/N");
                choice2=input.next();
                if (choice2.equals("Y")){
                    for (int i=0;i<hero.getInventory().size();i++){
                        System.out.println((i+1)+". item is "+hero.getInventory().get(i).getName()+" with weight of "+hero.getInventory().get(i).getWeight()+" with worth of "+hero.getInventory().get(i).getWorth());
                    }
                    System.out.println("Which one do you want to drop?");
                    dropChoice=input.nextInt();
                    switch (dropChoice){
                        case 1:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(0).getWeight());
                            hero.getInventory().remove(0);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;

                        case 2:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(1).getWeight());
                            hero.getInventory().remove(1);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;

                        case 3:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(2).getWeight());
                            hero.getInventory().remove(2);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;

                        case 4:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(3).getWeight());
                            hero.getInventory().remove(3);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;

                        case 5:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(4).getWeight());
                            hero.getInventory().remove(4);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;

                        case 6:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(5).getWeight());
                            hero.getInventory().remove(5);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;

                        case 7:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(6).getWeight());
                            hero.getInventory().remove(6);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;

                        case 8:
                            hero.setMovableWeight(hero.getMovableWeight()-hero.getInventory().get(7).getWeight());
                            hero.getInventory().remove(7);
                            System.out.println("After dropping the item your new weight is: "+hero.getMovableWeight());
                            break;
                    }
                }
                else if(choice2.equals("N"))
                    System.out.println("You can't play the game now.");
            }
        }


        for (int i=0;i<hero.getInventory().size();i++){
            scoreCounter+=hero.getInventory().get(i).getWorth();
        }
        scoreCounter+=hero.getWeapon().getWorth();
        scoreCounter+=hero.getArmor().getWorth();


        System.err.println("Your Score: " + scoreCounter);

        // Saves The Score to The "scores.txt"
        try {
            FileWriter myWriter = new FileWriter("scores.txt",true);
            myWriter.write(Integer.toString(scoreCounter)+"\n");
            myWriter.close();
            System.out.println(hero.getName()+" finished the game. Saved Townspeople score is saving... ");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        }



    public static void displayMenu() throws InterruptedException{
        System.err.println("Welcome to the Hero of the Dungeon");
        Thread.sleep(2000);
        System.out.println("");

        System.out.println("You are at first room in the dungeon...");
        System.out.println("In this dungeon, there are monsters and townspeople in it.");
        System.out.println("Your aim is save townspeople as much as you can and collect worthly items.");
        System.out.println("In the beginning of the game, you are going to play with basic items.");
        System.out.println("What is gonna be your hero's name?");
        System.out.println();
    }

    public static void endGame(){
        System.out.println("You are DEAD...");
        System.out.println();
        System.out.println("---------------------------");
        System.out.println("*******GAME OVER************");
        System.out.println("---------------------------");
        System.out.println();
    }

    public static void displayActions(){
        System.out.println("___________________Action Menu_____________________");
        System.out.println();
        System.out.println("You can attack to monsters with (attack monster) ");
        System.out.println("You can collect the items on the ground with (collect) ");
        System.out.println("If door has stair you can level up with (move stair) ");
        System.out.println("You can view your weapon stats with (weapon stats) ");
        System.out.println("You can view your armor stats with (armor stats) ");
        System.out.println("You can see your items in your inventory with (inventory) ");
        System.out.println("You can change your weapon with (change weapon) ");
        System.out.println("You can change your armor with (change armor) ");
        System.out.println("____________________________________________________");
        System.out.println();
    }


}

