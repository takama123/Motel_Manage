import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MotelManagerTestModule } from '../../../test.module';
import { ConvenientComponent } from 'app/entities/convenient/convenient.component';
import { ConvenientService } from 'app/entities/convenient/convenient.service';
import { Convenient } from 'app/shared/model/convenient.model';

describe('Component Tests', () => {
  describe('Convenient Management Component', () => {
    let comp: ConvenientComponent;
    let fixture: ComponentFixture<ConvenientComponent>;
    let service: ConvenientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ConvenientComponent],
      })
        .overrideTemplate(ConvenientComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConvenientComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConvenientService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Convenient(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.convenients && comp.convenients[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
