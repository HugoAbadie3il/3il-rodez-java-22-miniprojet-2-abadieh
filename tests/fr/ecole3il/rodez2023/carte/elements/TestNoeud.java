package fr.ecole3il.rodez2023.carte.elements;

import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.Assert;
import org.junit.Test;

public class TestNoeud {

    @Test
    public void TestNoeud() {
        Noeud<String> noeud = new Noeud<String>("A");
        Assert.assertEquals("A", noeud.getValeur());
    }
}
