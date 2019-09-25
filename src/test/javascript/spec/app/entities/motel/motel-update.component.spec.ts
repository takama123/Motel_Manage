import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { MotelUpdateComponent } from 'app/entities/motel/motel-update.component';
import { MotelService } from 'app/entities/motel/motel.service';
import { Motel } from 'app/shared/model/motel.model';

describe('Component Tests', () => {
  describe('Motel Management Update Component', () => {
    let comp: MotelUpdateComponent;
    let fixture: ComponentFixture<MotelUpdateComponent>;
    let service: MotelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [MotelUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MotelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MotelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MotelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Motel(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Motel();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
