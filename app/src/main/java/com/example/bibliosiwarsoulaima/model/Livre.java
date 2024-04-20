package com.example.bibliosiwarsoulaima.model;

public class Livre {
    private int id;

    public int getNombreCopie() {
        return nombreCopie;
    }

    public void setNombreCopie(int nombreCopie) {
        this.nombreCopie = nombreCopie;
    }

    private int nombreCopie;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNombreDePages() {
        return nombreDePages;
    }

    public String getDescription() {
        return description;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    private String titre,auteur,image,categorie;
    private int nombreDePages;
    private String description;
    private Emplacement emplacement;
    public class Emplacement {
        private String rayon;
        private String armoire;
        private String etagere;

        public String getRayon() {
            return rayon;
        }

        public String getArmoire() {
            return armoire;
        }

        @Override
        public String toString() {
            return "Emplacement { " +
                    "rayon='" + rayon + '\'' +
                    ", armoire='" + armoire + '\'' +
                    ", etagere='" + etagere + '\'' +
                    '}';
        }

        public void setRayon(String rayon) {
            this.rayon = rayon;
        }

        public void setEtagere(String etagere) {
            this.etagere = etagere;
        }

        public void setArmoire(String armoire) {
            this.armoire = armoire;
        }

        public String getEtagere() {
            return etagere;
        }

        public Emplacement(String rayon, String armoire, String etagere) {
            this.rayon = rayon;
            this.armoire = armoire;
            this.etagere = etagere;
        }
    }
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

}

