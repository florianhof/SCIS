Workflow
==============
Installation de l'environement de développement
-------------------
1. Installer Eclipse et Eclipe JEE
2. Télécharger OpenXava ; la version 5.4 est recommandée mais la version 5.5.1 a l'air de fonctionner. Extraire OpenXava dans un dossier.
3. Ouvrir Eclipse sur le workspace OpenXava.
4. Afficher la perspective Git, cloner le repository https://github.com/florianhof/SCIS.git en spécifiant , au 3ème écran, la destination comme le workspace OpenXava et la branche initiale (pour le moment nommée "develop").
5. Créer une nouvelle branche, checkout cette branche
6. Importer le clone come projet dans Eclipse.
7. Repasser dans la perspective Java, ouvrir la vue Ant (Window -> Show view -> Ant). La vue Ant apparaît sur la droite, y glisser le fichier SCIS\build.xml. Ouvrir SCIS dans la vue Ant et double-cliquer sur generatePortlets
8. (Obtenir et) copier les fichierx jxl.jar et postgresql-xxx.jar dans SCIS\web\WEB-INF\lib
9. Refresh (F5) du projet SCIS