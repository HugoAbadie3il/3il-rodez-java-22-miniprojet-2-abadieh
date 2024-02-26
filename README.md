# Questions

---------------------------

**Question :** Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe
et les informations associées à ces relations, comme les coûts des arêtes ?

**Réponse :** On pourrait utiliser une Map pour stocker les arêtes entre les nœuds du graphe et le coût des arêtes.

---------------------------

**Question :** Pourquoi pensez-vous que les classes Noeud et Graphe ont été définies avec des paramètres génériques ?

**Réponse :** Les classes Noeud et Graphe ont été définies avec des paramètres génériques pour permettre
la réutilisation du code pour différents types de données.

---------------------------

**Question :** Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?

**Réponse :** L'utilisation d'une interface permet de définir le format de retour des différents algorithmes
de recherche de chemin. Ainsi peu importe l'algorithme utilisé, le format de sa réponse sera le même.