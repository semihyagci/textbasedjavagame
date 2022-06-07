public class Weapon extends Item {
    private int damage;
    private int range;
    private int blockPercentage;

    public int getBlockPercentage() {
        return blockPercentage;
    }

    public void setBlockPercentage(int blockPercentage) {
        this.blockPercentage = blockPercentage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Weapon(String name, int weight, int value, int damage, int range, int blockpercentage) {
        super(name, weight, value);
        this.damage = damage;
        this.range = range;
        this.blockPercentage=blockpercentage;
    }

    public Weapon() {
        super();
        this.damage = 0;
        this.range = 0;
    }

  public void displayWeapon(Weapon weapon){
        if (weapon instanceof Sword){
            System.out.println("The Name of the Sword: " + super.getName());
            System.out.println("The Weight of the Sword: " + super.getWeight());
            System.out.println("The Worth of the Sword: " + super.getWorth());
            System.out.println("The Damage Value of the Sword: " + getDamage());
            System.out.println("The Range of the Sword: " + getRange());
            System.out.println();
        }
        else if (weapon instanceof Axe){
            System.out.println("The Name of the Axe: " + super.getName());
            System.out.println("The Weight of the Axe: " + super.getWeight());
            System.out.println("The Worth of the Axe: " + super.getWorth());
            System.out.println("The Damage Value of the Axe: " + getDamage());
            System.out.println("The Range of the Axe: " + getRange());
            System.out.println();
        }
        else if (weapon instanceof Bow){
            System.out.println("The Name of the Bow: " + super.getName());
            System.out.println("The Weight of the Bow: " + super.getWeight());
            System.out.println("The Worth of the Bow: " + super.getWorth());
            System.out.println("The Damage Value of the Bow: " + getDamage());
            System.out.println("The Range of the Bow: " + getRange());
            System.out.println();
        }
  }
}
