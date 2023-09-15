import axios from 'axios'

const API = axios.create({
    // local
    baseURL: 'http://127.0.0.1:8081/',
    // server
    // baseURL: 'https://127.0.0.1:8081/',
    headers: {
      "Content-Type": "application/json",
    },
    // withCredentials: true,
})
export default API;