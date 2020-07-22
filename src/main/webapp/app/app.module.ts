import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MotelManagerSharedModule } from 'app/shared/shared.module';
import { MotelManagerCoreModule } from 'app/core/core.module';
import { MotelManagerAppRoutingModule } from './app-routing.module';
import { MotelManagerHomeModule } from './home/home.module';
import { MotelManagerEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule.withServerTransition({ appId: 'motelManagerApp' }),
    MotelManagerSharedModule,
    MotelManagerCoreModule,
    MotelManagerHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MotelManagerEntityModule,
    MotelManagerAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class MotelManagerAppModule {}
