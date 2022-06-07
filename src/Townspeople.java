public class Townspeople extends Character{
    public Townspeople(String name, int HP, int hitDamage) {
        super(name, HP, hitDamage);
    }

    public Townspeople() {
        super();
    }

    public void greetings(Hero hero){
        System.out.println("Townspeople show their greetings to hero for saving...");
        System.out.println("Hero's HP increased with 5.");
        hero.setHP(hero.getHP()+5);
        System.out.println();
    }
}
