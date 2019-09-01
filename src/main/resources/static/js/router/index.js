import Vue from 'vue';
import Router from 'vue-router';

import AllCountries from '../pages/country/Country.vue';
import Query from '../pages/query/Query.vue';
import App from "../pages/App.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: '',
      component: App
    },
    {
      path: '/country/all',
      name: 'AllCountries',
      component: AllCountries
    },
    {
      path: '/query',
      name: 'Query',
      component: Query
    }
  ]
})
