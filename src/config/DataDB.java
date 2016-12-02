package config;

import Entities.Avion;
import Entities.TypeAvion;
import Repository.TypeAvionRepository;
import Services.AvionService;
import Services.MembreEquipageService;
import Services.TypeAvionService;
import Enum.TypeMembreEquipage;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Nico on 02/12/2016.
 */
public class DataDB {

    private TypeAvionService typeAvionService = new TypeAvionService();
    private AvionService avionService = new AvionService();
    private MembreEquipageService membreEquipageService = new MembreEquipageService();
    
    public static void main(String[] args){
        DataDB ddb = new DataDB();
    }

    public DataDB(){
        this.createTypeAvion();
        this.createAvion();
        this.createMembre();
    }

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

    public void createAvion(){
        for (int i = 0 ; i <= 2 ; i++) {
            for (TypeAvion typeAvion : this.typeAvionService.findAll()) {
                String randomString = String.valueOf(ThreadLocalRandom.current().nextInt(10000, 100000));
                this.avionService.addAvion(typeAvion, randomString);
            }
        }
    }

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

}
