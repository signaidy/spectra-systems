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
}

interface City {
  cityId: string;
  name: string;
}