package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * Classe de test pour l'algorithme de Dijkstra
 */
public class TestAlgorithmeDijkstra {

    private Graphe<String> graph;
    private AlgorithmeDijkstra<String> dijkstra;

    /**
     * Initialisation des attributs avant chaque test
     */
    @Before
    public void setup() {
        graph = new Graphe<>();
        dijkstra = new AlgorithmeDijkstra<>();
    }

    /**
     * Test de la méthode trouverChemin
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

        List<Noeud<String>> shortestPath = dijkstra.trouverChemin(graph, nodeA, nodeC);

        Assert.assertEquals(3, shortestPath.size());
        Assert.assertEquals(nodeA, shortestPath.get(0));
        Assert.assertEquals(nodeB, shortestPath.get(1));
        Assert.assertEquals(nodeC, shortestPath.get(2));
    }
}