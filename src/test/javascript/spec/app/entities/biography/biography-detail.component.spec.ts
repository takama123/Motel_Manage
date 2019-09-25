import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { BiographyDetailComponent } from 'app/entities/biography/biography-detail.component';
import { Biography } from 'app/shared/model/biography.model';

describe('Component Tests', () => {
  describe('Biography Management Detail Component', () => {
    let comp: BiographyDetailComponent;
    let fixture: ComponentFixture<BiographyDetailComponent>;
    const route = ({ data: of({ biography: new Biography(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [BiographyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BiographyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BiographyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load biography on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.biography).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
