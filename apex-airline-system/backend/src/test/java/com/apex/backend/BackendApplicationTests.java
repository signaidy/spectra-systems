package com.apex.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.ArrayList;
import static org.mockito.Mockito.when;

@SpringBootTest
class BackendApplicationTests {

	@Mock
	private ApexController controller;

	@Test
	public void signIn() {
		User user = new User(
				"pedro@gmail.com",
				"XXX",
				"Pedro",
				"Lopez",
				"Mexico",
				"6642",
				"22",
				"23",
				"user",
				"10");

		LoggedUser loggedUser = new LoggedUser(
				"10",
				"pedro@gmail.com",
				"Pedro",
				"Lopez",
				"Mexico",
				"6642",
				"22",
				"23",
				"user",
				"10/10/2024");

		when(controller.signIn(user)).thenReturn(loggedUser);

		Object response = controller.signIn(user);

		assertEquals(loggedUser, response);
	}

	@Test
	public void createUser() {
		String token = "XXX";
		User user = new User(
				"pedro@gmail.com",
				"XXX",
				"Pedro",
				"Lopez",
				"Mexico",
				"6642",
				"22",
				"23",
				"user",
				"10");

		LoggedUser loggedUser = new LoggedUser(
				"10",
				"pedro@gmail.com",
				"Pedro",
				"Lopez",
				"Mexico",
				"6642",
				"22",
				"23",
				"user",
				"10/10/2024");

		when(controller.createUser(user, token)).thenReturn(loggedUser);

		Object response = controller.createUser(user, token);

		assertEquals(loggedUser, response);
	}

	@Test
	public void getUser() {
		Long id = 1L;
		User user = new User(
				"email",
				"password",
				"first_name",
				"last_name",
				"origin_country",
				"passport_number",
				"age",
				"percentage",
				"role",
				"userId");

		when(controller.getUser(id)).thenReturn(user);

		Object response = controller.getUser(id);

		assertEquals(user, response);
	}

	@Test
	public void getUsers() {
		List<LoggedUser> users = new ArrayList<LoggedUser>();
		users.add(new LoggedUser(
				"user_id",
				"email",
				"first_name",
				"last_name",
				"origin_country",
				"passport_number",
				"role",
				"age",
				"percentage",
				"entry_date"));

		when(controller.getUsers()).thenReturn(users);

		Object response = controller.getUsers();

		assertEquals(users, response);
	}

	@Test
	public void updateUser() {
		User user = new User(
				"pedro@gmail.com",
				"XXX",
				"Pedro",
				"Lopez",
				"Mexico",
				"6642",
				"22",
				"23",
				"user",
				"10");

		when(controller.updateUser(user)).thenReturn("User updated successfully.");

		Object response = controller.updateUser(user);

		assertEquals("User updated successfully.", response);
	}

	@Test
	public void createFlight() {
		Flight flight = new Flight(
				10,
				10,
				"19/20/2024",
				"20/20/2024",
				10,
				10,
				10,
				10,
				10,
				10,
				"Perfect flight!",
				0);

		when(controller.createFlight(flight)).thenReturn("Flight created successfully.");

		Object response = controller.createFlight(flight);

		assertEquals("Flight created successfully.", response);
	}

	@Test
	public void getOneWayFlights() {
		String originCity = "NY";
		String destinationCity = "NJ";
		String departureDate = "20/20/2024";
		String arrivalDate = "20/20/2025";

		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight(
				10,
				10,
				"19/20/2024",
				"20/20/2024",
				10,
				10,
				10,
				10,
				10,
				10,
				"Perfect flight.",
				0));

		when(controller.getOneWayFlights(originCity, destinationCity, departureDate, arrivalDate)).thenReturn(flights);

		Object response = controller.getOneWayFlights(originCity, destinationCity, departureDate, arrivalDate);

