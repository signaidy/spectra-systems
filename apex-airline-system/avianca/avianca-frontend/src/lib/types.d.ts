interface User {
  userId: string;
  email: string;
  firstName: string;
  iat: number;
  lastName: string;
  originCountry: string;
  passportNumber: string;
  role: "user" | "admin" | "enterprise";
  age: string;
  percentage: string;
  entryDate: string;
}

interface City {
  cityId: string;
  name: string;
}

interface Flight {
  flightId: number;
  originCityId: number;
  originCityName: string;
  destinationCityId: number;
  destinationCityName: string;
  departureDate: string;
  arrivalDate: string;
  touristPrice: number;
  businessPrice: number;
  detail: string;
  touristQuantity: number;
  businessQuantity: number;
}

interface CompleteFlight {
  flightId: number;
  originCityId: number;
  originCityName: string;
  destinationCityId: number;
  destinationCityName: string;
  departureDate: string;
  arrivalDate: string;
  touristPrice: number;
  businessPrice: number;
  detail: string;
  touristQuantity: number;
  businessQuantity: number;
  touristCapacity: number;
  businessCapacity: number;
  state: number;
}

interface Ticket {
  ticketId: int;
  price: int;
  flightId: int;
  type: string;
  state: string;
  userId: int;
  userName: string;
}
