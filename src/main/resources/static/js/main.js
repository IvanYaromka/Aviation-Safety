//// The Vue build version to load with the `import` command
//// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
//import Vue from 'vue'
//import './plugins/axios'
//import './plugins/vuetify'
//import Vuetify from 'vuetify'
//import 'vuetify/dist/vuetify.min.css'
//import 'material-design-icons-iconfont/dist/material-design-icons.css'
//import App from './pages/App'
import router from './router'
//
import 'vue-resource/src/index.js'
//
Vue.use(VueResource);
Vue.http.headers.common['Access-Control-Allow-Origin'] = '*'
Vue.http.headers.common['Authorization'] = 'Basic ' + btoa('superadmin:superadmin')
//
//
//Vue.config.productionTip = false;
//Vue.use(Vuetify);
//
//import 'chart.js';
//import 'hchs-vue-charts';
//Vue.use(window.VueCharts);
//
//Chart.defaults.global.legend.display = false;
//
//
//export let countriesDump = [
//  {id: 1, name: "Belarus"},
//  {id: 2, name: "Lithuania"},
//  {id: 3, name: "Finland"},
//  {id: 4, name: "Denmark"},
//  {id: 5, name: "Australia"},
//  {id: 6, name: "Austria"},
//  {id: 7, name: "Sweden"},
//  {id: 8, name: "Israel"},
//  {id: 9, name: "Czech Republic"},
//  {id: 10, name: "Slovakia"},
//  {id: 11, name: "Hungary"},
//  {id: 12, name: "Indonesia"},
//  {id: 13, name: "Norway"},
//  {id: 14, name: "China"},
//  {id: 15, name: "Japan"},
//  {id: 16, name: "Ireland"},
//  {id: 17, name: "Latvia"}
//];
//
//export let airlinesDump = [
//  {id: 1, name: "Ryanair", country: countriesDump.find(c => c.name === "Ireland")},
//  {id: 2, name: "AirBaltic", country: countriesDump.find(c => c.name === "Latvia")},
//  {id: 3, name: "Wizz Air", country: countriesDump.find(c => c.name === "Hungary")}
//
//];
//
//export let airportsDump = [
//  {id: 1, name: "Minsk National Airport", icao: 'UMMS', country: countriesDump.find(c => c.name === "Belarus")},
//  {id: 2, name: "Vilnius Airport", icao: 'EYVI', country: countriesDump.find(c => c.name === "Lithuania")},
//  {id: 3, name: "Riga International Airport", icao: 'EVRA', country: countriesDump.find(c => c.name === "Latvia")}
//];
//
//export let pilotsDump = [
//  {id: 1, name: "John", surname: 'Doe', airline: airlinesDump[0]},
//  {id: 2, name: "Ivan", surname: 'Ivanou', airline: airlinesDump[1]},
//  {id: 3, name: "Aliaksandr", surname: 'Aleksandrovic', airline: airlinesDump[2]}
//];
//
//
//import VueResource from 'vue-resource';
//Vue.use(VueResource);
//
////export let host = "localhost:8847";
//
///* eslint-disable no-new */
//new Vue({
//  el: '#app',
//  router,
//  template: '<App/>',
//  components: {App}
//});



import Vue from 'vue'
import VueResource from 'vue-resource'
import Vuetify from 'vuetify'
import App from 'pages/App.vue'
import 'vuetify/dist/vuetify.min.css'


Vue.use(Vuetify)
Vue.use(VueResource)

new Vue({
  el: '#app',
  router,
  render: a => a(App)
})
