// 任務描述：
// 請模擬一個兩個角色的決鬥遊戲。
// 兩個角色會輪流共 n (0 < n <= 5) 個回合，每回合該角色會施放 m (0 < m <= 5) 個法術。
//
// 兩個角色的最大生命值及初始生命值為 30。
// 受傷後生命值可以為負數，但恢復後生命值不可超過最大生命值。
// 兩個角色的法術介紹：
//
// 安度因(Anduin)
// 編號 1 法術：聖光術(HolyLight)
// 效果：為自己恢復 4 點生命值。
//
// 編號 2 法術：心靈震爆(MindBlast)
// 效果：對對手造成 4 點傷害。
//
// 烏瑟(Uther)
// 編號 1 法術：神聖之火(HolyFire)
// 效果：對對手造成 2 點傷害，為自己恢復 2 點生命值。
//
// 編號 2 法術：光明聖印(SealOfLight)
// 效果：對對手造成 1 四點傷害，為自己恢復 3 點生命值。
//
// 第 1 回合為安度因施放法術。
// 每回合開始時需輸出此為第幾回合，
// 且每次施放法術時須輸出哪個角色受到多少點傷害或恢復多少點生命值，
// 以及更動後的生命值。
//
// 當 n 個回合進行完成或其中一個角色的生命值小於等於 0，即結束遊戲。
// 結束遊戲後需輸出兩個角色的生命值。
//
// 詳細輸出格式請見輸出說明和範例測資（測資 1、2）。
//
// 輸入說明：
// 第一行有一個正整數 n (1 <= n <= 5) 代表總回合術，接下來會有 n 行，
// 每行第一個整數 m (0 <= m <= 5) 代表該回合該角色施放的法術數，後方有 m 個整數代表依序施放的法術編號。
//
// 輸出說明：
// 每回合需輸出 "Turn {回合}:"。
// 每次施放法術需輸出 "{角色英文名稱} cast {法術英文名稱}"。
// 每次有角色受到治療，需輸出 "{角色英文名稱} hp +{實際恢復值} -> {恢復後生命值}"。
// 每次有角色受到傷害，需輸出 "{角色英文名稱} hp -{實際傷害值} -> {受傷後生命值}"。
// 遊戲結束後，需輸出 "GAME ENDED"、"Anduin: {安度因生命值} hp"、"Uther: {烏瑟生命值} hp"。

import java.util.Scanner;

public class ANS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Anduin anduin = new Anduin();
        Uther uther = new Uther();
        int turn = 1;

        for (int i = 0; i < n; i++) {
            System.out.println("Turn " + turn + ":");
            int m = scanner.nextInt();
            for (int j = 0; j < m; j++) {
                int s = scanner.nextInt();
                if (turn % 2 == 1) anduin.castSpell(s, uther);
                else uther.castSpell(s, anduin);
                if (anduin.hp <= 0 || uther.hp <= 0) {
                    endGame(anduin, uther);
                    return;
                }
            }
            turn++;
        }
        endGame(anduin, uther);
    }

    static void endGame(Anduin anduin, Uther uther) {
        System.out.println("GAME ENDED");
        System.out.println("Anduin: " + anduin.hp + " hp");
        System.out.println("Uther: " + uther.hp + " hp");
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
        if (hp >= 30) return;
        hp += value;
        if (hp > 30) {
            value -= hp - 30;
            hp = 30;
        }
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
        System.out.println("Anduin cast HolyLight");
        this.heal(4);
    }

    @Override
    void castSecondSpell(Character opponent) {
        System.out.println("Anduin cast MindBlast");
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
        System.out.println("Uther cast HolyFire");
        opponent.hurt(2);
        this.heal(2);
    }

    @Override
    void castSecondSpell(Character opponent) {
        System.out.println("Uther cast SealOfLight");
        opponent.hurt(1);
        this.heal(3);
    }
}
