import java.io.*;
import java.util.*;

class Main {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> preorder = new ArrayList<>();

        String input;
        while ((input = br.readLine()) != null) {
            preorder.add(Integer.parseInt(input));
        }
        List<Integer> postorder = postorderFromPreorder(preorder);
        StringBuilder sb = new StringBuilder();

        for (Integer i : postorder) {
            sb.append(Integer.toString(i)).append(NEW_LINE);
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static List<Integer> postorderFromPreorder(List<Integer> preorder) {
        List<Integer> postorder = new ArrayList<>();
        if (preorder == null || preorder.isEmpty()) {
            return postorder;
        }

        int rootVal = preorder.get(0);

        int splitIndex = preorder.size();
        for (int i = 1; i < preorder.size(); i++) {
            if (preorder.get(i) > rootVal) {
                splitIndex = i;
                break;
            }
        }

        List<Integer> leftSubtreePreorder = preorder.subList(1, splitIndex);
        List<Integer> rightSubtreePreorder = preorder.subList(splitIndex, preorder.size());

        postorder.addAll(postorderFromPreorder(leftSubtreePreorder));
        postorder.addAll(postorderFromPreorder(rightSubtreePreorder));

        postorder.add(rootVal);

        return postorder;
    }
}
