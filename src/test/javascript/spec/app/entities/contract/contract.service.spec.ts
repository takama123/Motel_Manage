import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ContractService } from 'app/entities/contract/contract.service';
import { IContract, Contract } from 'app/shared/model/contract.model';

describe('Service Tests', () => {
  describe('Contract Service', () => {
    let injector: TestBed;
    let service: ContractService;
    let httpMock: HttpTestingController;
    let elemDefault: IContract;
    let expectedResult: IContract | IContract[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ContractService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Contract(0, currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            checkInDate: currentDate.format(DATE_FORMAT),
            checkOutDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Contract', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            checkInDate: currentDate.format(DATE_FORMAT),
            checkOutDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            checkInDate: currentDate,
            checkOutDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Contract()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Contract', () => {
        const returnedFromService = Object.assign(
          {
            checkInDate: currentDate.format(DATE_FORMAT),
            checkOutDate: currentDate.format(DATE_FORMAT),
            decription: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            checkInDate: currentDate,
            checkOutDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Contract', () => {
        const returnedFromService = Object.assign(
          {
            checkInDate: currentDate.format(DATE_FORMAT),
            checkOutDate: currentDate.format(DATE_FORMAT),
            decription: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            checkInDate: currentDate,
            checkOutDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Contract', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
