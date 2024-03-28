package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe fournit des méthodes pour adapter un algorithme de recherche de chemin à une carte spécifique.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve un chemin entre deux positions sur une carte en utilisant un algorithme donné.
     *
     * @param algorithme l'algorithme à utiliser pour trouver le chemin.
     * @param carte      la carte sur laquelle chercher le chemin.
     * @param xDepart    la coordonnée X du départ.
     * @param yDepart    la coordonnée Y du départ.
     * @param xArrivee   la coordonnée X de l'arrivée.
     * @param yArrivee   la coordonnée Y de l'arrivée.
     * @return           un objet Chemin représentant le chemin trouvé.
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = getNoeud(graphe, xDepart, yDepart);
        Noeud<Case> arrivee = getNoeud(graphe, xArrivee, yArrivee);
        List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(graphe, depart, arrivee);

        if (noeudsChemin == null || noeudsChemin.isEmpty()) {
            return new Chemin(new ArrayList<>());
        }
        return new Chemin(afficherChemin(noeudsChemin));
    }

    /**
     * Crée un graphe à partir de la carte spécifiée.
     *
     * @param carte la carte à partir de laquelle créer le graphe.
     * @return le graphe créé.
     */
    private static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case currentCase = new Case(carte.getTuile(x, y), x, y);
                Noeud<Case> currentNoeud = new Noeud<>(currentCase);
                graphe.ajouterNoeud(currentNoeud);
            }
        }
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = getNoeud(graphe, x, y);
                ajouterAretesVoisines(graphe, currentNoeud, x, y, largeur, hauteur);
            }
        }
        return graphe;
    }

    /**
     * Ajoute des arêtes voisines au nœud spécifié dans le graphe.
     *
     * @param graphe le graphe auquel ajouter des arêtes.
     * @param currentNoeud le nœud auquel ajouter des arêtes voisines.
     * @param x la coordonnée X du nœud.
     * @param y la coordonnée Y du nœud.
     * @param largeur la largeur de la carte.
     * @param hauteur la hauteur de la carte.
     */
    private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> currentNoeud, int x, int y, int largeur,
                                              int hauteur) {

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                    Noeud<Case> voisinNoeud = getNoeud(graphe, newX, newY);
                    if (voisinNoeud != null) {
                        double cout = calculerCout(currentNoeud.getValeur(), voisinNoeud.getValeur());
                        graphe.ajouterArete(currentNoeud, voisinNoeud, cout);
                        currentNoeud.ajouterVoisin(voisinNoeud);
                    }
                }
            }
        }
    }

    /**
     * Calcule le coût entre deux cases données.
     *
     * @param from la case de départ.
     * @param to la case d'arrivée.
     * @return le coût entre les deux cases.
     */
    private static double calculerCout(Case from, Case to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Les cases doivent être non nulles");
        }
        return from.getTuile().getPenalite() + to.getTuile().getPenalite();
    }

    /**
     * Affiche le chemin trouvé.
     *
     * @param chemin la liste des nœuds constituant le chemin.
     * @return la liste des cases constituant le chemin.
     */
    private static List<Case> afficherChemin(List<Noeud<Case>> chemin) {
        if (chemin.isEmpty()) {
            System.out.println("Aucun chemin trouvé !");
            return new ArrayList<>();
        }
        System.out.print("Chemin: ");
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : chemin) {
            Case caseNode = noeud.getValeur();
            cheminCases.add(caseNode);
            System.out.print("\n -> " + caseNode.toString());
        }
        System.out.println();

        return cheminCases;
    }

    /**
     * Récupère le nœud à partir des coordonnées spécifiées.
     *
     * @param graphe le graphe contenant les nœuds.
     * @param x la coordonnée X du nœud.
     * @param y la coordonnée Y du nœud.
     * @return le nœud correspondant aux coordonnées spécifiées.
     */
    private static Noeud<Case> getNoeud(Graphe<Case> graphe, int x, int y) {
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            if (noeud.getValeur().getX() == x && noeud.getValeur().getY() == y) {
                return noeud;
            }
        }
        return null;
    }
}