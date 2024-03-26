import {
  Controller,
  Get,
  // Req,
  Post,
  // HttpCode,
  // Header,
  // Redirect,
  Query,
  // Delete,
  // Put,
  Param,
  Body,
} from "@nestjs/common";
import { HotelService } from "./hotel.service";

@Controller()
export class HotelController {
  constructor(private hotelService: HotelService) {}

  @Get("get-locations")
  getLocations() {
    return this.hotelService.getLocations();
  }

  @Get("get-cities")
  getCities() {
    return this.hotelService.getCities();
  }

  @Get("get-hotels")
  getHotels() {
    return this.hotelService.getHotels();
  }

  @Get("get-hotel-by-id/:id")
  getHotelById(@Param("id") id: string) {
    return this.hotelService.getHotelById(id);
  }

  @Get("get-filtered-hotels")
  getFilteredHotels(@Query() query) {
    return this.hotelService.getFilteredHotels(query.city);
  }

  @Get("get-user-reservations/:id")
  getUserReservations(@Param("id") id: string) {
    return this.hotelService.getUserReservations(id);
  }

  @Get("get-reservation-by-id/:id")
  getReservationById(@Param("id") id: string) {
    return this.hotelService.getReservationById(id);
  }

  @Post("create-reservation")
  createReservation(@Body() body) {
    return this.hotelService.createReservation(body);
  }
}
