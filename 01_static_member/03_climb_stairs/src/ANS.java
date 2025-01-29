// 任務描述：
// 試問棕聖從樓梯下爬到第 n 階 (1 <= n <= 30) 有幾種爬法？
// 舉例來說，若樓梯有 3 階 (n = 3)，則棕聖有三種爬法：
//   1. 先爬一階，再爬兩階
//   2. 先爬兩階，再爬一階
//   3. 先爬一階，再爬一階，再爬一階
//
// 撰寫一個函式或靜態方法，使其能接收一個整數 n 並求出爬法數。
//
// 輸入說明：
// 輸入一個正整數 n (1 <= n <= 30)，代表棕聖要從樓梯下爬到第 n 階。
//
// 輸出說明：
// 輸出棕聖有幾種爬法可以從階梯下爬到第 n 階。

import java.util.Scanner;

public class ANS {
    // 不要更改這裡。 >>>>>
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getMethodCounts(n));
    }
    // <<<<< 不要更改這裡。

    // 請在下方撰寫程式碼。
    static int getMethodCounts(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return getMethodCounts(n - 1) + getMethodCounts(n - 2);
    }
}