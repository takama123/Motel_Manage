import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { ConvenientUpdateComponent } from 'app/entities/convenient/convenient-update.component';
import { ConvenientService } from 'app/entities/convenient/convenient.service';
import { Convenient } from 'app/shared/model/convenient.model';

describe('Component Tests', () => {
  describe('Convenient Management Update Component', () => {
    let comp: ConvenientUpdateComponent;
    let fixture: ComponentFixture<ConvenientUpdateComponent>;
    let service: ConvenientService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ConvenientUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ConvenientUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConvenientUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConvenientService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Convenient(123);
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
        const entity = new Convenient();
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
