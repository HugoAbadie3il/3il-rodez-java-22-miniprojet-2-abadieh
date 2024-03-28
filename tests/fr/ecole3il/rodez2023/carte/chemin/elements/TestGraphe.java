package fr.ecole3il.rodez2023.carte.chemin.elements;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour le graphe
 */
public class TestGraphe{

    private Graphe<String> graph;

    @Before
    public void setup() {
        graph = new Graphe<>();
    }

    /**
     * Test de la méthode ajouterNoeud
     */
    @Test
    public void testAddNode() {
        Noeud<String> nodeA = new Noeud<>("A");
        graph.ajouterNoeud(nodeA);
        Assert.assertTrue(graph.getNoeuds().contains(nodeA));
    }

    /**
     * Test de la méthode ajouterArete
     */
    @Test
    public void testAddEdge() {
        Noeud<String> nodeA = new Noeud<>("A");
        Noeud<String> nodeB = new Noeud<>("B");
        graph.ajouterNoeud(nodeA);
        graph.ajouterNoeud(nodeB);
        graph.ajouterArete(nodeA, nodeB, 1.0);
        Assert.assertTrue(nodeA.getVoisins().contains(nodeB));
        Assert.assertTrue(nodeB.getVoisins().contains(nodeA));
    }

    /**
     * Test de la méthode getNoeuds
     */
    @Test
    public void testGetNodes() {
        Noeud<String> nodeA = new Noeud<>("A");
        Noeud<String> nodeB = new Noeud<>("B");
        graph.ajouterNoeud(nodeA);
        graph.ajouterNoeud(nodeB);
        Assert.assertTrue(graph.getNoeuds().contains(nodeA));
        Assert.assertTrue(graph.getNoeuds().contains(nodeB));
    }
}