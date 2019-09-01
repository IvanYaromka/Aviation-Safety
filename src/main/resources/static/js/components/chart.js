import Vue from 'vue'

Vue.component('chart', {
  props: ['title', 'dataset', 'labels', 'probability'],
  template: `
  <v-container>
    <v-container>
      <h1>{{ title }}, P = {{ probability }} %</h1>
    </v-container>
    <v-container align-start justify-start row fill-height>
      <chartjs-line :labels="labels" :data="dataset" :bind="true" :width="250" :height="250"/>
    </v-container>
  </v-container>
  `
});
