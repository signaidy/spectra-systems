declare global {
  interface Commentary {
    _id: string;
    userId: number;
    userName: string;
    date: Date;
    description: string;
    children: Commentary[];
  }

  export interface Hotel {
    _id: string;
    name: string;
    description: string;
    location: {
      street: string;
      city: string;
      state: string;
      country: string;
    };
    reviews: {  
      count: number;
      average: number;
    };
    rooms: {
      doubleSuite: string;
      juniorSuite: string;
      standardSuite: string;
      bigSuite: string;
    };
    amenities: string[];
    commentaries: Commentary[];
  }
}

export {};
