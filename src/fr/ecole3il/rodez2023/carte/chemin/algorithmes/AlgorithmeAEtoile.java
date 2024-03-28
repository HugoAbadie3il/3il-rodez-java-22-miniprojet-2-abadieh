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
        // Initialisation du set des noeuds à évaluer, du set des noeuds déjà évalués, des prédécesseurs, des scores réels et potentiels
        Set<Noeud<E>> openSet = new HashSet<>();
        Set<Noeud<E>> closedSet = new HashSet<>();
        Map<Noeud<E>, Noeud<E>> cameFrom = new HashMap<>();
        Map<Noeud<E>, Double> gScore = new HashMap<>();
        Map<Noeud<E>, Double> fScore = new HashMap<>();

        // Définition des scores initiaux, tous les noeuds ont un gScore et un fScore infini
        for (Noeud<E> node : graphe.getNoeuds()) {
            gScore.put(node, Double.MAX_VALUE);
            fScore.put(node, Double.MAX_VALUE);
            cameFrom.put(node, null);
        }

        // Initialisation du noeud de départ
        gScore.put(depart, 0.0);
        fScore.put(depart, h(depart, arrivee));
        openSet.add(depart);

        // Tant qu'il reste des noeuds à évaluer
        while (!openSet.isEmpty()) {
            // Récupère le noeud de l'open set avec le plus bas fScore
            Noeud<E> current = getNoeudWithLowestFScore(openSet, fScore);

            // Si le noeud actuel est le noeud d'arrivée, reconstruit le chemin et le retourne
            if (current.equals(arrivee)) {
                return reconstructPath(cameFrom, current);
            }

            // Déplace le noeud actuel de l'open set vers le closed set
            openSet.remove(current);
            closedSet.add(current);

            // Pour chaque voisin du noeud actuel
            for (Noeud<E> neighbor : graphe.getVoisins(current)) {
                // Ignore les voisins déjà évalués
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                // Calcule le gScore potentiel du voisin
                double tentativeGScore = gScore.get(current) + graphe.getCout(current, neighbor);

                // Si le voisin n'est pas dans l'open set, l'ajoute
                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                } else if (tentativeGScore >= gScore.get(neighbor)) {
                    // Si le gScore potentiel est plus grand que le gScore actuel, ignore ce voisin
                    continue;
                }

                // Ce chemin est le meilleur jusqu'à présent, on l'enregistre
                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeGScore);
                fScore.put(neighbor, gScore.get(neighbor) + h(neighbor, arrivee));
            }
        }

        // Si aucun chemin n'a été trouvé, retourne null
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