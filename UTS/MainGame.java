import java.util.Scanner;

interface Destroyable {
    void destroyed();
}

abstract class Zombie implements Destroyable {
    protected int health;
    protected int level;

    public Zombie(int health, int level) {
        this.health = health;
        this.level = level;
    }

    abstract void heal();


    public int getHealth() {
        return health;
    }

    public String getZombieInfo() {
        return "Health = " + health + "\nLevel = " + level + "\n";
    }
}

class WalkingZombie extends Zombie {
    public WalkingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        switch (level) {
            case 1 -> health += health * 0.2;
            case 2 -> health += health * 0.3;
            case 3 -> health += health * 0.4;
        }
    }

    @Override
    public void destroyed() {
        health -= health * 0.2;
        if (health < 0) health = 0;
    }

    @Override
    public String getZombieInfo() {
        return "Walking Zombie Info:\n" + super.getZombieInfo();
    }
}

class JumpingZombie extends Zombie {
    public JumpingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        switch (level) {
            case 1 -> health += health * 0.3;
            case 2 -> health += health * 0.4;
            case 3 -> health += health * 0.5;
        }
    }

    @Override
    public void destroyed() {
        health -= health * 0.1;
        if (health < 0) health = 0;
    }

    @Override
    public String getZombieInfo() {
        return "Jumping Zombie Info:\n" + super.getZombieInfo();
    }
}

class Barrier implements Destroyable {
    private int strength;

    public Barrier(int strength) {
        this.strength = strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public void destroyed() {
        strength -= 9;
        if (strength < 0) strength = 0;
    }

    public String getBarrierInfo() {
        return "Barrier Strength = " + strength + "\n";
    }
}

class Plant {
    public void doDestroy(Destroyable d) {
        d.destroyed();
        if (d instanceof Zombie z) {
            if (z.getHealth() == 0)
                System.out.println("⚠️ Zombie kalah!");
        } else if (d instanceof Barrier b) {
            if (b.getStrength() == 0)
                System.out.println("⚠️ Barrier kalah!");
        }
    }
}

public class MainGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Plant p = new Plant();

        System.out.println("=== GAME ZOMBIE vs PLANT ===");

        // Input data WalkingZombie
        System.out.print("Masukkan health WalkingZombie: ");
        int wzHealth = sc.nextInt();
        System.out.print("Masukkan level WalkingZombie (1-3): ");
        int wzLevel = sc.nextInt();
        WalkingZombie wz = new WalkingZombie(wzHealth, wzLevel);

        // Input data JumpingZombie
        System.out.print("Masukkan health JumpingZombie: ");
        int jzHealth = sc.nextInt();
        System.out.print("Masukkan level JumpingZombie (1-3): ");
        int jzLevel = sc.nextInt();
        JumpingZombie jz = new JumpingZombie(jzHealth, jzLevel);

        // Input data Barrier
        System.out.print("Masukkan kekuatan Barrier: ");
        int bStrength = sc.nextInt();
        Barrier b = new Barrier(bStrength);

        System.out.println("\n=== STATUS AWAL ===");
        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());

        System.out.print("\nMasukkan berapa kali plant menyerang: ");
        int serangan = sc.nextInt();

        System.out.println("\n=== Plant Menyerang! ===");
        for (int i = 0; i < serangan; i++) {
            p.doDestroy(wz);
            p.doDestroy(jz);
            p.doDestroy(b);
        }

        System.out.println("\n=== STATUS SETELAH DISERANG ===");
        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());

        sc.close();
    }
}
