package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

/**
 * Algorithme de recherche de chemin de A*
 * @param <E> Type générique de la valeur des noeuds
 */
public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve le chemin le plus court entre deux noeuds d'un graphe
     * @param graphe Graphe dans lequel chercher le chemin
     * @param depart Noeud de départ
     * @param arrivee Noeud d'arrivée
     * @return Liste des noeuds composant le chemin le plus court
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialize the open set, closed set, and maps to store the gScore and fScore of each node
        Set<Noeud<E>> openSet = new HashSet<>();
        Set<Noeud<E>> closedSet = new HashSet<>();
        Map<Noeud<E>, Noeud<E>> cameFrom = new HashMap<>();
        Map<Noeud<E>, Double> gScore = new HashMap<>();
        Map<Noeud<E>, Double> fScore = new HashMap<>();

        // Set the gScore and fScore of each node to infinity initially
        for (Noeud<E> node : graphe.getNoeuds()) {
            gScore.put(node, Double.MAX_VALUE);
            fScore.put(node, Double.MAX_VALUE);
            cameFrom.put(node, null);
        }

        // The gScore of the start node is 0 and its fScore is the heuristic cost from the start to the goal
        gScore.put(depart, 0.0);
        fScore.put(depart, h(depart, arrivee));
        openSet.add(depart);

        // While there are still nodes to be evaluated
        while (!openSet.isEmpty()) {
            // Get the node in the open set with the lowest fScore
            Noeud<E> current = getNoeudWithLowestFScore(openSet, fScore);

            // If the current node is the goal, then we have found the shortest path
            if (current.equals(arrivee)) {
                return reconstructPath(cameFrom, current);
            }

            // Move the current node from the open set to the closed set
            openSet.remove(current);
            closedSet.add(current);

            // For each neighbor of the current node
            for (Noeud<E> neighbor : graphe.getVoisins(current)) {
                // If the neighbor is in the closed set, ignore it
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                // Calculate the tentative gScore of the neighbor
                double tentativeGScore = gScore.get(current) + graphe.getCout(current, neighbor);

                // If the neighbor is not in the open set, add it
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScore.get(neighbor)) {
                    // If the tentative gScore is not better than the current gScore, ignore this neighbor
                    continue;
                }

                // If we reach here, then this path is the best so far, so record it
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeGScore);
                fScore.put(neighbor, gScore.get(neighbor) + h(neighbor, arrivee));
            }
        }

        // If we reach here, then there is no path from the start to the goal
        return null;
    }

    /**
     * Fonction heuristique pour estimer le coût du chemin restant
     * @param from Le noeud actuel
     * @param to Le noeud objectif
     * @return Le coût estimé du chemin restant
     */
    private double h(Noeud<E> from, Noeud<E> to) {
        // implements heuristic function here
        return 0.0;
    }

    /**
     * Retourne le noeud de l'open set avec le plus bas fScore
     * @param openSet L'ensemble des noeuds à évaluer
     * @param fScore Le fScore de chaque noeud
     * @return Le noeud avec le plus bas fScore
     */
    private Noeud<E> getNoeudWithLowestFScore(Set<Noeud<E>> openSet, Map<Noeud<E>, Double> fScore) {
        Noeud<E> lowest = null;

        for (Noeud<E> node : openSet) {
            if (lowest == null || fScore.get(node) < fScore.get(lowest)) {
                lowest = node;
            }
        }

        return lowest;
    }

    /**
     * Reconstitue le chemin à partir des prédécesseurs
     * @param cameFrom Les prédécesseurs de chaque noeud
     * @param current Le noeud actuel
     * @return Le chemin complet
     */
    private List<Noeud<E>> reconstructPath(Map<Noeud<E>, Noeud<E>> cameFrom, Noeud<E> current) {
        List<Noeud<E>> totalPath = new ArrayList<>();
        totalPath.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }

        Collections.reverse(totalPath);

        return totalPath;
    }
}