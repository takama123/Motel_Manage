import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { ConvenientDetailComponent } from 'app/entities/convenient/convenient-detail.component';
import { Convenient } from 'app/shared/model/convenient.model';

describe('Component Tests', () => {
  describe('Convenient Management Detail Component', () => {
    let comp: ConvenientDetailComponent;
    let fixture: ComponentFixture<ConvenientDetailComponent>;
    const route = ({ data: of({ convenient: new Convenient(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ConvenientDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ConvenientDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConvenientDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load convenient on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.convenient).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
