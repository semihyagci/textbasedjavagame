import java.util.Random;
import java.util.Scanner;

public class Monster extends Character implements MonsterBattle{
    static Scanner input = new Scanner(System.in);

    private Weapon weapon;
    private Armor armor;

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

    public Monster(String name, int HP, int hitDamage, Weapon weapon, Armor armor) {
        super(name, HP, hitDamage);
        this.weapon = weapon;
        this.armor = armor;
    }

    public Monster(Weapon weapon, Armor armor) {
        super();
        this.weapon = weapon;
        this.armor = armor;
    }

    @Override
    public void attack(Hero hero, Monster monster) {
        Random random = new Random();
        if (monster.getHP() > 0) {

            int randomRange = random.nextInt(4);
            if (randomRange <= monster.getWeapon().getRange()) {
                System.out.println("Monster is attacking back to hero...");
                System.out.println("The Damage Given by Monster to Hero: " + (monster.getHitDamage() + monster.getWeapon().getDamage() * randomRange));
                if ((hero.getArmor().getProtectionValue() - (monster.getHitDamage() + monster.getWeapon().getDamage() * randomRange) > 0)) {
                    System.out.println("Your armor didn't penetrate");
                    hero.setHP(hero.getHP());
                } else {
                    System.out.println("Damage absorved by armor: " + hero.getArmor().getProtectionValue() );
                    hero.setHP((hero.getHP() + hero.getArmor().getProtectionValue()) - (monster.getHitDamage() + monster.getWeapon().getDamage() * randomRange));
                }
                System.out.println();
                System.out.println("The remaining HP of Hero is: " + hero.getHP());
            } else if (randomRange > getWeapon().getRange()) {
                System.out.println("The monster missed the re-attack...");
            }
        }
        else {
            System.out.print(" ");
        }
    }

    @Override
    public void monsterDropItem(Monster monster, Hero hero) {
        int choice=0;
        System.out.println("The monster is dead, these dropped items are its items: ");
        System.out.println("Weapon= "+monster.getWeapon().getName());
        System.out.println("Armor= "+monster.getArmor().getName());
        System.out.println("If you want to pick weapon press 1.");
        System.out.println("If you want to pick armor press 2.");
        System.out.println("If you want to pick both of them press 3");
        System.out.println("If you don't want to pick none of them press 4");
        choice=input.nextInt();

        switch (choice){
            case 1:
                hero.getInventory().add(monster.getWeapon());
                hero.setMovableWeight(hero.getMovableWeight()+monster.getWeapon().getWeight());
                break;

            case 2:
                hero.getInventory().add(monster.getArmor());
                hero.setMovableWeight(hero.getMovableWeight()+monster.getArmor().getWeight());
                break;

            case 3:
                hero.getInventory().add(monster.getWeapon());
                hero.setMovableWeight(hero.getMovableWeight()+monster.getWeapon().getWeight());
                hero.getInventory().add(monster.getArmor());
                hero.setMovableWeight(hero.getMovableWeight()+monster.getArmor().getWeight());
                break;

            case 4:
                break;

            default:
                System.out.println("Invalid value.");
        }
    }
}
