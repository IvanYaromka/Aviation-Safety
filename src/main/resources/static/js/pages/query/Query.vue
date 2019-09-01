<template>
  <div>
    <v-tabs v-model="active" slider-color="cyan" align-start v-if="dataprovider">
      <v-tab ripple>Airline</v-tab>
      <v-tab ripple>Airport</v-tab>
      <v-tab ripple>Pilot</v-tab>
      <v-tab-item>
        <query type="Airline" :autocomplete="airlines" :items="arr => arr.map(a => a.name)" :handler="calculateButtonPressed"/>
      </v-tab-item>
      <v-tab-item>
        <query type="Airport" :autocomplete="airports" :items="arr => arr.map(a => a.name)"/>
      </v-tab-item>
      <v-tab-item>
        <query type="Pilot" :autocomplete="pilots" :items="arr => arr.map(p => p.name + ' ' + p.surname)"/>
      </v-tab-item>
    </v-tabs>
    <v-container grid-list-md text-xs-center v-else>
      <v-layout row wrap>
        <v-flex xs6 align-center>
          <chart :title="rool.title" :dataset="rool.dataset" :labels="rool.labels" :probability="rool.probability"/>
        </v-flex>
        <v-flex xs6 align-start>
          <chart :title="takeOffPitch.title" :dataset="takeOffPitch.dataset" :labels="takeOffPitch.labels" :probability="takeOffPitch.probability"/>
        </v-flex>
        <v-flex xs6 align-center>
          <chart :title="landPitch.title" :dataset="landPitch.dataset" :labels="landPitch.labels" :probability="landPitch.probability"/>
        </v-flex>
        <v-flex xs6 align-center>
          <chart :title="grofce.title" :dataset="grofce.dataset" :labels="grofce.labels" :probability="grofce.probability"/>
        </v-flex>
        <v-flex xs12 align-center>
          <h1>Q = 3.3 %</h1>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
  import {airlinesDump, airportsDump, pilotsDump} from "../../main.js";
  import query from '../../components/query.js'
  import chart from '../../components/chart.js'

  export default {
    name: "all-countries",
    data: function () {
      return {
        airlines: airlinesDump,
        airports: airportsDump,
        pilots: pilotsDump,
        dataprovider: true,
        rool: {
          title: 'Rool',
          dataset: [13, 43, 32, 23, 32, 43, 54, 55, 34, 21],
          labels: ['April 1', 'April 2', 'April 5', 'April 6', 'April 8', 'April 9', 'April 15', 'April 17', 'April 19', 'April 20'],
          probability: 73.1
        },
        takeOffPitch: {
          title: 'Take off pitch',
          dataset: [13, 14, 13, 13, 14, 15, 14, 16, 18, 13],
          labels: ['April 1', 'April 2', 'April 5', 'April 6', 'April 8', 'April 9', 'April 15', 'April 17', 'April 19', 'April 20'],
          probability: 86.5
        },
        landPitch: {
          title: 'Land pitch',
          dataset: [14, 18, 17, 15, 14, 14, 13, 15, 14, 15],
          labels: ['April 1', 'April 2', 'April 5', 'April 6', 'April 8', 'April 9', 'April 15', 'April 17', 'April 19', 'April 20'],
          probability: 88.3
        },
        grofce: {
          title: 'G-force',
          dataset: [2.1, 2.5, 2.7, 2.1, 2.2, 2.8, 3.2, 2.7, 3.4, 2.4],
          labels: ['April 1', 'April 2', 'April 5', 'April 6', 'April 8', 'April 9', 'April 15', 'April 17', 'April 19', 'April 20'],
          probability: 77.4
        }
      }
    },
    methods: {
      calculateButtonPressed: function () {
        this.dataprovider = false;
      }
    }
  }
</script>

<style scoped>

</style>
