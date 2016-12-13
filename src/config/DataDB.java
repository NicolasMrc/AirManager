package config;

import Entities.*;
import Repository.TypeAvionRepository;
import Services.*;
import Enum.TypeMembreEquipage;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import Enum.TypeUtilisateur;

/**
 * classe permettant de peupler la base de donnée
 * Created by Nico on 02/12/2016.
 */
public class DataDB {

    /**
     * service de type avion
     */
    private TypeAvionService typeAvionService = new TypeAvionService();

    /**
     * Service de la classe Avion
     */
    private AvionService avionService = new AvionService();

    /**
     * Service de la classe membre equipage
     */
    private MembreEquipageService membreEquipageService = new MembreEquipageService();

    /**
     * Service de la classe aeroport
     */
    private AeroportService aeroportService = new AeroportService();

    /**
     * Service de la classe utilisateur
     */
    private UtilisateurService utilisateurService = new UtilisateurService();

    /**
     * main permettant de lancer la population de la base de donnée
     * @param args
     */
    public static void main(String[] args){
        DataDB ddb = new DataDB();
    }

    /**
     * constructeur data
     */
    public DataDB(){
        //this.createTypeAvion();
        //this.createAvion();
        //this.createMembre();
        //this.createAeroport();
        createUtilisateurMembre();
    }

    /**
     * permet de creer en base des type avion
     */
    public void createTypeAvion() {

        TypeAvionRepository typeAvionRepository = new TypeAvionRepository();

        ArrayList<TypeAvion> typeAvions = new ArrayList<>();

        try {
            this.typeAvionService.addTypeAvion("A300", 5, 8);
            this.typeAvionService.addTypeAvion("A310", 3, 10);
            this.typeAvionService.addTypeAvion("A320", 6, 12);
            this.typeAvionService.addTypeAvion("A330", 1, 3);
            this.typeAvionService.addTypeAvion("A340", 23, 30);
            this.typeAvionService.addTypeAvion("A360", 4, 5);
            this.typeAvionService.addTypeAvion("A380", 2, 10);

            this.typeAvionService.addTypeAvion("B707", 2, 10);
            this.typeAvionService.addTypeAvion("B717", 2, 10);
            this.typeAvionService.addTypeAvion("B720", 2, 10);
            this.typeAvionService.addTypeAvion("B727", 2, 10);
            this.typeAvionService.addTypeAvion("B737", 2, 10);
            this.typeAvionService.addTypeAvion("B747", 2, 10);
            this.typeAvionService.addTypeAvion("B757", 2, 10);
            this.typeAvionService.addTypeAvion("B767", 2, 10);
            this.typeAvionService.addTypeAvion("B777", 2, 10);
            this.typeAvionService.addTypeAvion("B787", 2, 10);
        } catch (Exception e){

        }
    }

