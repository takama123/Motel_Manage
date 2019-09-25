import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MotelManagerTestModule } from '../../../test.module';
import { ContractComponent } from 'app/entities/contract/contract.component';
import { ContractService } from 'app/entities/contract/contract.service';
import { Contract } from 'app/shared/model/contract.model';

describe('Component Tests', () => {
  describe('Contract Management Component', () => {
    let comp: ContractComponent;
    let fixture: ComponentFixture<ContractComponent>;
    let service: ContractService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MotelManagerTestModule],
        declarations: [ContractComponent],
      })
        .overrideTemplate(ContractComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContractComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContractService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Contract(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contracts && comp.contracts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
