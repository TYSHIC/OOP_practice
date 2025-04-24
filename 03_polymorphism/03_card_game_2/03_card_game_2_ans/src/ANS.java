// 任務描述：
// 請製作一個回合制的卡牌遊戲。
// 規則與卡牌遊戲 1 大致相同。
//
// 此遊戲有兩個玩家，兩個玩家會輪流進行回合。
// 每個玩家有自己的英雄(hero)。
//
// 每個英雄的最大生命值及初始生命值為 30。
// 受傷後生命值可以為負數，但恢復後生命值不可超過最大生命值。
// 當有玩家的英雄生命值小於等於 0 或被摧毀(destroy)時，該玩家便輸掉遊戲並結束遊戲。
//
// 每個玩家一開始各有有 0 顆空的法力水晶，每當玩家的回合開始時會增加 1 顆，
// 並將所有空的法力水晶填滿，空的法力水晶最多只能有 10 顆，但法力水晶可以超過 10 顆。
//
// 兩個玩家各自擁有一套卡牌，所有卡牌一開始會在玩家的牌庫中。
// 每當玩家的回合開始時會從牌堆最上方抽一張牌並放到手牌的最右方。
// 當牌堆沒有卡牌時，會從牌堆中抽出疲勞(fatigue)，其會對你的英雄造成傷害。
// 第一次疲勞為 1 點，之後每次抽出疲勞所造成的傷害皆比上次多 1 點。
//
// 卡牌分為手下(minion)和法術(spell)兩種。
// 每張卡牌擁有法力值，當打出卡牌時便會消耗等同法力值的法力水晶。
// 打出法術會執行卡牌的效果，打出手下會將手下放置到戰場(battlefield)的最右邊。
// 戰場上最多只能有 7 個手下，若戰場上已有 7 個手下則無法再打出手下。
//
// 每回合玩家會打出手牌中可打出的法力值最高的可打出卡牌，若法力值相同時則打出靠左方的卡牌，
// 重複此動作直到玩家手中沒有可以打出的卡牌。
// 接著會由左到右依序使玩家戰場上的可攻擊的手下攻擊一個可被攻擊的敵人(enemy)。
// 每個手下會攻擊最左邊的敵方手下，若沒有敵方手下，則會攻擊敵方英雄。
// 每個手下每回合只能攻擊一次。
//
// 輸入說明：
// 輸入兩行 15 個以空白隔開的卡牌編號，
// 兩行依序代表玩家 1 與玩家 2 牌堆由上到下的卡牌。
//
// 輸出說明：
// 每回合開始時輸出 "Turn {回合}:"。
// 每當打出卡牌，輸出該卡牌的編號。
// 遊戲結束時，輸出遊戲結果：
// 若玩家 1 勝利，輸出 "Player1 Win."。
// 若玩家 2 勝利，輸出 "Player2 Win."。
// 若雙方平手，輸出 "Tie."。
//
// 每個輸出皆自成一行。
// 詳細輸出格式請見輸出說明和範例測資（測資 1、2）。

import java.util.Scanner;

public class ANS {
    public static void main(String[] args) {
        Game.initCards(); // 初始化所有卡牌

        Scanner scanner = new Scanner(System.in);

        // 讀入兩個玩家的牌堆
        for (int i = 0; i < 15; i++)
            Game.player1.deck.addCard(Game.getCard(scanner.nextInt()));
        for (int i = 0; i < 15; i++)
            Game.player2.deck.addCard(Game.getCard(scanner.nextInt()));

        Game.start();
    }
}

abstract class Game {
    private static final Card[] cards = new Card[10];

    static Player player1 = new Player();
    static Player player2 = new Player();

    static void initCards() {
        // TODO: 完成函式
        cards[0] = new Card(0, 1) {
            @Override
            void function(Player player) {
                player.restore(1);
                player.drawCards(2);
            }
        };
        cards[1] = new Card(1, 2) {
            @Override
            void function(Player player) {
                getOpponent(player).hurt(5);
            }
        };
        cards[2] = new Card(2, 3) {
            @Override
            void function(Player player) {
                getOpponent(player).hurt(2);
                player.restore(6);
            }
        };
    }

