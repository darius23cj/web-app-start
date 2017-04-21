package my.apps.web;


public class Product {
    private int id;
    private String nume;
    private String persoana;
    private int cantitate;

    public Product(String nume, int cantitate, String persoana) {
        this.nume = nume;
        this.cantitate = cantitate;
        this.persoana = persoana;
    }

    public Product(int id, String nume, String persoana, int cantitate) {
        this.id = id;
        this.nume = nume;
        this.persoana = persoana;
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPersoana() {
        return persoana;
    }

    public int getCantitate() {

        return cantitate;
    }

    public String toJson() {
        String nume = getNume();
        String persoana = getPersoana();
        int cantitate = getCantitate();

        return "{\"id\": " + getId() + ", \"nume\": \"" + nume + "\", \"cantitate\": " + cantitate + ", \"persoana\": \"" + persoana + "\"}";
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", persoana='" + persoana + '\'' +
                ", cantitate=" + cantitate +
                '}';
    }
}


