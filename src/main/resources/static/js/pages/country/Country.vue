<template>
  <div>
    <v-container fluid fill-width>
      <v-btn color="success" @click="selectActiveCountry(0)" >Add</v-btn>
    </v-container>
    <v-container fluid fill-height>
      <v-layout row justify-space-between>
        <v-flex xs4 order-md1 order-xs3>
          <v-list>
            <v-list-tile v-for="country in countries" :key="country.id" @click="selectActiveCountry(country.id)">

              <v-list-tile>
                <v-list-tile-title v-text="country.id"/>
              </v-list-tile>

              <v-list-tile-content>
                <v-list-tile-title v-text="country.name"/>
              </v-list-tile-content>

            </v-list-tile>
          </v-list>
        </v-flex>
        <v-flex xs6 order-lg2>
          <country :country="activeCountry" v-if="activeCountry != null"/>
        </v-flex>
      </v-layout>
    </v-container>
  </div>

</template>

<script>
  import '../../api/country/countryApi'
  import country from '../../components/country.js'
  import countryApi from "../../api/country/countryApi";

  export default {
    name: "all-countries",
    data: function () {
      return {
        countries: [],
        activeCountry: null
      }
    },
    created: function () {
       countryApi.findAll().then(res => this.countries = res.body);
    },
    methods: {
      selectActiveCountry: function (id) {
        if (id === 0) {
          this.activeCountry = {id: 0, name: "Enter name"}
        } else {
          this.activeCountry = this.countries.find(c => c.id === id)
        }
      }
    }
  }
</script>

<style scoped>

</style>
