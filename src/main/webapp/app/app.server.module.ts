import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { MotelManagerAppModule } from './app.module';
import { MainComponent } from './layouts/main/main.component';

@NgModule({
  imports: [MotelManagerAppModule, ServerModule],
  bootstrap: [MainComponent],
})
export class JhipsterAppServerModule {}
