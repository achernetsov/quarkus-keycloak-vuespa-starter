import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import Keycloak from 'keycloak-js'
import { createPinia } from 'pinia'

let initOptions = {
    url: 'http://localhost:8180', realm: 'autofaqer', clientId: 'frontend'
    // , onLoad: 'login-required'
  }
let keycloak: Keycloak.KeycloakInstance = Keycloak(initOptions);

keycloak.init({ onLoad: 'login-required' }).then((auth) => {
    if (!auth) {
      window.location.reload();
    } else {
    //   Vue.$log.info("Authenticated");

    console.log('Authenticated')

    const app = createApp(App, {keycloak: keycloak})

    app.use(router)
    app.use(createPinia())

    app.mount('#app')

    //   new Vue({
    //     el: '#app',
    //     render: h => h(App, { props: { keycloak: keycloak } })
    //   })
    }

//Token Refresh
setInterval(() => {
  keycloak.updateToken(70).then((refreshed) => {
      if (refreshed) {
        // Vue.$log.info('Token refreshed' + refreshed);
        console.debug("Token refreshed")
      } else {
        // console.warn('Token not refreshed, valid for '
        //   + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
        console.warn('Token not refreshed, valid')
      }
    }).catch(() => {
      console.error('Failed to refresh token');
    });
  }, 6000)

}).catch(() => {
  console.error("Authenticated Failed");
});