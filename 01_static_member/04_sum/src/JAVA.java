// 任務描述：
// 給定 n (0 < n <= 5) 個整數，求這些整數的總和？
//
// 撰寫多個函式或靜態方法，使其能接收 n 個整數並求出總和。
//
// 輸入說明：
// 第一行有一個正整數 n (0 < n <= 5) 代表資料個數，
// 第二行會有 n 個以空白隔開的整數（-1000000 <= 整數 <= 1000000）。
//
// 輸出說明：
// 輸出 n 個整數的總和。

import java.util.Scanner;

public class JAVA {
    // 不要更改這裡。 >>>>>
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int ans = 0; // redundant assignment
        if (n == 1) ans = sum(scanner.nextInt());
        else if (n == 2) ans = sum(scanner.nextInt(), scanner.nextInt());
        else if (n == 3) ans = sum(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        else if (n == 4) ans = sum(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        else if (n == 5)
            ans = sum(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        System.out.println(ans);
    }
    // <<<<< 不要更改這裡。

    // 請在下方撰寫程式碼。

}
