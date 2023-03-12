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