    static void start() {
        int turn = 0;

        while (true) {
            // 奇數回合為玩家 1，偶數回合為玩家 2
            turn++;
            System.out.println("Turn " + turn + ":");
            Player player = turn % 2 == 1 ? player1 : player2;

            // 使玩家增加 1 顆空的法力水晶，並回復所有空的法力水晶，然後從牌堆最上方抽 1 張牌
            player.addEmptyMana();
            player.restoreEmptyMana();
            player.drawCards(1);

            while (true) {
                // 尋找手牌中可打出的法力值最高的卡牌，若法力值相同時打出靠左方的卡牌
                Card nextCard = null;
                int nextCardIndex = -1;
                for (int i = 0; i < player.hand.getCount(); i++) {
                    Card card = player.hand.getCard(i);
                    if (card.cost <= player.getMana() && (nextCard == null || card.cost >= nextCard.cost)) {
                        nextCard = card;
                        nextCardIndex = i;
                    }
                }
                // 若無符合的卡牌，則結束回合
                if (nextCard == null)
                    break;
                // 否則打出卡牌
                System.out.println(nextCard.id);
                player.hand.removeCard(nextCardIndex);
                player.playCard(nextCard);

                // 在打出卡牌後，檢查遊戲是否結束：一方玩家生命值小於等於 0 或雙方手牌、牌堆皆無卡牌
                if (Game.player2.getHealth() <= 0) {
                    System.out.println("Player1 Win.");
                    return;
                } else if (Game.player1.getHealth() <= 0) {
                    System.out.println("Player2 Win.");
                    return;
                } else if (Game.player1.hand.getCount() == 0 && Game.player1.deck.getCount() == 0
                        && Game.player2.hand.getCount() == 0 && Game.player2.deck.getCount() == 0) {
                    System.out.println("Tie.");
                    return;
                }
            }
        }
    }

    static Card getCard(int id) {
        return cards[id];
    }

    // 獲取指定玩家的對手
    static Player getOpponent(Player player) {
        return player == player1 ? player2 : player1;
    }
}

class Player {
    // 一個卡牌列表的類別，用於處理手牌及牌堆
    static class CardList {
        private final Card[] cards = new Card[20];
        private int count = 0;

        void addCard(Card card) {
            for (int i = count - 1; i >= 0; i--)
                cards[i + 1] = cards[i];
            cards[0] = card;
            count++;
        }

        Card removeCard() {
            return cards[--count];
        }

        void removeCard(int index) {
            count--;
            for (int i = index; i < count; i++) {
                cards[i] = cards[i + 1];
            }
        }

        Card getCard(int index) {
            return cards[index];
        }

        int getCount() {
            return count;
        }
    }

    private int mana = 0; // 當前的法力水晶
    private int emptyMana = 0; // 當前的空的法力水晶
    private int health = 30; // 當前的生命值
    CardList deck = new CardList(); // 牌堆的牌
    CardList hand = new CardList(); // 手中的牌

    // 此玩家從牌堆最上方抽取指定數量的卡牌放入手牌的最右方，若牌堆無卡牌則不抽牌
    void drawCards(int count) {
        // TODO: 實現功能
        for (int i = 0; i < Math.min(count, deck.getCount()); i++)
            hand.addCard(deck.removeCard());
    }

    // 對此玩家造成指定傷害
    void hurt(int value) {
        // TODO: 實現功能
        health -= value;
    }

    // 為此玩家回復指定生命值
    void restore(int value) {
        // TODO: 實現功能
        health = Math.min(health + value, 30);
    }

    // 為此玩家回復所有空的法力水晶
    void restoreEmptyMana() {
        // TODO: 實現功能
        mana = emptyMana;
    }

    // 扣除此玩家指定的法力水晶
    void costMana(int value) {
        // TODO: 實現功能
        mana -= value;
    }

    // 為此玩家增加一顆空的法力水晶
    void addEmptyMana() {
        // TODO: 實現功能
        emptyMana = Math.min(emptyMana + 1, 10);
    }

    // 為此玩家增加一顆空的法力水晶
    int getMana() {
        // TODO: 實現功能
        return mana;
    }

    // 為此玩家增加一顆空的法力水晶
    int getHealth() {
        // TODO: 實現功能
        return health;
    }

    // 使此玩家打出一張卡牌
    void playCard(Card card) {
        // TODO: 實現功能
        costMana(card.cost);
        card.function(this);
    }
}

// 卡牌抽象類別
abstract class Card {
    final int cost;
    final int id;

    public Card(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    // 打出卡牌要執行的程式碼
    abstract void function(Player player);
}

abstract class Spell