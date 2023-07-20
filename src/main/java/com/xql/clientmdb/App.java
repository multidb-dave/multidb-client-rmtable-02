package com.xql.clientmdb;

public class App {
    public static void main(String[] args) {
        String baseUrl = "http://localhost:8080";
        // String baseUrl = "http://api001.multidb.net:8080";

        // List of virtual schemas
        String url = baseUrl + "/api/v1/schemas";
        ListSchemas listSchemas = new ListSchemas();
        listSchemas.setUrl(url);
        listSchemas.getSchemas();
        System.out.println();

        // List of database sources
        url = baseUrl + "/api/v1/dbconns";
        ListDbconns listDbconns = new ListDbconns();
        listDbconns.setUrl(url);
        listDbconns.getDbconns();
        System.out.println();

        RemoveDvtable removeDvtable = new RemoveDvtable();
        removeDvtable.setBaseUrl(baseUrl);

        // correct input
        // 1. remove a virtual table: suppliers
        System.out.println("1. remove a virtual table: northwind_c.suppliers");
        url = baseUrl + "/api/v1/rmtable/";
        removeDvtable.setUrl(url);
        removeDvtable.setDvsname("northwind_c");
        removeDvtable.setDvt_name("suppliers");

        removeDvtable.removeDvtable();
        System.out.println();

        // 2. remove a virtual table: suppliers; does not exist.
        System.out.println("2. remove a virtual table: northwind_c.suppliers; does not exist.");
        url = baseUrl + "/api/v1/rmtable/";
        removeDvtable.setUrl(url);
        removeDvtable.setDvsname("northwind_c");
        removeDvtable.setDvt_name("suppliers");

        removeDvtable.removeDvtable();
        System.out.println();

        // 3. remove a virtual table: shippers
        System.out.println("3. remove a virtual table: northwind_b.shippers");
        url = baseUrl + "/api/v1/rmtable/";
        removeDvtable.setUrl(url);
        removeDvtable.setDvsname("northwind_b");
        removeDvtable.setDvt_name("shippers");

        removeDvtable.removeDvtable();
        System.out.println();

        // 4. remove a virtual table: shippers; does not exist.
        System.out.println("4. remove a virtual table: northwind_b.shippers; does not exist.");
        url = baseUrl + "/api/v1/rmtable/";
        removeDvtable.setUrl(url);
        removeDvtable.setDvsname("northwind_b");
        removeDvtable.setDvt_name("shippers");

        removeDvtable.removeDvtable();
        System.out.println();

        // 5. remove a virtual table: customers
        System.out.println("5. remove a virtual table: northwind_a.customers");
        url = baseUrl + "/api/v1/rmtable/";
        removeDvtable.setUrl(url);
        removeDvtable.setDvsname("northwind_a");
        removeDvtable.setDvt_name("customers");

        removeDvtable.removeDvtable();
        System.out.println();

        // 6. remove a virtual table: customers; does not exist.
        System.out.println("6. remove a virtual table: northwind_a.customers; does not exist.");
        url = baseUrl + "/api/v1/rmtable/";
        removeDvtable.setUrl(url);
        removeDvtable.setDvsname("northwind_a");
        removeDvtable.setDvt_name("customers");

        removeDvtable.removeDvtable();
        System.out.println();

        // 7. get a list of virtual tables in virtual schema northwind_a
        System.out.println("7. get a list of virtual tables in virtual schema northwind_a");
        url = baseUrl + "/api/v1/schemas/northwind_a";
        listSchemas.setUrl(url);
        listSchemas.getSchemas();
        System.out.println();

        // 8. get a list of virtual tables in virtual schema northwind_b
        System.out.println("8. get a list of virtual tables in virtual schema northwind_b");
        url = baseUrl + "/api/v1/schemas/northwind_b";
        listSchemas.setUrl(url);
        listSchemas.getSchemas();
        System.out.println();

        // 9. get a list of virtual tables in virtual schema northwind_c
        System.out.println("9. get a list of virtual tables in virtual schema northwind_c");
        url = baseUrl + "/api/v1/schemas/northwind_c";
        // url =
        // "http://api001.multidb.net:8080/api/v1/schemas/dvs_authors_db/DVT_AUTHORS";
        // url = "http://localhost:8080/api/v1/schemas/dvs_authors_db/DVT_AUTHORS";
        // ListSchemas listSchemas = new ListSchemas();
        listSchemas.setUrl(url);
        listSchemas.getSchemas();
        System.out.println();
    }
}
