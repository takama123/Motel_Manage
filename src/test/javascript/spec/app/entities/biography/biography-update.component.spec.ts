import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { BiographyUpdateComponent } from 'app/entities/biography/biography-update.component';
import { BiographyService } from 'app/entities/biography/biography.service';
import { Biography } from 'app/shared/model/biography.model';

describe('Component Tests', () => {
  describe('Biography Management Update Component', () => {
    let comp: BiographyUpdateComponent;
    let fixture: ComponentFixture<BiographyUpdateComponent>;
    let service: BiographyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [BiographyUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BiographyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BiographyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BiographyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Biography(123);
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
        const entity = new Biography();
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
