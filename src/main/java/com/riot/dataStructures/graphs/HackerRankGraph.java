package com.riot.dataStructures.graphs;

import java.util.*;

public class HackerRankGraph {
    public class Solution {

        //Sending astronaughts to the moon, but only in pairs of people from different countries
        public class AstronaughtNode {
            public Integer id;
            public Boolean checked =false;
            public List<AstronaughtNode> adjacencies = new ArrayList<>();

            public AstronaughtNode(Integer id) {
                this.id=id;
            }
        }
        public void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            Scanner scanner = new Scanner(System.in);
            Integer totalAstronaughts = scanner.nextInt();
            Integer totalPairs = scanner.nextInt();
            Solution solution = new Solution();
            List<Integer> astronaughtPairs = new ArrayList<>();
            for (int i=0; i<totalPairs; i++) {
                astronaughtPairs.add(scanner.nextInt());
                astronaughtPairs.add(scanner.nextInt());
            }
            //create a list of nodes and their adjacencies
            //edge case - only one pair
            if (totalAstronaughts==2 && totalPairs==1) {
                System.out.print("0");
                return;
            }
            solution.executeAlgorithm(totalAstronaughts, totalPairs, astronaughtPairs);
        }

        public void executeAlgorithm(Integer totalAstronaughts, Integer totalPairs, List<Integer> astronaughtPairs) {
            List<Integer>countryClusterSizes = new ArrayList<>();
            Map<Integer, AstronaughtNode> astrMap = new HashMap<>();
            Integer astronaughtCount =0;
            //add all astronaught pairs to a map, and update their adjacencies
            for (int i=0; i<totalPairs; i++) {
                Integer astrA = astronaughtPairs.get(2*i);
                Integer astrB = astronaughtPairs.get((2*i)+1);
                if (astrMap.containsKey(astrA) && astrMap.containsKey(astrB)) {
                    //both nodes already exist on the map but just need to be linked
                    linkAstronaughtNodes(astrMap.get(astrA), astrMap.get(astrB));
                }
                else if (astrMap.containsKey(astrA)) {
                    //already have astronaught A, add B and update adjacencies
                    AstronaughtNode nodeB = new AstronaughtNode(astrB);
                    astrMap.put(astrB, nodeB);
                    astronaughtCount++;
                    linkAstronaughtNodes(astrMap.get(astrA), nodeB);
                } else if (astrMap.containsKey(astrB)) {
                    //already have astronaught B, add A and update adjacencies
                    AstronaughtNode nodeA = new AstronaughtNode(astrA);
                    astrMap.put(astrA, nodeA);
                    astronaughtCount++;
                    linkAstronaughtNodes(astrMap.get(astrB), nodeA);
                } else {
                    //both astronaughts are new
                    AstronaughtNode nodeB = new AstronaughtNode(astrB);
                    astrMap.put(astrB, nodeB);
                    AstronaughtNode nodeA = new AstronaughtNode(astrA);
                    astrMap.put(astrA, nodeA);
                    astronaughtCount+=2;
                    linkAstronaughtNodes(nodeA, nodeB);
                }
            }

            //loop through each astronaught, counting the number in each cluster using BFS.  Add to countryCluster list
            Queue<AstronaughtNode> queue = new LinkedList<>();
            for (AstronaughtNode currNode : astrMap.values()) {
                if (!currNode.checked) {
                    //System.out.println("\nNew cluster");
                    Integer newClusterCount =0;
                    queue.add(currNode);
                    while (!queue.isEmpty()) {
                        //BFS to find size of cluster
                        AstronaughtNode searchNode = queue.remove();
                        if (!searchNode.checked) {
                            newClusterCount++;
                            searchNode.checked=true;
                            //System.out.print("Visited: " + searchNode.id);
                            for (AstronaughtNode adjacency : searchNode.adjacencies) {
                                queue.add(adjacency);
                            }
                        }
                    }
                    countryClusterSizes.add(newClusterCount);
                }
            }

            //add remaining astronaughts with no adjacencies to clusters of their own
            for (int i=astronaughtCount; i<totalAstronaughts; i++) {
                countryClusterSizes.add(1);
            }

        /*for (int i=0; i<countryClusterSizes.size(); i++) {
            System.out.println("Cluster " + i + " size: " + countryClusterSizes.get(i));
        }*/
            //for each country cluster but the last
            //and for each other country cluster not yet reviewed
            Long totalValidPairs =0L;
            for (int countryIndex=0; countryIndex<countryClusterSizes.size()-1; countryIndex++) {
                for (int targetIndex=countryIndex+1; targetIndex<countryClusterSizes.size(); targetIndex++) {
                    totalValidPairs+=countryClusterSizes.get(countryIndex)*countryClusterSizes.get(targetIndex);
                }
            }
            System.out.print(totalValidPairs);
        }

        public void linkAstronaughtNodes(AstronaughtNode a, AstronaughtNode b) {
            a.adjacencies.add(b);
            b.adjacencies.add(a);
        }
    }
}
