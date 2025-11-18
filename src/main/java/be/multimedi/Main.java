package be.multimedi;

import be.multimedi.model.EPC;
import be.multimedi.model.Property;
import be.multimedi.model.PropertyType;
import be.multimedi.repository.PropertyRepository;
import be.multimedi.repository.PropertyRepositoryImpl;
import org.h2.tools.Server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        PropertyRepositoryImpl repo = new PropertyRepositoryImpl();

        try {
            Property p = createPropertyFromUserInput();
            repo.save(p);
            System.out.println("Property saved!");

            System.out.println("\nAll properties in the database:");
            showAllProperties(repo);

        } finally {
            repo.close();
        }
    }

    private static Property createPropertyFromUserInput() {
        Property property = new Property();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        property.setAddress(address);

        System.out.print("Enter selling price: ");
        double price = Double.parseDouble(scanner.nextLine());
        property.setPrice(price);

        // Property type
        System.out.println("Enter property type " +
                Arrays.toString(PropertyType.values()) + " :");
        PropertyType type = readPropertyType();
        property.setType(type);

        // EPC
        System.out.println("Enter EPC " +
                Arrays.toString(EPC.values()) + " :");
        EPC epc = readEpc();
        property.setEpc(epc);

        System.out.print("Enter living area (interior m²): ");
        int interiorArea = Integer.parseInt(scanner.nextLine());
        property.setInteriorArea(interiorArea);

        System.out.print("Enter plot area (m²): ");
        int plotArea = Integer.parseInt(scanner.nextLine());
        property.setPlotArea(plotArea);

        System.out.print("Enter number of bedrooms: ");
        int nrBedrooms = Integer.parseInt(scanner.nextLine());
        property.setNrBedrooms(nrBedrooms);

        System.out.print("Enter number of bathrooms: ");
        int nrBathrooms = Integer.parseInt(scanner.nextLine());
        property.setNrBathrooms(nrBathrooms);

        return property;
    }

    private static PropertyType readPropertyType() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return PropertyType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid type. Please enter one of " +
                        Arrays.toString(PropertyType.values()) + " : ");
            }
        }
    }

    private static EPC readEpc() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase()
                    .replace("+", "_PLUS"); // "A+" -> "A_PLUS"
            try {
                return EPC.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid EPC. Please enter one of " +
                        Arrays.toString(EPC.values()) + " : ");
            }
        }
    }

    private static void showAllProperties(PropertyRepository repo) {
        List<Property> properties = repo.findAll();
        properties.forEach(System.out::println);
    }
}