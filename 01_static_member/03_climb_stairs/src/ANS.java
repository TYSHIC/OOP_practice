import java.util.Scanner;

public class ANS {
    // 任務描述：
    // 棕聖喜歡爬樓梯，一次可以爬一階或爬兩階，試問棕聖從樓梯下爬到第 n 階 (1 <= n <= 30) 有幾種爬法？

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