package com.smitie.graphs;

import java.util.*;

public class Graph<T> {

    private final Map<T, List<T>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public boolean addEdge(T from, T to) {
        if (from == null || to == null)
            return false;

        List<T> adjacents = adjacencyList.get(from);

        if (adjacents == null) {
            List<T> list = new ArrayList<>();
            list.add(to);
            adjacencyList.put(from, list);
        } else
            adjacents.add(to);

        return true;
    }


    public Stack<T> topologicalSort(T startElement) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        topologicalSort(startElement, visited, stack);
        return stack;
    }

    private void topologicalSort(T current, Set<T> visited, Stack<T> stack) {
        visited.add(current);
        List<T> adjacents = adjacencyList.get(current);
        if (adjacents != null) {
            for (T t : adjacents) {
                if (t != null && !visited.contains(t)) {
                    topologicalSort(t, visited, stack);
                    visited.add(t);
                }
            }
        }
        stack.push(current);
    }
}
