import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MotelManagerSharedModule } from 'app/shared/shared.module';
import { MotelComponent } from './motel.component';
import { MotelDetailComponent } from './motel-detail.component';
import { MotelUpdateComponent } from './motel-update.component';
import { MotelDeleteDialogComponent } from './motel-delete-dialog.component';
import { motelRoute } from './motel.route';

@NgModule({
  imports: [MotelManagerSharedModule, RouterModule.forChild(motelRoute)],
  declarations: [MotelComponent, MotelDetailComponent, MotelUpdateComponent, MotelDeleteDialogComponent],
  entryComponents: [MotelDeleteDialogComponent],
})
export class MotelManagerMotelModule {}
