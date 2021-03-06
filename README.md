# LI323-DNA
2nd project for LI323.
**UPMC - L3 - 2014-2015**

## Analyse statistique de genomes

L'objectif de ce projet était de pouvoir mettre au point des éléments qui permetterai de faciliter la lecture d'une séquence génomique par le biais d'outils d'analyse statistique ainsi que de permettre l'annotation automatique de ceux-ci. Nous avons dans un premier temps effectué un modèle de détection de gènes qui nous permet d'effectuer la prédiction de l'appartenance d'une séquence a un génome. Par la suite , nous avons réduit le modèle de détection , afin de chercher l'appartenance ou non d'une séquence donné a un gène d'un génome. Et donc pouvoir effectuer cette annotation.

2.1.1) Appliquer le modèle de langue vu pour le projet 1 pour estimer un modèle à partir d’un génome donné. Ici, chaque organisme a en quelque sorte son génome écrit dans une "langue" spécifique. Ecrire la fonction correspondante d’estimation à partir d’un fichier FASTA Nous avons ici effectué un calcul simple afin d'avoir un modèle pour chaque génome : nombre d'occurences de la lettre donné ( A C G ou T ici ) / nombre de lettres total

2.1.2) Adapter la méthode du projet 1 qui permette de prédire que quelle espèce un segment d’ADN donné vient à partir de différent modèles de génomes.

Nous avons réutilisé notre fonction de détection de language d'un mot du premier projet ici mais , pour un segment adn via la somme des logarithmes des differentes probabilités a laquelle nous prennons le meilleur score.

2.1.3) Adapter la méthode du projet 1 qui permette de prédire que quelle espèce un segment d’ADN donné vient à partir de différent modèles de génomes.

On pourrait essayer de construire un modèle de génomes basés sur des binomes de lettres, voir des trinomes ( ce qui sera effectué plus bas ) afin d'obtenir plus de spécification. Nous pouvons aussi essayer de prendre des tailles très différentes de génomes afin d'obtenir des modèles bien différents. Enfin ,il est possible d'essayer de considérer que la probabilité qu'a une lettre de tomber deux fois de suite par exemple dans le modèle de génome ne sera pas la même que la probabilité qu'on les autres lettres de tomber.

2.2.1) Ecrire une fonction qui, à partir de plusieurs séquences de gènes, estime le modèle d’apparition de codons.

Nous avons ici pris tout les modèles possibles pour des codons ( excepté les codons stop , c'est a dire 61 modèles différents ) que nous avons stockés dans un tableau de hashage. Enfin nous avons compté par groupe de 3 lettres ces différentes séquences de genes en passant a la suivante si jamais un codon stop était rencontré. Puis nous avons effectué le meme calcul que précedemment : nombre d'occurences du codon( 1 sur les 61 possibles ) / nombre de codons totaux comptés.

2.2.2) Ecrire une fonction qui calcule la probabilité d’une séquence d’être un généré par le modèle de gène

Nous allons ici réutiliser ce que nous avons vu au premier projet. Il nous faut ici éffectuer la somme des logs sur la séquence donné sachant le gène ( ici une ligne dans le fichier FASTA sur les genes ). Nous obtenons un score pour chaque gène et prenons le score maximal de cette opération. Il faut le faire également sur la séquence donné mais sachant le génome , c'est a dire en utilisant le modèle de la premiere partie ou bien sur l'intégralité du fichier de séquence de genes. Enfin nous divisons les deux résultats obtenus afin d'obtenir un score. Nous effectuons cela 3 fois : en effet au cas ou le nombres de lettres de la séquence donné ne serait pas un multiple de trois , il nous faut éffectuer ce calcul sur la meme séquence mais décalé de un caractère et de deux caractère Nous prendrons le meilleur score entre les résultats déja obtenus sur chacun des calculs de chaque décalage.

2.2.3) Ecrire une fonction qui décide si un fragment de séquence est ou non un gène. Attention, comme les codons sont composés de trois lettres, vous devrez à chaque fois tester trois phases de lecture différentes et négliger les séquences qui pourraient apparaitre après un codon stop.

Nous avons ici pris des fragements des séquences de tests, et nous avons éffectué les mêmes calculs que précédamment dessus. Par la suite nous avons comparé les scores obtenus , et avons décidé de catégoriser en tant que gènes les scores obtenus qui étaient supérieurs aux scores max de la question précédente de chaque fichier.

2.2.4) On veut maintenant annoter les gènes le long du génome. Pour faire cela dans une première phase, on extrait va utiliser une fenêtre d’une certaine longueur (d = 100 nt par exemple), et la classer comme gène ou non gène. Ecrire la fonction correspondante.

Pour ce faire , nous avons découpé par blocs de x caratères les fichiers de génomes , puis nous avons effectué les mêmes calculs que précédamment et pris les mêmes décisions qu'a la question précédente afin de savoir si le i-ème bloc du fichier est un gène ou non.

2.2.5) Comment se comporte la qualité des résultats en fonction de la longueur de la fenêtre ? Quelle limita- tion voyez vous à cette méthode ? Comment pourrait-on imaginer une méthode plus performante ?

Plus nous prenons des fenêtres de taille élevés , plus les résultats trouvés seront faibles , vu que la précision sur les séquences recherchés augmentera. Dépassé une certaine limite , nous ne pourrons plus déterminer si oui ou non un morceau du génome sera un gène ou non , vu qu'il pourrait faire parti a la fois d'une région non codante , d'un gène et d'un autre gène par exemple.

3.1.1) Ecrire une fonction pour simuler une séquence de longueur donnée x fois. (a) en utilisant un modèle où toutes les lettres ont la même probabilité (équiprobable) (b) avec un modèle où pA =pT =0.4 et pG =pC =0.1

Cf méthode randomSeq dans la classe Simulation

3.1.2) Afin de calculer la probabilité empirique pour des comptages de mots de séquences aléatoires, nous avons parcouru deux par deux les séquences aléatoires crées et nous avons compté le nombres de mots trouvés parmi ceux demandés dans chacune d'entres elles. Si le nombre d'occurence trouvé était plus élevé dans la deuxième séquence que dans la première , nous incrémentons alors un compteur qui a la fin de la méthode nous rend la probabilité empirique.

Voici le diagramme de classe du projet.
![img](http://lplit.github.io/LI323-DNA/images/diag.png)
