import { Module } from "@nestjs/common";
import { HotelModule } from "./hotel.module";
import { ConfigModule } from "@nestjs/config";

@Module({
  imports: [HotelModule, ConfigModule.forRoot({ isGlobal: true })],
})
export class AppModule {}
