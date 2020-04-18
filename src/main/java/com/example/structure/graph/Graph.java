package com.example.structure.graph;

public class Graph {

    int[][] arr;

    Graph(int[][] arr) {
        this.arr = arr;
    }

    public static void main(String[] args) {
        int[][] myArr = {
                {0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0}
        };
        Graph graph = new Graph(myArr);
        int count = graph.scan();
        System.out.println(count);
    }

    // 6 is a max
    public int scan() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] == 1) {
                    count++;
                    roundArea(i, j);

                }
            }
        }
        return count;
    }

    // div in deep
    private void roundArea(int i, int j) {
        arr[i][j] = 2;
        if (j - 1 >= 0 && arr[i][j-1] == 1) {
            roundArea(i, j - 1);
        }

        if (j + 1 < 6 && arr[i][j+1] == 1) {
            roundArea(i, j + 1);
        }

        if (i - 1 >= 0 && arr[i - 1][j] == 1) {
            roundArea(i - 1, j);
        }

        if (i + 1 < 6 && arr[i + 1][j] == 1) {
            roundArea(i + 1, j);
        }
    }

}
