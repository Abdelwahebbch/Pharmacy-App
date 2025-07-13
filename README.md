
# 💊 Pharmacy-App

Une application JavaFX pour la gestion des **ordonnances médicales**, développée avec Java 17, Oracle Database et iText PDF pour la génération de documents.

---

## 📋 Fonctionnalités principales

- 📁 **Gestion des prescriptions** (ajout, modification, suppression, recherche)
- 🧑‍⚕️ **Vérification automatique** de l'existence du patient avant l'ajout d'une ordonnance
- 📄 **Génération PDF** stylée d'une ordonnance médicale
- 🌍 **Support multilingue (i18n)** *(bientôt disponible)*
- 🔐 Intégration prévue de rôles administrateur / pharmacien

---

## 🧱 Architecture

- **JavaFX** — Interface utilisateur
- **Oracle Database (ojdbc)** — Stockage des patients et ordonnances
- **Maven** — Gestion de projet et des dépendances
- **iText 7** — Génération de fichiers PDF
- **SLF4J** — Logging
- **MVC** — Architecture logique : `Controller`, `DAO`, `Model`, `Util`

---

## 🗂️ Structure du projet

```
src/
 └── main/
     └── java/
         └── com/pharmacy/
             ├── controller/         # Logique des interfaces
             ├── DAO/                # Accès aux données (prescriptions, patients)
             ├── Model/              # Classes métiers : Patient, Prescription
             ├── util/               # Outils : PDF, connexion DB, validation
             └── App.java            # Point d'entrée JavaFX
```

---

## 🛠️ Technologies

| Composant      | Version     |
|----------------|-------------|
| Java           | 17          |
| JavaFX         | 17+         |
| Oracle JDBC    | 19.3        |
| iText PDF      | 7.2.3       |
| Maven          | 3.x         |
| SLF4J          | 2.0.9       |

---

## ▶️ Lancer le projet

1. **Cloner le repo :**

```bash
git clone https://github.com/Abdelwahebbch/Pharmacy-App.git
cd pharmacy-app
```

2. **Configurer la base Oracle :**
   - Créer les tables nécessaires :
     ```sql
     CREATE TABLE users (...);
     CREATE TABLE medications (...);
     CREATE TABLE prescriptions (...);
     CREATE TABLE patients (...);
     CREATE TABLE sales (...);
     ```
   - Créer les séquences nécessaires :
     ```sql
     create sequence med_seq start with 1 increment by 1 nocache nocycle;
     create sequence patient_seq start with 1 increment by 1 nocache nocycle;
     create sequence pres_seq start with 1 increment by 1 nocache nocycle;
     ```
   - Modifier les informations de connexion dans `DataBaseConnection.java`

3. **Compiler et exécuter :**
   ```bash
   mvn clean javafx:run
   ```

> 📌 Assure-toi que JavaFX est bien installé et que ton IDE le reconnaît comme module.

---

## 📸 Captures d'écran

*À ajouter…*

---

## 📄 Génération PDF

L'ordonnance médicale est générée automatiquement et stylisée (avec titre centré, signature, sections, etc.).

```java
PdfGenerator.generatePrescriptionPDF(Prescription p);
```

📍 Le PDF est sauvegardé dans le dossier `prescriptions/`.

---

## 📦 Dépendances Maven principales

```xml
<dependency>
  <groupId>com.itextpdf</groupId>
  <artifactId>kernel</artifactId>
  <version>7.2.3</version>
</dependency>
<dependency>
  <groupId>org.openjfx</groupId>
  <artifactId>javafx-controls</artifactId>
  <version>17</version>
</dependency>
```

---

## 🌐 Multilingue (i18n)

Le support des langues se fait via `ResourceBundle`, et sera activé via un menu de sélection dans l'interface graphique (prochainement) (This feature is coming soon).

---

## 📜 Licence

Ce projet est open-source (MIT License). Tu peux l'utiliser, le modifier, et le partager librement.

---

## 👨‍💻 Auteur

Abdelwaheb Bouchahwa  
Etudiant en informatique 
📬 Contact : [LinkedIn](https://www.linkedin.com/in/abdelwaheb-bouchahwa-8449a5224/) | [Mail](mailto:bouchahwaabdelwaheb@ieee.org)

---

## ✅ TODO

- [x] Génération PDF
- [x] Affichage dynamique des prescriptions
- [ ] Gestion multilingue
- [ ] Authentification utilisateur
- [x] Dashboard analytique (pie chart, histogrammes)
