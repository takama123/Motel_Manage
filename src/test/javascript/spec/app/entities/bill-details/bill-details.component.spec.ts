import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MotelManagerTestModule } from '../../../test.module';
import { BillDetailsComponent } from 'app/entities/bill-details/bill-details.component';
import { BillDetailsService } from 'app/entities/bill-details/bill-details.service';
import { BillDetails } from 'app/shared/model/bill-details.model';

describe('Component Tests', () => {
  describe('BillDetails Management Component', () => {
    let comp: BillDetailsComponent;
    let fixture: ComponentFixture<BillDetailsComponent>;
    let service: BillDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [BillDetailsComponent],
      })
        .overrideTemplate(BillDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BillDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.billDetails && comp.billDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
