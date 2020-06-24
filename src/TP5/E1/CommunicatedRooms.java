package TP5.E1;

import TP3.DirectedGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by efalcon
 */
public class CommunicatedRooms {

    private DirectedGraph<Integer, Integer> directedGraph;

    public static void main(String[] args) {
        CommunicatedRooms cm = new CommunicatedRooms(1);
        for(int i = 1; i < 11; i++) {
            cm.addRoom(i);
        }
        cm.addDoorToRoom(1,2);
        cm.addDoorToRoom(1,8);
        cm.addDoorToRoom(2,4);
        cm.addDoorToRoom(2,3);
        cm.addDoorToRoom(8,4);
        cm.addDoorToRoom(4,7);
        cm.addDoorToRoom(4,11);
        cm.addDoorToRoom(3,11);
        List<List<Integer>> pathsToRoom = cm.findPathsToRoom(1, 11);
        System.out.println(pathsToRoom);
    }

    public CommunicatedRooms(int roomId1) {
        directedGraph = new DirectedGraph<>();
    }

    // Vertex
    public void addRoom(int roomId) {
        directedGraph.agregarVertice(roomId, 0);
    }

    // Arc
    public void addDoorToRoom(int roomId1, int roomId2) {
        directedGraph.agregarArco(roomId1,roomId2,0);
    }

    /**
     * Finds the different paths from 1 room to another
     * @return a list with solutions
     */
    public List<List<Integer>> findPathsToRoom(int roomId1, int roomId2) {
        Iterator<Integer> adyacentes = directedGraph.obtenerAdyacentes(roomId1);
        List<List<Integer>> lists = new ArrayList<>();
        adyacentes.forEachRemaining(x -> {

            List<Integer> pathRecursively = findPathRecursively(new ArrayList<>(), roomId1, roomId2, State.IN_PROGRESS);
            if(!pathRecursively.isEmpty()) {
                lists.add(pathRecursively);
            }
        });
        return lists;
    }

    /**
     *
     *
     * @param list
     * @param roomId1
     * @param roomId2
     * @param state
     * @return
     */
    public List<Integer> findPathRecursively(List<Integer> list, int roomId1, int roomId2, State state) {
        Iterator<Integer> adyacentes = directedGraph.obtenerAdyacentes(roomId1);
        if(state.equals(State.FOUND)) {
            return list;
        } else if(state.equals(State.NOT_FOUND)) {
            return new ArrayList<>();
        }

        list.add(roomId1);
        while(adyacentes.hasNext()) {
            Integer next = adyacentes.next();
            if(next == roomId2) {
                return findPathRecursively(list, next, roomId2, State.FOUND);
            } else {
                findPathRecursively(list, next, roomId2, State.IN_PROGRESS);
            }
        }
        return findPathRecursively(list, roomId1, roomId2, State.NOT_FOUND);
    }
}
