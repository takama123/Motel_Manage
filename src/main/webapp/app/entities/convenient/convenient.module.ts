import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MotelManagerSharedModule } from 'app/shared/shared.module';
import { ConvenientComponent } from './convenient.component';
import { ConvenientDetailComponent } from './convenient-detail.component';
import { ConvenientUpdateComponent } from './convenient-update.component';
import { ConvenientDeleteDialogComponent } from './convenient-delete-dialog.component';
import { convenientRoute } from './convenient.route';

@NgModule({
  imports: [MotelManagerSharedModule, RouterModule.forChild(convenientRoute)],
  declarations: [ConvenientComponent, ConvenientDetailComponent, ConvenientUpdateComponent, ConvenientDeleteDialogComponent],
  entryComponents: [ConvenientDeleteDialogComponent],
})
export class MotelManagerConvenientModule {}
