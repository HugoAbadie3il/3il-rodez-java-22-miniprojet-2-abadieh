package fr.ecole3il.rodez2023.carte.chemin.elements;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de test pour le noeud
 */
public class TestNoeud {

    /**
     * Test du constructeur
     */
    @Test
    public void TestNoeud() {
        Noeud<String> noeud = new Noeud<String>("A");
        Assert.assertEquals("A", noeud.getValeur());
    }
}
