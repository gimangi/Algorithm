import java.util.Scanner;

public class BOJ_15973 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        Square s1, s2;

        String[] line = sc.nextLine().split(" ");
        s1 = new Square(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));

        line = sc.nextLine().split(" ");
        s2 = new Square(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));

        if (isNull(s1, s2) || isNull(s2, s1))
            System.out.println("NULL");
        else if (isPoint(s1, s2) || isPoint(s1, s2))
            System.out.println("POINT");
        else if (isLine(s1, s2) || isLine(s2, s1))
            System.out.println("LINE");
        else
            System.out.println("FACE");
    }

    static boolean isNull(Square s1, Square s2) {
        if (s1.x2 < s2.x1 || s1.y2 < s2.y1)
            return true;
        return false;
    }

    static boolean isLine(Square s1, Square s2) {
        if (s1.x2 == s2.x1 || s1.x1 == s2.x2 || s1.y2 == s2.y1 || s1.y1 == s2.y2)
            return true;
        return false;
    }

    static boolean isPoint(Square s1, Square s2) {
        if (s1.x2 == s2.x1 && ((s1.y2 == s2.y1) || (s1.y1 == s2.y2)) ||
                s1.x1 == s2.x2 && ((s1.y2 == s2.y1) || (s1.y1 == s2.y2)))
            return true;
        return false;
    }
}

class Square {
    int x1;
    int x2;
    int y1;
    int y2;

    Square(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

    }
}
