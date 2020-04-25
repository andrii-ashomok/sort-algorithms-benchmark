package com.example.structure.graph;

import java.util.ArrayList;
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
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);
        List<Integer> ways = new ArrayList<>();
        boolean[] marked = new boolean[lists.size()];

        boolean[][] reachability = new boolean[lists.size()][lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            reachability[i][i] = true;
        }
        while (!queue.isEmpty()) {
            int elem = queue.remove();
            if (!marked[elem - 1]) {
                marked[elem - 1] = true;
                List<Integer> child = lists.get(elem - 1);
                for (Integer i : child) {
                    queue.add(i);
                    reachability[elem - 1][i - 1] = true;
                }
            }
        }

        ways.add(destination);
        return ways;
    }

    private void buildReachability() {

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


        BreadthFirstSearch graph1 = new BreadthFirstSearch(myList, 1, 4);
        List<Integer> result = graph1.findAWay();
        System.out.println(result);

       /* Graph2 graph2 = new Graph2(myList, 1, 12);
        result = graph2.findAWay();
        System.out.println(result);*/
    }
}
