package sg.edu.ntu.ftbsolutionscrm.config;

// import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.edu.ntu.ftbsolutionscrm.entity.Favourite;
import sg.edu.ntu.ftbsolutionscrm.entity.HDBUser;
import sg.edu.ntu.ftbsolutionscrm.entity.ResaleHdb;
import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;
import sg.edu.ntu.ftbsolutionscrm.entity.SalesHDBInteraction;

import sg.edu.ntu.ftbsolutionscrm.repository.FavouriteRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.HdbUserRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.ResaleHDBRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.SalespersonRepository;
import sg.edu.ntu.ftbsolutionscrm.repository.SalesHDBInteractionRepository;

import sg.edu.ntu.ftbsolutionscrm.exception.*;

@Component

public class DataLoader 
{
    @Autowired
    private ResaleHDBRepository resaleHdbRepository;

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private SalespersonRepository salespersonRepository;

    @Autowired
    private SalesHDBInteractionRepository salesHDBInteractionRepository;

    @Autowired
    private HdbUserRepository hdbUserRepository;

    @PostConstruct
    public void loadData() {
        // Clear existing data for demonstration purposes
        resaleHdbRepository.deleteAll();
        favouriteRepository.deleteAll();
        hdbUserRepository.deleteAll();
        resaleHdbRepository.deleteAll();
        salespersonRepository.deleteAll();
        salesHDBInteractionRepository.deleteAll();

        loadResaleHdb();
        loadHDBUser();
        loadFavourite();
        loadSalesPerson();

    }

    public void loadSalesPerson() {
        salespersonRepository.save(Salesperson.builder().firstName("Tony").lastName("Stark").email("tony@gmail.com")
                .contactNo("12345678").awards("Inspiring Innovator 2023").yearsInService(5).build());

        salespersonRepository
                .save(Salesperson.builder().firstName("Ahmad").lastName("Jeffri").email("Ajeffri90@gmail.com")
                        .contactNo("97867523").awards("").yearsInService(2).build());
    }

    public void loadHDBUser() {
        hdbUserRepository.save(HDBUser.builder().firstName("Ramdan").lastName("Maskov").email("MaskozRamdan@gmail.com")
                .isMarriedBoolean(true).contactNo("98765432").yearofbirth(2017).closetoschool(true).closetomall(false)
                .closetotransportation(true).closetoroadways(false).build());

        hdbUserRepository.save(HDBUser.builder().firstName("Dimitri").lastName("Ma").email("dmaluuu@gmail.com")
                .isMarriedBoolean(false).contactNo("19387467").yearofbirth(2017).closetoschool(true).closetomall(false)
                .closetotransportation(false).closetoroadways(false).build());

    }

    public void loadSalesHDBInteraction() 
    {

        Salesperson salesperson = salespersonRepository.findById(1L)
                .orElseThrow(() -> new SalesPersonNotFoundException(1L));
        HDBUser hdbUserId = hdbUserRepository.findById(1L)
                .orElseThrow(() -> new HDBUserNotFoundException(1L));

        salesHDBInteractionRepository.save(SalesHDBInteraction.builder()
                .salesperson(salesperson)
                .hdbUser(hdbUserId)
                .review("Very professional")
                .build());
    }

    // public void loadFavourite() {

    public void loadFavourite() 
    {
        // Fetch HDB Users and Resale HDB properties
        List<HDBUser> users = hdbUserRepository.findAll();
        List<ResaleHdb> properties = resaleHdbRepository.findAll();

        if (!users.isEmpty() && !properties.isEmpty()) 
        {
            Favourite favourite1 = new Favourite();
            favourite1.setUser(users.get(0)); // First user
            favourite1.setFlat(properties.get(0));// First property
            favourite1.setCreatedAt(LocalDateTime.now());

            Favourite favourite2 = new Favourite();
            favourite2.setUser(users.get(1)); // Second user
            favourite2.setFlat(properties.get(1)); // Second property
            favourite2.setCreatedAt(LocalDateTime.now());

            favouriteRepository.saveAll(List.of(favourite1, favourite2));
        }
    }

    // public void loadHDBUser() {
    // List<HDBUser> users = List.of(
    // new HDBUser("John", "Tan","john-tan@email.com", true),
    // new HDBUser("Tom","Tanny", "Tom-tanny@email.com",true)

