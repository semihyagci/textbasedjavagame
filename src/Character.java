public class Character {
    private String name;
    private int HP;
    private int hitDamage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(int hitDamage) {
        this.hitDamage = hitDamage;
    }

    public Character(String name, int HP, int hitDamage) {
        this.name = name;
        this.HP = HP;
        this.hitDamage = hitDamage;
    }

    public Character(){
        this.name="null";
        this.HP=0;
        this.hitDamage=0;
    }


}
