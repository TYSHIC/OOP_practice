// 任務描述：
// 依序給定長方形的長與寬（皆為大於 0 且小於等於 10000 的正整數），試求該長方形的周長與面積？
//
// 請設計一個名為 "Rectangle" 的類別，代表長方形，
// 使其建構子兩參數依序代表長與寬（皆為大於 0 且小於等於 10000 的正整數），
// 且能獲取其周長及面積。
//
// 輸入說明：
// 輸入兩個大於 0 且小於等於 10000 的正整數，依序代表長方形的長與寬。
//
// 輸出說明：
// 輸出該長方形的周長與面積，中間以空白隔開。

import java.util.Scanner;

// 不要更改這裡。 >>>>>
public class JAVA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length  = scanner.nextInt();
        int width  = scanner.nextInt();
        Rectangle rectangle = new Rectangle(length ,width);
        System.out.println(rectangle.getPerimeter() + " " + rectangle.getArea());
    }
}
// <<<<< 不要更改這裡。

// 請在下方撰寫程式碼。
