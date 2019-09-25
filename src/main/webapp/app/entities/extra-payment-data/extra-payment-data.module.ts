import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MotelManagerSharedModule } from 'app/shared/shared.module';
import { ExtraPaymentDataComponent } from './extra-payment-data.component';
import { ExtraPaymentDataDetailComponent } from './extra-payment-data-detail.component';
import { ExtraPaymentDataUpdateComponent } from './extra-payment-data-update.component';
import { ExtraPaymentDataDeleteDialogComponent } from './extra-payment-data-delete-dialog.component';
import { extraPaymentDataRoute } from './extra-payment-data.route';

@NgModule({
  imports: [MotelManagerSharedModule, RouterModule.forChild(extraPaymentDataRoute)],
  declarations: [
    ExtraPaymentDataComponent,
    ExtraPaymentDataDetailComponent,
    ExtraPaymentDataUpdateComponent,
    ExtraPaymentDataDeleteDialogComponent,
  ],
  entryComponents: [ExtraPaymentDataDeleteDialogComponent],
})
export class MotelManagerExtraPaymentDataModule {}
