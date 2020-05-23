package com.example.structure.graph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// check example ConnectedComponent.png
public class ConnectedComponent {

    private final List<List<Integer>> data;
    private final boolean[] marked;

    public ConnectedComponent(List<List<Integer>> data) {
        this.data = data;
        marked = new boolean[data.size()];
    }


    private void scan(int top) {
        if (top == -1) {
            return;
        }

        marked[top] = true;
        List<Integer> destinationFromTop = data.get(top);
        for (Integer destination : destinationFromTop) {
            if (!marked[destination]) {
                scan(destination);
            }
        }
    }

    private Integer getNumberOfConnectedComponent() {
        int count = 0;

        for (int i = 0; i < marked.length; i++) {
            if (!marked[i]) {
                count++;
                scan(i);
            }
        }

        return count;
    }

    private List<Pair<Integer, Integer>> findBridge() {
        List<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < data.size(); i++) {
            List<Integer> destinationFromTop = data.get(i);
            Integer point;
            for (Integer integer : destinationFromTop) {
                point = integer;

                Pair<Integer, Integer> pair = remove(i, point);

                Arrays.fill(marked, false);
                if (getNumberOfConnectedComponent() > 1) {
                    result.add(new Pair<Integer, Integer>(i, point));
                }

                add(i, point, pair);
            }
        }

        return result;
    }

    private Pair<Integer, Integer> remove(int x, int y) {
        List<Integer> list = data.get(x);
        int indexY = list.indexOf(y);
        list.remove(indexY);
        List<Integer> list2 = data.get(y);
        int indexX = list2.indexOf(x);
        list2.remove(indexX);
        return new Pair<Integer, Integer>(indexX, indexY);
    }


    private void add(int x, int y, Pair<Integer, Integer> pair) {
        List<Integer> list = data.get(x);
        list.add(pair.getValue(), y);
        List<Integer> list2 = data.get(y);
        list2.add(pair.getKey(), x);
    }


    public static void main(String[] args) {
        List<List<Integer>> myList = new ArrayList<List<Integer>>();
        List<Integer> zeroElements = new ArrayList<Integer>();

        zeroElements.add(1);
        zeroElements.add(2);
        myList.add(zeroElements);

        List<Integer> firstElements = new ArrayList<Integer>();

        firstElements.add(0);
        firstElements.add(3);
        firstElements.add(2);
        myList.add(firstElements);

        List<Integer> secondElements = new ArrayList<Integer>();
        secondElements.add(1);
        secondElements.add(0);
        myList.add(secondElements);

        List<Integer> thirdElements = new ArrayList<Integer>();
        thirdElements.add(1);
        thirdElements.add(4);
        thirdElements.add(6);
        myList.add(thirdElements);

        List<Integer> fourthElements = new ArrayList<Integer>();
        fourthElements.add(5);
        fourthElements.add(6);
        fourthElements.add(3);
        myList.add(fourthElements);

        List<Integer> fifthElements = new ArrayList<Integer>();
        fifthElements.add(6);
        fifthElements.add(4);
        myList.add(fifthElements);

        List<Integer> sixthElements = new ArrayList<Integer>();
        sixthElements.add(4);
        sixthElements.add(5);
        sixthElements.add(3);
        myList.add(sixthElements);

        ConnectedComponent cc = new ConnectedComponent(myList);
        System.out.println("components: " + cc.getNumberOfConnectedComponent());
        System.out.println("Array: " + Arrays.toString(cc.getMarked()));
        System.out.println("Array: " + cc.findBridge());
    }

    public boolean[] getMarked() {
        return marked;
    }

}
