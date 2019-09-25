import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.MotelManagerCustomerModule),
      },
      {
        path: 'biography',
        loadChildren: () => import('./biography/biography.module').then(m => m.MotelManagerBiographyModule),
      },
      {
        path: 'motel',
        loadChildren: () => import('./motel/motel.module').then(m => m.MotelManagerMotelModule),
      },
      {
        path: 'file',
        loadChildren: () => import('./file/file.module').then(m => m.MotelManagerFileModule),
      },
      {
        path: 'room',
        loadChildren: () => import('./room/room.module').then(m => m.MotelManagerRoomModule),
      },
      {
        path: 'convenient',
        loadChildren: () => import('./convenient/convenient.module').then(m => m.MotelManagerConvenientModule),
      },
      {
        path: 'services',
        loadChildren: () => import('./services/services.module').then(m => m.MotelManagerServicesModule),
      },
      {
        path: 'contract',
        loadChildren: () => import('./contract/contract.module').then(m => m.MotelManagerContractModule),
      },
      {
        path: 'bill-details',
        loadChildren: () => import('./bill-details/bill-details.module').then(m => m.MotelManagerBillDetailsModule),
      },
      {
        path: 'extra-payment-data',
        loadChildren: () => import('./extra-payment-data/extra-payment-data.module').then(m => m.MotelManagerExtraPaymentDataModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MotelManagerEntityModule {}
