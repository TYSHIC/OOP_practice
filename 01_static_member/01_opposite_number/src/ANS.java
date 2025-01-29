// 任務描述：
// 輸入一個整數 x (-10^9 < x < 10^9) 並輸出其相反數。
//
// 撰寫一個函式或靜態方法，使其能接收一個整數 x (-10^9 < x < 10^9) 並回傳其相反數。
//
// 輸入說明：
// 輸入一個整數 x (-10^9 < x < 10^9)。
//
// 輸出說明：
// 輸出整數 x 的相反數。

import java.util.Scanner;

public class ANS {
    // 不要更改這裡。 >>>>>
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getOppositeNumber(n));
    }
    // <<<<< 不要更改這裡。

    // 請在下方撰寫程式碼。
    static int getOppositeNumber(int n) {
        return -n;
    }
}