# Projet Colonisation LV-223

Vous trouverez sur ce dépôt les sources de l'IHM en JavaFX. Vous 
pouvez la garder en l'état ou bien la faire évoluer à votre 
convenance.

Le dossier [l223_lib](exec_jar/l223_lib) contient les bibliothèques
utilisées dans ce projet, notamment la bibliothèque _jFuzzyLogic_
permettant de mettre en place les mécanismes de logique floue qui 
sont attendus. 
L'analyse du fichier JSON contenant la structure de la planète est
réalisée à l'aide de la bibliothèque _Jackson_, elle aussi présente 
dans le dossier.

# Rapport LV-223
## Team Planet
### Ce qui a été appliqué 
#### Logique floue
Pour la planète nous avons appliqué la logique floue demandée sur le sujet. Mais nous avons ajouté un nouveau facteur qui est l’émotion. L’intérêt d’ajouter une autre variable d’entrée pour les règles de métamorphoses était de multiplier les règles afin de gagner en précision pour les métamorphoses.

#### ShockWave
La deuxième fonctionnalité faite pour la planète sont les shockwaves qui seront calculer aussi par la logique floue en utilisant ce qui a été fait pour les métamorphoses. En effet nous allons juger l’extraction ou le prélèvement via les fonctions d’appartenances d’entrée déja établies. Une fois que nous savons si l’extraction / prélèvement est petite , moyenne ou grande. Nous allons réaliser une onde de chocs d’une certaine amplitude et d’une certaine rapidité en fonction de notre ordre de grandeur d’extraction/prélèvement et aussi de l’émotion de la planète.

### Ce qu’il reste à faire
#### Agents réactifs
Pour la suite de la planète il serait intéressant d’ajouter des agents réactifs en effet avec l’émotion qui s’ajoute dans notre programme, nous aurions pu imaginer des agents réactifs qu’ils soient hostiles ou non pour aider les robots ou les nuire. Pas de soucis de temps, nous n’avons pas implémenté cette partie.


## Team James Bond
### Ce qui a été appliqué 
#### Astar
Pour certains agents, nous avons implémenté l'algorithme Astar pour trouver le chemin le plus rapide vers leurs objectifs, par exemple pour les mineurs. Cependant, ce ne sont pas les agents en soi qui choisissent leurs objectifs.

#### Choix des objectifs
Chaque agent de terrain (mineurs, cartographes, fermiers ...) possède une liste d'objectifs lui indiquant ce qu'il doit faire en priorité (exemple : en temps normal, un mineur mine, mais si le centralisateur lui indique qu'il doit aller chercher un autre robot en panne, cela passe en priorité). Et ces objectifs vont générer une liste d'action à effectuer pour le robot, et dans le cas où le robot doit aller miner par exemple, l'objectif demande au centralisateur quelle case il doit miner. Le centralisateur effectue donc un travail pour déterminer la case à choisir, en fonction de la distance de la case au centralisateur, au robot, et de la réactivité de la planet sur cette case en particulier.

### Ce qu’il reste à faire
#### Implémentation des autres robots
À l'heure actuelle, nous n'avons pas encore implémenté le comportement de base de tous les robots. De plus certains algorithmes généraux ne sont pas mis en place comme la phase d'appel d'offre entre le centralisateur et les robots pour choisir qui doit aider un robot blessé.
