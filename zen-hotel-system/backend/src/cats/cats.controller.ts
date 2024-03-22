import {
  Controller,
  Get,
  Req,
  Post,
  HttpCode,
  Header,
  Redirect,
  Query,
  Delete,
  Put,
  Param,
  Body,
} from "@nestjs/common";
import { Request } from "express";
import { CatsService } from './cats.service';

export interface Cat {
  name: string;
  age: number;
  breed: string;
}

export class CreateCatDto {
  name: string;
  age: number;
  breed: string;
}

export class UpdateCatDto {
  name: string;
  age: number;
  breed: string;
}

@Controller("cats")
export class CatsController {
  constructor(private catsService: CatsService) {}

  @Post()
  async create(@Body() createCatDto: CreateCatDto) {
    this.catsService.create(createCatDto);
  }

  @Get()
  async findAll(): Promise<Cat[]> {
    return this.catsService.findAll();
  }

  // @Get()
  // @Get("dogs") matches /cats/dogs
  // @Get(":id"")
  // @HttpCode(204)
  // @Header('Cache-Control', 'none')
  // @Redirect('https://nestjs.com', 301)
  // findAll(@Query() query, @Req() request: Request) {
  //   return `This action returns all cats (limit: ${query.limit} items)`;
  // }

  @Get(":id")
  findOne(@Param("id") id: string) {
    return `This action returns a #${id} cat`;
  }

  @Put(":id")
  update(@Param("id") id: string, @Body() updateCatDto: UpdateCatDto) {
    return `This action updates a #${id} cat`;
  }

  // @Post()
  // create(@Body() createCatDto: CreateCatDto) {
  //   return "This action adds a new cat";
  // }

  @Delete(":id")
  remove(@Param("id") id: string) {
    return `This action removes a #${id} cat`;
  }
}
