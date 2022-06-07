
public class Armor extends Item{
    private int protectionValue;


    public int getProtectionValue() {
        return protectionValue;
    }

    public void setProtectionValue(int protectionValue) {
        this.protectionValue = protectionValue;
    }

    public Armor(){
        super();
        this.protectionValue=0;
    }

    public Armor(String name, int weight, int value, int protectionValue) {
        super(name, weight, value);
        this.protectionValue = protectionValue;
    }

    public void displayArmor(){
        System.out.println("The Name of the Armor: " + super.getName());
        System.out.println("The Weight of the Armor: " + super.getWeight());
        System.out.println("The Worth of the Armor: " + super.getWorth());
        System.out.println("The Protection Value of the Armor: " + getProtectionValue());
        System.out.println();
    }
}
