package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        Map<Noeud<E>, Double> distances = new HashMap<>();
        PriorityQueue<Noeud<E>> file = new PriorityQueue<>(Comparator.comparing(distances::get));

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            distances.put(noeud, Double.MAX_VALUE);
        }

        distances.put(depart, 0.0);
        file.add(depart);

        while (!file.isEmpty()) {
            Noeud<E> current = file.poll();

            for (Noeud<E> voisin : graphe.getVoisins(current)) {
                double distance = distances.get(current) + graphe.getCout(current, voisin);

                if (distance < distances.get(voisin)) {
                    distances.put(voisin, distance);
                    predecesseurs.put(voisin, current);
                    file.add(voisin);
                }
            }
        }

        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> current = arrivee;

        while (current != null) {
            chemin.add(0, current);
            current = predecesseurs.get(current);
        }

        return chemin;
    }
}