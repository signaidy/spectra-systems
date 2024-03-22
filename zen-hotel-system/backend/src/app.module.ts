import { Module } from "@nestjs/common";
import { MarriotModule } from "./marriot/marriot.module";
@Module({
  imports: [MarriotModule],
})
export class AppModule {}
