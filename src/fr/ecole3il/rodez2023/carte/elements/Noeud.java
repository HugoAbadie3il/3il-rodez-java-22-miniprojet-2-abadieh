package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Noeud d'un graphe
 * @param <E> Type générique de la valeur du noeud
 */
public class Noeud<E> {
    private E valeur;
    private List<Noeud<E>> voisins;

    /**
     * Crée un noeud avec une valeur et initialise la liste des voisins.
     * @param valeur Valeur du noeud
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        voisins = new ArrayList<Noeud<E>>();
    }

    /**
     * Retourne la valeur du noeud
     * @return Valeur du noeud
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Retourne la liste des voisins du noeud
     * @return Liste des voisins du noeud
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Ajoute un voisin au noeud
     * @param voisin Noeud voisin
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        voisins.add(voisin);
    }
}
