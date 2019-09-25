import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MotelManagerTestModule } from '../../../test.module';
import { BiographyComponent } from 'app/entities/biography/biography.component';
import { BiographyService } from 'app/entities/biography/biography.service';
import { Biography } from 'app/shared/model/biography.model';

describe('Component Tests', () => {
  describe('Biography Management Component', () => {
    let comp: BiographyComponent;
    let fixture: ComponentFixture<BiographyComponent>;
    let service: BiographyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [BiographyComponent],
      })
        .overrideTemplate(BiographyComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiographyComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiographyService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Biography(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.biographies && comp.biographies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
