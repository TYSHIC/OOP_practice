import java.util.Scanner;

public class ANS {
    public static void main(String[] args) {
        Anduin anduin = new Anduin();
        Uther uther = new Uther();
        int turn = 1;

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            for (int j = 0; j < m; j++) {
                int s = scanner.nextInt();
                (turn % 2 == 0 ? anduin : uther).castSpell(s, turn % 2 == 0 ? uther : anduin);
            }
            turn++;
        }
    }
}

abstract class Character {
    int hp = 30;

    abstract String getName();

    abstract void castFirstSpell(Character opponent);

    abstract void castSecondSpell(Character opponent);

    void castSpell(int n, Character opponent) {
        if (n == 1) this.castFirstSpell(opponent);
        else if (n == 2) this.castSecondSpell(opponent);
    }

    void heal(int value) {
        hp += value;
        if (hp > 30) hp = 30;
        System.out.println(getName() + " hp +" + value + " -> " + hp);
    }

    void hurt(int value) {
        hp -= value;
        System.out.println(getName() + " hp -" + value + " -> " + hp);
    }
}

class Anduin extends Character {
    @Override
    String getName() {
        return "Anduin";
    }

    @Override
    void castFirstSpell(Character opponent) {
        System.out.println("HolyLight");
        this.heal(4);
    }

    @Override
    void castSecondSpell(Character opponent) {
        System.out.println("MindBlast");
        opponent.hurt(4);
    }
}

class Uther extends Character {
    @Override
    String getName() {
        return "Uther";
    }

    @Override
    void castFirstSpell(Character opponent) {
        System.out.println("HolyFire");
        opponent.hurt(2);
        this.heal(2);
    }

    @Override
    void castSecondSpell(Character opponent) {
        System.out.println("SealOfLight");
        opponent.hurt(1);
        this.heal(3);
    }
}

class Garrosh extends Character {
    boolean commandShout = false;

    @Override
    String getName() {
        return "Garrosh";
    }

    @Override
    void castFirstSpell(Character opponent) {
        System.out.println("MortalStrike");
        opponent.hurt(hp <= 12 ? 6 : 3);
    }

    @Override
    void castSecondSpell(Character opponent) {
        System.out.println("CommandShout");
        commandShout = true;
    }

    @Override
    void hurt(int value) {
        if (commandShout) {
            value /= 2;
            commandShout = false;
        }
        super.hurt(value);
    }
}
