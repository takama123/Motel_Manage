import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MotelManagerTestModule } from '../../../test.module';
import { BillDetailsUpdateComponent } from 'app/entities/bill-details/bill-details-update.component';
import { BillDetailsService } from 'app/entities/bill-details/bill-details.service';
import { BillDetails } from 'app/shared/model/bill-details.model';

describe('Component Tests', () => {
  describe('BillDetails Management Update Component', () => {
    let comp: BillDetailsUpdateComponent;
    let fixture: ComponentFixture<BillDetailsUpdateComponent>;
    let service: BillDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [BillDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BillDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BillDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BillDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BillDetails(123);
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
        const entity = new BillDetails();
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