    // );

    // hdbUserRepository.saveAll(users);

    // }

    public void loadResaleHdb() 
    {
        List<ResaleHdb> properties = List.of(
                new ResaleHdb("2024-01", "Yishun", "3 ROOM", "123", "Yishun Ave 1", "01-10", 70.0,
                        "Model A", 1995,
                        "Remaining 60 years", 300000),
                new ResaleHdb("2024-02", "Woodlands", "4 ROOM", "456", "Woodlands Ave 3", "05-12", 90.0,
                        "Model B",
                        2000,
                        "Remaining 65 years", 350000),
                new ResaleHdb("2024-03", "Bukit Panjang", "5 ROOM", "789", "Bukit Panjang Rd", "10-20",
                        110.0,
                        "Model C",
                        1998, "Remaining 55 years", 400000),
                new ResaleHdb("2024-04", "Ang Mo Kio", "3 ROOM", "101", "Ang Mo Kio Ave 8", "01-15",
                        75.0, "Model A",
                        1990,
                        "Remaining 63 years", 320000),
                new ResaleHdb("2024-05", "Bedok", "4 ROOM", "202", "Bedok Reservoir Rd", "02-06", 85.0,
                        "Model B", 2005,
                        "Remaining 67 years", 380000),
                new ResaleHdb("2024-06", "Clementi", "5 ROOM", "303", "Clementi Ave 5", "03-08", 100.0,
                        "Model D", 2003,
                        "Remaining 60 years", 420000),
                new ResaleHdb("2024-07", "Pasir Ris", "3 ROOM", "404", "Pasir Ris Dr 4", "01-12", 70.0,
                        "Model A", 1997,
                        "Remaining 58 years", 310000),
                new ResaleHdb("2024-08", "Tampines", "4 ROOM", "505", "Tampines St 81", "04-10", 90.0,
                        "Model C", 2004,
                        "Remaining 62 years", 360000),
                new ResaleHdb("2024-09", "Queenstown", "5 ROOM", "606", "Queenstown Rd", "05-11", 115.0,
                        "Model D",
                        1995,
                        "Remaining 59 years", 450000),
                new ResaleHdb("2024-10", "Jurong East", "3 ROOM", "707", "Jurong East St 21", "01-09",
                        80.0, "Model A",
                        1998, "Remaining 60 years", 330000),
                new ResaleHdb("2024-11", "Bishan", "4 ROOM", "808", "Bishan St 24", "03-12", 85.0,
                        "Model B", 2000,
                        "Remaining 63 years", 370000),
                new ResaleHdb("2024-12", "Sengkang", "5 ROOM", "909", "Sengkang Sq 10", "04-10", 100.0,
                        "Model C", 2007,
                        "Remaining 65 years", 460000),
                new ResaleHdb("2024-01", "Hougang", "3 ROOM", "1010", "Hougang Ave 7", "02-09", 70.0,
                        "Model A", 1999,
                        "Remaining 61 years", 315000),
                new ResaleHdb("2024-02", "Punggol", "4 ROOM", "1111", "Punggol Walk", "06-13", 90.0,
                        "Model B", 2005,
                        "Remaining 63 years", 375000),
                new ResaleHdb("2024-03", "Toa Payoh", "5 ROOM", "1212", "Toa Payoh Lorong 8", "07-14",
                        105.0, "Model D",
                        2000, "Remaining 60 years", 440000),
                new ResaleHdb("2024-04", "Bukit Batok", "3 ROOM", "1313", "Bukit Batok East Ave 3",
                        "01-05", 75.0,
                        "Model A", 1996, "Remaining 59 years", 325000),
                new ResaleHdb("2024-05", "Serangoon", "4 ROOM", "1414", "Serangoon Ave 3", "03-10",
                        85.0, "Model B",
                        2001,
                        "Remaining 64 years", 355000),
                new ResaleHdb("2024-06", "Tiong Bahru", "5 ROOM", "1515", "Tiong Bahru Rd", "04-08",
                        110.0, "Model C",
                        1997,
                        "Remaining 60 years", 430000),
                new ResaleHdb("2024-07", "Choa Chu Kang", "3 ROOM", "1616", "Choa Chu Kang North 6",
                        "02-06", 70.0,
                        "Model A", 1998, "Remaining 62 years", 310000),
                new ResaleHdb("2024-08", "Marine Parade", "4 ROOM", "1717", "Marine Parade Rd", "05-12",
                        90.0,
                        "Model B",
                        2002, "Remaining 65 years", 380000),
                new ResaleHdb("2024-09", "Novena", "5 ROOM", "1818", "Novena Ave", "06-15", 100.0,
                        "Model D", 2004,
                        "Remaining 66 years", 470000),
                new ResaleHdb("2024-10", "Eunos", "3 ROOM", "1919", "Eunos Rd 5", "01-07", 75.0,
                        "Model A", 1994,
                        "Remaining 57 years", 295000),
                new ResaleHdb("2024-11", "Yishun", "4 ROOM", "2020", "Yishun Ave 3", "03-07", 80.0,
                        "Model B", 2001,
                        "Remaining 64 years", 340000),
                new ResaleHdb("2024-12", "Woodlands", "3 ROOM", "2121", "Woodlands Ave 5", "02-08",
                        75.0, "Model A",
                        1999,
                        "Remaining 60 years", 310000),
                new ResaleHdb("2024-01", "Bukit Panjang", "5 ROOM", "2222", "Bukit Panjang Rd", "04-09",
                        105.0,
                        "Model D",
                        2006, "Remaining 65 years", 460000),
                new ResaleHdb("2024-02", "Ang Mo Kio", "3 ROOM", "2323", "Ang Mo Kio Ave 10", "05-10",
                        70.0, "Model A",
                        1995, "Remaining 62 years", 325000),
                new ResaleHdb("2024-03", "Bedok", "4 ROOM", "2424", "Bedok Reservoir Rd", "02-05", 85.0,
                        "Model B",
                        2004,
                        "Remaining 63 years", 370000),
                new ResaleHdb("2024-04", "Clementi", "5 ROOM", "2525", "Clementi Ave 4", "06-11", 95.0,
                        "Model C", 2000,
                        "Remaining 60 years", 430000),
                new ResaleHdb("2024-05", "Pasir Ris", "3 ROOM", "2626", "Pasir Ris Dr 6", "01-06", 70.0,
                        "Model A",
                        1998,
                        "Remaining 59 years", 310000),
                new ResaleHdb("2024-06", "Tampines", "4 ROOM", "2727", "Tampines St 22", "03-09", 90.0,
                        "Model B", 2002,
                        "Remaining 62 years", 380000),
                new ResaleHdb("2024-07", "Queenstown", "5 ROOM", "2828", "Queenstown Rd", "07-12",
                        115.0, "Model D",
                        2003,
                        "Remaining 64 years", 450000),
                new ResaleHdb("2024-08", "Jurong East", "3 ROOM", "2929", "Jurong East St 24", "02-10",
                        75.0, "Model A",
                        1995, "Remaining 58 years", 320000),
                new ResaleHdb("2024-09", "Bishan", "4 ROOM", "3030", "Bishan St 22", "01-08", 85.0,
                        "Model B", 1999,
                        "Remaining 60 years", 360000),
                new ResaleHdb("2024-10", "Sengkang", "5 ROOM", "3131", "Sengkang Sq 12", "04-10", 105.0,
                        "Model C",
                        2008,
                        "Remaining 66 years", 470000),
                new ResaleHdb("2024-11", "Hougang", "3 ROOM", "3232", "Hougang Ave 8", "02-06", 75.0,
                        "Model A", 1997,
                        "Remaining 59 years", 310000),
                new ResaleHdb("2024-12", "Punggol", "4 ROOM", "3333", "Punggol Walk", "06-12", 90.0,
                        "Model B", 2003,
                        "Remaining 61 years", 380000),
                new ResaleHdb("2024-01", "Toa Payoh", "5 ROOM", "3434", "Toa Payoh Lorong 6", "03-07",
                        110.0, "Model D",
                        2000, "Remaining 64 years", 440000),
                new ResaleHdb("2024-02", "Bukit Batok", "3 ROOM", "3535", "Bukit Batok East Ave 8",
                        "01-08", 75.0,
                        "Model A",
                        1994, "Remaining 55 years", 290000));

               
                for (ResaleHdb resaleHdb : properties)
                {
                resaleHdbRepository.save(resaleHdb);
                }

     }
}

       
