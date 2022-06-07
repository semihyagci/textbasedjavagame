public class Item {
    private String name;
    private int weight;
    private int worth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWorth() {
        return worth;
    }

    public void setWorth(int worth) {
        this.worth = worth;
    }

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.worth =value;
    }

    public Item(){
        this.name="null";
        this.weight=0;
        this.worth =0;
    }

}
