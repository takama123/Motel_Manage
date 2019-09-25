import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MotelManagerTestModule } from '../../../test.module';
import { MotelComponent } from 'app/entities/motel/motel.component';
import { MotelService } from 'app/entities/motel/motel.service';
import { Motel } from 'app/shared/model/motel.model';

describe('Component Tests', () => {
  describe('Motel Management Component', () => {
    let comp: MotelComponent;
    let fixture: ComponentFixture<MotelComponent>;
    let service: MotelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [MotelComponent],
      })
        .overrideTemplate(MotelComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MotelComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MotelService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Motel(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.motels && comp.motels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
