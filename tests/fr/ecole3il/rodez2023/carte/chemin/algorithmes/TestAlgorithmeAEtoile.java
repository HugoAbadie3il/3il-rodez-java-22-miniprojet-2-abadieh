package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * Test de l'algorithme de recherche de chemin de A*
 */
public class TestAlgorithmeAEtoile {

    private Graphe<String> graph;
    private AlgorithmeAEtoile<String> aetoile;

    /**
     * Initialisation des structures de données
     */
    @Before
    public void setup() {
        graph = new Graphe<>();
        aetoile = new AlgorithmeAEtoile<>();
    }

    /**
     * Test de recherche du chemin le plus court
     */
    @Test
    public void testFindShortestPath() {
        Noeud<String> nodeA = new Noeud<>("A");
        Noeud<String> nodeB = new Noeud<>("B");
        Noeud<String> nodeC = new Noeud<>("C");
        graph.ajouterNoeud(nodeA);
        graph.ajouterNoeud(nodeB);
        graph.ajouterNoeud(nodeC);
        graph.ajouterArete(nodeA, nodeB, 1.0);
        graph.ajouterArete(nodeB, nodeC, 1.0);
        graph.ajouterArete(nodeA, nodeC, 3.0);

        List<Noeud<String>> shortestPath = aetoile.trouverChemin(graph, nodeA, nodeC);

        Assert.assertEquals(3, shortestPath.size());
        Assert.assertEquals(nodeA, shortestPath.get(0));
        Assert.assertEquals(nodeB, shortestPath.get(1));
        Assert.assertEquals(nodeC, shortestPath.get(2));
    }
}