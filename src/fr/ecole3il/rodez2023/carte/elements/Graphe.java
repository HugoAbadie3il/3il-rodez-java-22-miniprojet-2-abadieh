package fr.ecole3il.rodez2023.carte.elements;

import java.util.*;

/**
 * Classe représentant un graphe comme une liste de noeuds et une liste d'arêtes.
 * @param <E> Type générique de la valeur des noeuds
 */
public class Graphe<E> {
    private Set<Noeud<E>> noeuds;
    private Map<Map<Noeud<E>, Noeud<E>>, Integer> aretes;

    private boolean isOriented;

    /**
     * Crée un graphe vide
     * @param isOriented Indique si le graphe est orienté ou non
     */
    public Graphe(boolean isOriented) {
        this.noeuds = new HashSet<>();
        this.aretes = new HashMap<>();
        this.isOriented = isOriented;
    }

    /**
     * Crée un graphe non orienté
     */
    public Graphe() {
        this.noeuds = new HashSet<>();
        this.aretes = new HashMap<>();
        this.isOriented = false;
    }

    /**
     * Ajout d'un noeud dans le graphe
     * @param noeud Noeud à ajouter
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        this.noeuds.add(noeud);
    }

    /**
     * Ajout d'une arête entre deux noeuds avec un coût
     * @param depart Noeud de départ
     * @param arrivee Noeud d'arrivée
     * @param cout Coût de l'arête
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, int cout) {
        // Ajout des noeuds s'ils ne sont pas déjà présents
        if (!noeuds.contains(depart))
            ajouterNoeud(depart);
        if (!noeuds.contains(arrivee))
            ajouterNoeud(arrivee);

        // Création de l'arête et ajout de celle-ci dans la liste des arêtes avec le coût.
        Map<Noeud<E>, Noeud<E>> arete = new HashMap<>();
        arete.put(depart, arrivee);
        this.aretes.put(arete, cout);
        depart.ajouterVoisin(arrivee);

        // Dans le cas où le graphe n'est pas orienté, on ajoute l'arête dans l'autre sens.
        if (!isOriented) {
            Map<Noeud<E>, Noeud<E>> arete_retour = new HashMap<>();
            arete_retour.put(arrivee, depart);
            this.aretes.put(arete_retour, cout);
            arrivee.ajouterVoisin(depart);
        }
    }

    /**
     * Retourne le coût d'une arête entre deux noeuds
     * @param depart Noeud de départ
     * @param arrivee Noeud d'arrivée
     * @return Coût de l'arête
     */
    public int getCout(Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Noeud<E>> arete = new HashMap<>();
        arete.put(depart, arrivee);
        return this.aretes.get(arete);
    }

    /**
     * Retourne la liste des noeuds du graphe
     * @return Liste des noeuds du graphe
     */
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(this.noeuds);
    }

    /**
     * Retourne la liste des voisins d'un noeud, vide si le noeud n'est pas dans le graphe
     * @param noeud Noeud dont on veut les voisins
     * @return Liste des voisins du noeud
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return noeuds.contains(noeud) ? noeud.getVoisins() : new ArrayList<>();
    }
}
