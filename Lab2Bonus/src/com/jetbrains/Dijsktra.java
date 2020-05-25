package com.jetbrains;

public class Dijsktra {
    private static final int NO_PARENT = -1;
    int[] distancesMember;
    int[] parents;

    public void dijkstra(int[][] dagMatrix, int sourceNode)
    {
        int numberOfNodes = dagMatrix[0].length;

        int[] distancesBetweenNodes = new int[numberOfNodes];

        boolean[] visitedClientNode = new boolean[numberOfNodes];

        //Initializare cu distante infinite si vizite false;
        for (int vertexIndex = 0; vertexIndex < numberOfNodes;
             vertexIndex++)
        {
            distancesBetweenNodes[vertexIndex] = Integer.MAX_VALUE;
            visitedClientNode[vertexIndex] = false;
        }

        distancesBetweenNodes[sourceNode] = 0;

        int[] parents = new int[numberOfNodes];

        parents[sourceNode] = NO_PARENT;

        for (int i = 1; i < numberOfNodes; i++)
        {

            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;

            for (int vertexIndex = 0;
                 vertexIndex < numberOfNodes;
                 vertexIndex++)
            {
                if (!visitedClientNode[vertexIndex] &&
                        distancesBetweenNodes[vertexIndex] <
                                shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = distancesBetweenNodes[vertexIndex];
                }
            }


            visitedClientNode[nearestVertex] = true;

            for (int vertexIndex = 0;
                 vertexIndex < numberOfNodes;
                 vertexIndex++)
            {
                int edgeDistance = dagMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < distancesBetweenNodes[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    distancesBetweenNodes[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }

        printSolution(sourceNode, distancesBetweenNodes, parents);
    }

    public void printSolution(int sourceNode, int[] distances, int[] parents)
    {
        int numberOfNodes = distances.length,totalDistance=0;
        System.out.print("Vertex\t Distance\tPath");

        for (int vertexIndex = 0;
             vertexIndex < numberOfNodes;
             vertexIndex++)
        {
            if (vertexIndex != sourceNode)
            {
                System.out.print("\n" + sourceNode + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                totalDistance+=distances[vertexIndex];
                printPath(vertexIndex, parents);
            }
        }

        System.out.println("\nTotal Tour Lengths: "+totalDistance);

        this.distancesMember=distances;
    }


    public void printPath(int currentVertex,
                                  int[] parents)
    {

        if (currentVertex == NO_PARENT)
        {
            return;
        }

        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");

        this.parents=parents;
    }

    public int[] getDistancesMember() {
        return distancesMember;
    }

    public void setDistancesMember(int[] distancesMember) {
        this.distancesMember = distancesMember;
    }

    public int[] getParents() {
        return parents;
    }

    public void setParents(int[] parents) {
        this.parents = parents;
    }
}
