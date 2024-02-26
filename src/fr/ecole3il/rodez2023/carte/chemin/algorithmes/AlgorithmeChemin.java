package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.List;

/**
 * Contrat pour les classes qui implémentent des algorithmes de recherche de chemin dans un graphe
 * @param <E> Type générique de la valeur des noeuds
 */
public interface AlgorithmeChemin<E> {
    /**
     * Trouve le chemin le plus court entre deux noeuds
     * @param graphe Graphe dans lequel chercher le chemin
     * @param depart Noeud de départ
     * @param arrivee Noeud d'arrivée
     * @return Liste des noeuds à parcourir pour aller du noeud de départ au noeud d'arrivée
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}