    /**
     * permet de creer des avion
     */
    public void createAvion(){
        for (int i = 0 ; i <= 2 ; i++) {
            for (TypeAvion typeAvion : this.typeAvionService.findAll()) {
                String randomString = String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000));
                this.avionService.addAvion(typeAvion, randomString);
            }
        }
    }

    /**
     * permet de creer le membre
     */
    public void createMembre(){
        this.membreEquipageService.addMembreEquipage("Blériot","Louis", TypeMembreEquipage.PILOTE);
        this.membreEquipageService.addMembreEquipage("Farman", "Henri", TypeMembreEquipage.PILOTE);
        this.membreEquipageService.addMembreEquipage("Garros","Roland", TypeMembreEquipage.PILOTE);
        this.membreEquipageService.addMembreEquipage("Boucher","Hélène", TypeMembreEquipage.PILOTE);
        this.membreEquipageService.addMembreEquipage("Lindbergh","Charles", TypeMembreEquipage.PILOTE);
        this.membreEquipageService.addMembreEquipage("Auriol","Jacqueline", TypeMembreEquipage.PILOTE);

        this.membreEquipageService.addMembreEquipage("Pégoud","Adolphe", TypeMembreEquipage.COPILOTE);
        this.membreEquipageService.addMembreEquipage("de Saint-Exupéry","Antoine", TypeMembreEquipage.COPILOTE);
        this.membreEquipageService.addMembreEquipage("Le Bris","Jean-Marie", TypeMembreEquipage.COPILOTE);
        this.membreEquipageService.addMembreEquipage("Earhart","Amelia", TypeMembreEquipage.COPILOTE);
        this.membreEquipageService.addMembreEquipage("Coleman","Bessie", TypeMembreEquipage.COPILOTE);

        this.membreEquipageService.addMembreEquipage("Smalley","Terrilyn", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Brickhouse","Burt", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Judy","Thelma", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Golder","Greg", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Desmarais","Lyndsay", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Acey","Babette", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Everest","Andres", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Levasseur","Mitchel", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Lei","Anibal", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Cruze","Cathey", TypeMembreEquipage.PNC);
        this.membreEquipageService.addMembreEquipage("Grunewald","Hilario", TypeMembreEquipage.PNC);
    }


    /**
     * permet de creer des utilisateur pour les membres
     */
    public void createUtilisateurMembre(){
        ArrayList<MembreEquipage> membreEquipages = this.membreEquipageService.findAll();

        for (MembreEquipage membreEquipage : membreEquipages){
            this.utilisateurService.save(membreEquipage.getNom(), "password", TypeUtilisateur.MEMBRE_EQUIPAGE, membreEquipage.getId());
        }
    }

    /**
     * permet de creer des aeroport en base
     *
     */
    public void createAeroport(){
        this.aeroportService.save(new Aeroport("Atlanta","ATL"));
        this.aeroportService.save(new Aeroport("Pékin", "PEK"));
        this.aeroportService.save(new Aeroport("Dubaï", "DXB"));
        this.aeroportService.save(new Aeroport("Chicago", "ORD"));
        this.aeroportService.save(new Aeroport("Tokyo", "HND"));
        this.aeroportService.save(new Aeroport("Londres", "LHR"));
        this.aeroportService.save(new Aeroport("Los Angeles", "LAX"));
        this.aeroportService.save(new Aeroport("Hong Kong", "HKG"));
        this.aeroportService.save(new Aeroport("Paris-Charles-de-Gaulle", "CDG"));
        this.aeroportService.save(new Aeroport("Dallas-Fort Worth", "DFW"));
        this.aeroportService.save(new Aeroport("Istanbul", "IST"));
        this.aeroportService.save(new Aeroport("Francfort", "FRA"));
        this.aeroportService.save(new Aeroport("Shanghai-Pudong", "PVG"));
        this.aeroportService.save(new Aeroport("Amsterdam", "AMS"));
        this.aeroportService.save(new Aeroport("Soekarno-Hatta", "CGK"));
        this.aeroportService.save(new Aeroport("New York-John F. Kennedy", "JFK"));
        this.aeroportService.save(new Aeroport("Singapour", "SIN"));
        this.aeroportService.save(new Aeroport("Canton Baiyun", "CAN"));
        this.aeroportService.save(new Aeroport("Denver", "DEN"));
        this.aeroportService.save(new Aeroport("Bangkok", "BKK"));
        this.aeroportService.save(new Aeroport("San Francisco", "SFO"));
        this.aeroportService.save(new Aeroport("Incheon", "ICN"));
        this.aeroportService.save(new Aeroport("Kuala Lumpur", "KUL"));
        this.aeroportService.save(new Aeroport("Madrid-Barajas", "MAD"));
        this.aeroportService.save(new Aeroport("Indira-Gandhi", "DEL"));
        this.aeroportService.save(new Aeroport("Las Vegas", "LAS"));
        this.aeroportService.save(new Aeroport("Charlotte-Douglas", "CLT"));
        this.aeroportService.save(new Aeroport("Miami", "MIA"));
        this.aeroportService.save(new Aeroport("Phoenix", "PHX"));
        this.aeroportService.save(new Aeroport("Chengdu-Shuangliu", "CTU"));
        this.aeroportService.save(new Aeroport("Chengdu-Shuangliu", "CTU"));
    }

}
