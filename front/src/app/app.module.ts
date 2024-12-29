import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { RouterModule } from "@angular/router";
import { AuthModule } from "./modules/auth/auth.module";
import { SessionModule } from "./modules/session/session.module";
import { AppComponent } from "./app.component";

const MatModule: never[] = []
  
  @NgModule({
    declarations: [AppComponent],
    imports: [
        BrowserModule,
        ...MatModule,
        RouterModule,
        AuthModule,
        SessionModule
    ],
    providers: [],
    bootstrap: [AppComponent],
  })
  export class AppModule {}