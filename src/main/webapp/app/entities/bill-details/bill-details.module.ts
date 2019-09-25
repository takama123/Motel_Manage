import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MotelManagerSharedModule } from 'app/shared/shared.module';
import { BillDetailsComponent } from './bill-details.component';
import { BillDetailsDetailComponent } from './bill-details-detail.component';
import { BillDetailsUpdateComponent } from './bill-details-update.component';
import { BillDetailsDeleteDialogComponent } from './bill-details-delete-dialog.component';
import { billDetailsRoute } from './bill-details.route';

@NgModule({
  imports: [MotelManagerSharedModule, RouterModule.forChild(billDetailsRoute)],
  declarations: [BillDetailsComponent, BillDetailsDetailComponent, BillDetailsUpdateComponent, BillDetailsDeleteDialogComponent],
  entryComponents: [BillDetailsDeleteDialogComponent],
})
export class MotelManagerBillDetailsModule {}
