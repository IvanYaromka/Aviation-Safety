import Vue from 'vue'
import {countriesDump} from '../main.js'

Vue.component('country', {
  props: ['country'],
  template: `
  <div>
    <h1>Country</h1>
    <div>
      <v-text-field v-model='countryName' :label="country.name"></v-text-field>
      <v-btn color="success" @click="save">Save</v-btn>
      <v-btn color="error" @click="del" v-if="country.id > 0">Delete</v-btn>
    </div>
  </div>
  `,
   data: function () {
      return {
        countryName: this.props['country'].name
      }
    },
    methods: {
      save: function() {
        let id = this.country.id;
        let name = this.countryName;
        if (id === 0) {
          countriesDump.push({id: countriesDump[countriesDump.length - 1].id + 1, name: name});
        } else {
          countriesDump.find(c => c.id === id).name = name;
        }
      },
      del: function () {
        let id = this.country.id;
        let ind = countriesDump.findIndex(c => c.id === id);
        countriesDump.splice(ind, ind + 1);
        this.country.id = 0;
        this.country.name = 'Enter name';
        this.countryName ='';
      },
      hasErrors: function() {
        return this.hasErrors;
      }
    }
});
