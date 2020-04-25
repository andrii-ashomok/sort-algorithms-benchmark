package com.example.structure.graph;

import java.util.ArrayList;
import java.util.List;

// Поиск в глубину (англ. Depth-first search, DFS) — один из методов обхода графа.
public class DepthFirstSearch {

    List<List<Integer>> lists;
    int from; int destination;

    public DepthFirstSearch(List<List<Integer>> lists, int from, int destination) {
        this.lists = lists;
        this.from = from;
        this.destination = destination;
    }

    public List<Integer> findAWay() {
        boolean[] marked = new boolean[lists.size()];
        List<Integer> way = new ArrayList<>();
        scan(from, destination, marked, way);
        if (!way.contains(destination)) {
            way.clear();
        }
        return way;
    }

    private boolean scan(int top, int destination, boolean[] marked, List<Integer> way) {
        if (way.contains(destination)) {
            return false;
        }

        if (top == -1) {
            return false;
        }

        way.add(top);
        marked[top - 1] = true;

        if (top == destination) {
            return false;
        }

        List<Integer> destinationFromTop = lists.get(top - 1);
        int elem;
        boolean noWays = true;
        for (Integer integer : destinationFromTop) {
            elem = integer;
            if (!marked[integer - 1]) {
                noWays = scan(elem, destination, marked, way);
                if (noWays) {
                    way.add(top);
                }
            }
        }

        return noWays;
    }


    // see screenshot - example.png
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


        DepthFirstSearch graph1 = new DepthFirstSearch(myList, 1, 8);
        List<Integer> result = graph1.findAWay();
        System.out.println(result);

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(myList, 1, 12);
        result = depthFirstSearch.findAWay();
        System.out.println(result);
    }

}
