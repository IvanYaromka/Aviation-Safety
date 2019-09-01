import Vue from 'vue'

const counrtiesUrl = 'http://localhost:9000/countries';

export default {
    findAll: () => Vue.http.get(counrtiesUrl)//,
    //findById: id => Vue.http.get(counrtiesUrl, {id: id}),
    //add: country => Vue.http.post(counrtiesUrl, country),
    //update: country => Vue.http.put(counrtiesUrl, {id: country.id}, country),
    //remove: id => Vue.http.delete(counrtiesUrl, {id}),
    //page: page => Vue.http.get('/message', {params: { page }})
}