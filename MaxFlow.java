import java.util.*;

class MaxFlow {
    public static void main (String[] args){
        int[][] capacities = {
            {0, 0, 5, 3, 0},
            {0, 0, 0, 0, 7},
            {0, 3, 0, 3, 3},
            {0, 5, 0, 0, 0},
            {0, 0, 0, 0, 0}
        };
        int[][] flows = new int[capacities.length][capacities.length];
        int maxFlow = 0;

        int source = 0;
        int sink = 4;

        while (true) {
            int flowAdded = addFlow(capacities, flows, new ArrayList<>(), source, sink, Integer.MAX_VALUE);
            if (flowAdded == 0)
                break;

            maxFlow += flowAdded;
        }

        System.out.println(maxFlow);
        System.out.println(Arrays.deepToString(flows));
    }

    static int addFlow (int[][] capacities, int[][] flows, ArrayList<Integer> visited, int place, int dest, int bonus){
        if (place == dest)
            return bonus;

        for (int i = 0; i < capacities.length; i++){
            if (flows[place][i] < capacities[place][i] && !visited.contains(i)){
                int flowVal = capacities[place][i] - flows[place][i];
                ArrayList<Integer> newVisited = (ArrayList<Integer>) visited.clone();
                newVisited.add(place);
                int found = addFlow(capacities, flows, newVisited, i, dest, flowVal < bonus ? flowVal : bonus);
                if (found > 0){
                    flows[place][i] += found;
                    return found;
                }
            }

            if (flows[i][place] > 0 && !visited.contains(i)){
                ArrayList<Integer> newVisited = (ArrayList<Integer>) visited.clone();
                newVisited.add(place);
                int found = addFlow(capacities, flows, newVisited, i, dest, flows[i][place] < bonus ? flows[i][place] : bonus);
                if (found > 0){
                    flows[i][place] -= found;
                    return found;
                }
            }
        }

        return 0;
    }
}