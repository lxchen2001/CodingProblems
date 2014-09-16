package com.coderevisited.lca;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

import com.coderevisited.utils.MathUtils;

public class SparseTableLCA
{
    private static int logV;
    private final Map<Integer, Node> nodeMap;

    public SparseTableLCA(Map<Integer, Node> nodeMap)
    {
        this.nodeMap = nodeMap;
        bfs(nodeMap.get(1));
        process();
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int V = scanner.nextInt();
        logV = (int) MathUtils.log(V, 2) + 1;
        Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();

        for (int i = 0; i < V - 1; i++) {
            int x = scanner.nextInt();
            if (!nodeMap.containsKey(x)) {
                nodeMap.put(x, new Node(x));
            }
            int y = scanner.nextInt();
            if (!nodeMap.containsKey(y)) {
                nodeMap.put(y, new Node(y));
            }

            Node m = nodeMap.get(x);
            Node n = nodeMap.get(y);
            m.addChild(n);
            n.addChild(m);
        }

        SparseTableLCA lca = new SparseTableLCA(nodeMap);
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            pw.println(lca.findLCA(nodeMap.get(A), nodeMap.get(B)).getV());
        }

        scanner.close();
        pw.close();
    }

    private Node findLCA(Node A, Node B)
    {
        if (A.getLevel() < B.getLevel()) {
            Node temp = A;
            A = B;
            B = temp;
        }

        int logLevel = (int) MathUtils.log(A.getLevel(), 2);
        for (int i = logLevel; i >= 0; i--) {
            if (A.getLevel() - (1 << i) >= B.getLevel()) {
                A = A.getSuperParent(i);
            }
        }
        if (A.getV() == B.getV()) {
            return A;
        }

        for (int i = logLevel; i >= 0; i--) {
            if (A.getSuperParent(i) != null && A.getSuperParent(i).getV() != B.getSuperParent(i).getV()) {
                A = A.getSuperParent(i);
                B = B.getSuperParent(i);
            }
        }

        return A.getParent();
    }

    private void process()
    {
        for (Node node : nodeMap.values()) {
            node.addSuperParent(node.getParent(), 0);
        }
        for (int j = 1; 1 << j < nodeMap.size(); j++) {
            for (Node node : nodeMap.values()) {
                if (node.getSuperParent(j - 1) != null) {
                    int superParent = node.getSuperParent(j - 1).getV();
                    Node n = nodeMap.get(superParent).getSuperParent(j - 1);
                    node.addSuperParent(n, j);
                }
            }
        }
    }

    private void bfs(Node root)
    {
        int level = 0;
        Queue<Node> bfsQueue = new LinkedList<Node>();
        bfsQueue.add(root);
        root.addLevel(level);
        while (!bfsQueue.isEmpty()) {
            Node n = bfsQueue.poll();
            for (Node child : n.getChildren()) {
                child.removeChild(n);
                child.addParent(n);
                child.addLevel(n.getLevel() + 1);
                bfsQueue.add(child);
            }
        }
    }

    private static class Node
    {
        private final int v;
        private final List<Node> children;
        private Node parent;
        private int level;
        private Node[] superParent = new Node[logV];

        private Node(int v)
        {
            this.v = v;
            this.children = new LinkedList<Node>();
        }

        public List<Node> getChildren()
        {
            return children;
        }

        public void addChild(Node node)
        {
            children.add(node);
        }

        public void removeChild(Node node)
        {
            children.remove(node);
        }

        public void addParent(Node node)
        {
            this.parent = node;
        }

        public void addLevel(int n)
        {
            this.level = n;
        }

        public int getLevel()
        {
            return level;
        }

        public Node getParent()
        {
            return parent;
        }

        public int getV()
        {
            return v;
        }

        public void addSuperParent(Node parent, int i)
        {
            superParent[i] = parent;
        }

        public Node getSuperParent(int index)
        {
            return superParent[index];
        }
    }

}
