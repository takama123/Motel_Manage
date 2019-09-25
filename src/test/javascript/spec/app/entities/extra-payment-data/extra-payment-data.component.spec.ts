import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MotelManagerTestModule } from '../../../test.module';
import { ExtraPaymentDataComponent } from 'app/entities/extra-payment-data/extra-payment-data.component';
import { ExtraPaymentDataService } from 'app/entities/extra-payment-data/extra-payment-data.service';
import { ExtraPaymentData } from 'app/shared/model/extra-payment-data.model';

describe('Component Tests', () => {
  describe('ExtraPaymentData Management Component', () => {
    let comp: ExtraPaymentDataComponent;
    let fixture: ComponentFixture<ExtraPaymentDataComponent>;
    let service: ExtraPaymentDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ExtraPaymentDataComponent],
      })
        .overrideTemplate(ExtraPaymentDataComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExtraPaymentDataComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExtraPaymentDataService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ExtraPaymentData(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.extraPaymentData && comp.extraPaymentData[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
