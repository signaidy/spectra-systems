import { Test, TestingModule } from '@nestjs/testing';
import { HotelController } from './hotel.controller';
import { HotelService } from './hotel.service';

describe('AppController', () => {
  let appController: HotelController;

  beforeEach(async () => {
    const app: TestingModule = await Test.createTestingModule({
      controllers: [HotelController],
      providers: [HotelService],
    }).compile();

    appController = app.get<HotelController>(HotelController);
  });

  describe('root', () => {
    it('should return "Hello World!"', () => {
      expect(appController.getCities).toBe('Hello World!');
    });
  });
});
