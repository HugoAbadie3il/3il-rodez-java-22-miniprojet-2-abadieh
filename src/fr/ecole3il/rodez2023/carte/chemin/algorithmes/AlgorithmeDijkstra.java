package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

/**
 * Algorithme de recherche de chemin de Dijkstra
 * @param <E> Type générique de la valeur des noeuds
 */
public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve le chemin le plus court entre deux noeuds d'un graphe
     * @param graphe Graphe dans lequel chercher le chemin
     * @param depart Noeud de départ
     * @param arrivee Noeud d'arrivée
     * @return Liste des noeuds composant le chemin le plus court
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des structures de données
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        Map<Noeud<E>, Double> distances = new HashMap<>();
        PriorityQueue<Noeud<E>> file = new PriorityQueue<>(Comparator.comparing(distances::get));

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            distances.put(noeud, Double.MAX_VALUE);
            predecesseurs.put(noeud, null);
        }

        distances.put(depart, 0.0);
        file.add(depart);

        // Exploration des noeuds
        while (!file.isEmpty()) {
            Noeud<E> current = file.poll();

            if (current.equals(arrivee)) {
                break;
            }

            for (Noeud<E> voisin : graphe.getVoisins(current)) {
                double distance = distances.get(current) + graphe.getCout(current, voisin);

                if (distance < distances.get(voisin)) {
                    distances.put(voisin, distance);
                    predecesseurs.put(voisin, current);
                    file.add(voisin);
                }
            }
        }

        // Reconstitution du chemin
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> current = arrivee;

        while (current != null) {
            chemin.add(0, current);
            current = predecesseurs.get(current);
        }

        Collections.reverse(chemin);

        return chemin;
    }
}