package se.yrgo.isbnvalidator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class StockSystemTest {
    @Test
    public void testGetValidIndicator() {
        ExternalISBNDataService testService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Understanding the Digital World", "Brian Kernighan");
            }
        };
        
        ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
            @Override
            public Book lookup(String isbn) {
                return null;
              }
        };

        StockSystem stockSystem = new StockSystem();
        stockSystem.setWebService(testService);
        stockSystem.setdatabaseService(testDatabaseService);
        
        String isbn = "069117654X";
        String indicator = stockSystem.getIndicator(isbn);
        assertEquals("654XB4", indicator); // expected = "654XB4"
    }
    
    @Test
    public void databaseIsUsed() {
        ExternalISBNDataService databaseService = 
                           mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = 
                           mock(ExternalISBNDataService.class);

        when(databaseService.lookup("069117654X")).thenReturn(new 
                          Book("069117654X", "Digital","Brian"));

        StockSystem stockSystem = new StockSystem();
        stockSystem.setWebService(webService);
        stockSystem.setdatabaseService(databaseService);

        String isbn = "069117654X";
        String indicator = stockSystem.getIndicator(isbn);
        verify(databaseService, times(1)).lookup("069117654X");
        verify(webService,times(0)).lookup(anyString());
    }
    
    @Test
    public void webServiceUsedIfNoDataInDatabase() {
        ExternalISBNDataService databaseService = 
                       mock(ExternalISBNDataService.class);
        ExternalISBNDataService webService = 
                        mock(ExternalISBNDataService.class);

        when(databaseService.lookup("069117654X")).thenReturn(null);
        when(webService.lookup("069117654X")).thenReturn(new Book("069117654X",  
                       "Digital","Brian"));

        StockSystem stockSystem = new StockSystem();
        stockSystem.setWebService(webService);
        stockSystem.setdatabaseService(databaseService);

        String isbn = "069117654X";
        String indicator = stockSystem.getIndicator(isbn);
        verify(databaseService, times(1)).lookup("069117654X");
        verify(webService,times(1)).lookup("069117654X");
    }
}
