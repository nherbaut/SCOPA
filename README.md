# 
https://img.shields.io/badge/S-yellow


# Règles

Le jeu se joue à au moins deux joueurs et comporte un maitre du jeu non joueur.


# Mise en place 🛠️
1. Distribution des lettres 🅰️
- Chaque joueur commence avec une pile aléatoire de lettres, face cachée.
- Les piles sont identiques en nombre, mais les lettres sont distribuées au hasard.
- 
2. Plateau de jeu 🎲
Un plateau classique de Scrabble est généré avec des cases spéciales :

- Case "Changement de langue" : Change la langue active du joueur .
- Cases bonus ⭐ :
    → Double lettre : Double la valeur d’une lettre.
    → Triple lettre : Triple la valeur d’une lettre.
    → Double mot : Double le score total du mot formé.
    → Triple mot : Triple le score total du mot formé.

3. Langue initiale 🥖
- Chaque joueur débute en français comme langue active.

4. Langues utilisées 🌍 :
- Français
- Anglais
- Espagnol


## Déroulé de la partie

Les joueurs sont répartis dans l'ordre d'arrivée. Chaque joueur affronte le prochain dans un duel, à tour de rôle (le dernier joueur affronte le premier). Si un joueur n'a plus de carte, il perd la partie et ne peut plus joueur.

### Déroulé des duels

* Lors d'un duel, chacun de joueur joue une carte de sa pile. Le joueur ayant joué la carte de plus haute valeur l'emporte et met sous sa pile les cartes jouées, en les mélangeant.
* Si les deux joueurs ont joué une carte de même valeur, le duel recommence en accumulant les cartes jouées de telle sorte à ce que le gagnant empoche la totalité des cartes du duel.

## Fin de la partie

Le gagnant est le dernier joueur en lice.

### Détail des classes principales

Un exemple de jeu supportant le réseau

* LocalWarGame la version du jeu supportant le jeu en local
* WarGameEngine le moteur du jeu
* WarGameNetorkPlayer le joueur distant en cas de partie réseau
* WarGameNetworkEngine la version du jeu supportant le réseau


# Protocole réseau

> Le protocole réseau définit les séquences des commandes échangées entre les différentes parties prenantes. Il doit contenir, pour chaque commande, l'expéditeur, le destinataire, le nom de la commande et le contenu du corps de la commande.

![protocole](doc/protocle.png)


