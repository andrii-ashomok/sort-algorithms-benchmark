package com.example.structure.graph;

import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
*  2 start rules:
* 1. values are always positive;
* 2. we sure that there is at least one way;
*  see graph.png
* */
public class AlgorithmDijkstra {
    private static final int DEFAULT = -1;
    private final List<List<Pair<Integer, Integer>>> data;
    private final int from;
    private final int destination;

    public AlgorithmDijkstra(List<List<Pair<Integer, Integer>>> data, int from, int destination) {
        this.data = data;
        this.from = from;
        this.destination = destination;
    }

    public List<Integer> findMinWay() {
        // key -  parent, val - child
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        int[] vertex = new int[data.size()];
        int[] distances = new int[data.size()];
        Arrays.fill(vertex, DEFAULT);
        Arrays.fill(distances, DEFAULT);
        List<Integer> ways = new ArrayList<>();
        boolean[] marked = new boolean[data.size()];

        queue.add(new Pair<>(DEFAULT, from));
        distances[from] = 0;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> elem = queue.remove();
            int newDistance = 0;
            if (elem.getKey() != DEFAULT) {
                newDistance = distances[elem.getKey()] + getWeight(elem.getKey(), elem.getValue());
            }

            if (distances[elem.getValue()] == DEFAULT || distances[elem.getValue()] > newDistance) {
                vertex[elem.getValue()] = elem.getKey();
                distances[elem.getValue()] = newDistance;
            }

            if (!marked[elem.getValue()]) {
                List<Pair<Integer, Integer>> children = data.get(elem.getValue());
                for (Pair<Integer, Integer> child : children) {
                    queue.add(new Pair<>(elem.getValue(), child.getKey()));
                }

                if (elem.getKey() != DEFAULT) {
                    marked[elem.getKey()] = true;
                }
            }
        }

        ways.add(destination);
        int temp = destination;
        while (temp != from) {
            temp = vertex[temp];
            ways.add(temp);
        }

        Collections.reverse(ways);
        return ways;
    }

    private int getWeight(int x, int y) {
        List<Pair<Integer, Integer>> from = data.get(x);
        for (Pair<Integer, Integer> pair : from) {
            if (pair.getKey() == y) {
                return pair.getValue();
            }
        }

        return  0;
    }


    public List<Integer> findMaxWay() {
        // key -  parent, val - child
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        int[] vertex = new int[data.size()];
        int[] distances = new int[data.size()];
        Arrays.fill(vertex, DEFAULT);
        Arrays.fill(distances, DEFAULT);
        List<Integer> ways = new ArrayList<>();
        boolean[] marked = new boolean[data.size()];

        queue.add(new Pair<>(DEFAULT, from));
        distances[from] = 0;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> elem = queue.remove();
            int newDistance = 0;
            if (elem.getKey() != DEFAULT) {
                newDistance = distances[elem.getKey()] + getWeight(elem.getKey(), elem.getValue());
            }

            if (!marked[elem.getValue()]) {
                if (distances[elem.getValue()] == DEFAULT || distances[elem.getValue()] < newDistance) {
                    vertex[elem.getValue()] = elem.getKey();
                    distances[elem.getValue()] = newDistance;
                }

                List<Pair<Integer, Integer>> children = data.get(elem.getValue());
                for (Pair<Integer, Integer> child : children) {
                    queue.add(new Pair<>(elem.getValue(), child.getKey()));
                }

                if (elem.getKey() != DEFAULT) {
                    marked[elem.getKey()] = true;
                }
            }
        }

        ways.add(destination);
        int temp = destination;
        while (temp != from) {
            temp = vertex[temp];
            ways.add(temp);
        }

        Collections.reverse(ways);
        return ways;
    }

    public static void main(String[] args) {
        List<List<Pair<Integer, Integer>>> myList = new ArrayList<>();
        List<Pair<Integer, Integer>> zeroElements = new ArrayList<>();

        zeroElements.add(new Pair<>(2, 1));
        zeroElements.add(new Pair<>(5, 1));
        zeroElements.add(new Pair<>(4, 2));
        myList.add(zeroElements);

        List<Pair<Integer, Integer>> firstElements = new ArrayList<>();

        firstElements.add(new Pair<>(3, 1));
        firstElements.add(new Pair<>(6, 4));
        myList.add(firstElements);

        List<Pair<Integer, Integer>> secondElements = new ArrayList<>();
        secondElements.add(new Pair<>(0, 1));
        secondElements.add(new Pair<>(6, 3));
        myList.add(secondElements);

        List<Pair<Integer, Integer>> thirdElements = new ArrayList<>();
        thirdElements.add(new Pair<>(5, 2));
        thirdElements.add(new Pair<>(1, 1));
        thirdElements.add(new Pair<>(4, 3));
        myList.add(thirdElements);

        List<Pair<Integer, Integer>> fourthElements = new ArrayList<>();
        fourthElements.add(new Pair<>(3, 3));
        fourthElements.add(new Pair<>(0, 2));
        myList.add(fourthElements);

        List<Pair<Integer, Integer>> fifthElements = new ArrayList<>();
        fifthElements.add(new Pair<>(0, 1));
        fifthElements.add(new Pair<>(3, 2));
        fifthElements.add(new Pair<>(6, 2));
        myList.add(fifthElements);

        List<Pair<Integer, Integer>> sixthElements = new ArrayList<>();
        sixthElements.add(new Pair<>(2, 3));
        sixthElements.add(new Pair<>(5, 2));
        sixthElements.add(new Pair<>(1, 4));
        myList.add(sixthElements);

        AlgorithmDijkstra dijkstra = new AlgorithmDijkstra(myList, 0, 1);
        List<Integer> result = dijkstra.findMinWay();
        System.out.println("Min way: " + result);

        dijkstra = new AlgorithmDijkstra(myList, 0, 1);
        result = dijkstra.findMaxWay();
        System.out.println("Max way: " + result);
    }

}
