import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7662 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i=0; i<T; i++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int j=0; j<k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char op = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                switch (op) {
                    case 'I':
                        map.put(num, map.getOrDefault(num, 0) + 1);
                        break;
                    case 'D':
                        if (map.isEmpty())
                            continue;

                        int deleteNum = num == 1 ? map.lastKey() : map.firstKey();

                        // 삭제하기 전 value (개수) 1인 경우에는 삭제
                        if (map.put(deleteNum, map.get(deleteNum)-1)==1)
                            map.remove(deleteNum);
                }
            }

            if (map.isEmpty())
                System.out.println("EMPTY");
            else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }

        br.close();
    }

}
