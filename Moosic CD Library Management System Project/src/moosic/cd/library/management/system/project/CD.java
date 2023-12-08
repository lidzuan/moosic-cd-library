
package moosic.cd.library.management.system.project;

public class CD {
    
    private final String id;
    private String category;
    private String title;
    private String artist;
    private String genre;
    private int year;
    private double price;
    private int quantity;
    
    
    //constructor to instantiate cd object and initialise its attributes
    public CD(String id, String category, String title, String artist, String genre, int year, double price, int quantity) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
    }
    
    
    //mutators
    public void setCategory(String c) {
        category = c;
    }

    public void setTitle(String t) {
        title = t;
    }
    
    public void setArtist(String a) {
        artist = a;
    }
    
    public void setGenre(String g) {
        genre = g;
    }

    public void setYear(int y) {
        year = y;
    }
    
    public void setPrice(double p) {
        price = p;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    
    //accessors
    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
    
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    
    
} //end CD.java class