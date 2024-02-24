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
