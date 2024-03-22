import { Module } from "@nestjs/common";
import { MarriotController } from "./marriot.controller";
import { MarriotService } from "./marriot.service";

@Module({
  controllers: [MarriotController],
  providers: [MarriotService],
})
export class MarriotModule {}
