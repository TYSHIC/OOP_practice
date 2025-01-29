// 任務描述：
// 給定 n (0 < n <= 10) 個人的姓名（純英文組成）及分數（整數，0 <= 分數 <= 100），
// 請依照分數由小到大輸出每個人的姓名及分數。
// 保證不會有同名或同分的情形。
//
// 輸入說明：
// 第一行有一個正整數 n (0 < n <= 10) 代表學生人數，接下來會有 n 行，
// 每行依序有一個字串 （純英文組成）及整數（整數，0 <= 分數 <= 100），分別代表姓名及分數。
// 保證不會有同名或同分的情形。
//
// 輸出說明：
// 依照分數由小到大輸出每個人的姓名及分數，姓名及分數間以空白隔開，每人以換行隔開。

import java.util.Scanner;

public class Ans {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++)
            students[i] = new Student(scanner.next(), scanner.nextInt());

        // 氣泡排序
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (students[j].score > students[j + 1].score) {
                    Student tmp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < n; i++)
            System.out.println(students[i].name + " " + students[i].score);
    }
}

class Student {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
