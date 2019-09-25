import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { ExtraPaymentDataUpdateComponent } from 'app/entities/extra-payment-data/extra-payment-data-update.component';
import { ExtraPaymentDataService } from 'app/entities/extra-payment-data/extra-payment-data.service';
import { ExtraPaymentData } from 'app/shared/model/extra-payment-data.model';

describe('Component Tests', () => {
  describe('ExtraPaymentData Management Update Component', () => {
    let comp: ExtraPaymentDataUpdateComponent;
    let fixture: ComponentFixture<ExtraPaymentDataUpdateComponent>;
    let service: ExtraPaymentDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ExtraPaymentDataUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ExtraPaymentDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExtraPaymentDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExtraPaymentDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ExtraPaymentData(123);
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
        const entity = new ExtraPaymentData();
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