		assertEquals(flights, response);
	}

	@Test
	public void createCommentary() {
		Commentary commentary = new Commentary(
				10,
				10,
				"Hello World!",
				10);

		when(controller.createCommentary(commentary)).thenReturn("Commentary created successfully.");

		Object response = controller.createCommentary(commentary);

		assertEquals("Commentary created successfully.", response);
	}

	@Test
	public void createRating() {
		Rating rating = new Rating(
				10,
				10,
				10);

		when(controller.createRating(rating)).thenReturn("Rating created successfully.");

		Object response = controller.createRating(rating);

		assertEquals("Rating created successfully.", response);
	}

	@Test
	public void getAllTickets() {
		List<TicketRecord> tickets = new ArrayList<TicketRecord>();
		tickets.add(new TicketRecord(
				10,
				10,
				10,
				"way",
				"active",
				10,
				"Pedro",
				"pedro@gmail.com"));

		when(controller.getAllTickets()).thenReturn(tickets);

		Object response = controller.getAllTickets();

		assertEquals(tickets, response);
	}

	@Test
	public void getFlights() {
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(new Flight(
				10,
				10,
				"19/20/2024",
				"20/20/2024",
				10,
				10,
				10,
				10,
				10,
				10,
				"Perfect flight.",
				0));

		when(controller.getFlights()).thenReturn(flights);

		Object response = controller.getFlights();

		assertEquals(flights, response);
	}

	@Test
	public void getCities() {
		List<City> cities = new ArrayList<City>();
		cities.add(new City("NY", "New York"));
		cities.add(new City("NJ", "New Jersey"));

		when(controller.getCities()).thenReturn(cities);

		Object response = controller.getCities();

		assertEquals(cities, response);
	}

	@Test
	public void getAllTicketInfo() {
		Ticket ticket = new Ticket(
				10,
				10,
				10,
				1,
				10);

		when(controller.getAllticketinfo()).thenReturn(ticket);

		Object response = controller.getAllticketinfo();

		assertEquals(ticket, response);
	}

	@Test
	public void getUserTickets() {
		int id = 10;
		record UserTicket(String ticket_id, String price, String type, String state, String flight_id,
				String origin, String destination, String departure_date, String arrival_date) {
		}

		List<UserTicket> usertickets = new ArrayList<>();

		usertickets.add(new UserTicket("10", "10", "one way", "active", "10", "NY", "NJ", "20/20/2024", "20/20/2025"));

		when(controller.getUsertickets(id)).thenReturn(usertickets);

		Object response = controller.getUsertickets(id);

		assertEquals(usertickets, response);
	}

	@Test
	public void getHistoricPurchases() {
		int id = 10;
		record User_purchases(String purchase_number, String ticket, String type, String origin, String destination,
				String purchase_date, String price, String paymenth_method,
				String departure_date, String arrival_date, String user_name, int discount, int user_id) {
		}

		List<User_purchases> historicalPurchases = new ArrayList<>();

		historicalPurchases.add(new User_purchases("10", "10", "one way", "NY", "NJ", "20/20/2024", "10", "cash",
				"20/20/2024", "20/20/2025", "Pedro", 0, 10));

		when(controller.getHistoricpurchases(id)).thenReturn(historicalPurchases);

		Object response = controller.getHistoricpurchases(id);

		assertEquals(historicalPurchases, response);
	}

	@Test
	public void getInventory() {
		record FLIGHTS(String origin, String destination, String departure_date, String arrival_date,
				int amount_normal, int amount_premium, int price_normal, int price_premium,
				String detail, boolean type, boolean state, int flight_id) {
		}

		List<FLIGHTS> flight = new ArrayList<>();

		flight.add(
				new FLIGHTS("NY", "NJ", "20/20/2024", "20/20/2025", 10, 10, 10, 10, "Perfect flight.", true, true, 10));

		when(controller.getInvetory()).thenReturn(flight);

		Object response = controller.getInvetory();

		assertEquals(flight, response);
	}

	@Test
	public void getAboutUs() {
		Aboutus aboutus = new Aboutus("We are the best", "gif", "yt", 4, "title_one", "text_one", "img_one",
				"title_two", "text_two", "img_two", "title_three", "text_three", "img_three",
				"title_four", "text_four", "img_four");

		when(controller.getAbout()).thenReturn(aboutus);

		Object response = controller.getAbout();

		assertEquals(aboutus, response);
	}

	@Test
	public void updateAboutUs() {
		Aboutus aboutus = new Aboutus("We are the best", "gif", "yt", 4, "title_one", "text_one", "img_one",
				"title_two", "text_two", "img_two", "title_three", "text_three", "img_three",
				"title_four", "text_four", "img_four");

		when(controller.updateAboutUs(aboutus)).thenReturn("Aboutus information updated");

		Object response = controller.updateAboutUs(aboutus);

		assertEquals("Aboutus information updated", response);
	}

	@Test
	public void purchase() {
		int amount = 2;
		String method = "visa";
		int discount = 0;
		int ticket_id = 79;
		int user_id = 1; 
		int flight_id = 21;
		String type = "premium"; 
		int state = 1; 

		record userTickets(int ticket_id, int user_id) {
		}

		String amountString = String.valueOf(amount);
		Ticket_purchase ticketPurchase = new Ticket_purchase(user_id, flight_id, state, type, amountString); 
	
		List<userTickets> usert = new ArrayList<>();
		usert.add(new userTickets(ticket_id,1)); 
	
		when(controller.purchase(ticketPurchase, amount, method, discount)).thenReturn(usert);
	
		Object response = controller.purchase(ticketPurchase, amount, method, discount);
	
		assertEquals(usert, response);
	}

	@Test
	public void getTicketstobuy() {
		int flight_id = 20;
		String category = "economy"; 

		 record Availabletickets(String origin, String destination, int price, int ticket_id) {
        }

		List<Availabletickets> availabletickets = new ArrayList<>();

		availabletickets.add(new Availabletickets("Guatemala", "Alemania", 400, 12)); 

		when(controller.getTicketstobuy(flight_id, category)).thenReturn(availabletickets);

		Object response = controller.getTicketstobuy(flight_id, category);

		assertEquals(availabletickets, response);
	}

	@Test
	public void getAmounttickets() {
		int flight_id = 20;
		String category = "economy"; 

		record ticketsamount(int tickets_amount) {
		}

		ticketsamount tickets = new ticketsamount(12); 

		when(controller.getAmounttickets(flight_id, category)).thenReturn(tickets);

		Object response = controller.getAmounttickets(flight_id, category);

		assertEquals(tickets, response);
	}


	@Test
	public void getpurchaselogs() {

		record purchases(String purchase_number, String ticket, String type, String origin, String destination,
                    String purchase_date, String price, String paymenth_method,
                    String departure_date, String arrival_date, int discount, String user_name) {
            }

	
		List<purchases> purchaseslogs = new ArrayList<>();
		purchaseslogs.add(new purchases("277", "450", "Premium", "Alemania", "Peru", "2024-03-01",  "700", "MasterCard", "2024-04-05", "2024-04-06", 10, "Ricardo Montaner")); 
	
		when(controller.getpurchaselogs()).thenReturn(purchaseslogs);
	
		Object response = controller.getpurchaselogs();
	
		assertEquals(purchaseslogs, response);
	}

	@Test
	public void getHeader() {

		record Header(String Text_Logo,
                    String Section,
                    String Link_Section,
                    String Link_Profile,
                    String Link_Login,
                    String Logo) {
            }

		Header headerinformation = new Header("Avianca", "About Us", "/aboutus", "/user/dashboard", "/signin", "https://i.ibb.co/X4qF7Lt/Image-Tab-1.png");

		when(controller.getHeader()).thenReturn(headerinformation);

		Object response = controller.getHeader();

		assertEquals(headerinformation, response);
	}

	@Test
	public void updateHeader() {
		Header head = new Header("American Airlines", "Bugdests", "/budegts", "/user/profile", "login", "https://i.ibb.co/X4qF7Lt/Image-Tab-1.png"); 

		when(controller.updateHeader(head)).thenReturn(head);

		Object response = controller.updateHeader(head);

		assertEquals(head, response);
	}

	//Flight and Tickets - Cancelation
	// @Test
	// public void updateFlightandTickets() {
	// 	int flight_id = 32; 

	// 	Header head = new Header("American Airlines", "Bugdests", "/budegts", "/user/profile", "login", "https://i.ibb.co/X4qF7Lt/Image-Tab-1.png"); 

	// 	when(controller.updateHeader(head)).thenReturn(head);

	// 	Object response = controller.updateHeader(head);

	// 	assertEquals(head, response);
	// }


	//Tickets Individual - Cancelation
	// @Test
	// public void updateFlightandTickets() {
	// 	int flight_id = 32; 

	// 	Header head = new Header("American Airlines", "Bugdests", "/budegts", "/user/profile", "login", "https://i.ibb.co/X4qF7Lt/Image-Tab-1.png"); 

	// 	when(controller.updateHeader(head)).thenReturn(head);

	// 	Object response = controller.updateHeader(head);

	// 	assertEquals(head, response);
	// }


	@Test
	public void getDiscount() {
		int id = 27; 

		record Discount(int discount) {
		}

	
		Discount discount = new Discount(45); 
	
		when(controller.getDiscount(id)).thenReturn(discount);
	
		Object response = controller.getDiscount(id);
	
		assertEquals(discount, response);
	}

	// @Test
	// public void updateFlight() {
	// 	int flight_id = 79; 

	// 	Flight flight = new Ticket_purchase(flightId, originCityId, destinationCityId, destinationCityName, 
	// 	departureDate, arrivalDate, touristPrice, businessPrice, detail, touristQuantity, businessQuantity, ); 


	// 	when(controller.updateHeader(head)).thenReturn(head);

	// 	Object response = controller.updateHeader(head);

	// 	assertEquals(head, response);
	// }

	@Test
	public void getFooter() {

		record Footer(String Title_1, String Section_1, String L1,
			String Section_2, String L2,
			String Section_3, String L3,
			String Section_4, String L4,
			String Section_5, String L5,
			String Section_6, String L6,
			String Title_2, String Q_1, String Link_quick_1,
			String Q_2, String Linkl_quick_2,
			String Title_3,
			String C_1,
			String C_2,
			String Copyright) {
	}

		Footer footerinformation = new Footer("About Us", "Avianca", "/aboutus", "Budegts", "/budgets", "Carriage", "/carriage",
		"Rules", "/rules", "Seats", "/seats", "Partners", "/aboutus", "Quick L", "Check fast", "/fastcheck", "Fast reservation", "/fastreserve", 
		"Contacts form", "CES, Guatemala", "@spectrasystem.com", "Avianca reserved");

		when(controller.getFooter()).thenReturn(footerinformation);

		Object response = controller.getFooter();

		assertEquals(footerinformation, response);
	}
	

	@Test
	public void updateFooter() {
		Footer footer = new Footer("About", "American Airlines", "/aboutus", "Budegts", "/budgets", "Carriage", "/carriage",
		"Rules", "/rules", "Seats", "/seats", "Partners", "/aboutus", "Quick L", "Check fast", "/fastcheck", "Fast reservation", "/fastreserve", 
		"Contacts form", "CES, Guatemala", "@americanair.com", "American Airlines"); 

		when(controller.updateFooter(footer)).thenReturn(footer);

		Object response = controller.updateFooter(footer);

		assertEquals(footer, response);
	}


}
