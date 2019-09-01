import Vue from 'vue'

Vue.component('query', {
  props: ['type', 'autocomplete', 'items', 'handler'],
  template: `
  <div>
    <div>
      <v-container>
      <v-autocomplete
            v-model="model"
            :items="items(autocomplete)"
            :loading="isLoading"
            :search-input.sync="search"
            color="white"
            hide-no-data
            hide-selected
            item-text="Description"
            item-value="API"
            placeholder="Start typing to Search"
            prepend-icon="mdi-database-search"
            return-object
      />
        <v-expand-transition>
          <v-list v-if="model">
            <v-list-tile v-for="(field, i) in fields" :key="i">
              <v-list-tile-content>
                <v-list-tile-title v-text="field.value"></v-list-tile-title>
                <v-list-tile-sub-title v-text="field.key"></v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-list>
        </v-expand-transition>
      </v-container>
    </div>
    <div>
      <v-container align-center justify-center row fill-height>
        <div>
          <v-container fluid fill-width>
            <h1>Begin date</h1>
            <v-date-picker v-model="beginPicker" color="cyan" :landscape="landscape" :reactive="reactive"/>
          </v-container>
        </div>
        <div>
          <v-container fluid fill-width>
            <h1>End date</h1>
            <v-date-picker v-model="endPicker" color="cyan" :landscape="landscape" :reactive="reactive"/>
          </v-container>
        </div>
      </v-container>
    </div>
    <div>
      <v-container align-center justify-center row fill-height>
        <v-btn large color="secondary" @click="handler">Calculate</v-btn>
      </v-container>
    </div>
  </div>
  `,
  data: function () {
    return {
      entries: [],
      isLoading: false,
      model: null,
      search: null,
      beginPicker: new Date().toISOString().substr(0, 10),
      endPicker: new Date().toISOString().substr(0, 10),
      landscape: true,
      reactive: false
    }
  },
  watch: {
    search(val) {
      // Items have already been loaded
      if (this.items.length > 0) {
        return;
      }

      // Items have already been requested
      if (this.isLoading) {
        return;
      }

      this.isLoading = true;

      // Lazily load input items
      /*fetch('https://api.publicapis.org/entries')
        .then(res => res.json())
        .then(res => {
          const { count, entries } = res
          this.count = count
          this.entries = entries
        })
        .catch(err =>  {
          console.log(err)
        })
        .finally(() => (this.isLoading = false))*/

      this.count = this.autocomplete.length;
      this.isLoading = false;
    }
  }
});
