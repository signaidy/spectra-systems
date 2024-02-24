interface User {
  userId: string;
  email: string;
  firstName: string;
  iat: number;
  lastName: string;
  originCountry: string;
  passportNumber: string;
  role: "USER" | "ADMIN" | "EMPLOYEE";
  age: string;
  percentage: string;
  entryDate: string;
}

interface City {
  cityId: string;
  name: string;
}