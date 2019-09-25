import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { ExtraPaymentDataDetailComponent } from 'app/entities/extra-payment-data/extra-payment-data-detail.component';
import { ExtraPaymentData } from 'app/shared/model/extra-payment-data.model';

describe('Component Tests', () => {
  describe('ExtraPaymentData Management Detail Component', () => {
    let comp: ExtraPaymentDataDetailComponent;
    let fixture: ComponentFixture<ExtraPaymentDataDetailComponent>;
    const route = ({ data: of({ extraPaymentData: new ExtraPaymentData(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ExtraPaymentDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ExtraPaymentDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExtraPaymentDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load extraPaymentData on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.extraPaymentData).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
