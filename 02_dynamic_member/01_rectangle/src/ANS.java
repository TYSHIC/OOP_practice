// 任務描述：
// 請設計一個名為 "Rectangle" 的類別，代表長方形，
// 使其建構子兩參數依序代表長與寬（皆為大於 0 且小於等於 10000 的正整數），
// 且能獲取其周長及面積。

import java.util.Scanner;

// 不要更改這裡。 >>>>>
public class ANS {
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
class Rectangle {
    int length;
    int width;

    Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    int getPerimeter() {
        return 2 * (length + width);
    }

    int getArea() {
        return length * width;
    }
}
