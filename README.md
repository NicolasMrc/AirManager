
Welcome to Air Manager !
===================


Air Manager est l'application de gestion de vol crée dans le cadre du cours de JAVA de L3 Asyria de l'EFREI.
Cette application vise à permettre l'administration et la visualisation de tableaux de vols.

Les utilisateurs de l'application sont classé en trois groupes

 - Administrateurs : ils peuvent de connecter au portail administration via l'interface d'authentification de l'application afin de créer ou supprimer des membres d’équipage, des vols, des avion, des types d'avion.
 - Managers : peuvent assigner une équipage aux vols déjà existants, un équipage contient un pilote, un copilote et un nombre de Personnel Non Commercial variable (qui dépend du type d'avion utilisé pour le vol).
 - Membre d'équipage : peuvent visualiser les vols sur lesquelles ils ont été assignés.



<i class="icon-eye"></i> Respect des consignes
-------------------

Un certain nombre de consignes ont été specifiée dans le sujet, vous les trouverez ci-dessous ainsi que la façon dont nous les avons implémentées.

#### <i class="icon-cog"></i> **Conception**

 - **Packages** : plusieur packages afin d'organiser l'application en structure MVC on retrouve donc entres autres les packages suivants :
		 **GUI** : regroupe les interfaces graphiques ainsi que les éléments utilisée par celles ci.
		 **Repository** : contient les différents repository de l'application permettant l'interaction avec la base de donnée.
		 **Services** : regroupes les service de chaque entités, contient le plus gros de la logique métier.
		**Entités** :  Les entités utilisées dans l'application.
		
 - **Interfaces** : Interface de Service, permet de définir les signature des méthodes communes aux services (findAll, save, delete...) en utilisant des type generique.
 
 - **Classes abstraites** : TODO

 - **Exceptions** : présentent dans le package Exceptions, on retrouve par exemple l'exception EntityNotFound, cette exception est levée lorsque un repository ne trouve pas d'entité en base correspondant a l'id passé en parametre d'un findById.

#### <i class="icon-refresh"></i> **Persistance**

Deux types de persistances sont employées dans l'application : 

- Toutes les données de l'application sont sauvegardées automatiquement en **base de donnée** lors de chaque modification sur une entitée.
- Les membres peuvent exporter dans un fichier le tableau des vols grâce a la **serialization** (on peut imaginer que d'implementer plus tard une fonctionnalité d'importation a partir de fichier serialiser grace a ce mechanimse)'.
- Les membres d'equipage peuvent exporter les vols sur lesquels ils sont assigné, cela créer un fichier html souvrant dans le navigateur de l'utilisateur contenant un tableau de vol tel que celui ci :
[![Vol](http://i.imgur.com/btje2Hf.png)](http://i.imgur.com/btje2Hf.png)

#### <i class="icon-refresh"></i> **Interface Graphique**

Nous avons pris soin de réaliser de belles interfaces graphiques afin de faciliter la compréhension et l'utilisation de l'application.

L'application est découpée en 4 interfaces :

#####L'interface de Login
[![Administration](http://i.imgur.com/wjqEZbC.png)](http://i.imgur.com/wjqEZbC.png)
#####Le portail Administrateur
[![Administration](http://i.imgur.com/36tgqLo.png)](http://i.imgur.com/36tgqLo.png)
#####Le portail Manager
[![Portail Manager](http://i.imgur.com/nfCykkw.png)](http://i.imgur.com/nfCykkw.png)
#####Le portail Membre d'équipage
[![Portail Membre](http://i.imgur.com/uvPVIsY.png)](http://i.imgur.com/uvPVIsY.png)

Le point de départ de l'application est l'interface de login, puis l'utilisateur pourra accéder aux autres interface en s'authentifiant avec son nom d'utilisateur et son mot de passe.

<i class="icon-download"></i> Installation
-------------

L'installation de Air Manager sur votre IDE préféré est très simple et peut s'effectuer en quelques étapes seulement : 

####**<i class="icon-github-circled"></i> Clonage du projet**

    git clone https://github.com/NicolasMrc/AirManager.git

####**<i class="icon-layers"></i> Configuration de la base de donnée**

**1ère étape :** 
Executer le script présent dans le fichier <kbd>CreationBase.sql</kbd> afin de creer la structure de la base de donnée.

**2ème étapge :**
Modifier les attributs <kbd>port</kbd>, <kbd>motDePasse</kbd>, <kbd>utilisateur</kbd> dans le fichier <kbd>BDDConfig.java</kbd> afin de permettre la connexion avec votre base de donnée.

####**<i class="icon-code"></i> Configuration de l'IDE**

Afin de pouvoir exécuter les RUN suivant il est nécessaire de lancer les méthodes main des classes listées ci-dessous.

**Configuration de RUN obligatoire :**

<kbd>LoginGUI.java</kbd>

**RUN facultatifs :**

<kbd>AdminGUI.java</kbd> Afin de lancer directement l'interface d'administration sans passer par la connexion
<kbd>ManagerGUI.java</kbd> Afin de lancer directement l'interface de Manager sans passer par la connexion
<kbd>DataDB.java</kbd> Pour pré remplir la base de donnée

#### **<i class="icon-list"></i> Population automatique de la base de donnée**
executer le main présent dans le fichier <kbd>DataDB.java</kbd>
		> **Utilisateurs inseré par la population de la base :**
 - Administrateur : nom d'utilisateur : "*admin*", mot de passe "*admin*".
 - Manager : nom d'utilisateur : "*manager*", mot de passe "*manager*".
 - Utilisateur : nom d'utilisateur : *nom de famille*, mot de passe : "*password*".
 
 
 
 TODO
 -------------
 
 Voici la listes des éléments qu'il reste a développer et à ameliorer :
 
 - Rework de l'interface Administration (pour appliquer les meme style et layout que les autres interface qui sont plus optimisées due aux connaissance prise au cours du développement du projet)
 - Utilisation de champs date avec date picker (pour l'instant juste des string poour les dates)
 - Fonction d'import de vol par fichier pour l'admin
 