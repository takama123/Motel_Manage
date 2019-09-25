import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { MotelDetailComponent } from 'app/entities/motel/motel-detail.component';
import { Motel } from 'app/shared/model/motel.model';

describe('Component Tests', () => {
  describe('Motel Management Detail Component', () => {
    let comp: MotelDetailComponent;
    let fixture: ComponentFixture<MotelDetailComponent>;
    const route = ({ data: of({ motel: new Motel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [MotelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MotelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MotelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load motel on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.motel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
