<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" :clipped="$vuetify.breakpoint.lgAndUp" fixed app :mini-variant.sync="mini">
      <v-list class="pt-0" dense>
        <v-list-tile v-for="item in items" :key="item.title" @click="item.action">
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>


    <v-toolbar :clipped-left="$vuetify.breakpoint.lgAndUp" app fixed>
      <v-toolbar-title>
        <v-toolbar-side-icon @click.stop="mini = !mini"/>
        <span class="hidden-sm-and-down">Aviation Safety</span>
      </v-toolbar-title>
    </v-toolbar>

    <v-content>
      <v-container fluid fill-height>
        <v-layout align-space-around justify-start column>
          <h1>Some</h1>
        </v-layout>
      </v-container>
    </v-content>

    <v-content>
      <v-container fluid fill-height>
        <v-layout align-space-around justify-start column>
          <router-view/>
        </v-layout>
      </v-container>
    </v-content>

    <v-footer app>Aviation Safety 2019</v-footer>
  </v-app>
</template>

<script>
  export default {
    name: 'app',
    data () {
      return {
        drawer: true,
        items: [
          { title: 'Airline', icon: 'local_airport', action: () => this.$router.push('/country/all') },
          { title: 'Airports', icon: 'transfer_within_a_station', action: () => this.$router.push('/country/all') },
          { title: 'Airlines', icon: 'near_me', action: () => this.$router.push('/country/all') },
          { title: 'Countries', icon: 'flag', action: () => this.$router.push('/country/all') },
          { title: 'Flights', icon: 'flight_takeoff', action: () => this.$router.push('/country/all') },
          { title: 'Pilots', icon: 'airline_seat_recline_normal', action: () => this.$router.push('/country/all') },
          { title: 'Users', icon: 'perm_identity', action: () => this.$router.push('/country/all') },
          { title: 'Query', icon: 'search', action: () => this.$router.push('/query') },
          { title: 'Profile', icon: 'settings', action: () => this.$router.push('/country/all') },
          { title: 'Log Out', icon: 'power_settings_new', action: () => this.$router.push('/country/all') }
        ],
        mini: true,
        right: null
      }
    }
  }
</script>

<style>
</style>
