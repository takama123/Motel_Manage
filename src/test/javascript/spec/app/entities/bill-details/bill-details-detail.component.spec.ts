import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { BillDetailsDetailComponent } from 'app/entities/bill-details/bill-details-detail.component';
import { BillDetails } from 'app/shared/model/bill-details.model';

describe('Component Tests', () => {
  describe('BillDetails Management Detail Component', () => {
    let comp: BillDetailsDetailComponent;
    let fixture: ComponentFixture<BillDetailsDetailComponent>;
    const route = ({ data: of({ billDetails: new BillDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [BillDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BillDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BillDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load billDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.billDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
