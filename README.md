# SCRABBLE ![Static Badge](https://img.shields.io/badge/S-yellow) ![Static Badge](https://img.shields.io/badge/C-yellow) ![Static Badge](https://img.shields.io/badge/A-yellow) ![Static Badge](https://img.shields.io/badge/B-yellow) ![Static Badge](https://img.shields.io/badge/B-yellow) ![Static Badge](https://img.shields.io/badge/L-yellow) ![Static Badge](https://img.shields.io/badge/E-yellow)



# Règles


Le jeu se joue à au moins deux joueurs et comporte un maitre du jeu non joueur.

Valeur des lettres

Lettre "J" du jeu francophone
A,E,I,L,N,O,R,S,T,U : 1 point
D,G,M : 2 points
B,C,P : 3 points
F,H,V : 4 points
J,Q : 8 points
K,W,X,Y,Z : 10 points
Joker : 0 point

# Mise en place 🛠️
1. Distribution des lettres 🅰️
- Chaque joueur commence avec une pile aléatoire de lettres.
- Les piles doivent contenir 7 lettre maximum.
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
- Français ![Drapeau Français](https://upload.wikimedia.org/wikipedia/en/thumb/c/c3/Flag_of_France.svg/20px-Flag_of_France.svg.png)
- Anglais ![Drapeau Angleterre](https://upload.wikimedia.org/wikipedia/en/thumb/b/be/Flag_of_England.svg/20px-Flag_of_England.svg.png)
- Espagnol ![Drapeau Espagne](https://upload.wikimedia.org/wikipedia/en/thumb/9/9a/Flag_of_Spain.svg/20px-Flag_of_Spain.svg.png)


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


