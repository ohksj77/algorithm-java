package algorithm.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PathFindGame {

    private static final List<Node> NODES = new ArrayList<>();
    private static final List<Integer> PRE = new ArrayList<>();
    private static final List<Integer> POST = new ArrayList<>();
    private Node root;

    public int[][] solution(int[][] nodeinfo) {
        for (int i = 0; i < nodeinfo.length; i++) {
            NODES.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        Collections.sort(NODES);
        root = NODES.get(0);
        for (int i = 1; i < NODES.size(); i++) {
            connectNode(root, NODES.get(i));
        }
        preOrder(root);
        postOrder(root);
        int[][] answer = new int[2][];
        answer[0] = PRE.stream().mapToInt(i -> i).toArray();
        answer[1] = POST.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    private void preOrder(Node now) {
        if (now != null) {
            PRE.add(now.num);
            if (now.left != null) {
                preOrder(now.left);
            }
            if (now.right != null) {
                preOrder(now.right);
            }
        }
    }

    private void postOrder(Node now) {
        if (now != null) {
            if (now.left != null) {
                postOrder(now.left);
            }
            if (now.right != null) {
                postOrder(now.right);
            }
            POST.add(now.num);
        }
    }

    private void connectNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                connectNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                connectNode(parent.right, child);
            }
        }
    }

    private class Node implements Comparable<Node> {
        private Node left;
        private Node right;
        private final int num;
        private final int x;
        private final int y;

        public Node(final int num, final int x, final int y) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(final Node o) {
            return o.y - this.y;
        }
    }
}
