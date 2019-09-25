import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MotelManagerSharedModule } from 'app/shared/shared.module';
import { BiographyComponent } from './biography.component';
import { BiographyDetailComponent } from './biography-detail.component';
import { BiographyUpdateComponent } from './biography-update.component';
import { BiographyDeleteDialogComponent } from './biography-delete-dialog.component';
import { biographyRoute } from './biography.route';

@NgModule({
  imports: [MotelManagerSharedModule, RouterModule.forChild(biographyRoute)],
  declarations: [BiographyComponent, BiographyDetailComponent, BiographyUpdateComponent, BiographyDeleteDialogComponent],
  entryComponents: [BiographyDeleteDialogComponent],
})
export class MotelManagerBiographyModule {}
