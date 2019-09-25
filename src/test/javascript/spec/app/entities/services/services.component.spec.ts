import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MotelManagerTestModule } from '../../../test.module';
import { ServicesComponent } from 'app/entities/services/services.component';
import { ServicesService } from 'app/entities/services/services.service';
import { Services } from 'app/shared/model/services.model';

describe('Component Tests', () => {
  describe('Services Management Component', () => {
    let comp: ServicesComponent;
    let fixture: ComponentFixture<ServicesComponent>;
    let service: ServicesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ServicesComponent],
      })
        .overrideTemplate(ServicesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServicesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServicesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Services(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.services && comp.services[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
