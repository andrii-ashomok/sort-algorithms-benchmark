package com.example.structure.graph;

import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Поиск в ширину (англ. breadth-first search, BFS) — один из методов обхода графа.
public class BreadthFirstSearch {

    List<List<Integer>> lists;
    int from; int destination;


    public BreadthFirstSearch(List<List<Integer>> lists, int from, int destination) {
        this.lists = lists;
        this.from = from;
        this.destination = destination;
    }

    public List<Integer> findAWay() {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(-1, from - 1));
        List<Integer> ways = new ArrayList<>();
        boolean[] marked = new boolean[lists.size()];
        int[] vertex = new int[lists.size()];
        Arrays.fill(vertex, -1);

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.remove();
            int elem = pair.getSecond();
            vertex[elem] = pair.getFirst();
            if (elem == destination - 1) {
                break;
            }

            if (!marked[elem]) {
                marked[elem] = true;
                List<Integer> child = lists.get(elem);
                for (Integer i : child) {
                    if (!marked[i - 1]) {
                        queue.add(new Pair<>(elem, i - 1));
                    }
                }
            }
        }

        ways.add(destination);
        int temp = destination;
        while (temp != from) {
            temp = vertex[temp - 1];
            if (temp == -1) {
                System.err.println("Vertex is not reachable");
                ways.clear();
                return ways;
            }
            temp = temp + 1;
            ways.add(temp);
        }


        return ways;
    }

    public static void main(String[] args) {
        List<List<Integer>> myList = new ArrayList<>();
        List<Integer> firstElements = new ArrayList<>();

        firstElements.add(10);
        myList.add(firstElements);

        List<Integer> secondElements = new ArrayList<>();
        secondElements.add(3);
        secondElements.add(6);
        myList.add(secondElements);

        List<Integer> thirdElements = new ArrayList<>();
        thirdElements.add(2);
        thirdElements.add(7);
        thirdElements.add(4);
        myList.add(thirdElements);

        List<Integer> fourthElements = new ArrayList<>();
        fourthElements.add(3);
        fourthElements.add(8);
        myList.add(fourthElements);

        List<Integer> fifthElements = new ArrayList<>();
        fifthElements.add(11);
        myList.add(fifthElements);

        List<Integer> sixthElements = new ArrayList<>();
        sixthElements.add(2);
        sixthElements.add(11);
        sixthElements.add(7);
        myList.add(sixthElements);

        List<Integer> seventhElements = new ArrayList<>();
        seventhElements.add(3);
        seventhElements.add(6);
        seventhElements.add(10);
        myList.add(seventhElements);

        List<Integer> eighthElements = new ArrayList<>();
        eighthElements.add(4);
        eighthElements.add(10);
        eighthElements.add(9);
        myList.add(eighthElements);

        List<Integer> ninthElements = new ArrayList<>();
        ninthElements.add(8);
        myList.add(ninthElements);

        List<Integer> tenthElements = new ArrayList<>();
        tenthElements.add(1);
        tenthElements.add(11);
        tenthElements.add(8);
        tenthElements.add(7);
        myList.add(tenthElements);

        List<Integer> eleventhElements = new ArrayList<>();
        eleventhElements.add(6);
        eleventhElements.add(10);
        eleventhElements.add(5);
        myList.add(eleventhElements);

        List<Integer> twelfthElements = new ArrayList<>();
        myList.add(twelfthElements);


        BreadthFirstSearch graph1 = new BreadthFirstSearch(myList, 1, 2);
        List<Integer> result = graph1.findAWay();
        System.out.println(result);

        BreadthFirstSearch graph2 = new BreadthFirstSearch(myList, 1, 12);
        result = graph2.findAWay();
        System.out.println(result);
    }
}
