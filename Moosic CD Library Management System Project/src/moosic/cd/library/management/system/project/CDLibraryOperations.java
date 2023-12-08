
package moosic.cd.library.management.system.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class CDLibraryOperations {
    
    private ArrayList<CD> cds;
    
    //constructor
    public CDLibraryOperations() {
        cds = new ArrayList<>();          //ArrayList of CDs
    }
    
    
    //method to read CD data from the text file and populate the cds ArrayList
    public void loadCDDataFromFile() {
        
        cds.clear();
        
        try {
            
            File cdFile = new File("CDLibrary.txt");
            FileReader fr = new FileReader(cdFile);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                
                //extract all eight CD attributes of a line when each ";" is found in the line
                String[] parts = line.split(";");

                if (parts.length == 8) {
                    
                    String id = parts[0];
                    String category = parts[1];
                    String title = parts[2];
                    String artist = parts[3];
                    String genre = parts[4];
                    int year = Integer.parseInt(parts[5]);
                    double price = Double.parseDouble(parts[6]);
                    int quantity = Integer.parseInt(parts[7]);
                    
                    //instantiate cd object with data from the file and add into cds ArrayList
                    CD cd = new CD(id, category, title, artist, genre, year, price, quantity);
                    cds.add(cd);
                    
                }
                
                line = br.readLine();
                    
            } //end while
            
            br.close();
            
        }
        catch (IOException iox) {
            //if text file not found, continue to run program
            //program will create the file when a new CD info is added
        }
        
    } //end of loadCDDataFromFile()
    
    
    //return number of CDs in CD library when method is called
    public int getNumOfCD() {
        return cds.size();
    }
    
    
    //method to calculate total CD copies available in the CD library
    public int calculateTotalCDCopies() {
        if (cds.isEmpty() == true)
            return 0;
        else {
            int totalCopies = 0;
            
            for (CD cd : cds) {
                totalCopies += cd.getQuantity();
            }
            
            return totalCopies;
        }
    }
        
    
    //method to calculate average price per CD
    public double calculateAverageUnitPrice() {
        if (cds.isEmpty() == true)
            return 0.0;
        else {
            double total = 0;
            
            for (CD cd : cds) {
                total += cd.getPrice();
            }
            
            double average = total / cds.size();
            return average;
        }
    }
    
    
    //method to calculate total value of all CDs in the library
    public double calculateTotalCDValue() {
        if (cds.isEmpty() == true)
            return 0.0;
        else {
            double grandTotal = 0;
            
            for (CD cd : cds) {
                double total = cd.getPrice() * cd.getQuantity();
                grandTotal += total;
            }
            
            return grandTotal;
        }
    }
    
    
    //method to display all CD info onto output text area
    public String displayCD() {
        
        String text1, cdInfo;
        StringBuilder sb = new StringBuilder();
        
        text1 = 
                "o+-o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o\n\n" +
                "--- ALL CD INFORMATION ---\n\n"
        ;
        
        sb.append(text1);
        
        int i = 1;
        
        //using enhanced for-each loop to display all CD info until end of ArrayList
        for (CD cd : cds) {
            
            cdInfo =
                    "CD no." + i + "\n" +
                    "--> ID             : " + cd.getId() + "\n" +
                    "--> Category  : " + cd.getCategory() + "\n" +
                    "--> Title         : " + cd.getTitle() + "\n" +
                    "--> Artist       : " + cd.getArtist() + "\n" +
                    "--> Genre      : " + cd.getGenre() + "\n" +
                    "--> Year         : " + cd.getYear() + "\n" +
                    String.format("--> Price        : RM%.2f%n", + cd.getPrice()) +
                    "--> Quantity  : " + cd.getQuantity() + "\n\n"
            ;
            
            sb.append(cdInfo);
            i++;
            
            if (i > cds.size())
                break;
            
        } //end for-each loop
        
        sb.append("o+-o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o");
        
        return sb.toString();
        
    } //end of displayCD()
    
    
    //overridden method of displayCD(), to display a particular CD info after searching for a CD ID
    public String displayCD(String id) {
        
        String cdInfo;
        int i=1;
        
        for (CD cd : cds) {
            if (cd.getId().equalsIgnoreCase(id)) {
                cdInfo =
                        "o+-o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o\n\n" +
                        "--- SEARCH CD ---\n\n" +
                        "You searched for CD ID: " + cd.getId() + "\n\n" +
                        "CD no." + i + "\n" +
                        "--> Category  : " + cd.getCategory() + "\n" +
                        "--> Title         : " + cd.getTitle() + "\n" +
                        "--> Artist       : " + cd.getArtist() + "\n" +
                        "--> Genre      : " + cd.getGenre() + "\n" +
                        "--> Year         : " + cd.getYear() + "\n" +
                        String.format("--> Price        : RM%.2f%n", + cd.getPrice()) +
                        "--> Quantity  : " + cd.getQuantity() + "\n\n" + 
                        "o+-o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o+-o-+o"
                ;
                
                return cdInfo;
            }
            
            i++;
        }
        
        cdInfo = "ID not found";
        return cdInfo;
        
    } //end of displayCD(String)
    
    
    //method to add cd object into cds ArrayList and save into file
    public void addCD(CD cd) {
        
        try {
            
            cds.add(cd);
            
            File cdFile = new File("CDLibrary.txt");
            FileWriter fw = new FileWriter(cdFile, true);
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println(cd.getId() + ";" + cd.getCategory() + ";" + cd.getTitle() + ";" + cd.getArtist() + ";"
                        + cd.getGenre() + ";" + cd.getYear() + ";" + cd.getPrice() + ";" + cd.getQuantity());
            
            pw.close();
            
        }
        catch (IOException iox) {
            JOptionPane.showMessageDialog(null, "Error adding CD data to file: " + iox.getMessage(),
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
            
    } //end of addCD(CD)
    
    
    //method to update CD info in ArrayList and save into file
    public void updateCD(CD updatedCD) {
        
        try {
            
            String searchID = updatedCD.getId();
            
            //locate the respective CD info in the ArrayList
            for (int i=0; i<cds.size(); i++) {
                
                //if found, update the existing CD object in ArrayList and save into file
                //.equalsIgnoreCase() as user input of ID could be in no caps
                if (cds.get(i).getId().equalsIgnoreCase(searchID)) {
                    
                    cds.get(i).setCategory(updatedCD.getCategory());
                    cds.get(i).setTitle(updatedCD.getTitle());
                    cds.get(i).setArtist(updatedCD.getArtist());
                    cds.get(i).setGenre(updatedCD.getGenre());
                    cds.get(i).setYear(updatedCD.getYear());
                    cds.get(i).setPrice(updatedCD.getPrice());
                    cds.get(i).setQuantity(updatedCD.getQuantity());
                    
                    File cdFile = new File("CDLibrary.txt");
                    FileWriter fw = new FileWriter(cdFile);
                    PrintWriter pw = new PrintWriter(fw);
                    
                    for (CD cd : cds) {
                        pw.println(cd.getId() + ";" + cd.getCategory() + ";" + cd.getTitle() + ";" + cd.getArtist() + ";"
                            + cd.getGenre() + ";" + cd.getYear() + ";" + cd.getPrice() + ";" + cd.getQuantity());
                    }
                    
                    pw.close();
                    break;      //exit loop once CD is updated
                    
                }
                
            } //end for loop
            
        }
        catch (IOException iox) {
            JOptionPane.showMessageDialog(null, "Error updating CD data to file: " + iox.getMessage(),
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
    } //end of updateCD(CD)
    
    
    //method to delete a cd object in cds ArrayList and remove from file
    public void deleteCD(String deleteID) {
        
        try {
            
            //traverse the ArrayList to find the matching CD to remove
            for (int i=0; i<cds.size(); i++) {
                
                if (cds.get(i).getId().equalsIgnoreCase(deleteID)) {

                    cds.remove(i);
                    
                    File cdFile = new File("CDLibrary.txt");
                    FileWriter fw = new FileWriter(cdFile);
                    PrintWriter pw = new PrintWriter(fw);
                    
                    for (CD cd : cds) {
                        pw.println(cd.getId() + ";" + cd.getCategory() + ";" + cd.getTitle() + ";" + cd.getArtist() + ";"
                            + cd.getGenre() + ";" + cd.getYear() + ";" + cd.getPrice() + ";" + cd.getQuantity());
                    }
                    
                    pw.close();
                    break;      //exit loop once CD is removed
                }
                
            } //end for loop
            
        }
        catch (IOException iox) {
            JOptionPane.showMessageDialog(null, "Error deleting CD data from file: " + iox.getMessage(),
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
    } //end of deleteCD(String)
    
    
    //method to return a randomly generated CD ID
    String generateRandomID() {
        
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; //ID will randomly generate from these characters
        StringBuilder sb = new StringBuilder();                     //sb object from StringBuilder class to build the random ID string character by character
        Random random = new Random();                               //declare and initialise random object
        
        final int indexNum = 5;                                     //fix the number of random characters in ID to 5
        
        for (int i=0; i<indexNum; i++) {
            int index = random.nextInt(characters.length());   //generate index value by random
            sb.append(characters.charAt(index));                  //from the random generated index, append the character at the index value to the end of sb
        }
        
        return sb.toString();                                       //convert sb to String and return the completed String ID
        
    } //end of generateRandomID()
    
    
    //method that returns boolean if cds ArrayList is empty
    public boolean isEmpty() {
        if (cds.isEmpty() == true)
            return true;
        else
            return false;
    }
    
    
} //end CDLibraryOperations.java class